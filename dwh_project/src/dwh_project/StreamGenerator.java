package dwh_project;

import java.sql.*;
import java.util.*;
import java.util.concurrent.BlockingQueue ;  
import java.util.concurrent.ArrayBlockingQueue ;  
 
class StreamGenerator extends Thread 
{
	  public BlockingQueue <transaction> tuple = new ArrayBlockingQueue <transaction> (1000);
	  public String url1;
	  public String url2;
      public String username;
      public String password;
	  @Override
	  public void run() 
	    {
		    Scanner scanner = new Scanner(System.in);
		    
		    System.out.println("Enter url for Transaction DB: ");
	        url1 = scanner.nextLine();
	        
	        System.out.println("Enter url for MasterData DB: ");
			url2 = scanner.nextLine();
	       
	        System.out.println("Enter your username: ");
	        username = scanner.nextLine();
	        
	        System.out.println("Enter your password: ");
	        password = scanner.nextLine();
	        
	        scanner.close();
	        
	        try 
	        {
		            Connection c = DriverManager.getConnection(url1, username, password);
		            Statement s = c.createStatement();
	                ResultSet rs = s.executeQuery("Select * from sales limit 1000");
		         
		            while(rs.next())
		            {
		            	transaction row = new transaction();
		            	
		            	row.OrderID = rs.getInt("OrderID");
		            	row.OrderDate = rs.getString("OrderDate");
		            	row.ProductID = rs.getInt("ProductID");
		            	row.CustomerID = rs.getInt("CustomerID");
		            	row.CustomerName = rs.getString("CustomerName");
		            	row.Gender = rs.getString("Gender");
		            	row.QuantityOrdered = rs.getInt("QuantityOrdered");
		            	
		            	tuple.put(row);
		            }
		     } 
	        catch (Exception e)
	        {
		         System.out.println("An exception occurred: " + e);
		    }
	    }
	  
}




