# spring-jms-message

This project demonstrates  how to send messages to ActiveMQ using a Spring Boot application.

This sample project is based on post [Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=com.codenotfound%3Aspring-jms-message-selector&metric=alert_status)

## How to Run the project and tests

### Run application

- Clone this repository 

```
 git https://github.com/guedim/spring-projects.git
```

- Move to the directory `active-mq-sender`

```
cd spring-projects/active-mq/active-mq-sender
```

- Fix broker url and credentials in `application.yml` located in  `src/main/resources`.
- Set Sender type (`HTTP` or `JMS`) using property  `sender:type`  in `application.yml` 
- Add the data file to process located in located in  `src/main/resources`. Take a look for file structure:

```csv
description_a,1
description_b,2
description_c,3
````

- Run spring boot application.

```
    ./mvnw spring-boot:run
```


### References


- https://activemq.apache.org/rest
- https://spring.io/guides/gs/consuming-rest/