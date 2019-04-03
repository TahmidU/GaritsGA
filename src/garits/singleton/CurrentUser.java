package garits.singleton;

import database.domain.account.Staff;

public class CurrentUser
{
    private static CurrentUser instance = null;
    private static Staff staff;

    public static CurrentUser getInstance()
    {
        if(instance == null)
            instance = new CurrentUser();

        return instance;
    }

    public Staff getStaff()
    {
        return staff;
    }

    public void setStaff(Staff staff)
    {
        CurrentUser.staff = staff;
    }
}
