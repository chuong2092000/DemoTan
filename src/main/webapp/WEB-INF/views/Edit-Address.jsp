<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Address Form</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <style>
        .address-form {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            background-color: rgba(255, 255, 255, 0.8); /* Màu nền trắng với độ mờ 80% */
            backdrop-filter: blur(10px); /* Làm mờ nền phía sau */
        }

        body {
            background-image: url('https://p.w3layouts.com/demos_new/template_demo/09-06-2017/elegant_login_form-demo_Free/1686839134/web/images/bg2.jpeg');
            background-size: cover; /* Đảm bảo hình nền bao phủ toàn bộ trang */
            background-position: center; /* Căn giữa hình nền */
            background-attachment: fixed; /* Giữ hình nền cố định khi cuộn trang */
            background-repeat: no-repeat;
        }

        .address-item button {
            margin-top: 5px;
        }

        table {
            border: 2px solid #000; /* Viền ngoài của bảng */
            background-color: rgba(0, 0, 0, 0.05); /* Màu nền nhẹ cho bảng */
        }

        table th, table td {
            text-align: center;
            border: 1px solid #000; /* Viền các ô trong bảng */
            background-color: rgba(255, 255, 255, 0.9); /* Màu nền nhẹ cho các ô trong bảng */
            padding: 10px;
        }

        table th {
            background-color: rgba(0, 0, 0, 0.1); /* Màu nền cho tiêu đề bảng */
            font-weight: bold;
        }

        table td {
            font-weight: 500;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <div class="address-form">
        <h2 class="text-center">Manage Addresses</h2>

        <!-- Address Table -->
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Street Address</th>
                <th>Province</th>
                <th>District</th>
                <th>Commune</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody id="addressTableBody" style="color: #007bff">
            <c:forEach items="${addresses}" var="address">
                <tr class="address-item">
                    <td>${address.street}</td>
                    <td>${address.province.province_name}</td>
                    <td>${address.district.district_name}</td>
                    <td>${address.commune.commune_name}</td>
                    <td>
                        <button type="button" class="btn btn-danger btn-sm deleteAddressBtn" data-toggle="modal"
                                data-target="#deleteAddressModal" data-id="${address.id}">Delete
                        </button>
                    </td>
                </tr>
            </c:forEach>
            <!-- Existing addresses will be populated here -->
            </tbody>
        </table>

        <!-- Form to Add/Edit Address -->
        <form id="addressForm" class="mt-4">
            <h4>Add/Edit Address</h4>
            <div id="addressFields">
                <div id="idUser" style="display: none" data-idUser="${user.usersId}"></div>
                <div class="form-group">
                    <label for="streetName">Street Address</label>
                    <input type="text" class="form-control" id="streetName" name="streetName"
                           placeholder="Enter street address"
                           required>
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
            </div>
            <div class="mt-3" style="display: ${display};color: ${color}">${message}</div>
            <div class="row">
                <div class="col-md-4">
                    <a href="/success" type="button" class="btn btn-success">Back</a>
                </div>
                <div class="col-md-8">
                    <button type="button" class="btn btn-primary btn-block" id="saveAddressBtn">Add Address</button>
                </div>
            </div>
        </form>
    </div>
</div>

<!-- Delete Confirmation Modal -->
<div class="modal fade" id="deleteAddressModal" tabindex="-1" role="dialog" aria-labelledby="deleteAddressModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteAddressModalLabel">Delete Address</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Are you sure you want to delete this address?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                <button type="button" class="btn btn-danger" id="confirmDeleteBtn">Delete</button>
            </div>
        </div>
    </div>
</div>

<!-- Success Modal for Delete -->
<div class="modal fade" id="deleteSuccessModal" tabindex="-1" role="dialog" aria-labelledby="deleteSuccessModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 style="color: red" class="modal-title" id="deleteSuccessModalLabel">Delete</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div style="color: red" class="modal-body">
                Address has been successfully deleted.
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>


<script>
    var apiUrl = '/api/provinces';
    var address = {
        streetName: null,
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

    $(document).on('click', '.deleteAddressBtn', function () {
        var addressId = $(this).data('id');
        $('#confirmDeleteBtn').data('id', addressId);
    });


    $('#confirmDeleteBtn').on('click', function () {
        var idUser = document.getElementById('idUser').getAttribute('data-idUser');
        var addressId = $(this).data('id');
        fetch('/api/deleteAddress/' + idUser + '/' + addressId, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json;charset=utf-8',
            },
        })
            .then(response => response.text())
            .then(data => {
                if (data === 'true') {
                    $('button[data-id="' + addressId + '"]').closest('tr').remove();

                    $('#deleteAddressModal').modal('hide');

                    // Show success modal
                    $('#deleteSuccessModal').modal('show');
                }
            })
            .catch((error) => {
                console.error('Error:', error);
            });
        // Remove the table row

    });
    $('#saveAddressBtn').on('click', function () {
        var idUser = document.getElementById('idUser').getAttribute('data-idUser');
        var streetName = $('#streetName').val();
        address.streetName = streetName;
        console.log(address);
        fetch('/api/addAddress/' + idUser, {
            method: 'POST',
            // headers: {
            //     'Content-Type': 'application/json;text/plain;charset=UTF-8',
            // },
            body: new Blob([JSON.stringify(address)], {type: 'application/json;text/plain;charset=UTF-8'})
        })
            .then(response => response.text())
            .then(data => {
                console.log('Success:', data);
            })
            .catch((error) => {
                console.error('Error:', error);
            });

        // Add new address
        $('#addressTableBody').append('<tr class="address-item">' +
            '<td>' + streetName + '</td>' +
            '<td>' + address.province + '</td>' +
            '<td>' + address.district + '</td>' +
            '<td>' + address.commune + '</td>' +
            '<td>' +
            '<button type="button" class="btn btn-danger btn-sm deleteAddressBtn" data-toggle="modal" data-target="#deleteAddressModal">Delete</button>' +
            '</td>' +
            '</tr>');

    });
</script>
</body>
</html>
