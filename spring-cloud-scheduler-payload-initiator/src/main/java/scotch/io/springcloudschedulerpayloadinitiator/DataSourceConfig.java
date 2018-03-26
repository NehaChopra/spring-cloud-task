package scotch.io.springcloudschedulerpayloadinitiator;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.task.configuration.DefaultTaskConfigurer;
import org.springframework.cloud.task.configuration.TaskConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataSourceConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);
	
	@Primary
	@Bean("dsTest")
	@ConfigurationProperties(prefix = "spring.datasource.test")
	public DataSource testDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean("jdbcTemplate")
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(testDataSource());
	} 
	
	@Bean("dsTask")
	@ConfigurationProperties(prefix = "spring.datasource.task")
	public DataSource taskDataSource() {
		return DataSourceBuilder.create().build();
	}
}
