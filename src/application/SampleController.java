package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;

public class SampleController implements Initializable{
	// table view  things 	
	private static ArrayList<Sections> data;
	private static ArrayList<Model2Sec> ModelData;
	private static ArrayList<Emp2Sec> EmpData;

	private static Connection connection;
	private static String dataBaseUsername = "root";
	private static String dataBasePassword = "1994";
	private static String dataBaseName = "projecthello";
	private static String URL = "127.0.0.1";
	private static String port = "3306";
	@FXML
		private TableView<Sections> TableV;
	    @FXML
	    private TableColumn<Sections,Integer> Emp_id_fx,Model_id_fx;

	    @FXML
	    private TableColumn<Sections, String> CS_fx,NS_fx,ST_fx,FT_fx,DOW_fx;
	    
	    
	    ArrayList<Sections> SecList;
		ObservableList<Sections> SecOList;
		 	
		//add info things
		 
//Model_id_txt,DOW_txt,Emp_id_txt,ST_txt,FT_txt
		    @FXML
		    private TextField Model_id_txt,DOW_txt,Emp_id_txt,ST_txt,FT_txt,Emp_Search_txt,Model_Search_txt;

		    @FXML
		    private ComboBox<String> CS_Combo;
		    
		    @FXML
		    private Button Add_bt,Edit_bt,ModelR_btn,EmpR_btn,Emp_Search_btn,Model_Search_btn;
		    
		    private int index;
		    

		    

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			//Emp_id_fx,Model_id_fx,CS_fx,NS_fx,ST_fx,FT_fx,DOW_fx
			//model_ID,current_stage,next_stage,start_time,end_time,date_of_work,employee_ID
			//here we give  the column of the table view the name we define
			Model_id_fx.setCellValueFactory(new PropertyValueFactory<Sections, Integer>("model_ID"));
			CS_fx.setCellValueFactory(new PropertyValueFactory<Sections, String>("current_stage"));
			NS_fx.setCellValueFactory(new PropertyValueFactory<Sections, String>("next_stage"));
			ST_fx.setCellValueFactory(new PropertyValueFactory<Sections, String>("start_time"));
			FT_fx.setCellValueFactory(new PropertyValueFactory<Sections, String>("end_time"));
			DOW_fx.setCellValueFactory(new PropertyValueFactory<Sections, String>("date_of_work"));
			Emp_id_fx.setCellValueFactory(new PropertyValueFactory<Sections, Integer>("employee_ID"));
			 
				try {
					SecList=new Main().getTuples();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			SecOList = FXCollections.observableArrayList(SecList);
			TableV.setItems(SecOList);
			TableV.setEditable(true);

			//the add data things
			CS_Combo.setItems(FXCollections.observableArrayList("Gypsum", "Photograph","Manufacturing","Finishing"));
		}
		
