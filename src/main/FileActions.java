package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileActions {

	private File fileOut;
	private Scanner scanner = null;

	private ArrayList <Integer> values = new ArrayList<Integer>();
	private Queue queues[];
	private Client clients[];

	private int numberOfClients;
	private int numberOfQueues;
	private int maxSimulationTime;
	private int minimumArrivalTime;
	private int maximumArrivalTime;
	private int minimumServiceTime;
	private int maximumServiceTime;

	public void readFromFile(File fileIn) {

		try {
			scanner = new Scanner(fileIn); //read from file with scanner
		}
		catch(Exception e){
			System.out.println("Couldn't find file :(");
		}
		while (scanner.hasNext()) //get all input info from file
		{
			String words[] = scanner.next().split(",");
			for (String string : words)
				values.add(Integer.parseInt(string));
		}
		numberOfClients = values.get(0);
		numberOfQueues = values.get(1);
		maxSimulationTime = values.get(2);
		minimumArrivalTime = values.get(3);
		maximumArrivalTime = values.get(4);
		minimumServiceTime = values.get(5);
		maximumServiceTime = values.get(6);

		this.queues = new Queue[numberOfQueues];
		for (int index = 0; index < numberOfQueues; index++)
			queues[index] = new Queue();  //initialize queues

		this.clients = new Client[numberOfClients];
		RandomClientGenerator g = new RandomClientGenerator(minimumArrivalTime, maximumArrivalTime, minimumServiceTime, maximumServiceTime, numberOfClients);

		clients = g.generateClients(); //generate random clients
	}

	//writing in file
	public PrintWriter printInFile(File fileOut) {

		this.fileOut = fileOut;
		FileWriter fileWriter = null;
		try {
			//initialize a file writer which will be transmitted to scheduler which writes in file
			fileWriter = new FileWriter(this.fileOut);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}

		PrintWriter printWriter = new PrintWriter(fileWriter);
		return printWriter;
	}

	public Queue[] getQueues() {
		return queues;
	}

	public Client[] getClients() {
		return clients;
	}

	public int getNumberOfQueues() {
		return numberOfQueues;
	}

	public int getMaxSimulationTime() {
		return maxSimulationTime;
	}

	public int getNumberOfClients() {
		return numberOfClients;
	}
}
