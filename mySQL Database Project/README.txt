/*********************************************************************************************************************************

This project is a website created on an AWS (Amazon Web Services) EC2 instance. The website was created to challenge my ability to define and manipulate
information directly to a mySQL database. mySQL was installed into the AWS instance and there the database containing information of
multiple tables was created. This website allows the user to enter credentials previously created in the database that 
has been hashed and salted. The user can view the automatically displayed information from the database when choosing either the 
'Orders' or 'Catalog' page. The 'Orders' page contains all the current orders directly from the Orders table in the database. The
'Catalog' page contains all the current product items directly from the 'Catalog' table in the database. Both pages allows the 
user to manipulate data directly to the database by either updating, deleting, or adding items. The user can freely move between
pages and at the end can 'Sign Out' of the Welcome page to return to the login page. Currently, the website is disabled due
to the cost of running the website for prolong periods of time through AWS.

In the mySQL Database project folder are all the files created for this website.

This is what each file does:

- login.php:
  This is the first page to access when logging in with credentials.
- session.php: 
  This code ensures that only users who have successfully logged in can access the website.
- welcome.php:
  This is the page that first appears after the user logins in.
- logout.php:
  Destroys the session and allows the user to return to the login.php page to enter credentials.
- config.php:
  Sets up constants for database connection details and establishes a connection to a MySQL database using the mysqli_connect
  function. 
- orders.php:
  The orders page that the user sees after clicking on the 'Orders' button from the welcome page.
- catalog.php:
  The catalog page that the user sees after clicking on the 'Catalog' button from the welcome page.
- update_order.php:
  Used make the 'Update Selected Order' button functional and contains SQL code connecting to the mySQL database on AWS.
- delete_order.php:
  Used make the 'Delete Selected Order' button functional and contains SQL code connecting to the mySQL database on AWS.
- add_order.php:
  Used make the 'Add Order' button functional and contains SQL code connecting to the mySQL database on AWS.
- addProduct.php:
  Used make the 'Add Product' button functional and contains SQL code connecting to the mySQL database on AWS.
- deleteProduct.php:
  Used make the 'Delete Product' button functional and contains SQL code connecting to the mySQL database on AWS.
- updateProduct.php:
  Used make the 'Update Product' button functional and contains SQL code connecting to the mySQL database on AWS.
- get_order_details.php:
  Used as helper code to retrieve all current existing information from Orders table in the database.
- get_seller_details.php:
  Used as helper code to retrieve all current existing information from the Seller table in the database.
  
*********************************************************************************************************************************/