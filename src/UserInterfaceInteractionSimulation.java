import edu.integration.core.AMQPRequester;
import edu.integration.requester.StudentAssignmentArchiveRequester;
import edu.integration.requester.StudentDataRequester;
import edu.integration.requester.StudentUpdateRequester;
import edu.integration.worker.StudentAssignmentArchiveWorker;
import edu.integration.worker.StudentDataWorker;
import edu.integration.worker.StudentUpdateWorker;

/**
 * This class simulates the interactions a set of users may have with an user
 * interface. It will send out user inputs to requesters which will then send
 * requests to the primary queue for parse out to workers. All simulation times
 * are exaggerated for dramatic reveal. (Maybe not)
 *
 * @author suw <suw@suwdo.com>
 *
 */
public class UserInterfaceInteractionSimulation {

	/**
	 * Primary starter. Prints out output to console.
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			// Start the workers
			(new Thread(new StudentDataWorker())).start();
			(new Thread(new StudentUpdateWorker())).start();
			(new Thread(new StudentAssignmentArchiveWorker())).start();

			// Send out various requests

			// Simulating a data request with the data on return
			AMQPRequester requester = new StudentDataRequester();
			String response = requester.handleRequest("81721");
			System.out.println("   >> Printing as JSON for simulation >> " + response);

			// Simulating a update request w/ success message on return
			requester = new StudentUpdateRequester();
			response = requester.handleRequest("182783");
			System.out.println("   >> " + response);

			// Simulating a large archive request with a reference to the data
			// on return
			requester = new StudentAssignmentArchiveRequester();
			response = requester.handleRequest("812723");
			System.out.println("   >> " + response);

			// Exit after all done
			System.exit(0);
		} catch (Exception e) {

			// Something horrible happened
			e.printStackTrace();
			System.exit(1);
		}
	}
}
