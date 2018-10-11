import java.lang.String;

/*
 * base class that has getters and setters for name and price,
 * which every item uses. This was so I could write less code
 * in the child classes.
 */

public class CatalogItem implements Comparable<CatalogItem>{
	String name;
	double price;
	
	public CatalogItem() {
		name = "hi";
		price = 9.99;
	}
	
	public String getName() {
		return this.name;
	}
	public double getPrice() {
		return this.price;
	}
	public void setName(String names) {
		this.name = names;
	}
	public void setPrice(double prices) {
		this.price = prices;
	}
	
	public int compareTo(CatalogItem compareItem) {
		if(this.price < compareItem.price) {
			return -1;
		}
		else if(this.price > compareItem.price) {
			return 1;
		}
		return 0;
		
	}
}