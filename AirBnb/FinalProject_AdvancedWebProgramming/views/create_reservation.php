<?php
include "views/inc/header.php";
var_dump($resObj);
?>

<body>
	<div id="booking" class="section">
		<div class="section-center">
			<div class="container">
				<div class="row">
					<div class="booking-form">
						<form action="<?= ROOT ?>reservations/create" method="post">
							<div class="form-header">
								<h2>Make your reservation</h2>
							</div>
							<div class="form-group">
								<input name = "name" class="form-control" type="text" placeholder="Enter your name">
								<span class="form-label">Name</span>
							</div>
							<div class="form-group">
								<input name="phone" class="form-control" type="tel" placeholder="Enter your phone number">
								<span class="form-label">Phone</span>
							</div>
							
							<div class="form-group">
								<select class="form-control" required>
									<option value="" label="&nbsp;" selected hidden></option>
									<option>1</option>
									<option>2</option>
									<option>3</option>
								</select>
								<span class="select-arrow"></span>
								<span class="form-label">Passenger Numbers</span>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<input name="startDate" class="form-control" type="date" required>
										<span class="form-label">Pickup Start Date</span>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<input name="endDate" class="form-control" type="date" required>
										<span class="form-label">Pickup End Date</span>
									</div>
								</div>
								
							</div>
							<div class="form-btn">
								<button type="submit" class="submit-btn">Book Now</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="../js/jquery.min.js"></script>
	<script>
		$('.form-control').each(function () {
			floatedLabel($(this));
		});

		$('.form-control').on('input', function () {
			floatedLabel($(this));
		});

		function floatedLabel(input) {
			var $field = input.closest('.form-group');
			if (input.val()) {
				$field.addClass('input-not-empty');
			} else {
				$field.removeClass('input-not-empty');
			}
		}
	</script>

</body><!-- This templates was made by Colorlib (https://colorlib.com) -->

<?php
include "views/inc/footer.php";
?>

</html>