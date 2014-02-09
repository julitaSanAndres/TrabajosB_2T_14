<?php

/*
 * Recupero los datos del usuario mediante su ID
 */

// array para volcar el resultado
$respuesta = array();
// Fijo estos dos valores
$respuesta["exito"] = 0;
$respuesta["mensaje"] = "Usuario no encontrado";

// llamo a la conexión de la base de datos
require_once __DIR__ . '/db_connect.php';

// realizo la conexión
$db = new DB_CONNECT();

// compruebo los parámetros recibidos
if (isset($_GET["user"])) {
    $user = $_GET['user'];

    $resultado = mysql_query("SELECT * FROM Clients "
            . "WHERE ID_Client='{$user}'") or die(mysql_error());

// Compruebo si la consulta devuelve algo
    if (!empty($resultado)) {
// Compruebo si ha devuelto una fila
        if (mysql_num_rows($resultado) == 1) {
// Vuelco el resultado sobre la misma variable
            $resultado = mysql_fetch_array($resultado);

            /*
             * Columnas de la tabla Clients
              ID_Client
              Name
              Surname_1
              Surname_2
              Address
              City
              Postal_code
              DNI
             */

            // En este array voy a guardar los valores de las columnas
            $cliente = array();

// Extraigo el valor de la columnas
            $cliente["id_usr"] = (int) $resultado["ID_Client"];
            $cliente["name"] = $resultado["Name"];
            $cliente["surname_1"] = $resultado["Surname_1"];
            $cliente["surname_2"] = $resultado["Surname_2"];
            $cliente["address"] = $resultado["Address"];
            $cliente["city"] = $resultado["City"];
            $cliente["postal_code"] = $resultado["Postal_code"];
            $cliente["DNI"] = $resultado["DNI"];

            // Guardo el array con lo datos del cliente el respuesta
            $respuesta["datos_cliente"] = array();
            array_push($respuesta["datos_cliente"], $cliente);

            $respuesta["exito"] = 1;
            $respuesta["mensaje"] = "OK";

            echo json_encode($respuesta);
        } else {
// Problema al recuperar la información del cliente
            echo json_encode($respuesta);
        }
    } else {
// Problema al recuperar la información del cliente
        echo json_encode($respuesta);
    }
} else {
// falta un parámetro
    $respuesta["mensaje"] = "Falta un argumento en la consulta";

// echoing JSON respuesta
    echo json_encode($respuesta);
}
?>
