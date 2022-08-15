using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Data;
using System.Windows.Media;
using IContract;
using MyEllipse01;
using MyLine01;
using MyRectangle01;

namespace FinalProject
{
    class ShapeToStringDataConverter : IValueConverter
    {
        public string Convert(IShape value)
        {
            StringBuilder result = new StringBuilder();
            IShape shape = value as IShape;

            switch (shape.MagicWord)
            {
                case "MyLine":
                    result.AppendLine(((MyLine)shape).MagicWord.ToString());
                    result.AppendLine("X1" + ":" + ((MyLine)shape).X1.ToString());
                    result.AppendLine("Y1" + ":" + ((MyLine)shape).Y1.ToString());
                    result.AppendLine("X2" + ":" + ((MyLine)shape).X2.ToString());
                    result.AppendLine("Y2" + ":" + ((MyLine)shape).X2.ToString());
                    result.AppendLine("Fill" + ":" + ((MyLine)shape).Fill.ToString());
                    result.AppendLine("StrokeColor" + ":" + ((MyLine)shape).StrokeColor.ToString());
                    result.AppendLine("styleLine" + ":" + ((MyLine)shape).styleLine.ToString());
                    result.AppendLine("PenWidth" + ":" + ((MyLine)shape).PenWidth.ToString());
                    result.AppendLine("-");
                    break;
                case "MyRectangle":
                    result.AppendLine(((MyRectangle)shape).MagicWord.ToString());
                    result.AppendLine("X" + ":" + ((MyRectangle)shape).X.ToString());
                    result.AppendLine("Y" + ":" + ((MyRectangle)shape).Y.ToString());
                    result.AppendLine("Height" + ":" + ((MyRectangle)shape).Width.ToString());
                    result.AppendLine("Width" + ":" + ((MyRectangle)shape).Height.ToString());
                    result.AppendLine("Fill" + ":" + ((MyRectangle)shape).Fill.ToString());
                    result.AppendLine("StrokeColor" + ":" + ((MyRectangle)shape).StrokeColor.ToString());
                    result.AppendLine("styleLine" + ":" + ((MyRectangle)shape).styleLine.ToString());
                    result.AppendLine("PenWidth" + ":" + ((MyRectangle)shape).PenWidth.ToString());
                    result.AppendLine("-");
                    break;
                case "MyEllipse":
                    result.AppendLine(((MyEllipse)shape).MagicWord.ToString());
                    result.AppendLine("X" + ":" + ((MyEllipse)shape).X.ToString());
                    result.AppendLine("Y" + ":" + ((MyEllipse)shape).Y.ToString());
                    result.AppendLine("Height" + ":" + ((MyEllipse)shape).Width.ToString());
                    result.AppendLine("Width" + ":" + ((MyEllipse)shape).Height.ToString());
                    result.AppendLine("Fill" + ":" + ((MyEllipse)shape).Fill.ToString());
                    result.AppendLine("StrokeColor" + ":" + ((MyEllipse)shape).StrokeColor.ToString());
                    result.AppendLine("styleLine" + ":" + ((MyEllipse)shape).styleLine.ToString());
                    result.AppendLine("PenWidth" + ":" + ((MyEllipse)shape).PenWidth.ToString());
                    result.AppendLine("-");
                    break;
                default:
                    result.AppendLine();
                    break;
            }

            return result.ToString();
        }

        public IShape ConvertBack(string[] value)
        {
            string[] data = value as string[];
            IShape result = null;

            //Line 2
            string[] line2 = data[1].Split(new string[] { ":" }, StringSplitOptions.None);

            //Line 3
            string[] line3 = data[2].Split(new string[] { ":" }, StringSplitOptions.None);

            //Line 4
            string[] line4 = data[3].Split(new string[] { ":" }, StringSplitOptions.None);

            //Line 5
            string[] line5 = data[4].Split(new string[] { ":" }, StringSplitOptions.None);

            //Line 6
            string[] line6 = data[5].Split(new string[] { ":" }, StringSplitOptions.None);

            //Line 7
            string[] line7 = data[6].Split(new string[] { ":" }, StringSplitOptions.None);

            //Line 8
            string[] line8 = data[7].Split(new string[] { ":" }, StringSplitOptions.None);

            //Line 9
            string[] line9 = data[8].Split(new string[] { ":" }, StringSplitOptions.None);

            switch (data[0])
            {
                case "MyLine":
                    result = new MyLine();
                    ((MyLine)result).X1 = Double.Parse(line2[1]);
                    ((MyLine)result).Y1 = Double.Parse(line3[1]);
                    ((MyLine)result).X2 = Double.Parse(line4[1]);
                    ((MyLine)result).Y2 = Double.Parse(line5[1]);
                    ((MyLine)result).Fill = (Color)ColorConverter.ConvertFromString(line6[1]);
                    ((MyLine)result).StrokeColor = (Color)ColorConverter.ConvertFromString(line7[1]);
                    StyleLines style;
                    Enum.TryParse(line8[1], out style);
                    ((MyLine)result).styleLine = (IContract.StyleLines)style;
                    ((MyLine)result).PenWidth = int.Parse(line9[1]);
                    break;
                case "MyRectangle":
                    result = new MyRectangle();
                    ((MyRectangle)result).X = Double.Parse(line2[1]);
                    ((MyRectangle)result).Y = Double.Parse(line3[1]);
                    ((MyRectangle)result).Width = Double.Parse(line4[1]);
                    ((MyRectangle)result).Height = Double.Parse(line5[1]);
                    ((MyRectangle)result).Fill = (Color)ColorConverter.ConvertFromString(line6[1]);
                    ((MyRectangle)result).StrokeColor = (Color)ColorConverter.ConvertFromString(line7[1]);
                    StyleLines style2;
                    Enum.TryParse(line8[1], out style2);
                    ((MyRectangle)result).styleLine = (IContract.StyleLines)style2;
                    ((MyRectangle)result).PenWidth = int.Parse(line9[1]);
                    break;
                case "MyEllipse":
                    result = new MyEllipse();
                    ((MyEllipse)result).X = Double.Parse(line2[1]);
                    ((MyEllipse)result).Y = Double.Parse(line3[1]);
                    ((MyEllipse)result).Width = Double.Parse(line4[1]);
                    ((MyEllipse)result).Height = Double.Parse(line5[1]);
                    ((MyEllipse)result).Fill = (Color)ColorConverter.ConvertFromString(line6[1]);
                    ((MyEllipse)result).StrokeColor = (Color)ColorConverter.ConvertFromString(line7[1]);
                    StyleLines style3;
                    Enum.TryParse(line8[1], out style3);
                    ((MyEllipse)result).styleLine = (IContract.StyleLines)style3;
                    ((MyEllipse)result).PenWidth = int.Parse(line9[1]);
                    break;
            }

            return result;

        }
    }
}
