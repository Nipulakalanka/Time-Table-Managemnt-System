package application.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import application.Model.Tags;

import java.sql.Connection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ManageTagController implements Initializable {

    @FXML
    private TableView<Tags> tvtag;

    @FXML
    private TableColumn<Tags, Integer> ttid;

    @FXML
    private TableColumn<Tags, String> ttsname;

    @FXML
    private TableColumn<Tags, String> ttscode;

    @FXML
    private TableColumn<Tags, String> ttrtag;

    @FXML
    private Button mtagupdate;

    @FXML
    private Button mtagclear;

    @FXML
    private Button mtagdelete;

    @FXML
    private Label tsubname;

    @FXML
    private Label trtag;

    @FXML
    private Label tsubcode;

    @FXML
    private TextField mid;
    
    @FXML
    private TextField mtagsname;

    @FXML
    private TextField mtagscode;

    @FXML
    private TextField mtagrelated;

    @FXML
    void addTag(ActionEvent event) throws IOException {

    	Parent root =FXMLLoader.load(getClass().getResource("/application/View/Tag.fxml"));
		Scene scene = new Scene(root);
		Stage stage =new Stage();
		stage.setScene(scene);
		stage.show();
    	
    }

    
    @FXML
    void Delete(MouseEvent event) {

    	Connection conn =getConnection();
    	    String query = "delete from tags where subjectn= ?";
    	        try {
    	        	preparedStatement = conn.prepareStatement(query);
    	        	preparedStatement.setString(1, mtagsname.getText());
    	        	preparedStatement.execute();
    	            JOptionPane.showMessageDialog(null, "Delete");
    	            UpdateTags();
    	           
    	        } catch (Exception e) {
    	            JOptionPane.showMessageDialog(null, e);
    	        }
    	
    }
    
    @FXML
    void Update(MouseEvent event) {

    	 try {
    	    	Connection conn =getConnection();
    	    	String id = mid.getText();
    	        String subjectn = mtagsname.getText();
    	        String subjectc = mtagscode.getText();
    	        String tagr = mtagrelated.getText();
    	        
    	        String query = "update tags set subjectn= '"+subjectn+"',subjectc= '"+subjectc+"',tagr= '"+tagr+"' where id='"+id+"' ";
    	        preparedStatement= conn.prepareStatement(query);
    	        preparedStatement.execute();
    	        JOptionPane.showMessageDialog(null, "Update");
    	        UpdateTags();
    	       
    	    } catch (Exception e) {
    	        JOptionPane.showMessageDialog(null, e);
    	    }
    	
    }
    
     int index = -1;
    
	ResultSet resultsset =null;
	PreparedStatement preparedStatement;
    
    @FXML
    void getSelected(MouseEvent event) {

        index = tvtag.getSelectionModel().getSelectedIndex();
        if (index <= -1){
        
            return;
        }
        
        mid.setText(ttid.getCellData(index).toString());
        mtagsname.setText(ttsname.getCellData(index).toString());
        mtagscode.setText(ttscode.getCellData(index).toString());
        mtagrelated.setText(ttrtag.getCellData(index).toString());
        
    	
    }
    
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		showTags();
		UpdateTags();
		
	}
	
public Connection getConnection() {
		
		Connection conn;
		try {
			conn =   DriverManager.getConnection("jdbc:mysql://localhost:3306/itpm","root","");
			return conn;
		}
		catch (Exception ex) {
			
			System.out.println("Error:"+ex.getMessage());
			return null;
		}
	
}

public ObservableList<Tags> getTagsList(){
	
	ObservableList<Tags> tagList = FXCollections.observableArrayList();
	Connection conn = getConnection();
	String query = "SELECT * FROM tags";
	
	Statement st;
	ResultSet rs;
	
	try {
		
		st = ((java.sql.Connection) conn).createStatement();
		rs= st.executeQuery(query);
		Tags tags;
		while(rs.next()) {
			
			tags = new Tags(rs.getInt("id"),rs.getString("subjectn"),rs.getString("subjectc"),rs.getString("tagr"));
		tagList.add(tags);
		
		}
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
		
	}
	return tagList;
}

public void showTags() {
	
	ObservableList<Tags> list= getTagsList();
	ttid.setCellValueFactory(new PropertyValueFactory<Tags , Integer>("id"));
	ttsname.setCellValueFactory(new PropertyValueFactory<Tags , String>("subjectn"));
	ttscode.setCellValueFactory(new PropertyValueFactory<Tags, String>("subjectn"));
	ttrtag.setCellValueFactory(new PropertyValueFactory<Tags, String>("tagr"));

	tvtag.setItems(list);

}



private void UpdateTags() {
	
	ttid.setCellValueFactory(new PropertyValueFactory<Tags , Integer>("id"));
	ttsname.setCellValueFactory(new PropertyValueFactory<Tags , String>("subjectn"));
	ttscode.setCellValueFactory(new PropertyValueFactory<Tags, String>("subjectn"));
	ttrtag.setCellValueFactory(new PropertyValueFactory<Tags, String>("tagr"));
    
	ObservableList<Tags> list= getTagsList();
    tvtag.setItems(list);
	
}

}
