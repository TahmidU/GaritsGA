package database.dao.contracts;

import database.domain.part.StockPartOrder;

import java.util.ArrayList;

public interface IStockPartOrder
{
    ArrayList<StockPartOrder> getAll();
    void save(StockPartOrder stockPartOrder);
}
