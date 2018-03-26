package scotch.io.springcloudschedulerpayloadinitiator;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.cloud.task.launcher.TaskLaunchRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@EnableBinding(Source.class)
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	@Autowired
	private Source source;
		 
	@Autowired
	PersonService personService;	 

	@Autowired
	@Qualifier("dsTest")
	private DataSource testDataSource;
	
	@Autowired
	@Qualifier("dsTask")
	private DataSource taskDataSource;
	 
	 
	@Scheduled(fixedRate = 2000, initialDelay = 5000)
	/*
	 * Runs in every 2 sec 
	 */

	public void scheduleTaskWithInitialDelay() throws SQLException {
		    
		logger.info("test datasource .......{} ", testDataSource);
		logger.info("task datasource .......{} ", taskDataSource);

		logger.info("Fixed Rate Task with Initial Delay :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
		
		String uri = "maven://scotch.io:logger-batch-task:jar:0.0.1-SNAPSHOT";
		
		List<String> commandArgsList = new ArrayList<>();
		Map<String, String> deploymentProperties = new HashMap<String, String>();
		deploymentProperties.put("spring.cloud.deployer.memory", "152");
		
		for(Person p : personService.getAllPerson()){
		  commandArgsList.add(p.toString());
		}
		
		TaskLaunchRequest request = new TaskLaunchRequest(uri, commandArgsList, null, createDeploymentProperties(), "LoggerTask");
		source.output().send(new GenericMessage<TaskLaunchRequest>(request));
		logger.info("Finished the scheduled task Fixed Rate Task with Initial Delay :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
	}
	
	private Map<String, String> createDeploymentProperties() {
		  Map<String, String> deploymentProperties = new HashMap<>();
		  deploymentProperties.put("spring.cloud.deployer.memory", "512");
		  deploymentProperties.put("server.port", "9595");
//		  deploymentProperties.put("deployer.log.count", "2");
			/*
			//deploymentProperties.put("javaOpts", "-Xms152m");
			//deploymentProperties.put("spring.cloud.deployer.nomad.javaOpts", "-Xms152m");
			*/
			
		  return deploymentProperties;
	}
	
	private Map<String, String> createEnvironmentProperties() {
		  Map<String, String> environmentProperties = new HashMap<>();
//			environmentProperties.put("spring.datasource.url", "jdbc:mysql://localhost:3306/springTask");
//			environmentProperties.put("spring.datasource.driver-class-name", "com.mysql.jdbc.Driver");
//			environmentProperties.put("spring.datasource.username", "root");
//			environmentProperties.put("spring.datasource.password", "root");
			
		  return environmentProperties;
	}
}


