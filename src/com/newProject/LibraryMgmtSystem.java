package com.newProject;

import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

class LibraryMgmtSystem
{
	ArrayList<BookDetails> Books = new ArrayList<>();
	LinkedList<String> issuedBooks = new LinkedList<>();
	LinkedList<String> returnsBooks = new LinkedList<>();
	Deque<String> recentTransactions = new ArrayDeque<>();
	LinkedHashMap<String,String> registerMembers = new LinkedHashMap<>();
	
	FileHandling f = new FileHandling();
	FineOperation fine = new FineOperation();
	MemberIdGenerator mem = new MemberIdGenerator();

	public void register(String member)
	{
		if(!registerMembers.containsValue(member))
		{
			registerMembers.put(mem.getId(),member);
			f.saveMembers(registerMembers.lastEntry().getKey(),registerMembers.lastEntry().getValue());
			System.out.println("Member added successfully.\nYour Member Id is "+registerMembers.lastEntry().getKey());
		}
		else
		{
			System.out.println("Member already exists.");
		}
	}
	
	public void addBook(int id,String bookname, String author, String genre,int quantity)
	{
	    Books.add(new BookDetails(id,bookname,author, genre,quantity));
	    System.out.println(bookname + " added to the library");
	    
	}
	
	
	public void issuebook(String member,String bookname)
	{
		if (!registerMembers.containsKey(member))
	    {
	        System.out.println("Member not registered in library.");
	        return;
	    }
		
	    boolean found = false;
	    for (BookDetails book : Books)
	    {
	        if (book.name.equalsIgnoreCase(bookname))
	        {
	            found = true;
	
	            if (book.quantity > 0)
	            {
	                issuedBooks.add(bookname);
	                book.quantity--;
	                recentTransactions.add("Issued|" + bookname +"|"+member+"|"+LocalDate.now());
	                f.saveTrans(recentTransactions.getLast());
	               
	                System.out.println(bookname + " has been issued.");
	            }
	            else
	            {
	                System.out.println("Book is out of stock.");
	            }
	            return;
	        }
	    }
	
	    if (!found)
	    {
	        System.out.println("Book not found in the library.");
	    }
	}
	
	
	public void returnBook(String member,String bookname)
	{
		if (!registerMembers.containsKey(member))
	    {
	        System.out.println("Member not registered in library.");
	        return;
	    }
		
	    if (issuedBooks.contains(bookname))
	    {
	        for (BookDetails book : Books)
	        {
	            if (book.name.equalsIgnoreCase(bookname))
	            {
	                book.quantity++;
	                break;
	            }
	        }
	
	        issuedBooks.remove(bookname);
	        returnsBooks.add(bookname);
	        recentTransactions.add("Return|" + bookname +"|"+member+"|"+LocalDate.now());
	        f.saveTrans(recentTransactions.getLast());
	        fine.calculateFine();
	        System.out.println(bookname + " has been returned.");
	    }
	    else
	    {
	        System.out.println("This book was not issued from this library.");
	    }
	}
	
	
	public void ShowAvailableBooks()
	{
	    System.out.println("\nAvailable Books:");
	    if(Books.isEmpty()) 
	    {
	        System.out.println("No books available!");
	    } 
	    else 
	    {
	        for (BookDetails b : Books) 
	        {
	            b.display();
	        }
	    }
	}
	
	public void ShowIssuedBooks()
	{
		System.out.println("\nIssued Books:");
	    if (issuedBooks.isEmpty()) 
	    {
	        System.out.println("No books issued!");
	    } 
	    else 
	    {
	    	int i = 0;
	        for (String book : issuedBooks) 
	        {
	            System.out.println((i + 1) + ". " + book);
	            i++;
	        }
	    }
		
	}
	
	public void ShowReturnBooks()
	{
		System.out.println("\nReturn Books:");
	    if (returnsBooks.isEmpty()) 
	    {
	        System.out.println("No books returned!");
	    } 
	    else 
	    {
	    	int i = 0;
	        for (String book : returnsBooks) 
	        {
	            System.out.println((i + 1) + ". " + book);
	            i++;
	        }
	    }
		
	}
	
	public void ShowRecentTransaction()
	{
		System.out.println("\nRecent Transactions:");
	    if (recentTransactions.isEmpty()) 
	    {
	        System.out.println("No recent Transactions!");
	    } 
	    else 
	    {
	    	int i = 0;
	        for (String trans : recentTransactions) 
	        {
	            System.out.println((i + 1) + ". " + trans);
	            i++;
	        }
	    }
		
	}
	
	public void ShowRegisterMembers()
	{
		System.out.println("\nRegister Members:");
	    if (registerMembers.isEmpty()) 
	    {
	        System.out.println("No member registered!");
	    } 
	    else 
	    {
	    	int i = 0;
	    	for (Map.Entry<String, String> entry : registerMembers.entrySet()) {
                System.out.println((entry.getKey()+ "|" + entry.getValue()));
                i++;
            }
	     
	    }
			
	}
	
	public void searchByAuthor(String searchauthor)
	{
		 for (BookDetails b : Books) 
	        {
	           if(b.author.equalsIgnoreCase(searchauthor))
    		   {
	        	  b.display(); 
    		   }
	           else
	           {
	        	   System.out.println("No record found.");
	           }
	        }
	}
	
	public void searchByName(String searchname)
	{
		 for (BookDetails b : Books) 
	        {
	           if(b.name.equalsIgnoreCase(searchname))
    		   {
	        	  b.display(); 
    		   }
	           else
	           {
	        	   System.out.println("No record found.");
	           }
	        }
	}
	
	public void searchByGenre(String searchgenre)
	{
		 for (BookDetails b : Books) 
	        {
	           if(b.genre.equalsIgnoreCase(searchgenre))
    		   {
	        	  b.display(); 
    		   }
	           else
	           {
	        	   System.out.println("No record found.");
	           }
	        }
	}
	
	
}

