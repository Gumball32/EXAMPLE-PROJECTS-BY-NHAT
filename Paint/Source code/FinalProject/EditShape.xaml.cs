using IContract;
using MyEllipse01;
using MyRectangle01;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace FinalProject
{
    /// <summary>
    /// Interaction logic for EditShape.xaml
    /// </summary>
    public partial class EditShape : Window
    {
        public IShape EditedShape { get; set; }
        public EditShape(IShape shape)
        {
            InitializeComponent();

            if (shape is MyRectangle) EditedShape = (MyRectangle)shape.Clone();
            else if (shape is MyEllipse) EditedShape = (MyEllipse)shape.Clone();

            this.DataContext = EditedShape;
        }

        private void OK_BUtton_Click(object sender, RoutedEventArgs e)
        {
            DialogResult = true;
        }
    }
}
