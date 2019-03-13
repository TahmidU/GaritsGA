import database.dao.DataSource;
import database.dao.IDao;
import database.dao.account.StaffDAO;
import database.dao.contracts.IStaff;
import database.domain.account.Staff;

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] arg)
    {
        Staff s = new Staff(1, "Bob", "Tob", "00000000000", "BT@hotmail.com", "Foreperson");

        IStaff test = new StaffDAO();

        //test.delete(s);
        test.save(s);
        //test.update(s);

        ArrayList<Staff> staffs;
        staffs = test.getAll();

        for(Staff n: staffs)
        {
            System.out.println(n.getId() + "\t" + n.getFirstName() + "\t" + n.getLastName() + "\t" + n.getPhoneNum() +
                    "\t" + n.getEmail() + "\t" + n.getType());
        }
    }
}
