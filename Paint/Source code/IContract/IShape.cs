using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IContract
{
    public enum StyleLines
    {
        Soild,
        Dash,
        Dot,
        DashDot,
        DashDotDot
    };
    public interface IShape
    {
        string MagicWord { get; }
        string DisplayName { get; }
        //string Tag { get; set; }
        IShape Clone();

        void HandleMouseDown(double x, double y);
        void HandleMouseUp(double x, double y);
    }
}
