<?php

class Comment {
    protected $conn;

    public function __construct($conn) {
        $this->conn = $conn;
    }

    public function countComments($id) {
        $sql = "SELECT COUNT(id) AS num_comments 
                FROM comments WHERE meme_id = ?";
         $stmt = $this->conn->prepare($sql);
         $stmt->bind_param("s", $id);
         $stmt->execute();
         $results = $stmt->get_result();
         return $results->fetch_assoc();
    }

    public function getComments($id) {
        $sql = "SELECT comments.*, username
                FROM comments 
                JOIN users ON users.id = comments.user_id
                WHERE meme_id = ?";
        $stmt = $this->conn->prepare($sql);
        $stmt->bind_param("s", $id);
        $stmt->execute();
        $results = $stmt->get_result();
        return $results->fetch_all(MYSQLI_ASSOC);
    }
}