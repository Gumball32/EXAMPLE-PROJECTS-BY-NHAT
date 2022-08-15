<?php

class ReviewController extends Controller {
    // constructor 
    public function __construct() {   
        
        parent::__construct();
    }

    // controller methods
    public function getReviews() {
        $reviews = new Review($this->conn);
        if($reviews->fetchReviews()->success()) {
            $reviews = $reviews->getReviews();
            echo "successs";
            var_dump($reviews);
            include "views/reviews.php";
        } else {
            echo "error";
        }
    }

    public function getReview($id) {
        $review = new Review($this->conn);
        if($review->fetchReview($id)->success()) {
            $review = $review->getReview();
            include "views/reviews.php";
        } else {
            include "views/_404.php";
        }
    }

    public function arrive() {
        include "views/reviews.php";
    }

    public function delete() {
        $review = new Review($this->conn);
        $review->fetchReview($this->req['delete']); // $_POST['delete'] the id
        $reservation = new Reservation($this->conn);
        $reservation->fetchRes($this->req['reservation_id']);
        $temp = $reservation->getRes();
        $room_id = $this->req['room_id'];
        if($_SESSION['user_id'] == $temp['user_id']) {
           if($review->delete($this->req['delete'])->success()) {
            Messenger::setMsg("Review deleted!", "warning");
            // redirect user to homepage
            Router::redirect("rooms/get/$room_id");
           }
        } else {
            Messenger::setMsg("This is not your review!", "danger");
            Router::redirect("rooms/get/$room_id");
        }
    }

    public function update() {
        $review = new Review($this->conn);
        $review->fetchReview($this->req['review_id']);
        if($_SESSION['user_id'] == $review->review['user_id']) {
            if($review->update($this->req)) {
                Messenger::setMsg("Comment updated!", "success");
                //Router::redirect("reviews/get/" . $this->req['review_id']);
            } //$_POST
        }
        
    }
}

?>