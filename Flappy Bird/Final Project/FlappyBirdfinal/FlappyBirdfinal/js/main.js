//playground
let playGroundDiv = document.querySelector('.playGround');

// Background scrolling speed
let move_speed = 4;

// Gravity constant value
let gravity = 0.5;

// Getting reference to the bird element
let bird = document.querySelector('.bird');

// Getting bird element properties
let bird_props = bird.getBoundingClientRect();
let background =
    document.querySelector('.background')
    .getBoundingClientRect();

// Getting reference to the score element
let score_val =
    document.querySelector('.score_val');
let message =
    document.querySelector('.message');
let score_title =
    document.querySelector('.score_title');

// Setting initial game state to start
let game_state = 'Start';



//Constant value for the gap between two pipes
let pipe_gap = 28;

//how often we make pipe
let distance = 100;

// Setting the level 
let easy = document.querySelector('.level_easy');
let medium = document.querySelector('.level_medium');
let difficult = document.querySelector('.level_difficult');

easy.addEventListener('click', function() {
    pipe_gap = 30;
    distance = 115;
    move_speed = 2;
});
medium.addEventListener('click', function() {
    pipe_gap = 28;
    distance = 100;
    move_speed = 4;
});
difficult.addEventListener('click', function() {
    pipe_gap = 27;
    move_speed = 6;
    distance = 80;
});

// Add an eventlistener for key presses
document.addEventListener('keydown', (e) => {

    // Start the game if enter key is pressed
    if (e.key == 'Enter' &&
        game_state != 'Play') {
        document.querySelectorAll('.pipe')
            .forEach((e) => {
                e.remove();
            });
        bird.style.top = '40vh';
        game_state = 'Play';
        message.innerHTML = '';
        score_title.innerHTML = 'Score : ';
        score_val.innerHTML = '0';
        play();
    }
});

function play() {
    function move() {

        // Detect if game has ended
        if (game_state != 'Play') return;

        // Getting reference to all the pipe elements
        let pipe = document.querySelectorAll('.pipe');
        pipe.forEach((element) => {

            let pipe_props = element.getBoundingClientRect();
            bird_props = bird.getBoundingClientRect();

            // Delete the pipes if they have moved out
            // of the screen hence saving memory
            if (pipe_props.right <= 0) {
                element.remove();
            } else {
                // Collision detection with bird and pipes
                if (
                    bird_props.left < pipe_props.left +
                    pipe_props.width &&
                    bird_props.left +
                    bird_props.width > pipe_props.left &&
                    bird_props.top < pipe_props.top +
                    pipe_props.height &&
                    bird_props.top +
                    bird_props.height > pipe_props.top|| (bird_props.top <= 0 ||
                        bird_props.bottom >= background.bottom)
                ) {

                    // Change game state and end the game
                    // if collision occurs
                    game_state = 'End';
                    message.innerHTML = 'Press Enter To Restart';
                    message.style.left = '28vw';
                    bird.style.top = '40vh';
                    return;
                } else {
                    // Increase the score if player
                    // has the successfully dodged the 
                    if (
                        pipe_props.right < bird_props.left &&
                        pipe_props.right +
                        move_speed >= bird_props.left &&
                        element.increase_score == '1'
                    ) {
                        score_val.innerHTML = +score_val.innerHTML + 1;
                    }
                    element.style.left =
                        pipe_props.left - move_speed + 'px';
                }
            }
            let score = document.querySelector('.updateScore');
            score.textContent = score_val.textContent;
        });

        requestAnimationFrame(move);
    }
    requestAnimationFrame(move);

    let bird_dy = 0;

    function apply_gravity() {
        if (game_state != 'Play') return;
        bird_dy = bird_dy + gravity;
        document.addEventListener('keydown', (e) => {
            if (e.key == 'ArrowUp' || e.key == ' ') {
                bird_dy = -7.6;
            }
        });

        // Collision detection with bird and
        // window top and bottom
        bird.style.top = bird_props.top + bird_dy + 'px';
        bird_props = bird.getBoundingClientRect();
        requestAnimationFrame(apply_gravity);
    }
    requestAnimationFrame(apply_gravity);

    let pipe_seperation = 0;

    function create_pipe() {
        if (game_state != 'Play') return;

        // Create another set of pipes
        // if distance between two pipe has exceeded
        // a predefined value
        if (pipe_seperation > distance) {

            pipe_seperation = 0;

            // Calculate random position of pipes on y axis
            let pipe_posi = Math.floor(Math.random() * 48) + 8;
            let pipe_inv = document.createElement('div');
            pipe_inv.className = 'pipe';
            pipe_inv.style.top = pipe_posi - 70 + 'vh';
            pipe_inv.style.left = '100vw';

            // Append the created pipe element in DOM
            document.body.appendChild(pipe_inv);
            let pipe = document.createElement('div');
            pipe.className = 'pipe';
            pipe.style.top = pipe_posi + pipe_gap + 'vh';
            pipe.style.left = '100vw';
            pipe.increase_score = '1';

            // Append the created pipe element in DOM
            document.body.appendChild(pipe);
        }
        pipe_seperation++;
        // pipe_gap--;

        requestAnimationFrame(create_pipe);
    }
    requestAnimationFrame(create_pipe);


    // changle level





}

