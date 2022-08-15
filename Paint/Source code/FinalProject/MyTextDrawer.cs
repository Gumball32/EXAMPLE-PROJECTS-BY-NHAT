using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Controls;
using System.Windows.Media;
using IContract;

namespace FinalProject
{
    class MyTextDrawer : IShapeDrawer
    {
        public string MagicWord { get => "MyText"; }

        public object Draw(IShape shape)
        {
            var info = shape as MyText;

            var rectangle = new TextBox();

            rectangle.Width = info.Width;
            rectangle.Height = info.Height;

            rectangle.TextWrapping = System.Windows.TextWrapping.Wrap;
            rectangle.Foreground = new SolidColorBrush(info.TextColor);
            rectangle.Visibility = System.Windows.Visibility.Visible;
            rectangle.FontSize = info.RectFontSize;
            rectangle.FontFamily = info.RectFontFamily;
            rectangle.FontStyle = info.FontStyle;
            rectangle.Text = "Text here";
            rectangle.TextDecorations = info.FontDecorations;
            rectangle.FontWeight = info.RectFontWeight;
            rectangle.IsReadOnly = false;

            rectangle.Margin = new System.Windows.Thickness(info.X, info.Y, 0, 0);
            rectangle.HorizontalAlignment = System.Windows.HorizontalAlignment.Left;
            rectangle.VerticalAlignment = System.Windows.VerticalAlignment.Top;

            //Canvas.SetLeft(rectangle, info.X);
            //Canvas.SetTop(rectangle, info.Y);

            return rectangle;
        }
    }
}
