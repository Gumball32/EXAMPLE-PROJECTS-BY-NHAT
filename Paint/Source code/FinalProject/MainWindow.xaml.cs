using Fluent;
using Microsoft.Win32;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using IContract;
using MyEllipse01;
using MyRectangle01;
using MyLine01;

namespace FinalProject
{
    public enum StyleLines
    {
        Soild,
        Dash,
        Dot,
        DashDot,
        DashDotDot
    };
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : RibbonWindow
    {
        bool isPreviewing = false;
        double lastX;
        double lastY;
        BindingList<object> objects = new BindingList<object>();
        BindingList<object> objectsForRedo = new BindingList<object>();
        BindingList<object> listForCopyAndPaste = new BindingList<object>();
        Dictionary<string, IShapeDrawer> actions = new Dictionary<string, IShapeDrawer>();
        Dictionary<string, IShape> prototypes = new Dictionary<string, IShape>();
        Color fillColor;
        Color outlineColor;
        public StyleLines styleLine = StyleLines.Soild;
        int penWidth = 1;
        IShape prototype = null;
        ShapeToStringDataConverter converter;
        string tag;
        Color textColor = Colors.Black;
        FontFamily fontFamilyText;
        FontWeight FontWeight;
        FontStyle fontStyle;
        TextDecorationCollection decorations;
        int fontSizeText = 8;

        string selectedAction = "None"; // 0: Line, 1: Rectangle
        bool isDone = true;
        BindingList<System.Windows.Controls.Button> listButton = new BindingList<System.Windows.Controls.Button>();

        public MainWindow()
        {
            InitializeComponent();

            converter = new ShapeToStringDataConverter();
            decorations = new TextDecorationCollection();

            //var action1 = new MyLineDrawer();
            //var action2 = new MyRectangleDrawer();
            //var action3 = new MyEllipseDrawer();
            var action4 = new PolyLineDrawer();
            var action5 = new MyTextDrawer();
            //actions.Add(action1.MagicWord, action1);
            //actions.Add(action2.MagicWord, action2);
            //actions.Add(action3.MagicWord, action3);
            actions.Add(action4.MagicWord, action4);
            actions.Add(action5.MagicWord, action5);

            //var prototype1 = new MyLine();
            //var prototype2 = new MyRectangle();
            //var prototype3 = new MyEllipse();
            var prototype4 = new MyPolyLine();
            var prototype5 = new MyText();
            //prototypes.Add(prototype1.MagicWord, prototype1);
            //prototypes.Add(prototype2.MagicWord, prototype2);
            //prototypes.Add(prototype3.MagicWord, prototype3);
            prototypes.Add(prototype4.MagicWord, prototype4);
            prototypes.Add(prototype5.MagicWord, prototype5);

            ListViewOfShapes.ItemsSource = objects;

        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            string magicWord = (sender as System.Windows.Controls.Button).Tag as string;
            selectedAction = magicWord;
            isDone = false;
        }

        private void RadioButton_Checked(object sender, RoutedEventArgs e)
        {
            System.Windows.Controls.Button button;
            button = sender as System.Windows.Controls.Button;
            tag = "None";
            tag = button.Tag as string;

            switch (tag)
            {
                case "MyLine":
                    selectedAction = "MyLine";
                    isDone = false;
                    break;
                case "MyRectangle":
                    selectedAction = "MyRectangle";
                    isDone = false;
                    break;
                case "MyEllipse":
                    selectedAction = "MyEllipse";
                    isDone = false;
                    break;
                case "MyPencil":
                    selectedAction = "MyPolyLine";
                    isDone = false;
                    break;
                case "MyEraser":
                    selectedAction = "MyPolyLine";
                    isDone = false;
                    break;
                case "MyText":
                    selectedAction = "MyText";
                    isDone = false;
                    break;
                case "None":
                    break;
            }
        }

        private void canvas_MouseDown(object sender, MouseButtonEventArgs e)
        {
            if (e.RightButton == MouseButtonState.Pressed) return;

            var position = e.GetPosition(canvas);
            lastX = position.X;
            lastY = position.Y;

            if (!isDone)
            {
                isPreviewing = true;
                prototype = prototypes[selectedAction].Clone() as IShape;
                if (prototype is MyRectangle)
                {
                    ((MyRectangle)prototype).Fill = fillColor;
                    ((MyRectangle)prototype).StrokeColor = outlineColor;
                    ((MyRectangle)prototype).styleLine = (IContract.StyleLines)styleLine;
                    ((MyRectangle)prototype).PenWidth = penWidth;
                }
                else if (prototype is MyEllipse)
                {
                    ((MyEllipse)prototype).Fill = fillColor;
                    ((MyEllipse)prototype).StrokeColor = outlineColor;
                    ((MyEllipse)prototype).styleLine = (IContract.StyleLines)styleLine;
                    ((MyEllipse)prototype).PenWidth = penWidth;
                }
                else if (prototype is MyLine)
                {
                    ((MyLine)prototype).Fill = fillColor;
                    ((MyLine)prototype).StrokeColor = outlineColor;
                    ((MyLine)prototype).styleLine = (IContract.StyleLines)styleLine;
                    ((MyLine)prototype).PenWidth = penWidth;
                }
                else if (prototype is MyPolyLine)
                {
                    if (tag == "MyPencil")
                    {
                        ((MyPolyLine)prototype).Fill = Colors.Transparent;
                        ((MyPolyLine)prototype).StrokeColor = outlineColor;
                    }
                    else if (tag == "MyEraser")
                    {
                        ((MyPolyLine)prototype).Fill = Colors.Transparent;
                        ((MyPolyLine)prototype).StrokeColor = Colors.White;
                    }
                    ((MyPolyLine)prototype).styleLine = styleLine;
                    ((MyPolyLine)prototype).PenWidth = penWidth;
                    ((MyPolyLine)prototype).polygonPoints = new PointCollection();
                }
                else if (prototype is MyText)
                {
                    ((MyText)prototype).TextColor = textColor;
                    ((MyText)prototype).RectFontFamily = fontFamilyText;
                    ((MyText)prototype).RectFontSize = fontSizeText;
                    ((MyText)prototype).FontStyle = fontStyle;
                    ((MyText)prototype).RectFontWeight = FontWeight;
                    ((MyText)prototype).FontDecorations = decorations;
                }

                if (prototype is null) return;
                prototype.HandleMouseDown(lastX, lastY);
            }
        }

        private void canvas_MouseUp(object sender, MouseButtonEventArgs e)
        {
            if (e.RightButton == MouseButtonState.Pressed) return;
            isPreviewing = false;

            var position = e.GetPosition(canvas);
            var currentX = position.X;
            var currentY = position.Y;

            if (!isDone)
            {
                if (currentX > 0 && currentY > 0)
                {
                    if (prototype is null) return;
                    prototype.HandleMouseUp(currentX, currentY);

                    if (prototype is MyText)
                    {
                        //MyText temp = prototype as MyText;
                        Layer layer = new Layer(prototype);
                        //Canvas.SetLeft(layer, temp.X);
                        //Canvas.SetTop(layer, temp.Y);

                        layer.Create();
                        grid.Children.Add(layer);
                        decorations = new TextDecorationCollection();
                        FontWeight = FontWeights.Normal;
                        fontStyle = FontStyles.Normal;
                    }

                    else
                    {
                        // UI level
                        canvas.Children.Add(actions[selectedAction].Draw(prototype) as UIElement);
                    }

                    // Business level
                    if (prototype is MyText)
                    {
                        return;
                    }
                    objects.Add(prototype);

                }

                isDone = true;
            }


        }

        private void canvas_MouseMove(object sender, MouseEventArgs e)
        {
            if (isPreviewing)
            {
                // Clear the canvas
                canvas.Children.Clear();

                // Draw all the previous drawn objects
                foreach (IShape shape in objects)
                {
                    var action = actions[shape.MagicWord];
                    if (action is MyText)
                        continue;
                    canvas.Children.Add(action.Draw(shape) as UIElement);
                }

                // Add new shape here
                var position = e.GetPosition(canvas);
                var currentX = position.X;
                var currentY = position.Y;
                if (prototype is null) return;
                prototype.HandleMouseUp(currentX, currentY);
                if (!(prototype is MyText))
                    canvas.Children.Add(actions[selectedAction].Draw(prototype) as UIElement);
            }

        }

        Point _InitPos;
        Point origMouseDownPoint;
        bool _LeftMouseHeld = false;
        bool isDrag = false;
        //IShape prototype2 = null;
        private List<object> _ResultsList = new List<object>();

        private void grid_MouseDown(object sender, MouseButtonEventArgs e)
        {

            if (_ResultsList.Count > 0 && !isDrag)
            {
                origMouseDownPoint = new Point();
                Point curMouseDownPoint = e.GetPosition(grid);

                origMouseDownPoint.Y = curMouseDownPoint.X;
                origMouseDownPoint.X = curMouseDownPoint.Y;

                isDrag = true;
            }

            else if (isDone)
            {
                var Limit = (FrameworkElement)sender;
                foreach (UIElement shape in _ResultsList)
                {
                    if (shape is Rectangle)
                    {
                        //shape = shape as Rectangle;
                    }
                }
                _ResultsList.Clear();

                // Get position relative to the grid content
                _InitPos = e.GetPosition(Limit);

                // With this, selection is more fluid but you can draw beyond limit of your container
                Limit.CaptureMouse();

                // Initialization of the SelectBox
                Canvas.SetLeft(SelectBox, _InitPos.X);
                Canvas.SetTop(SelectBox, _InitPos.X);
                SelectBox.Visibility = Visibility.Visible;

                // Set left mouse button state because mosemove will continue to draw if not filtered
                _LeftMouseHeld = true;

            }
        }

        private void grid_MouseMove(object sender, MouseEventArgs e)
        {

            if (isDrag)
            {
                Point curMouseDownPoint = e.GetPosition(grid);
                var dragDelta = curMouseDownPoint - origMouseDownPoint;
                origMouseDownPoint = curMouseDownPoint;

                canvas.Children.Clear();

                foreach (IShape shape in objects)
                {
                    var action = actions[shape.MagicWord];
                    canvas.Children.Add(action.Draw(shape) as UIElement);
                }

                foreach (UIElement selectedShape in _ResultsList)
                {
                    Rectangle rec = null;
                    Ellipse ell = null;
                    Line li = null;
                    if (selectedShape is Rectangle)
                    {
                        rec = selectedShape as Rectangle;

                        foreach (IShape shape in objects)
                        {
                            //if (dragDelta.X < 0 || dragDelta.Y < 0)
                            //{
                            //    return;
                            //}

                            if (shape is MyRectangle)
                            {
                                Rectangle temp = actions["MyRectangle"].Draw(shape) as Rectangle;
                                if (temp.Width == rec.Width && temp.Height == rec.Height)
                                {
                                    MyRectangle temmppp = shape as MyRectangle;
                                    temmppp.X += dragDelta.X;
                                    temmppp.Y += dragDelta.Y;
                                    canvas.Children.Add(actions["MyRectangle"].Draw(temmppp) as Rectangle);
                                }
                            }
                        }
                    }
                    if (selectedShape is Ellipse)
                    {
                        ell = selectedShape as Ellipse;

                        foreach (IShape shape in objects)
                        {
                            //if (dragDelta.X < 0 || dragDelta.Y < 0)
                            //{
                            //    return;
                            //}

                            if (shape is MyEllipse)
                            {
                                Ellipse temp = actions["MyEllipse"].Draw(shape) as Ellipse;
                                if (temp.Width == ell.Width && temp.Height == ell.Height)
                                {
                                    MyEllipse temmppp = shape as MyEllipse;
                                    temmppp.X += dragDelta.X;
                                    temmppp.Y += dragDelta.Y;
                                    if (temmppp.X < 0 || temmppp.Y < 0)
                                    {
                                        return;
                                    }
                                    canvas.Children.Add(actions["MyEllipse"].Draw(temmppp) as Ellipse);
                                }
                            }
                        }
                    }
                    if (selectedShape is Line)
                    {
                        li = selectedShape as Line;

                        foreach (IShape shape in objects)
                        {
                            //if (dragDelta.X < 0 || dragDelta.Y < 0)
                            //{
                            //    return;
                            //}

                            if (shape is MyLine)
                            {
                                Line temp = actions["MyLine"].Draw(shape) as Line;
                                if ((temp.X2 - temp.X1) == (li.X2 - li.X1) && (temp.Y2 - temp.Y1) == (li.Y2 - li.Y1))
                                {
                                    MyLine temmppp = shape as MyLine;
                                    temmppp.X1 += dragDelta.X;
                                    temmppp.Y1 += dragDelta.Y;
                                    temmppp.X2 += dragDelta.X;
                                    temmppp.Y2 += dragDelta.Y;

                                    //if (temmppp.X1 < 0 || temmppp.X2 < 0 || temmppp.Y1 < 0 || temmppp.Y2 < 0)
                                    //{
                                    //    return;
                                    //}
                                    canvas.Children.Add(actions["MyLine"].Draw(temmppp) as Line);
                                }
                            }
                        }
                    }

                }
            }

            else if (isDone)
            {
                Grid Limit = (Grid)sender;

                // to calculate only if left mouse button is held;
                if (_LeftMouseHeld)
                {
                    // Get current position relative to the grid content
                    Point currentPos = e.GetPosition(Limit);

                    /*
                        Parameters can't be negative then we will invert base according to mouse value
                    */

                    // X coordinates
                    if (currentPos.X > _InitPos.X)
                    {
                        Canvas.SetLeft(SelectBox, _InitPos.X);
                        SelectBox.Width = currentPos.X - _InitPos.X;
                    }
                    else
                    {
                        Canvas.SetLeft(SelectBox, currentPos.X);
                        SelectBox.Width = _InitPos.X - currentPos.X;
                    }

                    // Y coordinates
                    if (currentPos.Y > _InitPos.Y)
                    {
                        Canvas.SetTop(SelectBox, _InitPos.Y);
                        SelectBox.Height = currentPos.Y - _InitPos.Y;
                    }
                    else
                    {
                        Canvas.SetTop(SelectBox, currentPos.Y);
                        SelectBox.Height = _InitPos.Y - currentPos.Y;
                    }

                    /*
                     * With a rectangle geometry you could add every shapes INSIDE the rectangle
                     * With a point geometry you must go over the shape to select it.
                     */
                    VisualTreeHelper.HitTest(canvas,
                        new HitTestFilterCallback(Filter),
                        new HitTestResultCallback(MyHitTestResult),
                        /*new PointHitTestParameters(currentPos)*/
                        new GeometryHitTestParameters(new RectangleGeometry(new Rect(_InitPos, currentPos)))
                        );
                }
            }
        }

        private void grid_MouseUp(object sender, MouseButtonEventArgs e)
        {
            //Point curMouseDownPoint = e.GetPosition(this);

            if (isDrag)
            {
                _ResultsList.Clear();
                isDrag = false;
            }

            else if (isDone)
            {
                var Limit = (FrameworkElement)sender;

                // Set left mouse button state to released
                _LeftMouseHeld = false;

                Limit.ReleaseMouseCapture();

                // Hide all the listbox (if you forget to specify width and height you will have remanent coordinates
                SelectBox.Visibility = Visibility.Collapsed;
                SelectBox.Width = 0;
                SelectBox.Height = 0;

            }
        }

        private HitTestFilterBehavior Filter(DependencyObject potentialHitTestTarget)
        {
            // Type of return is very important
            if (potentialHitTestTarget is Rectangle || potentialHitTestTarget is Line || potentialHitTestTarget is Ellipse)
            {
                if (!_ResultsList.Contains(potentialHitTestTarget))
                {
                    if (potentialHitTestTarget is Rectangle)
                    {
                        _ResultsList.Add(potentialHitTestTarget);
                        ((Rectangle)potentialHitTestTarget).StrokeThickness = 10;
                    }

                    if (potentialHitTestTarget is Ellipse)
                    {
                        _ResultsList.Add(potentialHitTestTarget);
                        ((Ellipse)potentialHitTestTarget).StrokeThickness = 10;
                    }

                    if (potentialHitTestTarget is Line)
                    {
                        _ResultsList.Add(potentialHitTestTarget);
                        ((Line)potentialHitTestTarget).StrokeThickness = 10;
                    }
                }
                return HitTestFilterBehavior.ContinueSkipChildren;
            }

            return HitTestFilterBehavior.Continue;

        }


        // Return the result of the hit test to the callback.
        public HitTestResultBehavior MyHitTestResult(HitTestResult result)
        {
            // Set the behavior to return visuals at all z-order levels.
            return HitTestResultBehavior.Continue;
        }

        private void fillColorPicker_SelectedColorChanged(object sender, RoutedEventArgs e)
        {
            Fluent.ColorGallery colorGallery = sender as Fluent.ColorGallery;
            fillColor = (Color)colorGallery.SelectedColor;
        }

        private void StyleChecked(object sender, RoutedEventArgs e)
        {
            System.Windows.Controls.RadioButton radioButton = sender as System.Windows.Controls.RadioButton;
            int tag = -1;
            bool check = Int32.TryParse(radioButton.Tag as string, out tag);

            if (check)
            {
                styleLine = (StyleLines)tag;
            }
        }

        private void WidthChecked(object sender, RoutedEventArgs e)
        {
            System.Windows.Controls.RadioButton radioButton = sender as System.Windows.Controls.RadioButton;
            int tag = -1;
            bool check = Int32.TryParse(radioButton.Tag as string, out tag);

            if (check)
            {
                penWidth = tag;
            }
        }

        private void oulineColorPicker_SelectedColorChanged(object sender, RoutedEventArgs e)
        {
            Fluent.ColorGallery colorGallery = sender as Fluent.ColorGallery;
            outlineColor = (Color)colorGallery.SelectedColor;
        }

        #region Menu

        private const string GifFilter = "Gif image (*.gif)|*.gif";
        private const string JpegFilter = "Jpeg image (*.jpeg)|*.jpeg";
        private const string BitmapFilter = "Bitmap file (*.bmp)|*.bmp";
        private const string PngFilter = "Png image (*.png)|*.png";

        private void MenuNew_Click(object sender, RoutedEventArgs e)
        {
            

            MainWindow win2 = new MainWindow();
            win2.Show();

        }

        private void MenuOpen_Click(object sender, RoutedEventArgs e)
        {
            var openDialog = new OpenFileDialog();
            if (openDialog.ShowDialog(this).GetValueOrDefault() && openDialog.CheckFileExists)
            {
                var brush = new ImageBrush()
                {
                    ImageSource = new BitmapImage(new Uri(openDialog.FileName, UriKind.Relative))
                };
                canvas.Background = brush;
                openDialog.Reset();
            }

            //string ImagePath = FileSystem.OpenFile();
            //if (ImagePath == "") return;
            //SetImage(ImagePath);
        }

        private void MenuSave_Click(object sender, RoutedEventArgs e)
        {
            var saveDialog = new SaveFileDialog
            {
                Filter = $"{JpegFilter}|{PngFilter}|{GifFilter}|{BitmapFilter}",
                DefaultExt = "jpeg",
                AddExtension = true
            };
            if (saveDialog.ShowDialog(this).GetValueOrDefault(false))
            {
                SaveCanvas(saveDialog);
            }
        }

        private void MenuExit_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }

