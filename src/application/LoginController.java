package application;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {
	@FXML
    private Button loginBtn;

    @FXML
    private TextField userField;
    
    public void handleLogin(ActionEvent e)
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
        		Alert alert= new Alert(Alert.AlertType.INFORMATION);
        			alert.setContentText("YO IM AN ADMIN");
        				alert.showAndWait();
        				return;
	        	//login as admin stuff"
	        	//maybe add a user boolean is admin thing
	        }
    }
        
        
}


