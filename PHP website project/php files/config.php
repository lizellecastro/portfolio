<?php
   define('DB_SERVER', 'localhost');
   define('DB_USERNAME', 'example_user');
   define('DB_PASSWORD', 'password123');
   define('DB_DATABASE', 'test_db');
   $db = mysqli_connect(DB_SERVER,DB_USERNAME,DB_PASSWORD,DB_DATABASE);
?>