import java.lang.String;

/*
 * audiobook class that has getters and setters for runningtime,
 * which every book and audiobook uses. The constructor is used whenever a new 
 * audiobook is created. I overrode the toString() method according to the 
 * assignment requirements.
 */

public class AudioBook extends Book{
	double runningTime;
	double discount;
	
	public AudioBook() {
		runningTime = 9.9;
	}
	private AudioBook (String names, double prices, String authors, int ISBNs, double runTime) {
		this.name = names;
		this.price = prices;
		this.author = authors;
		this.ISBN = ISBNs;
		this.runningTime = runTime;
	}
	
	public double getRunTime() {
		return this.runningTime;
	}
	public void setRunTime(double runTime) {
		this.runningTime = runTime;
	}
	public double getPrice() {
		this.discount = .5 * this.price;
		return this.discount;
	}
	
	public String toString() {
		String output = "Title: " + this.name + "| Author: " + this.author + "| Price: "
					+ (this.price * .9) + "| ISBN: " + this.ISBN + "| Running Time: " + this.runningTime;
		return output;
	}
}