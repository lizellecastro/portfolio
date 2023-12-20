<?php
include("config.php");

if (isset($_GET['seller_id'])) {
    $sellerId = mysqli_real_escape_string($db, $_GET['seller_id']);
    $query = "SELECT Seller_Name FROM Seller WHERE Seller_ID = '$sellerId'";
    $result = mysqli_query($db, $query);

    if ($row = mysqli_fetch_assoc($result)) {
        $sellerDetails = array('Seller_Name' => $row['Seller_Name']);
        echo json_encode($sellerDetails);
    } else {
        echo json_encode(array('Seller_Name' => ''));
    }
} else {
    echo json_encode(array('Seller_Name' => ''));
}

mysqli_close($db);
?>