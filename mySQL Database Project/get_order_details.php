<?php
include("config.php");

if (isset($_GET['order_id'])) {
    $orderId = mysqli_real_escape_string($db, $_GET['order_id']);

    $query = "SELECT * FROM OrderList WHERE Order_ID = '$orderId'";
    $result = mysqli_query($db, $query);

    if ($result) {
        $orderDetails = mysqli_fetch_assoc($result);
        echo json_encode($orderDetails);
    } else {
        echo json_encode(['error' => 'Failed to fetch order details']);
    }
} else {
    echo json_encode(['error' => 'Order ID not provided']);
}

mysqli_close($db);
?>