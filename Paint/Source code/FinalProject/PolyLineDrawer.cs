using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Media;
using System.Windows.Shapes;
using IContract;

namespace FinalProject
{
    class PolyLineDrawer : IShapeDrawer
    {
        public string MagicWord => "MyPolyLine";

        public object Draw(IShape shape)
        {
            var info = shape as MyPolyLine;
            Polyline polyline = new Polyline();
            Canvas.SetLeft(polyline, 0);
            Canvas.SetTop(polyline, 0);
            polyline.Stroke = new SolidColorBrush(info.StrokeColor);
            polyline.Fill = new SolidColorBrush(info.Fill);
            polyline.StrokeThickness = info.PenWidth;
            polyline.Points = info.polygonPoints;

            return polyline;
        }
    }
}
