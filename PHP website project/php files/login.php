<?php
   include("config.php");
   session_start();

   error_reporting(E_ALL);
   ini_set('display_errors', 1);

   $error = "";

   if($_SERVER["REQUEST_METHOD"] == "POST") {
      // username and password sent from form

      $myusername = mysqli_real_escape_string($db,$_POST['username']);
      $plain_password = mysqli_real_escape_string($db,$_POST['password']);
      $salt = "ghyv@145!"; 
      $hashed_password = hash('sha256', $salt . $plain_password); 

      $sql = "SELECT id FROM admin WHERE username = '$myusername' and passcode = '$hashed_password'";
      $result = mysqli_query($db,$sql);
   
      if (!$result) {
        printf("Error: %s\n", mysqli_error($db));
        exit();
      }

      $count = mysqli_num_rows($result);

      // If result matched $myusername and $hashed_password, table row must be 1 row

      if($count == 1) {
         $row = mysqli_fetch_array($result, MYSQLI_ASSOC);
         $_SESSION['login_user'] = $myusername;

         header("location: welcome.php");
      } else {
         $error = "Your Login Name or Password is invalid";
      }
   }
?>

<html>

<head>
    <title>Login Page</title>
    <style type="text/css">
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            font-size: 16px;
            background-color: #f9f9f9;
        }

        .login-container {
            width: 350px;
            margin: 80px auto;
            border: 1px solid #ddd;
            background-color: #f9f9f9;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            position: relative;
        }

        .login-container h2 {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
        }

        .login-container label {
            display: block;
            margin: 15px 0 5px 0;
            font-weight: bold;
            color: #555;
        }

        .login-container input[type="text"],
        .login-container input[type="password"] {
            width: calc(100% - 20px);
            padding: 12px;
            margin-bottom: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
            color: #333;
        }

        .login-container input[type="submit"] {
            width: 100%;
            background-color: #4CAF50;
            color: white;
            padding: 14px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        .login-container input[type="submit"]:hover {
            background-color: #45a049;
        }

        .error-message {
            font-size: 14px;
            color: #cc0000;
            margin-top: 20px;
            text-align: center;
        }

        .login-container::before {
            content: "";
            display: block;
            background-image: url('https://via.placeholder.com/350x150');
            background-size: cover;
            width: 100%;
            height: 150px;
            border-top-left-radius: 10px;
            border-top-right-radius: 10px;
            position: absolute;
            top: 0;
            left: 0;
            z-index: -1;
        }
    </style>
</head>

<body>
    <div class="login-container">
        <h2>Web Development Project 2</h2>
        <div>
            <form action="" method="post">
                <label for="username">Username:</label>
                <input type="text" name="username" required /><br /><br />
                <label for="password">Password:</label>
                <input type="password" name="password" required /><br /><br />
                <input type="submit" value="Login" /><br />
            </form>
        </div>
        <div class="error-message">
            <?php echo $error; ?>
        </div>
    </div>
</body>

</html>