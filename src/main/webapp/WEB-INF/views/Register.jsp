<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register Form</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-image: url('https://p.w3layouts.com/demos_new/template_demo/09-06-2017/elegant_login_form-demo_Free/1686839134/web/images/bg2.jpeg');
            background-size: cover; /* Đảm bảo hình nền bao phủ toàn bộ trang */
            background-position: center; /* Căn giữa hình nền */
            background-attachment: fixed; /* Giữ hình nền cố định khi cuộn trang */
            background-repeat: no-repeat;
        }
        .error-message {
            display: none; /* Ẩn thông báo lỗi mặc định */
            padding: 10px;
            background-color: #f8d7da;
            color: red;
            border: 1px solid #f5c6cb;
            border-radius: 5px;
            margin-bottom: 15px;
        }
        .form-container {
            max-width: 600px;
            margin: 50px auto;
            padding: 30px;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .container.form-container {
            background: rgba(255, 255, 255, 0.8); /* Nền trắng nửa trong suốt */
            padding: 20px;
            border-radius: 8px; /* Làm cho góc của form tròn hơn */
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Thêm hiệu ứng bóng cho form */
        }

        .image-preview {
            margin-top: 10px;
            width: 100%;
            height: auto;
            border: 1px solid #ddd;
            border-radius: 4px;
            text-align: center;
            line-height: 150px;
            position: relative;
        }

        .image-preview img {
            max-width: 100%;
            max-height: 150px;
            border-radius: 4px;
        }

        .form-container h2 {
            color: #007bff; /* Màu xanh Bootstrap chính */
        }

        .form-container {
            border-radius: 8px; /* Bo góc biểu mẫu */
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Hiệu ứng bóng đổ */
            padding: 20px;
            background-color: #f0f8ff; /* Màu nền xanh nhạt */
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-control {
            border-radius: 4px; /* Bo góc các ô nhập liệu */
            box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.1); /* Hiệu ứng bóng đổ bên trong ô nhập liệu */
        }


        .text-center {
            color: #5bc0de; /* Màu xanh nhạt cho tiêu đề */
        }

        .image-preview img {
            max-width: 100%; /* Đảm bảo hình ảnh không vượt quá khung chứa */
            border-radius: 4px; /* Bo góc hình ảnh */
        }
    </style>
</head>
<body>
<div class="container form-container">
    <h2 class="text-center ">User Registration</h2>
    <form>
        <div class="row">
            <!-- Left Column -->
            <div class="col-md-6">
                <div class="form-group">
                    <label for="fullName">Full Name:</label>
                    <input name="fullName" type="text" class="form-control" id="fullName" placeholder="Enter full name"
                           required>
                </div>
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input name="userName" type="text" class="form-control" id="username" placeholder="Enter username"
                           required>
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" class="form-control" id="email" placeholder="Enter email" required>
                </div>

                <div class="form-group">
                    <label for="password">Password:</label>
                    <input name="password" type="password" class="form-control" id="password"
                           placeholder="Enter password" required>
                </div>
                <div class="form-group">
                    <label for="phone">Phone Number:</label>
                    <input name="phoneNumber" type="tel" class="form-control" id="phone"
                           placeholder="Enter phone number" required>
                </div>
                <div class="form-group">
                    <label for="gender">Gender:</label>
                    <select name="gender" class="form-control" id="gender" required>
                        <option value="">Select Gender</option>
                        <option value="true">Male</option>
                        <option value="false">Female</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="birthdate">Date of Birth:</label>
                    <input name="birthDate" type="date" class="form-control" id="birthdate" required>
                </div>
                <div id="errorMessage" class="error-message">Email is duplicate</div>
            </div>

            <!-- Right Column -->
            <div class="col-md-6">
                <div class="form-group">
                    <label for="streetName">Street Name</label>
                    <input name="streetName" type="tel" class="form-control" id="streetName"
                           placeholder="Enter street name" required>
                </div>
                <div class="form-group">
                    <label for="province">Province/City:</label>
                    <select name="province" class="form-control" id="province" required>
                        <option value="">Select Province/City</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="district">District:</label>
                    <select name="district" class="form-control" id="district" required>
                        <option value="">Select District</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="commune">Commune:</label>
                    <select name="commune" class="form-control" id="commune" required>
                        <option value="">Select Commune</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="profile-picture">Profile Picture:</label>
                    <input type="file" class="form-control-file" id="profile-picture" name="profilePicture"
                           accept="image/*" required>
                    <div class="image-preview" id="image-preview">
                        <img id="preview-image" src="" alt="Profile Picture" style="display: none;">
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <a href="/login" type="button" class="btn btn-success">Back</a>
            </div>
            <div class="col-md-6">
                <button id="btn-loading"  class="btn btn-block btn-primary" disabled >
                    <span class="spinner-border spinner-border-sm" aria-hidden="true"></span>
                    <span role="status">Loading...</span>
                </button>
                <button id="btn-submit" type="submit"  class="btn btn-primary btn-block">
                    Register
                </button>
            </div>
        </div>
    </form>
</div>


<script>
    document.getElementById('btn-loading').style.display = 'none';
    document.getElementById('btn-submit').style.display = 'block';
    var apiUrl = '/api/provinces';
    var address = {
        idProvince: null,
        province: null,
        idDistrict: null,
        district: null,
        idCommune: null,
        commune: null
    };
    fetch(apiUrl, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json;charset=utf-8',
        },
    })
        .then(response => response.text())
        .then(data => {
            var provinceSelect = document.getElementById('province');
            var jsondata = JSON.parse(data);
            jsondata.forEach(function (province) {
                var option = document.createElement('option');
                option.value = province.id;
                option.textContent = province.name;
                option.setAttribute('data-name', province.name);
                provinceSelect.appendChild(option);
            });
        })
        .catch(error => {
            console.error('Error:', error);
        });
    document.getElementById('province').addEventListener('change', function () {
        var provinceId = this.value;
        var provinceName = this.options[this.selectedIndex].getAttribute('data-name');
        address.idProvince = provinceId;
        address.province = provinceName;
        fetch('/api/districts/' + provinceId, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json;charset=utf-8',
            },
        })
            .then(response => response.text())
            .then(data => {
                var districtSelect = document.getElementById('district');
                districtSelect.replaceChildren();
                var option = document.createElement('option');
                option.textContent = 'Select District';
                var jsondata = JSON.parse(data);
                jsondata.forEach(function (district) {
                    var option = document.createElement('option');
                    option.value = district.id;
                    option.textContent = district.name;
                    option.setAttribute('data-name', district.name);
                    districtSelect.appendChild(option);
                });
            })
            .catch(error => {
                console.error('Error:', error);
            });
    });
    document.getElementById('district').addEventListener('change', function () {
        var districtName = this.options[this.selectedIndex].getAttribute('data-name');
        var districtId = this.value;
        address.idDistrict = districtId;
        address.district = districtName;
        fetch('/api/commune/' + districtId, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json;charset=utf-8',
            },
        })
            .then(response => response.text())
            .then(data => {
                var communeSelect = document.getElementById('commune');
                var jsondata = JSON.parse(data);
                communeSelect.replaceChildren();
                var option = document.createElement('option');
                option.textContent = 'Select Commune';
                jsondata.forEach(function (commune) {
                    var option = document.createElement('option');
                    option.value = commune.id;
                    option.textContent = commune.name;
                    option.setAttribute('data-name', commune.name);
                    communeSelect.appendChild(option);
                });
            })
            .catch(error => {
                console.error('Error:', error);
            });
    });
    document.getElementById('commune').addEventListener('change', function () {
        var communeId = this.value;
        var communeName = this.options[this.selectedIndex].getAttribute('data-name');
        address.idCommune = communeId;
        address.commune = communeName;
        console.log(address);
    });
    document.getElementById('profile-picture').addEventListener('change', function (event) {
        const file = event.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                const img = document.getElementById('preview-image');
                img.src = e.target.result;
                img.style.display = 'block';
            };
            reader.readAsDataURL(file);
        } else {
            const img = document.getElementById('preview-image');
            img.style.display = 'none';
        }
    });
    document.querySelector('form').addEventListener('submit', function (event) {
        event.preventDefault();
        function userRequest(fullName, userName, email, password, phoneNumber, birthDate, gender , streetName) {
            this.fullName = fullName;
            this.userName = userName;
            this.email = email;
            this.password = password;
            this.phoneNumber = phoneNumber;
            this.birthDate = birthDate;
            this.gender = gender;
            this.streetName = streetName;
            this.idProvince = address.idProvince;
            this.province = address.province;
            this.idDistrict = address.idDistrict;
            this.district = address.district;
            this.idCommune = address.idCommune;
            this.commune = address.commune;
        }

        var profilePicture = document.getElementById('profile-picture').files[0];
        var user = new userRequest(
            document.getElementById('fullName').value,
            document.getElementById('username').value,
            document.getElementById('email').value,
            document.getElementById('password').value,
            document.getElementById('phone').value,
            document.getElementById('birthdate').value,
            document.getElementById('gender').value,
            document.getElementById('streetName').value,
        );
        var formData = new FormData();
        formData.append('profilePicture', profilePicture);
        formData.append('userRequest', new Blob([JSON.stringify(user)], {
            type: 'application/json;text/plain;charset=UTF-8'
        }));
        document.getElementById('btn-loading').style.display = 'block';
        document.getElementById('btn-submit').style.display = 'none';
            fetch('/api/register', {
                method: 'POST',
                body: formData // data can be `string` or {object}!
            })
                .then(response => response.text())
                .then(data => {
                    console.log(data);
                    setTimeout(() => {
                        if (data === 'false'){
                            document.getElementById('btn-loading').style.display = 'none';
                            document.getElementById('btn-submit').style.display = 'block';
                            showError();
                        } else {
                            hideError();
                            window.location.href = '/login';
                        }
                    }, 3000);

                })
                .catch(error => {
                    console.error('Error:', error);
                });

    });
    function showError() {
        var errorMessage = document.getElementById('errorMessage');
        errorMessage.style.display = 'block'; // Hiển thị thông báo lỗi
    }
    function hideError() {
        var errorMessage = document.getElementById('errorMessage');
        errorMessage.textContent = 'Create account successfully!';
        errorMessage.style.display = 'block'; // Ẩn thông báo lỗi
        errorMessage.style.backgroundColor = '#d4edda'; // Màu nền xanh nhạt
        errorMessage.style.borderColor = '#c3e6cb'; // Màu viền xanh nhạt
        errorMessage.style.color = '#155724'; // Màu chữ xanh lá cây
    }
</script>
<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
