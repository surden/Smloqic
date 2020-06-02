<?php

$server = "localhost";
$username = "u5848556_mobile";
$password = "J-XMo$*#PU#%";
$dbname = "u5848556_mobile";

$conn = new mysqli($server,$username,$password,$dbname);

if($conn->connect_error){
    die("Koneksi gagal/failed".$conn->connect_error);
}
$sql="Select * FROM data";
$result=$conn->query($sql);
$data=array();

if($result->num_rows>0){
    while ($row=$result->fetch_assoc()){
        $data[]=$row; 
    }
}else{
    echo "data kosong";
}
echo json_encode($data);
$conn->close();

?>