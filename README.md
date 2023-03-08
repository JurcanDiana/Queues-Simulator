# Queues-Simulator

This project is a GUI application developed using Java Swing. It simulates N clients arriving for service, entering Q queues, waiting, being served, and then exiting the queues.\
When the simulation begins, the clients are generated at random and have three parameters: an ID (a number between 1 and N), an arrival time (the simulation time at which they are ready to enter the queue), and a service time (the time interval or duration needed to serve the client).\
The program calculates the average waiting time by tracking the total time spent by each client in the queues. When a client's arrival time exceeds the simulation time, they are added to the queue with the shortest waiting time. The implementation is done using threads and synchronization mechanisms.
