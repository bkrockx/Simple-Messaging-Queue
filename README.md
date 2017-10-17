This application is a in-memory queueing system.

Requirements The requirements of this application are:

[1] IntelliJ IDEA/IDE

[2] Maven

[3] Spring boot 


How to Run?

Run the application using the following command

1. For Running the producer and consumer
=> mvn clean package -DskipTests or mvn clean package(This will create the jar file to run the consumer and producer of the application)

=> java -jar -Dspring.profiles.active=stage 'jar file constructed'
(This will run the prodcuer and consumer)

Description of components inside the projects

Controllers
Producer => Responsible for producing the json messages to the consume via RouterService.
Consumer => Responsible for consuming the message after being produced to seperate consumer.

Service:
MessageDeliveryService: uses topological sorting to ensure that the message is delivered in order. Function delivery() uses function deliveryUtil()
and subsequently return a stack containing customer ids in the order to be delivered.

ConsumerService : consumes message on the basis of ordering 
ProducerService : produces json messages

Model
Consumer : can generate the consumers with msg,prefrences,url,id and also handle a new message being sent.
It will also perform retry mechanism.

ConsumerDependencyGraph : ConsumerDependencyGraph contains the adjacency matrix containing the order in which message is needed to be delivered to consumers.
Suppose message should be delivered to consumer a first then to b, then dependencyMatrix[a] contains b.

InputRequest/Message : Parsing the json object to java model and then using it in subsequent methods implemented.