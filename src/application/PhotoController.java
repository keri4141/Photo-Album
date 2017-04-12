package application;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Pair;


public class PhotoController {

	@FXML
	private ListView<Tag> tags;
	
	@FXML
	private ImageView PHOTO_LIST;
	
	@FXML
	private TilePane PHOTO_PANE;
	

	private ObservableList<Photos> photos_ObservableLIST= FXCollections.observableArrayList();
	
	  List<User> USERS;
	  List<Photos> photolist= new ArrayList<Photos>();
	    String userNAME;
	    Album album_name;
	    String photo_path;
	    String TagName="";
	    String TagValue="";
	    String selected_photo="";
	    ImageView selected_image_view=null;
	    boolean isalbumEmpty=false;
	
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
		
       //POPULATE WITH IMAGES
       
       //CHECK IF EMPTY ALBUM
  	 for(int i =0;i<USERS.size();i++)
     {
     	   if(USERS.get(i).toString().equals(userNAME))
     	   {
     		int albumlistLength=FileHandler.fileofUsers.get(i).getAlbumList().size();
     		   //add the album to matching username
     		   for(int j =0;j<albumlistLength;j++)
     			   {
     			   		
     			   		if(FileHandler.fileofUsers.get(i).getAlbumList().get(j).toString().equals(album_name.toString()))
     			   		{	
     			   				//loop through photo list
     			   			List<Photos> listofPaths=FileHandler.fileofUsers.get(i).getAlbumList().get(j).getPhotoList();
     			   			
     			   				if(listofPaths.size()==0)
     			   				{
     			   					isalbumEmpty=true;
     			   					break;
     			   				}
  	
     			   		}
     			   		
     			   }
 
     		break;
     	   }
     }
       
       
       //ELSE ITS NOT EMPTY SO LOAD IMAGES
       
       if(isalbumEmpty==false)
       {
	  	 //FIND THE ALBUM TO PUT THE PHOTO IN
			 for(int i =0;i<USERS.size();i++)
		       {
		       	   if(USERS.get(i).toString().equals(userNAME))
		       	   {
		       		int albumlistLength=FileHandler.fileofUsers.get(i).getAlbumList().size();
		       		   //add the album to matching username
		       		   for(int j =0;j<albumlistLength;j++)
		       			   {
		       			   		
		       			   		if(FileHandler.fileofUsers.get(i).getAlbumList().get(j).toString().equals(album_name.toString()))
		       			   		{	
		       			   				//loop through photo list
		       			   			photolist=FileHandler.fileofUsers.get(i).getAlbumList().get(j).getPhotoList();
		       			   				for(int k =0;k<photolist.size();k++)
		       			   				{
		       			   					photo_path=photolist.get(k).toString();
		       			   					
			       			   				ImageView imageView= new ImageView();
					       			   		Image image = new Image(photo_path);
	
					       			    	imageView.setImage(image);
					       			    	imageView.setFitHeight(110);
					       			    	imageView.setFitWidth(110);
					       			    	
					       			    	PHOTO_PANE.getChildren().addAll(imageView); //adds image to tilepane
					       			    	
					       			    	imageView.setPickOnBounds(true);
		       			   				}
		       			   				break;		
		       			   		}
		       			   		
		       			   } //end of album loop
		   
		       		break;
		       	   }
		       }
       	} //END OF CHECK IF ALBUM IS EMPTY
		 
		//this is to make imageView clickable
	    	ObservableList<Node> childNode = PHOTO_PANE.getChildren();

