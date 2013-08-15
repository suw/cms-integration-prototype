package edu.integration.worker;

import edu.integration.core.*;

/**
 * This class simulates an instructor requesting an archive of student submitted
 * assignments for a course. This returns an link to a web storage archive.
 *
 * @author suw <suw@suwdo.com>
 *
 */
public class StudentAssignmentArchiveWorker extends AMQPWorker implements
		Runnable {

	/**
	 * Construct the worker
	 */
	public StudentAssignmentArchiveWorker() {
		super("StudentAssignmentArchiveWorker");
	}

	/**
	 * Simulate constructing and getting the URL for a web accessible archive
	 * file with an authentication token.
	 *
	 * @param String message Simulated student identifier
	 *
	 * @return String Web download URL
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected String doTask(String message) {

		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "https://storage.somedomain.edu/storage/archive" + message
				+ "/auth=182372613KAJWmjALX";
	}

	/**
	 * Wait for task as thread
	 *
	 * @return void
	 */
	@Override
	public void run() {
		waitForTask();
	}
}
