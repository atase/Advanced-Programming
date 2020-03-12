/**
 *
 * Class Drones
 * @author Nastasa Petru-Alexandru
**/

class Drone extends Vehicle{

	public Drone(){
		super();
	}

	public Drone(String name){
		super(name);
	}

	public Drone(String name, Depot parentDepot){
		super(name, parentDepot);
	}

	public String toString(){
		return "Drone";
	}
}