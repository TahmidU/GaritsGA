package garits.singleton;

import database.domain.account.CustomerAcc;

public class CustomerSingleton
{
    private static CustomerSingleton instance = null;
    private static CustomerAcc customerAcc;

    public static CustomerSingleton getInstance()
    {
        if(instance == null)
            instance = new CustomerSingleton();

        return instance;
    }

    public CustomerAcc getCustomerAcc() {
        return customerAcc;
    }

    public void setCustomerAcc(CustomerAcc customerAcc) {
        CustomerSingleton.customerAcc = customerAcc;
    }
}
