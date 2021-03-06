package database.dao.contracts;

import database.domain.discount.DiscountPlan;

import java.util.ArrayList;

public interface IDiscountPlan
{
    ArrayList<DiscountPlan> getAll();
    DiscountPlan getByDiscountId(int discountId);
    DiscountPlan getByAccId(int id);
    void save(DiscountPlan discountPlan);
    void update(DiscountPlan discountPlan);
    void delete(DiscountPlan discountPlan);
    void delete(int id);
}
