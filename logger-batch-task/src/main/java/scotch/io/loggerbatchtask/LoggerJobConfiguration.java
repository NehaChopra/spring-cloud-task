package scotch.io.loggerbatchtask;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	Person customer;
	
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	PersonService personService;	
	 
	@Override
	public void run(String... strings) throws Exception {
		logger.info("Task Payload Values.............. " + strings);
		
		if(strings != null) {
			for(String ele : strings) {
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
							personService.insert(customer);
							logger.info("Finished LoggerJob Job LoggerJobStep Step is running .............");
							return RepeatStatus.FINISHED;
						}
					})
					.build())
				.build();
	}
	
}




//@Bean
//public CustomizedTaskConfigurer getTaskConfigurer()
//{
//  return new CustomizedTaskConfigurer(dataSource);
//}
//
//@Bean
//@Primary
//@ConfigurationProperties(prefix="test.datasource")
//public DataSource testDatasource(){
//	DataSource testDatasource = (DataSource) DataSourceBuilder.create().build();
//	logger.info("spring.secondDatasource.............. "+testDatasource);
//  return testDatasource;
//}