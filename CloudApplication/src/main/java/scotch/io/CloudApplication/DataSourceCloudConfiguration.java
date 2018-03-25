package scotch.io.CloudApplication;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("cloud")
public class DataSourceCloudConfiguration{

  @Bean
  public Cloud cloud() {
    return new CloudFactory().getCloud();
  }

  @Bean
  @ConfigurationProperties(prefix = "spring.datasource.url")
  public DataSource dataSource() {
    DataSource dataSource = cloud().getSingletonServiceConnector(DataSource.class, null);
    System.out.println(dataSource);
    return dataSource;
  }
}




//package scotch.io.CloudApplication;
//
//
//
//import javax.sql.DataSource;
//
//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.context.annotation.Profile;
//
//@Configuration
//@Profile("default")
//public class DataSourceLocalConfiguration{
//
//@Bean
//@ConfigurationProperties(prefix = "spring.datasource.url")
//public DataSource dataSource() {
//	  return (DataSource) DataSourceBuilder.create().build();
//}
//
//}