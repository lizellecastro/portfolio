<!DOCTYPE html>
<html>

<head>
    <title>Catalog</title>
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
            margin-top: 20px; 
        }

        th,
        td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        .product-row:hover {
            background-color: #f5f5f5;
            cursor: pointer;
        }

        .product-details,
        .seller-details {
            display: none;
            background-color: #f9f9f9;
            padding: 10px;
            margin-top: 5px;
        }

        /* Added styles for text fields and buttons */
        .form-container {
            margin-top: 20px;
            text-align: left;
        }

        .form-container input {
            margin-bottom: 10px;
        }

        .form-container button {
            background-color: #4CAF50;
            color: white;
            padding: 10px;
            border: none;
            cursor: pointer;
        }
    </style>
</head>

<body>
    <h1>Catalog Page</h1>

    <?php
    include("config.php");

    $query = "SELECT * FROM Product";
    $result = mysqli_query($db, $query);
    ?>

    <div class="content">
        <h2>Product Catalog</h2>
        <table border="1" cellpadding="5">
            <tr>
                <th>Product ID</th>
                <th>Product Name</th>
            </tr>
            <?php
            while ($row = mysqli_fetch_assoc($result)) {
                echo "<tr class='product-row' data-product-id='{$row['Product_ID']}'>";
                echo "<td>{$row['Product_ID']}</td>";
                echo "<td>{$row['Product_Name']}</td>";
                echo "</tr>";
                echo "<tr class='product-details' data-product-id='{$row['Product_ID']}'>";
                echo "<td colspan='2'>";
                echo "<strong>Description:</strong> {$row['Description']}<br>";
                echo "<strong>Price:</strong> {$row['Price']}<br>";
                echo "<strong>Quantity:</strong> {$row['Quantity']}<br>";
                echo "<strong>Seller ID:</strong> <span class='seller-link' data-seller-id='{$row['Seller_ID']}'>{$row['Seller_ID']}</span><br>";
                echo "</td>";
                echo "</tr>";
                echo "<tr class='seller-details' id='seller-details-{$row['Product_ID']}' data-seller-id='{$row['Seller_ID']}'>";
                echo "<td colspan='2'>";
                echo "<strong>Seller Name:</strong> <span class='seller-name'></span><br>";
                echo "</td>";
                echo "</tr>";
            }
            ?>
        </table>

        <!-- Added text fields and buttons for updating, deleting, and adding -->
        <div class="form-container">
            <label for="productName">Product Name:</label>
            <input type="text" id="productName" name="productName">
            <br>
            <label for="productDescription">Product Description:</label>
            <input type="text" id="productDescription" name="productDescription">
            <br>
            <label for="productPrice">Product Price:</label>
            <input type="text" id="productPrice" name="productPrice">
            <br>
            <label for="productQuantity">Product Quantity:</label>
            <input type="text" id="productQuantity" name="productQuantity">
            <br>
            <label for="sellerID">Seller ID:</label>
            <input type="text" id="sellerID" name="sellerID">
            <br>
            <button onclick="updateProduct()">Update Product</button>
            <button onclick="deleteProduct()">Delete Product</button>
            <button onclick="addProduct()">Add Product</button>
        </div>
    </div>

    <?php
    mysqli_close($db);
    ?>

    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const productRows = document.querySelectorAll('.product-row');
            const sellerDetailsContainers = document.querySelectorAll('.seller-details');

            productRows.forEach(row => {
                row.addEventListener('click', function (event) {
                    const productId = this.getAttribute('data-product-id');
                    const productDetails = document.querySelector(`.product-details[data-product-id='${productId}']`);

                    if (productDetails.style.display === 'none' || productDetails.style.display === '') {
                        productDetails.style.display = 'table-row';
                    } else {
                        productDetails.style.display = 'none';
                    }

                    // Prevent the click event from propagating to the parent row
                    event.stopPropagation();
                });
            });

            const sellerLinks = document.querySelectorAll('.seller-link');

            sellerLinks.forEach(link => {
                link.addEventListener('click', function (event) {
                    const sellerId = this.getAttribute('data-seller-id');
                    const sellerDetailsContainer = this.closest('.product-row').nextElementSibling.nextElementSibling;
                    const sellerDetails = sellerDetailsContainer.querySelector('.seller-name');

                    if (sellerDetails.innerHTML === '') {
                        // Fetch seller information using AJAX
                        const xhr = new XMLHttpRequest();
                        xhr.onreadystatechange = function () {
                            if (xhr.readyState === XMLHttpRequest.DONE) {
                                if (xhr.status === 200) {
                                    const sellerName = JSON.parse(xhr.responseText).Seller_Name;
                                    sellerDetails.innerHTML = sellerName;
                                }
                            }
                        };

                        xhr.open('GET', `get_seller_details.php?seller_id=${sellerId}`, true);
                        xhr.send();
                    }

                    if (sellerDetailsContainer.style.display === 'none' || sellerDetailsContainer.style.display === '') {
                        sellerDetailsContainer.style.display = 'table-row';
                    } else {
                        sellerDetailsContainer.style.display = 'none';
                    }

                    // Prevent the click event from propagating to the parent row
                    event.stopPropagation();
                });
            });

            // Close seller details when clicking outside of the seller details area
            document.addEventListener('click', function () {
                sellerDetailsContainers.forEach(container => {
                    container.style.display = 'none';
                });
            });
        });

        function updateProduct() {
            // Retrieve values from the form
            const productName = document.getElementById('productName').value;
            const productDescription = document.getElementById('productDescription').value;
            const productPrice = document.getElementById('productPrice').value;
            const productQuantity = document.getElementById('productQuantity').value;
            const sellerID = document.getElementById('sellerID').value;

            // Call the updateProduct function with the retrieved values
            const xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function () {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        // Parse the JSON response
                        const response = JSON.parse(xhr.responseText);

                        // Check the status and display the message
                        if (response.status === 'success') {
                            console.log(response.message);
                        } else {
                            console.error(response.message);
                        }
                    }
                }
            };

            // Adjust the URL and parameters based on your server-side script
            xhr.open('POST', 'updateProduct.php', true);
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            xhr.send(`product_name=${productName}&description=${productDescription}&product_price=${productPrice}&product_quantity=${productQuantity}&seller_id=${sellerID}`);
        }
        
        function deleteProduct() {
            // Retrieve values from the form
            const productName = document.getElementById('productName').value;

            // Call the deleteProduct function with the retrieved values
            const xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function () {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        // Handle the response, if needed
                        console.log(xhr.responseText);
                    } else {
                        console.error("HTTP request failed with status:", xhr.status);
                    }
                }
            };

            // Adjust the URL and parameters based on server-side script
            xhr.open('POST', 'deleteProduct.php', true);
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

            // Make sure to send the correct parameter
            xhr.send(`product_name=${encodeURIComponent(productName)}`);
        }


        function addProduct() {
            // Retrieve values from the form
            const productName = document.getElementById('productName').value;
            const productDescription = document.getElementById('productDescription').value;
            const productPrice = document.getElementById('productPrice').value;
            const productQuantity = document.getElementById('productQuantity').value;
            const sellerID = document.getElementById('sellerID').value;

            // Call the addProduct function with the retrieved values
            const xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function () {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        // Handle the response, if needed
                        console.log(xhr.responseText);
                    }
                }
            };

            // Adjust the URL and parameters based on server-side script
            xhr.open('POST', 'addProduct.php', true);
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            xhr.send(`product_name=${productName}&description=${productDescription}&price=${productPrice}&quantity=${productQuantity}&seller_id=${sellerID}`);
        }
    </script>

    <a class="back-link" href="welcome.php">Back to Welcome Page</a>
</body>

</html>