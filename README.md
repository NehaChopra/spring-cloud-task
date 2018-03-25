# spring-cloud-task
Sample Examples of Spring Cloud Data Flow is a toolkit for building data integration and real-time data processing pipelines.    Pipelines consist of Spring Boot apps, built using the Spring Cloud Stream or Spring Cloud Task microservice frameworks. This makes Spring Cloud Data Flow suitable for a range of data processing use cases, from import/export to event streaming and predictive analytics.



Run the Spring cloud dataflow server for UI modification:

java -jar spring-cloud-dataflow-server-local-1.3.1.RELEASE.jar
--spring.datasource.url="jdbc:mysql://localhost/springTask" --spring.datasource.username="root" --spring.datasource.password="root" --spring.datasource.driver-class-name="org.mariadb.jdbc.Driver" 


Requirement:
1. RabbitMQ
2. mysql ()




https://docs.spring.io/spring-cloud-task/1.1.1.RELEASE/reference/htmlsingle/#features-task-configurer


