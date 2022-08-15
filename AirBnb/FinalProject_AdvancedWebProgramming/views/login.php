<?php
include "views/inc/header.php";
?>

<style>
  <?php include "public/css/login.css";?>
</style>

<div class="bg s1">
  <div class="layer1">

    <div class="container my-5 py-5">
      <?php if(!empty($errors)): ?>
      <div class="alert alert-danger">
        <?php var_dump($errors); ?>
      </div>
      <?php endif; ?>

      <div class="board_login pt-5 mx-auto" style="width:600px; height: 350px;">
        <h2 class="text-center font-weight-bold">Login</h2>
        <div class="">
          <div class="register">
            <!-- <h3><i class="fa fa-user-circle" aria-hidden="true"></i></h3> -->
            <form action="<?= ROOT ?>users/login" method="post">
              <div class="form-group">
                <label for="username"></label>
                <input type="text" name="username" class="form-control mx-auto" placeholder="Username" style="width:400px">
              </div>    

              <div class="form-group">
                <label for="password"></label>
                <input type="password" name="password" class="form-control mx-auto"  placeholder="Password" style="width:400px">
              </div> 
              <?php //CSRF::outputToken(); ?>
              <button type="submit" class="btn-login my-3 mx-5" style="width: 500px;"><i class="fa fa-book mr-2" aria-hidden="true"></i>Sign In</button>
              <div>
                <p class="text-center my-4">New Airbnb?<a href="<?= ROOT ?>register"> Sign Up</a></p>
              </div>
            </form>
            <?php 
            // var_dump($_SESSION) 
            ?>
          </div> <!-- end of col-md-6 -->
        </div>
      </div>
    </div>
  </div>
</div>


<?php
include "views/inc/footer.php";
?>