<?php

$db_name="tribe_mail";
$mysql_username = "root";
$mysql_password = "";
$server_name = "localhost";

$username=$_POST["username"];

$con = mysqli_connect($server_name,$mysql_username,$mysql_password,$db_name);

$sql = "select * from user_data where username like '$username';";

$result = mysqli_query($con,$sql);

$response = array();

while($row=mysqli_fetch_array($result)){
	
	array_push($response,array("name"=>$row[1],"username"=>$row[8]));
	
}

echo json_encode(array("server_response"=>$response));

mysqli_close($con);

?>