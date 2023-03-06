package main;

import java.util.ArrayList;

public class Queue extends Thread {

	private ArrayList<Client> clients = new ArrayList<Client>();
	private int waitingTime = 0;

	public void run() {
		try {
			while (!Thread.currentThread().isInterrupted()) {

				sleep(1000);
				serveClient();
			}
		}
		catch (InterruptedException e)
		{
			System.out.println("Interrupted.");
		}
	}

	public synchronized void serveClient() throws InterruptedException
	{
		while (clients.size() == 0) //if there are no clients in the queue, wait
			wait();

		if (clients.get(0).getServiceTime() > 1) //if service time for first client is != 0
		{
			clients.get(0).decrementServiceTime();
			waitingTime--; //decrement service time and waiting time for the queue
		}
		else
		{
			clients.get(0).setQueueStatus(false); //set status of client to false(it doesn't belong to any queue)
			clients.remove(0); //remove client from queue
			waitingTime--;

			if (clients.size() == 0)
				wait(); //if it was the last client, end thread
		}
		notifyAll(); //notify classes that wait
	}

	public synchronized void addClient(Client c) throws InterruptedException {

		c.setQueueStatus(true);
		clients.add(c);
		waitingTime += c.getServiceTime();
		notifyAll();
	}

	public String status() {
		if (clients.size() == 0)
			return ": closed";

		String currentClients = ": ";
		for (Client c : clients)
			currentClients += c.toString();
		return currentClients;
	}

	public int getNumberOfClients() {
		return clients.size();
	}

	public ArrayList<Client> getClients() {
		return clients;
	}

	public int getWaitingTime() {
		return waitingTime;
	}

}
