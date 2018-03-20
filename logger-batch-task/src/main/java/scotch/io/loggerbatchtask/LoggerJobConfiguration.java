package scotch.io.loggerbatchtask;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.activation.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.jdbc.Connection;



@Configuration
public class LoggerJobConfiguration implements CommandLineRunner {
	private static final Log logger = LogFactory.getLog(LoggerJobConfiguration.class);
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	
	 @Autowired
	 private JdbcTemplate jdbcTemplate;	 
	 
	 @Autowired
	 PersonService personService;	
	 
	 Person customer;

	@Override
	public void run(String... strings) throws Exception {
		logger.info("Task Payload Values.............. ");
		for(String ele : strings) {
			if ( ele.startsWith("{")) {
				logger.info("hello.........." +ele);
				
				customer = new Person();
				customer.setFirstName(ele);
				customer.setLastName(ele);
			}
		}
	}
	
	@Bean
	public Job job1() {
		return jobBuilderFactory.get("LoggerJob "+LocalDateTime.now())
				.start(stepBuilderFactory.get("LoggerJobStep")
					.tasklet(new Tasklet() {
						@Override
						public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
							logger.info("LoggerJob Job LoggerJobStep Step is running .............");
							personService.insert(customer, jdbcTemplate);
							logger.info("Finished LoggerJob Job LoggerJobStep Step is running .............");
							return RepeatStatus.FINISHED;
						}
					})
					.build())
				.build();
	}
	
}
