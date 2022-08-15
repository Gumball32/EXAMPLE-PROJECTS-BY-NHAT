<?php

class Controller {
    // properties
    public $conn;
    public $req;
    public $params;
    public $files;

    public function __construct()
    {
        // get access to the post reqest arr
        $this->req = $_POST;
        $this->params = $_GET;
        $this->files = $_FILES;
        // CSRF Middleware
        //CSRF::checkToken($this->req);
        // bring in db connection
        $this->conn = DB::getConn();
    }
}