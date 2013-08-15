package edu.integration.core;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

/**
 * Core AMQPWorker class that implements Java Runnable so it can run as a
 * separate thread.
 *
 * @see AMQPAble
 *
 * @implements Runnable
 *
 * @author suw <suw@suwdo.com>
 *
 */
abstract public class AMQPWorker extends AMQPAble implements Runnable {

	/**
	 * The queue consumer
	 *
	 * @var QueuingConsumer consumer
	 */
	protected QueueingConsumer consumer;

	/**
	 * The queue channel
	 *
	 * @var Channel channel
	 */
	protected Channel channel;

	/**
	 * Set up the worker
	 *
	 * @param String workerType
	 */
	public AMQPWorker(String workerType) {
		System.out.println(" [x] Started worker with type = " + workerType);
		this.connect(workerType);
	}

	/**
	 * Connect to the AMQP server
	 *
	 * @param queueName
	 * @return boolean  True if connection successful
	 */
	protected boolean connect(String queueName) {
		// If already connected, don't bother connecting again
		if (connected) {
			return true;
		}
		try {
			// Set up the connection to the RabbitMQ server
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost(AMQPAble.serverAddress);
			Connection connection = factory.newConnection();

			// Connect to the queue
			this.channel = connection.createChannel();
			this.channel.queueDeclare(queueName, false, false, false, null);
			this.channel.basicQos(1);

			// Set as consumer (aka request granter)
			this.consumer = new QueueingConsumer(this.channel);
			this.channel.basicConsume(queueName, false, this.consumer);

			this.connected = true;

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Wait for a task to come in.
	 *
	 * @return void
	 */
	public void waitForTask() {

		while (true) {
			try {
				QueueingConsumer.Delivery delivery = this.consumer
						.nextDelivery();

				BasicProperties props = delivery.getProperties();
				BasicProperties replyProps = new BasicProperties.Builder()
						.correlationId(props.getCorrelationId()).build();

				String message = new String(delivery.getBody());

				String response = this.doTask(message);

				this.channel.basicPublish("", props.getReplyTo(), replyProps,
						response.getBytes());
				this.channel.basicAck(delivery.getEnvelope().getDeliveryTag(),
						false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Implement me to do a task, called by waitForTask()
	 *
	 * @return
	 */
	abstract protected String doTask(String message);
}
