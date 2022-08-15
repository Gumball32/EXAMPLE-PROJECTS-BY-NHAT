<?php

class Room {
    public $room_id;
    public $room_name;
    public $home_type;
    public $room_type;
    public $total_occupancy;
    public $total_bedrooms;
    public $total_bathrooms;
    public $summary;
    public $address;
    public $has_TV;
    public $has_kitchen;
    public $has_air_con;
    public $has_heating;
    public $has_internet;
    public $price;
    public $owner_id;
    public $created_at;
    public $conn;
    public $room = [];
    public $rooms = [];
    public $errors = [];
    public $offset;
    public $limit;
    public $num_rooms;
    public $reservations = [];
    public $user_name;
    public $image;

    // constructor (inject DB conn)
    public function __construct($conn) {
        $this->conn = $conn;
        $this->countNumPosts();
    }
    // Room methods
    // count the number of rooms so pager can be calc'd
    public function countNumPosts() {
        $sql = "SELECT COUNT(id) AS num_rooms FROM rooms";
        $stmt = $this->conn->query($sql);
        $result = $stmt->fetch_assoc();
        $this->num_rooms = $result['num_rooms'];
    }

    public function fetchRoom($id) {
        $this->room_id = $id;
        $sql = "SELECT r.*, u.name as username
                from rooms r, users u
                where r.id=? and r.owner_id=u.id";
        $stmt = $this->conn->prepare($sql);
        $stmt->bind_param("s", $this->room_id);
        $stmt->execute();
        $result = $stmt->get_result();
        $this->room = $result->fetch_assoc();
        return $this;
    }

    public function calcPager() {
        $this->num_pages = ceil($this->num_rooms / $this->limit);
    }

    public function getNumPages() {
        return $this->num_pages;
    }

    public function getRoom() {
        return $this->room;
    }

    public function fetchRooms($offset, $limit) {
        $this->offset = $offset;
        $this->limit = $limit;
        $this->calcPager();
        $sql = "SELECT *
                FROM rooms
                LIMIT ?,?";
        $stmt = $this->conn->prepare($sql);
        $stmt->bind_param("ii", $offset, $limit);
        $stmt->execute();
        $results = $stmt->get_result();
        if($results->num_rows === 0) {
            $this->errors['fetch_err'] = "Couldn't retrieve resource!";
        } else {
            $this->rooms = $results->fetch_all(MYSQLI_ASSOC);
        }
        return $this;
    }

    public function getRooms() {
        return $this->rooms;
    }

    public function convertCheckBox($room, $name) {
        if(isset($room[$name])) 
        {
            return 1;
        }
        else
        {
            return 0;
        }	
    }

    public function fetchReservations($id) {   
    $sql = "SELECT res.id
            FROM reservations res
            WHERE res.room_id = ?";
    $stmt = $this->conn->prepare($sql);
    $stmt->bind_param("s", $id);
    $stmt->execute();
    $results = $stmt->get_result();    
    $this->reservations = $results->fetch_all(MYSQLI_ASSOC);
    var_dump($this->reservations);
    return $this->reservations;
        
    }

    public function validateRoom($room) {
        $this->room_name = htmlspecialchars($room['room_name']);
        $this->home_type = htmlspecialchars($room['home_type']);
        $this->room_type = htmlspecialchars($room['room_type']);
        $this->total_occupancy = htmlspecialchars($room['total_occupancy']);
        $this->total_bedrooms = htmlspecialchars($room['total_bedrooms']);
        $this->total_bathrooms = htmlspecialchars($room['total_bathrooms']);
        $this->address = htmlspecialchars($room['address']);
        $this->summary = htmlspecialchars($room['summary']);
        $this->price = htmlspecialchars($room['price']);
        $this->owner_id = htmlspecialchars($room['owner']);
        $this->has_TV = $this->convertCheckBox($room, 'has_TV');
        $this->has_air_con = $this->convertCheckBox($room, 'has_air_con');
        $this->has_heating = $this->convertCheckBox($room, 'has_heating');
        $this->has_internet = $this->convertCheckBox($room, 'has_internet');
        $this->has_kitchen = $this->convertCheckBox($room, 'has_kitchen');

        if(empty($this->total_occupancy) || empty($this->total_bedrooms)
        || empty($this->total_bathrooms) || empty($this->address)
        || empty($this->summary) || empty($this->price)
        || empty($this->room_name)) {
            $this->errors['post_form_err'] = "New room fields cannot be empty!";
        }
        
        return $this;
    }

