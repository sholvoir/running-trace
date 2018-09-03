# Week 11

Q1. In what cases you want to use Spring Data? In what cases you want to use Spring Data REST?

A1: If you have data repository requirement you should consider to use Spring Data.
If you need to expose data diresctly to REST API and no business logic you can consider to use spring data rest.

---
Q2. What are the design decisions when you choose relational DB VS NoSQL DB?

A1: If you need complex query and transaction you have to choose relational DB else NoSQL DB.

---
Q3. Why did you introduce MQ in the system architecture?

A3: Decouple and Asynchronous.

---
Q4: What are the key differences between Kafka and RabbitMQ?

A4: Delivery exactly once guarantee in Kafka (>=0.11.0),
Select one between at least once or at most once in RabbitMQ.

---
Q5: What is polyline? Why do you use polyline instead of a series of lat and longs?

A5: Polyline encoding is a lossy compression algorithm that allows you to store a series of coordinates as a single string.
It's convenient to use polyline when transport data between server and client through web than to use a series of coordinates.

---
Q6: How to create a new Thread in Java? What are the pros and cons with these two approaches?

A6: implements Runnable or extends Thread.
Runnable let you have free to select parent class. Thread is simpler.

---
Q7: What are future, executor and scheduler?

A7: Future is a concurrency method. it represents the result of an asynchromous computation. You can check if the computation is complete, wait for its conpletion and retrieve the result.
Executor implements multitype thread pool. Scheduler can run task periodly that is used to replace timer.
