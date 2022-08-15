<?php

class Router {
    // static properties
    public static $route;
    public static $url;
    public static $found = false;
    public static $validRoute = [];
    public static $params;

    // static methods
    // get method to handle get requests if $url == $route invoke fn()
    public static function get($route, $function) {
        self::$route = $route;
        self::$validRoute[] = self::$route; 
        if(!isset($_GET['url'])) {
            self::$url = '';
        } else {
            self::$url = $_GET['url'];
        }

        self::getParams();

        if(self::$route == self::$url && $_SERVER['REQUEST_METHOD'] == "GET") {
            self::$found = true;
            $function->__invoke(self::$params);
        }

    }

    public static function post($route, $function) {
        self::$route = $route;
        self::$validRoute[] = self::$route; 
        if(!isset($_GET['url'])) {
            self::$url = '';
        } else {
            self::$url = $_GET['url'];
        }
        // check if route === to the user requested url
        if(self::$route == self::$url && $_SERVER['REQUEST_METHOD'] == "POST") {
            self::$found = true;
            $function->__invoke(self::$params);
        }
    }

    // getParams method to extract wild card params
    public static function getParams() {
        if(strpos(self::$route, "{") !== false) {
            // var_dump(self::$route);
            // var_dump(self::$url);
            $routeArr = explode("/", self::$route);
            $urlArr = explode("/", self::$url);
            // var_dump($routeArr);
            // var_dump($urlArr);
            self::$params = end($urlArr);
            // var_dump(self::$params);
            array_pop($routeArr);
            array_push($routeArr, self::$params);
            // var_dump($routeArr);
            // var_dump($urlArr);
            self::$route = join("/", $routeArr);
            self::$url = join("/", $urlArr);
            // var_dump(self::$route);
            // var_dump(self::$url);
         }
    }
    
    public static function redirect($dest) {
        header("Location: " . ROOT . $dest);
    }

}