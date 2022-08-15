<?php

Router::get("", function() {
    Router::redirect("rooms");
});

Router::post("search", function() {
    //var_dump($_POST);
    $roomController = new RoomController;
    $roomController->searchRoom($_POST['home_type'], $_POST['room_type'], $_POST['total_occupancy'], $_POST['total_bedrooms']);
   
});

Router::get("rooms", function() {
    $roomController = new RoomController();
    $roomController->getRooms();
});

Router::get("rooms/get/{id}", function($id) {
    $roomController = new RoomController;
    $roomController->getRoom($id);
});

// Router::post("rooms/get/{id}", function($id) {
//     $roomController = new RoomController;
//     $roomController->getRoom($id);
// });

Router::get("rooms/create", function() {
    $roomController = new RoomController;
    $roomController->newRoom();
});

Router::post("rooms/create", function() {
    var_dump($_FILES);
    var_dump($_POST);
    $roomController = new RoomController;
    $roomController->create();
});

Router::post("rooms/delete", function() {
    $roomController = new RoomController;
    $roomController->delete();
});

Router::post("rooms/update", function() {
    $roomController = new RoomController;
    $roomController->update();
});

Router::post("review/delete", function() {
    $reviewController = new ReviewController;
    $reviewController->delete();
});

//User
Router::get("login", function($id) {
    include "views/login.php";
});
Router::get("register", function($id) {
    include "views/register.php";
});
Router::get("head", function() {
    include "views/head.php";
});
Router::get("users/login", function() {
    $usersController = new UsersController;
    $usersController->getLogin();
});
Router::post("users/login", function() {
    $usersController = new UsersController;
    $usersController->login();
});

Router::post("users/create", function() {
    $usersController = new UsersController;
    $usersController->create();
});

Router::get("users/create", function() {
    $usersController = new UsersController;
    $usersController->getRegister();
});

Router::post("users/login", function() {
    $usersController = new UsersController;
    $usersController->login();
});
Router::get("users/logout", function() {
    $usersController = new UsersController;
    $usersController->logout();
});

Router::get("user/get/{id}", function($id) {
    $usersController = new UsersController;
    $usersController->getUser($id);
});

Router::post("user/edit", function() {
    var_dump($_FILES['upload']);
    var_dump($_POST);
    $usersController = new UsersController;
    $usersController->getUpdatedUser($_POST['name'], $_POST['email'], $_POST['phone'], $_POST['id']);
    echo "get user";
});
Router::post("img", function() {
    var_dump($_POST);
  var_dump($_FILES);
  $usersController = new UsersController;
  $usersController->getAvatar($_POST['id']);
    
});

//Reservations
Router::get("reservations", function() {
    echo "Welcome to ITEC MVC Blog";
    $resController = new ReservationsController;
    $resController->getRess();


    // $postsController = new PostsController;
    // $postsController->getPosts();
});

Router::get("reservations/get/{id}", function($id) {
    $resController = new ReservationsController;
    $resController->getRes($id);
    // $postsController = new PostsController;
    // $postsController->getPost($id);
});

Router::get("reservations/create/{id}", function($id) {
    $resController = new ReservationsController;
    $resController->newReservation($id);
    
});
Router::get("reservations/create", function() {
    
    $resController = new ReservationsController;
    $resController->newReservation_get();
    
});


Router::post("reservations/create", function() {
    $resController = new ReservationsController;
    $resController->create();
});