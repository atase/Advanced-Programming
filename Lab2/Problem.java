import java.util.*;

/*enum VehicleType{
	CAR,
	TRUCK,
	DRONE
}*/


public class Problem{

	public List<Vehicle> getVehicles(List<Depot> depots){
		
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		for(Depot depot : depots){
			List<Vehicle> vehiclesDepot = depot.getVehicles();
			if(vehiclesDepot != null)
				for(Vehicle vehicle : vehiclesDepot)
					vehicles.add(vehicle);
		}

		return vehicles;
	}


	public static void main(String[] args){

		Client client1 = new Client("Client1", 1);
		Client client2 = new Client("Client2", 2);
		Client client3 = new Client("Client3", 3);
		Client client4 = new Client("Client4", 4);
		Client client5 = new Client("Client5", 1);
		Client client6 = new Client("Client6", 3);
		Client client7 = new Client("Client7", 2);

		List<Client> clients = new ArrayList<Client>();
		clients.add(client1);
		clients.add(client2);
		clients.add(client3);
		clients.add(client4);
		clients.add(client5);
		clients.add(client6);
		clients.add(client7);

		
		Car vehicle1 = new Car("Car1");
		Truck vehicle2 = new Truck("Truck1");

		Depot depot1 = new Depot("Depot1");
		Depot depot2 = new Depot("Depot2");
		
		depot1.addVehicle(vehicle1);
		depot2.addVehicle(vehicle2);

		vehicle1.setParentDepot(depot1);
		vehicle2.setParentDepot(depot2);

		List<Depot> depots = new ArrayList<Depot>();
		depots.add(depot1);
		depots.add(depot2);


		Solution solution = new Solution(depots, clients);

		System.out.println(solution.toString());

	}
}