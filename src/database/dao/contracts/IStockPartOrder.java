package database.dao.contracts;

import database.domain.part.StockPartOrder;

import java.util.ArrayList;

public interface IStockPartOrder
{
    ArrayList<StockPartOrder> getAll();
    ArrayList<StockPartOrder> getByPartOrderNum(String orderNum);
    ArrayList<StockPartOrder> getByStockPartId(int stockPartId);
    void save(StockPartOrder stockPartOrder);
}
