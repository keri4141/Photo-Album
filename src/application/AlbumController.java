package application;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
	
	
    
    public void start(Stage mainStage)
    {
    	//when window is closed save the file
        mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
                FileHandler.WriteFile();
            }
        });
        
        
    	
    }
    
    
	public void handleRename()
	{
		
		
	}
	
	public void handleCreate()
	{
		
		
	}
	
	public void handleDelete()
	{
		
	}
	
	public void handleOpen()
	{
		
		
	}

}
