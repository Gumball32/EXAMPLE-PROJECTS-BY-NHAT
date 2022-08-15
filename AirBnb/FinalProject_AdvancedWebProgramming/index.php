<?php
include "core/init.php";
include "core/DB.php";
DB::createInstance();
DB::connect("localhost", "root", "", "airbnb");

define("ROOT", substr($_SERVER['PHP_SELF'], 0, -9));
define("PUBLIC_ROOT", ROOT . "public/");
include "core/web.php";
