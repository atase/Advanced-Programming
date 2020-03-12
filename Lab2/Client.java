/**
 *
 * class Client
 * @author Nastasa Petru-Alexandru
**/

import java.util.Objects;


public class Client{
	private String name;
	private int visitingTime;

	/**
	 *
	 * Constructor
	 * @param name given client name
	 * @param visitingTime given client visiting time 
	**/
	public Client(){
		this.name = "";
		this.visitingTime = 0;
	}

	public Client(String name){
		this.name = name;
		this.visitingTime = 0;
	}

	public Client(String name, int visitingTime){
		this.name = name;
		this.visitingTime = visitingTime;
	}

	@Override
	public String toString(){
		return this.name + " " +this.visitingTime;
	}


	@Override
	public boolean equals(Object obj){
		if(obj == null)
			return false;

		if(!(obj instanceof Client))
			return false;
	
		Client client = (Client) obj;
		return this.visitingTime == client.visitingTime && Objects.equals(this.name, client.name);
	}

	@Override
	public int hashCode(){
		return Objects.hash(this.name, this.visitingTime);
	}

	/**
	 *
	 * Setter for client name
	 * @param string
	**/
	public void setClientName(String name){
		this.name = name;
	}


	/**
	 *
	 * Setter for client visiting time
	 * @param int
	**/
	public void setVisitingTime(int visitingTime){
		this.visitingTime = visitingTime;
	}




	/**
	 *
	 * Getter for client name
	 * @return string
	**/
	public String getName(){
		return this.name;
	}

	/**
	 *
	 * Getter for client visiting time
	 * @return int
	**/
	public int getVisitingTime(){
		return this.visitingTime;
	}

}