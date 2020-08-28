import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class ProductDetails {

	private JFrame frame;
	private JTextField productName;
	private JLabel lblNewLabel_1;
	private JTextField price;
	private JTextField totalCost;
	private JSpinner quantity;
	private JButton btnAddData;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args){
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductDetails window = new ProductDetails();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		getConnection();
	}
	public static Connection getConnection()
	{
		Connection con = null;
		try
		{
			Class.forName ("oracle.jdbc.OracleDriver"); // registering the driver class
			con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XE", "system", "system");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * Create the application.
	 */
	public ProductDetails(){
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @param <DefalutTableModel>
	 */
	private <DefalutTableModel> void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().setBackground(new Color(32, 178, 170));
		frame.setBounds(100, 100, 739, 486);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sales");
		lblNewLabel.setBackground(SystemColor.textHighlight);
		lblNewLabel.setLabelFor(frame);
		lblNewLabel.setFont(new Font("Microsoft JhengHei", Font.BOLD, 40));
		lblNewLabel.setBounds(252, 10, 130, 43);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblProductName = new JLabel("Product Name ");
		lblProductName.setForeground(SystemColor.textText);
		lblProductName.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
		lblProductName.setBounds(37, 68, 163, 43);
		frame.getContentPane().add(lblProductName);
		
		lblNewLabel_1 = new JLabel("Quantity");
		lblNewLabel_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(37, 135, 146, 34);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblPrice = new JLabel("price");
		lblPrice.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
		lblPrice.setBounds(37, 183, 122, 39);
		frame.getContentPane().add(lblPrice);
		
		JLabel lblNewLabel_2 = new JLabel("Totalcost ");
		lblNewLabel_2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(37, 245, 130, 29);
		frame.getContentPane().add(lblNewLabel_2);
		
		productName = new JTextField();
		productName.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
		productName.setBounds(210, 77, 233, 34);
		frame.getContentPane().add(productName);
		productName.setColumns(1);
		
	    quantity = new JSpinner();
		quantity.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		quantity.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
		quantity.setBounds(210, 135, 57, 32);
		frame.getContentPane().add(quantity);
		
		price = new JTextField();
		price.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
		price.setBounds(210, 186, 233, 33);
		frame.getContentPane().add(price);
		price.setColumns(1);
		
		btnAddData = new JButton("Add Data");
		btnAddData.addActionListener(new ActionListener() {
		 public void actionPerformed(ActionEvent e) {
				int num1;
			   float num2,ans; 
			   
				try {
					num1=(int) quantity.getValue();
					num2=Float.parseFloat(price.getText());
					ans=num1*num2;
					totalCost.setText(Float.toString(ans));}
					catch(Exception q) {
						JOptionPane.showMessageDialog(null,"Enter a valid data..... :( ");
						q.printStackTrace();
					}
				try {
					Class.forName ("oracle.jdbc.OracleDriver"); // registering the driver class
				    Connection	conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XE", "system", "system");
					String query = "insert into sales(product_name, quantity, price, total_cost) values (?, ?, ?, ?)";
					PreparedStatement statement = conn.prepareStatement(query);
					
		            statement.setString(1, productName.getText());
		            statement.setInt(2, (int) quantity.getValue());
		            statement.setFloat(3, Float.parseFloat(price.getText()));
		            statement.setFloat(4, Float.parseFloat(totalCost.getText()));
		            statement.execute();
					JOptionPane.showMessageDialog(null, "Connection Established");
				   }
				catch(Exception q) {
					JOptionPane.showMessageDialog(null,"no Access");
					q.printStackTrace();
				}
				

			}
			
	});
		
		totalCost = new JTextField();
		totalCost.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
		totalCost.setBounds(210, 249, 233, 29);
		frame.getContentPane().add(totalCost);
		totalCost.setColumns(1);
		btnAddData.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
		btnAddData.setBounds(481, 186, 136, 33);
		frame.getContentPane().add(btnAddData);
		
	}
}
