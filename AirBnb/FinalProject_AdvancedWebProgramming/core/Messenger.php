<?php

class Messenger {
    public static $msg;
    public static $msg_class;

    public static function setMsg($msg, $msg_class) {
        $_SESSION['msg'] = $msg;
        $_SESSION['msg_class'] = $msg_class;
    }

    public static function clearMsg() {
        unset($_SESSION['msg']);
        unset($_SESSION['msg_class']);
    }

    public static function checkMsg() {
        if(isset($_SESSION['msg'])) {
            echo "<div class='container pt-4'><div class='alert alert-". $_SESSION['msg_class'] . "'>" . $_SESSION['msg'] . "</div></div>";
            self::clearMsg();
        }
    }
}