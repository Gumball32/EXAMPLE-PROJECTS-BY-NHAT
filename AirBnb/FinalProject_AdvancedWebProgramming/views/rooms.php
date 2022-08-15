<?php
    include "views/inc/header.php";
?>


<style>
  <?php include "public/css/SingleRoomStyle.css";?>
</style>

<div class="container">
    <h3>Recent Rooms: <!--?php echo $post['num_rooms'] ?--></h3>
    <hr>
    <div class="row">
        <?php foreach ($rooms as $room): ?>
            <div class="col-md-4 my-3 d-flex my-4" data-tv="<?php echo $room["has_tv"] ?>">
                <div class="card w-100 bg-light" style="width: 100%">
                    <div class="card-general p-4">
                        <a href="<?= ROOT?>rooms/get/<?=$room['id']?>">
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
                                            <img class="d-block w-100" src="public/images/bedroom.webp" alt="bedroom">
                                        </div>

                                        <div class="carousel-item">
                                            <img class="d-block w-100" src="public/images/bathroom.jpg" alt="bathroom">
                                        </div>

                                        <div class="carousel-item">
                                            <img class="d-block w-100" src="public/images/balcony.jpg" alt="balcony">
                                        </div>
                                    <?php endif; ?>
                                </div>
                            </div>
                            <h4 class='text-capitalize mt-4'><?= $room['room_type'] ?></h4>
                        </a>

                        <div class="card-body">
                            <p>Type of home: <?= $room['home_type'] ?></p>
                            <p>Price: <?= $room['price'] ?></p>
                            <p>Brief Description: <?= substr($room['summary'], 0, 100);?></p>
                            <button class="my-3" Type="submit" href="<?= ROOT?>rooms/get/<?=$room['id']?>">View More...</button>
                        </div>
                    </div>

                    <div class="card-footer d-flex justify-content-between bg-light" style="width:100%">
                            <p><?php echo $room['address'] . " | " .  date("d M Y", strtotime($room['created_at']));?></p>
                    </div>
                    
                </div>
            </div>
        <?php endforeach; ?>
    </div>
</div>

<div class="container">
<nav aria-label="Page navigation example">
  <ul class="pagination">
    <li class="page-item"><a class="page-link" href="#">Previous</a></li>
<?php
    for ($i=0; $i < $num_pages; $i++) { 
      $page_num = $i + 1;
      $offset = $limit * $i;
      echo "<li class='page-item'><a class='page-link' href='rooms?offset={$offset}&limit={$limit}'>{$page_num}</a></li>";
    }
?>
    <li class="page-item"><a class="page-link" href="#">Next</a></li>
  </ul>
</nav>  
</div>
<script src="<?php ROOT ?>public/js/SingleRoomMain.js"></script>
<?php
    include "views/inc/footer.php";
?>