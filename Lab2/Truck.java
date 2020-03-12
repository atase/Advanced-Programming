/**
 *
 * Class Truck
 * @author Nastasa Petru-Alexandru
**/

class Truck extends Vehicle{

	public Truck(){
		super();
	}

	public Truck(String name){
		super(name);
	}

	public Truck(String name, Depot parentDepot){
		super(name, parentDepot);
	}

	public String toString(){
		return "Truck";
	}
}