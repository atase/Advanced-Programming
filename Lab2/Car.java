/**
 *
 * Class Car
 * @author Nastasa Petru-Alexandru
**/

class Car extends Vehicle{


	public Car(){
		super();
	}

	public Car(String name){
		super(name);
	}

	public Car(String name, Depot parentDepot){
		super(name, parentDepot);
	}

	public String toString(){
		return "car";
	}
}