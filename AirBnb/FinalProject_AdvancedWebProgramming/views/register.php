<?php
include "views/inc/header.php";
?>

<style>
  <?php include "public/css/register.css" ?>
</style>

<div class="bg s2">
  <?php if(!empty($errors)): ?>
    <div class="alert alert-danger">
      <?php var_dump($errors); ?>
    </div>
  <?php endif; ?>
<div class="layer2">
 <div class="rblock pt-5">
     <div class="create border  pt-5 mx-auto mb-5" style="width:500px; height: 670px;">
         <h3 class="text-center"><i class="fa fa-user mr-2" aria-hidden="true"></i>Create New Account</h3>
        <form action="<?= ROOT ?>users/create" method="post">
            <div class="form-group">
              <label class="my-3" for="username">Username</label>
              <input style="width:400px" type="text" name="username" class="form-control mx-auto" placeholder="Enter your username...">
            </div>    
            <div class="form-group">
              <label class="my-3" for="email">Email</label>
              <input style="width:400px" type="email" name="email" class="form-control mx-auto" placeholder="Enter your email...">
            </div> 
            <div class="form-group">
              <label class="my-3" for="password">Password</label>
              <input style="width:400px" type="password" name="password" class="form-control mx-auto" placeholder="Enter your password...">
            </div> 
            <div class="form-group">
              <label class="my-3" for="password_confirm">Password Confirm</label>
              <input style="width:400px" type="password" name="password_confirm" class="form-control mx-auto" placeholder="Enter your password again...">
            </div>  
            <div class="form-group">
              <label class="my-3" for="phonenumber">Phone Number</label>
              <input style="width:400px" type="phone" name="phonenumber" class="form-control mx-auto" placeholder="Enter your phone number...">
            </div> 
            <button style="width:400px" type="submit" class="btn-register my-3" style="width: 500px;"><i class="fa fa-plus-square mr-2" aria-hidden="true"></i>Create Account</button>
    
            <div>
              <p class="text-center">Already have an account?<a href="<?= ROOT ?>login"> Sign in</a></p>
            </div>
        </form>     
   </div> <!-- end of col-md-6 -->
 </div>
</div>
</div>


<?php
include "views/inc/footer.php";
?>