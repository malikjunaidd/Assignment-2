package Home;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class login 
{
	JFrame ff;
	
	JLabel l1,l2,l3,l4,l5;
	JTextField t1,t2,t3,t4,t5;
	JPanel p1,p2,p3,p4,p5,pb;
	JButton b1,b2,b3;
	
	public login() 
	{
	
		ff = new JFrame("Login Page");
		ff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ff.setSize(400,400);
		
		
		
		l1 = new JLabel("Student Registration No : ");
		
		l2 = new JLabel("Student Name :            ");
		
	    l3 = new JLabel("Section :                 ");
		
		l4 = new JLabel("Semester :                ");
		
		l5 = new JLabel("Degree :                  ");
		
		
		ff.setLayout(new FlowLayout());
		t1 = new JTextField(15);
		t2 = new JTextField(15);
		t3 = new JTextField(15);
		t4 = new JTextField(15);
		t5 = new JTextField(15);
		
		 p1 = new JPanel();
		
		
		
		p1.add(l1);
		p1.add(t1);
		
		ff.add(p1);
		
		 p2 = new JPanel();
		
		p2.add(l2);
		p2.add(t2);
		ff.add(p2);
		
		 p3 = new JPanel();
		
		p3.add(l3);
		p3.add(t3);
		
		ff.add(p3);
		
		p4 = new JPanel();
		
		p4.add(l4);
		p4.add(t4);
		
		ff.add(p4);
		
		p5 = new JPanel();
		p5.add(l5);
		p5.add(t5);
		ff.add(p5);
		
		pb = new JPanel();
		
		 b1 = new JButton("Reset");
		b1.addActionListener(new ActionListener()
				{
			public void actionPerformed(ActionEvent e)
			{
				empty();
				
			}
			
				});
		 b2 = new JButton("Submit");
		b2.addActionListener(new ActionListener()
		{
	public void actionPerformed(ActionEvent e)
	{
		senddata();
		 
	}
	
	}
		
	);
		 b3 = new JButton("Show");
			b3.addActionListener(new ActionListener()
					{
				public void actionPerformed(ActionEvent e)
				{
					showdata();
				}
				
					});
		pb.add(b1);
		pb.add(b2);
		pb.add(b3);
		
		ff.add(pb);
		

		
		ff.setVisible(true);
		
	}
	public void empty()
	{
		t1.setText("");
		t2.setText("");
		t3.setText("");
		t4.setText("");
		t5.setText("");
	}
	public void senddata()
	{
		try
	    {
	      // create a mysql database connection
	      
	      String myUrl = "jdbc:mysql://localhost:3306/studentInfo";
	      Class.forName("com.mysql.cj.jdbc.Driver");
	      Connection conn = DriverManager.getConnection(myUrl, "root", "root");
	    
	      // create a sql date object so we can use it in our INSERT statement
	      
	      String query = " insert into student (stuReg, stuName, section, semester, degree)"
	        + " values (?, ?, ?, ?, ?)";

	     String s1 = t1.getText();
	     String s2 = t2.getText();
	//     String s3 = t3.getText();
	     String s4 = t4.getText();
	     String s5 = t5.getText();
	     
	     int n1 = Integer.parseInt(s1);
	     int n4 = Integer.parseInt(s4);
	    // char c = s3.charAt(0);
	  //   int a = c;
	     
	     
	      PreparedStatement preparedStmt = conn.prepareStatement(query);
	      preparedStmt.setInt(1, n1);
	      preparedStmt.setString (2, s2);
	      preparedStmt.setString(3,"");
	      preparedStmt.setInt(4, n4);
	      preparedStmt.setString(5, s5);

	      preparedStmt.execute();
	      
	      conn.close();
	      empty();
	      
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception!");
	      System.err.println(e.getMessage());
	    }
	  

	}
	public void showdata()
	{
		JFrame frame2;
		 DefaultTableModel  defaultTableModel;
		 
		 
		 JTable table;
		 frame2 = new JFrame("Database Results");
	        frame2.setLayout(new FlowLayout());
	        frame2.setSize(400, 400);
	 
	        //Setting the properties of JTable and DefaultTableModel
	        defaultTableModel = new DefaultTableModel();
	        table = new JTable(defaultTableModel);
	        table.setPreferredScrollableViewportSize(new Dimension(300, 100));
	        table.setFillsViewportHeight(true);
	        frame2.add(new JScrollPane(table));
	        defaultTableModel.addColumn("Student Registration no");
	        defaultTableModel.addColumn("Student Name");
	        defaultTableModel.addColumn("Section");
	        defaultTableModel.addColumn("Semester");
	        defaultTableModel.addColumn("Degree");
	        
	 
	        Connection connection;
	        Statement statement;
	 
	 
	        try {
	        
	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentInfo", "root", "root");//Crating connection with database
	            statement = connection.createStatement();//crating statement object
	            String query = "select * from STUDENT ";//Storing MySQL query in A string variable
	            ResultSet resultSet = statement.executeQuery(query);//executing query and storing result in ResultSet
	 
	 
	            while (resultSet.next()) {
	            
	             //Retrieving details from the database and storing it in the String variables
	                int id = resultSet.getInt("stuReg");
	                String name = resultSet.getString("stuName");
	                String sec = resultSet.getString("section");
	                int sem = resultSet.getInt("semester");
	                String deg = resultSet.getString("degree");
	                
	                
	                    defaultTableModel.addRow(new Object[]{id,name,sec,sem,deg});//Adding row in Table
	                    frame2.setVisible(true);//Setting the visibility of second Frame
	                  //  frame2.validate();
	                  //  break;
	                
	 
	            }
	            
	           

    }
	        catch(Exception e)
            {
            	e.printStackTrace();
            }
 
	}

	public static void main(String args[])
	{
		login ll = new login();
	}

}
