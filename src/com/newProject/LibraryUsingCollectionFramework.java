package com.newProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

class MemberIdGenerator
{
	 public static String getId() {
	        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	        Random rand = new Random();
	        String id = "";

	        for (int i = 0; i < 6; i++) {
	            id += chars.charAt(rand.nextInt(chars.length()));
	        }
	        return id;
	    }
}

class BookIdGenerator {
    public int getId() {
    	Random rand = new Random();
    	int id = rand.nextInt(9000)+1000;
    	return id;
    }
}

class BookDetails 
{
	int id;
    String name;
    String author;
    String genre;
    int quantity;

    BookDetails(int id,String name,String author, String genre, int quantity) 
    {
    	this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.quantity = quantity;
    }

    void display() {
        System.out.println("Book id :"+id+" | Name: " + name +" | Author: "+ author + " | Genre: " + genre+" | Qty: " + quantity);
    }
}

public class LibraryUsingCollectionFramework {
	public static void main(String[] args) {
		int choice,qty,ch,i = 0;
		String name,member,genre,author,user,pass,newpass,conpass;
		boolean flag = false;
		Scanner sc = new Scanner(System.in);
		
		LibraryMgmtSystem l = new LibraryMgmtSystem();
		FileHandling f = new FileHandling();
		BookIdGenerator id = new BookIdGenerator();
		FineOperation fine = new FineOperation();
		LoginPage log = new LoginPage();
		
		l.Books = f.loadBooks();
		l.recentTransactions = f.loadTrans();
		l.registerMembers = f.loadMembers();
		fine.calculateFine();
		
		try
		{
			do {
        		System.out.println("\n------------------ Login Page ------------------");
    	        	System.out.print("Enter username: ");
	                user = sc.nextLine();
	                System.out.print("Enter password: ");
	                pass = sc.nextLine();
        	        flag = log.login(user,pass);
        	        i++;
        	}while(flag == false && i<2);
			
			if(flag == true)
	        {
           
		        do{
		            System.out.println("\n------------------ Library Menu ------------------");
		        System.out.println("1. Add Book");
		        System.out.println("2. Register new member");
		        System.out.println("3. Issue Book");
		        System.out.println("4. Return Book");
		        System.out.println("5. Show Operations");
		        System.out.println("6. Search Operations");
		        System.out.println("7. Fine Operations ");
		        System.out.println("8. Change Password");
		        System.out.println("0. Exit");
		        System.out.print("Enter choice: ");
		        choice = sc.nextInt();
		        sc.nextLine();  
		
		        switch (choice) {
		            case 1:
		                System.out.print("Enter book name: ");
		                name = sc.nextLine();
		                
		                System.out.println("Enter Author name: ");
		                author = sc.nextLine();
		
		                System.out.print("Enter genre: ");
		                genre = sc.nextLine();
		                
		                System.out.print("Enter quantity: ");
		                qty = sc.nextInt();
		                
		                l.addBook(id.getId(),name,author,genre,qty);
		                
		                break;
		            case 2:
		                System.out.print("Enter member name: ");
		                member = sc.nextLine();
		                
		                l.register(member);
		                break;
		            case 3:
		            	System.out.print("Enter member id: ");
		                member = sc.nextLine();
		                
		                System.out.print("Enter book name to issue: ");
		                name = sc.nextLine();
		                
		                l.issuebook(member,name);
		                
		                break;
		            case 4:
		            	System.out.print("Enter member id: ");
		                member = sc.nextLine();
		                
		                System.out.print("Enter book name to return: ");
		                name = sc.nextLine();
		                
		                l.returnBook(member,name);
		                
		                break;
		                
		            case 5:
		            	do
		            	{
		            		System.out.println("\n--------- Show Menu --------");
		            		System.out.println("1. Show All Books");
		        	        System.out.println("2. Show Recent Issued Books");
		        	        System.out.println("3. Show Recent Returned Books");
		        	        System.out.println("4. Show recent Transactions");
		        	        System.out.println("5. Show register members");
		        	        System.out.println("0. Exit");
		        	        System.out.print("Enter choice: ");
		        	        ch = sc.nextInt();
		        	        
		        	        switch(ch)
		        	        {
		        	        case 1:
			        	        l.ShowAvailableBooks();
				                break;
				            case 2:
				            	l.ShowIssuedBooks();
				                break;
				            case 3:
				            	l.ShowReturnBooks();
				                break;
				            case 4:
				            	l.ShowRecentTransaction();
				                break;
				            case 5:
				            	l.ShowRegisterMembers();
				                break;
				            case 0:
				                System.out.println("Exiting Show ...");
			        	        }
		            	}while(ch != 0);
		                break;
		                
		            case 6:
		            	do
		            	{
		            		System.out.println("\n--------- Search Menu --------");
		            		System.out.println("1. Search by Author");
		        	        System.out.println("2. Search by Book Name");
		        	        System.out.println("3. Search by Book Genre");
		        	        System.out.println("0. Exit");
		        	        System.out.print("Enter choice: ");
		        	        ch = sc.nextInt();
		        	        sc.nextLine();
		        	        
		        	        switch(ch)
		        	        {
		        	        case 1:
		        	        	System.out.println("Enter Author name: ");
		    	                author = sc.nextLine();
			        	        l.searchByAuthor(author);
				                break;
				            case 2:
				            	System.out.println("Enter Book name: ");
		    	                name = sc.nextLine();
			        	        l.searchByName(name);
				                break;
				            case 3:
				            	System.out.println("Enter Book genre: ");
		    	                genre = sc.nextLine();
			        	        l.searchByGenre(genre);
				                break;
				            case 0:
				                System.out.println("Exiting Search ...");
			        	        }
		            	}while(ch != 0);
		                break;
		                
		            case 7:
		            	do
		            	{
		            		System.out.println("\n--------- Fine Menu --------");
		            		System.out.println("1. Check fine");
		        	        System.out.println("2. Pay Fine");
		        	        System.out.println("0. Exit");
		        	        System.out.print("Enter choice: ");
		        	        ch = sc.nextInt();
		        	        sc.nextLine();
		        	        
		        	        switch(ch)
		        	        {
		        	        case 1:
		        	        	System.out.print("Enter member id: ");
		    	                member = sc.nextLine();
			        	        fine.ChkFine(member);;
				                break;
				            case 2:
				            	System.out.print("Enter member id: ");
				                member = sc.nextLine();
			        	        fine.PayFine(member);
				                break;
				            case 0:
				                System.out.println("Exiting Search ...");
			        	        }
		            	}while(ch != 0);
		                break;
		                
		            case 8:
		            	System.out.print("Enter username: ");
		                user = sc.nextLine();
		                System.out.print("Enter password: ");
		                pass = sc.nextLine();
		                System.out.print("Enter new password: ");
		                newpass = sc.nextLine();
		                System.out.print("Confirm new password: ");
		                conpass = sc.nextLine();
	        	        log.changePassword(user, pass, newpass, conpass);
		                break;
		                
		            case 0:
		                System.out.println("Exiting Library Management...");
		                System.out.println("\n--------------------------------------------------");
		                break;
		            default:
		                System.out.println("Invalid choice! Please try again.");
		            }
		        }while(choice != 0); 
	        }
			
		}
		catch(Exception e)
		{
			System.out.println("Exception occured :"+e.getMessage());
		}
		finally
		{
			f.saveBooks(l.Books);
		}
	}
}
