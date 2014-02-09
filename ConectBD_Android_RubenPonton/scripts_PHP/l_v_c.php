<?php

/*
 * Devuelve los valores de la tarjeta para un usuario electrónico dado
 */

// array para volcar el resultado
$respuesta = array();
$respuesta["exito"] = 0;
$respuesta["mensaje"] = "No hay tarjeta de claves que mostrar";

// llamo a la conexión de la base de datos
require_once __DIR__ . '/db_connect.php';

// realizo la conexión
$db = new DB_CONNECT();

// compruebo los parámetros recibidos
if (isset($_GET["id_elec_user"])) {
    $id_e_u = $_GET['id_elec_user'];

    $resultado = mysql_query("SELECT * FROM BANK.Values_card "
            . "where ID_Electronic_user={$id_e_u}") or die(mysql_error());

// Compruebo si la consulta devuelve algo
    if (!empty($resultado)) {
// Compruebo si ha devuelto una fila
        if (mysql_num_rows($resultado) == 1) {
            // Vuelco el resultado sobre la misma variable
            $resultado = mysql_fetch_array($resultado);

            /*
              CAMPOS DE LA TABLA Values_card
              `ID_Values_card`,
              `ID_Electronic_user`,
              `Number`,
              `A1`,
              `A2`,
              `A3`,
              `B1`,
              `B2`,
              `B3`,
              `C1`,
              `C2`,
              `C3`
             */
// declaro el array
            $tarjeta = array();
// extraigo los valores de las columnas correspondientes

            $tarjeta["id_t_v"] = (int) $resultado["ID_Values_card"];
            $tarjeta["num"] = (int) $resultado["Number"];
            $tarjeta["A1"] = (int) $resultado["A1"];
            $tarjeta["A2"] = (int) $resultado["A2"];
            $tarjeta["A3"] = (int) $resultado["A3"];
            $tarjeta["B1"] = (int) $resultado["B1"];
            $tarjeta["B2"] = (int) $resultado["B2"];
            $tarjeta["B3"] = (int) $resultado["B3"];
            $tarjeta["C1"] = (int) $resultado["C1"];
            $tarjeta["C2"] = (int) $resultado["C2"];
            $tarjeta["C3"] = (int) $resultado["C3"];

            // Declaro el nodo
            $respuesta["tarjeta"] = array();
// voy guardando en el array las filas
            array_push($respuesta["tarjeta"], $tarjeta);

            $respuesta["exito"] = 1;
            $respuesta["mensaje"] = "El cliente tiene tarjeta de claves";

// devuelvo el resultado en formato JSON
            echo json_encode($respuesta);
        } else {
// la consulta no devolvió nada
// devuelvo el resultado en formato JSON
            echo json_encode($respuesta);
        }
    } else {
// la consulta no devolvió nada
// devuelvo el resultado en formato JSON
        echo json_encode($respuesta);
    }
} else {
// falta un parámetro
    $respuesta["mensaje"] = "Falta un argumento en la consulta";

// devuelvo el resultado en formato JSON
    echo json_encode($respuesta);
}
?>