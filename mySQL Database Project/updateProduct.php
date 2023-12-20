<?php
include("config.php");

// Enable error reporting for debugging
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $productName = mysqli_real_escape_string($db, $_POST['product_name']);
    $description = mysqli_real_escape_string($db, $_POST['description']);
    $price = mysqli_real_escape_string($db, $_POST['product_price']);
    $quantity = mysqli_real_escape_string($db, $_POST['product_quantity']);
    $sellerId = mysqli_real_escape_string($db, $_POST['seller_id']);
    
    // Retrieve other fields for updating

    $query = "UPDATE Product SET Product_Name = '$productName', Description = '$description', Price = '$price', Quantity = '$quantity', Seller_ID = '$sellerId' WHERE Product_Name = '$productName'";
    $result = mysqli_query($db, $query);

    // Return a JSON response
    if ($result) {
        echo json_encode(array('status' => 'success', 'message' => 'Product updated successfully'));
    } else {
        echo json_encode(array('status' => 'error', 'message' => 'Error updating product: ' . mysqli_error($db)));
    }
}

mysqli_close($db);
?>