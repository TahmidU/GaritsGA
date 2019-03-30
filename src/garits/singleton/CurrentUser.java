package garits.singleton;

public class CurrentUser
{
    private static CurrentUser instance = null;
    private static String userName;

    public static CurrentUser getInstance()
    {
        if(instance == null)
            instance = new CurrentUser();

        return instance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        CurrentUser.userName = userName;
    }
}
