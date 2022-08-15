<?php
session_start();
if(!isset($_SESSION['logged_in'])) {
    $_SESSION['logged_in'] = false;
}

spl_autoload_register(function($class) {
    if(file_exists("core/" . $class . ".php")) {
        include_once "core/" . $class . ".php";
    } else if(file_exists("controllers/" . $class . ".php")) {
        include_once "controllers/" . $class . ".php";
    } else if(file_exists("models/" . $class . ".php")) {
        include_once "models/" . $class . ".php";
    } 
    // else if(file_exists("middleware/" . $class . ".php")) {
    //     include_once "middleware/" . $class . ".php";
    // }
});
