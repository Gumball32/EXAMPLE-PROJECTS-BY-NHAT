<?php
include 'includes/head.php';
    $errors = [];
if(isset($_POST['username'])) {
    var_dump($_POST);
    $user = new User($conn);
    $user->validateLogin($_POST);
    $errors = $user->errors;
}
?>
<div class="container mt-5 py-5">
    <?php if(!empty($errors)): ?>
        <div class="alert alert-danger">
            <?php foreach($errors as $err) {
                echo "{$err} <br>";
            }
            ?>
        </div>
    <?php endif; ?>
    <div class="row">
    <div class="col-md-6 offset-md-3">
        <h3 class="font-weight-light">
            Login please-
        </h3>
        <form action="login.php" method="post">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" class="form-control" name="username">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" name="password">
            </div>
            <button type="submit" name="" id="" class="btn btn-primary btn-lg btn-block"> <i class="fa fa-plus-circle" aria-hidden="true"></i>  Login </button>
        </form>
            <p class="mt-3">username: admin</p>
            <p>password: itec2020</p>
    </div>  
    </div>
</div>
<?php
    include "includes/footer.php";
?>