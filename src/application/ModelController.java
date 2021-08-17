package application;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ModelController implements Initializable {
	private static ArrayList<ModelReport> ModData;

    @FXML
    private TableView<ModelReport> Model_Table_fx;

    @FXML
    private TableColumn<ModelReport, Integer> ModelID_fx,NumOfUnits_fx;

   
    @FXML
    private TableColumn<ModelReport, String> HS_fx,RD_fx,DD_fx,Status_fx;
// ModelID_fx,NumOfUnits_fx,HS_fx,RD_fx,DD_fx,Status_fx
   
    @FXML
    private Button Show,Back;

   
    @FXML
    private TextField StartDate,EndDate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
		
	}
    @FXML
    void ShowData(ActionEvent event) {
    	ModData = new ArrayList<ModelReport>();
		String From,To;
		From=StartDate.getText();
		To=EndDate.getText();
		if(From.isEmpty()||To.isEmpty()) {
			new WarningWindow().display("WArning","please Fill the two text field with dates");
		}
		else {
		if (isValidDate(From)&&isValidDate(To)) {
			ModData=new Main().prepareModelReport(From, To);
			//id,numOfTeeth,status,hoursSpent,dateRecieve,dateDilever,nextStage,dateWorked
			// ModelID_fx,NumOfUnits_fx,HS_fx,RD_fx,DD_fx,Status_fx

			ModelID_fx.setCellValueFactory(new PropertyValueFactory<ModelReport, Integer>("id"));
			Status_fx.setCellValueFactory(new PropertyValueFactory<ModelReport, String>("status"));
			HS_fx.setCellValueFactory(new PropertyValueFactory<ModelReport, String>("hoursSpent"));
			RD_fx.setCellValueFactory(new PropertyValueFactory<ModelReport, String>("dateRecieve"));
			DD_fx.setCellValueFactory(new PropertyValueFactory<ModelReport, String>("dateDilever"));
			NumOfUnits_fx.setCellValueFactory(new PropertyValueFactory<ModelReport, Integer>("numOfTeeth"));
			
			Model_Table_fx.setItems(FXCollections.observableArrayList(ModData));
		}
		else new WarningWindow().display("WArning","please enter date in correct format");
		}
    }
    
    @FXML
    public void SwithchToSection (ActionEvent event) throws IOException{   
		Parent screen2 = FXMLLoader.load(getClass().getResource("Sections.fxml"));
        Scene scene2 = new Scene(screen2);
        Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();     
        stage2.setScene(scene2);
        stage2.show();  
	}
    
    
    public static boolean isValidDate(String DateToCheck) {
		 if (DateToCheck == null) {
		        return false;
		    }
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        dateFormat.setLenient(false);
	        try {
	            dateFormat.parse(DateToCheck.trim());
	        } catch (ParseException e) {
	            return false;
	        }
	        return true;
	    }		
}