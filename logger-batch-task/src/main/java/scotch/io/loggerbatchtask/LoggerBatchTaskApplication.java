package scotch.io.loggerbatchtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;

@SpringBootApplication
@EnableTask
@EnableBatchProcessing
public class LoggerBatchTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoggerBatchTaskApplication.class, args);
	}
}
