/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.Controller;


import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;


import application.Main;
import application.Model.Location;
import application.Model.Students;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Rashid Iqbal
 */
public class AppController implements Initializable {
    
    double x = 0, y = 0;
    
    @FXML
    private Label countlbl;
    
    @FXML
    private NumberAxis count;
    
    @FXML
    private CategoryAxis location;
    
    @FXML
    private BarChart<String, Number> barchart;
    
    @FXML
    private BorderPane mainpane;
    
    @FXML
    private Label latestlec;
    
    @FXML
    private Label countlbl1;
    
    @FXML
    private Label grouplbl;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
        XYChart.Series<String, Number> series =new XYChart.Series<>();
        series.setName("Labortory");
        series.getData().add(new XYChart.Data<>("lab",getLabCount()));
        
        
        XYChart.Series<String, Number> series2 =new XYChart.Series<>();
        series2.setName("Lecture Hall");
        series2.getData().add(new XYChart.Data<>("lec hall",getLecCount()));
       
        
        barchart.getData().add(series);
        barchart.getData().add(series2);
    }
    
    public int getLabCount() {
    	int countM = 0;
    	
		Connection con =getConnection();
		
		String query ="SELECT * FROM location where rtype='"+"lab"+"'";
		Statement st;
		ResultSet rs;
		
		try {
			st =con.createStatement();
			rs =st.executeQuery(query);
			Location location;
			while(rs.next()) {
				countM++;
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			return countM;
		}
    }
    
    public int getLecCount() {
    	int countM = 0;
    	
		Connection con =getConnection();

		String query ="SELECT * FROM location where rtype='"+"lec hall"+"'";
		Statement st;
		ResultSet rs;
		
		try {
			st =con.createStatement();
			rs =st.executeQuery(query);
			Location location;
			while(rs.next()) {
				countM++;
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			return countM;
		}
    }

    @FXML
    private void dragged(MouseEvent event) {
        
        Node node = (Node) event.getSource();
        
        Stage stage = (Stage) node.getScene().getWindow();
        
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }
    
    @FXML
    private void pressed(MouseEvent event) {
        
        x = event.getSceneX();
        y = event.getSceneY();
    }
    
    
    public Connection getConnection() {
		Connection con;
		try{
			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/itpm", "root","");
			return con;
			
		}catch(Exception e) {
			System.out.println("Error: "+e.getMessage());
			return null;
			
		}
	}
    
    @FXML
    public void Button1Action(MouseEvent event) {
    	/*System.out.println("You Clikced me!");
    	FxmlLoarder object = new FxmlLoarder();
    	Pane view =object.getPage("AddLocation");
    	mainpane.setCenter(view);*/
    	
    	
    	try {
			Parent root =FXMLLoader.load(Main.class.getResource("/application/View/AddLocation.fxml"));
			Scene scene = new Scene(root);
			Stage stage =new Stage();
			stage.setScene(scene);
			stage.show();
			
			
			
			
			
			
						
			
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
			
		}
		
    }
    
    @FXML
    void Button2Action(MouseEvent event) {
    	
    	try {
			Parent root =FXMLLoader.load(Main.class.getResource("/application/View/Students.fxml"));
			Scene scene = new Scene(root);
			Stage stage =new Stage();
			stage.setScene(scene);
			stage.show();
			
			
			
			
			
			
						
			
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
			
		}

    }
    
    @FXML
    void Button3Action(MouseEvent event) {
    	
    	try {
			Parent root =FXMLLoader.load(Main.class.getResource("/application/View/Tag.fxml"));
			Scene scene = new Scene(root);
			Stage stage =new Stage();
			stage.setScene(scene);
			stage.show();
			
			
			
			
			
			
						
			
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
			
		}

    }
    

    
    
    @FXML
    public void RoomCount(MouseEvent event) {
    	int countM = 0;
    	
		Connection con =getConnection();
		String query ="SELECT * FROM location";
		Statement st;
		ResultSet rs;
		
		try {
			st =con.createStatement();
			rs =st.executeQuery(query);
			Location location;
			while(rs.next()) {
				countM++;
			}
				
				
				
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			
			
			countlbl.setText(String.valueOf(countM));
		}
		
		
		
    	

    }
    
    @FXML
    public void StudentCount(MouseEvent event) {
    	
    	int countM1 = 0;
    	
		Connection con =getConnection();
		String query ="SELECT * FROM students";
		Statement st;
		ResultSet rs;
		
		try {
			st =con.createStatement();
			rs =st.executeQuery(query);
			Students students;
			while(rs.next()) {
				countM1++;
			}
				
				
				
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			
			System.out.println(countM1);
			countlbl1.setText(String.valueOf(countM1));
		}
		
		
		
    	

    }

    

    
    public void getLatline(MouseEvent event){
    	
    	Connection con =getConnection();
		String query =("SELECT *FROM students ORDER BY id DESC LIMIT 1;");
		Statement st;
		ResultSet rs;
		
		try {
			st =con.createStatement();
			rs =st.executeQuery(query);
			Location location;
			while(rs.next()) {
				
				String groupid =rs.getString("groupid");
				System.out.println("Bname is:" +groupid);
				
				grouplbl.setText(groupid);
				
				
			}
				
				
				
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
			
			
		}
		
		
		
    	
    	
    	
    	
    }
    
}
