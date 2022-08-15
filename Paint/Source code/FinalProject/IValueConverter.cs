using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using IContract;

namespace FinalProject
{
    interface IValueConverter
    {
        string Convert(IShape value);
        IShape ConvertBack(string[] value);
    }
}
