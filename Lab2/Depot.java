import java.util.*;

/**
 *
 * class Depot
 * @author Nastasa Petru-Alexandru
**/

public class Depot{

	/**
	 * 
	 * Variable
	 * 
	**/
	private String name;
	private List<Vehicle> vehicles = new ArrayList<Vehicle>();
	private int vehicleCount;


	/**
	 *
	 * Constructor
	 * @param name given depot name;
	 * @param objects given depot objects;
	**/

	public Depot(){
		this.name = "";
		this.vehicleCount = 0;
	}

	public Depot(String name){
		this.name = name;
		this.vehicleCount = 0;
	}

	public Depot(String name, List<Vehicle> objects){
		this.name = name;
		this.vehicleCount = objects.size();
		for(Vehicle vehicle : objects)
			vehicles.add(vehicle);
	}


	/**
	 *
	 * Setter for depot name
	 * @param name given depot name
	**/
	public void setDepotName(String name){
		this.name = name;
	}


	/**
	 *
	 * Getter for depot name
	 * @return string
	**/
	public String getName(){
		return this.name;
	}

	/**
	 *
	 * Getter for vehicles from depot
	 * @return ArrayList
	**/
	public List<Vehicle> getVehicles(){
		if(vehicleCount != 0)
			return this.vehicles;
		return null;
	}

	/**
	 *
	 * Getter for no of vehicle from depot
	 * @return int
	**/
	public int getNoVehicles(){
		return this.vehicleCount;
	}

	@Override
	public String toString(){
		return this.name;
	}

	/**
	 *
	 * method used to check existence of vehicle
	 * @param obj pointer to the vehicle
	 * @return true if vehicle exists or false if not exists
	**/
	public boolean vehicleExists(Vehicle vehicle){
		return this.vehicles.contains(vehicle);
	}


	/**
	 *
	 * Add vehicle methods
	 * @param vehicle single vehicle
	 * @param arrayOfVehicle ArrayList of vehicles
	 * add vehicle iff not exists already in the depot
	 **/

	public void addVehicle(Vehicle vehicle){
		if(!(this.vehicleExists(vehicle)))
			vehicles.add(vehicle);
		this.vehicleCount = vehicles.size();
	}

	public void addVehicles(List<Vehicle> arrayOfVehicle){
		for(Vehicle obj : arrayOfVehicle)
			if(!(this.vehicleExists(obj)))
				vehicles.add(obj);
		this.vehicleCount = vehicles.size();
	}

}