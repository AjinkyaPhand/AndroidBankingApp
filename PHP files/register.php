<?php
    $conn = mysqli_connect("localhost", "id7375066_ajinkyaphand", "9028836116", "id7375066_bankapp");
    
    $name = $_POST["name"];
    $email = $_POST["email"];
    $password = $_POST['password'];
    
    $statement = mysqli_prepare($conn, "INSERT INTO register_details (Name,Email,Password) VALUES (?, ?, ?)");
    mysqli_stmt_bind_param($statement, "ssi", $name, $email, $password);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>