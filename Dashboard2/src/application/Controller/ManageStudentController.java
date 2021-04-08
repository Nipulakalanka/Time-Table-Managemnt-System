package application.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import application.Model.Students;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ManageStudentController implements Initializable {

    @FXML
    private TableView<Students> mst;

    @FXML
    private TableColumn<Students, Integer> msid;

    @FXML
    private TableColumn<Students, String> msays;

    @FXML
    private TableColumn<Students, String> msprog;

    @FXML
    private TableColumn<Students, String> msgnum;

    @FXML
    private TableColumn<Students, String> mssgn;

    @FXML
    private TableColumn<Students, String> msgnid;

    @FXML
    private TableColumn<Students, String> mssgnid;
	
    @FXML
    private Button mstuupdate;

    @FXML
    private Button matudelete;

    @FXML
    private Button mstuclear;

    @FXML
    private TextField msidd;
    
    @FXML
    private TextField macyr;

    @FXML
    private TextField msgn;

    @FXML
    private TextField mgn;

    @FXML
    private TextField mpro;

    @FXML
    private TextField mgid;

    @FXML
    private TextField msgid;

    int index = -1;
    
   	ResultSet resultsset =null;
   	PreparedStatement preparedStatement;
    
    @FXML
    void getselected(MouseEvent event) {
    	 index = mst.getSelectionModel().getSelectedIndex();
         if (index <= -1){
         
             return;
         }
         
         msidd.setText(msid.getCellData(index).toString());
         macyr.setText(msays.getCellData(index).toString());
         mpro.setText(msprog.getCellData(index).toString());
         mgn.setText(msgnum.getCellData(index).toString());
         msgn.setText(mssgn.getCellData(index).toString());
         mgid.setText(msgnid.getCellData(index).toString());
         msgid.setText(mssgnid.getCellData(index).toString());
    }

    @FXML
    void Delete(MouseEvent event) {
    	Connection conn =getConnection();
	    String query = "delete from students where ayrsem= ?";
	        try {
	        	preparedStatement = conn.prepareStatement(query);
	        	preparedStatement.setString(1, macyr.getText());
	        	preparedStatement.execute();
	            JOptionPane.showMessageDialog(null, "Delete");
	            UpdateStudents();
	           
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, e);
	        }
	
    }

    @FXML
    void addstudent(ActionEvent event) throws IOException {

    	Parent root =FXMLLoader.load(getClass().getResource("/application/View/Students.fxml"));
		Scene scene = new Scene(root);
		Stage stage =new Stage();
		stage.setScene(scene);
		stage.show();
    	
    }
    @FXML
    void Update(MouseEvent event) {

    	 try {
 	    	Connection conn =getConnection();
 	    	String id = msidd.getText();
 	        String ayrsem = macyr.getText();
 	        String prog = mpro.getText();
 	        String ggroup = mgn.getText();
 	        String sgroup = msgn.getText();
	        String groupid = mgid.getText();
	        String sgroupid = msgid.getText();
 	        
 	        
 	        String query = "update students set ayrsem= '"+ayrsem+"',prog= '"+prog+"',ggroup= '"+ggroup+"',sgroup = '"+sgroup+"',groupid ='"+groupid+"',sgroupid ='"+sgroupid+" ' where id='"+id+"' ";
 	        preparedStatement= conn.prepareStatement(query);
 	        preparedStatement.execute();
 	        JOptionPane.showMessageDialog(null, "Update");
 	       UpdateStudents();
 	       
 	    } catch (Exception e) {
 	        JOptionPane.showMessageDialog(null, e);
 	    }
 	
    	
    }
	
    
public Connection getConnection() {
		
		Connection conn;
		try {
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/itpm","root","");
			return conn;
		}
		catch (Exception ex) {
			
			System.out.println("Error:"+ex.getMessage());
			return null;
		}
	}
    
    
    public ObservableList<Students> getStudentList(){
    	
    	ObservableList<Students> studentList = FXCollections.observableArrayList();
    	Connection conn = getConnection();
    	String query = "SELECT * FROM students";
    	
    	Statement st;
    	ResultSet rs;
    	
    	try {
    		
    		st = ((java.sql.Connection) conn).createStatement();
    		rs= st.executeQuery(query);
    		Students students;
    		while(rs.next()) {
    			
    			students = new Students(rs.getInt("id"),rs.getString("ayrsem"),rs.getString("prog"),rs.getString("ggroup"),rs.getString("sgroup"),rs.getString("groupid"),rs.getString("sgroupid"));
    			studentList.add(students);
    		
    		}
    	}
    	catch(Exception ex)
    	{
    		ex.printStackTrace();
    		
    	}
    	return studentList;
    }
    
    
    public void showStudents() {
    	
    	ObservableList<Students> list= getStudentList();
    	msid.setCellValueFactory(new PropertyValueFactory<Students , Integer>("id"));
    	msays.setCellValueFactory(new PropertyValueFactory<Students , String>("ayrsem"));
    	msprog.setCellValueFactory(new PropertyValueFactory<Students, String>("prog"));
    	msgnum.setCellValueFactory(new PropertyValueFactory<Students, String>("ggroup"));
    	mssgn.setCellValueFactory(new PropertyValueFactory<Students, String>("sgroup"));
    	msgnid.setCellValueFactory(new PropertyValueFactory<Students, String>("groupid"));
    	mssgnid.setCellValueFactory(new PropertyValueFactory<Students, String>("sgroupid"));
    	
    	mst.setItems(list);

    }
    
    private void UpdateStudents() {
    	
    	ObservableList<Students> list= getStudentList();
    	msid.setCellValueFactory(new PropertyValueFactory<Students , Integer>("id"));
    	msays.setCellValueFactory(new PropertyValueFactory<Students , String>("ayrsem"));
    	msprog.setCellValueFactory(new PropertyValueFactory<Students, String>("prog"));
    	msgnum.setCellValueFactory(new PropertyValueFactory<Students, String>("ggroup"));
    	mssgn.setCellValueFactory(new PropertyValueFactory<Students, String>("sgroup"));
    	msgnid.setCellValueFactory(new PropertyValueFactory<Students, String>("groupid"));
    	mssgnid.setCellValueFactory(new PropertyValueFactory<Students, String>("sgroupid"));
    	
    	mst.setItems(list);
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		showStudents();
		UpdateStudents();
	}


}
