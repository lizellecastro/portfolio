<?php include('session.php'); ?>

<!DOCTYPE html>
<html>
   
<head>
    <title>Welcome</title>
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
        h2 {
            color: #666;
        }
        .todo-list {
            width: 50%;
            margin: 30px auto;
            padding: 20px;
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 5px;
            text-align: left;
        }
        .todo-list ol {
            padding-left: 20px;
        }
        .logout-link {
            display: block;
            margin-top: 30px;
            color: #4CAF50;
            text-decoration: none;
            font-weight: bold;
        }
    </style>
</head>
   
<body>
    <h1>Welcome <?php echo $login_session; ?></h1>

    <div class="todo-list">
        <h2>TODO LIST (FROM DATABASE)</h2>
        <ol>
            <?php
            $user = "example_user";
            $password = "password123";
            $database = "test_db";
            $table = "todo_list";

            try {
                $db = new PDO("mysql:host=localhost;dbname=$database", $user, $password);
                foreach ($db->query("SELECT content FROM $table") as $row) {
                    echo "<li>" . $row['content'] . "</li>";
                }
            } catch (PDOException $e) {
                print "Error!: " . $e->getMessage() . "<br/>";
                die();
            }
            ?>
        </ol>
    </div>

    <a class="logout-link" href="logout.php">Sign Out</a>
</body>

</html>