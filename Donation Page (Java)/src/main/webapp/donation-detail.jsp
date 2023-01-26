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

<title>Kind Heart Charity - Post Detail</title>

<!-- CSS FILES -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<link href="css/bootstrap-icons.css" rel="stylesheet">

<link href="css/templatemo-kind-heart-charity.css" rel="stylesheet">

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-icons.css" rel="stylesheet">
<link href="css/templatemo-kind-heart-charity.css" rel="stylesheet">
</head>
<body>
	<%@ include file="header.jsp" %>
		<main>

        <section class="news-detail-header-section text-center">
            <div class="section-overlay"></div>

            <div class="container">
                <div class="row">

                    <div class="col-lg-12 col-12">
                        <h1 class="text-white">Post Detail</h1>
                    </div>

                </div>
            </div>
        </section>

        <section class="news-section section-padding">
            <div class="container">
                <div class="row">

                    <div class="col-lg-12 col-12">
                        <div class="news-block">
                            <div class="news-block-top d-flex justify-content-center">
                                <img src="${post.mainImage}"
		                                        class="rounded mx-auto d-block" alt="" style="
		                                        width: 100%;
												height: 70vh;
												object-fit: contain;
												
		                                        ">

                                <div class="news-category-block">
                                    <a href="#" class="category-block-link">
                                        Lifestyle,
                                    </a>

                                    <a href="#" class="category-block-link">
                                        Clothing Donation
                                    </a>
                                </div>
                            </div>

                            <div class="news-block-info">
                                <div class="d-flex mt-2">
                                    <div class="news-block-date">
                                        <p>
                                            <i class="bi-calendar4 custom-icon me-1"></i>
                                            Start Date: ${post.startDate}
                                        </p>
                                    </div>

                                    <div class="news-block-author mx-5">
                                        <p>
                                            <i class="bi-person custom-icon me-1"></i>
                                            By Admin
                                        </p>
                                    </div>
                                    
                                    <div class="news-block-author mx-5">
                                        <p>
                                            <i class="bi-person custom-icon me-1"></i>
                                            Total Donation: ${total}
                                        </p>
                                    </div>

                                    <div class="news-block-comment">
                                        <p>
                                            <i class="bi-chat-left custom-icon me-1"></i>
                                            End Date: ${post.endDate}
                                        </p>
                                    </div>
                                </div>

                                <div class="news-block-title mb-2">
                                    <h2>${post.name}</h2>
                                </div>
                                <% if (user1 != null) { %>
                                <a href="DonationController?action=showDonate&id=${post.ID}" class="btn btn-success">Donate now</a>
                                <% } %>
                                <% if (user1 != null && user1.getRole() == 1) { %>
                                <a href="CharityPostController?action=edit&id=${post.ID}" class="btn btn-primary">Edit</a>
                                <a href="delete_confirmation.jsp?url=CharityPostController&action=delete&id=${post.ID}" class="btn btn-danger">Delete</a>
                                <a href="CharityPostController?action=endDonate&id=${post.ID}" class="btn btn-warning">End Donate</a>
								<% } %>
                                <!-- <div class="news-block-body">
                                    <p><strong>Lorem Ipsum</strong> dolor sit amet, consectetur adipsicing kengan omeg
                                        kohm tokito Professional charity theme based on Bootstrap</p>

                                    <p><strong>Sed leo</strong> nisl, This is a Bootstrap 5.2.2 CSS template for charity
                                        organization websites. You can feel free to use it. Please tell your friends
                                        about TemplateMo website. Thank you.</p>

                                    <blockquote>Sed leo nisl, posuere at molestie ac, suscipit auctor mauris. Etiam quis
                                        metus elementum, tempor risus vel, condimentum orci.</blockquote>
                                </div>
 -->
 
 								<div class="row mt-5 mb-4">
 									<h4>Description: </h4>
 									<p>${post.description}</p>
 								</div>
                                <div class="row mt-5 mb-4">
                                    <div class="col-lg-6 col-12 mb-4 mb-lg-0">
                                        <img src="images/news/africa-humanitarian-aid-doctor.jpg"
                                            class="news-detail-image img-fluid" alt="">
                                    </div>

                                    <div class="col-lg-6 col-12">
                                        <img src="images/news/close-up-happy-people-working-together.jpg"
                                            class="news-detail-image img-fluid" alt="">
                                    </div>
                                </div>

                                

                                <div class="social-share border-top mt-5 py-4 d-flex flex-wrap align-items-center">
                                    <div class="tags-block me-auto">
                                        <a href="#" class="tags-block-link">
                                            Donation
                                        </a>

                                        <a href="#" class="tags-block-link">
                                            Clothing
                                        </a>

                                        <a href="#" class="tags-block-link">
                                            Food
                                        </a>
                                    </div>

                                    <div class="d-flex">
                                        <a href="#" class="social-icon-link bi-facebook"></a>

                                        <a href="#" class="social-icon-link bi-twitter"></a>

                                        <a href="#" class="social-icon-link bi-printer"></a>

                                        <a href="#" class="social-icon-link bi-envelope"></a>
                                    </div>
                                </div>

                                <div class="author-comment d-flex mt-3 mb-4">
                                    <img src="images/avatar/studio-portrait-emotional-happy-funny.jpg"
                                        class="img-fluid avatar-image" alt="">

                                    <div class="author-comment-info ms-3">
                                        <h6 class="mb-1">Jack</h6>

                                        <p class="mb-0">Kind Heart Charity is the most supportive organization. This is
                                            Bootstrap 5 HTML CSS template for everyone. Thank you.</p>

                                        <div class="d-flex mt-2">
                                            <a href="#" class="author-comment-link me-3">Like</a>

                                            <a href="#" class="author-comment-link">Reply</a>
                                        </div>
                                    </div>
                                </div>

                                <div class="author-comment d-flex ms-5 ps-3">
                                    <img src="images/avatar/pretty-blonde-woman-wearing-white-t-shirt.jpg"
                                        class="img-fluid avatar-image" alt="">

                                    <div class="author-comment-info ms-3">
                                        <h6 class="mb-1">Daisy</h6>

                                        <p class="mb-0">Sed leo nisl, posuere at molestie ac, suscipit auctor mauris.
                                            Etiam quis metus elementum, tempor risus vel, condimentum orci</p>

                                        <div class="d-flex mt-2">
                                            <a href="#" class="author-comment-link me-3">Like</a>

                                            <a href="#" class="author-comment-link">Reply</a>
                                        </div>
                                    </div>
                                </div>

                                <div class="author-comment d-flex mt-3 mb-4">
                                    <img src="images/avatar/portrait-young-redhead-bearded-male.jpg"
                                        class="img-fluid avatar-image" alt="">

                                    <div class="author-comment-info ms-3">
                                        <h6 class="mb-1">Wilson</h6>

                                        <p class="mb-0">Lorem Ipsum dolor sit amet, consectetur adipsicing kengan omeg
                                            kohm tokito Professional charity theme based on Bootstrap</p>

                                        <div class="d-flex mt-2">
                                            <a href="#" class="author-comment-link me-3">Like</a>

                                            <a href="#" class="author-comment-link">Reply</a>
                                        </div>
                                    </div>
                                </div>

                                <form class="custom-form comment-form mt-4" action="#" method="post" role="form">
                                    <h6 class="mb-3">Write a comment</h6>

                                    <textarea name="comment-message" rows="4" class="form-control" id="comment-message"
                                        placeholder="Your comment here"></textarea>

                                    <div class="col-lg-3 col-md-4 col-6 ms-auto">
                                        <button type="submit" class="form-control">Comment</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </section>
    </main>
	<%@ include file="footer.jsp" %>
</body>
</html>