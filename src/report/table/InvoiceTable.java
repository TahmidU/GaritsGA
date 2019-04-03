package report.table;

public class InvoiceTable
{
    private String item;
    private String part_num;
    private String unit_cost;
    private String qty;
    private String cost;

    public InvoiceTable(String item, String part_num, String unit_cost, String qty, String cost) {
        this.item = item;
        this.part_num = part_num;
        this.unit_cost = unit_cost;
        this.qty = qty;
        this.cost = cost;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getPart_num() {
        return part_num;
    }

    public void setPart_num(String part_num) {
        this.part_num = part_num;
    }

    public String getUnit_cost() {
        return unit_cost;
    }

    public void setUnit_cost(String unit_cost) {
        this.unit_cost = unit_cost;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
