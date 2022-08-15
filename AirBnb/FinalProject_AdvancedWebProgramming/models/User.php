<?php

class User {
    // properties
    public $user_id;
    public $user_name;
    public $user_email;
    public $user_password;
    public $user_password_confirm;
    public $user_phonenumber;
    public $user_hash;
    public $user_role;
    public $conn;
    public $avatar;
    public $errors = [];
    public $user = [];
    public $users = [];

    public function __construct($conn) {
        $this->conn = $conn;
    }
    public function getUser(){
        return $this->user;
    }
    // get user by name -> return $this
    public function getUserByName($username) {
        $sql = "SELECT * FROM users WHERE users.name = ?";
        $stmt = $this->conn->prepare($sql);
        $stmt->bind_param("s", $username);
        $stmt->execute();
        $result = $stmt->get_result();
        $this->user = $result->fetch_assoc();
        return $this;
    }  
    public function getUserByID($id) {
        $sql = "SELECT * FROM users WHERE users.id = ?";
        $stmt = $this->conn->prepare($sql);
        $stmt->bind_param("s", $id);
        $stmt->execute();
        $result = $stmt->get_result();
        $this->user = $result->fetch_assoc();
        return $this;
    }

    

   


    // check if user exists return $this [create err if no user]
    public function checkUserExists() {
        if(!empty($this->user)) {
            return true;
        } else {
            return false;
        }
    }

    // validate user 
    public function validateLogin($req) {
        //$this->user['user_hash'] = $this->user_hash;
        if(!password_verify($req['password'], $this->user['password'])) {
            $this->errors['password_err'] = "Invalid password!";
        }
        return $this;

    }

    public function success() {
        if(empty($this->errors)) {
            return true;
        } else {
            return false;
        }
    }

    public function login() {
        $_SESSION['user_name'] = $this->user['name']; //in db
        //$_SESSION['user_role'] = $this->user['role'];
        $_SESSION['logged_in'] = true;
        $_SESSION['user_id'] = $this->user['id'];
        var_dump($this->user);
    }

    public function create() {
        // password_hash the user password
        $this->user_hash = password_hash($this->user_password, PASSWORD_DEFAULT);
    
        // insert new user
        $sql = "INSERT INTO users (name, email, password, phone_number) VALUES (?,?,?,?)";
        $stmt = $this->conn->prepare($sql);
        $stmt->bind_param("ssss", $this->user_name, $this->user_email, $this->user_hash, $this->user_phonenumber);
        $stmt->execute();
        var_dump($stmt);
        if($stmt->affected_rows === 1) {
            $this->getUserByName($this->user_name);
        } else {
            $this->errors['insert_err'] = "There was a problem creating the user";
        }
        return $this;
    } 
    public function updateUser($name, $email, $phone, $id){
        $sql = "UPDATE users SET name = ?, email = ?, phone_number = ? WHERE id = ?";
            $stmt = $this->conn->prepare($sql);
            $stmt->bind_param("ssss", $name, $email, $phone, $id);
            $stmt->execute();
            if($stmt->affected_rows === 1) {
                //echo "12";
            } else {
                $this->errors['insert_err'] = "There was a problem updating the user";
                //var_dump($this);
            }
            return $this;

    
    }

    public function validateNewUser($user) {
        // if there are any errors add them to $this->errors arr
        //move user attributes from $user arr to User properties (sanitize name + email)
        $this->user_name = htmlspecialchars($user['username']);
        $this->user_email = filter_var($user['email'], FILTER_SANITIZE_EMAIL);
        $this->user_password = $user['password'];
        $this->user_password_confirm = $user['password_confirm'];
        $this->user_phonenumber = $user['phonenumber'];
        // (3) if statements to check username (should not exist), email is valid, 
        if($this->getUserByName($this->user_name)->checkUserExists()) {
            $this->errors['create_username_err'] = "Username is already taken!";
        }
        //validate email 
        if(!filter_var($this->user_email, FILTER_VALIDATE_EMAIL)) {
            $this->errors['create_email_err'] = "Invalid email!";
        }
        // passwords match && !empty
        if($this->user_password !== $this->user_password_confirm || empty($this->user_password)) {
            $this->errors['create_password_err'] = "Password must match and cannot be empty!";
        }

         return $this;
    }

    public function validateAvatar($file){
        $file = $_FILES['upload'];
        if(FileManager::validateFile($file, 5000000) === false) {
            $this->errors['errors'] = "Invalid file!";
        }
        $this->avatar = FileManager::uploadFile();
        return $this;
        var_dump($file['image']);

    }

    public function changeAvatar ($id){
        var_dump($this->avatar);
        var_dump($id);
        $this->user_id= $_SESSION['user_id'];
        $sql = "UPDATE users SET profile_img = ? WHERE id = ?";
        $stmt = $this->conn->prepare($sql);
        $stmt->bind_param("ss",$this->avatar, $id);
        $stmt->execute();
        $_SESSION['img'] = $this->avatar;
        if($stmt->affected_rows !== 1) {
            $this->errors['insert_err'] = "avatar not change";
        } else {
            $this->post_id = $stmt->insert_id;
        }
        return $this;
        var_dump($_SESSION['img']);

    } 
    
    


}