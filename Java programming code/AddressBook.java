/*******************************************************************************************************
 * Collaborative Project created by Lizelle Castro and Bailey Stoneman
 * Purpose:	This project is intended to allow the user to submit Address Book information
		such as first and last names, home addresses, phone numbers, and email addresses
		that is then uploaded in a database of address book records via internet. Additionally, a 
		graphical user interface appears upon the execution of this program and allows
		the user to easily search, update, delete, and print existing records.
********************************************************************************************************/

	import java.awt.*;
	import java.sql.*;
	import java.util.*;
	import javax.swing.*;
	import java.awt.event.*;
	import java.awt.print.*;
	import java.awt.Frame.*;
	

	public class AddressBook extends JFrame implements Printable, ActionListener {


		    public static final int WIDTH = 800;
			public static final int HEIGHT = 600;
			public JInternalFrame mFrame;
			public JTextField f1;
			public JTextField l1;
			public JTextField address;
			public JTextField addressLine2;
			public JTextField city1;
			public JTextField state1;
			public JTextField zipC;
			public JTextField emailAdd;
			public JTextField phoneNumber;
			// you need to add more text fields here

			public String firstname;
			public String lastname;
			public String address1;
			public String address2;
			public String city;
			public String state;
			public String zipCode;
			public String email;
			public String phone;
			public int personID;


		public AddressBook()
	    {

	      	super();
			setSize(WIDTH, HEIGHT);
			setLocation(80,80); // setting the location on the screen
			setTitle("Button Demo");
			Container contentPane = getContentPane();
			contentPane.setBackground(Color.gray);
		//	addWindowListener(new WindowDestroyer());

			JDesktopPane dtp = new JDesktopPane();
			setContentPane(dtp);

	    	JPanel buttonPanel = new JPanel(); // Java panel to hold buttons
	    	buttonPanel.setLayout(new FlowLayout());// Setting the layout of buttons

	    	JPanel textPanel = new JPanel();// Java panel to hold labels and text fields
	    	textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.PAGE_AXIS));


	    	JPanel FirstName = new JPanel();
	    	FirstName.setLayout(new FlowLayout());
	    	JLabel fname = new JLabel("First Name");
	    	f1 = new JTextField(10);

	    	JPanel LastName = new JPanel();
	    	LastName.setLayout(new FlowLayout());
	    	JLabel lname = new JLabel("Last Name");
	    	l1 = new JTextField(10);


	    	JPanel addrPanel = new JPanel();
	    	addrPanel.setLayout(new FlowLayout());
	    	JLabel addr = new JLabel("Address");
	    	address = new JTextField(10);

	    	JPanel addrPanel2 = new JPanel();
			addrPanel2.setLayout(new FlowLayout());
			JLabel addr2 = new JLabel("Address 2");
	    	addressLine2 = new JTextField(10);
	    	
	    	JPanel cityPanel = new JPanel();
	    	cityPanel.setLayout(new FlowLayout());
	    	JLabel city = new JLabel("City");
	    	city1 = new JTextField(10);
	    	
	    	JPanel statePanel = new JPanel();
	    	statePanel.setLayout(new FlowLayout());
	    	JLabel state = new JLabel("State");
	    	state1 = new JTextField(10);
	    	
	    	JPanel zipPanel = new JPanel();
	    	zipPanel.setLayout(new FlowLayout());
	    	JLabel zipCode = new JLabel("Zip Code");
	    	zipC = new JTextField(10);
	    	
	    	JPanel emailPanel = new JPanel();
	    	emailPanel.setLayout(new FlowLayout());
	    	JLabel email = new JLabel("Email");
	    	emailAdd = new JTextField(10);
	    
	    	
	    	JPanel phonePanel = new JPanel();
	    	phonePanel.setLayout(new FlowLayout());
	    	JLabel phoneNum = new JLabel("PhoneNumber");
	    	phoneNumber = new JTextField(10);
	    	

	    	FirstName.add(fname);
	    	FirstName.add(f1);
	    	LastName.add(lname);
	    	LastName.add(l1);
	    	addrPanel.add(addr);
	    	addrPanel.add(address);
	    	addrPanel2.add(addr2);
	    	addrPanel2.add(addressLine2);
	    	cityPanel.add(city);
	    	cityPanel.add(city1);
	    	statePanel.add(state);
	    	statePanel.add(state1);
	    	zipPanel.add(zipCode);
	    	zipPanel.add(zipC);
	    	emailPanel.add(email);
	    	emailPanel.add(emailAdd);
	    	phonePanel.add(phoneNum);
	    	phonePanel.add(phoneNumber);


	    	//continue adding the text fields

	    	textPanel.add(FirstName);
	    	textPanel.add(LastName);
	    	textPanel.add(addrPanel);
	    	textPanel.add(addrPanel2);
	    	textPanel.add(cityPanel);
	    	textPanel.add(statePanel);
	    	textPanel.add(zipPanel);
	    	textPanel.add(emailPanel);
	    	textPanel.add(phonePanel);

	    	mFrame = new JInternalFrame("First frame", true,true, true, true);
	    	mFrame.setLayout(new BorderLayout());

	    	JButton insertButton = new JButton("Insert");
	    	insertButton.addActionListener(this);

	    	JButton deleteButton = new JButton("Delete");
	    	deleteButton.addActionListener(this);

	    	JButton updateButton = new JButton("Update");
	    	updateButton.addActionListener(this);
	        // Search button bookmark
	    	//
	    	//
	    	JButton searchButton = new JButton("Search");
	    	searchButton.addActionListener(this);
	    	
	    	JButton printButton = new JButton("Print");
	    	printButton.addActionListener(this);

	    	buttonPanel.add(insertButton);
	    	buttonPanel.add(updateButton);
	    	buttonPanel.add(deleteButton);
	    	buttonPanel.add(searchButton);
	    	buttonPanel.add(printButton);

	    	mFrame.setSize(400, 300);
	    	mFrame.setLocation(50, 50);

	    	mFrame.add(textPanel, BorderLayout.WEST); // adds textPanel to the main frame of the window
	    	mFrame.add(buttonPanel, BorderLayout.SOUTH);// adds buttons to the main frame
	    	mFrame.setVisible(true);
	    	dtp.add(mFrame);


	   }
	   public void actionPerformed(ActionEvent e)
		{
			if(e.getActionCommand().equals("Insert"))
			{
				try {

					Class.forName("org.sqlite.JDBC");
					Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\NESki\\OneDrive\\Desktop\\project.db");

			        Statement statement = connection.createStatement();

			        firstname = f1.getText();   //gets the value from the text field (first name)
			        lastname = l1.getText();    // gets the value from the text field (last name)
			        address1 = address.getText();  // gets the value from the text field (address 1)
			        address2 = addressLine2.getText(); // gets the value from the text field (address 2)
			        city = city1.getText();
			        state = state1.getText();
			        zipCode = zipC.getText();
			        email = emailAdd.getText();    // gets the value fron the text field (email address)
			        phone = phoneNumber.getText(); // gets the value from the text field (phone number)
			        StringBuffer results = new StringBuffer();


			        statement.executeUpdate("INSERT INTO names (firstName, lastName) VALUES ('"+firstname+"','"+lastname+"')"); //inserts a record into names table
			        
			        ResultSet resultSet = statement.executeQuery( "SELECT personID FROM names Where firstname = ('"+firstname+"')" );
			        ResultSetMetaData metaData = resultSet.getMetaData();
			        results.append(resultSet.getObject(1));
			       
			        
			        
			        
			        statement.executeUpdate("INSERT INTO phoneNumbers (personID, phoneNumber) VALUES ('"+results+"','"+phone+"')"); //inserts a record into phoneNumbers table
			        
			        statement.executeUpdate("INSERT INTO emaiilAddresses (personID, emailAddress) VALUES ('"+results+"','"+email+"')"); //inserts a record into emailAddress table
			        
			        statement.executeUpdate("INSERT INTO addresses (personID, address1, address2, city, state, zipCode) VALUES ('"+results+"','"+address1+"','"+address2+"','"+city+"','"+state+" ','"+zipCode+"')"); //inserts a record into addresses table
			        
			        
			         statement.close();
			         connection.close();
			         
			         JOptionPane.showMessageDialog(null, "Insertion completed");
			       
				}
				catch ( SQLException sqlException ) {
		         JOptionPane.showMessageDialog( null,
		            sqlException.getMessage(), "Database Error",
		            JOptionPane.ERROR_MESSAGE );
		         System.exit( 1 );
		      }

		     catch(ClassNotFoundException cnfex) {

		          System.out.println("Problem in loading or "
		                  + "registering MS Access JDBC driver");
		          cnfex.printStackTrace();
		      }
			}
			else if(e.getActionCommand().equals("Update"))
			{
				try {

					//Class.forName("org.sqlite.JDBC");
					Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\NESki\\OneDrive\\Desktop\\project.db");

			        Statement statement = connection.createStatement();

			        firstname = f1.getText();   //gets the value from the text field (first name)
			        lastname = l1.getText();    // gets the value from the text field (last name)
			        address1 = address.getText();  // gets the value from the text field (address 1)
			        address2 = addressLine2.getText(); // gets the value from the text field (address 2)
			        city = city1.getText();
			        state = state1.getText();
			        zipCode = zipC.getText();
			        email = emailAdd.getText();    // gets the value fron the text field (email address)
			        phone = phoneNumber.getText(); // gets the value from the text field (phone number)
			        StringBuffer results = new StringBuffer();


			       ResultSet resultSet = statement.executeQuery( "SELECT personID FROM names Where firstname = ('"+firstname+"')" );
			        ResultSetMetaData metaData = resultSet.getMetaData();
			        results.append(resultSet.getObject(1));
			        
			        
			       
			        
			         statement.executeUpdate("UPDATE names SET firstName = '"+firstname+"', lastName = '"+lastname+"' WHERE personID = '"+results+"' "); //inserts a record into names table
			        
			        statement.executeUpdate("UPDATE phoneNumbers SET phoneNumber = '"+phone+"'  WHERE personID = '"+results+"' "); //inserts a record into phoneNumbers table
			        
			        statement.executeUpdate("UPDATE emaiilAddresses SET emailAddress = '"+email+"'  WHERE personID = '"+results+"' ");  //inserts a record into emailAddress table
			        
			        statement.executeUpdate("UPDATE addresses SET address1 = '"+address1+"', address2 = '"+address2+"',  city = '"+city+"', state = '"+state+"', zipCode = '"+zipCode+"'  WHERE personID = '"+results+"' "); //inserts a record into addresses table
			        
			        
			         statement.close();
			         connection.close();
			         
			         JOptionPane.showMessageDialog(null, "Update completed");

				}
				catch ( SQLException sqlException ) {
		         JOptionPane.showMessageDialog( null,
		            sqlException.getMessage(), "Database Error",
		            JOptionPane.ERROR_MESSAGE );
		         System.exit( 1 );
			}
			}

			else if(e.getActionCommand().equals("Delete"))
			{
				try 
				{
					Class.forName("org.sqlite.JDBC");
					Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\NESki\\OneDrive\\Desktop\\project.db");

			        Statement statement = connection.createStatement();

			        firstname = f1.getText();   //gets the value from the text field (first name)
			        lastname = l1.getText();    // gets the value from the text field (last name)
			        address1 = address.getText();  // gets the value from the text field (address 1)
			        address2 = addressLine2.getText(); // gets the value from the text field (address 2)
			        city = city1.getText();
			        state = state1.getText();
			        zipCode = zipC.getText();
			        email = emailAdd.getText();    // gets the value fron the text field (email address)
			        phone = phoneNumber.getText(); // gets the value from the text field (phone number)
			        StringBuffer results = new StringBuffer();
			        
			        ResultSet resultSet = statement.executeQuery( "SELECT personID FROM names Where firstname = ('"+firstname+"')" );
			        ResultSetMetaData metaData = resultSet.getMetaData();
			        results.append(resultSet.getObject(1));
			        
			        
			       
			        
			         statement.executeUpdate("DELETE FROM names WHERE personID = '"+results+"' "); //inserts a record into names table
			        
			        statement.executeUpdate("DELETE FROM phoneNumbers WHERE personID = '"+results+"' "); //inserts a record into phoneNumbers table
			        
			        statement.executeUpdate("DELETE FROM emaiilAddresses WHERE personID = '"+results+"' ");  //inserts a record into emailAddress table
			        
			        statement.executeUpdate("DELETE FROM addresses WHERE personID = '"+results+"' ");
			        
			        statement.executeUpdate("DELETE FROM names WHERE personID = '"+results+"' "); //inserts a record into names table
			        statement.close();
			        connection.close();
				}
				catch ( SQLException sqlException ) {
			         JOptionPane.showMessageDialog( null,
			            sqlException.getMessage(), "Database Error",
			            JOptionPane.ERROR_MESSAGE );
			         System.exit( 1 );
			      }

			     catch(ClassNotFoundException cnfex) {

			          System.out.println("Problem in loading or "
			                  + "registering MS Access JDBC driver");
			          cnfex.printStackTrace();
			      }
			}
			
			else if(e.getActionCommand().equals("Search"))
			{

		         try {

					Class.forName("org.sqlite.JDBC");
					Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\NESki\\OneDrive\\Desktop\\project.db");

			        Statement statement = connection.createStatement();
			        firstname = f1.getText(); //gets the value from the text field (first name)
		            lastname = l1.getText(); // gets the value from the text field (last name)
		            StringBuffer personID = new StringBuffer();
		            
		           

		            ResultSet personIDSet = statement.executeQuery("SELECT personID FROM names WHERE firstName = ('"+firstname+"') AND lastName = ('"+lastname+"')");
		            ResultSetMetaData personIDmetaData = personIDSet.getMetaData();
			        personID.append(personIDSet.getObject(1));
			        
			        
			       
			             ResultSet resultSet = statement.executeQuery( "SELECT * FROM addresses WHERE personID = ('"+personID+"')" );
			             StringBuffer results = new StringBuffer();
				         ResultSetMetaData metaData = resultSet.getMetaData();
				         int numberOfColumns = metaData.getColumnCount();
				        
				         while ( resultSet.next() ) {
				            results.append( resultSet.getObject( 3 ));
				            address.setText(results.toString());
				            results = new StringBuffer();
				            results.append( resultSet.getObject( 4 ));
				            addressLine2.setText(results.toString());
				            results = new StringBuffer();
				            results.append( resultSet.getObject( 5 ));
				            city1.setText(results.toString());
				            results = new StringBuffer();
				            results.append( resultSet.getObject( 6 ));
				            state1.setText(results.toString());
				            results = new StringBuffer();
				            results.append( resultSet.getObject( 7 ));
				            zipC.setText(results.toString());
				            
				         }
		         
				         
				         
			             resultSet = statement.executeQuery( "SELECT * FROM emaiilAddresses WHERE personID = ('"+personID+"')" );
			             results = new StringBuffer();
				         metaData = resultSet.getMetaData();
				         numberOfColumns = metaData.getColumnCount();
				        
				         if (resultSet.next() == false)
					        {
					        	JOptionPane.showMessageDialog(null, "Record Not Found");
					        } 
				         while ( resultSet.next() ){
				               results.append( resultSet.getObject( 3 ));
				            results.append( "\n" );
				         } 
				        

				         emailAdd.setText(results.toString());
				         
				         
			             resultSet = statement.executeQuery( "SELECT * FROM phoneNumbers WHERE personID = ('"+personID+"')" );
			             results = new StringBuffer();
				         metaData = resultSet.getMetaData();
				         numberOfColumns = metaData.getColumnCount();
				    
				         
				         
				         while ( resultSet.next() ) {
				               results.append( resultSet.getObject( 3 ));
				            
				         }
				        phoneNumber.setText(results.toString());
				        


		            statement.close();
		            connection.close();
		           
		           
			         
			      
				}
				catch ( SQLException sqlException ) {
		         JOptionPane.showMessageDialog( null,
		            sqlException.getMessage(), "Database Error",
		            JOptionPane.ERROR_MESSAGE );
		         System.exit( 1 );
		      }

		     catch(ClassNotFoundException cnfex) {

		          System.out.println("Problem in loading or "
		                  + "registering MS Access JDBC driver");
		          cnfex.printStackTrace();
		      }

			}  
			else if(e.getActionCommand().equals("Print"))
			{
				/*JFrame f = new JFrame("Print UI Example");
				JTextArea text = new JTextArea(50, 20);
				StringBuffer results = new StringBuffer();*/
				
				PrinterJob job = PrinterJob.getPrinterJob();
		         job.setPrintable(this);
		         boolean ok = job.printDialog();
		         if (ok) {
		             try {
		                  job.print();
		             } catch (PrinterException ex) {
		              /* The job did not successfully complete */
		             }
		         }		        
		        		
			}
			else
				System.out.println("Error in button interface");
		}



	   public static void main( String args[] )
	   {
	      AddressBook window = new AddressBook();
	      window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	      window.setVisible(true);

	   }
	   public int print(Graphics g, PageFormat pf, int page) throws
       PrinterException {

if (page > 0) { /* We have only one page, and 'page' is zero-based */
return NO_SUCH_PAGE;
}

/* User (0,0) is typically outside the imageable area, so we must
* translate by the X and Y values in the PageFormat to avoid clipping
*/
Graphics2D g2d = (Graphics2D)g;
g2d.translate(pf.getImageableX(), pf.getImageableY());

/* Now print the window and its visible contents */
mFrame.printAll(g);

/* tell the caller that this page is part of the printed document */
return PAGE_EXISTS;
}
	}

	class WindowDestroyer extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
		    System.exit(0);
		}

	}


