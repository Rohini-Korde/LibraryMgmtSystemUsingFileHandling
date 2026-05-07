package com.newProject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class FileHandling 
{
	
	ArrayList<BookDetails> loadBooks() 
	{
		ArrayList<BookDetails> Books = new ArrayList<>();
		BufferedReader br = null;

	    try 
	    {
	    	br = new BufferedReader(new FileReader("books.txt"));

	        String line;
	        while ((line = br.readLine()) != null) 
	        {
	            String[] data = line.split("\\|");

	            int id = Integer.parseInt(data[0]);
	            String name = data[1];
	            String author = data[2];
	            String genre = data[3];
	            int quantity = Integer.parseInt(data[4]);

	            BookDetails book = new BookDetails(id, name,author, genre,quantity);
	            Books.add(book);
	        }

	    }
	    catch (IOException e) 
	    {
	        System.out.println("Books file not found or empty.");
	    }
	    finally
	    {
	    	try 
	    	{
				br.close();
			} 
	    	catch (IOException e) 
	    	{
				e.printStackTrace();
			}
	    	return Books;
	    }
	}
	
	void saveBooks(ArrayList<BookDetails> Books)
	{
		BufferedWriter bw = null;
		
		try
		{
			bw = new BufferedWriter(new FileWriter("books.txt"));
			
			for(BookDetails b : Books)
			{
				bw.write(b.id+"|"+b.name+"|"+b.author+"|"+b.genre+"|"+b.quantity);
				bw.newLine();
			}
		}
		catch(IOException e)
		{
			System.out.println("Error in saving file.");
		}
		finally
		{
			try 
			{
				bw.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	Deque<String> loadTrans()
	{
		Deque<String> trans = new ArrayDeque<>();
		BufferedReader br = null;

	    try 
	    {
	    	br = new BufferedReader(new FileReader("transactions.txt"));

	        String line;
	        while ((line = br.readLine()) != null) 
	        {
	            trans.add(line);
	        }

	    }
	    catch (IOException e) 
	    {
	        System.out.println("Transaction file not found or empty.");
	    }
	    finally
	    {
	    	try 
	    	{
				br.close();
			} 
	    	catch (IOException e) 
	    	{
				e.printStackTrace();
			}
	    	return trans;
	    }
	}
	
	void saveTrans(String trans)
	{
		BufferedWriter bw = null;
		
		try
		{
			bw = new BufferedWriter(new FileWriter("transactions.txt",true));
			bw.write(trans);
			bw.newLine();
		}
		catch(IOException e)
		{
			System.out.println("Error in saving file.");
		}
		finally
		{
			try 
			{
				bw.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	
	LinkedHashMap<String,String> loadMembers()
	{
		LinkedHashMap<String,String> members = new LinkedHashMap<>();
		BufferedReader br = null;

	    try 
	    {
	    	br = new BufferedReader(new FileReader("members.txt"));

	        String line;
	        while ((line = br.readLine()) != null) 
	        {
	        	String[] data = line.split("\\|");
	            members.put(data[0], data[1]);
	        }

	    }
	    catch (IOException e) 
	    {
	        System.out.println("Transaction file not found or empty.");
	    }
	    finally
	    {
	    	try 
	    	{
				br.close();
			} 
	    	catch (IOException e) 
	    	{
				e.printStackTrace();
			}
	    	return members;
	    }
	}
	
	void saveMembers(String id,String members)
	{
		BufferedWriter bw = null;
		
		try
		{
			bw = new BufferedWriter(new FileWriter("members.txt",true));
			
            bw.write((id+ "|" + members));
			bw.newLine();
			
		}
		catch(IOException e)
		{
			System.out.println("Error in saving file.");
		}
		finally
		{
			try 
			{
				bw.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
}


