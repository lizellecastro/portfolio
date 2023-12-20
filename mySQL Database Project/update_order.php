<?php
include("config.php");

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $orderId = mysqli_real_escape_string($db, $_POST['orderId']);
    $newOrderDate = mysqli_real_escape_string($db, $_POST['newOrderDate']);

    $query = "UPDATE OrderList SET Order_Date = '$newOrderDate' WHERE Order_ID = '$orderId'";

    if (mysqli_query($db, $query)) {
        echo "Order updated successfully!";
    } else {
        echo "Error: " . $query . "<br>" . mysqli_error($db);
    }
}

mysqli_close($db);
?>