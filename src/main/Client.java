package main;

public class Client {

	private int clientID;
	private int arrivalTime;
	private int serviceTime;
	private int waitingTime = 1;
	private boolean queueStatus;

	public Client(int clientID, int arrivalTime, int serviceTime) {
		this.clientID = clientID + 1;
		this.arrivalTime = arrivalTime;
		this.serviceTime = serviceTime;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public int getServiceTime() {
		return serviceTime;
	}

	public void decrementServiceTime() {
		serviceTime--;
	}

	public int getWaitingTime() {
		return waitingTime;
	}

	public void incrementWaitingTime() {
		waitingTime++;
	}

	public boolean getQueueStatus() {
		return queueStatus;
	}

	public void setQueueStatus(boolean status) {
		queueStatus = status;
	}

	public String toString() {
		return "(" + clientID +
				", " + arrivalTime +
				", " + serviceTime + ")";
	}
}