        private void MenuNew_CanExecute(object sender, CanExecuteRoutedEventArgs e)
        {
            e.CanExecute = true;
        }

        #region Save Canvas

        private void MenuPNGSave_Click(object sender, RoutedEventArgs e)
        {
            var saveDialog = new SaveFileDialog
            {
                Filter = $"{PngFilter}",
                DefaultExt = "png",
                AddExtension = true
            };
            if (saveDialog.ShowDialog(this).GetValueOrDefault(false))
            {
                SaveCanvas(saveDialog);
            }
        }

        private void MenuJPEGSave_Click(object sender, RoutedEventArgs e)
        {
            var saveDialog = new SaveFileDialog
            {
                Filter = $"{JpegFilter}",
                DefaultExt = "jpeg",
                AddExtension = true
            };
            if (saveDialog.ShowDialog(this).GetValueOrDefault(false))
            {
                SaveCanvas(saveDialog);
            }
        }

        private void MenuGifSave_Click(object sender, RoutedEventArgs e)
        {
            var saveDialog = new SaveFileDialog
            {
                Filter = $"{GifFilter}",
                DefaultExt = "gif",
                AddExtension = true
            };
            if (saveDialog.ShowDialog(this).GetValueOrDefault(false))
            {
                SaveCanvas(saveDialog);
            }
        }

