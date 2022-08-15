<?php

class DB {
    private static $connection;
    private static $instance = null;

    private function __construct() {
        
    }

    public static function createInstance() {
        if(is_null(self::$instance)) {
            self::$instance = new DB;
        }
        return self::$instance;
    }

    public static function connect($host, $user, $pw, $db) {
        self::$connection = new mysqli($host, $user, $pw, $db);
    }

    public static function getConn() {
        return self::$connection;
    }
}