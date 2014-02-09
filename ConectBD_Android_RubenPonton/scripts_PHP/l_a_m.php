<?php

/*
 * Devuelve el listado de movimientos de una cuenta dada
 */


// array para volcar el resultado
$respuesta = array();
$respuesta["exito"] = 0;
$respuesta["mensaje"] = "No hay movimientos que mostrar";

// llamo a la conexión de la base de datos
require_once __DIR__ . '/db_connect.php';

// realizo la conexión
$db = new DB_CONNECT();


// compruebo los parámetros recibidos
if (isset($_GET["id_account"])) {
    $id_account = $_GET['id_account'];

    $resultado = mysql_query("SELECT * FROM Movements INNER JOIN Movement_type "
            . "ON Movements.ID_Movement_type= Movement_type.ID_Movement_type "
            . "WHERE ID_Account={$id_account}") or die(mysql_error());

// Compruebo si la consulta devuelve algo
    if (!empty($resultado)) {
// Compruebo si ha devuelto más de una fila
        if (mysql_num_rows($resultado) > 0) {
            $respuesta["movimientos"] = array();

            /*
             * CAMPOS DE LA TABLA Movements
              `Movements`.`ID_Movement`,
              `Movements`.`ID_Account`,
              `Movements`.`ID_Movement_type`,
              `Movements`.`Amount`,
              `Movements`.`Current_balance`,
              `Movements`.`Description`,
              `Movements`.`Date`
             * 
             * CAMPOS DE LA TABLA Movement_type
              `Movement_type`.`ID_Movement_type`,
              `Movement_type`.`Type`
             */

            while ($fila = mysql_fetch_array($resultado)) {
// declaro el array temporal
                $cuentas = array();

// extraigo los valores de las columnas correspondientes
                $cuentas["id_movimiento"] = (int) $fila["ID_Movement"];
                $cuentas["cantidad"] = (double) $fila["Amount"];
                $cuentas["balance_actual"] = (double) $fila["Current_balance"];
                $cuentas["descripcion"] = $fila["Description"];
                $cuentas["fecha"] = (String) $fila["Date"];
                $cuentas["tipo_movimiento"] = $fila["Type"];

// voy guardando en el array las filas
                array_push($respuesta["movimientos"], $cuentas);
            }

            $respuesta["exito"] = 1;
            $respuesta["mensaje"] = "Hay movimientos en la cuenta";

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