        private void MenuBitmapSave_Click(object sender, RoutedEventArgs e)
        {
            var saveDialog = new SaveFileDialog
            {
                Filter = $"{BitmapFilter}",
                DefaultExt = "bmp",
                AddExtension = true
            };
            if (saveDialog.ShowDialog(this).GetValueOrDefault(false))
            {
                SaveCanvas(saveDialog);
            }
        }

        private void SaveCanvas(SaveFileDialog saveDialog)
        {
            if (saveDialog == null) throw new ArgumentNullException(nameof(saveDialog));
            var rtb = new RenderTargetBitmap((int)canvas.RenderSize.Width,
                (int)canvas.RenderSize.Height, 96d, 96d, PixelFormats.Default);
            rtb.Render(canvas);
            var encoder = (dynamic)null;
            string temp = saveDialog.SafeFileName.Split('.')[1];

            switch (temp)
            {
                case "png":
                    encoder = (BitmapEncoder)new PngBitmapEncoder();
                    break;
                case "jpeg":
                    encoder = new JpegBitmapEncoder();
                    break;
                case "gif":
                    encoder = new GifBitmapEncoder();
                    break;
                case "bmp":
                    encoder = new BmpBitmapEncoder();
                    break;
            };
            encoder?.Frames.Add(BitmapFrame.Create(rtb));
            var path = saveDialog.FileName;
            var fs = (dynamic)null;
            try
            {
                fs = File.OpenWrite(path);
            }
            catch (Exception e)
            {
                MessageBox.Show("Error!");
            }
            encoder?.Save(fs);
        }

