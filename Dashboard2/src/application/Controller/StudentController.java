package application.Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import application.Model.Tags;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class StudentController implements Initializable{

    @FXML
    private Label academicyear;

    @FXML
    private Label gn;

    @FXML
    private Label prog;

    @FXML
    private Label sgn;

    @FXML
    private Button saveStu;

    @FXML
    private Button generateSID;

    @FXML
    private Button clearStu;

    @FXML
    private TextField GID;

    @FXML
    private TextField SGID;

    @FXML
    private TextField academicyearsemester;

    @FXML
    private TextField programme;

    @FXML
    private TextField groupnumber;

    @FXML
    private TextField subgroupnumber;

    String query =null;
  	ResultSet resultsset =null;
  	PreparedStatement preparedStatement;
  	Tags tags;
    
    @FXML
    void Clear(MouseEvent event) {

    }

    @FXML
    void GenerateId(ActionEvent event) {
    	String academicYearSemesterS = academicyearsemester.getText();
    	String programmeS = programme.getText();
    	String groupNoS = groupnumber.getText();
    	String sGroupNoS = subgroupnumber.getText();
    	String output1 = academicYearSemesterS+"."+programmeS+"."+groupNoS;
    	String output2 = academicYearSemesterS+"."+programmeS+"."+groupNoS+"."+sGroupNoS;
    

    	GID.setText(output1);
    	SGID.setText(output2);
    	Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setContentText("ID generated");
		alert.showAndWait();
    }

    @FXML
    void Save(MouseEvent event) {
    	String ayrsem = academicyearsemester.getText();
		String prog =programme.getText();
		String group =groupnumber.getText();
		String sgroup = subgroupnumber.getText();
		String groupid =GID.getText();
		String sgroupid =SGID.getText();
		
		if(ayrsem.isEmpty() ||prog.isEmpty()||group.isEmpty()||sgroup.isEmpty()||groupid.isEmpty()||sgroupid.isEmpty()) {
			Alert alert =new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Please Fill All Data");
			alert.showAndWait();
		}
		else {
			insertStudent();
			
		}
		
		
		
		try {
			Parent root =FXMLLoader.load(getClass().getResource("/application/View/ManageStudent.fxml"));
			Scene scene = new Scene(root);
			Stage stage =new Stage();
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    	

    }

	private void insertStudent() {
		Connection conn =getConnection();
		String query ="insert into students (ayrsem,prog,ggroup,sgroup,groupid,sgroupid) values(?,?,?,?,?,?)";
		try {
			
		preparedStatement = getConnection().prepareStatement(query);
		preparedStatement.setString(1, academicyearsemester.getText());
		preparedStatement.setString(2, programme.getText());
		preparedStatement.setString(3, groupnumber.getText());
		preparedStatement.setString(4, subgroupnumber.getText());
		preparedStatement.setString(5, GID.getText());
		preparedStatement.setString(6, SGID.getText());
		preparedStatement.execute();
		
		JOptionPane.showMessageDialog(null, "Insert Successfully");
	}catch(SQLException ex) {
		JOptionPane.showMessageDialog(null, ex);
	}
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
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


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * @FXML void select(ActionEvent event) {
	 * 
	 * 
	 * String a =
	 * academicyearsemester.getSelectionModel().getSelectedItem().toString();
	 * academicyear.setText(a);
	 * 
	 * String p = programme.getSelectionModel().getSelectedItem().toString();
	 * prog.setText(p);
	 * 
	 * String g = groupnumber.getSelectionModel().getSelectedItem().toString();
	 * gn.setText(g);
	 * 
	 * String s = subgroupnumber.getSelectionModel().getSelectedItem().toString();
	 * sgn.setText(s); }
	 * 
	 * 
	 * 
	 * @Override public void initialize(URL arg0, ResourceBundle arg1) {
	 * ObservableList<String> academicyearsemesterList =
	 * FXCollections.observableArrayList("Y1.S1","Y1.S2","Y2.S1","Y2.S2","Y3.S1",
	 * "Y3.S2","Y4.S1","Y4.S2");
	 * academicyearsemester.setItems(academicyearsemesterList);
	 * 
	 * ObservableList<String> programmeList =
	 * FXCollections.observableArrayList("IT","SE","CS","DS","CSNE","ISE","IM");
	 * programme.setItems(programmeList);
	 * 
	 * ObservableList<String> groupnumberList =
	 * FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9","10",
	 * "11","12","13","14","15","16","17","18","19","20");
	 * groupnumber.setItems(groupnumberList);
	 * 
	 * ObservableList<String> subgroupnumberList =
	 * FXCollections.observableArrayList("1","2","3");
	 * subgroupnumber.setItems(subgroupnumberList); }
	 */
    

    
}
