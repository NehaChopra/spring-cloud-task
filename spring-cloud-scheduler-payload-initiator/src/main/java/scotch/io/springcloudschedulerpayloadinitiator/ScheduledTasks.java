package scotch.io.springcloudschedulerpayloadinitiator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.task.launcher.TaskLaunchRequest;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.cloud.stream.messaging.Source;


@Component
@EnableBinding(Source.class)
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	@Autowired
	private Source source;
	
	@Scheduled(fixedRate = 2000, initialDelay = 5000)
	/*
	 * Runs in every 2 sec 
	 */
	public void scheduleTaskWithInitialDelay() {
	    logger.info("Fixed Rate Task with Initial Delay :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
		String uri = "maven://scotch.io:logger-batch-task:jar:0.0.1-SNAPSHOT";
		List<String> commandArgsList = new ArrayList<>();
		commandArgsList.add("TaskRequest");
		commandArgsList.add("Arguments.......");
		TaskLaunchRequest request = new TaskLaunchRequest(uri, commandArgsList, null, null, "TaskLauncher");
		source.output().send(new GenericMessage<TaskLaunchRequest>(request));
		logger.info("Finished the scheduled task Fixed Rate Task with Initial Delay :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
	}
}
