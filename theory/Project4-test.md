# BitTiger Project 4 test

Q1: Why do we use Dependency Injection(5’)? How is DI used in Spring(5’)??

A1: (1)DI is a good design mode that let service code do not need to think about client and client code do not think about the service how to be implement and create and just use it, the frame work will inject the sevice object to client.  
(2)use @Service annotate to mark a clase as a injectable and use @Autowired to a Constructor/Field/Proporty in client class.

---
Q2: What are the advantages of Spring Boot compared to traditional Spring?(10’)
A2: XML free and simplify the development and configure.

---
Q3: What are Spring Boot starter projects?(10’)

A3: Spring Boot Starters are a set of convenient dependency descriptors that you can include in your application. You get a one-stop-shop for all the Spring and related technology that you need without having to hunt through sample code and copy paste loads of dependency descriptors.

---
Q4: How do we connect to an external MySQL database in Spring Boot application(10’)?

A4: Add dependency: spring-boot-starter-data-jpa, spring-boot-starter-jdbc and mysql-connector-java  
Add resources/application.yml include: spring/datasource/url,username,password.  
Add data class to project and annotate it with @Entity and @Table.  
Add a Repository interface for data class whick extends JpaRepository.  
Use the methods provided by Repository interface to CRUD mysql data.

---
Q5: What is the need for Profiles(10’)? How can you use profiles to configure environment specific configuration with Spring Boot(10’)?

A5: (1)Spring Profiles provide a way to segregate parts of your application configuration and make it be available only in certain environments.  
(2)Add @Profile("production") to @Configuration and @Component.  
Add application.yml include: spring/profiles/active=production

---
Q6: Your company is working on a video streaming website. User can register and login, watch video streaming,  subscribe videos daily, weekly, monthly or yearly, pay the subscription by various payment methods and review the videos. The website can also recommend related videos based on user video view history.  
Answer the following questions based on above requirements. 8 points per question.

1) If you are an architect, how do you design the system to make it more scalable?  
2) Why did you use this architecture?  
3) How do backend components talk to each other?  
4) Is online or offline data extraction more appropriate for recommendation?  
5) What system functions can be async? How to do it?

A6: (1)Architect based on microserive include UI, webserver, authentication and authorization service, video stream service, user subscribe and preference service, user video view history processing pipline, payment service.  
(2)Every service can indenpently be deployed and scaled.  
(3)use service registration and discovery service like spring eureka, so components can find each other and then call it's API.
(4)Offline.
(5)Payment, use message queue.