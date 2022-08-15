# Group members
- Cao Minh Nhat - 2059031
- Nguyen Hoang My - 2059029
- Dinh Nguyet Tram - 2059045
- Nguyen Khanh An - 2059001

# Contents
### ***I - Notice on how to use this program***
 - Repeatedly click on those ***Graphic Button*** for each time drawing
 - Stretch to select objects
 - To fill color/outline for your drawing, click on `Outline`/ `Fill Button` before clicking those ***Graphic Button***
  - Scroll mouse to **zoom in/out**
 - To customize your text, click on `Text` at the ***Tab Control***.
 - To select single element for editing again, click on `List` at the ***Tab Control***. It'll show a list of objects. 
 <br>
 If you wish to edit any shapes, just simply press right button on that object. It's your choice to **Edit/Copy/Delete** single element here.
 - There is a ***Customize Quick Access Toolbar*** at the top left corner. User can make a **Undo/Redo/New/Save** for their drawing here.
 
 - There are also some shortcuts for these buttons
   + `New`: Ctrl + N (New Program) 
   + `Save`: Ctrl + S
   + `Undo`: Ctrl + Z
   + `Redo`: Ctrl + Y
   

 
### ***II - Requirements Check*** 
#### <mark>What have we done and ways to do?</mark>
##### <u>Core Requirements (Done all) </u>
*  Dynamically load all graphic objects that can be drawn from external `DLL files`.
    > 1. Create a project named ***IContract*** includes *IShape* and *IShapeDrawer* as Class Library.
    > 2. Create projects **MyLine**, **MyRectangle**, **MyEllipse** and **MyLineDrawer**, **MyRectangleDrawer**, **MyEllipseDrawer**  as Class Library.
    > 3. Add ***IContract*** as references to the all previous projects.
    > This enables programmer to create as many shapes as they want since it creates a dependency between the projects in the build system.
    > 4. Scan for `*.dll` files then correspondingly add to list of drawn-able shapes.

- The user can choose which object to draw
<br>
    >  1. Loop through each object in Prototype list to create its corresponding button and assigning its `Tag` at the same time.
<br>
    >  2. Cloning the `SelectedAction`, which flexibly switches cases based on its `button.Tag`

- The list of drawn objects can be saved and loaded again for continuing later
    > An interface has `Convert`(**IShape** to **String**) and `ConvertBack`( **String** to **IShape**) function. Convert and saved as .`txt` file and vice versa.
    
- The user can see the preview of the object they want to draw

- The user can finish the drawing preview and their change becomes permanent with previously drawn objects
=

- Save and load all drawn objects as an image in **bmp/png/jpg** format (rasterization).


##### <u>Improvements </u>
- Adding image to the canvas
    > 1. `Using Microsoft.Win32` for supporting `openFileDialog`.
    > 2. Check file exists and choose image
    > 3. add to **ImageSource**

- Undo
    > 1.  Add the last element in `Object` List to `ObjectsForRedo` List
    > 2. Remove last element in `Object` List
    > 3. Clear canvas and redraw all object in the `Object` List

- Redo
    > 1. Add the last element in `ObjectForRedo` List back to `Object` List
    > 2. Remove last element in `ObjectForRedo` List
    >3. Clear canvas and redraw all object in the `Object` List

- Adding text to the list of drawn-able objects
    > Create a new separated window to enable users to add text directly on canvas. 

- Zooming
    > Add a class `ZoomBorder` covers Panels. Enabling mouse scroll for panning.

- Allow the user to change the color, pen width, stroke type (dash, dot, dash dot dot...
    > Assigning tag for each kind of button. Then generating style for each time clicking.

- Select a single element for editing again
    > Binding all drawn object to a list that shown in a listview. Users press right mouse on the listview to select single element

- Reduce flickering when drawing preview by using buffer to redraw all the canvas

- Fill color by boundaries

- Cut / Copy / Paste

#### <mark>What haven't we done </mark>
- Adding layers support
- Rotate the image

### III - What should be taken into account for a bonus?
- Be able to resize the canvas, which limits drawing area
- Be able to **Drag/Drop** with mouse scrolling 
- **Drag/Drop** using `VisualTreeHelper.HitTest` Method, which allow user to retrieve all of the visuals under the point or geometry, not just the topmost one.
- Applied **Command Design Pattern** to this program


### IV - Expected Grade for each member
* Cao Minh Nhat: 9.75
* Dinh Nguyet Tram: 9
* Nguyen Khanh An: 9
* Nguyen Hoang My: 9


### V - Video Demo
https://youtu.be/_rbAePwLgc8
## Thanks for reading <3






    






 
 
 
 


