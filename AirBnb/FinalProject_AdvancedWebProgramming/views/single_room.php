<?php
    include "views/inc/header.php";
?>

<style>
  <?php include "public/css/SingleRoomStyle.css";?>
</style>

<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
        <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner">
        <?php if($room['image'] != null): ?>
            <div class="carousel-item active">
                <img class="d-block w-100" src="<?php echo PUBLIC_ROOT . $room['image'] ?>" alt="bedroom">
            </div>
        <?php else: ?>
            <div class="carousel-item active">
                <img class="d-block w-100" src="<?php echo PUBLIC_ROOT?>images/bedroom.webp" alt="bedroom">
            </div>

            <div class="carousel-item">
                <img class="d-block w-100" src="<?php echo PUBLIC_ROOT?>images/bathroom.jpg" alt="bathroom">
            </div>

            <div class="carousel-item">
                <img class="d-block w-100" src="<?php echo PUBLIC_ROOT?>images/balcony.jpg" alt="balcony">
            </div>
        <?php endif; ?>
    </div>
</div>

<div class="container py-3">
    <?php if(!empty($room)): ?>
    <div class="d-flex justify-content-between">
        <div class="room-header">
            <h2 class="text-capitalize mt-4 text-success my-3" data-id="<?php echo $room['id']?>"> <?php echo $room['room_type'] . "| " ."ID Room: ". $room['id']; ?></h2>
            <h6 class="font-italic font-weight-light text-capitalize"><?php echo "Type Of Home: " . $room['home_type'] . " | " . date("d-M-Y", strtotime($room['created_at'])); ?></h6>
            <h4 class="room-body mt-3 text-info text-capitalize"><?php echo "Description: " . $room['summary']; ?></h4>
        </div>

        <div class="p-3 rating-room" style="width: 130px;">
            <p><i class="fa fa-thumbs-up mr-1"></i>0</p>
            <p><i class="fa fa-thumbs-down mr-1"></i>0</p>
        </div>
    </div>

    <hr class="my-4">

    <!-- reservation  -->

     <div class="d-flex justify-content-end">
        <form action="<?= ROOT ?>reservations/create/<?= $room['id'] ?>" method='get'>
           <button type="submit" name="reserveBtn" class="btn btn-primary btn-sm">Reserve</button>
           
        </form>
        </div>
    <!--  -->

    <div class="container">
        <div class="table1-information mt-5 d-flex">
            <table class="table border" style="width: 620px">
                <thead>
                    <tr>
                    <th class="text-center" colspan="2" scope="col">General Informations</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                    <th class="text-center" scope="row"><i class="fa fa-user mr-2"></i>Total Occupancy:</th>
                    <td class="text-center"><?php echo $room['total_occupancy']?></td>
                    </tr>
                    <tr>
                    <th class="text-center" scope="row"><i class="fa fa-bed mr-2"></i>Total Bedrooms:</th>
                    <td class="text-center"><?php echo $room['total_bedrooms']?></td>
                    </tr>
                    <tr>
                    <th class="text-center" scope="row"><i class="fa fa-bath mr-2"></i>Total Bathrooms:</th>
                    <td class="text-center"><?php echo $room['total_bathrooms']?></td>
                    </tr>
                    <tr>
                    <th class="text-center" scope="row"><i class="fa fa-map mr-2"></i>Address:</th>
                    <td class="text-center"><?php echo $room['address']?></td>
                    </tr>
                    <tr>
                    <th class="text-center" scope="row"><i class="fa fa-dollar-sign mr-2"></i>Price:</th>
                    <td class="text-center"><?php echo $room['price']?></td>
                    </tr>
                </tbody>
            </table>

            <div class="photo-beach1">
                <img src="<?= ROOT ?>public/images/beach1.jpg" style="width: 430px" alt="beach photo">
            </div>
        </div>
    </div>
    

    <div class="container">
        <div class="table2-information mt-5 d-flex">
            <div class="photo-beach2 mr-5">
                <img src="<?= ROOT ?>public/images/beach2.jpg" style="width: 430px" alt="beach photo">
            </div>
            <table class="table border" style="width: 620px">
                <thead>
                    <tr>
                    <th class="text-center" colspan="2" scope="col">Property Available</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                    <th class="text-center" scope="row"><i class="fa fa-tv mr-2"></i>TV: </th>
                    <td class="text-center"><?php echo $room['has_tv']?></td>
                    </tr>
                    <tr>
                    <th class="text-center" scope="row"><i class="fa fa-fire mr-2"></i>Kitchen:</th>
                    <td class="text-center"><?php echo $room['has_kitchen']?></td>
                    </tr>
                    <tr>
                    <th class="text-center" scope="row"><i class="fa fa-wind mr-2"></i>Air Conditioner:</th>
                    <td class="text-center"><?php echo $room['has_air_con']?></td>
                    </tr>
                    <tr>
                    <th class="text-center" scope="row"><i class="fa fa-fire mr-2"></i>Heating:</th>
                    <td class="text-center"><?php echo $room['has_heating']?></td>
                    </tr>
                    <tr>
                    <th class="text-center" scope="row"><i class="fa fa-wifi mr-2"></i>Internet:</th>
                    <td class="text-center"><?php echo $room['has_internet']?></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    
    
    <div class="d-flex justify-content-between mt-5">
        <a href="<?= ROOT ?>" class="btn-back btn-sm text-white text-center">
        <i class="fa fa-arrow-left mr-2" aria-hidden="true"></i>Back</a>
        <?php if($_SESSION['logged_in']): ?>
        <?php if($_SESSION['user_id'] == 1 || $_SESSION['user_id'] == $room["owner_id"]): ?>
        <form action="<?= ROOT ?>rooms/delete" method='post'>
            <input type="hidden" name="delete" value="<?php echo $room['id'];?>">
            <?php //CSRF::outputToken(); ?>
            <button type="submit" class="btn-delete btn-sm text-white text-center"><i class="fa fa-trash mr-2" aria-hidden="true"></i> Delete</button>
        </form>
        <?php if($_SESSION['logged_in'] === true && $_SESSION['user_id'] == $room['owner_id']) {
                include "views/inc/editModal.php";
            }
        ?>
        <?php endif; ?>
        <?php endif; ?>
    </div>

    <?php if($_SESSION['logged_in']) {
         include "views/inc/review.php";
    } else {
        echo "<h3>Please login to comment</h3>";
    }
    ?>

    <?php if(!empty($reviews)): ?>
        <div class="reviews">
            <?php foreach($reviews as $review): ?>
                <form method="post" action="<?= ROOT ?>review/delete">
                    <div class="board bg-light p-4 my-3">
                        <div class="information-comment d-flex justify-content-between">
                            <div class="information-user d-flex">
                                <img class="mr-2" src="<?= ROOT ?>public/images/beach2.jpg" style="width:40px" alt="avatar">
                                <h5><?php echo "User ID: " . $room['owner_id']. " | $temp_username" ?></h5>
                            </div>
                            <hr class="mt-3">
                                
                            <div class="reviews-content">
                                <h5>Rating: <?php echo $review['rating'];?><i class="fa fa-star ml-1"></i></h5>
                            </div>
                        </div>
                        <hr class="mt-3">
                        <p class="mt-3"><?php echo $review['comment'];?></p>
                        <input type="hidden" name="delete" value="<?php echo $review['id'];?>">
                        <input type="hidden" name="room_id" value="<?php echo $room['id'];?>">
                        <input type="hidden" name="reservation_id" value="<?php echo $review['reservation_id'];?>">
                        <button type="submit" class="btn-delete2 text-white text-center btn-sm mt-3"><i class="fa fa-trash" aria-hidden="true"></i> Delete</button>
                    </div>
                </form>
            <?php endforeach; ?>
        </div>
    <?php else: ?>
        <h6 class="text-muted mt-4">No reviews yet...</h6>
    <?php endif; ?>
    <?php else: ?>
        <h1>room not found!</h1>
    <?php endif; ?>
</div>

<!-- Jquery JS-->
<script src="vendor/jquery/jquery.min.js"></script>
<!-- Vendor JS-->
<script src="vendor/select2/select2.min.js"></script>
<script src="vendor/datepicker/moment.min.js"></script>
<script src="vendor/datepicker/daterangepicker.js"></script>
<?php
    include "views/inc/footer.php";
?>
<?php Messenger::checkMsg(); ?>