        #endregion

        #endregion

        #region Copy, Paste and Cut

        private void MenuCopy_Click(object sender, ExecutedRoutedEventArgs e)
        {
            CopyToClipboard();

            //if (objects.Count < 1) return;

            //if (listForCopyAndPaste.Count > 0) listForCopyAndPaste.Clear();

            //for (int i = 0; i < objects.Count; i++)
            //{
            //    IShape temp = new IShape();
            //    temp = objects[i] as UIElement;
            //    listForCopyAndPaste.Add(objects[i]);
            //}
        }

        private void CopyToClipboard()
        {
            var rtb = new RenderTargetBitmap((int)canvas.RenderSize.Width,
                (int)canvas.RenderSize.Height, 96d, 96d, PixelFormats.Default);
            rtb.Render(canvas);
            Clipboard.SetImage(rtb);
        }

        private void MenuCut_Click(object sender, ExecutedRoutedEventArgs e)
        {
            CopyToClipboard();
            canvas.Background = null;
            canvas.Children.Clear();
        }

        private void MenuPaste_Click(object sender, ExecutedRoutedEventArgs e)
        {
            canvas.Children.Clear();
            canvas.Background = new ImageBrush
            {
                ImageSource = Clipboard.GetImage()
            };

            //if (objects.Count < 1 || listForCopyAndPaste.Count < 1) return;

            //foreach (IShape shape in listForCopyAndPaste)
            //{
            //    objects.Add(shape);
            //}

            //// Clear the canvas
            //canvas.Children.Clear();

            //// Draw all the previous drawn objects
            //foreach (IShape shape in objects)
            //{
            //    var action = actions[shape.MagicWord];
            //    if (action is MyText)
            //        continue;
            //    canvas.Children.Add(action.Draw(shape) as UIElement);
            //}
            
            //listForCopyAndPaste.Clear();
        }

