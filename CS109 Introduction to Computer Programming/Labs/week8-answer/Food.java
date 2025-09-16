import java.util.ArrayList;

public class Food {

	private int id;
	private String name;
	private String type;
	private int size;
	private double price;

	public Food(int id, String name, String type, int size, double price) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.size = size;
		this.price = price;
	}
	

	public static void main(String[] args) {
		Food[] foods = new Food[4];
		foods[0]=new Food(1,"pizza","Seafood",11,12);
		foods[1]=new Food(2,"pizza","Beef",9,10);
		foods[2]=new Food(3,"fried rice","Seafood",5,12);
		foods[3]=new Food(4,"noodles","Beef",6,14);
		for (Food f: foods) {
			System.out.println(f);
		}
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}



}
