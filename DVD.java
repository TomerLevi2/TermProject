import java.lang.String;

/*
 * dvd class that has getters and setters for director, year, and dvdCode,
 * which every dvd The constructor is used whenever a new dvd is created.
 * I overrode the toString() method according to the assignment requirements.
 */

public class DVD extends CatalogItem{
	String director;
	int year;
	int dvdCode;
	double discount;
	
	public DVD() {
		director = "Michael Bay";
		year = 1998;
		dvdCode = 12345;
	}
	private DVD(String names, double prices, String directors, int years, int code) {
		this.name = names;
		this.price = prices;
		this.director = directors;
		this.year = years;
		this.dvdCode = code;
	}
	
	public String getDirector(){
		return this.director;
	}
	public int getYear() {
		return this.year;
	}
	public int getDVDCode() {
		return dvdCode;
	}
	public void setDirector(String directors) {
		this.director = directors;
	}
	public void setYear(int years) {
		this.year = years;
	}
	public void setDVDCode(int code) {
		this.dvdCode = code;
	}
	public double getPrice() {
		this.discount = .8 * this.price;
		return this.discount;
	}

	public String toString() {
		String output = "Title: " + this.name + "| Director: " + this.director + "| Price: "
					+ this.price + "| Year: " + this.year + "| DVD Code: " + this.dvdCode;
		return output;
	}
}