package p2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import p2.logging.Station;
import p2.ts.TrainSystem;
import p2.enums.SimulatorStatus;
import p2.enums.SystemStatus;
import p2.event.Event;
import p2.interfaces.SimulatorInterface;
import p2.logging.Logable;

public class Simulator extends Logable implements SimulatorInterface {

	private int currentTime = -1;
	private SimulatorStatus status = SimulatorStatus.Uninitialised;
	private TrainSystem trainSystem = new TrainSystem();
	private Scanner file;
	private ArrayList<String> flaggedEvents = new ArrayList<String>();

	public Simulator(String initialisationFile) throws FileNotFoundException {
		initialise(initialisationFile);
		setInitialised();
	}

	private void initialise(String initialisationFile) throws FileNotFoundException {

		if (status == SimulatorStatus.Uninitialised || status == SimulatorStatus.Initialised) {

			// create scanner to read from file
			file = new Scanner(new File(initialisationFile));

			/*
			 * use a loop to read in the initialisation data
			 * 
			 * remember to only use the try-catch-finally constructs to catch and recover
			 * from exceptions
			 * 
			 * create stations
			 * 
			 * create segments
			 * 
			 * create routes
			 * 
			 * process open events, close events, and create and register trains
			 * 
			 * ensure open and close events get added to the log
			 */
			int x = 0;
			// this.currentTime = 1;
			Scanner scan = new Scanner(System.in);
			String cont;
			while (file.hasNext()) {
				try {
					ArrayList<String> segListForStops = new ArrayList<String>();
					String line = file.next();
					System.out.println(line);
					if (x == 0) {
						int currTime = Integer.parseInt(line);
						x = 1;
						continue;
					}

					if (line.contains("Stations")) {
						int next = Integer.parseInt(file.next());
						for (int index = 0; index < (next + 1); index++) {
							String stringCheck = file.nextLine();
							if (stringCheck.equalsIgnoreCase(" ") || stringCheck.equalsIgnoreCase("")) {
								System.out.println("Line is empty : " + stringCheck + "\n");
								continue;
							}
							String nextLine = stringCheck;
							if (!nextLine.equals(" ") && !nextLine.equals("")) {
								this.trainSystem.addStation(nextLine);
							}
						}
						System.out.println("Stations have been created successfully\n");
						System.out.println("\nPress ENTER to continue reading . . . \n");
						cont = scan.nextLine();
						continue;
					}

					if (line.contains("Segments")) {
						int next = Integer.parseInt(file.next());
						for (int i = 0; i < (next + 1); i++) {
							String stringCheck = file.nextLine();
							if (stringCheck.equalsIgnoreCase(" ") || stringCheck.equalsIgnoreCase("")) {
								System.out.println("Line is empty : " + stringCheck + "\n");

								continue;
							}
							String split[] = stringCheck.split(":");
							String segmentName = "";
							String startStation = "";
							String endStation = "";
							for (int index = 0; index < split.length; index++) {
								if (!split[index].equalsIgnoreCase(" ") || !split[index].equalsIgnoreCase("")) {
									if (index == 0) {
										segmentName = split[index].toString();
										segListForStops.add(segmentName);
									} else if (index == 1) {
										startStation = split[index].toString();
									} else {
										endStation = split[index].toString();
									}
								}
							}
							this.trainSystem.addSegment(segmentName, startStation, endStation);

						}
						System.out.println("\nPress ENTER to continue reading . . . \n");
						cont = scan.nextLine();
						continue;
					}

					if (line.contains("Routes")) {
						int next = Integer.parseInt(file.next());
						for (int index = 0; index < (next + 1); index++) {
							String stringCheck = file.nextLine();
							if (stringCheck.equalsIgnoreCase("") || stringCheck.equalsIgnoreCase(" ")) {
								System.out.println("Line is empty : '" + stringCheck + "'\n");
								continue;
							}
							String split[] = stringCheck.split(":");
							String routeName = "";
							Boolean isRoundTrip = false;
							ArrayList<String> segs = new ArrayList<String>();
							for (int i = 0; i < split.length; i++) {
								if (!split[i].equalsIgnoreCase(" ") || !split[i].equalsIgnoreCase("")) {
									if (i == 0) {
										routeName = split[i].toString();
									} else if (i == 1) {
										if (split[i].equalsIgnoreCase("false")) {
											isRoundTrip = false;
										} else {
											isRoundTrip = true;
										}
									} else {
										String stopsString[] = split[i].toString().split(";");
										for (String stp : stopsString) {
											segs.add(stp.toString().replace(" ", ""));
											System.out.println("Stop '" + stp.replace(" ", "")
													+ "' has been added to the 'stoplist' \n");
										}
									}
								}
							}
							this.trainSystem.addRoute(routeName, isRoundTrip, segs);
						}
						System.out.println("\nPress ENTER to continue reading . . . \n");
						cont = scan.nextLine();
						continue;

					}

					if (line.contains("Trains")) {
						int next = Integer.parseInt(file.next());
						for (int i = 0; i < (next + 1); i++) {
							String stringCheck = file.nextLine();
							if (stringCheck.equalsIgnoreCase("") || stringCheck.equalsIgnoreCase(" ")) {
								System.out.println("Line is empty : " + stringCheck + "\n");
								continue;
							}
							String split[] = stringCheck.split(":");
							String trainName = "";
							int startTime = 0;
							String trainRoute = "";
							ArrayList<String> stops = new ArrayList<String>();
							for (int index = 0; index < split.length; index++) {
								if (index == 0) {
									trainName = split[index].toString();
								} else if (index == 1) {
									startTime = Integer.parseInt(split[index].toString());
								} else if (index == 2) {
									trainRoute = split[index].toString();
								} else {
									if (split[index].equalsIgnoreCase("all")) {
										stops = segListForStops;
									} else {
										String splitString[] = split[index].split(";");
										for (String str : splitString) {
											stops.add(str);
										}
									}
								}
							}
							this.trainSystem.setToWorking();
							this.trainSystem.addTrain(trainName, startTime);
							this.trainSystem.registerTrain(trainName, trainRoute, stops);
						}
						System.out.println("\nPress ENTER to continue reading . . . \n");
						cont = scan.nextLine();
						continue;
					}

					if (line.contains("Events")) {
						int next = Integer.parseInt(file.next());
						for (int i = 0; i < (next + 1); i++) {
							String stringCheck = file.nextLine();
							if (stringCheck.equalsIgnoreCase(" ") || stringCheck.equalsIgnoreCase("")) {
								System.out.println("Line is empty: " + stringCheck + "\n");
							}
							String split[] = stringCheck.trim().split(":");
							System.out.println(Arrays.toString(split));
							String eventType = "";
							String objType = "";
							String objId = "";
							for (int index = 0; index < split.length; index++) {
								if (split[index].equalsIgnoreCase("Open")) {
									eventType = "Open";
								}
								if (split[index].equalsIgnoreCase("Close")) {
									eventType = "Close";
								}

								if (split[index].equalsIgnoreCase("Advance")) {
									eventType = "Advance";
								}

								if (index == 1) {
									objType = split[index].toString();
								} else if (index == 2) {
									objId = split[index].toString();
								}

							}
							if (eventType.equalsIgnoreCase("Open")) {
								if (objType.equalsIgnoreCase("Segment")) {
									this.trainSystem.openSegment(objId);
								}
							} else if (eventType.equalsIgnoreCase("Close")) {
								if (objType.equalsIgnoreCase("Segment")) {
									this.trainSystem.closeSegment(objId);
								} else if (objType.equalsIgnoreCase("all")) {
									this.trainSystem.closeAll(objId);
								}
							} else if (eventType.equalsIgnoreCase("Change Light")) {
								if (objType.equalsIgnoreCase("Segment")) {
									System.out.println("Change light Event was recognized but not accounted for.\n");
								}
							} else if (eventType.equalsIgnoreCase("Advance")) {
								this.trainSystem.setToWorking();
								this.trainSystem.advance();
							}
						}
						System.out.println("\nPress ENTER to continue reading . . . \n");
						cont = scan.nextLine();
						continue;
					}

					this.currentTime = Integer.parseInt(line);

					System.out.println("Current time is : " + this.currentTime + "\n");

				} catch (NumberFormatException n) {
					System.out.println(
							"There may be a problem with input file spacing.\nEnsure spaces that are not necessary are removed.\n");
					System.out.println("Exception handled . . . ");

				} catch (Exception e) {
					System.err.println(e);
					System.out.println(Arrays.toString(e.getStackTrace()));
					System.out.println("\nSomething went wrong while reading line of file\n");
					System.out.println("Error handled . . .\nContinuing reading file . . .\n");

				} finally {

				}
			}
			System.out.println("\nEnd of file\n");
		}

		// otherwise do nothing as the simulation has started.
	}

