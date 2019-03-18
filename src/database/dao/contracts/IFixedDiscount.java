package database.dao.contracts;

import database.domain.discount.DiscountPlan;
import database.domain.discount.FixedDiscount;

import java.util.ArrayList;

public interface IFixedDiscount
{

    ArrayList<FixedDiscount> getAll();
    FixedDiscount getById(int id);
    FixedDiscount getByDiscountId(int discountId);
    void save(FixedDiscount fixedDiscount);
    void update(FixedDiscount fixedDiscount);
    void delete(FixedDiscount fixedDiscount);
    void delete(int id);
}
