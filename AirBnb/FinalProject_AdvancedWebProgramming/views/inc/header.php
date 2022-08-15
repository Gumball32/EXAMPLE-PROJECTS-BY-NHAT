<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Airbnb Vacation Homes</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.0/css/all.min.css" integrity="sha512-10/jx2EXwxxWqCLX/hHth/vu2KY3jCF70dCQB8TSgNjbCVAC/8vai53GfMDrO2Emgwccf2pJqxct9ehpzG+MTw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <link rel="stylesheet" href="<?= ROOT ?>public/css/style.css">
    <link rel="stylesheet" href="<?= ROOT ?>public/css/createReservation.css">
</head>
<body>
    <header>
        <div class="container">
            <div class="wrapper d-flex justify-content-between align-items-center pt-3">
                <a class="navbar-brand" href="<?= ROOT ?>rooms"><img src="<?= ROOT ?>public/images/logo.png" width="100px"></a>
                <div class="choices btn btn-md shadow-lg d-flex p-1">
                    
                    <button class="btn-destination btn btn-link text-dark font-weight-bold mx-3">Anywhere</button>
    
                    <button class="btn-scheduel btn btn-link text-dark font-weight-bold schedule mr-3">Anyweek</button>
                    
                    <!-- <button type="text" disabled autocomplete="off" name="search" class="form-control p-3" placeholder="Add guest..."> -->
                    <!-- <div class="icon"><i class="fa-solid fa-magnifying-glass"></i></div> -->
                </div>

                <div class="profile d-flex align-items-center">
                    <a href="<?= ROOT ?>users/create"><button class="btn btn-md btn-light text-dark mr-3">Sign Up</button></a>
                    <button class="btn btn-link text-dark"><i class="fa-solid fa-globe"></i></button>
                    <div class="header-part1 ml-3">
                        <div class="dropdown">
                            <button onclick="myDropdownFunction()" class="btn-dropdown"><i class="fa fa-bars"></i><i class="fa fa-user ml-3"></i></button>
                                <div id="myDropdownMenu" class="dropdown-menu">
                                    <?php if($_SESSION['logged_in']): ?>
                                        <a id='create-room' href="<?= ROOT ?>rooms/create">Register A Room</a>
                                        <a id='profile' href="<?= ROOT ?>user/get/<?=$_SESSION['user_id']?>">My Profile</a>
                                    <?php endif; ?>
                                    <?php if($_SESSION['logged_in'] == false): ?>
                                        <a id='login' href="<?= ROOT ?>users/login">Log In</a>
                                    <?php else: ?>
                                        <a id='logout' href="<?= ROOT ?>users/logout">Log Out</a>
                                    <?php endif; ?>
                                </div><!--end of udropdown-menu-->
                            </div><!--end of user-->
                        </div><!--end of header-part1-->
                    </div>
            </div>
        </div>
    </header>
    <?php Messenger::checkMsg(); ?>

    <div class="header-part2">
                <!--Header menu choices-->
                <div class="container">
                    <div class="horizontal-scroll">
                        <div class="main-content" id="mainContents" onclick="myFunction(event)">
                            <!--Island 1-->
                            <li>
                                <a href="#" class="active btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/8e507f16-4943-4be9-b707-59bd38d56309.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Island</p>
                                </a><!--end of menu-->
                            </li>

                            <!--Beach 2-->
                            <li>
                                <a href="#" class="btn">
                                    <img class="mx-auto d-block" src="https://a0.muscache.com/pictures/10ce1091-c854-40f3-a2fb-defc2995bcaf.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Beach</p>
                                </a><!--end of menu-->
                            </li>
                            
                            <!--Great swimming pool 3-->
                            <li>
                                <a href="#" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/3fb523a0-b622-4368-8142-b5e03df7549b.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Great swimming pool</p>
                                </a><!--end of menu-->
                            </li>
                            
                            <!--Impressive! 4-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/c5a4f6fc-c92c-4ae8-87dd-57f1ff1b89a6.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Impressive!</p>
                                </a><!--end of menu-->
                            </li>
                            
                            <!--National Park 5-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/c0a24c04-ce1f-490c-833f-987613930eca.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">National Park</p>
                                </a><!--end of menu-->
                            </li>
                            
                            <!--Small house 6-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/35919456-df89-4024-ad50-5fcb7a472df9.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Small house</p>
                                </a><!--end of menu-->
                            </li>
                            
                            <!--Design 7-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/50861fca-582c-4bcc-89d3-857fb7ca6528.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Design</p>
                                </a><!--end of menu-->
                            </li>
                            
                            <!--North Pole 8-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/8b44f770-7156-4c7b-b4d3-d92549c8652f.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">North Pole</p>
                                </a><!--end of menu-->
                            </li>
                            
                            <!--Cabin 9-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/732edad8-3ae0-49a8-a451-29a8010dcc0c.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Cabin</p>
                                </a><!--end of menu-->
                            </li>
                            
                            <!--Lakeside 10-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/677a041d-7264-4c45-bb72-52bff21eb6e8.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Lakeside</p>
                                </a><!--end of menu-->
                            </li>
                            
                            <!--Play golf 11-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/6b639c8d-cf9b-41fb-91a0-91af9d7677cc.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Play golf</p>
                                </a><!--end of menu-->
                            </li>
                            
                            <!--Great view 12-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/3b1eb541-46d9-4bef-abc4-c37d77e3c21b.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Great view</p>
                                </a><!--end of menu-->
                            </li>
                            
                            <!--Caves 13-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/4221e293-4770-4ea8-a4fa-9972158d4004.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Caves</p>
                                </a><!--end of menu-->
                            </li>
                            
                            <!--Surf 14-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/957f8022-dfd7-426c-99fd-77ed792f6d7a.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Surf</p>
                                </a><!--end of menu-->
                            </li>
                            
                            <!--A-frame house 15-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/1d477273-96d6-4819-9bda-9085f809dad3.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">A-frame house</p>
                                </a><!--end of menu-->
                            </li>
                            
                            <!--Underground house 16-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/d7445031-62c4-46d0-91c3-4f29f9790f7a.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Underground house</p>
                                </a><!--end of menu-->
                            </li>
                            
                            <!--Campground 17-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/ca25c7f3-0d1f-432b-9efa-b9f5dc6d8770.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Campground</p>
                                </a><!--end of menu-->
                            </li>
                            
                            <!--Shared house 18-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/52c8d856-33d0-445a-a040-a162374de100.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Shared house</p>
                                </a><!--end of menu-->
                            </li>
                            
                            <!--Luxe 19-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/c8e2ed05-c666-47b6-99fc-4cb6edcde6b4.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Luxe</p>
                                </a><!--end of menu-->
                            </li>
                            
                            <!--Tropic 20-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/ee9e2a40-ffac-4db9-9080-b351efc3cfc4.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Tropic</p>
                                </a><!--end of menu-->
                            </li>
                            
                            <!--Serving breakfast 21-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/5ed8f7c7-2e1f-43a8-9a39-4edfc81a3325.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Serving breakfast</p>
                                </a><!--end of menu-->
                            </li>
                            
                            <!--Castle 22-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/1b6a8b70-a3b6-48b5-88e1-2243d9172c06.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Castle</p>
                                </a> <!--end of menu--> 
                            </li>
                                                         
                            <!--Famous Cities 23-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/ed8b9e47-609b-44c2-9768-33e6a22eccb2.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Famous Cities</p>
                                </a> <!--end of menu-->  
                            </li>
                                                       
                            <!--Countryside 24-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/6ad4bd95-f086-437d-97e3-14d12155ddfe.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Countryside</p>
                                </a> <!--end of menu-->  
                            </li>
                                                     
                            <!--Villa 25-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/78ba8486-6ba6-4a43-a56d-f556189193da.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Villa</p>
                                </a><!--end of menu-->
                            </li>
                            
                            <!--Farm 26-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/aaa02c2d-9f0d-4c41-878a-68c12ec6c6bd.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Farm</p>
                                </a> <!--end of menu--> 
                            </li>
                                                 
                            <!--Historical house 27-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/33dd714a-7b4a-4654-aaf0-f58ea887a688.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Historical house</p>
                                </a><!--end of menu-->
                            </li>
                            
                            <!--Sea view 28-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/bcd1adc0-5cee-4d7a-85ec-f6730b0f8d0c.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Sea view</p>
                                </a><!--end of menu-->
                            </li>
                                                     
                            <!--Cycladic style house 29-->    
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/e4b12c1b-409b-4cb6-a674-7c1284449f6e.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Cycladic style house</p>
                                </a><!--end of menu-->  
                            </li>                        
                                                 
                            <!--Windmill 30-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/5cdb8451-8f75-4c5f-a17d-33ee228e3db8.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Windmill</p>
                                </a>  <!--end of menu--> 
                            </li>
                                                   
                            <!--Skiing 31-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/c8bba3ed-34c0-464a-8e6e-27574d20e4d2.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Skiing</p>
                                </a><!--end of menu-->
                            </li>
                                                          
                            <!--Chef's Kitchen 32--> 
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/ddd13204-a5ae-4532-898c-2e595b1bb15f.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Chef's Kitchen</p>
                                </a><!--end of menu-->
                            </li>                          
                                                          
                            <!--Containers 33-->   
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/0ff9740e-52a2-4cd5-ae5a-94e1bfb560d6.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Containers</p>
                                </a>  <!--end of menu-->
                            </li>                        
                                                       
                            <!--Camping vehicle 34--> 
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/31c1d523-cc46-45b3-957a-da76c30c85f9.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Camping vehicle</p>
                                </a> <!--end of menu--> 
                            </li>                           
                                                      
                            <!--Shepherd's Tent 35-->  
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/747b326c-cb8f-41cf-a7f9-809ab646e10c.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Shepherd's Tent</p>
                                </a>  <!--end of menu-->
                            </li>                          
                                                
                            <!--Tree house 36-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/4d4a4eba-c7e4-43eb-9ce2-95e1d200d10e.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Tree house</p>
                                </a><!--end of menu-->
                            </li>
                            
                            <!--Vineyard 37-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/60ff02ae-d4a2-4d18-a120-0dd274a95925.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Vineyard</p>
                                </a><!--end of menu-->
                            </li>
                            
                            <!--Kezhan 38-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/8f7740f0-4854-4057-8082-e098649cf689.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Kezhan</p>
                                </a> <!--end of menu-->
                            </li>
                                                         
                            <!--Farm house 39-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/f60700bc-8ab5-424c-912b-6ef17abc479a.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Farm house</p>
                                </a><!--end of menu-->
                            </li>
                            
                            <!--Resort 40-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/251c0635-cc91-4ef7-bb13-1084d5229446.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Resort</p>
                                </a><!--end of menu-->
                            </li>
                            
                            <!--Tower 41-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/d721318f-4752-417d-b4fa-77da3cbc3269.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Tower</p>
                                </a><!--end of menu-->
                            </li>
                            
                            <!--Ryokan 42-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/827c5623-d182-474a-823c-db3894490896.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Ryokan</p>
                                </a><!--end of menu-->
                            </li>
                            
                            <!--Desert 43-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/a6dd2bae-5fd0-4b28-b123-206783b5de1d.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Desert</p>
                                </a> <!--end of menu-->
                            </li>
                                                    
                            <!--Minsu 44-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/48b55f09-f51c-4ff5-b2c6-7f6bd4d1e049.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Minsu</p>
                                </a> <!--end of menu--> 
                            </li>
                                                  
                            <!--Dome house 45-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/89faf9ae-bbbc-4bc4-aecd-cc15bf36cbca.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Dome house</p>
                                </a><!--end of menu-->
                            </li>
                            
                            <!--Yurt tent 46-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/4759a0a7-96a8-4dcd-9490-ed785af6df14.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Yurt tent</p>
                                </a><!--end of menu-->
                            </li>
                            
                            <!--Boat 47-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/687a8682-68b3-4f21-8d71-3c3aef6c1110.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Boat</p>
                                </a><!--end of menu-->
                            </li>
                            
                            <!--The house does not use utility services 48-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/9a2ca4df-ee90-4063-b15d-0de7e4ce210a.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">The house does not use utility services</p>
                                </a> <!--end of menu-->
                            </li>
                                                         
                            <!--Houseboats 49-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/c027ff1a-b89c-4331-ae04-f8dee1cdc287.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Houseboats</p>
                                </a><!--end of menu--> 
                            </li>
                                                        
                            <!--Ski track straight to the accomodation 50-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/757deeaa-c78f-488f-992b-d3b1ecc06fc9.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Ski track straight to the accomodation</p>
                                </a><!--end of menu-->
                            </li>
                            
                            <!--Grand piano 51-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/8eccb972-4bd6-43c5-ac83-27822c0d3dcd.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Grand piano</p>
                                </a><!--end of menu-->
                            </li>
                            
                            <!--Creative space 52-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/8a43b8c6-7eb4-421c-b3a9-1bd9fcb26622.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Creative space</p>
                                </a><!--end of menu-->
                            </li> 
                            
                            <!--Riad 53-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/7ff6e4a1-51b4-4671-bc9a-6f523f196c61.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Riad</p>
                                </a><!--end of menu-->
                            </li>
                            
                            <!--House of cones 54-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/33848f9e-8dd6-4777-b905-ed38342bacb9.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">House of cones</p>
                                </a><!--end of menu-->
                            </li>
                            
                            <!--Dammuso 55-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/c9157d0a-98fe-4516-af81-44022118fbc7.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Dammuso</p>
                                </a><!--end of menu-->
                            </li>
                            
                            <!--Lake 56-->
                            <li>
                                <a href="index.php" class="btn">
                                    <img class=" mx-auto d-block" src="https://a0.muscache.com/pictures/a4634ca6-1407-4864-ab97-6e141967d782.jpg" width="24px" height="24px" alt="Island Picture">
                                    <p class="text-center font-weight-bold small my-1">Lake</p>
                                </a><!--end of menu-->
                            </li>
                            

                        </div><!--end of main-content-->
                        <button class="btn-scroll" id="btn-scroll-left" onclick="scrollHorizontally(1)"><i class="fa fa-chevron-left"></i></button>
                        <button class="btn-scroll" id="btn-scroll-right" onclick="scrollHorizontally(-1)"><i class="fa fa-chevron-right"></i></button>
                    </div><!--end of horizontal-scroll-->
                </div><!--end of container-->

                <!--Filter-->
                <div class="filter">
                    <button type="button" class="btn-filter"><i class="fa fa-sliders mr-2"></i>Filter</button>
                </div><!--end of filter-->

            </div><!--end of d-flex-->

    <div class="container">
        <div class="menuDes">
            <h2 class="text-center pt-3 text-white">Find Your Home All Around The World</h2>
            <div class="country">
                <div class="d-flex mt-3 justify-content-center">
                    <a class="m-4" href="#">

                        <div class="card">
                            <img class="card-img-top" src="<?= PUBLIC_ROOT?>images/flexible.jpg" alt="" style="height:10rem">
                            <div class="card-body">
                                <h6 class="card-title text-dark text-center">I'm flexible</h6>
                                
                            </div>    
                        </div>
                    </a>
                    <a class="m-4" href="#">

                        <div class="card">
                            <img class="card-img-top" src="<?= PUBLIC_ROOT?>images/europe.webp" alt="" style="height:10rem">
                            <div class="card-body">
                                <h6 class="card-title text-dark text-center">Europe</h6>
                                
                            </div>    
                        </div>
                    </a>
                   
                    <a class="m-4" href="#">

                        <div class="card">
                            <img class="card-img-top" src="<?= PUBLIC_ROOT?>images/france.webp" alt="" style="height:10rem">
                            <div class="card-body">
                                <h6 class="card-title text-dark text-center">France</h6>
                                
                            </div>    
                        </div>
                    </a>
                   
                </div>
                <div class="d-flex mt-2 justify-content-center">
                    <a class="m-4" href="#">

                        <div class="card">
                            <img class="card-img-top" src="<?= PUBLIC_ROOT?>images/us.webp" alt="" style="height:10rem">
                            <div class="card-body">
                                <h6 class="card-title text-dark text-center">United States</h6>
                                
                            </div>    
                        </div>
                    </a>
                    <a class="m-4" href="#">

                        <div class="card">
                            <img class="card-img-top" src="<?= PUBLIC_ROOT?>images/canada.webp" alt="" style="height:10rem">
                            <div class="card-body">
                                <h6 class="card-title text-dark text-center">Canada</h6>
                                
                            </div>    
                        </div>
                    </a>
                   
                    <a class="m-4" href="#">

                        <div class="card">
                            <img class="card-img-top" src="<?= PUBLIC_ROOT?>images/east.webp" alt="" style="height:10rem">
                            <div class="card-body">
                                <h6 class="card-title text-dark text-center">East Asia</h6>
                                
                            </div>    
                        </div>
                    </a>
                   
                </div>

            </div>
        </div>
    </div>

    <!-- Calendar -->
    <div class="container">
        <div class="calendar">
            <div class="date pb-5 pt-5">
                <div class="btn-listMonth d-flex flex-wrap justify-content-center">
                    <a class="january mr-3">
                        <div class="card">
                        <i class="fa-solid fa-calendar mt-4 text-center"></i>
                                <h6 class="card-title text-dark text-center mt-2">Junary</h6>
                                <p class="card-title text-center">2022</p>
                        </div>
                    </a>
                    <a class="febuary mr-3">
                        <div class="card">
                        <i class="fa-solid fa-calendar mt-4"></i>
                                <h6 class="card-title text-dark text-center mt-2">febuary</h6>
                                <p class="card-title text-center">2022</p>
                        </div>
                    </a>
                    <a class="march mr-3">
                        <div class="card">
                        <i class="fa-solid fa-calendar mt-4"></i>
                                <h6 class="card-title text-dark text-center mt-2">march</h6>
                                <p class="card-title text-center">2022</p>
                        </div>
                    </a>
                    <a class="april mr-3">
                        <div class="card">
                        <i class="fa-solid fa-calendar mt-4"></i>
                                <h6 class="card-title text-dark text-center mt-2">april</h6>
                                <p class="card-title text-center">2022</p>
                        </div>
                    </a>
                    <a class="may mr-3">
                        <div class="card">
                        <i class="fa-solid fa-calendar mt-4"></i>
                                <h6 class="card-title text-dark text-center mt-2">may</h6>
                                <p class="card-title text-center">2022</p>
                        </div>
                    </a>
                    <a class="june mr-3">
                        <div class="card">
                        <i class="fa-solid fa-calendar mt-4"></i>
                                <h6 class="card-title text-dark text-center mt-2">June</h6>
                                <p class="card-title text-center">2022</p>
                        </div>
                    </a>
                    <a class="july mr-3">
                        <div class="card">
                        <i class="fa-solid fa-calendar mt-4"></i>
                                <h6 class="card-title text-dark text-center mt-2">July</h6>
                                <p class="card-title text-center">2022</p>
                        </div>
                    </a>
                    <a class="august mr-3">
                        <div class="card">
                        <i class="fa-solid fa-calendar mt-4"></i>
                                <h6 class="card-title text-dark text-center mt-2">August</h6>
                                <p class="card-title text-center">2022</p>
                        </div>
                    </a>
                    <a class="september mr-3">
                        <div class="card">
                        <i class="fa-solid fa-calendar mt-4"></i>
                                <h6 class="card-title text-dark text-center mt-2">September</h6>
                                <p class="card-title text-center">2022</p>
                        </div>
                    </a>
                    <a class="october mr-3 mt-4">
                        <div class="card">
                        <i class="fa-solid fa-calendar mt-4"></i>
                                <h6 class="card-title text-dark text-center mt-2">October</h6>
                                <p class="card-title text-center">2022</p>
                        </div>
                    </a>
                    <a class="november mr-3 mt-4">
                        <div class="card">
                        <i class="fa-solid fa-calendar mt-4"></i>
                                <h6 class="card-title text-dark text-center mt-2">November</h6>
                                <p class="card-title text-center">2022</p>
                        </div>
                    </a>
                    <a class="december mr-3 mt-4">
                        <div class="card">
                        <i class="fa-solid fa-calendar mt-4"></i>
                                <h6 class="card-title text-dark text-center mt-2">December</h6>
                                <p class="card-title text-center">2022</p>
                        </div>
                    </a>
                </div>
            </div>
        </div>
    </div>

    <!-- search -->
    <div class="container">
        <div class="menuGuest">
            <div class="d-flex justify-content-between">
                <div class="container">
                    <div class="row mt-5">
                        <div class="col-md-6 offset-md-3 my-4 py-3">
                            <div class="card p-4">
                                <h3 class="text-capitalize my-3"><i class="fa fa-pencil" aria-hidden="true"></i>Search a Room</h3>
                                <form action="<?= ROOT ?>search" method="post" enctype="multipart/form-data">
                                <div class="position-flex">
                                
                                    <div class="form-group">
                                        <label for="home_type">Home Type: </label>
                                        <select class="form-control" style="width: 200px;" id="home_home_type_search" name="home_type">
                                        
                                        <?php foreach($rooms as $room): ?>
                                            <option><?= $room['home_type']?></option>
                                        <?php endforeach; ?>
                                        </select>
                                    </div>

                                    <div class="form-group">
                                        <label for="room_type">Room Type: </label>
                                        <select class="form-control" style="width: 200px;" id="room_type_search" name="room_type">
                                        <?php foreach($rooms as $room): ?>
                                            <option><?= $room['room_type']?></option>
                                        <?php endforeach; ?>
                                        </select>
                                    </div>
                                </div>
                                
                                <div class="position-choose">
                                    <div class="form-group">
                                    <label for="total_occupancy">Enter total occupancy: </label>
                                    <input type="number" name="total_occupancy" id="total_occupancy_search" class="form-control" style="width: 200px;" placeholder="Enter number here...">
                                    </div>

                                    <div class="form-group">
                                    <label for="total_bedrooms">Enter total bedrooms: </label>
                                    <input type="number" name="total_bedrooms" id="total_bedrooms_search" class="form-control" style="width: 200px;" placeholder="Enter number here...">
                                    </div>

                                </div>
                                </div>
                                

                                
                                <?php //CSRF::outputToken(); ?>
                                <button type="submit" class="btn-createPost btn-primary btn-block btn-lg my-3 submit-search"><i class="fa fa-plus-square" aria-hidden="true"></i>Search</button>                                
                            </div>
                            </form>
                        </div>
                    </div>
                </div>
                
            </div>
        </div>
    </div>
    <?php
    ?>