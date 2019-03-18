package database.dao.contracts;

import database.domain.discount.FlexibleDiscount;

import java.util.ArrayList;

public interface IFlexibleDiscount
{
    ArrayList<FlexibleDiscount> getAll();
    FlexibleDiscount getById(int id);
    FlexibleDiscount getByDiscountID(int discountId);
    void save(FlexibleDiscount flexibleDiscount);
    void update(FlexibleDiscount flexibleDiscount);
    void delete(FlexibleDiscount flexibleDiscount);
    void delete(int id);
}
