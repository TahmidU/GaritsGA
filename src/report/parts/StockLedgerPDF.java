package report.parts;

import database.dao.part.StockPartDAO;
import database.domain.part.StockPart;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import report.table.StockLedgerTable;
import util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class StockLedgerPDF implements Runnable
{
    private final String dataSource = "StockDataSource";
    private final String jrxmlSource = "src/report/parts/stock_ledger.jrxml";
    private final String pdfDest = "stock_ledger.pdf";

    private ArrayList<StockPart> stockParts;
    private ArrayList<StockLedgerTable> stockLedgerTables;

    public StockLedgerPDF()
    {
        stockParts = new StockPartDAO().getAll();
    }

    @Override
    public void run()
    {
        try
        {
            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlSource);

            stockLedgerTables = new ArrayList<>();

            for(StockPart s : stockParts)
            {
                stockLedgerTables.add(new StockLedgerTable(s.getPartName(),String.valueOf(s.getPartId()), s.getManufacturer(), s.getVehicleType(), s.getStartYr() + "-" + s.getEndYr(),
                        String.valueOf(s.getPrice()), String.valueOf(s.getQuantity()) ));
            }

            JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(stockLedgerTables);

            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("StockDataSource", jrBeanCollectionDataSource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfFile(jasperPrint, pdfDest);
            Log.write("PDF Generation finished...");
        } catch (JRException e) {
            e.printStackTrace();
            Log.write("Something went wrong.");
        }
    }
}
