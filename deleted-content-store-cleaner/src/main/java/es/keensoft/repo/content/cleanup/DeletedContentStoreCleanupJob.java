package es.keensoft.repo.content.cleanup;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class DeletedContentStoreCleanupJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
        JobDataMap jobData = context.getJobDetail().getJobDataMap();
        DeletedContentStoreCleaner contentStoreCleaner = (DeletedContentStoreCleaner) jobData.get("deletedContentStoreCleaner");;
        contentStoreCleaner.execute();
		
	}

}
