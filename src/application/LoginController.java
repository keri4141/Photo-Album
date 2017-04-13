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

/**
 * 
 * @author Alvin Chau and Andy Phan 
 *
 */

public class LoginController {
	@FXML
    private Button loginBtn;

    @FXML
    private TextField userField;
    
    List<User> USERS;
    /**
     * 
     * @param mainStage
     * @throws ClassNotFoundException
     */
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
    
   /**
    * 
    * @param e
    * @throws IOException
    * @throws ClassNotFoundException
    */
    public void handleLogin(ActionEvent e) throws IOException, ClassNotFoundException
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
        else if("admin".equals(userField.getText())==false) //if its not admin then he typed something else
        {
        	ArrayList<User> listofpeep=(ArrayList<User>) USERS; //typecast it into arraylist so I can loop easier
        	String usernamefield=userField.getText();
        	
        	for( int i=0;i<listofpeep.size();i++)
        	{
        		
        		if(listofpeep.get(i).toString().equals(usernamefield))
        		{
        			FXMLLoader loader = new FXMLLoader();
        	        loader.setLocation(getClass().getResource("UserSubsystem.fxml"));
        	        Parent root = (Parent) loader.load();
                		
                		Scene home_page_scene=new Scene(root);
                		Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                	
                		app_stage.hide();
                		app_stage.setScene(home_page_scene);
                		
                		AlbumController albumcontrol =loader.getController();
                		
                		app_stage.show();
                		albumcontrol.start(app_stage,usernamefield);
        			break;
        		}
        		
        		
        	}
        }
        
       
    }
        
        
}


