# spring-cloud-task
Sample Examples of Spring Cloud Data Flow is a toolkit for building data integration and real-time data processing pipelines.    Pipelines consist of Spring Boot apps, built using the Spring Cloud Stream or Spring Cloud Task microservice frameworks. This makes Spring Cloud Data Flow suitable for a range of data processing use cases, from import/export to event streaming and predictive analytics.




java -jar spring-cloud-dataflow-server-local-1.3.1.RELEASE.jar      --spring.datasource.url="jdbc:mysql://localhost/springcloud"     --spring.datasource.username="root"     --spring.datasource.password="root"     --spring.datasource.driver-class-name="org.mariadb.jdbc.Driver" --spring.rabbitmq.host="localhost:5672" --spring.rabbitmq.port="5672" --spring.rabbitmq.username="guest" --spring.rabbitmq.password="guest"





java -jar spring-cloud-dataflow-shell-1.3.1.RELEASE.jar --spring.datasource.url="jdbc:mysql://localhost/springTask"     --spring.datasource.username="root"     --spring.datasource.password="root"     --spring.datasource.driver-class-name="org.mariadb.jdbc.Driver" --spring.rabbitmq.host="localhost:5672" --spring.rabbitmq.port="5672" --spring.rabbitmq.username="guest" --spring.rabbitmq.password="guest"


app register --name loggerTask --type task --uri maven://scotch.io:logger-batch-task:jar:0.0.1-SNAPSHOT

task create loggerTask --definition loggerTask




task create myjob --definition batch-job



java -jar spring-cloud-dataflow-server-local-1.3.0.M2.jar \
        --spring.datasource.url=jdbc:postgresql://localhost:5432/<database-name> \
        --spring.datasource.username=<username> \
        --spring.datasource.password=<password> \
        --spring.datasource.driver-class=org.postgresql.Driver \
        --spring.rabbitmq.host=127.0.0.1 \
        --spring.rabbitmq.port=5672 \
        --spring.rabbitmq.username=guest \
        --spring.rabbitmq.password=guest



https://docs.spring.io/spring-cloud-task/1.1.1.RELEASE/reference/htmlsingle/#features-task-configurer


