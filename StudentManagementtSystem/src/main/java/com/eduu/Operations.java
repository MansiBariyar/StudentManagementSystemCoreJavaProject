package com.eduu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Operations
{
	private static Connection conn;
	private static Statement st;
	private static  String sql;
	private static ResultSet rs;
	private static PreparedStatement pst;
	
	private static int id,courseid,adminid;
	private static String name,email,password,adminpassword,adminname;
	private static long phone;
	private static int age;
	private static int i;
	private static Scanner sc=new Scanner(System.in);

	//how to create student account
	public static void createaccount() throws SQLException, ClassNotFoundException 
	{
		conn=DatabaseConnection.getConnection();
		//String sql="insert into student_details(name,age,email,phone,courseid,password) values(?,?,?,?,?,?)";
		//pst=conn.prepareStatement(sql);
		System.out.println("--------------------Add Details-------------------");
		//String sql1="select max(id) as id from student_details";
		//PreparedStatement pst1=conn.prepareStatement(sql1);
		//rs=pst1.executeQuery();
		//if(rs.next())
		//{
			//id=rs.getInt("id");
		//}
		
		System.out.println("Enter name:");
		name=sc.next();
		System.out.println("Enter age:");
		age=sc.nextInt();
		System.out.println("Enter email:");
		email=sc.next();
		System.out.println("Enter phoneno:");
		phone=sc.nextLong();
		//Operations.showcourse();
		
			Operations.showcourse();
		
		System.out.println("Choose your courseid:");
		courseid=sc.nextInt();
		System.out.println("Enter password:");
		password=sc.next();
	    //sc.close();
		//pst.setInt(1, id);
		String sql="insert into student_details(name,age,email,phone,courseid,password) values(?,?,?,?,?,?)";
		pst=conn.prepareStatement(sql);
		pst.setString(1, name);
		pst.setInt(2, age);
		pst.setString(3, email);
		pst.setLong(4, phone);
		pst.setInt(5, courseid);
		pst.setString(6, password);
		
		int i = pst.executeUpdate();
		if(i>0)
		{
			//name=rs.getString(1);
			System.out.println("------------ Welcome------------");
			System.out.println();
			System.out.println("********************************Your account created successfully**********************************");
			System.out.println();
			
			
		}
		else
		{
			System.out.println("not created");
		}
		
	}
         		
	

	public static void createlogin() throws SQLException 
	{
		
		while(true)
		{	
			System.out.println("********************************************************************************************");
			System.out.println("-----------------------------------Select Option---------------------------------------------");
			System.out.println("*********************************************************************************************");
			System.out.println("1. Admin Login");
			System.out.println("2. Student Login");
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter your choice: ");
			int choice=sc.nextInt();
			switch (choice) {
			case 1:Operations.adminlogin();
				break;
			case 2:Operations.studentlogin();
				break;
			
			default:System.out.println("Invalid choice");
				break;
			}
		}
	}

	public static void studentlogin() throws SQLException {
		conn=DatabaseConnection.getConnection();
		st = conn.createStatement();
		System.out.println("---------- Enter details for login ----------");
		System.out.println();
		System.out.println("Enter your email:");
		email=sc.next();
		System.out.println("Enter your password:");
		password=sc.next();
		sql="select name from student_details where email=? and password=?";
		pst=conn.prepareStatement(sql);
		pst.setString(1,email);
		pst.setString(2,password);
		rs=pst.executeQuery();
		if(rs.next())
		{
			name=rs.getString(1);
			System.out.println("Welcome "+name +"... ");
			System.out.println("*********************************Login successsful********************************************");
			System.out.println();
			//System.out.println();
		
		Operations.studenthome2();
		}
		else
		{
			System.out.println("***********Invalid email and password**************");
			System.out.println();
			System.out.println("***********Thank you*****************************");
		}
		
		
	}

	public static void studenthome2() throws SQLException
	{
		while(true)
		{
			
			System.out.println("............Select Option you want to perform  ..........");
			System.out.println();
			System.out.println("1. View your details.");
			System.out.println("2. Update your profile.");
			System.out.println("3. Logout.");
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter your choice: ");
			int choice=sc.nextInt();
			switch (choice) {
			case 1:Operations.viewprofile();
				break;
			case 2:Operations.updatestudentprofile();
				break;
		    case 3:System.out.println("******************** Thank you for using Student Management System ********************");
		    	System.exit(0);
				break;
			default:System.out.println("****** Invalid choice *******");
				break;
			}
		}
	}

	public static void adminlogin() throws SQLException {
		Operations.adminhome1();
	}

	public static void adminhome1() throws SQLException {
		conn=DatabaseConnection.getConnection();
		sql="select * from admin_details where adminid=? and adminpassword=?";
		
		System.out.println("Enter your details");

		System.out.println("Enter your id:");
		adminid=sc.nextInt();
		System.out.println("Enter your password:");
		adminpassword=sc.next();
	
		pst=conn.prepareStatement(sql);
		pst.setInt(1,adminid);
		pst.setString(2,adminpassword);
		rs=pst.executeQuery();
		if(rs.next())
		{
			adminname=rs.getString(2);
			System.out.println("Welcome "+adminname +"... ");
			System.out.println("******************************** Login successsful *******************************************");
			System.out.println();
			
		
		Operations.adminhome2();
		}
		else
		{
			System.out.println("******************************** Invalid id and password ************************************");
			System.out.println();
			System.out.println("------------------------- Thank you ------------------------");
			System.out.println();
		}
	}
		
		public static void adminhome2() throws SQLException {
		while(true)
		{	System.out.println("************************************************************************************************");
			System.out.println("----------------------------------Select Options------------------------------------------------");
			System.out.println("*************************************************************************************************");
			System.out.println();
			System.out.println("1. Update student profile.");
			System.out.println("2. Delete student profile.");
			System.out.println("3. Search student profile.");
			System.out.println("4. Display all student profiles.");
			System.out.println("5. Logout.");
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter your choice: ");
			int choice=sc.nextInt();
			switch (choice) {
			case 1:Operations.updatestudentprofile();
				
				break;
			case 2:Operations.deleteprofile();
				break;
			case 3:Operations.searchstudentprofile();
			    break;
			case 4:Operations.showpallprofile();
		    	break;
		    
			    
		    case 5:System.out.println("*********************** Thank you for using Student Management System ************************");
		    	System.exit(0);
				break;
			default:System.out.println("************* Invalid choice **************");
				break;
			}
		}
	}
	public static void showpallprofile() throws SQLException {
		
		conn=DatabaseConnection.getConnection();
		st = conn.createStatement();
		sql = "select * from student_details";
		
		rs = st.executeQuery(sql);
		
		System.out.println("Id\tName\tAge\tPhone\tCourseID\tPassword");
		System.out.println("__________________________________________________________________________");
		while(rs.next()) {
			id=rs.getInt("id");
			 
			name = rs.getString("name");
			age= rs.getInt("age");
			phone = rs.getLong("phone");
			courseid = rs.getInt("courseid");
			password = rs.getString("password");
		
			
			System.out.println(id+"\t"+name+"\t"+age+"\t"+phone+"\t"+courseid+"\t"+password);
		}
		
		
	}



	public static void searchstudentprofile() throws SQLException
	{
		conn=DatabaseConnection.getConnection();
		st = conn.createStatement();
		
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter id to view your record: ");
		
		String query="select * from student_details where id=?";
	    PreparedStatement statement = conn.prepareStatement(query);
	    id= sc.nextInt();
		statement.setInt(1,id);
	    rs=statement.executeQuery();
	    if(rs.next()) 
	    {
	    	name=rs.getString(3);
	    	System.out.println("****** Details of  "+name +"... ");
	    	System.out.println("ID: "+rs.getInt("id"));
			
			System.out.println("Name: "+rs.getString("name"));
			System.out.println("Age: "+rs.getInt("age"));
			System.out.println("Email: "+rs.getString("email"));
			System.out.println("Phone: "+rs.getLong("phone"));
			System.out.println("CourseId: "+rs.getInt("courseid"));
			
		}
	    else 
	    {
			System.out.println("No results found for student: "+id);
		}
		
	}



	public static void deleteprofile() throws SQLException 
	{
		
			conn=DatabaseConnection.getConnection();
			String sql="delete from student_details where id=?";
			pst=conn.prepareStatement(sql);
			Scanner sc=new Scanner(System.in);
			System.out.println("enter id");
		    id=sc.nextInt();
			pst.setInt(1,id);
			int i=pst.executeUpdate();
			if(i>0) 
			{
				System.out.println("Record deleted");
	
			} 
			else 
			{
				System.out.println("not deleted");
			}
	}

			
			
	public static void updatestudentprofile() throws SQLException{
		
		conn=DatabaseConnection.getConnection();
		String sql="select * from student_details where id=?";
		pst=conn.prepareStatement(sql);
			
		System.out.println("Enter id:");
		id=sc.nextInt();
			
		pst.setInt(1,id);
		rs=pst.executeQuery();
		if(rs.next()) 
		{
				System.out.println("Choose option");
				System.out.println("1.To change name");
				System.out.println("2.Change age");
				System.out.println("3.Change email");
				System.out.println("4.change phone no");
				System.out.println("5.change password");
				System.out.println("enter your choice");
				int upchoice=sc.nextInt();
				switch(upchoice)
				{
				case 1:
					
					sql="update student_details set name=? where id=?";
					pst= conn.prepareStatement(sql);
					System.out.println("Enter your new name:");
					name=sc.next();
					pst.setString(1,name);
					System.out.println("Enter your id:");
					id=sc.nextInt();
					pst.setInt(2,id);
					i=pst.executeUpdate();
					if(i>0) 
					{
						System.out.println("Name is updated");
					}
					else 
					{
						System.out.println("Name is not updated");
					}
					break;
					
				case 2:
					System.out.println("Enter age to update");
					int age=sc.nextInt();
					sql="update student_details set age=? where id=?";
					pst=conn.prepareStatement(sql);
					pst.setInt(1,age);
					//System.out.println("Enter your id:");
					//id=sc.nextInt();
					pst.setInt(2,id);
					i= pst.executeUpdate();
					if(i>0) 
					{
						System.out.println("Age updated ");
					}
					else 
					{
						System.out.println("Age not updated");
					}
					break;
					
				case 3:
					System.out.println("Enter email to change");
					String email=sc.next();
					sql="update student_details set email=? where id=?";
					pst=conn.prepareStatement(sql);
					pst.setString(1,email);
					System.out.println("Enter your id:");
					id=sc.nextInt();
					pst.setInt(2,id);
					i=pst.executeUpdate();
					if(i>0)
					{
						System.out.println("Email is updated");
					}
					else
					{
						System.out.println("Email is not updated");
					}
					break;
					
				case 4:
					System.out.println("Enter phone to change");
					long phone=sc.nextLong();
					sql="update student_details set phone=? where id=?";
					pst=conn.prepareStatement(sql);
					pst.setLong(1,phone);
					System.out.println("Enter your id:");
					id=sc.nextInt();
					pst.setInt(2,id);
					i=pst.executeUpdate();
					if(i>0)
					{
						System.out.println("Phone is updated");
					}
					else 
					{
						System.out.println("Phone is not updated");
					}
					break;
				case 5:
					System.out.println("Enter new password to change");
					String password=sc.next();
					sql="update student_details set password=? where id=?";
					pst= conn.prepareStatement(sql);
					pst.setString(1,password);
					System.out.println("Enter your id:");
					id=sc.nextInt();
					pst.setInt(2,id);
					i=pst.executeUpdate();
					if(i>0) 
					{
						System.out.println("Password is updated");
					}
					else 
					{
						System.out.println("Password is not updated");
					}
					break;
				default:
					System.out.println("Invalid option");
				}
			}
			else 
			{
				System.out.println(id+" does not exist");
			}
	}

	public static void showprofile() throws SQLException 
	{
		conn=DatabaseConnection.getConnection();
		st = conn.createStatement();
		
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter id to view your record: ");
		
		String query="select * from student_details where id=?";
	    PreparedStatement statement = conn.prepareStatement(query);
	   
	    System.out.println("Enter your id:");
		id=sc.nextInt();
		statement.setInt(1,id);
	    rs=statement.executeQuery();
	    if(rs.next()) 
	    {
	    	name=rs.getString(1);
	    	System.out.println("****** Details of  "+name +"... ");
			System.out.println("Id: "+rs.getInt("id"));
			System.out.println("Name: "+rs.getString("name"));
			System.out.println("Age: "+rs.getInt("age"));
			System.out.println("Email: "+rs.getString("email"));
			System.out.println("Phone: "+rs.getLong("phone"));
			System.out.println("CourseId: "+rs.getInt("courseid"));
			System.out.println();
			
		}
	    else 
	    {
			System.out.println("No results found for student: "+id);
		}
	}

		
	


	
	public static void viewprofile() throws SQLException {
		conn=DatabaseConnection.getConnection();
		st = conn.createStatement();
		
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter email to view your record: ");
		
		
		String query="select * from student_details where email=?";
	    PreparedStatement statement = conn.prepareStatement(query);
	   // System.out.println("Enter your id:");
	    email=sc.next();
		statement.setString(1,email);
	    rs=statement.executeQuery();
	    if(rs.next()) 
	    {
	    	System.out.println("****** Your Details ******** ");
	    	System.out.println("Id: "+rs.getInt("Id"));
			System.out.println("Name: "+rs.getString("name"));
			System.out.println("Age: "+rs.getInt("age"));
			System.out.println("Email: "+rs.getString("email"));
			System.out.println("Phone: "+rs.getLong("phone"));
			System.out.println("CourseId: "+rs.getInt("courseid"));
			
		}
	    else 
	    {
			System.out.println("No results found for student: "+id);
		}
	}
	public static void showcourse() throws SQLException,ClassNotFoundException {
		conn=DatabaseConnection.getConnection();
		String sql1="select * from course";
		pst=conn.prepareStatement(sql1);
		rs=pst.executeQuery();
		if(rs.next())
		{
			System.out.println("*******Course Details*********");
			System.out.println("+-------------------------------------------------+");
			System.out.println("|CourseId\tCoursename\t\tCoursefee |");
			System.out.println("+-------------------------------------------------+");
			do {
				int courseid=rs.getInt("courseid");
				String coursename=rs.getString("coursename");
				float coursefee=rs.getFloat("coursefee");
				
				System.out.println("|"+courseid+"\t\t"+coursename+"\t\t\t"+coursefee+"\t  |");
			}while(rs.next());
			System.out.println("+-------------------------------------------------+");
		}
		else {
			System.err.println("Course details not found");
		}
		
		
		
	}
}



	
		
	


