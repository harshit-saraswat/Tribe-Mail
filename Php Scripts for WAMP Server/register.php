<?php

require "conn.php";
$name=$_POST["name"];
$dob=$_POST["dob"];
$gender=$_POST["gender"];
$contact=$_POST["contact"];
$address=$_POST["address"];
$city=$_POST["city"];
$state=$_POST["state"];
$username=$_POST["username"];
$password=$_POST["password"];

$mysql_qry="insert into user_data(name,dob,gender,contact,address,city,state,username,password) values('$name','$dob','$gender','$contact','$address','$city','$state','$username','$password');";

if($conn->query($mysql_qry)===TRUE){
	echo "Registration Success!";
}
else {
	echo "Registration Unsuccessful\n";
	echo "Error:\n".$conn->error;
}

$conn->close();

?>