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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Pair;


public class PhotoController {
	
	@FXML
	private TextArea captiontextArea;

	@FXML
	private ListView<Tag> tags;
	
	@FXML
	private ImageView PHOTO_LIST;
	
	@FXML
	private TilePane PHOTO_PANE;
	

	private ObservableList<Tag> tags_ObservableLIST= FXCollections.observableArrayList();
	Photos imagePhoto;
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
       
  	 /*
  	  * When loading up the photoview it will instantly set the caption textarea to be not visible
  	  * When an image is clicked, the visilbity value will be set to true
  	  *  
  	  */
  	 if(selected_image_view==null)
  	 {
  		captiontextArea.setVisible(false);
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
	    	
	    	
	    //display tags
	    /*
	     * when an image is clicked it should display the tags
	     * so go into imageSelect when an image is selected i would want to compare the photo selected with the user list
	     * 
	     */
	    	

	}
	
	public void handleAddPhoto(ActionEvent e)
	{
	
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
		       		//   tags_ObservableLIST.add(tag);
		       		//add to the list view
		       		//tags.setItems(tags_ObservableLIST);
		       		
		       		FileHandler.WriteFile();
		       		break;
		       	   }
		       }
		 }
		
	}
	
	public void imageSelect(ImageView imageview)
	{
		
		//should clear the listview
		tags_ObservableLIST.removeAll(tags_ObservableLIST);		
		
		
		//then update it again
		
		
		int imageIndex = PHOTO_PANE.getChildren().indexOf(imageview);
		
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
		       			   		
		       			   		//photolist.add(new_photo);
		       			   	 //test to see if it was added
		 		       		  photolist=FileHandler.fileofUsers.get(i).getAlbumList().get(j).getPhotoList();
		       			   		break;
	       			   		}
	       			   		
	       			   }
	     
	       		break;
	       	   }
	       }
		 
		//imageIndex starts from 1
		 
		 imagePhoto = photolist.get(imageIndex-1);
		 
		  //add to observable list
    		 tags_ObservableLIST.addAll(imagePhoto.getTagList());
    		//add to the list view
    		tags.setItems(tags_ObservableLIST);
    		
    		captiontextArea.setVisible(true);
    		captiontextArea.setText(imagePhoto.getCaption());
    		
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
	
	public void Tagdialog()
	{
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle("Add Tags");
		dialog.setHeaderText("Enter the Tags");
		ButtonType logButton = new ButtonType("Add", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(logButton,ButtonType.CANCEL);
		
		
		GridPane grid=new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20,15,10,10));
		TextField fn= new TextField();
		fn.setPromptText("Tag Name");
		TextField ln=new TextField();
		ln.setPromptText("Tag Value");
		
		
		
		
		
		grid.add(new Label("Tag Name: "),0,0);
		grid.add(fn, 1, 0);
		grid.add(new Label("Tag Value: "), 0, 1);
		grid.add(ln, 1, 1);
		
		
		
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
		
		TagName=fn.getText();
		TagValue=ln.getText();
		System.out.println(TagName);
		System.out.println(TagValue);

		
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
	
	public void handleAddTag(ActionEvent e)
	{
		if(selected_photo.equals("")==true)
		{
			 Alert alert =
                     new Alert(Alert.AlertType.INFORMATION);
             alert.setContentText("You must select a photo first!");
             alert.showAndWait();
             
             return;   	
		}
		else
		{
			Tagdialog();
			
			if(TagName.equals("")==true && TagValue.equals("")==true)
			{
				 Alert alert =
	                     new Alert(Alert.AlertType.INFORMATION);
	             alert.setContentText("You must enter both Tag Name and Value!");
	             alert.showAndWait();
	             
	             return;   	
			}
			
			//remember to reset tagvalue and tag name
			
			Tag tag= new Tag(TagName,TagValue);
			
			
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
			       			   					//
			       			   					FileHandler.fileofUsers.get(i).getAlbumList().get(j).getPhotoList().get(k).setTag(tag);
			       			   					System.out.println(FileHandler.fileofUsers.get(i).getAlbumList().get(j).getPhotoList().get(k).getTagList());
			       			   				break;
			       			   				}
			       			   				
		       			   				}
		       			   				break;		
		       			   		}
		       			   		
		       			   } //end of album loop
		   
		       		break;
		       	   }
		       }
			
			  //add to observable list
       		   tags_ObservableLIST.add(tag);
       		//add to the list view
       		tags.setItems(tags_ObservableLIST);
			
		}
	}
	
	
	public void handleDeleteTag(ActionEvent e)
	{
		Tag tag = tags.getSelectionModel().getSelectedItem();

        int nextIndex = tags.getSelectionModel().getSelectedIndex() + 1;

        //Selected song is not the last one
        if(nextIndex != tags_ObservableLIST.size()){
            tags.getSelectionModel().select(nextIndex);
        }

        tags_ObservableLIST.remove(tag);
      
        
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
		       			   					//
		       			   					
		       			   					FileHandler.fileofUsers.get(i).getAlbumList().get(j).getPhotoList().get(k).getTagList().remove(tag);
		       			   					//stem.out.println(FileHandler.fileofUsers.get(i).getAlbumList().get(j).getPhotoList().get(k).getTagList());
		       			   				break;
		       			   				}
		       			   				
	       			   				}
	       			   				break;		
	       			   		}
	       			   		
	       			   } //end of album loop
	   
	       		break;
	       	   }
	       }
        
        //leHandler.fileofUsers.remove(person);
        FileHandler.WriteFile();
		
	}
	
	
	public void handleSaveCaption(ActionEvent e)
	{
		if(imagePhoto.equals(null))
		{
			System.out.println("YO PICK A PHOTO FIRST");
			return;
		}
		
		else
		{
			imagePhoto.setCaption(captiontextArea.getText());
			
			 
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
			       			   					//
			       			   					
			       			   					FileHandler.fileofUsers.get(i).getAlbumList().get(j).getPhotoList().get(k).setCaption(captiontextArea.getText());;
			       			   					//stem.out.println(FileHandler.fileofUsers.get(i).getAlbumList().get(j).getPhotoList().get(k).getTagList());
			       			   				break;
			       			   				}
			       			   				
		       			   				}
		       			   				break;		
		       			   		}
		       			   		
		       			   } //end of album loop
		   
		       		break;
		       	   }
		       }
	    	  FileHandler.WriteFile();
			// it def saves it into the photo System.out.println(imagePhoto.getCaption());
		}
		
	}
	
	

	
}
