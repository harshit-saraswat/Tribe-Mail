<?php

$db_name="tribe_mail";
$mysql_username = "root";
$mysql_password = "";
$server_name = "localhost";

$tag1=$_POST["tag1"];

$con = mysqli_connect($server_name,$mysql_username,$mysql_password,$db_name);

$sql = "select * from laws_data where problem like '%$tag1%';";

$result = mysqli_query($con,$sql);

$response = array();

while($row=mysqli_fetch_array($result)){
	
	array_push($response,array("tags"=>$row[1],"name"=>$row[2],"details"=>$row[3]));
	
}

echo json_encode(array("server_response"=>$response));

mysqli_close($con);

?>