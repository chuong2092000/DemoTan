package com.example.demo.service.impl;

import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.Commune;
import com.example.demo.dto.response.District;
import com.example.demo.dto.response.Province;
import com.example.demo.model.Address;
import com.example.demo.model.User;
import com.example.demo.repo.AddressRepo;
import com.example.demo.repo.CommuneRepo;
import com.example.demo.repo.DistrictRepo;
import com.example.demo.repo.ProvinceRepo;
import com.example.demo.repo.RoleRepo;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.EmailService;
import com.example.demo.service.FileStorageService;
import com.example.demo.service.UserService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.demo.constant.ApiConstant.API_COMMUNE;
import static com.example.demo.constant.ApiConstant.API_DISTRICT;
import static com.example.demo.constant.ApiConstant.API_PROVINCE;
import static com.example.demo.constant.ApiConstant.HTM;
import static com.example.demo.constant.GoogleConstant.GOOGLE_CLIENT_ID;
import static com.example.demo.constant.GoogleConstant.GOOGLE_CLIENT_SECRET;
import static com.example.demo.constant.GoogleConstant.GOOGLE_GRANT_TYPE;
import static com.example.demo.constant.GoogleConstant.GOOGLE_LINK_GET_TOKEN;
import static com.example.demo.constant.GoogleConstant.GOOGLE_LINK_GET_USER_INFO;
import static com.example.demo.constant.GoogleConstant.GOOGLE_REDIRECT_URI;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final RoleRepo roleRepo;

    private final UserRepo userRepo;

    private final ProvinceRepo provinceRepo;

    private final DistrictRepo districtRepo;

    private final CommuneRepo communeRepo;

    private final FileStorageService fileStorageService;

    private final AddressRepo addressRepo;

    private final EmailService emailService;

    private Integer id = 0;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String getTokenGoogle(String code) {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(GOOGLE_LINK_GET_TOKEN))
                .POST(
                        HttpRequest.BodyPublishers.ofString("client_id=" + GOOGLE_CLIENT_ID +
                                "&client_secret=" + GOOGLE_CLIENT_SECRET +
                                "&redirect_uri=" + GOOGLE_REDIRECT_URI +
                                "&code=" + code +
                                "&grant_type=" + GOOGLE_GRANT_TYPE)
                )
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        String responseBody = "";
        String accessToken = "";
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                responseBody = response.body();
            } else {
                System.out.println("Error: " + response.statusCode());
            }
            if (!(responseBody.isEmpty())) {
                JsonObject jobj = JsonParser.parseString(responseBody).getAsJsonObject();
                accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return accessToken;
    }

    @Override
    public User GoogleAccountGetUserInfo(String accessToken) {

        String link = GOOGLE_LINK_GET_USER_INFO + accessToken;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(link))
                .GET()
                .build();

        String responseBody = "";
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                responseBody = response.body();
            } else {
                System.out.println("Error: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JsonObject jobj = JsonParser.parseString(responseBody).getAsJsonObject();
        System.out.println(jobj);
        User user = new User();
        user.setUsername(jobj.get("given_name").toString().replaceAll("\"", ""));
        user.setFullName(jobj.get("name").toString().replaceAll("\"", ""));
        user.setEmail(jobj.get("email").toString().replaceAll("\"", ""));
        user.setProfileImage(jobj.get("picture").toString().replaceAll("\"", ""));
        user.setRoleId(roleRepo.findById(2).orElse(null));
        user.setPassword("null");
        user.setBirthDate(new Date());
        user.setGender(true);
        user.setPhone("null");

        if (userRepo.existsByEmail(user.getEmail())) {
            user = userRepo.findByEmail(user.getEmail());
        }
        return user;
    }

    @Override
    public User login(UserRequest userRequest) {
        User user = findByEmail(userRequest.getEmail());
        if (user != null && user.getPassword().equals(userRequest.getPassword())) {
            id = user.getUsersId();
            return user;
        } else {
            return null;
        }
    }

    @Override
    public User register(UserRequest userRequest, MultipartFile file) {

        User user = new User();
        Address address = new Address();

        if (!userRepo.existsByEmail(userRequest.getEmail())) {

            user.setFullName(userRequest.getFullName());
            user.setUsername(userRequest.getUserName());
            user.setEmail(userRequest.getEmail());
            user.setPassword(userRequest.getPassword());
            user.setPhone(userRequest.getPhoneNumber());

            if (convertStringToDate(userRequest.getBirthDate()) != null) {

                user.setBirthDate(convertStringToDate(userRequest.getBirthDate()));
                user.setGender(userRequest.getGender());
                address.setStreet(userRequest.getStreetName());

                com.example.demo.model.Province province = new com.example.demo.model.Province(Integer.parseInt(userRequest.getIdProvince()), userRequest.getProvince());
                if (!provinceRepo.existsById(province.getProvince_id())) {

                    com.example.demo.model.Province save = provinceRepo.save(province);
                    address.setProvince(save);
                } else {

                    Optional<com.example.demo.model.Province> byId = provinceRepo.findById(province.getProvince_id());
                    address.setProvince(byId.orElse(null));
                }
                com.example.demo.model.District district = new com.example.demo.model.District(Integer.parseInt(userRequest.getIdDistrict()), userRequest.getDistrict(), province);
                if (!districtRepo.existsById(district.getDistrict_id())) {

                    com.example.demo.model.District save = districtRepo.save(district);
                    address.setDistrict(save);
                } else {

                    Optional<com.example.demo.model.District> byId = districtRepo.findById(district.getDistrict_id());
                    address.setDistrict(byId.orElse(null));
                }
                com.example.demo.model.Commune commune = new com.example.demo.model.Commune(Integer.parseInt(userRequest.getIdCommune()), userRequest.getCommune(), district);
                if (!communeRepo.existsById(commune.getCommune_id())) {

                    com.example.demo.model.Commune save = communeRepo.save(commune);
                    address.setCommune(save);
                } else {

                    Optional<com.example.demo.model.Commune> byId = communeRepo.findById(commune.getCommune_id());
                    address.setCommune(byId.orElse(null));
                }
                if (file != null) {
                    UUID uuid = UUID.randomUUID();
                    fileStorageService.save(file, uuid);// Save file to server
                    String fileUUID = uuid + (file.getOriginalFilename() != null ? file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.')) : "");
                    user.setProfileImage(fileUUID);
                    user.setRoleId(roleRepo.findById(2).orElse(null));
                    String buildEmail = emailService.buildEmail(user.getEmail(), user.getUsersId() + "", user.getPassword(), false);
                    emailService.send(user.getEmail(), buildEmail, "Register success");
                    User save = userRepo.save(user);
                    address.setUsers_id(save);
                    addressRepo.save(address);
                }
            }
            return user;
        } else {
            return null;
        }
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public List<Province> fetchProvinces() {
        ApiResponse response = restTemplate.getForObject(API_PROVINCE, ApiResponse.class);
        if (response != null && response.getError() == 0) {
            return response.getData();
        }
        return null;
    }

    @Override
    public List<District> fetchDistricts(String provinceId) {
        ApiResponse response = restTemplate.getForObject(API_DISTRICT + provinceId + HTM, ApiResponse.class);
        if (response != null && response.getError() == 0) {
            List<Province> provinceResponse = response.getData();
            return provinceResponse
                    .stream()
                    .map(province -> new District(province.getId(), province.getName()))
                    .toList();
        }
        return null;
    }

    @Override
    public List<Commune> fetchCommunes(String districtId) {
        ApiResponse response = restTemplate.getForObject(API_COMMUNE + districtId + HTM, ApiResponse.class);
        if (response != null && response.getError() == 0) {
            List<Province> provinceResponse = response.getData();
            return provinceResponse
                    .stream()
                    .map(province -> new Commune(province.getId(), province.getName()))
                    .toList();
        }
        return null;
    }

    @Override
    public boolean forgotPassword(String email) {
        User user = userRepo.findByEmail(email);
        if (user != null) {
            UUID uuid = UUID.randomUUID();
            user.setPassword(uuid.toString());
            String buildEmail = emailService.buildEmail(user.getEmail(), user.getUsersId() + "", uuid.toString(), true);
            if (emailService.send(user.getEmail(), buildEmail, "Forgot password")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User changePassword(Integer id, UserRequest userRequest) {
        User user = userRepo.findById(id).orElse(null);
        if (user != null) {
            if (user.getPassword().equals(userRequest.getCurrentPassword())) {
                if (userRequest.getNewPassword().equals(userRequest.getConfirmNewPassword())) {
                    user.setPassword(userRequest.getConfirmNewPassword());
                    String buildEmail = emailService.buildEmail(user.getEmail(), user.getUsersId() + "", "", true);
                    emailService.send(user.getEmail(), buildEmail, "You Just Change Password");
                    return userRepo.save(user);
                }
            }
        }
        return null;
    }

    @Override
    public User edit(Integer id, UserRequest userRequest, MultipartFile file) {

        User user = userRepo.findByUsersId(id);

        if (user != null) {
            user.setFullName(userRequest.getFullName());
            user.setUsername(userRequest.getUserName());
            user.setPhone(userRequest.getPhoneNumber());
            if (convertStringToDate(userRequest.getBirthDate()) != null) {
                user.setBirthDate(convertStringToDate(userRequest.getBirthDate()));
                user.setGender(userRequest.getGender());
                if (file != null) {
                    UUID uuid = UUID.randomUUID();
                    fileStorageService.save(file, uuid);// Save file to server
                    String fileUUID = uuid + (file.getOriginalFilename() != null ? file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.')) : "");
                    user.setProfileImage(fileUUID);
                    User save = userRepo.save(user);
                    return save;
                } else {
                    User save = userRepo.save(user);
                    return save;
                }
            }
        }
        return null;
    }

    @Override
    public Address addAddress(Integer id, UserRequest userRequest) {
        Address address = new Address();
        User user = userRepo.findByUsersId(id);
        if (user != null) {

            address.setStreet(userRequest.getStreetName());

            com.example.demo.model.Province province = new com.example.demo.model.Province(Integer.parseInt(userRequest.getIdProvince()), userRequest.getProvince());
            if (!provinceRepo.existsById(province.getProvince_id())) {

                com.example.demo.model.Province save = provinceRepo.save(province);
                address.setProvince(save);
            } else {

                Optional<com.example.demo.model.Province> byId = provinceRepo.findById(province.getProvince_id());
                address.setProvince(byId.orElse(null));
            }
            com.example.demo.model.District district = new com.example.demo.model.District(Integer.parseInt(userRequest.getIdDistrict()), userRequest.getDistrict(), province);
            if (!districtRepo.existsById(district.getDistrict_id())) {

                com.example.demo.model.District save = districtRepo.save(district);
                address.setDistrict(save);
            } else {

                Optional<com.example.demo.model.District> byId = districtRepo.findById(district.getDistrict_id());
                address.setDistrict(byId.orElse(null));
            }
            com.example.demo.model.Commune commune = new com.example.demo.model.Commune(Integer.parseInt(userRequest.getIdCommune()), userRequest.getCommune(), district);
            if (!communeRepo.existsById(commune.getCommune_id())) {

                com.example.demo.model.Commune save = communeRepo.save(commune);
                address.setCommune(save);
            } else {

                Optional<com.example.demo.model.Commune> byId = communeRepo.findById(commune.getCommune_id());
                address.setCommune(byId.orElse(null));
            }
            address.setUsers_id(user);
            System.out.println(address);
            return addressRepo.save(address);
        }
        return null;
    }

    @Override
    public List<Address> getAddress(Integer id) {
        System.out.println(addressRepo.findByUsers_id(id).get(0));
        return addressRepo.findByUsers_id(id);
    }

    @Override
    public Address deleteAddress(Integer id, Integer addressId) {
        Address address = addressRepo.findByUsers_idAndId(id, addressId);
        if (address != null) {
            address.setStatus(true);
            return addressRepo.save(address);
        }
        return null;
    }

    public Date convertStringToDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
