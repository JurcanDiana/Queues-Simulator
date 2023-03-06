package main;
import java.util.Random;

public class RandomClientGenerator {

	private int minimumArrivalTime;
	private int maximumArrivalTime;
	private int minimumServiceTime;
	private int maximumServiceTime;
	private int numberOfClients;
	private static float totalServiceTime;
	private static float totalWaitingTime;

	public RandomClientGenerator() {
	}

	public RandomClientGenerator(int minimumArrivalTime, int maximumArrivalTime, int minimumServiceTime, int maximumServiceTime, int numberOfClients) {
		this.minimumArrivalTime = minimumArrivalTime;
		this.maximumArrivalTime = maximumArrivalTime;
		this.minimumServiceTime = minimumServiceTime;
		this.maximumServiceTime = maximumServiceTime;
		this.numberOfClients = numberOfClients;
	}

	public Client[] generateClients()
	{
		Client clients[] = new Client[numberOfClients];
		int randomArrival, randomService;

		totalServiceTime = 0;
		totalWaitingTime = 0;

		for (int index = 0; index < numberOfClients; index++)
		{
			Random r = new Random();
			randomArrival = r.nextInt((maximumArrivalTime - minimumArrivalTime) + 1) + minimumArrivalTime;
			randomService = r.nextInt((maximumServiceTime - minimumServiceTime) + 1) + minimumServiceTime;
			clients[index] = new Client(index, randomArrival, randomService);

			totalServiceTime += randomService;
			totalWaitingTime += randomArrival;
		}
		return clients;
	}

	public float getTotalServiceTime() {
		return totalServiceTime;
	}

	public float getTotalWaitingTime() {
		return totalWaitingTime;
	}
}
