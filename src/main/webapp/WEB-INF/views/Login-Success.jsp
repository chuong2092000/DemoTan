<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <style>
        body {
            background-image: url('https://p.w3layouts.com/demos_new/template_demo/09-06-2017/elegant_login_form-demo_Free/1686839134/web/images/bg2.jpeg');
            background-size: cover; /* Đảm bảo hình nền bao phủ toàn bộ trang */
            background-position: center; /* Căn giữa hình nền */
            background-attachment: fixed; /* Giữ hình nền cố định khi cuộn trang */
            background-repeat: no-repeat;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="/login">WebTMDT</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/change-password" style="display: ${display}">Change Password</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/edit-profile" style="display: ${display}">Edit Profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/edit-address" style="display: ${display}">Edit Address</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link">${user.email}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/login">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>