<?php

$db_name="tribe_mail";
$mysql_username = "root";
$mysql_password = "";
$server_name = "localhost";

$tag1=$_POST["tag1"];
$tag2=$_POST["tag2"];
$tag3=$_POST["tag3"];
$tag4=$_POST["tag4"];

$con = mysqli_connect($server_name,$mysql_username,$mysql_password,$db_name);

$sql = "select * from scheme_data where tags like '%$tag1%' and tags like '%$tag2%' and tags like '%$tag3%' and tags like '%$tag4%';";

$result = mysqli_query($con,$sql);

$response = array();

while($row=mysqli_fetch_array($result)){
	
	array_push($response,array("tags"=>$row[1],"name"=>$row[5],"details"=>$row[6]));
	
}

echo json_encode(array("server_response"=>$response));

mysqli_close($con);

?>