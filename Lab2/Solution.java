/**
 * Class Solution
 *	@author Nastasa Petru-Alexandru
 *
 *
*/

import java.util.*;

public class Solution{

	/**
	 * @param list of clients
	 * @param list of depots
	 * @param list of tours
	**/

	private List<Depot> depots;
	private List<Client> clients;
	private List<Tour> tours;


	/**
	 *
	 * Default constructor
	 *
	**/

	public Solution(){
		this.depots = null;
		this.clients = null;
	}

	/**
	 *
	 * Constructor with params
	 * @param objectsDepot list of depots
	 * @param objectsClients list of clients
	**/

	public Solution(List<Depot> objectsDepot,  List<Client> objectsClient){

		this.depots = new ArrayList<Depot>();
		this.clients = new ArrayList<Client>();

		for(Depot depot : objectsDepot)
			this.depots.add(depot);

		for(Client client : objectsClient)
			this.clients.add(client);

		
	}

	/**
	 *
	 * Setter for depots
	 * @param objects list of depots
	**/
	public void setDepots(List<Depot> objects){
		if(this.depots != null){
			depots.clear();
			depots.addAll(objects);
		}else{
			this.depots = new ArrayList<Depot>(objects);
		}

	}



	/**
	 *
	 * Setter for clients
	 * @param objects list of clients
	**/
	public void setClients(List<Client> objects){
		if(this.clients != null){
			clients.clear();
			clients.addAll(objects);
		}else{
			this.clients = new ArrayList<Client>(objects);
		}
	}

	/**
	 *
	 * Getter for depots implied in solution computation
	 * @return list of depots
	**/
	public List<Depot> getDepots(){
		return this.depots;
	}

	/**
	 *
	 * Getter for clients implied in solution computation
	 * @return list of client
	**/
	public List<Client> getClients(){
		return this.clients;
	}


	/**
	 *
	 * Compute solution for list of depots and list of clients
	 *  - for every depot, get a list of vehicles
	 *  - for every vehicle,  try to mark some clients
	 *  - if all clients are marked after all vehicle are included in tours then we have a solution
	 * @return list of tours if solution is complete, null otherwise
	**/

	public List<Tour> computeSolution(){

		List<Integer> markClient = new ArrayList<Integer>();

		if(this.depots != null && this.clients != null){
			this.clients = this.sortClients();
			this.tours = new ArrayList<Tour>();
			
			for(Depot depot : depots){
				
				List<Vehicle> vehicles = depot.getVehicles();
				if(vehicles != null){	


					for(Vehicle vehicle : vehicles){

						Tour tour = new Tour(vehicle);	
						Integer lastClientTime = 0;
						for(Client client : clients)
							if(!markClient.contains(this.clients.indexOf(client))){
								if(client.getVisitingTime() > lastClientTime){
									markClient.add(this.clients.indexOf(client));
									tour.addClient(client);
									lastClientTime = client.getVisitingTime();
								}		
							}
						this.tours.add(tour);

					}

				}

			}
		}

		for(Client client : clients)
			if(!markClient.contains(this.clients.indexOf(client)))
				return null;

		return this.tours;
	}


	/**
	 *
	 * function for sort clients after visiting time
	 * @return list of sorted clients
	 *
	**/

	public List<Client> sortClients(){
		if(this.clients != null){
			for(int i = 0; i < clients.size(); i++)
				for(int j = 0; j < clients.size(); j++)
					if(clients.get(i).getVisitingTime() <= clients.get(j).getVisitingTime())
						Collections.swap(clients, i, j);
			
		}
		return this.clients;
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();

		if(this.depots != null && this.clients != null){
			sb.append("*****[ Data problem ] : \n");
			for(Depot depot : depots){
				sb.append(depot.toString());
				sb.append("\n");
				List<Vehicle> vehicles = depot.getVehicles();
				if(vehicles == null)
					sb.append("No vehicles ! \n");
				else{
					for(Vehicle vehicle : vehicles){
						sb.append(vehicle.toString());
						sb.append("\n");
					}
				}
			}

			for(Client client : clients)
				sb.append(client.toString() + " ");

			sb.append("\n\n*****[ Problem ] : \n");
			this.tours = computeSolution();
			if(this.tours == null){
				sb.append("No solution ! \n");
			}else{
				for(Tour tour : tours)
					sb.append(tour.toString());
			}

		}

		return sb.toString();

	}


}