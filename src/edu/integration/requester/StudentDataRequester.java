package edu.integration.requester;

import org.json.simple.JSONValue;

import edu.integration.core.*;

/**
 * Requester for student data
 *
 * @author suw <suw@suwdo.com>
 */
public class StudentDataRequester extends AMQPRequester {

	/**
	 * Construct the requester
	 *
	 * @throws Exception
	 */
	public StudentDataRequester() throws Exception {
		super("StudentDataWorker");
	}

	/**
	 * Handle request for student data
	 *
	 * @param String
	 *            input Student ID
	 */
	@Override
	public String handleRequest(String input) {
		System.out
				.println(" [x] Sending request to get student data with student id = "
						+ input);
		try {
			String jsonStudentData = this.call(input);
			Object obj = JSONValue.parse(jsonStudentData);

			System.out
					.println(" [x] Found data for Student with id = " + input);
			return obj.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return " [Error] Could not retrieve student data";
		}
	}
}
