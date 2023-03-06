package main;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main( String[] args ) {

		//possible files
		File fileIn1 = new File("./src/testfiles/in-test-1.txt");
		File fileIn2 = new File("./src/testfiles/in-test-2.txt");
		File fileIn3 = new File("./src/testfiles/in-test-3.txt");

		File fileOut1 = new File("./src/testfiles/out-test-1.txt");
		File fileOut2 = new File("./src/testfiles/out-test-2.txt");
		File fileOut3 = new File("./src/testfiles/out-test-3.txt");

		//user input
		System.out.println("Choose an option between: \n \t1. Test 1\n" +
				"\t2. Test 2\n\t3. Test 3\n" +
				"Please only type the digit associated with your answer.");

		Scanner sc = new Scanner(System.in);
		int option = sc.nextInt();

		FileActions action = new FileActions();

		//choosing file
		switch(option) {
			case 1:
				action.readFromFile(fileIn1);

				Scheduler scheduler1 = new Scheduler(action.getQueues(),
						action.getNumberOfQueues(),
						action.getNumberOfClients(),
						action.getClients(),
						action.getMaxSimulationTime(),
						action.printInFile(fileOut1));

				scheduler1.start();
				break;
			case 2:
				action.readFromFile(fileIn2);

				Scheduler scheduler2 = new Scheduler(action.getQueues(),
						action.getNumberOfQueues(),
						action.getNumberOfClients(),
						action.getClients(),
						action.getMaxSimulationTime(),
						action.printInFile(fileOut2));

				scheduler2.start();
				break;
			case 3:
				action.readFromFile(fileIn3);

				Scheduler scheduler3 = new Scheduler(action.getQueues(),
						action.getNumberOfQueues(),
						action.getNumberOfClients(),
						action.getClients(),
						action.getMaxSimulationTime(),
						action.printInFile(fileOut3));

				scheduler3.start();
				break;
			default:
				System.out.println("Wrong input.\nPlease type a digit between 1, 2 and 3.");
		}
     }
}
