import java.util.List;
import java.util.ArrayList;

public class Knapsack{
	
	private String name;
	private int capacity; 
	private List<Object> container;
	private int numberOfItems;
	private int totalWeight;
	private int totalValue;
	private float totalProfit;


	public Knapsack(){
		this.name = null;
		this.capacity = 0;
		this.container = new ArrayList<Object>();
		this.numberOfItems = 0;
		this.totalWeight = 0;
		this.totalValue = 0;
		this.totalProfit = 0;
	}

	public Knapsack(String name){
		this.name = name;
		this.capacity = 0;
		this.container = new ArrayList<Object>();
		this.numberOfItems = 0;
		this.totalWeight = 0;
		this.totalValue = 0;
		this.totalProfit = 0;
	}

	public Knapsack(String name, int capacity){
		this.name = name;
		this.capacity = capacity;
		this.container = new ArrayList<Object>();
		this.numberOfItems = 0;
		this.totalWeight = 0;
		this.totalValue = 0;
		this.totalProfit = 0;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setCapacity(int capacity){
		this.capacity = capacity;
	}

	public int getCapacity(){
		return this.capacity;
	}

	public String getName(){
		return this.name;
	}

	public int getSize(){
		return this.numberOfItems;	
	}


	public List<Object> getObjects(){
		return this.container;
	}

	public void addObject(Object obj){
		if(obj instanceof Book && this.totalWeight + ((Book)obj).getWeight() <= this.capacity){
			
			this.container.add(obj);
			this.numberOfItems = this.container.size();

			this.totalWeight = this.totalWeight + ((Book)obj).getWeight();
			this.totalValue = this.totalValue + ((Book)obj).getValue();
			this.totalProfit = this.totalProfit + ((Book)obj).profitFactor((float)((Book)obj).getValue(), (float)((Book)obj).getWeight()); 
		
		}else if(obj instanceof Food && this.totalWeight + ((Food)obj).getWeight() <= this.capacity){
			
			this.container.add(obj);
			this.numberOfItems = this.container.size();

			this.totalWeight = this.totalWeight + ((Food)obj).getWeight();
			this.totalValue = this.totalValue + ((Food)obj).getValue(); 
			this.totalProfit = this.totalProfit + ((Food)obj).profitFactor((float)((Food)obj).getValue(), (float)((Food)obj).getWeight());
		
		}else if(obj instanceof Weapon && this.totalWeight + ((Weapon)obj).getWeight() <= this.capacity){
	
			this.container.add(obj);
			this.numberOfItems = this.container.size();

			this.totalWeight = this.totalWeight + ((Weapon)obj).getWeight();
			this.totalValue = this.totalValue + ((Weapon)obj).getValue(); 
			this.totalProfit = this.totalProfit + ((Weapon)obj).profitFactor((float)((Weapon)obj).getValue(), (float)((Weapon)obj).getWeight());
		}
	}

	public void addBooks(List<Book> books){
		for(Object item : books){
			if(((Weapon)item).getWeight() + this.totalWeight <= this.capacity){
				container.add(item);
				this.totalWeight = this.totalWeight + ((Book)item).getWeight();
				this.totalValue = this.totalValue + ((Book)item).getValue(); 
				this.totalProfit = this.totalProfit + ((Book)item).profitFactor((float)((Book)item).getValue(), (float)((Book)item).getWeight());
			}
		}
		
		this.numberOfItems = this.container.size();
	}

	public void addFoods(List<Food> foods){
		for(Object item : foods){
			if(((Weapon)item).getWeight() + this.totalWeight <= this.capacity){
				container.add(item);
				this.totalWeight = this.totalWeight + ((Food)item).getWeight();
				this.totalValue = this.totalValue + ((Food)item).getValue();
				this.totalProfit = this.totalProfit + ((Food)item).profitFactor((float)((Food)item).getValue(), (float)((Food)item).getWeight());
			}
		}
		
		this.numberOfItems = this.container.size();
	}

	public void addWeapons(List<Weapon> weapons){
		for(Object item : weapons){
			if(((Weapon)item).getWeight() + this.totalWeight <= this.capacity){
				container.add(item);
				this.totalWeight = this.totalWeight + ((Weapon)item).getWeight();
				this.totalValue = this.totalValue + ((Weapon)item).getValue();
				this.totalProfit = this.totalProfit + ((Weapon)item).profitFactor((float)((Weapon)item).getValue(), (float)((Weapon)item).getWeight()); 
			}
		}
		
		this.numberOfItems = this.container.size();
	}

	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("Knapsack-> Name : '" + this.name + "' Capacity: '" + this.capacity + "'");

		if(this.numberOfItems != 0){
			str.append("\nObjects : \n");

			for(Object item : container){
				if(item instanceof Book){
					str.append("Book : " + ((Book)item).getName());
				}else if(item instanceof Food){
					str.append("Food : " + ((Food)item).getName());
				}else if(item instanceof Weapon){
					str.append("Weapon : " + ((Weapon)item).getName());
				}
				
				str.append("\n");
			}	
		}

		return str.toString();
	}

	public String getTotal(){

		StringBuilder str = new StringBuilder();
		str.append("[ TOTAL ]\n");	
		if(this.numberOfItems == 0){
			str.append("No items in knapsack ! ");
			str.append("\nCapacity : " + this.capacity);
			str.append("\nWeight : 0");
			str.append("\nValue : 0");
			str.append("\nProfit : 0");
		}else{
			str.append("Number of items in knapsack : " + this.numberOfItems);
			str.append("\nCapacity : " + this.capacity);
			str.append("\nWeight : " + this.totalWeight);
			str.append("\nValue : " + this.totalValue);

			str.append("\nProfit : " + this.totalProfit);
		}

		return str.toString();

	}

	public float totalProfit(){
		return ((float)this.totalValue / (float)this.totalWeight);
	}

}