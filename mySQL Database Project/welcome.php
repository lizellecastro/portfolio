<?php
session_start();

if (!isset($_SESSION['login_user'])) {
    header("location: login.php");
    exit();
}
?>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
    <style type="text/css">
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            padding: 30px;
            text-align: center;
        }
        h1 {
            color: #333;
        }
        .welcome-container {
            margin: 30px auto;
        }
        .welcome-container button {
            padding: 10px 20px;
            font-size: 16px;
            margin: 0 10px;
        }
        .logout-link {
            display: block;
            margin-top: 30px;
            color: #4CAF50;
            text-decoration: none;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <h1>Welcome <?php echo $login_session; ?></h1>

    <div class="welcome-container">
        <h2>HAL E-Commerce Corporation</h2>
        <button onclick="location.href='orders.php'">Orders</button>
        <button onclick="location.href='catalog.php'">Catalog</button>
    </div>

    <a class="logout-link" href="logout.php">Sign Out</a>
</body>
</html>