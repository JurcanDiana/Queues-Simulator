package main;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Scheduler extends Thread {

	private Queue queues[];
	private int numberOfQueues;
	private int numberOfClients;
	private int maxSimulation;
	private ArrayList<Client> clients = new ArrayList<Client>();

	private int currentTime = 0;
	private int numberClientsPerQueue = 0;
	private int maxClients = 0;
	private static int peakHour = 0;

	private RandomClientGenerator randomClientGenerator = new RandomClientGenerator();
	private boolean areAnyClientsLeft;
	private PrintWriter writer;

	public Scheduler(Queue queues[], int numberOfQueues, int numberOfClients, Client clients[], int maxSimulation, PrintWriter writer) {
		this.queues = queues;
		this.numberOfQueues = numberOfQueues;
		this.numberOfClients = numberOfClients;
		Collections.addAll(this.clients, clients);
		this.maxSimulation = maxSimulation;
		this.writer = writer; //file in which we write
	}

	public void run() {

		try {
			System.out.println("Thread is running...");
			while(currentTime < maxSimulation) {
				areAnyClientsLeft = false;
				writer.print("\nTime " + currentTime + "\nWaiting clients: ");

			for (Client c : clients)

				if (c.getArrivalTime() > currentTime) {
						areAnyClientsLeft = true;
						writer.print(c.toString() + " ");
				}

				//clients which do not belong to any queue
				else if (c.getArrivalTime() == currentTime) {

					int minQueue = indexMinimumQueue();  //index of queue with minimum waiting time

					if (queues[minQueue].getState().equals(Thread.State.NEW) || queues[minQueue].getState().equals(Thread.State.TERMINATED) ) //if thread didn't start
						queues[minQueue].start(); //start thread for a queue only if there are clients in it

					queues[minQueue].addClient(c);
				}

				else if (c.getArrivalTime() < currentTime && c.getQueueStatus() == true) //if arrival time is smaller than time and the client belongs to a queue
					c.incrementWaitingTime();//increment waiting time of client

			writer.print("\n");

			numberClientsPerQueue = 0;

			for (int j = 0; j < numberOfQueues; j++) {

				numberClientsPerQueue += queues[j].getNumberOfClients();

				writer.println("Queue " + (j + 1) + queues[j].status());  //write the content of a queue

				//compute peak hour
				if(numberClientsPerQueue >= maxClients) {
					maxClients = numberClientsPerQueue;
					peakHour = currentTime;
				}
			}

			sleep(1000); //a second
			writer.print("\n");
			System.out.println("TIME: " + currentTime);

			currentTime++;

			if (!areAnyClientsLeft && emptyQueues() || currentTime == maxSimulation) {
				//if there are clients in the queue with arrivalTime > time si queues have nothing to process, terminate app

				///before we close the queue, we compute the average waiting time
				writer.println("\n\nAverage waiting time: " + randomClientGenerator.getTotalWaitingTime() / numberOfClients);

				//the average service time
				writer.println("Average service time: " + randomClientGenerator.getTotalServiceTime() / numberOfClients);

				//peak time
				writer.println("Peak time: " + peakHour);

				interrupt();
				System.out.println("Thread has been interrupted.");//interrupt thread and break

				writer.close();//close file
				System.exit(0);
				break;
			}
		}
	}
	catch(InterruptedException e) {
		 System.out.println(e.toString());
	}
}

	//returns index of queue with minimum waiting time
	public int indexMinimumQueue() throws InterruptedException {
		int aux = queues[0].getWaitingTime();
		int min = 0;
		for (int i = 1; i < numberOfQueues; i++)
			if (queues[i].getWaitingTime() < aux)
			{
				aux = queues[i].getWaitingTime();
				min = i;
			}
		return min;
	}

	//verifies if there are any clients left in the queues
	public boolean emptyQueues() {
		boolean flag = true;
		for (Queue c : queues)
			if (c.getNumberOfClients() != 0)
				flag = false;
		return flag;
	}
}
