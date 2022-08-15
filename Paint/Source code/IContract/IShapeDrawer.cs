using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IContract
{
    public interface IShapeDrawer
    {
        string MagicWord { get; }
        object Draw(IShape shape);
    }
}
