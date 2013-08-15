package edu.integration.worker;

import org.json.simple.JSONObject;

import edu.integration.core.*;

/**
 * This class simulates the grabbing of student data from a database. For
 * simplicity, the data shall be manually entered into a JSONObject.
 *
 * @author suw <suw@suwdo.com>
 *
 */
public class StudentDataWorker extends AMQPWorker implements Runnable {

	/**
	 * Construct the worker
	 */
	public StudentDataWorker() {
		super("StudentDataWorker");
	}

	/**
	 * Simulate the processing for getting student data based on
	 * student ID input.
	 *
	 * @param String message Pretend Student ID number
	 *
	 * @return String JSON Output
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected String doTask(String message) {

		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		JSONObject ret = new JSONObject();
		ret.put("name", "Babby Smith");
		ret.put("email_address", "bsmith@yahoo.com");
		ret.put("home_phone", "717-55-4421");
		ret.put("home_address", "191 Central Ave, Los Angeles, CA");
		ret.put("local_address", "8371 Newport Street, Costa Mesa, CA");
		ret.put("emergency_contact", "John Smith (717-555-4421)");
		ret.put("level", new Integer(1));

		return ret.toString();
	}

	/**
	 * Wait for task as thread
	 */
	@Override
	public void run() {
		waitForTask();
	}
}
