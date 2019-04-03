package report.table;

public class StockLedgerTable
{
    private String part_name;
    private String code;
    private String manufactuer;
    private String type;
    private String years;
    private String price;
    private String Stock;

    public StockLedgerTable(String part_name, String code, String manufactuer, String type, String years, String price, String Stock) {
        this.part_name = part_name;
        this.code = code;
        this.manufactuer = manufactuer;
        this.type = type;
        this.years = years;
        this.price = price;
        this.Stock = Stock;
    }

    public String getPart_name() {
        return part_name;
    }

    public void setPart_name(String part_name) {
        this.part_name = part_name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getManufactuer() {
        return manufactuer;
    }

    public void setManufactuer(String manufactuer) {
        this.manufactuer = manufactuer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStock() {
        return Stock;
    }

    public void setStock(String Stock) {
        this.Stock = Stock;
    }
}
