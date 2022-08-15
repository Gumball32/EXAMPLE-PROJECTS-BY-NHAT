
<!-- Button trigger modal -->
<button type="button" class="btn-edit btn-sm text-white text-center" data-toggle="modal" data-target="#exampleModalLong">
  <i class="fa fa-pencil mr-2" aria-hidden="true"></i>Edit
</button>

<!-- Modal -->
<div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
    <form action="<?= ROOT; ?>rooms/update" method="post">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Edit Room</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
       
          <label for="room_name">Room name: </label>
          <input type="text" name="room_name" value="<?php echo $room['room_name']; ?>" class="form-control edit-title">
          <label for="body">Summary: </label>
          <textarea name="summary" rows="4" class="form-control edit-body"><?php echo $room['summary']; ?></textarea>
          <input type="hidden" name="room_id" class="edit-room_id" value="<?php echo $room['id']?>">
          <label for="home_type">Home Type: </label>
          <select class="form-control" id="home-home_type" name="home_type">
            <option><?php echo $room['home_type']?></option>
            <option>Apartment</option>
            <option>House</option>
            <option>Secondary unit</option>
            <option>Unique space</option>
            <option>Bed and breakfast</option>
            <option>Boutique hotel</option>
          </select>
          <label for="room_type">Room Type: </label>
          <select class="form-control" id="room_type" name="room_type">
            <option><?php echo $room['room_type']?></option>
            <option>Entire place</option>
            <option>Private rooms</option>
            <option>Hotel rooms</option>
            <option>Shared rooms</option>
          </select>
          <label for="total_occupancy">Enter total occupancy: </label>
          <input type="number" name="total_occupancy" id="total_occupancy" class="form-control" value="<?php echo $room['total_occupancy']?>">
          <label for="total_bedrooms">Enter total bedrooms: </label>
          <input type="number" name="total_bedrooms" id="total_bedrooms" class="form-control" value="<?php echo $room['total_bedrooms']?>">
          <label for="total_bathrooms">Enter total bathrooms: </label>
          <input type="number" name="total_bathrooms" id="total_bathrooms" class="form-control" value="<?php echo $room['total_bathrooms']?>">
          <label for="address">Address</label>
          <input type="text" name="address" id="address" class="form-control" value="<?php echo $room['address']; ?>">

          <div class="Properties-full mt-2 d-flex">
            <div class="Properties1">

              <div class="item1">
                <div class="input-group mb-3">
                  <div class="input-group-prepend">
                    <div class="input-group-text d-flex">
                      <input type="checkbox" id="has_TV" name="has_tv" <?php if($room['has_tv'] == 1){echo "checked";} ?>>
                      <label class="form-check-label" for="has_tv">TV</label>
                    </div>
                  </div>
                </div>

              </div>

              <div class="item2">
                <div class="input-group mb-3">
                  <div class="input-group-prepend">
                    <div class="input-group-text d-flex">
                      <input type="checkbox" id="has_kitchen" name="has_kitchen" <?php if($room['has_kitchen'] == 1){echo "checked";} ?>>
                      <label class="form-check-label" for="has_kitchen">Kitchen</label>
                    </div>
                  </div>
                </div>
              </div>

              <div class="item3">
                <div class="input-group mb-3">
                  <div class="input-group-prepend">
                    <div class="input-group-text d-flex">
                      <input type="checkbox" id="has_air_con" name="has_air_con" <?php if($room['has_air_con'] == 1){echo "checked";} ?>>
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
                      <input type="checkbox" id="has_heating" name="has_heating" <?php if($room['has_heating'] == 1){echo "checked";} ?>>
                      <label class="form-check-label" for="has_heating">Heating</label>
                    </div>
                  </div>
                </div>
              </div>

              <div class="item5">
                <div class="input-group">
                  <div class="input-group-prepend">
                    <div class="input-group-text d-flex">
                      <input type="checkbox" id="has_internet" name="has_internet" <?php if($room['has_internet'] == 1){echo "checked";} ?>>
                      <label class="form-check-label" for="has_internet">Internet</label>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <label for="price">Price: </label>
          <input type="number" name="price" id="price" class="form-control" value="<?php echo $room['price']; ?>">
          <label for="owner">Owner: </label>
          <input type="number" name="owner" id="owner" class="form-control" value="<?php echo $room['owner_id']; ?>">
          <label for="title">Post Image</label>
          <input type="file" name="image" class="form-control">
          <?php //CSRF::outputToken(); ?>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary save-edit">Save changes</button>

      </div>
      </form>
    </div>
  </div>
</div>