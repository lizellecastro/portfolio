<!DOCTYPE html>
<html>
<head>
    <title>Orders</title>
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
        .content {
            margin: 30px auto;
        }
        .back-link {
            display: block;
            margin-top: 20px;
            color: #4CAF50;
            text-decoration: none;
            font-weight: bold;
        }
        table {
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        .order-row:hover {
            background-color: #f5f5f5;
            cursor: pointer;
        }
        .order-details {
            display: none;
            background-color: #f9f9f9;
            padding: 10px;
            margin-top: 5px;
        }
    </style>
</head>
<body>
    <h1>Orders Page</h1>

    <?php
    include("config.php");

    $query = "SELECT Order_ID, Order_Date, Total_amt, Status, Customer_ID FROM OrderList";
    $result = mysqli_query($db, $query);
    ?>

    <div class="content">
        <h2>List of Orders</h2>
        <table border="1" cellpadding="5">
            <tr>
                <th>Order ID</th>
                <th>Order Date</th>
            </tr>
            <?php
            while ($row = mysqli_fetch_assoc($result)) {
                echo "<tr class='order-row' data-order-id='{$row['Order_ID']}'>";
                echo "<td>{$row['Order_ID']}</td>";
                echo "<td>{$row['Order_Date']}</td>";
                echo "</tr>";
                echo "<tr class='order-details' data-order-id='{$row['Order_ID']}'>";
                echo "<td colspan='2'>";
                echo "<strong>Total Amount:</strong> {$row['Total_amt']}<br>";
                echo "<strong>Status:</strong> {$row['Status']}<br>";
                echo "<strong>Customer ID:</strong> {$row['Customer_ID']}<br>";
                echo "</td>";
                echo "</tr>";
            }
            ?>
        </table>

        <div class="action-buttons">
            <button id="addButton">Add Order</button>
            <button id="deleteButton">Delete Selected Order</button>
            <button id="updateButton">Update Selected Order</button>
        </div>

        <form id="addOrderForm" style="display: none;">
            Order Date: <input type="text" id="orderDate" name="orderDate">
            <input type="submit" value="Add Order">
        </form>

    </div>

    <?php
    mysqli_close($db);
    ?>

    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const orderRows = document.querySelectorAll('.order-row');

            orderRows.forEach(row => {
                row.addEventListener('click', function () {
                    const orderId = this.getAttribute('data-order-id');
                    const orderDetails = document.querySelector(`.order-details[data-order-id='${orderId}']`);

                    if (orderDetails.style.display === 'none' || orderDetails.style.display === '') {
                        orderDetails.style.display = 'table-row';
                    } else {
                        orderDetails.style.display = 'none';
                    }
                });
            });

            document.getElementById('addButton').addEventListener('click', function () {
                // Show the add order form
                const addOrderForm = document.getElementById('addOrderForm');
                addOrderForm.style.display = 'block';

                // Prevent the default form submission behavior
                addOrderForm.addEventListener('submit', function (event) {
                    event.preventDefault();

                    // Get form data
                    const formData = new FormData(addOrderForm);

                    // Submit the form data to add_order.php using AJAX
                    const xhr = new XMLHttpRequest();
                    xhr.open("POST", "add_order.php", true);
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === 4 && xhr.status === 200) {
                            console.log(xhr.responseText); // Log the server response

                            // Hide the add order form after submission
                            addOrderForm.style.display = 'none';
                        }
                    };
                    xhr.send(formData);
                });
            });

            document.getElementById('deleteButton').addEventListener('click', function () {
                const orderId = prompt("Enter the Order ID to delete:");
                if (orderId) {
                    // Submit the order ID to delete_order.php using AJAX
                    const xhr = new XMLHttpRequest();
                    xhr.open("POST", "delete_order.php", true);
                    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === 4 && xhr.status === 200) {
                            console.log(xhr.responseText); // Log the server response
                        }
                    };
                    xhr.send(`orderId=${orderId}`);
                }
            });

            document.getElementById('updateButton').addEventListener('click', function () {
                const orderId = prompt("Enter the Order ID to update:");
                const newOrderDate = prompt("Enter the new Order Date:");
                if (orderId && newOrderDate) {
                    // Submit the order ID and new order date to update_order.php using AJAX
                    const xhr = new XMLHttpRequest();
                    xhr.open("POST", "update_order.php", true);
                    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === 4 && xhr.status === 200) {
                            console.log(xhr.responseText); // Log the server response
                        }
                    };
                    xhr.send(`orderId=${orderId}&newOrderDate=${newOrderDate}`);
                }
            });
        });
    </script>

    <a class="back-link" href="welcome.php">Back to Welcome Page</a>
</body>
</html>