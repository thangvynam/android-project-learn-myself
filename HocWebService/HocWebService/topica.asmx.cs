using System;
using System.Collections.Generic;
using System.Web;
using System.Web.Services;

namespace HocWebService
{
    /// <summary>
    /// Summary description for topica
    /// </summary>
    [WebService(Namespace = "http://topica.edu.vn/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]

    public class topica : System.Web.Services.WebService
    {

        [WebMethod]
        public string HelloWorld()
        {
            return "Hello World";
        }
        [WebMethod]
        public List<String>GetDanhSach()
        {
            List<String> ds = new List<String>();
            ds.Add("Thang");
            ds.Add("Vỹ");
            ds.Add("Nam");
            return ds;
        }
        [WebMethod]
        public String GiaiPhuongTrinhBac1(int a,int b)
        {
            if (a == 0 && b == 0)
                return "Vô số nghiệm";
            else if (a == 0 && b == 0)
                return "Vô nghiệm";
            return "x = " + (-b*1.0 / a);
        }

        [WebMethod]
        public Contact LayThongTinChiTiet(int ma)
        {
            Contact c = new Contact();
            c.Ma = ma;
            c.Phone = "0906368182";
            c.Ten = "Thang Vỹ Nam";
            return c;
        }
        [WebMethod]
        public List<Contact>Lay5Contact()
        {
            List<Contact> ds= new List<Contact>();
            ds.Add(new Contact() { Ma = 1, Ten = "Sơn Tùng Mtp", Phone = "123456789" });
            ds.Add(new Contact() { Ma = 2, Ten = "Cristian Ronaldo", Phone = "21151236" });
            ds.Add(new Contact() { Ma = 3, Ten = "Sagna", Phone = "213143543" });
            ds.Add(new Contact() { Ma = 4, Ten = "Mendy", Phone = "768765642" });
            ds.Add(new Contact() { Ma = 5, Ten = "Bernado Silva", Phone = "398271038" });
            return ds;
        }
        [WebMethod]
        public Contact GetDetail(int ma)
        {
            return new Contact() { Ma = ma, Ten = "Thang Vỹ Nam", Phone = "0906368182" };
        }

    }
}
