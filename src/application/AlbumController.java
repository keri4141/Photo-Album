package application;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AlbumController {
	
	
	@FXML
    private Button albumCreate;
	
	@FXML
    private Button albumOpen;
	
	@FXML
    private Button albumDelete;
	
	@FXML
    private Button albumRename;

    @FXML
    private TextField albumField;
	
    List<User> USERS;
    String userNAME;
    
    @FXML
    private ListView<Album> albumlist;

    
    private ObservableList<Album> albums_ObservableLIST= FXCollections.observableArrayList();
    
    
    public void start(Stage mainStage, String userName) throws ClassNotFoundException
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
       userNAME=userName;
 
       for(int i =0;i<USERS.size();i++)
       {
       	   if(USERS.get(i).toString().equals(userNAME))
       	   {
       		albums_ObservableLIST.addAll(USERS.get(i).getAlbumList());
       		albumlist.setItems(albums_ObservableLIST);
       		break;
       	   }
       }
       
    }
    
    
	public void handleRename()
	{
		
		
	}
	
	public void handleCreate()
	{
		/*IF THE ALBUM NAME FIELD IS EMPTY*/
		 if("".equals(albumField.getText()))
         {
             Alert alert =
                     new Alert(Alert.AlertType.INFORMATION);
             alert.setContentText("You must enter a album name");
             alert.showAndWait();
             
             return;   	
         }
		 
		 //create new album
		 Album new_album=new Album(albumField.getText());
		 
		 //find the logged in user
		 for(int i =0;i<USERS.size();i++)
	       {
	       	   if(USERS.get(i).toString().equals(userNAME))
	       	   {
	       		   FileHandler.fileofUsers.get(i).setAlbum(new_album);
	       		   System.out.println("ALBUM: "+FileHandler.fileofUsers.get(i).getAlbumList());
	       		   
	       		albums_ObservableLIST.add(new_album);
	       		albumlist.setItems(albums_ObservableLIST);
	       		FileHandler.WriteFile();
	       	   }
	       }
		 
		 
		 
		 
		 
	}
	
	public void handleDelete()
	{
		
	}
	
	public void handleOpen()
	{
		
		
	}

}
