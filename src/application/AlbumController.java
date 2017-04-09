package application;

import java.io.IOException;
import java.util.List;

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

        //retrieve all the list of users
       USERS= FileHandler.retrieveUsers();
       
       //user that logged in
       userNAME=userName;
       
 /*Finds the matching username and loads up the observableList and List View
  */
       for(int i =0;i<USERS.size();i++)
       {
       	   if(USERS.get(i).toString().equals(userNAME))
       	   {
       		//adds the list of albums that the logged in user has to observable list and listview
       		albums_ObservableLIST.addAll(USERS.get(i).getAlbumList());
       		albumlist.setItems(albums_ObservableLIST);
       		break;
       	   }
       }
       
    }
    
    
	public void handleRename(ActionEvent e)
	{
		
		
	}
	
	public void handleLogOut(ActionEvent e) throws ClassNotFoundException, IOException
	{
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Login.fxml"));
        Parent root = (Parent) loader.load();
    		//Parent home_page_parent=FXMLLoader.load(getClass().getResource("AdminSubsystem.fxml"));
    		Scene home_page_scene=new Scene(root);
    		Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
    	
    		app_stage.hide();
    		app_stage.setScene(home_page_scene);
    		
    		LoginController logincontrol =loader.getController();
    		
    		app_stage.show();
    		logincontrol.start(app_stage);
		
	}
	
	
	public void handleCreate(ActionEvent e)
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
		 
		 /*
		  * Check if the album name already exists for this user
		  */
		for(Album a: albums_ObservableLIST)
		{
			if(a.toString().equals(albumField.getText()))
            {
                Alert alert =
                        new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("This album already exists!");
                alert.showAndWait();
                return;
            }
		}
		
		 
		 
		 //create new album
		 Album new_album=new Album(albumField.getText());
		 
		 /*
		  * Find the user that logged in in FileHandler's list of users and add new album to the matching
		  * username's list of album field
		  */
		 for(int i =0;i<USERS.size();i++)
	       {
	       	   if(USERS.get(i).toString().equals(userNAME))
	       	   {
	       		   //add the album to matching username
	       		   FileHandler.fileofUsers.get(i).setAlbum(new_album);
	       		   
	       		   //test to see if it was added
	       		   System.out.println("ALBUM: "+FileHandler.fileofUsers.get(i).getAlbumList());
	       		   
	       		   //add to observable list
	       		albums_ObservableLIST.add(new_album);
	       		//add to the list view
	       		albumlist.setItems(albums_ObservableLIST);
	       		
	       		FileHandler.WriteFile();
	       	   }
	       }
	 
	}
	
	public void handleDelete(ActionEvent e)
	{
		//selected album
		Album album = albumlist.getSelectionModel().getSelectedItem();

        int nextIndex = albumlist.getSelectionModel().getSelectedIndex() + 1;

        //Selected song is not the last one
        if(nextIndex != albums_ObservableLIST.size()){
            albumlist.getSelectionModel().select(nextIndex);
        }

        albums_ObservableLIST.remove(album);
        
        /*
         * Loops through the userslist and finds the matching user in the handleFile users list
         * then loops through that users album list and finds a matching album name to remove from the album list
         * from that user
         */
        for(int i =0;i<USERS.size();i++)
	       {
	       	   if(USERS.get(i).toString().equals(userNAME))
	       	   {
	       		System.out.println("test "+album);
	       		int albumlistLength=FileHandler.fileofUsers.get(i).getAlbumList().size();
	       		   //add the album to matching username
	       			for(int j =0;j<albumlistLength;j++)
	       			{
	       				//find the album that matches the album name selected
	       				if(FileHandler.fileofUsers.get(i).getAlbumList().get(j).toString().equals(album.toString()))
	       				{
	       					//remove that album from the album list
	       					FileHandler.fileofUsers.get(i).getAlbumList().remove(j);
	       					break;
	       				}
	       			}
	       		System.out.println("HM: " + FileHandler.fileofUsers.get(i).getAlbumList());
	  
	       		FileHandler.WriteFile();
	       		break;
	       	   }
	       }
       // FileHandler.fileofUsers.remove(album);
        //FileHandler.WriteFile();
	}
	
	public void handleOpen(ActionEvent e)
	{
		
		
	}
	


}
