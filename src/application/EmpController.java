package application;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class EmpController implements Initializable {
	private static ArrayList<EmpReport> EmpData;

    @FXML
    private TableView<EmpReport> Emp_Table_fx;
    @FXML
    private TableColumn<EmpReport, Integer> EmpID_fx,NumOfModels_fx;
    @FXML
    private TableColumn<EmpReport, String> Emp_Name_fx,Section_fx,HW_fx;
    @FXML
    private Button Show,Back;
    @FXML
    private TextField StartDate,EndDate;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
		
	}
    @FXML
	public void ShowData (ActionEvent event){   
		EmpData = new ArrayList<EmpReport>();
		String From,To;
		From=StartDate.getText();
		To=EndDate.getText();
		if(From.isEmpty()||To.isEmpty()) {
			new WarningWindow().display("WArning","please Fill the two text field with dates");
		}
		else {
		if (isValidDate(From)&&isValidDate(To)) {
		EmpData=new Main().prepareEmpReport(From, To);
		//empID,NumOfModelsDone,empName,NumOfHours,Section

		EmpID_fx.setCellValueFactory(new PropertyValueFactory<EmpReport, Integer>("empID"));
		Emp_Name_fx.setCellValueFactory(new PropertyValueFactory<EmpReport, String>("empName"));
		Section_fx.setCellValueFactory(new PropertyValueFactory<EmpReport, String>("Section"));
		HW_fx.setCellValueFactory(new PropertyValueFactory<EmpReport, String>("NumOfHours"));
		NumOfModels_fx.setCellValueFactory(new PropertyValueFactory<EmpReport, Integer>("NumOfModelsDone"));
		Emp_Table_fx.setItems(FXCollections.observableArrayList(EmpData));
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