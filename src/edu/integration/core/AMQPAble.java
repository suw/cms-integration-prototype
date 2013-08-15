package edu.integration.core;

/**
 * All classes capable of using AMQP will extend from here so they connect to
 * the same server and avoid making multiple connections.
 *
 * @author suw <suw@suwdo.com>
 */
public class AMQPAble {

	/**
	 * Server address for the AMQP server
	 *
	 * @var String serverAddress
	 */
	static protected final String serverAddress = "10.0.1.99";

	/**
	 * Is this AMQPAble connected?
	 *
	 * @var boolean connected
	 */
	protected boolean connected = false;
}
