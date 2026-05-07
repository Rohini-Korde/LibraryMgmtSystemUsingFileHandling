package com.newProject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FineOperation {
	Scanner sc = new Scanner(System.in);
	
	void calculateFine() {
		try (BufferedReader br = new BufferedReader(new FileReader("transactions.txt"));
	         BufferedWriter bw = new BufferedWriter(new FileWriter("fines.txt", true));
	    	BufferedReader b = new BufferedReader(new FileReader("members.txt"))) {

	        String line;
	        String membername = "";
	        String memberId = "";
	        String bookName = "";
	        LocalDate issueDate = null;
	        LocalDate returnDate = null;
	        

	        while ((line = br.readLine()) != null) {

	            String[] data = line.split("\\|");

	            String action = data[0];
	            bookName = data[1];
	            memberId = data[2];
	            LocalDate date = LocalDate.parse(data[3]);
	            
	            while((line = b.readLine()) != null)
	            {
	            	String[] members = line.split("\\|");
	            	
	            	if(memberId.equalsIgnoreCase(members[0]))
	            	{
	            		membername = members[1];
	            	}
	            }

	            if (action.equalsIgnoreCase("Issued")) {
	                issueDate = date;
	            }

	            else if (action.equalsIgnoreCase("Return") && issueDate != null) 
	            {
	                returnDate = date;

	                long totalDays = returnDate.toEpochDay() - issueDate.toEpochDay();

	                int allowedDays = 7;
	                
	                int lateDays;
	                
	                if(totalDays > allowedDays)
	                {
	                	 lateDays = (int) (totalDays - allowedDays);
	                }
	                else 
	                {
	                    lateDays = 0;
	                }

	                double fine = lateDays * 5;
	                String status;
	                if(lateDays > 0) 
	                {
	                	status = "UNPAID";
	                }
	                else 
	                {
	                	status = "PAID";
	                }
	                
	                bw.write(memberId + "|" +membername + "|" +bookName + "|" +issueDate + "|" +returnDate + "|" +lateDays + "|" +fine + "|" +status);
	                bw.newLine();
	            }
	        }

	    } catch (Exception e) {
	        System.out.println("Error calculating fine.");
	    }
	}


	void updateFineStatus(String member_id) {

	    ArrayList<String> updated = new ArrayList<>();

	    try (BufferedReader br = new BufferedReader(new FileReader("fines.txt"))) {

	        String line;

	        while ((line = br.readLine()) != null) {

	            String[] d = line.split("\\|");

	            if (d[0].equals(member_id) && d[7].equals("UNPAID")) 
	            {
	                d[7] = "PAID";
	            }

	            updated.add(String.join("|", d));
	        }

	    } catch (IOException e) {
	        System.out.println("Error reading fine file.");
	        return;
	    }

	    try (BufferedWriter bw = new BufferedWriter(new FileWriter("fines.txt"))) {

	        for (String l : updated) {
	            bw.write(l);
	            bw.newLine();
	        }

	    } catch (IOException e) {
	        System.out.println("Error updating fine file.");
	    }
	}
	
	void ChkFine(String member_id) {
		String lineb;
		boolean foundb = false;

		try (BufferedReader b = new BufferedReader(new FileReader("members.txt"))) {

		    while ((lineb = b.readLine()) != null) {
		        String[] members = lineb.split("\\|");

		        if (member_id.equalsIgnoreCase(members[0])) {
		            foundb = true;
		            break;
		        }
		    }

		    if (!foundb) {
		        System.out.println("Member not registered in library.");
		        return;
		    }

		} catch (IOException e) {
		    e.printStackTrace();
		}

	    try (BufferedReader br = new BufferedReader(new FileReader("fines.txt"))) {

	        String line;
	        boolean found = false;

	        while ((line = br.readLine()) != null) {

	            String[] d = line.split("\\|");

	            if (d[0].equals(member_id) && d[7].equals("UNPAID")) {
	                System.out.println(
	                    "Book: " + d[2] +
	                    " | Fine: ₹" + d[6] +
	                    " | Status: " + d[7]
	                );
	                found = true;
	            }
	        }

	        if (!found) {
	            System.out.println("No pending fine.");
	        }

	    } catch (IOException e) {
	        System.out.println("Fine file not found.");
	    }
	}
	
	void PayFine(String member_id)
	{
		String lineb;
		boolean foundb = false;

		try (BufferedReader b = new BufferedReader(new FileReader("members.txt"))) {

		    while ((lineb = b.readLine()) != null) {
		        String[] members = lineb.split("\\|");

		        if (member_id.equalsIgnoreCase(members[0])) {
		            foundb = true;
		            break;
		        }
		    }

		    if (!foundb) {
		        System.out.println("Member not registered in library.");
		        return;
		    }

		} catch (IOException e) {
		    e.printStackTrace();
		}

		 try (BufferedReader br = new BufferedReader(new FileReader("fines.txt"))) {

		        String line;
		        boolean found = false;

		        while ((line = br.readLine()) != null) {

		            String[] d = line.split("\\|");

		            if (d[0].equals(member_id) && d[7].equals("UNPAID")) {
		                System.out.println(
		                    "Book: " + d[2] +
		                    " | Fine: ₹" + d[6] +
		                    " | Status: " + d[7]
		                );
		                found = true;
		            }
		        }

		        if (!found) {
		            System.out.println("No pending fine.");
		            return;
		        }

		    } catch (IOException e) {
		        System.out.println("Fine file not found.");
		    }
		 
		int choice;
		char ch;
		do
		{
			System.out.println("\n------ Payment Method  --------");
	        System.out.println("1. Credit/Debit");
	        System.out.println("2. UPI");
	        System.out.println("3. Cash");
	        System.out.println("0. Exit");
	        System.out.print("Enter choice: ");
	        choice = sc.nextInt();
	        sc.nextLine();  
			switch(choice)
			{
			case 1,2,3:
				System.out.println("Is Payment Completed (y/n) :");
				ch = sc.next().charAt(0);
				if(ch == 'Y' || ch == 'y')
				{
					System.out.println("Congrats!!! Payment is Done.....");
					// status update
					updateFineStatus(member_id);
				}
				else
				{
					System.out.println("Try other method :) ");
				}
			case 0:
                System.out.println("Exiting...");
                System.out.println("\n--------------------------------------------------");
                break;
            default:
                System.out.println("Invalid choice! Please try again.");
            }
        }while(choice != 0); 
	}

}
