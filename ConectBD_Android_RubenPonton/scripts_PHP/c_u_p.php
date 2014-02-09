<?php

/*
 * Compruebo el usuario y la contraseña dados contra la base de datos
 */

// array para volcar el resultado
$respuesta = array();
// Fijo estos dos valores
$respuesta["id_usr"] = -1;
$respuesta["exito"] = 0;
$respuesta["mensaje"] = "Contraseña y/o usuario incorrectos";

// llamo a la conexión de la base de datos
require_once __DIR__ . '/db_connect.php';

// realizo la conexión
$db = new DB_CONNECT();

// compruebo los parámetros recibidos
if (isset($_GET["user"]) && isset($_GET["psw"])) {
    $user = $_GET['user'];
    $psw = $_GET['psw'];

    $resultado = mysql_query("SELECT * FROM Electronic_user "
            . "WHERE User='{$user}' and Password='{$psw}'") or die(mysql_error());

// Compruebo si la consulta devuelve algo
    if (!empty($resultado)) {
// Compruebo si ha devuelto una fila
        if (mysql_num_rows($resultado) == 1) {
// Vuelco el resultado sobre la misma variable
            $resultado = mysql_fetch_array($resultado);

// Extraigo el valor de la columna
            $id_usr = $resultado["ID_Client"];

            $respuesta["id_usr"] = (int) $id_usr;
            $respuesta["exito"] = 1;
            $respuesta["mensaje"] = "OK";

            echo json_encode($respuesta);
        } else {
// contraseña y/o usuario incorrectos
            echo json_encode($respuesta);
        }
    } else {
// contraseña y/o usuario incorrectos
        echo json_encode($respuesta);
    }
} else {
// falta un parámetro
    $respuesta["mensaje"] = "Falta un argumento en la consulta";

// echoing JSON respuesta
    echo json_encode($respuesta);
}
?>