        #endregion

        private void btnReSize_Click(object sender, RoutedEventArgs e)
        {
            var ResizeWindow = new ResizeWindow(canvas.Width, canvas.Height);

            ResizeWindow.Owner = Application.Current.MainWindow;
            ResizeWindow.SizeUpdate += (s, args) => {
                grid.Width = args.Width;
                grid.Height = args.Height;
                canvas.Width = args.Width;
                canvas.Height = args.Height;
                backGround.Width = args.Width;
                backGround.Height = args.Height;
            };
            ResizeWindow.ShowInTaskbar = false;
            ResizeWindow.Show();
            ResizeWindow.Activate();
            ResizeWindow.Topmost = true;
            ResizeWindow.Topmost = false;
            ResizeWindow.Focus();
        }

        private void btnUndo_Click(object sender, RoutedEventArgs e)
        {
            if (objects.Count < 1)
            {
                return;
            }

            IShape temp = objects[objects.Count() - 1] as IShape;
            objectsForRedo.Add(temp);
            int pos = objects.Count();
            objects.RemoveAt(pos - 1);

            // Clear the canvas
            canvas.Children.Clear();

            // Draw all the previous drawn objects
            foreach (IShape shape in objects)
            {
                var action = actions[shape.MagicWord];
                if (action is MyText)
                    continue;
                canvas.Children.Add(action.Draw(shape) as UIElement);
            }

        }

