<?php

class User {

    protected $conn;
    public $username;
    protected $password;
    public $user_id;
    public $user = [];
    public $errors = [];

    public function __construct($conn)
    {
        $this->conn = $conn;
    }

    public function validateLogin($login_user) {
        $this->getUserByName($login_user['username']);
        if(empty($this->user)) {
            $this->errors['username'] = "Username not found!";
        } else {
            if(!password_verify($login_user['password'], $this->user['password_hash'])) {
                $this->errors['password'] = "invalid Password!";
            } else {
                $this->loginUser();
            }
        }
    }

    protected function loginUser() {
        $_SESSION['logged_in'] = true;
        $_SESSION['username'] = $this->user['username'];
        $_SESSION['user_id'] = $this->user['id'];
        $_SESSION['role'] = $this->user['role'];
        header("Location: index.php?msg=Login+successful&msgClass=success");
    }

    public function getUserByName($username) {
        $sql = "SELECT * FROM users
        WHERE users.username = ?";
        $stmt = $this->conn->prepare($sql);
        $stmt->bind_param("s", $username);
        $stmt->execute();
        $results = $stmt->get_result();
        $this->user = $results->fetch_assoc();
    }

    public function getUserById($id) {
        $sql = "SELECT * FROM users
        WHERE users.id = ?";
        $stmt = $this->conn->prepare($sql);
        $stmt->bind_param("s", $id);
        $stmt->execute();
        $results = $stmt->get_result();
        $this->user = $results->fetch_assoc();
    }
}