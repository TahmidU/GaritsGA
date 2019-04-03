package report.table;

public class StockLevelTable
{
    private String part_nm;
    private String code;
    private String manufacturer;
    private String type;
    private String years;
    private String price;
    private String initial_stock;
    private String initial_cost;
    private String used;
    private String delivery;
    private String new_stock;
    private String stock_cost;
    private String low_thres;

    public StockLevelTable(String part_nm, String code, String manufacturer, String type, String years, String price,
                           String initial_stock, String initial_cost, String used, String delivery, String new_stock,
                           String stock_cost, String low_thres) {
        this.part_nm = part_nm;
        this.code = code;
        this.manufacturer = manufacturer;
        this.type = type;
        this.years = years;
        this.price = price;
        this.initial_stock = initial_stock;
        this.initial_cost = initial_cost;
        this.used = used;
        this.delivery = delivery;
        this.new_stock = new_stock;
        this.stock_cost = stock_cost;
        this.low_thres = low_thres;
    }

    public String getPart_nm() {
        return part_nm;
    }

    public void setPart_nm(String part_nm) {
        this.part_nm = part_nm;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
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

    public String getInitial_stock() {
        return initial_stock;
    }

    public void setInitial_stock(String initial_stock) {
        this.initial_stock = initial_stock;
    }

    public String getInitial_cost() {
        return initial_cost;
    }

    public void setInitial_cost(String initial_cost) {
        this.initial_cost = initial_cost;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getNew_stock() {
        return new_stock;
    }

    public void setNew_stock(String new_stock) {
        this.new_stock = new_stock;
    }

    public String getStock_cost() {
        return stock_cost;
    }

    public void setStock_cost(String stock_cost) {
        this.stock_cost = stock_cost;
    }

    public String getLow_thres() {
        return low_thres;
    }

    public void setLow_thres(String low_thres) {
        this.low_thres = low_thres;
    }
}
