package edu.integration.requester;

import edu.integration.core.*;

/**
 * Requester for updating a student to graduate status
 *
 * @author suw <suw@suwdo.com>
 */
public class StudentUpdateRequester extends AMQPRequester {

	/**
	 * Construct the requester
	 *
	 * @throws Exception
	 */
	public StudentUpdateRequester() throws Exception {
		super("StudentUpdateWorker");
	}

	/**
	 * Handle request to update undergrad student to graduate status
	 *
	 * @param String
	 *            input Student ID
	 */
	@Override
	public String handleRequest(String input) {
		System.out
				.println(" [x] Sending request to update student with student id = "
						+ input + " to graduate status");
		try {
			return this.call(input);
		} catch (Exception e) {
			e.printStackTrace();
			return " [Error] Could not update student status";
		}
	}
}
