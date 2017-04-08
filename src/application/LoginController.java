package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class LoginController {
	@FXML
    private Button loginBtn;

    @FXML
    private TextField userField;
    
    List<User> USERS;
    public void start(Stage mainStage) throws ClassNotFoundException
    {
    	//when window is closed save the file
        mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
                FileHandler.WriteFile();
            }
        });
        
        FileHandler.ReadFile();
        
        /*TESTING IF I CAN READ FROM FILE*/
       USERS= FileHandler.retrieveUsers();
      

    }
    
   
    public void handleLogin(ActionEvent e) throws IOException
    {
    	//name must not be empty
        if("".equals(userField.getText()))
        {
            Alert alert =
                    new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("You must enter a username");
            alert.showAndWait();
            
            return;   	
        }
        
        else if("admin".equals(userField.getText()))
	        {
        	FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("AdminSubsystem.fxml"));
	        Parent root = (Parent) loader.load();
        		//Parent home_page_parent=FXMLLoader.load(getClass().getResource("AdminSubsystem.fxml"));
        		Scene home_page_scene=new Scene(root);
        		Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        	
        		app_stage.hide();
        		app_stage.setScene(home_page_scene);
        		
        		AdminController admincontrol =loader.getController();
        		
        		app_stage.show();
        		admincontrol.start(app_stage);
        		
	        	//login as admin stuff"
	        	//maybe add a user boolean is admin thing
	        }
        else if("admin".equals(userField.getText())==false)
        {
        	ArrayList listofpeep=(ArrayList) USERS;
        	String usernamefield=userField.getText();
        	
        	for( int i=0;i<listofpeep.size();i++)
        	{
        		
        		
        		if(listofpeep.get(i).toString().equals(usernamefield))
        		{
        			System.out.println("HERE: "+ listofpeep.get(i));
        			break;
        		}
        	}
        }
        
       
    }
        
        
}


