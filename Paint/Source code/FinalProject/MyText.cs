using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Media;
using IContract;

namespace FinalProject
{
    class MyText : IShape
    {
        public string MagicWord { get => "MyText"; }

        public string DisplayName { get => "Text"; }

        public double X { get; set; }
        public double Y { get; set; }
        public double Width { get; set; }
        public double Height { get; set; }
        public Color Fill { get; set; }
        public Color StrokeColor { get; set; }
        public Color TextColor { get; set; }
        public string RectText { get; set; }
        public double RectFontSize { get; set; }
        public FontStretch RectFontStretch { get; set; }
        public TextDecorationCollection FontDecorations { get; set; }
        public FontStyle FontStyle { get; set; }
        public FontWeight RectFontWeight { get; set; }
        public FontFamily RectFontFamily { get; set; }

        public IShape Clone()
        {
            return MemberwiseClone() as IShape;
        }

        public void HandleMouseDown(double x, double y)
        {
            X = x;
            Y = y;
        }

        public void HandleMouseUp(double x, double y)
        {
            Width = Math.Abs(x - X);
            Height = Math.Abs(y - Y);
        }
    }
}