	/*
	 * Simulator and initialiseFixed provided just to test your methods without
	 * having to read the input from the file
	 */
	public Simulator() throws FileNotFoundException {
		initialiseFixed();
		setInitialised();
	}

	private void initialiseFixed() {

		if (status == SimulatorStatus.Uninitialised || status == SimulatorStatus.Initialised) {

			trainSystem.addStation("Stationx");
			trainSystem.addStation("Alberb");
			trainSystem.addStation("Trusty");
			trainSystem.addStation("1tationx");

			trainSystem.addSegment("Seg1", "Stationx", "Alberg");
			trainSystem.addSegment("Seg2", "Alberg", "Trusty");
			trainSystem.addSegment("Seg3", "Trusty", "1tationx");

			ArrayList<String> rs = new ArrayList<String>();
			rs.add("Seg1");
			rs.add("Seg2");
			rs.add("Seg3");
			trainSystem.addRoute("R1", false, rs);

			trainSystem.addTrain("train_1", 2);
			trainSystem.addTrain("train_2", 1);

			ArrayList<String> stops = new ArrayList<String>();
			stops.add("Alberg");
			trainSystem.registerTrain("train_1", "R1", stops);
			stops.remove(0);
			stops.add("Trusty");
			trainSystem.registerTrain("train_2", "R1", stops);

		}

		// otherwise do nothing as the simulation has started.
	}

