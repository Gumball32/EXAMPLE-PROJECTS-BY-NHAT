<?php

class RoomController extends Controller {
    public function __construct()
    {
        parent::__construct();
    }

    public function getRooms() {
        $rooms = new Room($this->conn);
        $offset = $this->params['offset'] ?? 0;
        $limit = $this->params['limit'] ?? 3;
        if($rooms->fetchRooms($offset, $limit)->success()) {
            $num_pages = $rooms->getNumPages();
            $rooms = $rooms->getRooms();
            include "views/rooms.php";
        } else {
            echo "error";
        }
    }

    public function getRoom($id) {
        $roomObj = new Room($this->conn);        
        if ( $roomObj->fetchRoom($id)->success()) {
            $reviewObj = new Review($this->conn);
            if (isset($this->params['review'])) {
                $reviewObj->create($this->params);   
            }
            $reviewObj->fetchReviews($id);

            $room = $roomObj->getRoom();
            $reviews = $reviewObj->getReviews();

            //$reservations = $roomObj->fetchReservations($room['id']);
            $temp_username = $roomObj->room['username'];
            
            include "views/single_room.php";
        }
    }

    public function create() {
        $room = new Room($this->conn);
        var_dump($this->files);
        if($room->validateImage($this->files)->success()){
            if($room->validateRoom($this->req)->success()) {
                if($room->createNewRoom()->success()) {
                var_dump("success3");
                 Messenger::setMsg("New room created!", "success");
                 $room = $room->getRoomByName($room->room_name);
                 Router::redirect("");
                 //var_dump($room->room['id']);
                }
             } else {
                 $errors = $room->errors;
                 Messenger::setMsg(strval("Failed to create! " . $errors["post_form_err"]), "danger");    
                 include "views/create_room.php";
             }
        }else{
            echo "no";
        }
    }

    public function newRoom() {
        include "views/create_room.php";
    }

    public function delete() {
        // confirm A or B: A->the room belongs to current user
        // B-> the current user role is 1 (ie admin)
        $room = new Room($this->conn);
        $room->fetchRoom($this->req['delete']); // $_room['delete'] the id
        if($_SESSION['user_id'] == $room->room['owner_id'] || $_SESSION['user_id'] == 1) {
            // delete room
           if($room->delete($this->req['delete'])->success()) {
            Messenger::setMsg("Room deleted!", "warning");
               // redirect user to homepage
            Router::redirect("");
           }
        } else {            
            include "views/_403.php";
        } 
    }

    public function update() {
        $room = new Room($this->conn);
        $room->fetchRoom($this->req['room_id']);
        if($_SESSION['user_id'] == $room->room['owner_id'] || $_SESSION['user_id'] == 1) {
                // if($room->validateRoom($this->req)->success()) {
                    //var_dump($room);
                    if($room->update($this->req)->success()) {  
                        //var_dump($room);
                        Messenger::setMsg("Room updated!", "success");
                        Router::redirect("rooms/get/" . $this->req['room_id']);
                    } else {
                        include "views/_403.php";
                    }
                // } ////$_POST
                // else {
                //     include "views/_403.php";
                // } 
        }
        
    }

    public function searchRoom($home_type, $room_type, $total_occupancy, $total_bedrooms){
        $rooms = new Room($this->conn);        
        if ($rooms->search($home_type, $room_type, $total_occupancy, $total_bedrooms)->success()) {
            //$rooms->search($home_type, $room_type, $total_occupancy, $total_bedrooms);
            echo "ok";
            $rooms = $rooms->getSearch();

            include "views/searchRoom.php";

        }else{
            echo "ok";

        }
        
    }
}