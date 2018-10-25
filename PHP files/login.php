<?php
    $conn = mysqli_connect("localhost", "id7375066_ajinkyaphand", "9028836116", "id7375066_bankapp");
    
   $email = $_POST["email"];
   $password = $_POST["password"];
    
	//$sql = mysql_query("SELECT * FROM register_details where Email = '$email'  AND Password= '$password'")or die(mysql_error());
	
	
    $statement = mysqli_prepare($conn, "SELECT * FROM register_details WHERE Email = ? AND Password = ?");
    mysqli_stmt_bind_param($statement, "si", $email, $password);
    mysqli_stmt_execute($statement);
   
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $Name, $Email, $Password);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;  
        $response["name"] = $Name;
		$response["email"] = $Email;
    }
    
    echo json_encode($response);
    
    
?>