var slideIndex = 1;
showSlides(slideIndex);

// Next/previous controls
function plusSlides(n) {
  showSlides(slideIndex += n);
}

// Thumbnail image controls
function currentSlide(n) {
  showSlides(slideIndex = n);
}

function showSlides(n) {
  var i;
  var slides = document.getElementsByClassName("mySlides");
  var dots = document.getElementsByClassName("dot");
  if (n > slides.length) {slideIndex = 1}
  if (n < 1) {slideIndex = slides.length}
  for (i = 0; i < slides.length; i++) {
      slides[i].style.display = "none";
  }
  for (i = 0; i < dots.length; i++) {
      dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex-1].style.display = "block";
  dots[slideIndex-1].className += " active";
}


var slideIndex2 = 0;
showSlides2();

function showSlides2() {
  var i;
  var slides = document.getElementsByClassName("mySlides");
  for (i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";
  }
  slideIndex2++;
  if (slideIndex2 > slides.length) {slideIndex2 = 1}
  slides[slideIndex2-1].style.display = "block";
  setTimeout(showSlides2, 10000);
}


var slideIndex3 = 1;
showSlides11(slideIndex3);
function showSlides11(n) {
  var i;
  var slides2 = document.getElementsByClassName("mySlides2");
  if (n > slides2.length) { slideIndex3 = 1 }
  if (n < 1) { slideIndex3 = slides2.length }
  for (i = 0; i < slides2.length; i++) {
    slides2[i].style.display = "none";
  }
  slides2[slideIndex3 - 1].style.display = "block";
}

function plusSlides2(n) {
  showSlides11(slideIndex3 += n);
}

let navCheck = document.querySelector('.mobile-input');
navCheck.addEventListener("click", (event) => {
  console.log("a");
  if (navCheck.checked) {
    let headerMain = document.querySelector(".header-main");
    headerMain.classList.add("header-main-2");
    let navSet = document.querySelector(".header-nav-settings");
    navSet.classList.add("header-nav-settings-2");
    let headerNavUser = document.querySelector(".header-nav-user");
    headerNavUser.classList.add("header-nav-user-2");
    let headerNavSection = document.querySelector(".header-nav-sections");
    headerNavSection.classList.add("header-nav-sections-2");
    let headerNavSecA = document.querySelector(".header-nav-sections ul li a");
    headerNavSecA.classList.add("header-nav-sections-2-ul-li-a");
    let headerNavSecul = document.querySelector(".header-nav-sections ul");
    headerNavSecul.classList.add("header-nav-sections-2-ul");
  }
  else {
    let headerMain = document.querySelector(".header-main");
    headerMain.classList.remove("header-main-2");
    let navSet = document.querySelector(".header-nav-settings");
    navSet.classList.remove("header-nav-settings-2");
    let headerNavUser = document.querySelector(".header-nav-user");
    headerNavUser.classList.remove("header-nav-user-2");
    let headerNavSection = document.querySelector(".header-nav-sections");
    headerNavSection.classList.remove("header-nav-sections-2");
    let headerNavSecA = document.querySelector(".header-nav-sections ul li a");
    headerNavSecA.classList.remove("header-nav-sections-2-ul-li-a");
    let headerNavSecul = document.querySelector(".header-nav-sections ul");
    headerNavSecul.classList.remove("header-nav-sections-2-ul");
  }
})


window.onscroll = function () { myFunction() };

var navbar = document.getElementsByClassName(".header-main");

var sticky = navbar.offsetTop;

window.onscroll = function () { scrollFunction() };

function scrollFunction() {
  if (document.body.scrollTop > 80 || document.documentElement.scrollTop > 150) {
    document.querySelector("header").setAttribute("style", "background:black")
  } else {
    document.querySelector("header").setAttribute("style", "background:linear-gradient(to bottom,#25211e 0,rgba(37,33,30,0) 100%)")
  }
}