        private void btnRedo_Click(object sender, RoutedEventArgs e)
        {
            if (objectsForRedo.Count < 1)
            {
                return;
            }

            IShape temp = objectsForRedo[objectsForRedo.Count() - 1] as IShape;
            objects.Add(temp);
            int pos = objectsForRedo.Count();
            objectsForRedo.RemoveAt(pos - 1);

            // Clear the canvas
            canvas.Children.Clear();

            // Draw all the previous drawn objects
            foreach (IShape shape in objects)
            {
                var action = actions[shape.MagicWord];
                if (action is MyText)
                    continue;
                canvas.Children.Add(action.Draw(shape) as UIElement);
            }
        }

        private void fontFamilySelected(object sender, RoutedEventArgs e)
        {
            System.Windows.Controls.ComboBox comboBox = sender as System.Windows.Controls.ComboBox;
            if (comboBox != null)
            {
                FontFamily font = comboBox.SelectedItem as FontFamily;
                fontFamilyText = font;
            }
        }

        private void fontSizeSelected(object sender, RoutedEventArgs e)
        {
            System.Windows.Controls.TextBox text = sender as System.Windows.Controls.TextBox;
            if (text == null)
            {
                return;
            }

            int size;
            bool check = int.TryParse(fontSize.Text.ToString(), out size);

            if (check)
            {
                fontSizeText = size;
            }
        }

        private void Text_ColorChanged(object sender, RoutedEventArgs e)
        {
            Fluent.ColorGallery colorGallery = sender as Fluent.ColorGallery;
            textColor = (Color)colorGallery.SelectedColor;
        }

        private void saveData_Click(object sender, RoutedEventArgs e)
        {
            SaveFileDialog saveDialog = new SaveFileDialog();

            saveDialog.Filter = "txt files (*.txt)|*.txt|All files (*.*)|*.*";
            saveDialog.FilterIndex = 2;
            saveDialog.RestoreDirectory = true;
            bool check = (bool)saveDialog.ShowDialog();

            if (!check)
            {
                return;
            }

            string filename = saveDialog.FileName;
            string temp = "";

            foreach (IShape shape in objects)
            {
                string data = converter.Convert(shape);
                temp += data;
            }

            File.WriteAllText(filename, temp);
        }

        private void importData_Click(object sender, RoutedEventArgs e)
        {
            OpenFileDialog openFileDialog = new OpenFileDialog();

            openFileDialog.InitialDirectory = "c:\\";
            openFileDialog.Filter = "txt files (*.txt)|*.txt|All files (*.*)|*.*";
            openFileDialog.FilterIndex = 2;
            openFileDialog.RestoreDirectory = true;
            bool check = (bool)openFileDialog.ShowDialog();

            if (!check)
            {
                return;
            }

            string filename = openFileDialog.FileName;
            IShape temp;
            string[] array;
            string readAll = File.ReadAllText(filename);
            string[] arrayOfShapes = readAll.Split(new string[] { "-" }, StringSplitOptions.None);

            for (int i = 0; i < arrayOfShapes.Length - 1; i++)
            {
                array = arrayOfShapes[i].Split(new[] { "\r\n", "\r", "\n" }, StringSplitOptions.RemoveEmptyEntries);
                if (array.Length == 0) continue;
                temp = converter.ConvertBack(array);
                if (temp == null) continue;
                objects.Add(temp);
            }

            // Clear the canvas
            canvas.Children.Clear();

            // Draw all the previous drawn objects
            foreach (IShape shape in objects)
            {
                var action = actions[shape.MagicWord];
                if (action is MyText)
                    continue;
                canvas.Children.Add(action.Draw(shape) as UIElement);
            }
        }

