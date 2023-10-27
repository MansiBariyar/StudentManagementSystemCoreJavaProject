package com.eduu;

import java.sql.SQLException;
import java.util.Scanner;

public class StudentMain 
{
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		String c;
		while(true)
		{
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			System.out.println("            WELCOME TO STUDENT MANAGEMENT SYSTEM                  ");
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			System.out.println("------------------------ Select Option ----------------------------------------------------");
			System.out.println("1. Create Account");
			System.out.println("2. Login");
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter your choice: ");
			int choice=sc.nextInt();
			switch (choice) {
			case 1:Operations.createaccount();
				break;
			case 2:Operations.createlogin();
				break;
			
			default:System.out.println("Invalid choice");
			}
			System.out.println("do u want to continue press y or n");
			c=sc.next();
			if(c.equalsIgnoreCase("y")) {
				continue;
			}
			else if(c.equalsIgnoreCase("n")) {
				break;
			}
		}
	}
}
