<?php

$host = 'localhost';
$username = 'phpmyadmin';
$password = 'root';
$database = 'team7';

$db = new mysqli($host, $username, $password, $database);

//if can not connect to database, error
if ($db->connect_error) {
  die("Connection failed: " . $db->connect_error);
}

?>