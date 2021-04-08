package application.Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.security.auth.Refreshable;
import javax.swing.JOptionPane;

import application.Model.Location;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ManageLocationController implements Initializable {
	
	@FXML
	private TableView<Location> tvlocation;
	@FXML
	private TableColumn<Location,Integer> tvid;
	@FXML
	private TableColumn <Location,String> tvbuilding;
	@FXML
	private TableColumn<Location,String> tvroom;
	@FXML
	private TableColumn<Location,String> tvRtype;
	@FXML
	private TableColumn<Location,Integer> tvcap;
	@FXML
	private Button btnupdate;
	@FXML
	private Button btndelete;
	@FXML
	private Button addlocation;
	@FXML
	private TextField id;
	@FXML
	private TextField tfEBname;
	@FXML
	private TextField tfERname;
	@FXML
	private TextField tfERtype;
	@FXML
	private TextField tfEcap;
	
	int index =-1;
	
	String query =null;
	ResultSet resultsset =null;
	PreparedStatement preparedStatement;
	Location location;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		showLocation();
		RefreshTable();
		
		// TODO Auto-generated method stub
		
	}
	
	public Connection getConnection() {
		Connection con;
		try{
			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/ITPM", "root","");
			return con;
			
		}catch(Exception e) {
			System.out.println("Error: "+e.getMessage());
			return null;
			
		}
	}
	
	public ObservableList<Location> getLcationList(){
		
		ObservableList<Location> locationList =FXCollections.observableArrayList();
		Connection con =getConnection();
		String query ="SELECT * FROM location";
		Statement st;
		ResultSet rs;
		
		try {
			st =con.createStatement();
			rs =st.executeQuery(query);
			Location location;
			while(rs.next()) {
				location =new Location(rs.getInt("id"), rs.getString("bname"), rs.getString("rname"), rs.getString("rtype"), rs.getInt("cap"));
				locationList.add(location);
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
		return locationList;
		
		
		
	}
	
	public void showLocation() {
		
		ObservableList<Location> list = getLcationList();
		
		tvid.setCellValueFactory(new PropertyValueFactory<Location, Integer>("id"));
		tvbuilding.setCellValueFactory(new PropertyValueFactory<Location, String>("bname"));
		tvroom.setCellValueFactory(new PropertyValueFactory<Location, String>("rname"));
		tvRtype.setCellValueFactory(new PropertyValueFactory<Location, String>("rtype"));
		tvcap.setCellValueFactory(new PropertyValueFactory<Location, Integer>("cap"));
		
		tvlocation.setItems(list);
	}
	
	@FXML
	void getSelected (MouseEvent event) {
		index = tvlocation.getSelectionModel().getFocusedIndex();
		if(index <= -1) {
			return;
		}
		id.setText(tvid.getCellData(index).toString());
		tfEBname.setText(tvbuilding.getCellData(index).toString());
		tfERname.setText(tvroom.getCellData(index).toString());
		tfERtype.setText(tvRtype.getCellData(index).toString());
		tfEcap.setText(tvcap.getCellData(index).toString());
		
		
	}
	
	public void EditLOcation() {
		
	try {
		Connection con =getConnection();
		String val1 = id.getText();
		String val2 =tfEBname.getText();
		String val3 =tfERname.getText();
		String val4 =tfERtype.getText();
		String val5 =tfEcap.getText();
		
		String query ="update location set id ='"+val1+"',bname ='"+val2+"',rname ='"+val3+"',"
				+ "rtype ='"+val4+"',cap ='"+val5+"' where id='"+val1+"'";
		
		
		preparedStatement = getConnection().prepareStatement(query);
		preparedStatement.execute();
		
		
		
		JOptionPane.showMessageDialog(null, "Updated Successfully");
		RefreshTable();
	}catch(SQLException ex) {
		JOptionPane.showMessageDialog(null, ex);
	}
}
	
public void RefreshTable() {
	
	tvid.setCellValueFactory(new PropertyValueFactory<Location, Integer>("id"));
	tvbuilding.setCellValueFactory(new PropertyValueFactory<Location, String>("bname"));
	tvroom.setCellValueFactory(new PropertyValueFactory<Location, String>("rname"));
	tvRtype.setCellValueFactory(new PropertyValueFactory<Location, String>("rtype"));
	tvcap.setCellValueFactory(new PropertyValueFactory<Location, Integer>("cap"));
	
	ObservableList<Location> list = getLcationList();
	tvlocation.setItems(list);
	

	
}

public void DeleteLocation() {
	Connection con =getConnection();
	String query ="delete from location where id =?";
	
	try {
		preparedStatement = getConnection().prepareStatement(query);
		preparedStatement.setString(1, id.getText());
		preparedStatement.execute();
		JOptionPane.showMessageDialog(null, "Deleted Successfully");
		RefreshTable();
	}catch(SQLException ex) {
		JOptionPane.showMessageDialog(null, ex);
	}
}
	

}
