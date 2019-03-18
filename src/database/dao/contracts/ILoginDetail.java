package database.dao.contracts;

import database.domain.account.LoginDetail;

import java.util.ArrayList;

public interface ILoginDetail
{
    ArrayList<LoginDetail> getAll();
    LoginDetail getByUsername(String username);
    LoginDetail getByStaffId(int staffId);
    void save(LoginDetail loginDetail);
    void update(LoginDetail loginDetail);
    void delete(LoginDetail loginDetail);
    void delete(String username);
}
