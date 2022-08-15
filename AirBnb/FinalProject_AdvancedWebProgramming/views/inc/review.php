<style>
    <?php include "public/css/review.css";?>
</style>

<div class="review-add border mt-3">
    <div class="container">
        <form action="<?= ROOT ?>rooms/get/<?= $room['id'] ?>" method="get">
            <label class="mt-3" for="review font-weight-light my-3"><h2>Review: </h2></label>
            <hr class="p-3">
            <form>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="rating">Rate:</label>
                        <input type="number" name="rating" class="form-control" id="rating" placeholder="Enter rating here..">
                    </div>

                    <div class="form-group col-md-6">
                        <label for="reservation_id">Reservation ID:</label>
                        <input type="number" name="reservation_id" class="form-control" id="reservation_id" placeholder="Enter reservation id here..">
                    </div>
                </div>
                <div class="form-group">
                    <label for="reservation_id">Your Review:</label>
                    <textarea name="review" id="review" placeholder="Enter your review here..." rows="4" class="form-control edit-body"></textarea>
                    <input type="hidden" class="form-control" name="review_room_id" value="<?= $room['id'] ?>">
                </div>
                <button type="submit" class="btn-save text-white mt-2"><i class="fa fa-save mr-2"></i>Send</button>
            </form>
        </form>
    </div>
</div>