<?php

    header("Access-Control-Allow-Origin: *");
    header("Content-Type: application/json; charset=ISO-8859-1");

    include ("./conn.php");

    $postdata = file_get_contents("php://input");
    $request = json_decode($postdata);
    @$email = $request->email;
    @$password = $request->password;
    @$name = $request->name;
    @$nasc = $request->nasc;
    @$cpf = $request->cpf;
    @$gender = $request->gender;
    @$isClient = $request->isClient;
    @$driver = $request->driver;
    @$model = $request->model;
    @$active = $request->active;

    $sql =  

?>