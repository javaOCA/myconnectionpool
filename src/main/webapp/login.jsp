<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
    <h1>Login page</h1>

${requestScope.error}

<form action="./login" method="post">
    <p>User login</p>  <input type="text" name="userlogin">
    <p>User password</p>  <input type="password" name="userpassword">

    </br><input type="submit" name="loginButton" value="Login" >
</form>

</body>
</html>