        //private void textStyle(object sender, RoutedEventArgs e)
        //{
        //    ToggleButton toggle = sender as ToggleButton;
        //    if (toggle == null)
        //    {
        //        return;
        //    }
        //    string name = toggle.Name;

        //    switch (name)
        //    {
        //        case "textBold":
        //            FontWeight = FontWeights.Bold;
        //            break;
        //        case "textItalic":
        //            fontStyle = FontStyles.Italic;
        //            break;
        //        case "textUnderline":
        //            decorations.Add(TextDecorations.Underline);
        //            break;
        //        case "textStrikethrough":
        //            decorations.Add(TextDecorations.Strikethrough);
        //            break;
        //    }
        //}

        private void RibbonWindow_Loaded(object sender, RoutedEventArgs e)
        {
            // Quét toàn bộ thư mục để biết các khả năng
            // Lấy thư mục hiện tại
            var exePath = Assembly.GetExecutingAssembly().Location;
            var folder = System.IO.Path.GetDirectoryName(exePath);
            var fis = new DirectoryInfo(folder).GetFiles("*.dll");

            foreach (var fi in fis)
            {
                Debug.WriteLine(fi.FullName);
                var domain = AppDomain.CurrentDomain;
                var assembly = domain.Load(AssemblyName.GetAssemblyName(fi.FullName));
                var types = assembly.GetTypes();

                foreach (var type in types)
                {
                    if (type.IsClass)
                    {
                        if (typeof(IShape).IsAssignableFrom(type))
                        {
                            prototypes.Add(type.Name, Activator.CreateInstance(type) as IShape);
                        }
                        else if (typeof(IShapeDrawer).IsAssignableFrom(type))
                        {
                            string name = "";
                            if (type.Name == "MyRectangleDrawer") name = "MyRectangle";
                            else if (type.Name == "MyEllipseDrawer") name = "MyEllipse";
                            else if (type.Name == "MyLineDrawer") name = "MyLine";
                            actions.Add(name, Activator.CreateInstance(type) as IShapeDrawer);
                        }
                    }
                }
            }

            foreach (var type in prototypes)
            {
                Debug.WriteLine(type.Key);
            }

            foreach (var type in actions)
            {
                Debug.WriteLine(type.Key);
            }

            foreach (var type in prototypes)
            {
                var button = new System.Windows.Controls.Button()
                {
                    Tag = type.Value.MagicWord
                };

                string sou = $@"Images\{type.Value.MagicWord}.png";
                BitmapImage bit = new BitmapImage(new Uri(sou, UriKind.Relative));
                Image image = new Image { Source = bit };
                image.Width = 35;
                image.Height = 30;

                TextBlock tb = new TextBlock()
                {
                    Text = type.Value.DisplayName,
                    VerticalAlignment = VerticalAlignment.Center,
                    HorizontalAlignment = HorizontalAlignment.Center,
                };

                var panel = new StackPanel()
                {
                    Width = 35,
                    Height = 50,
                    VerticalAlignment = VerticalAlignment.Top
                };
                panel.Orientation = Orientation.Vertical;

                //panel.Children.Add(button);
                panel.Children.Add(image);
                panel.Children.Add(tb);

                button.Content = panel;

                actionsPanel.Children.Add(button);
                button.Click += Button_Click;
                listButton.Add(button);
            }
            selectedAction = "None";
        }

