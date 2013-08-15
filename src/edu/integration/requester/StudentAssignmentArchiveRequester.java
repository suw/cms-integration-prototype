package edu.integration.requester;

import edu.integration.core.*;

/**
 * Requester for an instructor wanting an archive of student submitted
 * assignments as a web download.
 *
 * @author suw <suw@suwdo.com>
 */
public class StudentAssignmentArchiveRequester extends AMQPRequester {


	/**
	 * Construct the requester
	 *
	 * @throws Exception
	 */
	public StudentAssignmentArchiveRequester() throws Exception {
		super("StudentAssignmentArchiveWorker");
	}

	/**
	 * Handle the request for student assignment archive
	 *
	 * @param String input Student ID as string
	 */
	@Override
	public String handleRequest(String input) {
		System.out
				.println(" [x] Sending request for student assignment archive with student id = "
						+ input);
		try {
			return this.call(input);
		} catch (Exception e) {
			e.printStackTrace();
			return " [Error] Could not update student status";
		}
	}
}
