package scotch.io.loggerbatchtask;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggerJobConfiguration {
	private static final Log logger = LogFactory.getLog(LoggerJobConfiguration.class);
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	
	@Bean
	public Job job1() {
		return jobBuilderFactory.get("LoggerJob "+LocalDate.now())
				.start(stepBuilderFactory.get("LoggerJobStep")
					.tasklet(new Tasklet() {
						@Override
						public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
							logger.info("LoggerJob Job LoggerJobStep Step is running .............");
							return RepeatStatus.FINISHED;
						}
					})
					.build())
				.build();
	}
	
}