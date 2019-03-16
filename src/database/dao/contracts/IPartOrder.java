package database.dao.contracts;

import database.domain.part.PartOrder;

import java.util.ArrayList;

public interface IPartOrder
{
    ArrayList<PartOrder> getAll();
    void save(PartOrder partOrder);
    void update(PartOrder partOrder);
    void delete(PartOrder partOrder);
    void delete(String order_number);
}
