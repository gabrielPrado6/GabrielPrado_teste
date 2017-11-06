<?php
    header("Access-Control-Allow-Origin: *");
    header("Content-Type: application/json; charset=ISO-8859-1");

    include ("./conn.php");

    $sql = "SELECT * FROM cr_model;";

        $outRes = "";

         if ($res = $db->query($sql)) {
                if ($res->fetchColumn() > 0) {
                    foreach ($db->query($sql) as $row) {
                        if ($outRes != "") {$outRes .= ",";}
                        $outRes .= '{ "id" : "'  . $row["id_cr_model"].'",';
                        $outRes .= ' "model" : "'. $row["cr_model"].'"}';
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