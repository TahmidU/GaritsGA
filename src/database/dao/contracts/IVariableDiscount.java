package database.dao.contracts;

import database.domain.discount.VariableDiscount;

import java.util.ArrayList;

public interface IVariableDiscount
{
    ArrayList<VariableDiscount> getAll();
    VariableDiscount getByVariableDiscount(int id);
    VariableDiscount getByDiscountId(int discountId);
    void save(VariableDiscount variableDiscount);
    void update(VariableDiscount variableDiscount);
    void delete(VariableDiscount variableDiscount);
    void delete(int id);
}
