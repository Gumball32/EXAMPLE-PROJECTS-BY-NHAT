let desBtn = document.querySelector('.btn-destination');
desBtn.addEventListener('click', function() {
    let menuDes = document.querySelector('.menuDes');
    console.log(menuDes);
    menuDes.classList.toggle("hidden");
})

let scheBtn = document.querySelector('.btn-scheduel');
scheBtn.addEventListener('click', function() {
    let calendar = document.querySelector('.calendar');
    console.log(calendar);
    calendar.classList.toggle("hidden");
})
let guestBtn = document.querySelector(".btn-filter");
guestBtn.addEventListener('click', function() {
    let guest = document.querySelector('.menuGuest');
    console.log(guest);
    guest.classList.toggle("hidden");
})

//  Scroll Horizontally

let currentScrollPosition = 0;
let scrollAmount = 320;

const sCont = document.querySelector(".main-content");
const hScroll = document.querySelector(".horizontal-scroll");
const btnScrollLeft = document.querySelector("#btn-scroll-left");
const btnScrollRight = document.querySelector("#btn-scroll-right");

btnScrollLeft.style.opacity = "0";

let maxScroll = sCont.offsetWidth + hScroll.offsetWidth;

function scrollHorizontally(val) {
    currentScrollPosition += (val * scrollAmount);

    if (currentScrollPosition >= 0) {
        currentScrollPosition = 0;
        btnScrollLeft.style.opacity = "0";
    } else {
        btnScrollLeft.style.opacity = "1";
    }

    if (currentScrollPosition >= maxScroll) {
        currentScrollPosition = maxScroll;
        btnScrollRight.style.opacity = "0";
    } else {
        btnScrollRight.style.opacity = "1";
    }

    sCont.style.left = currentScrollPosition + "px";
}

//Scroll Horizontally active hover

/*var btnContainer = document.getElementById("mainContents");
var btns = btnContainer.getElementsByClassName("btn");

for(var i = 0; i < btns.length; i++) {
    btns[i].addEventListener('click', function() {
        var current = document.getElementsByClassName("active");
        current[0].className = current[0].className.replace(" active", "");
        this.className += " active";
    })
}*/


function myFunction() {
    var btns = document.querySelector(".active");
    if (btns !== null) {
        btns.classList.remove("active");
    } else {
        btns.classList.addClass(" active");
    }
    //e.target.className = "active";
}

// Login user

/* When the user clicks on the button,
toggle between hiding and showing the dropdown content */
function myDropdownFunction() {

    document.getElementById("myDropdownMenu").style.display = "block";

}

// Close the dropdown menu if the user clicks outside of it
window.onclick = function(event) {
    if (!event.target.matches('.btn-dropdown')) {
        var dropdowns = document.getElementsByClassName("dropdown-menu");
        var i;
        for (i = 0; i < dropdowns.length; i++) {
            var openDropdown = dropdowns[i];
            if (openDropdown.classList.contains('show')) {
                openDropdown.classList.remove('show');
            }
        }
    }
}