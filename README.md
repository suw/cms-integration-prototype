# README

This is a prototype project for testing out an possible system wide integration
using RabbitMQ as the task/message broker. This

## Requirements

* Java SE 1.6.x+ with packages:
    * JSON-Simple 1.1.x
        * https://code.google.com/p/json-simple/
    * RabbitMQ-Java-Client 3.1.x
        * http://www.rabbitmq.com/java-client.html
* RabbitMQ Server 3.0.x

## To Run

1. Start RabbitMQ server on any reachable IP.
2. Update edu.integration.core.AMQPAble.serverAddress to IP of RabbitMQ server
3. Run UserInterfaceInteractionSimulation, watch console for simulated output

## References/resources

* “RabbitMQ - RabbitMQ tutorial - Remote procedure call (RPC) - Java”
    * http://www.rabbitmq.com/tutorials/tutorial-six-java.html
* “Decoding Examples”
    * https://code.google.com/p/json-simple/wiki/DecodingExamples
* “Concurrency - Simple”
    * http://docs.oracle.com/javase/tutorial/essential/concurrency/simple.html

## License

This project is licensed under GPL, version 3.0. Please see LICENSE for details.

## Warranty (From GPLv3)

THERE IS NO WARRANTY FOR THE PROGRAM, TO THE EXTENT PERMITTED BY APPLICABLE
LAW. EXCEPT WHEN OTHERWISE STATED IN WRITING THE COPYRIGHT HOLDERS AND/OR
OTHER PARTIES PROVIDE THE PROGRAM “AS IS” WITHOUT WARRANTY OF ANY KIND,
EITHER EXPRESSED OR IMPLIED, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE.
THE ENTIRE RISK AS TO THE QUALITY AND PERFORMANCE OF THE PROGRAM IS WITH
YOU. SHOULD THE PROGRAM PROVE DEFECTIVE, YOU ASSUME THE COST OF ALL NECESSARY
SERVICING, REPAIR OR CORRECTION.
