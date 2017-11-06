<?php

    header("Access-Control-Allow-Origin: *");
    header("Content-Type: application/json; charset=ISO-8859-1");

    include ("./conn.php");

    $postdata = file_get_contents("php://input");
    $request = json_decode($postdata);
    @$email = $request->email;
    @$password = $request->password;

    $sql = "SELECT id_user, id_driver, is_customer, email_user, name_user, cpf_user, nasc_user, gender_user FROM tb_user WHERE email_user = '".$email."' AND password_user = '".$password."' LIMIT 1 ;";

    $outRes = "";

     if ($res = $db->query($sql)) {
            if ($res->fetchColumn() > 0) {
                foreach ($db->query($sql) as $row) {
                    $outRes .= '{ "user" : "'  . $row["id_user"].'",';
                    $outRes .= '"driver" : "'  . $row["id_driver"].'",';
                    $outRes .= '"customer" : "'  . $row["is_customer"].'",';
                    $outRes .= '"email" : "'  . $row["email_user"].'",';
                    $outRes .= '"name" : "'  . $row["name_user"].'",';
                    $outRes .= '"cpf" : "'  . $row["cpf_user"].'",';
                    $outRes .= '"nasc" : "'  . $row["nasc_user"].'",';
                    $outRes .= ' "gender" : "'. $row["gender_user"].'"}';
                 }
            }else{
                $outRes .= '{"error" : "Usuario ou Senha invalidos"}';
            }
     }else{
        $outRes .= '{"error" : "Servidor fora do ar"}';
     }

     $outRes ='{"records":['.$outRes.']}';

    echo $outRes;
?>