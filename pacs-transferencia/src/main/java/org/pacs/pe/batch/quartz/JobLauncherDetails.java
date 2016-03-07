package org.pacs.pe.batch.quartz;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import org.quartz.JobExecutionContext;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class JobLauncherDetails extends QuartzJobBean {
	static final String JOB_NAME = "jobName";
	private JobLocator jobLocator;
	private JobLauncher jobLauncher;

	public void setJobLocator(JobLocator jobLocator) {
		this.jobLocator = jobLocator;
	}

	public void setJobLauncher(JobLauncher jobLauncher) {
		this.jobLauncher = jobLauncher;
	}

	protected void executeInternal(JobExecutionContext context) {
		Map<String, Object> jobDataMap = context.getMergedJobDataMap();

		String jobName = (String) jobDataMap.get("jobName");

		JobParameters jobParameters = getJobParametersFromJobMap(jobDataMap);
		try {
			this.jobLauncher.run(this.jobLocator.getJob(jobName), jobParameters);
		} catch (JobExecutionException e) {
			e.printStackTrace();
		}
	}

	private JobParameters getJobParametersFromJobMap(Map<String, Object> jobDataMap) {
		JobParametersBuilder builder = new JobParametersBuilder();
		for (Map.Entry<String, Object> entry : jobDataMap.entrySet()) {
			String key = (String) entry.getKey();
			Object value = entry.getValue();
			if (((value instanceof String)) && (!key.equals("jobName"))) {
				builder.addString(key, (String) value);
			} else if (((value instanceof Float)) || ((value instanceof Double))) {
				builder.addDouble(key, Double.valueOf(((Number) value).doubleValue()));
			} else if (((value instanceof Integer)) || ((value instanceof Long))) {
				builder.addLong(key, Long.valueOf(((Number) value).longValue()));
			} else if ((value instanceof Date)) {
				builder.addDate(key, (Date) value);
			}
		}
		builder.addDate("run date", new Date());

		return builder.toJobParameters();
	}
}
