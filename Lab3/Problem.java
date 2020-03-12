import java.util.*;

public class Problem{
	public static void main(String[] args){
		
		Book book1 = new Book("Dragon Rising", 3, 5);
		Book book2 = new Book("A Blade in the Dark", 3, 5);

		Food food1 = new Food("Cabbage", 2, 4);
		Food food2 = new Food("Rabbit", 2, 4);

		Weapon weapon = new Weapon("Sword", 5, 10);


		List<Book> books = new ArrayList<Book>();
		List<Food> foods = new ArrayList<Food>();
		List<Weapon> weapons = new ArrayList<Weapon>();

		books.add(book1);
		books.add(book2);

		foods.add(food1);
		foods.add(food2);

		weapons.add(weapon);

		Knapsack knapsack = new Knapsack("Ghiozdan", 10);

		System.out.println(knapsack.toString());

		System.out.println("***************************************************");

		System.out.println(book1.toString());
		System.out.println(book2.toString());
		System.out.println(food1.toString());
		System.out.println(food2.toString());
		System.out.println(weapon.toString());

		System.out.println("***************************************************");
			
		//knapsack.addBooks(books);
		//knapsack.addFoods(foods);
		knapsack.addWeapons(weapons);
	

		System.out.println(knapsack.toString());
		System.out.println(knapsack.getTotal());

	}
}