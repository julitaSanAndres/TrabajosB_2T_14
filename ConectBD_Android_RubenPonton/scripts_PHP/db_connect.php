<?php

/**
 * Clase para gestionar la conexión a la base de datos
 */
class DB_CONNECT {

    // Constructor
    function __construct() {
        // Conectar
        $this->connect();
    }

    // Destructor
    function __destruct() {
        // Cierra la conexión
        $this->close();
    }

    /**
     * Función que conecta con la base de datos
     */
    function connect() {
        // leo las variables de conexión
        require_once __DIR__ . '/db_config.php';

        // Pruebo la conexión con la base de datos
        $con = mysql_connect(DB_SERVER, DB_USER, DB_PASSWORD) or die(mysql_error());

        // Selecciono la base
        $db = mysql_select_db(DB_DATABASE) or die(mysql_error()) or die(mysql_error());

        // Devuelvo el cursor de la conexión
        return $con;
    }

    /**
     * Cierra la base de datos
     */
    function close() {
        // Cierro la conexión
        mysql_close();
    }

}

?>
