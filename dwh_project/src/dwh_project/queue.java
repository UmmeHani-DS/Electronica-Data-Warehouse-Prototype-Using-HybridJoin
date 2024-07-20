package dwh_project;

class Node  
{
	private int data;
	private Node next;
	private Node prev;
	
	public void setData(int d)
	{
	   this.data = d;	
	}
	
	public int getData()
	{
		return this.data;
	}
	
	public void setNext(Node n)
	{
		this.next = n;
	}
	
	public Node getNext()
	{
		return this.next;
	}
	
	public void setPrev(Node p)
	{
		this.prev = p;
	}
	
	public Node getPrev()
	{
		return this.prev;
	}
	
}


public class queue 
{
	private Node front;
    private Node rear;
    
    public void enqueue(int d)
    {
        Node newnode = new Node();
        
        newnode.setData(d);
        
        if (rear == null)
        {
            front = newnode;
            rear = newnode;
        } 
        else 
        {
            rear.setNext(newnode);
            newnode.setPrev(rear);
            rear = newnode;
        }
    }
    
    public void dequeue(Node n)
    {
        if(n == front && n == rear)
        {
        	front = null;
        	rear = null;
        }
        
        else if(n == front)
        {
        	front = front.getNext();
        	front.setPrev(null);
        }
        
        else if(n == rear)
        {
        	rear = rear.getPrev();
        	rear.setNext(null);
        }
        
        else
        {
        	Node temp1 = n.getPrev();
        	temp1.setNext(n.getNext());
        	
        	Node temp2 = n.getNext();
        	temp2.setPrev(n.getPrev());
        	
        }
        
    }
    
    public int pop()
    {
        if (front == null) 
        {
            return -1; 
        }
        
        else 
        {
            int poppedData = front.getData();
            front = front.getNext();

            if (front == null) 
            {
                rear = null; 
            } 
            
            else 
            {
                front.setPrev(null); 
            }

            return poppedData;
        }
    }

    public void display()
    {
    	Node current = front;
    	
    	while(current != null)
    	{
    		System.out.print(current.getData() + " ");
    		current = current.getNext();
    	}
    	
    	System.out.println();
    }

}
