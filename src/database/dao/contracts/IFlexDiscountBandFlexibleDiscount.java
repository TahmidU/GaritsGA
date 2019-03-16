package database.dao.contracts;

import database.domain.discount.FlexDiscountBandFlexibleDiscount;

import java.util.ArrayList;

public interface IFlexDiscountBandFlexibleDiscount
{
    ArrayList<FlexDiscountBandFlexibleDiscount> getAll();
    void save(FlexDiscountBandFlexibleDiscount flexDiscountBandFlexibleDiscount);
    void delete(FlexDiscountBandFlexibleDiscount flexDiscountBandFlexibleDiscount);
    void delete(String band);
    void delete(int id);
}
