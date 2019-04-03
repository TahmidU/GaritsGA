package report.table;

public class VehicleTable
{
    private String reg_num;
    private String make;
    private String model;
    private String eng_serial;
    private String chassis_num;
    private String colour;

    public VehicleTable(String reg_num, String make, String model, String eng_serial, String chassis_num, String colour) {
        this.reg_num = reg_num;
        this.make = make;
        this.model = model;
        this.eng_serial = eng_serial;
        this.chassis_num = chassis_num;
        this.colour = colour;
    }

    public String getReg_num() {
        return reg_num;
    }

    public void setReg_num(String reg_num) {
        this.reg_num = reg_num;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEng_serial() {
        return eng_serial;
    }

    public void setEng_serial(String eng_serial) {
        this.eng_serial = eng_serial;
    }

    public String getChassis_num() {
        return chassis_num;
    }

    public void setChassis_num(String chassis_num) {
        this.chassis_num = chassis_num;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}
