package com.example.demo.service;

import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.Commune;
import com.example.demo.dto.response.District;
import com.example.demo.dto.response.Province;
import com.example.demo.model.Address;
import com.example.demo.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    public String getTokenGoogle(String code);

    public User GoogleAccountGetUserInfo(String accessToken);

    public User login(UserRequest userRequest);

    public User register(UserRequest userRequest, MultipartFile file);

    public User findByEmail(String email);

    public List<Province> fetchProvinces();

    public List<District> fetchDistricts(String provinceId);

    public List<Commune> fetchCommunes(String districtId);

    public boolean forgotPassword(String email);

    public User changePassword(Integer id, UserRequest userRequest);

    public User edit(Integer id, UserRequest userRequest, MultipartFile file);

    public Address addAddress(Integer id,UserRequest userRequest);

    public List<Address> getAddress(Integer id);

    public Address deleteAddress(Integer id, Integer addressId);
}
