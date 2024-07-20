package dwh_project;

public class transaction
{
	public int OrderID;
    public String OrderDate;
    public int ProductID; 
    public int CustomerID;
    public String CustomerName; 
    public String Gender;
    public int QuantityOrdered;
    
    public void display()
    {
    	System.out.println("Order ID: " + OrderID);
    	System.out.println("Order Date: " + OrderDate);
    	System.out.println("Product ID: " + ProductID);
    	System.out.println("Customer ID: " + CustomerID);
    	System.out.println("Customer Name: " + CustomerName);
    	System.out.println("Gender: " + Gender);
    	System.out.println("QuantityOrdered: " + QuantityOrdered);
    	
    }
    

}
