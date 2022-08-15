using IContract;
using MyLine01;
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
    /// Interaction logic for EditLine.xaml
    /// </summary>
    public partial class EditLine : Window
    {
        public IShape EditedShape { get; set; }
        public EditLine(IShape shape)
        {
            InitializeComponent();

            EditedShape = (MyLine)shape.Clone();
            this.DataContext = EditedShape;
        }

        private void OK_BUtton_Click(object sender, RoutedEventArgs e)
        {
            DialogResult = true;
        }
    }
}
