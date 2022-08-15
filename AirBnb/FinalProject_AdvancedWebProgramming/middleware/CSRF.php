<?php

class CSRF {
    // properties
    //token
    private static $token;

    //methods
    // create a token
    public static function createToken() {
        $token = openssl_random_pseudo_bytes(32);
        $token = bin2hex($token);
        self::$token = $token;
    }

    // clear token
    public static function clearToken() {
        unset($_SESSION['token']);
    }

    // output token as hidden input
    public static function outputToken() {
        if(!isset($_SESSION['token'])) {
        self::createToken();
        $_SESSION['token'] = self::$token;
        }
        echo "<input type='hidden' name='token' value='" . self::$token . "'>";
    }
    // check token
    public static function checkToken($req) {
        if(!empty($req)) {
            if(!isset($req['token']) || $_SESSION['token'] !== $req['token']) {
                include "views/_403.php";
                self::clearToken();
                exit;
            }
            self::clearToken();
        }
        self::clearToken();
    }
}