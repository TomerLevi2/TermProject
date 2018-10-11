import java.lang.String;

/*
 * book class that has getters and setters for author and isbn,
 * which every book and audiobook uses. This was so I could write less code
 * in the child classes. the constructor is used whenever a new book is created.
 * I overrode the toString() method according to the assignment requirements.
 */

public class Book extends CatalogItem {
	String author;
	int ISBN;
	double discount;
	
	public Book() {
		author = "Harold";
		ISBN = 99;
	}
	
	private Book (String names, double prices, String authors, int ISBNs) {
		this.name = names;
		this.price = prices;
		this.author = authors;
		this.ISBN = ISBNs;
	}
	
	public String getAuthor() {
		return this.author;
	}
	public int getISBN() {
		return this.ISBN;
	}
	public void setAuthor(String authors) {
		this.author = authors;
	}
	public void setISBN(int ISBNs) {
		this.ISBN = ISBNs;
	}
	public double getPrice() {
		this.discount = .9 * this.price;
		return this.discount;
	}
	
	public String toString() {
		String output = "Title: " + this.name + "| Author: " + this.author + "| Price: " 
						+ this.price + "| ISBN: " + this.ISBN;
		return output;
	}
}
