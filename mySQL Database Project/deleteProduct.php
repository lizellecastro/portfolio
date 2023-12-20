<?php
include("config.php");

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $productName = mysqli_real_escape_string($db, $_POST['product_name']);

    $query = "DELETE FROM Product WHERE Product_Name = '$productName'";
    $result = mysqli_query($db, $query);

    if ($result) {
        echo "Product deleted successfully";
    } else {
        // Provide more accurate error logging
        echo "Error deleting product: " . mysqli_error($db);
    }
}

mysqli_close($db);
?>