	    	for(int i = 0; i < childNode.size(); i++)
	    	{
	    		Node temp1 = childNode.get(i);
	    		temp1.setOnMouseClicked(Event -> {

	    			imageSelect((ImageView) temp1);

	    			System.out.println("got: "+selected_photo);
	    		});
	    	}
       

	}
	
	public void handleAddPhoto(ActionEvent e)
	{
		//dialog();
		
		 if("".equals(photo_path))
         {
             Alert alert =
                     new Alert(Alert.AlertType.INFORMATION);
             alert.setContentText("You must enter a path");
             alert.showAndWait();
             
             return;   	
         }
		 
		 /*
		  * Adds the photo to the user who logged in
		  * 
		  */
		 else{
			
			 
			 FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Select Image File");
				fileChooser.getExtensionFilters().addAll(
						new FileChooser.ExtensionFilter("Image Files",
								"*.bmp", "*.png", "*.jpg", "*.gif")); 
				File selectedFile = fileChooser.showOpenDialog(null);
				
				
				ImageView imageView= new ImageView();
				
				try {
		    		photo_path = selectedFile.toURI().toURL().toString();
		    	} catch (MalformedURLException e1) {
		    		// TODO Auto-generated catch block
		    		e1.printStackTrace();
		    	}
		    	Image image = new Image(photo_path);
		    	imageView.setImage(image);
		    	imageView.setFitHeight(110);
		    	imageView.setFitWidth(110);
		    	
		    	PHOTO_PANE.getChildren().addAll(imageView); //adds image to tilepane
		    	
		    	imageView.setPickOnBounds(true);

		    	//this is to make imageView clickable
		    	ObservableList<Node> childNode = PHOTO_PANE.getChildren();

		    	for(int i = 0; i < childNode.size(); i++)
		    	{
		    		Node temp1 = childNode.get(i);
		    		temp1.setOnMouseClicked(Event -> {

		    			imageSelect((ImageView) temp1);
		    			
		    			System.out.println("got: "+selected_photo);
		    		});
		    	}
		    	
			 Photos new_photo = new Photos(photo_path);
			 
			 
			 //FIND THE ALBUM TO PUT THE PHOTO IN
			 for(int i =0;i<USERS.size();i++)
		       {
		       	   if(USERS.get(i).toString().equals(userNAME))
		       	   {
		       		int albumlistLength=FileHandler.fileofUsers.get(i).getAlbumList().size();
		       		   //add the album to matching username
		       		   for(int j =0;j<albumlistLength;j++)
		       			   {
		       			   		
		       			   		if(FileHandler.fileofUsers.get(i).getAlbumList().get(j).toString().equals(album_name.toString()))
		       			   		{	
			       			   		FileHandler.fileofUsers.get(i).getAlbumList().get(j).setPhoto(new_photo);
			       			   		//photolist.add(new_photo);
			       			   	 //test to see if it was added
			 		       		   System.out.println("PHOTO: "+FileHandler.fileofUsers.get(i).getAlbumList().get(j).getPhotoList());
			       			   		break;
		       			   		}
		       			   		
		       			   }
		      
		       		   
		       		   //add to observable list
		       		//   photos_ObservableLIST.add(new_photo);
		       		//add to the list view
		       		//photolist.setItems(photos_ObservableLIST);
		       		
		       		FileHandler.WriteFile();
		       		break;
		       	   }
		       }
		 }
		
	}
	
	public void imageSelect(ImageView imageview)
	{
		int imageIndex = PHOTO_PANE.getChildren().indexOf(imageview);
		//imageIndex starts from 1
		Photos imagePhoto = photolist.get(imageIndex-1);
		selected_image_view=imageview;
		selected_photo=imagePhoto.toString();
	}
	
	
	public void handleDeletePhoto(ActionEvent e)
	{
		if(selected_photo.equals("")==true)
		{
			 Alert alert =
                     new Alert(Alert.AlertType.INFORMATION);
             alert.setContentText("You must select a photo to delete!");
             alert.showAndWait();
             
             return;   	
		}
		
		else
		{
			 for(int i =0;i<USERS.size();i++)
		       {
		       	   if(USERS.get(i).toString().equals(userNAME))
		       	   {
		       		int albumlistLength=FileHandler.fileofUsers.get(i).getAlbumList().size();
		       		   //add the album to matching username
		       		   for(int j =0;j<albumlistLength;j++)
		       			   {
		       			   		
		       			   		if(FileHandler.fileofUsers.get(i).getAlbumList().get(j).toString().equals(album_name.toString()))
		       			   		{	
		       			   				//loop through photo list
		       			   			photolist=FileHandler.fileofUsers.get(i).getAlbumList().get(j).getPhotoList();
		       			   				for(int k =0;k<photolist.size();k++)
		       			   				{
		       			   					photo_path=photolist.get(k).toString();
		       			   					
			       			   				if(photo_path.equals(selected_photo))
			       			   				{
			       			   					FileHandler.fileofUsers.get(i).getAlbumList().get(j).getPhotoList().remove(k);
			       			   					PHOTO_PANE.getChildren().remove(selected_image_view);
			       			   				break;
			       			   				}
			       			   				
		       			   				}
		       			   				break;		
		       			   		}
		       			   		
		       			   } //end of album loop
		   
		       		break;
		       	   }
		       }
		}
	}
	
	public void dialog()
	{
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle("Add Yo path to image");
		dialog.setHeaderText("Enter your name");
		ButtonType logButton = new ButtonType("Add", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(logButton,ButtonType.CANCEL);
		
		
		GridPane grid=new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20,15,10,10));
		TextField fn= new TextField();
		fn.setPromptText("Image Path");
		
		
		
		
		
		grid.add(new Label("Image Path: "),0,0);
		grid.add(fn, 1, 0);
		
		
		
		dialog.getDialogPane().setContent(grid);
		Platform.runLater(()->fn.requestFocus());
		
		dialog.setResultConverter(dialogButton -> {
			if(dialogButton == logButton)
			{
				return new String(fn.getText());
			}
			return null;
		});
		
		Optional<String> result = dialog.showAndWait();
		
		photo_path=result.get();

		
	}
	
	public void handleBack(ActionEvent e) throws IOException, ClassNotFoundException
	{
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("UserSubsystem.fxml"));
        Parent root = (Parent) loader.load();
    		//Parent home_page_parent=FXMLLoader.load(getClass().getResource("AdminSubsystem.fxml"));
    		Scene home_page_scene=new Scene(root);
    		Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
    	
    		app_stage.hide();
    		app_stage.setScene(home_page_scene);
    		
    		AlbumController albumcontrol =loader.getController();
    		
    		app_stage.show();
    		albumcontrol.start(app_stage,userNAME);
		
	}
	
	

	
}
