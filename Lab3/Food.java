public class Food implements Item{

	private String name;
	private int weight;
	private int value;

	public Food(){
		this.name = null;
		this.weight = 0;
		this.value = 0;
	}

	public Food(String name){
		this.name = name;
		this.weight = 0;
		this.value = 0;
	}

	public Food(String name, int weight){
		this.name = name;
		this.weight = weight;
		this.value = 0;
	}

	public Food(String name, int weight, int value){
		this.name = name;
		this.weight = weight;
		this.value = value;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setWeight(int weight){
		this.weight = weight;
	}

	public void setValue(int value){
		this.value = value;
	}

	public String getName(){
		return this.name;
	}

	public int getWeight(){
		return this.weight;
	}

	public int getValue(){
		return this.value;
	}


	public String toString(){
		return String.format("Food -> Name : '" + this.name + "' Weight : '" + this.weight + "' Value : " + this.value + "'");
	}

}