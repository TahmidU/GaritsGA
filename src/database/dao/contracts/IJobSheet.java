package database.dao.contracts;

import database.domain.job.JobSheet;

import java.util.ArrayList;

public interface IJobSheet
{
    ArrayList<JobSheet> getAll();
    void save(JobSheet jobSheet);
    void update(JobSheet jobSheet);
    void delete(JobSheet jobSheet);
    void delete(int job_no);
}
