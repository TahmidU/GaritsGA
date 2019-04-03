package report.table;

public class JobSheetTable
{
    private String desc;
    private String part_num;
    private String quantity;

    public JobSheetTable(String desc, String part_num, String quantity) {
        this.desc = desc;
        this.part_num = part_num;
        this.quantity = quantity;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPart_num() {
        return part_num;
    }

    public void setPart_num(String part_num) {
        this.part_num = part_num;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
