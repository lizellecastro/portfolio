<?php
include("config.php");

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $orderDate = mysqli_real_escape_string($db, $_POST['orderDate']);

    $query = "INSERT INTO OrderList (Order_Date) VALUES ('$orderDate')";

    if (mysqli_query($db, $query)) {
        echo "Order added successfully!";
    } else {
        echo "Error: " . $query . "<br>" . mysqli_error($db);
    }
}

mysqli_close($db);
?>