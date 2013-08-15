package edu.integration.worker;

import edu.integration.core.*;

/**
 * This class simulates the updating of a student with a particular student id
 * from undergraduate status to graduate status.
 *
 * @author suw <suw@suwdo.com>
 *
 */
public class StudentUpdateWorker extends AMQPWorker implements Runnable {

	/**
	 * Construct the worker
	 */
	public StudentUpdateWorker() {
		super("StudentUpdateWorker");
	}

	/**
	 * Simulate the updating of a student to graduate status based on
	 * student ID input.
	 *
	 * @param String message Student ID
	 *
	 * @return String Success message
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected String doTask(String message) {

		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "Student with student id = " + message
				+ " updated to graduate status";
	}

	/**
	 * Wait for task as thread
	 */
	@Override
	public void run() {
		waitForTask();
	}
}
