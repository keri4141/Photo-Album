package application;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import application.User;

public class AdminController {
	
	
	@FXML
    private Button userCreate;
	
	@FXML
    private Button userDelete;

    @FXML
    private TextField usernameField;
    
    @FXML
    private ListView<User> userlist;

    
    private ObservableList<User> users= FXCollections.observableArrayList();
    
    public void start(Stage mainStage)
    {
    	
    	 mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
             public void handle(WindowEvent event) {
                 FileHandler.WriteFile();
             }
         });
    	  FileHandler.ReadFile();

          users.addAll(FileHandler.fileofUsers);
         
          userlist.setItems(users);
    }
    
    public void handleCreate(ActionEvent e) throws IOException
    {
    	 if("".equals(usernameField.getText()))
         {
             Alert alert =
                     new Alert(Alert.AlertType.INFORMATION);
             alert.setContentText("You must enter a username");
             alert.showAndWait();
             
             return;   	
         }
    	User person = new User(usernameField.getText());
    	FileHandler.fileofUsers.add(person);
    	users.add(person);
    	userlist.setItems(users);
    }
    
    public void handleDelete(ActionEvent e) throws IOException
    {
    	User person = userlist.getSelectionModel().getSelectedItem();

        int nextIndex = userlist.getSelectionModel().getSelectedIndex() + 1;

        //Selected song is not the last one
        if(nextIndex != users.size()){
            userlist.getSelectionModel().select(nextIndex);
        }

        users.remove(person);
      
        FileHandler.fileofUsers.remove(person);
      
    }
    
    public void handleLogout(ActionEvent e) throws IOException
    {
    	
    	Parent home_page_parent=FXMLLoader.load(getClass().getResource("Login.fxml"));
		Scene home_page_scene=new Scene(home_page_parent);
		Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		app_stage.hide();
		app_stage.setScene(home_page_scene);
		app_stage.show();
		 
    }
    
 

}
