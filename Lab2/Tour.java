/**
 *
 * Tour class
 *	@author Nastasa Petru-Alexandru
**/


import java.util.*;

public class Tour{

	private Vehicle vehicle;
	private List<Client> clients;
	private int clientCount;


	/**
	 *
	 * Constructor
	 *
	**/
	public Tour(){
		this.vehicle = null;
		this.clients = new ArrayList<Client>();
		this.clientCount = 0;
	}

	public Tour(Vehicle vehicle){
		this.vehicle = vehicle;
		this.clients = new ArrayList<Client>();
		this. clientCount = 0;
	}

	public Tour(List<Client> arrayOfClients){
		this.vehicle = vehicle;
		this.clients = arrayOfClients;
		this.clientCount = arrayOfClients.size();
	}


	public void addClient(Client client){
		if(!this.clientExists(client))
			clients.add(client);
	}

	/**
	 *
	 * Add clients - check existence of clients
	 * @param arrayOfClients - arraylist of clients
	 *
	**/
	public void addClients(List<Client> arrayOfClients){
		for(Client client : arrayOfClients)
			if(!this.clientExists(client))
				clients.add(client);

		/*if(!this.checkTour())
			;//Error*/
	}

	/**
	 *
	 * Getter for clients array
	 * @return ArrayList
	**/
	public List<Client> getClients(){
		return this.clients;
	}

	/**
	 *
	 * Getter for vehicle
	 * @return vehicle
	**/
	public Vehicle getVehicle(){
		return this.vehicle;
	}



	/**
	 *
	 * Setter for vehicle
	 * @param vehicle 
	**/
	public void assignVehicle(Vehicle vehicle){
		this.vehicle = vehicle;
	}


	/**
	 *
	 * Setter for clients
	 * @param arrayOfClients
	**/
	public void assignClients(List<Client> arrayOfClients){
		this.clients = arrayOfClients;
	}



	/**
	 *
	 * Method to check validity of a tour
	 * @return true if tour is valid, false otherwise
	**/
	/*private boolean checkTour(){
		for(int i=0; i<clients.size()-1; i++)
			if(clients.get(i).getVisitingTime() >= clients.get(i+1).getVisitingTime())
				return false;
		return true;
	}*/
	/**
	 *
	 * Client existence
	 * @param obj
	 **/
	public boolean clientExists(Client obj){
		return this.clients.contains(obj);
	}

	@Override
	public String toString(){

		StringBuilder sb = new StringBuilder();
		sb.append(this.vehicle.getName());
		sb.append(" -> ");
		sb.append(this.vehicle);
		sb.append(":\n");

		sb.append(this.vehicle.getParentDepot().getName());
		sb.append(" -> ");

		for(Client client : clients){
			sb.append(client.getName());
			sb.append(" -> ");
		}


		sb.append(this.vehicle.getParentDepot().getName());
		sb.append("\n");

		return sb.toString();
		
	}

}