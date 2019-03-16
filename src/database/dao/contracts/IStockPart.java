package database.dao.contracts;

import database.domain.part.StockPart;

import java.util.ArrayList;

public interface IStockPart
{
    ArrayList<StockPart> getAll();
    void save(StockPart stockPart);
    void update(StockPart stockPart);
    void delete(StockPart stockPart);
    void delete(int part_id);
}
