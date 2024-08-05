<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Forgot-Password Form</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .forgot-password-form {
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            background-color: #ffffff; /* Màu nền của form */
        }

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
            color: red;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <div class="forgot-password-form">
        <h2 class="text-center">Forgot Password</h2>
        <form action="/forgot-password" method="post" id="forgotPasswordForm">
            <div class="form-group">
                <label for="email">Email address</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="Enter your email"
                       required>
                <div class="error-message" style="display: ${display};color: ${color}">${message}</div>
            </div>
            <div class="row">
                <div class="col-4">
                    <a type="button" href="/login" class="btn btn-outline-success ">Login</a></div>
                <div class="col-8">
                    <button  type="submit" class="btn btn-primary btn-block">Send Password Reset Link
                    </button>
                </div>
            </div>
        </form>
        <div id="message" class="mt-3"></div>
    </div>
</div>
</body>
</html>