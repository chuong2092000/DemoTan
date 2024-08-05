<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Change-Password Form</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <style>
        .change-password-form {
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            background-color: #ffffff;
        }

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
<div class="container mt-5">
    <div class="change-password-form">
        <h2 class="text-center">Change Password</h2>
        <form action="/change-password" method="post" id="changePasswordForm">
            <div class="form-group">
                <label for="currentPassword">Current Password</label>
                <input type="password" name="currentPassword" class="form-control" id="currentPassword"
                       placeholder="Enter current password"
                       required>
            </div>
            <div class="form-group">
                <label for="newPassword">New Password</label>
                <input type="password" name="newPassword" class="form-control" id="newPassword"
                       placeholder="Enter new password" required>
            </div>
            <div class="form-group">
                <label for="confirmNewPassword">Confirm New Password</label>
                <input type="password" name="confirmNewPassword" class="form-control" id="confirmNewPassword"
                       placeholder="Confirm new password"
                       required>
            </div>
            <span style="display: ${display};color: ${color}">${message}</span>
            <div class="row">
                <div class="col-md-4">
                    <a type="button" href="/success" class="btn btn-primary btn-block">Home</a>
                </div>
                <div class="col-md-8">
                    <button type="submit" class="btn btn-primary btn-block">Change Password</button>
                </div>
            </div>
        </form>
        <div id="message" class="mt-3"></div>
    </div>
</div>
</body>
</html>