let btnPlay = document.querySelector(".btn-play");
btnPlay.addEventListener('click', function() {    
    let menuDiv = document.querySelector(".main-menu");
    menuDiv.classList.add('Z-1');
});

let btnBack = document.querySelector(".btn-back");
btnBack.addEventListener('click', function() {    
    let menuDiv = document.querySelector(".main-menu");
    menuDiv.classList.contains('Z-1');
    menuDiv.classList.remove('Z-1');
});


let cus1 = document.querySelector('.cus1');
let cus2 = document.querySelector('.cus2');
let cus3 = document.querySelector('.cus3');
let cus4 = document.querySelector('.cus4');
let cus5 = document.querySelector('.cus5');
let cus6 = document.querySelector('.cus6');

cus1.addEventListener("click", change1);
cus2.addEventListener("click", change2);
cus3.addEventListener("click", change3);
cus4.addEventListener("click", change4);
cus5.addEventListener("click", change5);
cus6.addEventListener("click", change6);

function change1(){
    let img = document.querySelector('.bird img');
    img.setAttribute("src", 'img/flapp.png');
}
function change2(){
    let img = document.querySelector('.bird img');
    img.setAttribute("src", 'img/bird8.png');
}
function change3(){
    let img = document.querySelector('.bird img');
    img.setAttribute("src", 'img/bird6.png');
}

function change4(){
    let img = document.querySelector('.bird img');
    img.setAttribute("src", 'img/bird3.png');
}
function change5(){
    let img = document.querySelector('.bird img');
    img.setAttribute("src", 'img/bird4.png');
}
function change6(){
    let img = document.querySelector('.bird img');
    img.setAttribute("src", 'img/bird5.png');
}


let leadBtn = document.querySelector('.btn-leaderboard');
leadBtn.addEventListener("click", show); 
function show(){
    document.getElementById("hide").style.display = "block";
}

let closeBtn = document.querySelector('.close');
closeBtn.addEventListener("click", hideBoard);
function hideBoard(){
    document.getElementById("hide").style.display = "none";
}


let bg1 = document.querySelector('.bg1');
let bg2 = document.querySelector('.bg2');
let bg3 = document.querySelector('.bg3');
let bg4 = document.querySelector('.bg4');
let bg5 = document.querySelector('.bg5');
let bg6 = document.querySelector('.bg6');

bg1.addEventListener("click", bgchange1);
bg2.addEventListener("click", bgchange2);
bg3.addEventListener("click", bgchange3);
bg4.addEventListener("click", bgchange4);
bg5.addEventListener("click", bgchange5);
bg6.addEventListener("click", bgchange6);

function bgchange1(){
    document.getElementById("bg").style.backgroundImage = "url('img/jungle.png')";
}
function bgchange2(){
    document.getElementById("bg").style.backgroundImage = "url('img/city.png')";
}
function bgchange3(){
    document.getElementById("bg").style.backgroundImage = "url('img/christmas.jpg')";
}
function bgchange4(){
    document.getElementById("bg").style.backgroundImage = "url('img/background.png')";
}
function bgchange5(){
    document.getElementById("bg").style.backgroundImage = "url('img/night.jpg')";
}
function bgchange6(){
    document.getElementById("bg").style.backgroundImage = "url('img/flower.png')";
}

//record
let record = {
    "name": '',
    "nickName": '',
    "score": ''
}
let participantList = document.querySelector('.participant');

let submitBtn = document.querySelector('.submit');
submitBtn.addEventListener("click", function(event) {
    event.preventDefault();
    record = {
        name: document.querySelector('.name').value,
        nickName: document.querySelector('.username').value,
        score: score_val.textContent
    }
    console.log(score_val.textContent);
    addToChart(record);
    alert("Submitted");
})

//leaderboard
function init() {
    //console.log('init');
    if (!localStorage.hasOwnProperty("chart")) {
        let chart = [];
        localStorage.setItem("chart", JSON.stringify(chart));
        console.log(localStorage.getItem("chart"));
    }
    
}
init();

function addToChart(play) {
    //console.log('add to chart');
    let chart = [];
    chart = JSON.parse(localStorage.getItem('chart'));
    chart.forEach((record) => {
        if (record.name == play.name) {
            return;
        }
    })
    chart.push(play);
    updateChart(chart);
    localStorage.setItem('chart', JSON.stringify(chart));
    console.log(chart);
}

function updateChart(chart) {
    //console.log('update');
    let newRecord = '';
    if (chart.length < 0) {
        return;
    }
    let m = 1;
    chart.forEach((record, i) => {
        console.log(record.name);
        console.log(record.score);
        console.log(record.nickName);
        newRecord += `<tr>
                        <th scope="row">${m++}</th>
                        <td>${record.name}</td>
                        <td>${record.score}</td>
                        <td>${record.nickName}</td>
                      </tr>`;
    })
    participantList.innerHTML = newRecord;
}

let btnRecord = document.querySelector('.recordsaved');
btnRecord.addEventListener("click", SaveRecord);

function SaveRecord(){
    let form = document.querySelector('.form');
    form.classList.toggle("d-none");
}

