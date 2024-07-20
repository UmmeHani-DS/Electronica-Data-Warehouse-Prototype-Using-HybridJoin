package dwh_project;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import java.util.*;
import java.util.Date;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.IOException;


public class hybridjoin extends Thread
{
	  public StreamGenerator stream1 = new StreamGenerator();
	  public queue q = new queue();
	  public MultiValuedMap<Integer, Object> HashMap = new ArrayListValuedHashMap<>();
	  public List<List<String>> masterdata = new ArrayList<List<String>>(10);
	  public int key;
      public Statement s;
      public Statement s1;
	
	  @Override
	  public void run() 
	  {
			   	 stream1.start();           // starting the stream for transaction data            
				 transaction buffer = null;      //  buffer to store the transaction data
		       
			// Putting values in the buffer
				 
				try 
				{
					buffer = stream1.tuple.take();  
					
				} 
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				
				try 
				{
					Connection c;
					c = DriverManager.getConnection(stream1.url2, stream1.username, stream1.password);
		       	    s1 = c.createStatement();
		       	    
		       	    Connection c1;
					c1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/electronica-dw", "root", "root");
		       	    s = c.createStatement();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
				
		// Hybridjoin Working starts here	        
					
				while (buffer != null)
				{
						q.enqueue(buffer.ProductID);
						
						  try 
					        {
					       	    key = q.pop(); 
					
					            ResultSet rs = s.executeQuery("SELECT * FROM masterdata WHERE productID >= " + key + " ORDER BY productID LIMIT 10");
					         
					            while(rs.next() && masterdata.size() < 10)
					            {
					            	List <String> data = new ArrayList <> ();
					            	
					            	data.add(rs.getString("ProductID"));
					            	data.add(rs.getString("productName"));
					            	data.add(rs.getString("productPrice"));
					            	data.add(rs.getString("supplierID"));
					            	data.add(rs.getString("supplierName"));
					            	data.add(rs.getString("storeID"));
					            	data.add(rs.getString("storeName"));	
					            	
					            	masterdata.add(data);	
					           }
					            
						            
							 } 
					        catch (Exception e)
					        {
						         System.out.println("An exception occurred: " + e);
					
					        } 
						
						
						  HashMap.putAll(buffer.ProductID , List.of(Arrays.asList(buffer.OrderID, buffer.OrderDate,
								  buffer.CustomerID, buffer.CustomerName,buffer.Gender , buffer. QuantityOrdered)));
						 
						  String orderdate = buffer.OrderDate;
						  
						  SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
						  Date date = null;
						try {
							date = sdf.parse(orderdate);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						  
						  Calendar calendar = Calendar.getInstance();
						  calendar.setTime(date);
						  int day = calendar.get(Calendar.DAY_OF_WEEK);
						  int week = calendar.get(Calendar.WEEK_OF_YEAR);
						  int year = calendar.get(Calendar.YEAR);
						  int month = calendar.get(Calendar.MONTH) + 1;
						  int quarter = (month - 1) / 3 + 1;
						 
				        System.out.println(day + " " + week + " " + month + " " + quarter + " " + year);
						 
				        try {
				        	 ResultSet mf = s.executeQuery("insert ignore into timeDimension values(" + day + "," + week + "," + month + "," + quarter + "," + year + ")");
						} catch (SQLException e) {
							e.printStackTrace();
						}
				        
						  
						  //System.out.println(orderdate);
						//Object value = HashMap.get(key);
						
						//System.out.println(value);
						
			            buffer = stream1.tuple.poll(); 
			            
			           try 
			            {
							Thread.sleep(10);
						} 
			           catch (InterruptedException e)
			           {
							e.printStackTrace();
						}
			            		
		          }
			
			 //String url = "jdbc:mysql://localhost:3306/masterdata";
								 
		                 		
	 }
	  
	 public static void main(String[] args) throws NumberFormatException, IOException, InterruptedException, SQLException
		{
			hybridjoin h = new hybridjoin();
			h.start();
		}

}
