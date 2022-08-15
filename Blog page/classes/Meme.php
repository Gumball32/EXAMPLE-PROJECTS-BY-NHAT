<?php

class Meme {

    protected $conn;

    public function __construct($conn)
    {
        $this->conn = $conn;
    }

    public function getMemes() {
        $sql = "SELECT memes.*, username, COUNT(comments.id) AS num_comments FROM memes
        JOIN users ON users.id = memes.user_id
        LEFT JOIN comments ON comments.meme_id = memes.id
        GROUP BY memes.id";
        $stmt = $this->conn->prepare($sql);
        $stmt->execute();
        $results = $stmt->get_result();
        return $results->fetch_all(MYSQLI_ASSOC);
    }

    public function getMeme($id) {
        $sql = "SELECT * FROM memes
                JOIN users ON users.id = memes.user_id
                WHERE memes.id = ?";
        $stmt = $this->conn->prepare($sql);
        $stmt->bind_param("s", $id);
        $stmt->execute();
        $results = $stmt->get_result();
        return $results->fetch_assoc();
    }

    public function deleteMeme($id) {
        $sql = "DELETE FROM memes
                WHERE memes.id = ?";
        $stmt = $this->conn->prepare($sql);
        $stmt->bind_param("s", $id);
        $stmt->execute();
        if($stmt->affected_rows === 1) {
            header("Location: index.php?msg=Meme+deleted&msgClass=success");
        } else {
            header("Location: index.php?msg=Failed+to+delete+meme&msgClass=danger");
        }
    }
}