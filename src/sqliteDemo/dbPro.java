package sqliteDemo;

import java.sql.*;
import java.util.Scanner;

//SQLException;
//import java.sql.Connection;

public class dbPro {
	// CRUD Operation Using Java with SQLite Database
	// Create Read Update Delete
	public static void main(String[] args) throws Exception{
		Class.forName("org.sqlite.JDBC");
		Connection con = DriverManager.getConnection("jdbc:sqlite:/C:\\db\\vasanth.db");
		
		Statement stmt = con.createStatement();
//		String qry = "SELECT ID,NAME,AGE,CITY from users";
		ResultSet rs;
		PreparedStatement st;
		
		String qry="";
		int id,age,choice;
		String name,city;
		
		Scanner in = new Scanner(System.in);
		Scanner str = new Scanner(System.in);
		
		while(true) {
			System.out.println("SQLite Java CRUD Operation");
			System.out.println("1. Insert");
			System.out.println("2. Update");
			System.out.println("3. Delete");
			System.out.println("4. Select");
			System.out.println("5. Exit");
			System.out.println("Enter a choice: ");
			choice = in.nextInt();
			System.out.println("----------------------------");
			
			switch(choice) {
			case 1:
				System.out.println("1. Insert New Data");
				
				System.out.println("Enter Name: ");
				name=str.nextLine();
				System.out.println("Enter Age: ");
				age= in.nextInt();
				System.out.println("Enter City: ");
				city = str.nextLine();
				
				qry = "insert into users(NAME,AGE,CITY) VALUES (?,?,?)";
				st = con.prepareStatement(qry);
				
				st.setString(1,name);
				st.setInt(2, age);
				st.setString(3, city);
				st.executeUpdate();
				System.out.println("Data Insert Success");
				
				break;
			case 2:
				System.out.println("2. Updating a data");
				
				System.out.println("Enter ID: ");
				id = in.nextInt();
				System.out.println("Enter Name: ");
				name = str.nextLine();
				System.out.println("Enter Age: ");
				age = in.nextInt();
				System.out.println("Enter City: ");
				city= str.nextLine();
				
				qry="update users set NAME=?,AGE=?,CITY=? where ID=?";
				
				st = con.prepareStatement(qry);
				
				st.setString(1, name);
				st.setInt(2, age);
				st.setString(3, city);
				st.setInt(4, id);
				st.executeUpdate();
				System.out.println("Data Updated Successfully");
				
				break;
			case 3:
				System.out.println("3. Deleted");
				
				System.out.println("Enter ID: ");
				id=in.nextInt();
				
				qry = "delete from users where ID=?";
				st = con.prepareStatement(qry);
				st.setInt(1, id);
				
				st.executeUpdate();
				System.out.println("Data Deleted Success");
				
				break;
			case 4:
				System.out.println("4. print all Records");
				qry = "SELECT ID,NAME,AGE,CITY from users";
				rs = stmt.executeQuery(qry);
				while(rs.next()) {
					id = rs.getInt("ID");
					name=rs.getString("NAME");
					age = rs.getInt("AGE");
					city = rs.getString("CITY");
					
					System.out.print(id+" ");
					System.out.print(name+" ");
					System.out.print(age+" ");
					System.out.println(city+" ");
				}
				break;
			case 5:
				System.out.println("Thank You");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid Selection");
				break;
			}
			System.out.println("----------------------------");
		}
	}

}
