using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Media;
using IContract;

namespace FinalProject
{
    class MyPolyLine : IShape
    {
        public string MagicWord => "MyPolyLine";

        public string DisplayName => "MyPolyLine";

        public Color Fill { get; set; }
        public Color StrokeColor { get; set; }
        public StyleLines styleLine { get; set; }
        public int PenWidth { get; set; }
        public PointCollection polygonPoints;

        public IShape Clone()
        {
            return MemberwiseClone() as IShape;
        }

        public void HandleMouseDown(double x, double y)
        {
            Point newPoint = new Point();
            newPoint.X = x;
            newPoint.Y = y;
            polygonPoints.Add(newPoint);
        }

        public void HandleMouseUp(double x, double y)
        {
            Point newPoint = new Point();
            newPoint.X = x;
            newPoint.Y = y;
            polygonPoints.Add(newPoint);
        }
    }
}
