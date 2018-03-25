package scotch.io.CloudApplication;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class CloudApplication implements CommandLineRunner{

	@Autowired 
	private DataSource datasource;
	
//	@Bean
//	public DataSource dataSource() {
//		return DataSourceBuilder.create().build();
//	}

	  
	public void run(String... args) throws Exception {
		org.apache.tomcat.jdbc.pool.DataSource tomcat = (org.apache.tomcat.jdbc.pool.DataSource) datasource;
		System.err.println(tomcat.getDriverClassName() + ", " + tomcat.getUrl());
		System.err.println("hello.........."+tomcat);
	}

	public static void main(String[] args) {
		SpringApplication.run(CloudApplication.class, args);
	}
}
//class CloudConfig extends AbstractCloudConfig {
//
//    @Bean
//    public DataSource inventoryDataSource() {
//        return (DataSource) connectionFactory().dataSource("springTask");
//    }
//
//}

//public class Configuration {
//    @Configuration
//    @Profile("cloud")
//    static class CloudConfiguration {
//
//        @Bean
//        public DataSource dataSource() {
//            CloudFactory cloudFactory = new CloudFactory();
//            Cloud cloud = cloudFactory.getCloud();
//            String serviceID = cloud.getServiceID();
//            return cloud.getServiceConnector(serviceID, DataSource.class, null);
//        }
//    }
//
//    @Configuration
//    @Profile("default")
//    static class LocalConfiguration {
//
//    @Bean
//        public DataSource dataSource() {
//            BasicDataSource dataSource = new BasicDataSource();
//            dataSource.setUrl("jdbc:postgresql://localhost/db");
//            dataSource.setDriverClassName("org.postgresql.Driver");
//            dataSource.setUsername("postgres");
//            dataSource.setPassword("postgres");
//            return dataSource;
//        }
//    }
//}