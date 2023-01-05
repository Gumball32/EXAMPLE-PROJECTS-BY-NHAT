<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta name="description" content="">
<meta name="author" content="">

<title>Forever Love Charity - Donation</title>

<!-- CSS FILES -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<link href="css/bootstrap-icons.css" rel="stylesheet">

<link href="css/templatemo-kind-heart-charity.css" rel="stylesheet">

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-icons.css" rel="stylesheet">
<link href="css/templatemo-kind-heart-charity.css" rel="stylesheet">
</head>
<body>
	<%@ include file="header.jsp"  %>
	<section class="donate-section">
            <div class="section-overlay"></div>
            <div class="container">
                <div class="row">

                    <div class="col-lg-6 col-12 mx-auto">
                        <form class="custom-form donate-form" action="DonationController" method="GET" role="form">
                            <h3 class="mb-4">Make a donation</h3>
                            <div class="row">
                                <div class="col-lg-12 col-12">
                                    <h5 class="mt-2 mb-3">Select an amount</h5>
                                </div>

                                <div class="col-lg-6 col-12 form-check-group">
                                    <div class="input-group">
                                        <span class="input-group-text" id="basic-addon1">$</span>

                                        <input type="number" name="amount" class="form-control" placeholder="Custom amount"
                                            aria-label="Username" aria-describedby="basic-addon1" required>
                                    </div>
                                </div>

                                <div class="col-lg-12 col-12">
                                    <input type="text" name="transaction-description" class="form-control"
                                        placeholder="Enter description of the transaction" required>
                                </div>
									<input class="hidden" name="action" value="donate" style="visibility:hidden">
									<input class="hidden" name="postId" value="${postId}" style="visibility:hidden">
                                    <button type="submit" class="form-control mt-5">Submit Donation</button>
                               </div>
                              </form>
                          </div>
                    </div>

                </div>
        </section>
	<%@ include file="footer.jsp"  %>
</body>
</html>