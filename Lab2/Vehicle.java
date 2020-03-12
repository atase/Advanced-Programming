
/**
 *	Abstract class Vehicle
 *	@author Nastasa Petru-Alexandru
 * [OPTIONAL ABSTRACT !!! ]
**/

import java.util.Objects;

/*enum VehicleType{
	CAR, TRUCK, DRONE;
}*/


public abstract class Vehicle{
	
	/**
	 *
	 * Variable
	 *
	**/
	protected String name;
	protected Depot parentDepot;
	protected VehicleType type;


	/**
	 *
	 * Constructor
	 * @param name of vehicle
	 * @param parentDepot of vehicle
	**/
	public Vehicle(){
		this.name = null;
		this.parentDepot = null;
		this.type = VehicleType.CAR;
	}

	public Vehicle(String name){
		this.name = name;
		this.parentDepot = null;
		this.type = VehicleType.CAR;
	}

	public Vehicle(String name, Depot parentDepot){
		if(this.parentDepot != null)
			; //Error
		this.name = name;
		this.parentDepot = parentDepot;
	}

	/*public Vehicle(String name, Depot parentDepot, VehicleType type){
		if(this.parentDepot != null)
			; //Error
		this.name = name;
		this.parentDepot = parentDepot;
		this.type = type;
	}*/
	/**
	 *
	 * Abstract Overloaded toString method
	 * @return string for each type of vehicle
	**/
	public abstract String toString();


	/**
	 *
	 * setter for parent depot
	 * @param parentDepot pointer to the parent depot
	**/
	public void setParentDepot(Depot parentDepot){
		if(this.parentDepot != null)
			; //Error
		this.parentDepot = parentDepot;
	}

	/**
	 *
	 * setter for type
	 * @param type of vehicle
	**/
	public void setName(String name){
		this.name = name;
	}

	/**
	 *
	 * setter for type
	 * @param type of vehicle
	**/
	public void setVehicleType(VehicleType type){
		this.type = type;
	}


	/**
	 *
	 * getter for parent depot
	 * @return pointer to the parent depot
	**/
	public Depot getParentDepot(){
		return this.parentDepot;
	}


	/**
	 *
	 * getter for name
	 * @return string
	**/
	public String getName(){
		return this.name;
	}

	/**
	 *
	 * getter for type
	 * @return string
	**/
	public VehicleType getType(){
		return this.type;
	}


	@Override
	public boolean equals(Object obj){
		if(obj == null)
			return false;

		if(!(obj instanceof Vehicle))
			return false;

		Vehicle vehicle = (Vehicle) obj;
		return Objects.equals(this.name, vehicle.name);
	}


	@Override
	public int hashCode(){
		return Objects.hash(this.name, this.parentDepot);
	}

	
	
	/*@Override
	public String toString(){
		return this.type + " " + this.name + " " + this.parentDepot;
	}*/

	
}