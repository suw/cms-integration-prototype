package edu.integration.core;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * Abstract class that represents any class that can make an AMQP request
 *
 * @author suw <suw@suwdo.com>
 */
abstract public class AMQPRequester extends AMQPAble {

	/**
	 * Connection
	 *
	 * @var Connection connection
	 */
	protected Connection connection;

	/**
	 * Channel we're connecting to
	 *
	 * @var Channel channel
	 */
	protected Channel channel;

	/**
	 * Queue name
	 *
	 * @var String requestQueueName
	 */
	protected String requestQueueName;

	/**
	 * Reply queue name
	 *
	 * @var String replyQueueName
	 */
	protected String replyQueueName;

	/**
	 * Consumer
	 *
	 * @var QueuingConsumer consumer
	 */
	protected QueueingConsumer consumer;

	public AMQPRequester(String workerQueue) throws Exception {
		this.requestQueueName = workerQueue;

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(serverAddress);
		connection = factory.newConnection();
		channel = connection.createChannel();

		replyQueueName = channel.queueDeclare().getQueue();
		consumer = new QueueingConsumer(channel);
		channel.basicConsume(replyQueueName, true, consumer);
	}

	/**
	 * Send out the request and receive the data
	 *
	 * @param message
	 * @return String response
	 * @throws Exception
	 */
	protected String call(String message) throws Exception {
		String response = null;
		String corrId = java.util.UUID.randomUUID().toString();

		BasicProperties props = new BasicProperties.Builder()
				.correlationId(corrId).replyTo(replyQueueName).build();

		channel.basicPublish("", requestQueueName, props, message.getBytes());

		// Wait for response. Not necessary if no user feedback needed
		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			if (delivery.getProperties().getCorrelationId().equals(corrId)) {
				response = new String(delivery.getBody());
				break;
			}
		}
		return response;
	}

	/**
	 * Handle a request. Needs to be implemented by individual AMQPWorkers
	 *
	 * @param String input
	 */
	abstract public String handleRequest(String input);
}
