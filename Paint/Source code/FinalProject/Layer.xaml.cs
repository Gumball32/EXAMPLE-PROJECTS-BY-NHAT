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
using System.Windows.Navigation;
using System.Windows.Shapes;
using IContract;

namespace FinalProject
{
    /// <summary>
    /// Interaction logic for Layer.xaml
    /// </summary>
    public partial class Layer : UserControl
    {
        private object obj;
        public Layer(object item)
        {
            InitializeComponent();

            if (item is MyText)
            {
                obj = new MyText();
                obj = item;
            }
        }

        public void Create()
        {
            if (obj is MyText)
            {
                MyTextDrawer temp = new MyTextDrawer();
                TextBox newBox = (TextBox)temp.Draw(obj as IShape);
                LayerCanvas.Background = Brushes.Transparent;
                LayerCanvas.Children.Add(newBox);
            }
        }

        private void ItemCanvas_SizeChanged(object sender, SizeChangedEventArgs e)
        {
            if (obj is MyText)
            {
                //((TextObject)objectBase).rect.Width = this.Width;
                //((TextObject)objectBase).rect.Height = this.Height;

                this.Width = ((MyText)obj).Width;
                this.Height = ((MyText)obj).Height;
            }
        }
    }
}