        private void editMenu_Click(object sender, RoutedEventArgs e)
        {
            IShape item = null;

            if (ListViewOfShapes.SelectedItem is MyEllipse) item = ListViewOfShapes.SelectedItem as MyEllipse;
            else if (ListViewOfShapes.SelectedItem is MyRectangle) item = ListViewOfShapes.SelectedItem as MyRectangle;
            else if (ListViewOfShapes.SelectedItem is MyLine) item = ListViewOfShapes.SelectedItem as MyLine;

            if (item == null) return;

            if (item is MyRectangle || item is MyEllipse)
            {
                var screen = new EditShape(item);

                if (screen.ShowDialog() == true)
                {
                    if (item is MyRectangle)
                    {
                        ((MyRectangle)item).X = ((MyRectangle)screen.EditedShape).X;
                        ((MyRectangle)item).Y = ((MyRectangle)screen.EditedShape).Y;
                        ((MyRectangle)item).Width = ((MyRectangle)screen.EditedShape).Width;
                        ((MyRectangle)item).Height = ((MyRectangle)screen.EditedShape).Height;
                        ((MyRectangle)item).Fill = ((MyRectangle)screen.EditedShape).Fill;
                        ((MyRectangle)item).StrokeColor = ((MyRectangle)screen.EditedShape).StrokeColor;
                        ((MyRectangle)item).styleLine = ((MyRectangle)screen.EditedShape).styleLine;
                        ((MyRectangle)item).PenWidth = ((MyRectangle)screen.EditedShape).PenWidth;
                    }
                    else if (item is MyEllipse)
                    {
                        ((MyEllipse)item).X = ((MyEllipse)screen.EditedShape).X;
                        ((MyEllipse)item).Y = ((MyEllipse)screen.EditedShape).Y;
                        ((MyEllipse)item).Width = ((MyEllipse)screen.EditedShape).Width;
                        ((MyEllipse)item).Height = ((MyEllipse)screen.EditedShape).Height;
                        ((MyEllipse)item).Fill = ((MyEllipse)screen.EditedShape).Fill;
                        ((MyEllipse)item).StrokeColor = ((MyEllipse)screen.EditedShape).StrokeColor;
                        ((MyEllipse)item).styleLine = ((MyEllipse)screen.EditedShape).styleLine;
                        ((MyEllipse)item).PenWidth = ((MyEllipse)screen.EditedShape).PenWidth;
                    }
                }
            }
            else if (item is MyLine)
            {
                var screen = new EditLine(item);

                if (screen.ShowDialog() == true)
                {
                    ((MyLine)item).X1 = ((MyLine)screen.EditedShape).X1;
                    ((MyLine)item).Y1 = ((MyLine)screen.EditedShape).Y1;
                    ((MyLine)item).X2 = ((MyLine)screen.EditedShape).X2;
                    ((MyLine)item).Y2 = ((MyLine)screen.EditedShape).Y2;
                    ((MyLine)item).Fill = ((MyLine)screen.EditedShape).Fill;
                    ((MyLine)item).StrokeColor = ((MyLine)screen.EditedShape).StrokeColor;
                    ((MyLine)item).styleLine = ((MyLine)screen.EditedShape).styleLine;
                    ((MyLine)item).PenWidth = ((MyLine)screen.EditedShape).PenWidth;
                }
            }

            // Clear the canvas
            canvas.Children.Clear();

            // Draw all the previous drawn objects
            foreach (IShape shape in objects)
            {
                var action = actions[shape.MagicWord];
                if (action is MyText)
                    continue;
                canvas.Children.Add(action.Draw(shape) as UIElement);
            }
        }

        private void deleteMenu_Click(object sender, RoutedEventArgs e)
        {
            int ele = ListViewOfShapes.SelectedIndex;
            objects.RemoveAt(ele);

            // Clear the canvas
            canvas.Children.Clear();

            // Draw all the previous drawn objects
            foreach (IShape shape in objects)
            {
                var action = actions[shape.MagicWord];
                if (action is MyText)
                    continue;
                canvas.Children.Add(action.Draw(shape) as UIElement);
            }
        }

        private void textBold_Click(object sender, RoutedEventArgs e)
        {
            if (FontWeight == FontWeights.Bold)
            {
                FontWeight = FontWeights.Normal;
                return;
            }
            FontWeight = FontWeights.Bold;
        }

        private void textItalic_Click(object sender, RoutedEventArgs e)
        {
            if (fontStyle == FontStyles.Italic)
            {
                fontStyle = FontStyles.Normal;
                return;
            }
            fontStyle = FontStyles.Italic;
        }

        private void textUnderline_Click(object sender, RoutedEventArgs e)
        {
            decorations.Add(TextDecorations.Underline);
        }

        private void textStrikethrough_Click(object sender, RoutedEventArgs e)
        {
            decorations.Add(TextDecorations.Strikethrough);
        }

        private void copyMenu_Click(object sender, RoutedEventArgs e)
        {
            IShape item = null;

            if (ListViewOfShapes.SelectedItem is MyEllipse) item = ListViewOfShapes.SelectedItem as MyEllipse;
            else if (ListViewOfShapes.SelectedItem is MyRectangle) item = ListViewOfShapes.SelectedItem as MyRectangle;
            else if (ListViewOfShapes.SelectedItem is MyLine) item = ListViewOfShapes.SelectedItem as MyLine;

            listForCopyAndPaste.Add(item);
        }

        private void ClearAll_Click(object sender, RoutedEventArgs e)
        {
            canvas.Children.Clear();
            objects.Clear();

            for (int i = 0; i < grid.Children.Count; i++)
            {
                if (grid.Children[i] is Layer) grid.Children.RemoveAt(i);
            }
            objects.Clear();
            isDone = true;


            for (int i = 0; i < grid.Children.Count; i++)
            {
                if (!(grid.Children[i] is Layer)) continue;
                grid.Children.Remove((Layer)grid.Children[i]);
            }
        }
    }
}
