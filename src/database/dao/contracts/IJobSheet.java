package database.dao.contracts;

import database.domain.job.JobSheet;

import java.util.ArrayList;

public interface IJobSheet
{
    ArrayList<JobSheet> getAll();
    JobSheet getByJobNum(int jobNum);
    ArrayList<JobSheet> getByStaffId(int staffId);
    ArrayList<JobSheet> getByVehicleReg(String vehicleReg);
    JobSheet getByBookingId(int bookingId);
    void save(JobSheet jobSheet);
    void update(JobSheet jobSheet);
    void delete(JobSheet jobSheet);
    void delete(int job_no);
}
