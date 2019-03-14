import database.dao.DBHelper;
import database.dao.account.LoginDetailDAO;
import database.dao.account.StaffDAO;
import database.dao.contracts.ILoginDetail;
import database.dao.contracts.IStaff;
import database.domain.account.LoginDetail;
import database.domain.account.Staff;

import java.util.ArrayList;

public class Main
{
    public static void main(String[] arg)
    {

        Staff s = new Staff(1, "Tahmid", "Uddin", "07464081609", "tahmiduddin@hotmail.co.uk", "Admin");

        IStaff test = new StaffDAO();
        ILoginDetail test2 = new LoginDetailDAO();


        LoginDetail ld = new LoginDetail("TahmidU", 1, "jim");

        //test.delete("TahmidU");
        //test.save(s);
        //test2.save(ld);
        //test.update(s);

        ArrayList<LoginDetail> loginDetails;
        loginDetails = test2.getAll();

        for(LoginDetail n: loginDetails)
        {
            System.out.println(n.getUserName() + "\t" + n.getStaffID() + "\t" + n.getPassword());
        }


        //DBHelper dbHelper = new DBHelper();
        //dbHelper.backUpDB();
    }
}
