package report.table;

public class PartTable
{
    private String order_num;
    private String desc;
    private String qty;
    private String price;

    public PartTable(String order_num, String desc, String qty, String price) {
        this.order_num = order_num;
        this.desc = desc;
        this.qty = qty;
        this.price = price;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
