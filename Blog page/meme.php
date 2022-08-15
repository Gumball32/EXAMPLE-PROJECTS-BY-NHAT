<?php
include 'includes/head.php';

$meme = [];

if(isset($_GET['id'])) {
    $memes = new Meme($conn);
    $comment = new Comment($conn);
    $comments = $comment->getComments($_GET['id']);
    $meme = $memes->getMeme($_GET['id']);
}
?>

<div class="container py-4 my-4">
    <?php if(empty($meme)):  ?>
       <h1 class="display-4 my-5 py-4"> <i class="fa fa-frown" aria-hidden="true"></i> Meme not found!</h1>
    <?php else: ?>
    <div class="row">
        <div class="col-md-6 offset-md-3 my-3 d-flex mt-5">
        <div class="card w-100">
            <img class="card-img-top" src="<?= $meme['meme_img'] ?>" height="200px" style="object-fit:cover" width="100%" alt="">
            <div class="card-body">
            <h3 class="card-title"><?= $meme['meme_title'] ?></h3>
            <div class="d-flex justify-content-between">
                <div>
                <?= $meme['username'] . " | " . date("d M Y", strtotime($meme['date_created'])); ?>
                </div>
                <div>
                <i class="fa fa-comment" aria-hidden="true"></i> <?= count($comments);?>
                </div>
            </div>
            <hr class="my-3">
            <p><?= $meme['meme_body'] ?></p>
            <div class="d-flex justify-content-between">
                <a href="index.php"><button class="btn btn-outline-warning btn-sm"><i class="fa fa-arrow-circle-left" aria-hidden="true"></i> Back</button></a>
                <?php if($_SESSION['logged_in'] && $_SESSION['role'] === 1): ?>
                <form action="meme.php" method="post">
                    <button type="submit" class="btn btn-danger btn-sm"><i class="fa fa-trash-o" aria-hidden="true"></i> Delete</button>
                </form>
                <?php endif; ?>
            </div>
            </div>
            <div class="card-footer" id="comments">
                <?php if(!empty($comments)): ?>
                    <?php foreach($comments as $comment): ?>
                    <p class="my-1">
                       <?= $comment['comment_text'] ?>
                    </p>
                    <p class="font-weight-light font-italic my-1">
                        <?= $comment['username'] ?>
                    </p>
                    <hr>
                    <?php endforeach; ?>
                <?php else: ?>
                    <p class="font-weight-light font-italic">
                        No comments yet...
                    </p>
                <?php endif; ?>
            </div>
        </div>
        </div>
    </div>
    <?php endif; ?>
</div>
<?php
    include "includes/footer.php";
?>