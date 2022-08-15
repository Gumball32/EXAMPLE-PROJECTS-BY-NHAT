<?php 
include "views/inc/header.php";
?>

<style>
  <?php include "public/css/CreateRoomStyle.css";?>
</style>

<div class="form-post">
<div class="container">
    <div class="row mt-5">
        <div class="col-md-6 offset-md-3 my-4 py-3">
            <div class="card p-4">
                <h3 class="text-capitalize my-3"><i class="fa fa-pencil" aria-hidden="true"></i> Create a Room</h3>
                <form action="<?= ROOT ?>rooms/create" method="post" enctype="multipart/form-data">
                  <div class="position-flex">
                    <div class="form-group">
                        <label for="home_type">Home Type: </label>
                        <select class="form-control" style="width: 200px;" id="home-home_type" name="home_type">
                          <option>Select Type...</option>
                          <option>Apartment</option>
                          <option>House</option>
                          <option>Secondary unit</option>
                          <option>Unique space</option>
                          <option>Bed and breakfast</option>
                          <option>Boutique hotel</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="room_type">Room Type: </label>
                        <select class="form-control" style="width: 200px;" id="room_type" name="room_type">
                          <option>Select Type...</option>
                          <option>Entire place</option>
                          <option>Private rooms</option>
                          <option>Hotel rooms</option>
                          <option>Shared rooms</option>
                        </select>
                    </div>
                  </div>
                  
                  <div class="position-choose">
                    <div class="form-group">
                      <label for="room_name">Enter name of room: </label>
                      <input type="text" name="room_name" id="total_occupancy" class="form-control" style="width: 200px;" placeholder="Enter name here...">
                    </div>

                    <div class="form-group">
                      <label for="total_occupancy">Enter total occupancy: </label>
                      <input type="number" name="total_occupancy" id="total_occupancy" class="form-control" style="width: 200px;" placeholder="Enter number here...">
                    </div>

                    <div class="form-group">
                      <label for="total_bedrooms">Enter total bedrooms: </label>
                      <input type="number" name="total_bedrooms" id="total_bedrooms" class="form-control" style="width: 200px;" placeholder="Enter number here...">
                    </div>

                    <div class="form-group">
                      <label for="total_bathrooms">Enter total bathrooms: </label>
                      <input type="number" name="total_bathrooms" id="total_bathrooms" class="form-control" style="width: 200px;" placeholder="Enter number here...">
                    </div>
                  </div>

                <div class="Properties-full mt-2 d-flex">
                    <div class="Properties1">

                      <div class="item1">
                        <div class="input-group mb-3">
                          <div class="input-group-prepend">
                            <div class="input-group-text d-flex">
                              <input type="checkbox" id="has_TV" name="has_tv">
                              <label class="form-check-label" for="has_tv">TV</label>
                            </div>
                          </div>
                        </div>
                      </div>

                      <div class="item2">
                        <div class="input-group mb-3">
                          <div class="input-group-prepend">
                            <div class="input-group-text d-flex">
                              <input type="checkbox" id="has_kitchen" name="has_kitchen">
                              <label class="form-check-label" for="has_kitchen">Kitchen</label>
                            </div>
                          </div>
                        </div>
                      </div>

                      <div class="item3">
                        <div class="input-group mb-3">
                          <div class="input-group-prepend">
                            <div class="input-group-text d-flex">
                              <input type="checkbox" id="has_air_con" name="has_air_con">
                              <label class="form-check-label text-nowrap" for="has_air_con">Air Condition</label>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
            
                    <div class="Properties2">
                      <div class="item4">
                        <div class="input-group mb-3">
                          <div class="input-group-prepend">
                            <div class="input-group-text d-flex">
                              <input type="checkbox" id="has_heating" name="has_heating">
                              <label class="form-check-label" for="has_heating">Heating</label>
                            </div>
                          </div>
                        </div>
                      </div>

                      <div class="item5">
                        <div class="input-group mb-3">
                          <div class="input-group-prepend">
                            <div class="input-group-text d-flex">
                              <input type="checkbox" id="has_internet" name="has_internet">
                              <label class="form-check-label" for="has_internet">Internet</label>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>

                  <div class="form-group">
                    <label for="summary">Description: </label>
                    <textarea class="form-control" name="summary" id="summary" rows="3" placeholder="Enter text here..."></textarea>
                  </div>

                  <div class="position-info">
                    <div class="form-group">
                      <label for="address">Address</label>
                      <input type="text" name="address" id="address" style="width: 200px;" class="form-control" placeholder="Enter address here...">
                    </div>

                    <div class="form-group">
                      <label for="price">Price: </label>
                      <input type="number" name="price" id="price" style="width: 200px;" class="form-control" placeholder="Enter price here...">
                    </div>

                    <div class="form-group">
                      <label for="owner">Owner: </label>
                      <input type="number" name="owner" id="owner" style="width: 200px;" class="form-control" placeholder="Enter id of owner here...">
                    </div>

                    <div class="form-group">
                      <label for="title">Post Image</label>
                      <input type="file" name="image" style="width: 200px;" class="form-control">
                    </div>
                  </div>

                  
                  <?php //CSRF::outputToken(); ?>
                  <button type="submit" class="btn-createPost btn-primary btn-block btn-lg my-3"><i class="fa fa-plus-square" aria-hidden="true"></i> Create New Post</button>
                </form>
            </div>
        </div>
    </div>
  </div>
</div>


<?php
    include "views/inc/footer.php";
?>