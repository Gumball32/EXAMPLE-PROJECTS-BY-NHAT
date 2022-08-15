<?php
include 'includes/head.php';

$memes = new Meme($conn);

$memesArr = $memes->getMemes();
?>
    <!-- Begin page content -->
    <div class="jumbotron" style="background: url(images/bg2.jpg); background-size:cover;">
    <main role="main" class="container mt-4 py-3">
      <div class="p-5 bg-white">
      <h1 class="py-2"><i class="fa fa-book" aria-hidden="true"></i> Best Memes to Cringe to</h1>
      <p class="lead">Output the 12 memes using BS4 Cards (img/title/text). Link them their single memes using a get request with the meme ID.</p>
      </div>
      </main>
    </div>
      <hr>
    <div class="container py-4">
      <h3 class="font-weight-light">Recent Memes</h3>
      <hr>
      <div class="row">
        <?php  foreach($memesArr as $meme):  ?>
          <div class="col-md-4 my-3 d-flex">
            <div class="card w-100">

            <a href="meme.php?id=<?php echo $meme['id']; ?>"><img class="card-img-top" src="<?= $meme['meme_img'] ?>" height="200px" style="object-fit:cover" width="100%" alt=""></a>
              <div class="card-body">
               <a href="meme.php?id=<?php echo $meme['id']; ?>"> <h3 class="card-title"><?= $meme['meme_title'] ?></h3></a>
                <p><?= $meme['meme_body'] ?></p>
              </div>
              <div class="card-footer d-flex justify-content-between">
                <div>
              <?= $meme['username'] . " | " . date("d M Y", strtotime($meme['date_created'])); ?>
              </div>
              <div>
              <a href="meme.php?id=<?php echo $meme['id']; ?>#comments"><i class="fa fa-comment" aria-hidden="true"></i> <?= $meme['num_comments'];?></a>
              </div>
              </div>
            </div>
          </div>

          <?php endforeach; ?>
      </div>
</div>
<?php
include "includes/footer.php";
?>