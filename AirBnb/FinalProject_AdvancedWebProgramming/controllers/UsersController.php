<?php

class UsersController extends Controller {
    // properties

    // constructor 
    public function __construct()
    {   
        
        parent::__construct();
    }

    public function getLogin() {
        include "views/login.php";
    }

    public function getRegister() {
        include "views/register.php";
    }

    public function getUser($id) {
        $user = new User($this->conn);
        if($user->getUserByID($id)->success()) {
            $user = $user->getUser();
            //include "views/home.php";
            include "views/profile.php";
            
        } else {
            include "views/_404.php";
        }
    }
    public function  getUpdatedUser($name, $email, $phone, $id) {
        $user = new User($this->conn);
        // check user exists
             if($user->updateUser($name, $email, $phone, $id)->success()) {
                 Messenger::setMsg("change successfully", "success");
                 //var_dump($user);
                 //include "views/profile.php";
                 $userController = new UsersController();
                 $userController->getUser($id);
                 echo "updating";

             } else {
                Messenger::setMsg("change failed", "success");
             }
        
    }
    
    public function login() {
        $url =  ROOT . "login"; //edit to home
       $user = new User($this->conn);
       // check user exists
       if($user->getUserByName($this->req['username'])->checkUserExists()) {
            if($user->validateLogin($this->req)->success()) {
                $user->login();
                Messenger::setMsg("Logged in!", "success");
                Router::redirect("");
            } else {
               echo "password fail!";
               var_dump($user->user);
            }
       } else {
            $errors['username_err'] = "User does not exist!";
            include "views/login.php";
       }
    }

    public function create() {
        // create a new User model
        $user = new User($this->conn);
        // call User validateNewUser($this->req)->success()
        if($user->validateNewUser($this->req)->success()) {
            if($user->create()->success()) {
                //$user->login();
                Messenger::setMsg("New account created!", "success");
                Router::redirect("");
                //header('Location: ' . $url);
            } else {
                var_dump("hah");
            }
        } else {
            $errors = $user->errors;
            include "views/login.php";
        }
    }

    public function logout() {
        Messenger::setMsg("Logged out!", "warning");
        $_SESSION['logged_in'] = false;
        Router::redirect("users/login");
    }

    public function getAvatar($id){
        $user = new User($this->conn);
        if($user->validateAvatar($this->files)->success()) {
            $user->changeAvatar($id);
            echo "avatar sucess";
            //include "views/head.php";
            header("Location: " . ROOT . "user/get/" . $id);          
        } else {
           echo "huhu";
        }
    }
}