    public function createNewRoom() {
        $sql = "INSERT INTO `rooms` 
                (`room_name`, `home_type`, `room_type`, `total_occupancy`, `total_bedrooms`, `total_bathrooms`, `summary`, `address`, `has_TV`, `has_kitchen`, `has_air_con`, `has_heating`, `has_internet`, `price`, `owner_id`, `image`, `created_at`) 
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP);";
        $stmt = $this->conn->prepare($sql);
        $stmt->bind_param("ssssssssssssssss", $this->room_name, $this->home_type, $this->room_type, $this->total_occupancy, 
                            $this->total_bedrooms, $this->total_bathrooms, $this->summary,
                            $this->address, $this->has_TV, $this->has_kitchen, $this->has_air_con, 
                            $this->has_heating, $this->has_internet, $this->price, $this->owner_id, $this->image);
        $stmt->execute();
        if($stmt->affected_rows !== 1) {
            $this->errors['insert_err'] = "Room was not created!";
        } else {
            $this->getRoomByName($this->room_name);
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

    public function delete($id) {
       $sql = "DELETE FROM rooms WHERE rooms.id = ?";
       $stmt = $this->conn->prepare($sql);
       $stmt->bind_param("s", $id);
       $stmt->execute();
       if($stmt->affected_rows !== 1) {
           $this->errors['delete_err'] = "Failed to delete room!";
       } 
       return $this;
    }

    public function update($updatedRoom) {
        $this->room_name = htmlspecialchars($updatedRoom['room_name']);
        $this->home_type = htmlspecialchars($updatedRoom['home_type']);
        $this->room_type = htmlspecialchars($updatedRoom['room_type']);
        $this->total_occupancy = htmlspecialchars($updatedRoom['total_occupancy']);
        $this->total_bedrooms = htmlspecialchars($updatedRoom['total_bedrooms']);
        $this->total_bathrooms = htmlspecialchars($updatedRoom['total_bathrooms']);
        $this->address = htmlspecialchars($updatedRoom['address']);
        $this->summary = htmlspecialchars($updatedRoom['summary']);
        $this->price = htmlspecialchars($updatedRoom['price']);
        $this->owner_id = htmlspecialchars($updatedRoom['owner']);
        $this->has_TV = $this->convertCheckBox($updatedRoom, 'has_TV');
        $this->has_air_con = $this->convertCheckBox($updatedRoom, 'has_air_con');
        $this->has_heating = $this->convertCheckBox($updatedRoom, 'has_heating');
        $this->has_internet = $this->convertCheckBox($updatedRoom, 'has_internet');
        $this->has_kitchen = $this->convertCheckBox($updatedRoom, 'has_kitchen');
        $sql = "UPDATE `rooms` 
                SET `room_name` = ?, `home_type` = ?, `room_type` = ?, `total_occupancy` = ?,
                 `total_bedrooms` = ?, `total_bathrooms` = ?, `summary` = ?,
                  `address` = ?, `has_TV` = ?, `has_kitchen` = ?,
                   `has_air_con` = ?, `has_heating` = ?, `has_internet` = ?, `owner_id` = ?, price = ? 
                WHERE `rooms`.`id` = ?";
        $stmt = $this->conn->prepare($sql);
        $stmt->bind_param("ssssssssssssssss", $this->room_name, $this->home_type, $this->room_type, $this->total_occupancy, $this->total_bedrooms, $this->total_bathrooms, $this->summary, $this->address, $this->has_TV, $this->has_kitchen,$this->has_air_con, $this->has_heating, $this->has_internet, $this->owner_id, $this->price, $this->room_id);
        var_dump($stmt);
        $stmt->execute();
        if($stmt->affected_rows !== 1) {
            $this->errors['update_err'] = "Failed to update room!";
        }
        return $this;
    }

    public function getRoomByName($room_name) {
        $sql = "SELECT * FROM rooms WHERE rooms.room_name = ?";
        $stmt = $this->conn->prepare($sql);
        $stmt->bind_param("s", $room_name);
        $stmt->execute();
        $result = $stmt->get_result();
        $this->room = $result->fetch_assoc();
        return $this;
    }

    public function search($home_type, $room_type, $total_occupancy, $total_bedrooms){
        $sql = "SELECT * FROM rooms where rooms.home_type = ? and rooms.room_type = ? and rooms.total_occupancy = ? and rooms.total_bedrooms = ?";
         $stmt = $this->conn->prepare($sql);
         $stmt->bind_param("ssss", $home_type, $room_type, $total_occupancy, $total_bedrooms);
         $stmt->execute();
         //var_dump($stmt);
         $results = $stmt->get_result();
         //var_dump($results);
         $this->searchRoom = $results->fetch_all(MYSQLI_ASSOC);
         return $this;

    }
    function getSearch(){
        return $this->searchRoom;
    }
    function validateImage($file){
        $file = $_FILES['image'];
        if(FileManager::validateFile($file, 5000000) === false) {
            $this->errors['errors'] = "Invalid file!";
        }
        $this->image = FileManager::uploadFile();
        return $this;
       // var_dump($file['image']);
    }
}