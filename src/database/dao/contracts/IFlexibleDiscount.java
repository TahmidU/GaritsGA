package database.dao.contracts;

import database.domain.discount.FlexibleDiscount;

import java.util.ArrayList;

public interface IFlexibleDiscount
{
    ArrayList<FlexibleDiscount> getAll();
    void save(FlexibleDiscount flexibleDiscount);
    void update(FlexibleDiscount flexibleDiscount);
    void delete(FlexibleDiscount flexibleDiscount);
    void delete(int id);
}