		public void ModelSearch (ActionEvent event){   
			//Emp_id_fx,Model_id_fx,CS_fx,NS_fx,ST_fx,FT_fx,DOW_fx
			//model_ID,current_stage,next_stage,start_time,end_time,date_of_work,employee_ID
			//here we give  the column of the table view the name we define
			Model_id_fx.setCellValueFactory(new PropertyValueFactory<Sections, Integer>("model_ID"));
			CS_fx.setCellValueFactory(new PropertyValueFactory<Sections, String>("current_stage"));
			NS_fx.setCellValueFactory(new PropertyValueFactory<Sections, String>("next_stage"));
			ST_fx.setCellValueFactory(new PropertyValueFactory<Sections, String>("start_time"));
			FT_fx.setCellValueFactory(new PropertyValueFactory<Sections, String>("end_time"));
			DOW_fx.setCellValueFactory(new PropertyValueFactory<Sections, String>("date_of_work"));
			Emp_id_fx.setCellValueFactory(new PropertyValueFactory<Sections, Integer>("employee_ID"));
			if(Model_Search_txt.getText().isEmpty()) {
				try {
					SecList=new Main().getTuples();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				int modelID=Integer.parseInt(Model_Search_txt.getText());
				try {
					SecList=new Main().getTuplesWithid(modelID);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			SecOList = FXCollections.observableArrayList(SecList);
			TableV.setItems(SecOList);
			TableV.setEditable(true);
			TableV.refresh();

			//the add data things
			CS_Combo.setItems(FXCollections.observableArrayList("Gypsum", "Photograph","Manufacturing","Finishing"));
		
		}
		public void EmpSearch (ActionEvent event){   
			//Emp_id_fx,Model_id_fx,CS_fx,NS_fx,ST_fx,FT_fx,DOW_fx
			//model_ID,current_stage,next_stage,start_time,end_time,date_of_work,employee_ID
			//here we give  the column of the table view the name we define
			Model_id_fx.setCellValueFactory(new PropertyValueFactory<Sections, Integer>("model_ID"));
			CS_fx.setCellValueFactory(new PropertyValueFactory<Sections, String>("current_stage"));
			NS_fx.setCellValueFactory(new PropertyValueFactory<Sections, String>("next_stage"));
			ST_fx.setCellValueFactory(new PropertyValueFactory<Sections, String>("start_time"));
			FT_fx.setCellValueFactory(new PropertyValueFactory<Sections, String>("end_time"));
			DOW_fx.setCellValueFactory(new PropertyValueFactory<Sections, String>("date_of_work"));
			Emp_id_fx.setCellValueFactory(new PropertyValueFactory<Sections, Integer>("employee_ID"));
			if(Emp_Search_txt.getText().isEmpty()) {
				try {
					SecList=new Main().getTuples();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
			 int EmpID=Integer.parseInt(Emp_Search_txt.getText());
				try {
					SecList=new Main().getTuplesWithEmpid(EmpID);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			SecOList = FXCollections.observableArrayList(SecList);
			TableV.setItems(SecOList);
			TableV.setEditable(true);
			TableV.refresh();

			//the add data things
			CS_Combo.setItems(FXCollections.observableArrayList("Gypsum", "Photograph","Manufacturing","Finishing"));
		
		}
		public void SwithchToEmpReport (ActionEvent event) throws IOException{   
			Parent screen2 = FXMLLoader.load(getClass().getResource("EmpReport.fxml"));
	        Scene scene2 = new Scene(screen2);
	        Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();     
	        stage2.setScene(scene2);
	        stage2.show();  
		}
		public void SwithchToModelReport (ActionEvent event) throws IOException{   
			Parent screen2 = FXMLLoader.load(getClass().getResource("ModeReport.fxml"));
	        Scene scene2 = new Scene(screen2);
	        Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();     
	        stage2.setScene(scene2);
	        stage2.show();
		}
		
		/*
		public void EditTuple (ActionEvent event){   
			Model_id_txt.setEditable(false);
			String model = Model_id_txt.getText();
			
			String currentstage = CS_Combo.getValue();
			String nextstage="";
			//Gypsum, Photograph, Manufacturing, Finishing, Delivery
			
			
			if (currentstage=="Gypsum") {
				nextstage="Photograph";
			}else if (currentstage=="Photograph") {
				nextstage="Manufacturing";
			}else if (currentstage=="Manufacturing") {
				nextstage="Finishing";
			}else if (currentstage=="Finishing") {
				nextstage="Delivery";
			}
			ST_txt.setEditable(false);
			FT_txt.setEditable(false);
			String starttime = ST_txt.getText();
			String finishtime = FT_txt.getText();
			String dateofwork = DOW_txt.getText();
			String empid = Emp_id_txt.getText();
			Sections secToTrans = new Sections();			
		//*	
		if (model.isEmpty() ||finishtime.isEmpty() || dateofwork.isEmpty() || empid.isEmpty() || starttime.isEmpty()) {
				new WarningWindow().display("Warnign", "Please fill all fields");
		}else { 
			//**
			if (!isValidNum(model) || !isValidNum(empid)
					|| !isValidDate(dateofwork)||!isValidTime(finishtime)
					||!isValidTime(starttime)){
				new WarningWindow().display("Warnign", "please follow the right format");
				
			}else {
				//***
			if(!isValidEmpId(empid) || !isValidModelId(model)){
					
			}else {
				//******************************************************************
				secToTrans=new Sections(Integer.parseInt(model), currentstage, nextstage, 
						starttime,finishtime,dateofwork,Integer.parseInt(empid));
				//****
				if ((!isEmpWorkInThisSection(secToTrans))|| (!isEmpWorkThatDay(secToTrans))){
				
			}else {
				if ( isEmpWorkInThisSection(secToTrans)&&isEmpWorkThatDay(secToTrans)) {
					
					 updateDataToDatabase(new Sections(Integer.parseInt(model), currentstage, nextstage, 
						starttime,finishtime,dateofwork,Integer.parseInt(empid)));
					 
					 
					 
					 
					 try {
							SecList=new Main().getTuples();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					SecOList = FXCollections.observableArrayList(SecList);
					TableV.setItems(SecOList);	 	
							
				TableV.refresh();
				CS_Combo.setValue("choose the wanted Stage");
				
				DOW_txt.clear();
				Emp_id_txt.clear();
				ST_txt.clear();
				FT_txt.clear();
				Model_id_txt.setEditable(true);
				ST_txt.setEditable(true);
				FT_txt.setEditable(true);
				}
			}
				
		}
			}
		}
	            
	            
	           // String sql = "update users set user_id= '"+value1+"',username= '"+value2+"',password= '"+
	            //        value3+"',email= '"+value4+"',type= '"+value5+"' where user_id='"+value1+"' ";
	            
	        
	    }
		void updateDataToDatabase(Sections sec) {
			try {	
				//model_ID,current_stage,next_stage,start_time,end_time,date_of_work,employee_ID
				String sql = "update Sections set start_time= '"+sec.getStart_time()+"',finish_time= '"+sec.getEnd_time()+"',date_of_work= '"+sec.getDate_of_work()+"',employee_ID= "+
						sec.getEmployee_ID()+" where model_ID="+sec.getModel_ID()+" and  current_stage= '"+sec.getCurrent_stage()+"' and next_stage= '"+
						sec.getNext_stage()+"'; ";
				executeStatement(sql);
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		*/

		@FXML
		public String ShowSelected (MouseEvent event){
			index = TableV.getSelectionModel().getSelectedIndex();
		    if (index <= -1){
		    
		        return "";
		    }
		  //Emp_id_fx,Model_id_fx,CS_fx,NS_fx,ST_fx,FT_fx,DOW_fx
		//model_ID,current_stage,next_stage,start_time,end_time,date_of_work,employee_ID
		  //Model_id_txt,DOW_txt,Emp_id_txt,ST_txt,FT_txt
		    Model_id_txt.setText(Model_id_fx.getCellData(index).toString());
		    Emp_id_txt.setText(Emp_id_fx.getCellData(index).toString());
		    ST_txt.setText(ST_fx.getCellData(index).toString());
		    FT_txt.setText(FT_fx.getCellData(index).toString());
		    DOW_txt.setText(DOW_fx.getCellData(index).toString());
		    CS_Combo.setPromptText(CS_fx.getCellData(index).toString());
		    return CS_fx.getCellData(index).toString();
		    }
		
		
		
		 public void addTuple(ActionEvent event) {
			//Emp_id_fx,Model_id_fx,CS_fx,NS_fx,ST_fx,FT_fx,DOW_fx
			//model_ID,current_stage,next_stage,start_time,end_time,date_of_work,employee_ID
			//Model_id_txt,DOW_txt,Emp_id_txt,ST_txt,FT_txt
			String model = Model_id_txt.getText();
			
			String currentstage = CS_Combo.getValue();
			String nextstage="";
			//Gypsum, Photograph, Manufacturing, Finishing, Delivery
			
			if (currentstage=="Gypsum") {
				nextstage="Photograph";
			}else if (currentstage=="Photograph") {
				nextstage="Manufacturing";
			}else if (currentstage=="Manufacturing") {
				nextstage="Finishing";
			}else if (currentstage=="Finishing") {
				nextstage="Delivery";
			}

			String starttime = ST_txt.getText();
			String finishtime = FT_txt.getText();
			String dateofwork = DOW_txt.getText();
			String empid = Emp_id_txt.getText();
			Sections secToTrans = new Sections();			
		//*	
		if (model.isEmpty() ||finishtime.isEmpty() || dateofwork.isEmpty() || empid.isEmpty() || starttime.isEmpty()) {
				new WarningWindow().display("Warnign", "Please fill all fields");
		}else { 
			//**
			if (!isValidNum(model) || !isValidNum(empid)
					|| !isValidDate(dateofwork)||!isValidTime(finishtime)
					||!isValidTime(starttime)){
				new WarningWindow().display("Warnign", "please follow the right format");
				
			}else {
				//***
			if(!isValidEmpId(empid) || !isValidModelId(model)){
					
			}else {
				//******************************************************************
				secToTrans=new Sections(Integer.parseInt(model), currentstage, nextstage, 
						starttime,finishtime,dateofwork,Integer.parseInt(empid));
				//****
				if ((!isEmpWorkInThisSection(secToTrans))|| (!isEmpWorkThatDay(secToTrans))|| (isModelEnd(secToTrans)) || (isModelWorked(secToTrans)) ||(!isModelSecValid(secToTrans))){
				
			}else {
				if ( isEmpWorkInThisSection(secToTrans)&&isEmpWorkThatDay(secToTrans)&&(!isModelEnd(secToTrans))&&(!isModelWorked(secToTrans)&&(isModelSecValid(secToTrans)))) {
					
					 addDataToDatabase(new Sections(Integer.parseInt(model), currentstage, nextstage, 
						starttime,finishtime,dateofwork,Integer.parseInt(empid)));
				TableV.getItems().add(new Sections(Integer.parseInt(model), currentstage, nextstage, 
						starttime,finishtime,dateofwork,Integer.parseInt(empid)));
				new WarningWindow().display("Notification", "!!!!!!!!!!!!!the info is added successfuly!!!!!!!!!!!!!!!!!!");

				TableV.refresh();
				CS_Combo.setValue("choose the wanted Stage");
				
				DOW_txt.clear();
				Emp_id_txt.clear();
				Model_id_txt.clear();
				ST_txt.clear();
				FT_txt.clear();
					 
				}
			}
				
		}
			}
		}
		}
		void addDataToDatabase(Sections sec) {
			try {	
				
				String SQL="Insert into Sections values("
						+sec.getModel_ID()+","+sec.getEmployee_ID()+",'"+sec.getCurrent_stage()+"','"+ sec.getNext_stage() +
						"','"+sec.getStart_time()+"','"+sec.getEnd_time()+"','"+sec.getDate_of_work()+
						"');";

				executeStatement(SQL);
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		public void executeStatement(String SQL) throws SQLException, ClassNotFoundException {
			try {
				Connection connection = new Main().getConnection();
				Statement statement = connection.createStatement();
				statement.execute(SQL);
				statement.close();
			} catch (SQLException s) {
				s.printStackTrace();
			}
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
		 public static boolean isValidTime(String TimeToCheck) {
			 if (TimeToCheck == null) {
			        return false;
			    }
		        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		        dateFormat.setLenient(false);
		        try {
		            dateFormat.parse(TimeToCheck.trim());
		        } catch (ParseException e) {
		            return false;
		        }
		        return true;
		    }
		 public static boolean isValidNum(String NumToCheck) {
			    if (NumToCheck == null) {
			        return false;
			    }
			    try {
			        int num = Integer.parseInt(NumToCheck);
			    } catch (NumberFormatException nfe) {
			        return false;
			    }
			    return true;
			}
		 public static Date toDate(String D) {
			 Date date = null;
			 try {
			     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			     dateFormat.setLenient(false);
			     date = dateFormat.parse(D);
			 } catch (ParseException ex) {
			     ex.printStackTrace();
			 }
			return date;
			 
		 }
		 public static Date toTime(String T) {
			 Date Time = null;
			 try {
			     SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
			     timeFormat.setLenient(false);
			     Time = timeFormat.parse(T);
			 } catch (ParseException ex) {
			     ex.printStackTrace();
			 }
			return Time;
			 
		 }		 
		 
		 //return true if there are model id and emp id in the database
		 public static boolean isValidModelId(String ModelID)  {
			    boolean flag=false;
			        int num = Integer.parseInt(ModelID);
			    int[] arr= {};
				try {
					arr = new Main().CheckModelID();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    for (int i = 0; i < arr.length; i++) {
					if (num==arr[i]) {
						flag = true;
						 break;

					}
				}
			    if (flag==false)					new WarningWindow().display("Warnign", "the model id you enter does not exist");

			    return flag;
			}
		 
		 public static boolean isValidEmpId(String EmpID) {
			    boolean flag=false;
			        int num = Integer.parseInt(EmpID);
			    int[] arr= {};
				try {
					arr = new Main().CheckEmpID();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    for (int i = 0; i < arr.length; i++) {
					if (num==arr[i]) {
						flag = true;
						 break;

					}
				}
if (flag==false)					new WarningWindow().display("Warnign", "the employee id you enter does not exist");

			    return flag;
			}
//return true if the model end process and if the model worked in that stage	 
		 public static boolean isModelEnd(Sections sec) {
			 ArrayList<Model2Sec> ModelData = new ArrayList<>();
			boolean flag = false;
			 try {
				ModelData=new Main().CheckModelWithSections(sec.getModel_ID());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 // match the next stage with dilevary 
			 while (!ModelData.isEmpty()) {
				 Model2Sec dateFromArr=new Model2Sec();
				 dateFromArr=ModelData.remove(ModelData.size()-1);
				 
				 if ((dateFromArr.getCurrent_stage().compareToIgnoreCase("Delivery"))==0) {
					 flag=true;
					new WarningWindow().display("Warnign", "the model you enter has been done before");

					 break;

				 }
			 }
			return flag;
			 
		 }
		 //to check if the model worked before in that section or not
		 public static boolean isModelWorked(Sections sec) {
			 ArrayList<Model2Sec> ModelData = new ArrayList<>();
			 boolean flag = false;
			 try {
				ModelData=new Main().CheckModelWithSections(sec.getModel_ID());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 // match the next stage with dilevary 
			 while (!ModelData.isEmpty()) {
				 Model2Sec dateFromArr=new Model2Sec();
				 dateFromArr=ModelData.remove(ModelData.size()-1);
				 
				 if ((sec.getCurrent_stage().compareToIgnoreCase(dateFromArr.getCurrent_stage()))==0) {
					 flag=true;
						new WarningWindow().display("Warnign", "the model you entered has been worked in this section before");
					 break;
				 }
			 }
			return flag;
			 
		 }
		 //to prevent doing the model section before the other
		 public static boolean isModelSecValid(Sections sec) {
			 ArrayList<Sections> ModelData = new ArrayList<Sections>();
			 boolean flag = false;
			 try {
					ModelData=new Main().getTuplesWithid(sec.getModel_ID());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 //--- Gypsum, Photograph, Manufacturing, Finishing, Delivery

			 if ((sec.getCurrent_stage().compareToIgnoreCase("Photograph"))==0) {
				 ArrayList<Sections> TempData = new ArrayList<Sections>();
				 TempData=ModelData;
				 while (!TempData.isEmpty()) {
					 Sections dateFromArr=new Sections();
					 dateFromArr=TempData.remove(TempData.size()-1);
					 
					 if ((dateFromArr.getCurrent_stage().compareToIgnoreCase("Gypsum"))==0) {
						 if (toDate(sec.getDate_of_work()).after(toDate(dateFromArr.getDate_of_work()))){
							 flag = true;
							 break;
						 }else if (toTime(sec.getStart_time()).after(toTime(dateFromArr.getEnd_time()))) {
							 flag = true;
							 break;

						 }
							 	
					 } 
				 }
				 
			 }else if((sec.getCurrent_stage().compareToIgnoreCase("Manufacturing"))==0) {
				 ArrayList<Sections> TempData = new ArrayList<Sections>();
				 TempData=ModelData;
				 while (!TempData.isEmpty()) {
					 Sections dateFromArr=new Sections();
					 dateFromArr=TempData.remove(TempData.size()-1);
					 
					 if ((dateFromArr.getCurrent_stage().compareToIgnoreCase("Photograph"))==0) {
						 if (toDate(sec.getDate_of_work()).after(toDate(dateFromArr.getDate_of_work()))){
							 flag = true;
							 break;
						 }else if (toTime(sec.getStart_time()).after(toTime(dateFromArr.getEnd_time()))) {
							 flag = true;
							 break;

						 }
							 	
					 } 
				 }
			 }else if((sec.getCurrent_stage().compareToIgnoreCase("Finishing"))==0) {
				 ArrayList<Sections> TempData = new ArrayList<Sections>();
				 TempData=ModelData;
				 while (!TempData.isEmpty()) {
					 Sections dateFromArr=new Sections();
					 dateFromArr=TempData.remove(TempData.size()-1);
					 
					 if ((dateFromArr.getCurrent_stage().compareToIgnoreCase("Manufacturing"))==0) {
						 if (toDate(sec.getDate_of_work()).after(toDate(dateFromArr.getDate_of_work()))){
							 flag = true;
							 break;
						 }else if (toTime(sec.getStart_time()).after(toTime(dateFromArr.getEnd_time()))) {
							 flag = true;
							 break;

						 }
							 	
					 } 
				 }
			 }else if((sec.getCurrent_stage().compareToIgnoreCase("Delivery"))==0) {
				 ArrayList<Sections> TempData = new ArrayList<Sections>();
				 TempData=ModelData;
				 while (!TempData.isEmpty()) {
					 Sections dateFromArr=new Sections();
					 dateFromArr=TempData.remove(TempData.size()-1);
					 
					 if ((dateFromArr.getCurrent_stage().compareToIgnoreCase("Finishing"))==0) {
						 if (toDate(sec.getDate_of_work()).after(toDate(dateFromArr.getDate_of_work()))){
							 flag = true;
							 break;
						 }else if (toTime(sec.getStart_time()).after(toTime(dateFromArr.getEnd_time()))) {
							 flag = true;
							 break;

						 }
							 	
					 } 
				 }
			 }
			 
			 
			 if(flag==false) {
				 new WarningWindow().display("Warnign", "you cant do this section before the other or the time is incorrect ");
			 }
			return flag;
			 
		 }

		 public static boolean isEmpWorkInThisSection(Sections sec) {
			 ArrayList<Emp2Sec> EmpData = new ArrayList<>();
			 boolean flag = false;
			 try {
				 EmpData=new Main().CheckEmpWithSections(sec.getEmployee_ID());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				new WarningWindow().display("Warnign", "cant reach the class");
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				new WarningWindow().display("Warnign", "sql error");
				e.printStackTrace();
			}
			 // match the next stage with the jop type 
			 while (!EmpData.isEmpty()) {
				 Emp2Sec dateFromArr=new Emp2Sec();
				 dateFromArr=EmpData.remove(EmpData.size()-1);
				 if ((sec.getCurrent_stage().compareToIgnoreCase(dateFromArr.getJop_type()))==0) {
					 flag=true;
					 break;
				 }
			 }
			 if (flag == false)					new WarningWindow().display("Warnign", "the employee dont work in this section");

			return flag;
			 
		 }
		 public static boolean isEmpWorkThatDay(Sections sec) {
			 ArrayList<Emp2Sec> EmpData = new ArrayList<>();
			 boolean flag = false;

				 try {
					EmpData=new Main().CheckEmpWithSections(sec.getEmployee_ID());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			 // match the next stage with the jop type 
			 while (!EmpData.isEmpty()) {
				 Emp2Sec dateFromArr=new Emp2Sec();
				 dateFromArr=EmpData.remove(EmpData.size()-1);
				 
				 if ((sec.getDate_of_work().compareToIgnoreCase(dateFromArr.getReg_date()))==0) {
					 if((toTime(sec.getStart_time())).after(toTime(dateFromArr.getEntry_time()))&&
							 (toTime(sec.getStart_time())).before(toTime(dateFromArr.getOut_time()))&&
							 (toTime(sec.getEnd_time())).after(toTime(dateFromArr.getEntry_time()))&&
							 (toTime(sec.getEnd_time())).before(toTime(dateFromArr.getOut_time()))&&
							 (toTime(sec.getStart_time())).before(toTime(sec.getEnd_time()))) {
						 flag=true;
						 break;
					 }
				 }
			 }
			 if (flag == false )	new WarningWindow().display("Warnign", "check the Date you enter or time");

			return flag;
			 
		 }
		 
		 
		
		 public static Connection getConnection() throws ClassNotFoundException, SQLException {
				return new ConnectionWithDatabase(URL, port, dataBaseName, dataBaseUsername, dataBasePassword).makeConnections();
			}
			public static void closeConnection(Connection connection) throws SQLException {
				connection.close();
			}
			
		
}
