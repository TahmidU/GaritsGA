package database.dao.contracts;

import database.domain.discount.FlexDiscountBand;

import java.util.ArrayList;

public interface IFlexDiscountBand
{
    ArrayList<FlexDiscountBand> getAll();
    void save(FlexDiscountBand flexDiscountBand);
    void update(FlexDiscountBand flexDiscountBand);
    void delete(FlexDiscountBand flexDiscountBand);
    void delete(String band);
}
