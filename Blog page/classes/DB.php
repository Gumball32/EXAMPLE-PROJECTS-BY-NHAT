<?php
// Creates a DB instance using the Singleton pattern
class DB {
  private static $instance = null;
  private static $connection;

  public static function getInstance() {
    if(is_null(self::$instance)) {
      self::$instance = new DB();
    }
    return self::$instance;
  }
  private function __construct() {

  }

  public static function connect($host, $user, $pw, $db) {
    self::$connection = new mysqli($host, $user, $pw, $db);
  }
  public static function getConn() {
    return self::$connection;
  }
}
