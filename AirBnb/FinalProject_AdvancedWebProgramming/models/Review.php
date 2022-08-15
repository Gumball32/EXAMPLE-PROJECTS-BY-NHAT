<?php

class Review {
    public $review = [];
    public $reviews = [];
    public $errors = [];
    public $review_id;
    public $review_rating;
    public $review_comment;
    public $conn;
    public $room_id;
    public $reservation_id;

    // constructor (inject DB conn)
    public function __construct($conn) {
        $this->conn = $conn;
    }

    // Post methods
    // "setter" for the post prop
    public function fetchReview($id) {
        $this->review_id = $id;
        $sql = "SELECT rev.*, res.room_id
                FROM reviews rev, reservations res
                WHERE rev.id = ?
                and rev.reservation_id=res.id";
        $stmt = $this->conn->prepare($sql);
        $stmt->bind_param("s", $this->review_id);
        $stmt->execute();
        $result = $stmt->get_result();
        if($result->num_rows !== 1) {
            $this->errors['fetch_err'] = "Couldn't retrieve resource!";
        } else {
            $this->review = $result->fetch_assoc();
            var_dump($this->review);
        }
        return $this;
    }

    public function fetchReviews($id) {
        $this->room_id = $id;
        $sql = "SELECT re.*, res.room_id
                FROM reviews re JOIN reservations res
                ON re.reservation_id=res.id
                and res.room_id = ?";
        $stmt = $this->conn->prepare($sql);
        $stmt->bind_param("s", $this->room_id);
        $stmt->execute();
        $results = $stmt->get_result();    
        $this->reviews = $results->fetch_all(MYSQLI_ASSOC);
        return $this;
        
    }

    public function getReview() {
        return $this->review;
    }

    public function getReviews() {
        return $this->reviews;
    }

    // success method, return T / F if $error is empty
    public function success() {
        if(empty($this->errors)) {
            return true;
        } else {
            return false;
        }
    }

    public function delete($id) {
        $sql = "DELETE FROM reviews WHERE reviews.id = ?";
        $stmt = $this->conn->prepare($sql);
        $stmt->bind_param("s", $id);
        $stmt->execute();
        if($stmt->affected_rows !== 1) {
            $this->errors['delete_err'] = "Failed to delete comment!";
        } 
        return $this;
    }
 
    public function create($review) {
        $this->review_comment = htmlspecialchars($review['review']);
        //$this->review_id = $review['review_id'];
        $this->review_rating = $review['rating'];
        $this->reservation_id = $review['reservation_id'];
        $sql = "INSERT INTO `reviews` 
                        (reservation_id, rating, comment)
                VALUES (?,?,?);";
        $stmt = $this->conn->prepare($sql);
        $stmt->bind_param("sss", $this->reservation_id, $this->review_rating, $this->review_comment);
        $stmt->execute();
        if($stmt->affected_rows !== 1) {
            $this->errors['update_err'] = "Failed to create comment!";
        }
        return $this;
    }
}

?>