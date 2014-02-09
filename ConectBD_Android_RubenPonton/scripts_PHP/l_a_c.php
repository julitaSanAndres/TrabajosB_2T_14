<?php

/*
 * Devuelve la lista de cuentas de un cliente dado
 */


// array para volcar el resultado
$respuesta = array();
// Fijo estos dos valores
$respuesta["exito"] = 0;
$respuesta["mensaje"] = "No hay cuentas que mostrar";

// llamo a la conexión de la base de datos
require_once __DIR__ . '/db_connect.php';

// realizo la conexión
$db = new DB_CONNECT();

// compruebo los parámetros recibidos
if (isset($_GET["id_client"])) {
    $id_client = $_GET['id_client'];

    $resultado = mysql_query("SELECT * FROM Accounts INNER JOIN Account_type "
            . "ON Accounts.ID_Account_type=Account_type.ID_Account_type "
            . "WHERE ID_Client={$id_client}") or die(mysql_error());

// Compruebo si la consulta devuelve algo
    if (!empty($resultado)) {
// Compruebo si ha devuelto una fila
        if (mysql_num_rows($resultado) > 0) {
            $respuesta["cuentas"] = array();

            /*
              CAMPOS DE LA TABLA Accounts
              `ID_Account`,
              `IBAN`,
              `ID_Client`,
              `Balance`,
              `ID_Account_type`

              CAMPOS DE LA TABLA Account_type
              `ID_Account_type`,
              `Type`
             */

            while ($fila = mysql_fetch_array($resultado)) {
// declaro el array temporal
                $cuentas = array();

// extraigo los valores de las columnas correspondientes
                $cuentas["id_cuenta"] = (int) $fila["ID_Account"];
                $cuentas["iban"] = $fila["IBAN"];
                $cuentas["balance"] = (double) $fila["Balance"];
                $cuentas["tipo_cuenta"] = $fila["Type"];

// voy guardando en el array las filas
                array_push($respuesta["cuentas"], $cuentas);
            }

            $respuesta["exito"] = 1;
            $respuesta["mensaje"] = "El cliente tiene cuentas";

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