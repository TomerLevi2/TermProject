/*
 * Name: Tomer Levi
 * Net Id: txl160430
 * Class: CS 2336.503
 * Term Project
 * 
 * This prompts the user to choose a menu option. The user can then
 * 		choose option A, to sign in as a manger, option b to be a customer, or
 * 		option c, to exit. If the user chooses option A, the user must first
 * 		log in as a manager. If they can log in, the can choose to add a new
 * 		item to the catalog, remove an item from the catalog, display the catalog,
 * 		or create a backup file. If option b is selected, the user can display all books
 * 		or dvds, but they are sorted by price, add an item to the cart, remove an item from the
 * 		cart, display the cart, or checkout. Through the use of classes, I have
 * 		defined these objects and have created getters and setters for each catalog item.
 * 		Also, I have made a validator class to validate all input.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.lang.String;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;

public class TermProject { 

     public static void main(String[] args) {
    	 String name = "";			//Catalog
    	 double price = 0.0;
    	 String author = "";			//books
    	 int ISBN = -4;
    	 double runningTime = -1.1;	//audiobooks
    	 String director = "";		//dvds
    	 int year = -5;
    	 int dvdCode = -5;
    	 String itemType;
    	 int index = -1;			//used by methods
    	 String matchName;
    	 boolean validInput = false;
    	 boolean exists = true;
    	 boolean inCart = false;
     	 ArrayList<Book> books = new ArrayList<Book>();
     	 ArrayList<DVD> dvds = new ArrayList<DVD>();
     	 ArrayList<CatalogItem> cart = new ArrayList<CatalogItem>();
     	 Validator test = new Validator();		//used to check if user inputs are valid
		 
    	 
    	 int option;
    	 int userOption;
    	 boolean managerExists;
    	 Scanner s = new Scanner(System.in);
   		
    	 do {
    		 option = options();
    		 switch(option) {
    		 case 0:
    			 //prompts the user to log in, if the login is valid, the program continues.
    			 //If it is not valid, then the program exits to the main menu.
    			 managerExists = checkCredentials();
    			 if(managerExists == false) {
    				 System.out.println("Unrecognized Credentials");
    				 break;
    			 }
    			 do {
    				 userOption = menuA();
    				 switch(userOption) {
    				 case 1:
    		     			//gets the proper input from the user and validates it
    		     			System.out.println("Please enter the book's ISBN number: ");
    		     			ISBN = s.nextInt();
    		     			s.nextLine();

    		     			//checks to see if the input is positive
    		     			test.setNoStatic(ISBN);
    		     			validInput = test.isPositive(test.getNoStatic());
    		     			if(validInput == false) {
    		     				System.out.println("Your input was invalid.");
    		     				break;
    		     			}
    		     			
    		     			//checks to see if the isbn code already exists
    		     			exists = bookExists(books, ISBN);
    		     			if(exists == true) {
    		     				System.out.println("This book already exits.");
    		     				break;
    		     			}
    		     			
    		     			//prompts the user for input, then checks to see if the string is not empty
    		     			System.out.println("Please enter the book's name: ");
    		     			name = s.nextLine();

    		     			test.setNonStatic(name);
    		     			validInput = test.isNonEmptyString(test.getNonStatic());
    		     			if(validInput == false) {
    		     				System.out.println("Your input was invalid.");
    		     				break;
    		     			}
    		     			
    		     			//prompts the user for input and checks to make sure that the input is positive
    		     			System.out.println("Please enter the book's price: ");
    		     			price = s.nextDouble();
    		     			s.nextLine();
    		     			
    		     			test.setNotStatic(price);
    		     			validInput = test.isPositiveInput(test.getNotStatic());
    		     			if(validInput == false) {
    		     				System.out.println("Your input was invalid.");
    		     				break;
    		     			}
    		     			
    		     			//prompts the user for input, then checks to see if the string is not empty
    		     			System.out.println("Please enter the book's author: ");
    		     			author = s.nextLine();

    		     			test.setNonStatic(author);
    		     			validInput = test.isNonEmptyString(test.getNonStatic());
    		     			if(validInput == false) {
    		     				System.out.println("Your input was invalid.");
    		     				break;
    		     			}
    		     			
    		     			//creates a new book based on the criteria inputed by the user
    		     			Book a = new Book();
    		     			a.setAuthor(author);
    		     			a.setISBN(ISBN);
    		     			a.setName(name);
    		     			a.setPrice(price);
    		     			books.add(a);
    		     			
    		     			
    		     			break;
    		     		case 2:
    		     			//the audiobook uses the same validation and variables as the book
    		     			//except the addition of running time
    		     			//gets proper information from the user and validates it
    		     			System.out.println("Please enter the audiobook's ISBN number: ");
    		     			ISBN = s.nextInt();
    		     			s.nextLine();
    		     			
    		     			test.setNoStatic(ISBN);
    		     			validInput = test.isPositive(test.getNoStatic());
    		     			if(validInput == false) {
    		     				System.out.println("Your input was invalid.");
    		     				break;
    		     			}
    		     			
    		     			exists = bookExists(books, ISBN);
    		     			if(exists == true) {
    		     				System.out.println("This audiobook already exits.");
    		     				break;
    		     			}
    		     			
    		     			System.out.println("Please enter the audiobook's name: ");
    		     			name = s.nextLine();
    		     			
    		     			test.setNonStatic(name);
    		     			validInput = test.isNonEmptyString(test.getNonStatic());
    		     			if(validInput == false) {
    		     				System.out.println("Your input was invalid.");
    		     				break;
    		     			}
    		     			
    		     			System.out.println("Please enter the audiobook's price: ");
    		     			price = s.nextDouble();
    		     			s.nextLine();
    		     			
    		     			test.setNotStatic(price);
    		     			validInput = test.isPositiveInput(test.getNotStatic());
    		     			if(validInput == false) {
    		     				System.out.println("Your input was invalid.");
    		     				break;
    		     			}
    		     			
    		     			System.out.println("Please enter the audiobook's author: ");
    		     			author = s.nextLine();

    		     			test.setNonStatic(author);
    		     			validInput = test.isNonEmptyString(test.getNonStatic());
    		     			if(validInput == false) {
    		     				System.out.println("Your input was invalid.");
    		     				break;
    		     			}
    		     			
    		     			//prompts the user for input and checks to make sure that the input is positive
    		     			System.out.println("Please enter the audiobook's running time: ");
    		     			runningTime = s.nextDouble();
    		     			s.nextLine();
    		     			
    		     			test.setNotStatic(runningTime);
    		     			validInput = test.isPositiveInput(test.getNotStatic());
    		     			if(validInput == false) {
    		     				System.out.println("Your input was invalid.");
    		     				break;
    		     			}
    		     			
    		     			//creates a new audiobook based on information entered by the user
    		     			AudioBook b = new AudioBook();
    		     			b.setAuthor(author);
    		     			b.setISBN(ISBN);
    		     			b.setName(name);
    		     			b.setPrice(price);
    		     			b.setRunTime(runningTime);
    		     			books.add(b);
    		     			
    		     			break;
    		     		case 3:
    		     			/*
    		     			 * prompts the user for input. If it is a string, then
    		     			 * the validator class makes sure it is not an empty string.
    		     			 * If it is an int or double, the validator class makes sure 
    		     			 * it is positive.
    		     			 */
    		     			System.out.println("Please enter the DVD's code: ");
    		     			dvdCode = s.nextInt();
    		     			s.nextLine();
    		     			
    		     			test.setNoStatic(dvdCode);
    		     			validInput = test.isPositive(test.getNoStatic());
    		     			if(validInput == false) {
    		     				System.out.println("Your input was invalid.");
    		     				break;
    		     			}
    		     			
    		     			exists = dvdExists(dvds, dvdCode);
    		     			if(exists == true) {
    		     				System.out.println("This DVD already exits.");
    		     				break;
    		     			}
    		     			
    		     			System.out.println("Please enter the DVD's name: ");
    		     			name = s.nextLine();

    		     			test.setNonStatic(name);
    		     			validInput = test.isNonEmptyString(test.getNonStatic());
    		     			if(validInput == false) {
    		     				System.out.println("Your input was invalid.");
    		     				break;
    		     			}
    		     			
    		     			System.out.println("Please enter the DVD's price: ");
    		     			price = s.nextDouble();
    		     			s.nextLine();
    		     			
    		     			test.setNotStatic(price);
    		     			validInput = test.isPositiveInput(test.getNotStatic());
    		     			if(validInput == false) {
    		     				System.out.println("Your input was invalid.");
    		     				break;
    		     			}
    		     			
    		     			System.out.println("Please enter the DVD's director: ");
    		     			director = s.nextLine();

    		     			test.setNonStatic(director);
    		     			validInput = test.isNonEmptyString(test.getNonStatic());
    		     			if(validInput == false) {
    		     				System.out.println("Your input was invalid.");
    		     				break;
    		     			}
    		     			
    		     			System.out.println("Please enter the DVD's release year: ");
    		     			year = s.nextInt();
    		     			s.nextLine();
    		     			
    		     			test.setNoStatic(year);
    		     			validInput = test.isPositive(test.getNoStatic());
    		     			if(validInput == false) {
    		     				System.out.println("Your input was invalid.");
    		     				break;
    		     			}
    		     			
    		     			//creates a new dvd based on user input
    		     			DVD c = new DVD();
    		     			c.setName(name);
    		     			c.setPrice(price);
    		     			c.setDirector(director);
    		     			c.setDVDCode(dvdCode);
    		     			c.setYear(year);
    		     			dvds.add(c);
    		     			break;
    		     		case 4:
    		     			//gets an ISBN number from the user, and makes sure it is positive
    		     			System.out.println("Please enter the Book's ISBN number: ");
    		     			ISBN = s.nextInt();
    		     			s.nextLine();
    		     			
    		     			test.setNoStatic(ISBN);
    		     			validInput = test.isPositive(test.getNoStatic());
    		     			if(validInput == false) {
    		     				System.out.println("Your input was invalid.");
    		     				break;
    		     			}
    		     			
    		     			//calls the findBook method to get the index of the item that matches
    		     			//the ISBN number entered by the user. If -1 is returned, then an
    		     			//error message outputs, otherwise, the book is removed.
    		     			index = findBook(books, ISBN);
    		     			if(index == -1) {
    		     				System.out.println("This item does not exist.");
    		     				break;
    		     			}
    		     			
    		     			books.remove(index);
    		     			
    		     			break;
    		     		case 5:
    		     			//gets an DVD code from the user, and makes sure it is positive
    		     			System.out.println("Please enter the DVD's code: ");
    		     			dvdCode = s.nextInt();
    		     			s.nextLine();
    		     			
    		     			test.setNoStatic(dvdCode);
    		     			validInput = test.isPositive(test.getNoStatic());
    		     			if(validInput == false) {
    		     				System.out.println("Your input was invalid.");
    		     				break;
    		     			}
    		     			
    		     			//calls the findDVD method to get the index of the item that matches
    		     			//the dvd code entered by the user. If -1 is returned, then an
    		     			//error message outputs, otherwise, the dvd is removed.
    		     			index = findDVD(dvds, dvdCode);
    		     			if(index == -1) {
    		     				System.out.println("This item does not exist.");
    		     				break;
    		     			}
    		     			
    		     			dvds.remove(index);
    		     			
    		     			break;
    		     		case 6:
    		     			//outputs all books if there is more than 1 book
    		     			if(books.size() > 0) {
    		     				displayBooks(books);
    		     			}
    		     			System.out.println("-----------------------------------------------------------------------------------------------------------------");
    		     			//outputs all books if there is more than 1 dvd
    		     			if(dvds.size() > 0) {
    		     				displayDVDs(dvds);
    		     			}
    		     			System.out.println();
    		     			
    		     			break;
    				 case 7:
    					 createBackupFile(books, dvds);
    					 break;
    				default:
    					 break;
    				 }
    			 } while (userOption != 9);
    			 break;
    		 case 1:
    			 do {
    				 userOption = menuB();
    				 switch(userOption) {
    				 case 1:
    					 //displays all books
    					 itemType = "Books";
    					 displayBooks(books, itemType);
    					 break;
    				 case 2:
    					 //displays all dvds
    					 itemType = "DVDs";
    					 displayDVDs(dvds, itemType);
    					 break;
    				 case 3:
    					 //prompts the user for a book or audiobook to add, and checks to make
    					 //sure the user input is not an empty string
    					 System.out.println("Please enter the name of the book you would like add: ");
    					 matchName = s.nextLine();
    					 
    					 test.setNonStatic(matchName);
 		     			 validInput = test.isNonEmptyString(test.getNonStatic());
 		     			 if(validInput == false) {
 		     				 System.out.println("Your input was invalid.");
 		     				 break;
 		     			 }
    					 
    					 for(int i = 0; i < books.size(); i++) {
    						 if(matchName.equals(books.get(i).getName())) {
    							 cart.add(books.get(i));
    						 }
    					 }
    					 break;
    				 case 4:
    					//prompts the user for a dvd to add, and checks to make
    					 //sure the user input is not an empty string
    					 System.out.println("Please enter the name of the dvd you would like add: ");
    					 matchName = s.nextLine();
    					 
    					 test.setNonStatic(matchName);
 		     			 validInput = test.isNonEmptyString(test.getNonStatic());
 		     			 if(validInput == false) {
 		     				 System.out.println("Your input was invalid.");
 		     				 break;
 		     			 }
    					 
    					 for(int i = 0; i < dvds.size(); i++) {
    						 if(matchName.equals(dvds.get(i).getName())) {
    							 cart.add(dvds.get(i));
    						 }
    					 }
    					 break;
    				 case 5:
    					 /*
    					  * checks to see if the cart is empty, if it is, then a message is outputted.
    					  * If the cart is not empty, then the user is prompted for the name of the book
    					  * they want removed from the cart. It then checks to make sure the input is
    					  * not an empty string. If the book does not exist in the cart, then an
    					  * error message is shown.
    					  */
    					 inCart = false;
    					 
    					 if(cart.size() == 0){
    						 System.out.println("Your cart is empty.");
    					 }
    					 else{
    						 System.out.println("Please enter the name of the book you would like remove from your cart: ");
        					 matchName = s.nextLine();
        					 
        					 test.setNonStatic(matchName);
     		     			 validInput = test.isNonEmptyString(test.getNonStatic());
     		     			 if(validInput == false) {
     		     				 System.out.println("Your input was invalid.");
     		     				 break;
     		     			 }
        					 
        					 for(int i = 0; i < cart.size(); i++) {
        						 if(matchName.equals(cart.get(i).getName())) {
        							 cart.remove(i);
        							 inCart = true;
        						 }
        					 }
        					 if(inCart == false) {
        						 System.out.println("That item does not exist in your cart.");
        					 }
    					 }
    					 break;
    				 case 6:
    					 /*
    					  * checks to see if the cart is empty, if it is, then a message is outputted.
    					  * If the cart is not empty, then the user is prompted for the name of the dvd
    					  * they want removed from the cart. It then checks to make sure the input is
    					  * not an empty string. If the dvd does not exist in the cart, then an
    					  * error message is shown.
    					  */
    					 inCart = false;
    					 
    					 if(cart.size() == 0){
    						 System.out.println("Your cart is empty.");
    					 }
    					 else{
    						 System.out.println("Please enter the name of the dvd you would like remove from your cart: ");
        					 matchName = s.nextLine();
        					 
        					 test.setNonStatic(matchName);
     		     			 validInput = test.isNonEmptyString(test.getNonStatic());
     		     			 if(validInput == false) {
     		     				 System.out.println("Your input was invalid.");
     		     				 break;
     		     			 }
        					 
        					 for(int i = 0; i < cart.size(); i++) {
        						 if(matchName.equals(cart.get(i).getName())) {
        							 cart.remove(i);
        							 inCart = true;
        						 }
        					 }
        					 if(inCart == false) {
        						 System.out.println("That item does not exist in your cart.");
        					 }
    					 }
    					 break;
    				 case 7:
    					 /*
    					  * checks to see if the cart is empty, if it is a message is shown.
    					  * If not, then the cart is outputted.
    					  */
    					 if(cart.size() == 0) {
    						 System.out.println("Your cart is empty.");
    					 }
    					 else {
    						 displayCart(cart);
    					 }
    					 break;
    				 case 8:
    					 /*
    					  * the checkout method is called.
    					  */
    					 checkout(cart);
    					 break;
    				default:
    					 break;
    				 }
    			 } while (userOption != 9);
    			 break;
    		 default:
    			 break;
    		 }
    		 
    	 } while (option != 3);
    	 
    	 s.close();
     }
     
     public static int options() {
    	 /*
    	  * The initial menu is shown and asks the user for input. 
    	  * Then the validator class makes sure that the input is valid,
    	  * and if it is the do while loop makes sure that input corresponds
    	  * to an option in the menu. Then the option is returned.
    	  */
    	 String input;
    	 char option;
    	 boolean validInput = false;
    	 Validator test = new Validator();
    	 Scanner s = new Scanner(System.in);
    	do {
    	
    	 System.out.println("**Welcome to the Comets Books and DVDs Store**");
    	 System.out.println("");
    	 System.out.println("Please select your role:");
    	 System.out.println("A - store manager");
    	 System.out.println("B - customer");
    	 System.out.println("C - exit store");
    	 input = s.nextLine();
    	 
		test.setNonStatic(input);
		validInput = test.isNonEmptyString(test.getNonStatic());
		if(validInput == false) {
			System.out.println("Your input was invalid.");
			break;
		}
    	 
    	 option = input.charAt(0);
    	 
    	 if(option == 'A' || option == 'a') {
    		 return 0;
    	 }
    	 else if(option == 'B' || option == 'b') {
    		 return 1;
    	 }
    	 else if(option == 'C' || option == 'c') {
    		 return 3;
    	 }
    	 else {
    		 System.out.println("Your input was invalid");
    	 }
    	} while (option != 'A' || option != 'a' || option != 'B' || option != 'b' || option != 'C' || option != 'c');
    	return 3;
     }
     public static int menuA() {
    	 /*
    	  * The menu for option A is shown and asks the user for input. 
    	  * Then the validator class makes sure that the input is valid,
    	  * and if it is the do while loop makes sure that input corresponds
    	  * to an option in the menu. Then the option is returned.
    	  */
    	 int option;
    	 boolean validInput = false;
    	 Validator test = new Validator();
    	 Scanner s = new Scanner(System.in);
    	 
    	 do {
    		 System.out.println("**Welcome to the Comets Books and DVDs Store (Catalog Section)**");
    		 System.out.println("");
    		 System.out.println("Choose from the following options:");
    		 System.out.println("1 - Add Book");
    		 System.out.println("2 - Add AudioBook");
    		 System.out.println("3 - Add DVD");
    		 System.out.println("4 - Remove Book");
    		 System.out.println("5 - Remove DVD");
    		 System.out.println("6 - Display Catalog");
    		 System.out.println("7 - Create backup file");
    		 System.out.println("9 - Exit Catalog section");
    		 option = s.nextInt();
  			 s.nextLine();
  			 
  			test.setNoStatic(option);
 			validInput = test.isPositive(test.getNoStatic());
 			if(validInput == false) {
 				System.out.println("Your input was invalid.");
 				break;
 			}
    		 
    	 } while ((option < 1 || option > 9) && option == 8);
    	 
    	 return option;
     }
     public static int menuB() {
    	 /*
    	  * The menu for option B is shown and asks the user for input. 
    	  * Then the validator class makes sure that the input is valid,
    	  * and if it is the do while loop makes sure that input corresponds
    	  * to an option in the menu. Then the option is returned.
    	  */
    	 int option;
    	 boolean validInput = false;
    	 Validator test = new Validator();
    	 Scanner s = new Scanner(System.in);
    	 
    	 do {
    		 System.out.println("**Welcome to the Comets Books and DVDs Store (Catalog Section)**");
    		 System.out.println("");
    		 System.out.println("Choose from the following options:");
    		 System.out.println("1 - Browse books inventory (price low to high)");
    		 System.out.println("2 - Browse DVDs inventory (price low to high)");
    		 System.out.println("3 - Add a Book to the cart");
    		 System.out.println("4 - Add a DVD to the cart");
    		 System.out.println("5 - Delete a Book from the cart");
    		 System.out.println("6 - Delete a DVD from the cart");
    		 System.out.println("7 - View cart");
    		 System.out.println("8 - Checkout");
    		 System.out.println("9 - Done Shopping");
    		 option = s.nextInt();
    		 s.nextLine();
    		 
    		 test.setNoStatic(option);
  			 validInput = test.isPositive(test.getNoStatic());
  			 if(validInput == false) {
  				 System.out.println("Your input was invalid.");
  				 break;
  			 }
    		 
    	 } while (option < 1 || option > 9);
    	 
    	 return option;
     }
     public static boolean checkCredentials() {
    	 String username;
    	 String password;
    	 String checkValid;
    	 boolean exists = false;
    	 boolean userValid = false;
    	 boolean passValid = false;
    	 Scanner s = new Scanner(System.in);
    	 
    	 /*
    	  * prompts the user for the login credentials. It then checks
    	  * if they are valid throught he validator class.
    	  */
    	 do {
    		 System.out.println("Please enter your username: ");
    		 username = s.nextLine();
    		 
    		 System.out.println("Please enter your password: ");
    		 password = s.nextLine();
    		 
    		 Validator a = new Validator();
    		 a.setNonStatic(username);
    		 
    		 Validator b = new Validator();
    		 b.setNonStatic(password);
    		 
    		 userValid = a.isNonEmptyString(a.getNonStatic());
    		 passValid = b.isNonEmptyString(b.getNonStatic());
    	 } while(userValid == false && passValid == false);
    	 
    	 /*
    	  * Then it combines the user input to format that would be in the credentials file.
    	  * And then it checks the file to see if it is in the file. There are try and 
    	  * catch variables in case the file is not found. If the user input are not in the file,
    	  * false is returned. If they are in the file, then true is returned.
    	  */
    	 checkValid = username + "," + password; 
    	 String filename = "credentials.txt";
    	 File file = new File(filename);
    	 
    	 try {
 			Scanner a = new Scanner(file);		
 			while (a.hasNextLine()){
 				if(checkValid.equals(a.nextLine())) {
 					exists = true;
 					return exists;
 				}
 			}
 		}
 		catch (FileNotFoundException fnfx) {
 			System.out.println("File: " + fnfx + " was not found.");
 		}
 		catch (Exception ex) {
 			System.out.println("Caught some generic Exception " + ex.toString());
 		}
 		return exists;
     }
     public static void createBackupFile(ArrayList<Book> item, ArrayList<DVD> items) {
    	 String day;
    	
    	 /*
    	  * gets the date and time and appends it in a string of the right 
    	  * format. Then it creates a backup file and uses the date as
    	  * the name for the file. It then prints every book, including audiobook,
    	  * and dvd to the file via their respective toString method.
    	  */
    	DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
    	Date date = new Date();
    	day = dateFormat.format(date); //yyyy_MM_dd_HH_mm_ss
    		
    	String fileName = "catalog_backup_" + day +".txt";
  		String outputFileName = fileName;
  		File outputFile = new File(outputFileName);
  		
  		try {
  			PrintWriter pw = new PrintWriter(outputFile);
  			for(int i = 0; i < (item.size()); i++) {
  	    		 pw.println(item.get(i).toString());
  	    	}
  			for(int i = (0); i < (items.size()); i++) {
  				pw.println(items.get(i).toString());
  	    	}
  			pw.close();
  		}
  		catch (FileNotFoundException fnfx) {
  			System.out.println("File: " + fnfx + " was not found.");
  		}
     }
     public static boolean bookExists(ArrayList<Book> items, int code) {
    	 //checks if a book with the same ISBN number exists.
    	 //If another book does exist, true is returned, else
    	 //false is returned.
    	 int existingCode = 0;
    	 for(int i = 0; i < items.size(); i++) {
    		 existingCode = items.get(i).getISBN();
    		 if(existingCode == code) {
    			 return true;
    		 }
    	 }
    	 return false;
     }
     public static boolean dvdExists(ArrayList<DVD> items, int code) {
    	//checks if a dvd with the same dvd code exists.
    	 //If another dvd does exist, true is returned, else
    	 //false is returned.
    	 int existingCode = 0;
    	 for(int i = 0; i < items.size(); i++) {
    		 existingCode = items.get(i).getDVDCode();
    		 if(existingCode == code) {
    			 return true;
    		 }
    	 }
    	 return false;
     }
     public static int findBook(ArrayList<Book> items, int code) {
    	 //finds ISBN number, returns that place in the array, then returns the index
    	 int codes = -1;
    	 for(int i = 0; i < items.size(); i++) {
    		 codes = items.get(i).getISBN();
    		 if(codes == code) {
    			 return i;
    		 }
    	 }
    	 return -1;
     }
     public static int findDVD(ArrayList<DVD> items, int code) {
    	 //finds DVD code, returns that place in the array, then returns the index
    	 int codes = -1;
    	 for(int i = 0; i < items.size(); i++) {
    		 codes = items.get(i).getDVDCode();
    		 if(codes == code) {
    			 return i;
    		 }
    	 }
    	 return -1;
     }
     public static void displayBooks(ArrayList<Book> items) {
    	 //display all elements in the array list using .toString()
    	 for(int i = 0; i < items.size(); i++) {
    		 System.out.println(items.get(i).toString());
    	 }
     }
     public static void displayDVDs(ArrayList<DVD> items) {
    	 //display all elements in the array list using .toString()
    	 for(int i = 0; i < items.size(); i++) {
    		 System.out.println(items.get(i).toString());
    	 }
     }
     public static void displayBooks(ArrayList<Book> books, String type) {
    	 int length = 0;
    	 Collections.sort(books);
    	 
    	//This prints the table overhead
 		System.out.print("Inventory Number    ");
 		System.out.print(type);
 		System.out.println("                  Prices");
 		System.out.println("-------------------------------------------------");
 		
 		 /*
 		  * outputs the books in a good format. It outputs the index, the book name,
 		  * and the price.
 		  */
    	 for(int i = 0; i < books.size(); i++) {
    		 int j = i + 1;
    		 length = books.get(i).getName().length();
    		 length = 23 - length;
    		 
    		 System.out.print(j + "                   "  + books.get(i).getName());
    		 
    		 for(int a = 0; a < length; a ++){
    			 System.out.print(" ");
    		 }
    		 
    		 System.out.print("$");
 			 DecimalFormat dec = new DecimalFormat("#0.00");
 			 System.out.println(dec.format(books.get(i).getPrice()));
    	 }
     }
     public static void displayDVDs(ArrayList<DVD> dvds, String type) {
    	 int length = 0;
    	 Collections.sort(dvds);
    	 
    	//This prints the table overhead
 		System.out.print("Inventory Number    ");
 		System.out.print(type);
 		System.out.println("                  Prices");
 		System.out.println("-------------------------------------------------");
 		
 		/*
		  * outputs the books in a good format. It outputs the index, the dvd name,
		  * and the price.
		  */
 		for(int i = 0; i < dvds.size(); i++) {
 			 int j = i + 1;
    		 length = dvds.get(i).getName().length();
    		 length = 23 - length;
    		 
    		 System.out.print(j + "                   "  + dvds.get(i).getName());
    		 
    		 for(int a = 0; a < length; a ++){
    			 System.out.print(" ");
    		 }
    		 
    		 System.out.print("$");
 			 DecimalFormat dec = new DecimalFormat("#0.00");
 			 System.out.println(dec.format(dvds.get(i).getPrice()));
    	 }
     }
     public static void displayCart(ArrayList<CatalogItem> cart) {
    	 int length = 0;
    	 
    	 /*
 		 * This is the heading of the table for the cart
 		 */
 		System.out.println("Items             Prices");
 		System.out.println("------------------------");
 		
 		/*
		  * outputs the books in a good format. It outputs the name,
		  * and the price of everything in the cart.
		  */
 		for(int i = 0; i < cart.size(); i++) {
 			length = cart.get(i).getName().length();
 			length = 18 - length;
 			
 			System.out.print(cart.get(i).getName());
   		 
 			for(int a = 0; a < length; a ++){
 				System.out.print(" ");
 			}
   		 
 			System.out.print("$");
 			DecimalFormat dec = new DecimalFormat("#0.00");
			System.out.println(dec.format(cart.get(i).getPrice()));
 		}
     }
     public static void checkout(ArrayList<CatalogItem> cart) {
    	 double total = 0.0;
    	 
    	 for(int i = 0; i < cart.size(); i++) {
    		 total = cart.get(i).getPrice();
    	 }
    	 total = total * 1.0825;
    	 
    	 /*
 		 * Here I print out the total, and format
 		 * it the same way I have formatted the whole way.
 		 * Except now I find the length of the price
 		 * and subtract the length of that from the spaces.
 		 */
 		System.out.print("Total + tax: ");
 		double space = total / 100.00;
 		space = (int) space;
 		space = 7 - space;
 		for(int i = 0; i < space; i++){
 			System.out.print(" ");
 		}
 		System.out.print("$");
 		DecimalFormat dec = new DecimalFormat("#0.00");
 		System.out.println(dec.format(total));
     }
}