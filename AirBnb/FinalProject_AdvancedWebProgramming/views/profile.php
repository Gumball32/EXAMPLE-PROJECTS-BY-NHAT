<?php

include "views/inc/header.php";
?>
<h1 class="text-center">Your Account</h1>
<div class="row">
    <div class="col-md-4 ml-5 pl-5">
        <?php if($user['profile_img'] === NULL): ?>
            <img src="<?= PUBLIC_ROOT?>images/avatar.webp" height="300px" width="100%" alt="" class="post-img my-3" style="border-radius: 98px; height: 200px; width: 201px;"></div>
        <?php else: ?>
            <img src="<?= PUBLIC_ROOT . $user['profile_img']; ?>" height="300px" width="100%" alt="" class="avatar" style="border-radius: 98px; height: 205px; width: 200px;"></div>
        <?php endif; ?>

        <?php outputBtn($user); ?>
        
    <div class="edit ml-5 text-center">
        <?php //outputBtn($user); ?>
        <form action="<?=ROOT . "img"; ?>" method="post" enctype="multipart/form-data" class="text-center d-flex">
        <input type="hidden" name="id" value="<?= $user['id'] ?>">
        <input type="file" name="upload">
        <button type="submit" class="btn-sm btn-warning mt-1">Upload</button>
    </form>
        
        

    </div>
</div>


<?php
include "views/inc/footer.php"

?>


<?php
function outputBtn($user) { 
    $name = $user['name'];
    $email = $user['email'];
    $phoneNumber = $user['phone_number'];
    $id = $user['id'];

    //var_dump(ROOT);
    $action = ROOT . 'user/get/' . $id;

    $action2 = ROOT . 'user/edit';

    
     if(isset($_GET['edit'])) {
        echo "
        <div class='col-md-6 mt-5'>
        <form action='$action2' method='post' enctype='multipart/form-data'>
            <div class='form-group'>
                <h3>Name</h3>
                <input type='text' class='form-control' value='$name' name='name'>
            </div>
            <div class='form-group'>
                <h3>Email</h3>
                <input type='email' class='form-control' value='$email' name='email'>
            </div>
            <div class='form-group'>
                <h3>Phone Number</h3>
                <input type='text' class='form-control' value='$phoneNumber' name='phone'>
            </div>
            <input type='hidden' name='id' value = '$id'>
            <input type='hidden' name='save'>
            <input type='file' name='upload'>
            <button type='submit' class='btn btn-success mr-2'>Save</button>

            
            
        </form>
        </div>
       
         ";
      } else {
        echo "
    <div class='col-md-6 mt-5'>
        <div class='d-flex justify-content-between'>
            <h3>Name</h3>
            <p>$name</p>

        </div>
        <div class='d-flex justify-content-between'>
            <h3>Email</h3>
            <p>$email</p>

        </div>
        <div class='d-flex justify-content-between'>
            <h3>Password</h3>
            <p>...</p>

        </div>
    
        <div class='d-flex justify-content-between'>
            <h3>Phone Number</h3>
            <p>$phoneNumber</p>
        </div>
    </div>
        <form action='$action' method='get'>
            <input type='hidden' name='edit'>
            <button type='submit' class='btn btn-warning mr-2'>Edit</button>
        </form>
        ";
      } 
 
    }
?>

  
