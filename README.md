This application is a in-memory queueing system.

Requirements The requirements of this application are:

[1] IntelliJ IDEA

[2] Maven

[3] Spring boot (Starter with test)


How to Run?

Run the application using the following command

1. For Running the producer and consumer
=> mvn clean package -DskipTests (This will create the jar file to run the consumer and producer of the application)

=> java -jar -Dspring.profiles.active=stage 'jar file constructed'
(This will run the prodcuer and consumer)

2. For testing the producer and consumer
=> Now Run the tests to check the consumers are consuming the data or not
You can run by going inside the test folder and running by right click and run 

Using command line
mvn test (This will run the test with the message / seperate for bulk and single message )


Also if you want you can run the producer/consumer using the java command and then make a curl request or using postman 
to check the consumer recieved the message or not