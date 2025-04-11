<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Registration</title>
    <style>
        body {
            background: linear-gradient(to right, #c9d6ff, #e2e2e2);
            font-family: Arial;
            display: flex; justify-content: center; align-items: center; height: 100vh;
        }
        .form-box {
            background: #fff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
            width: 350px;
        }
        input {
            width: 100%; padding: 10px; margin: 10px 0;
        }
        .btn {
            background-color: #007bff; color: #fff; border: none;
            padding: 10px; width: 100%; border-radius: 5px; cursor: pointer;
        }
        .btn:hover { background-color: #0056b3; }
    </style>
</head>
<body>
<div class="form-box">
    <h2 style="text-align:center;">User Registration</h2>
    <form action="UserRegisterServlet" method="post">
        <input type="text" name="username" placeholder="Username" required />
        <input type="email" name="email" placeholder="Email" required />
        <input type="password" name="password" placeholder="Password" required />
        <input type="submit" class="btn" value="Register">
    </form>
</div>
</body>
</html>
