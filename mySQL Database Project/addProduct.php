<?php
include("config.php");

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Retrieve form data
    $productName = mysqli_real_escape_string($db, $_POST['product_name']);
    $description = mysqli_real_escape_string($db, $_POST['description']);
    $price = mysqli_real_escape_string($db, $_POST['price']);
    $quantity = mysqli_real_escape_string($db, $_POST['quantity']);
    $sellerId = mysqli_real_escape_string($db, $_POST['seller_id']);

    // Use prepared statement to prevent SQL injection
    $query = "INSERT INTO Product (Product_Name, Description, Price, Quantity, Seller_ID) 
              VALUES (?, ?, ?, ?, ?)";
    
    $stmt = mysqli_prepare($db, $query);

    // Bind parameters
    mysqli_stmt_bind_param($stmt, "ssdsi", $productName, $description, $price, $quantity, $sellerId);

    // Execute the statement
    $result = mysqli_stmt_execute($stmt);

    if ($result) {
        echo "Product added successfully";
    } else {
        echo "Error adding product: " . mysqli_error($db);
    }

    // Close statement
    mysqli_stmt_close($stmt);
}

mysqli_close($db);
?>