package application;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;





public class PhotoController {

	@FXML
	private ListView<Photos> photolist;


	private ObservableList<Photos> photos_ObservableLIST= FXCollections.observableArrayList();
	
	  List<User> USERS;
	    String userNAME;
	    Album album_name;
	
	public void start(Stage mainStage,String username, Album selected_album_name) throws ClassNotFoundException
	{
		//when window is closed save the file
        mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
                FileHandler.WriteFile();
            }
        });
        
        FileHandler.ReadFile();

        //retrieve all the list of users
       USERS= FileHandler.retrieveUsers();
       album_name=selected_album_name;
       //user that logged in
       userNAME=username;
       
       System.out.println("USER: " +userNAME+ " SELECTED ALBUM: "+ album_name);
		
	}
	
	public void handleAddPhoto(ActionEvent e)
	{
		
		
	}
	
	public void handleDeletePhoto(ActionEvent e)
	{
		
	}
	
	
	
	
}