	public int getCurrentTime() {
		return currentTime;
	}

	private void setInitialised() {
		this.status = SimulatorStatus.Initialised;
	}

	@Override
	public boolean isFinished() {
		return trainSystem.currentStatus() == SystemStatus.Finished;
	}

	@Override
	public void simulate() {
		if (status == SimulatorStatus.Initialised) {
			currentTime = 0;
			status = SimulatorStatus.Working;
		}

		/*
		 * start a loop here and advance the train system until it is completed.
		 */

		if (status == SimulatorStatus.Working) {
			currentTime++;

			// check file for additional trains or events at the currenttTime instant and
			// process them

			// tell the trainSystem to advance
			ArrayList<Event> events = trainSystem.advance();

			/* process the events */
			for (Event e : events) {
				if (e.getTime() != currentTime)
					flaggedEvents.add(e.toString());
				addToLog(e);
			}

			/*
			 * Keep in mind:
			 * 
			 * 1. when the train system can no longer advance, because there are closures
			 * hindering movement and there are no other input in the file to process, end
			 * the loop and call the appropriate method in the train system to finish the
			 * execution/simulation
			 * 
			 */

		} else {
			/* do nothing... */
		}

		// loop ends

		// outside the loop, close the scanner
		if (file != null)
			file.close();
	}

	// each worth 4 marks

	@Override
	public boolean validate() {
		boolean isValidExecution = true;

		isValidExecution = isValidExecution && super.validate();

		/*
		 * From here, see notes in superclass on printing what you are doing
		 */
		isValidExecution = isValidExecution && flaggedEvents.size() > 0;

		/*
		 * get all distinct objects from log, filter log to get events for each object
		 * ask train system to validate against the log of the object and use the value
		 * returned to update the isValidExecution variable.
		 */

		return isValidExecution;
	}

	@Override
	public String toString() {
		String str = "";
		str += helperString(str) + "\n";
		str += "--- Events --\n";
		str += logSize() == 0 ? " \tno events" : "";
		for (String object : getObjects()) {
			str += "Object=[" + object + ", events=" + logSize() + "]\n";
			for (String event : getEvents(object))
				str += "\t" + event + "\n";
		}
		str = helperString2(str);
		return str;
	}

	public String toShortString() {
		String str = "";
		str = helperString(str);
		str += "There are " + logSize() + " events with " + distinctObjects() + " distinct objects.\n";
		str = helperString2(str);
		return str;
	}

	private String helperString(String str) {
		str += "The current time instant is: " + currentTime + "\n";
		str += "The current status is: " + status.getDescription() + "\n";
		return str;
	}

	private String helperString2(String str) {
		str += currentTime <= 0 ? "\nNothing to validate as yet."
				: "\n\nValidation has " + (validate() ? "passed" : "failed");
		return str;
	}

	/*
	 * just a tester for you, will not be graded your UI/GUI class should be a
	 * different class that calls the methods in the simulator class.
	 */
	public static void main(String[] args) throws FileNotFoundException {

		// if (args.length > 0)
		// simulator = new Simulator(args[0]);
		// else
		// simulator = new Simulator();

		// simulator.simulate();
		// simulator.validate();
		// System.out.println(simulator.toShortString());
		// System.out.println(simulator); // uncomment to print all the events
	}
}
