
package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class Main {
	private static ArrayList<Sections> data;
	private static ArrayList<Model2Sec> ModelData;
	private static ArrayList<Emp2Sec> EmpData;
	private static ArrayList<ModelReport> modRep;
	private static ArrayList<EmpReport> empRep;

	private static Connection connection;
	private static String dataBaseUsername = "root";
	private static String dataBasePassword = "1994";
	private static String dataBaseName = "projecthello";
	private static String URL = "127.0.0.1";
	private static String port = "3306";

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
	}
	/*
	public ArrayList<ClockReport> clkReport(int emp,String From,String To) throws ClassNotFoundException, SQLException {
		connection = new ConnectionWithDatabase(URL, port, dataBaseName, dataBaseUsername, dataBasePassword).makeConnections();
		
		ClockRep = new ArrayList<ClockReport>();
		String SQL = "select C.employee_ID,E.employee_name,E.job_type,timediff(C.out_time,C.entry_time),E.salary_hourly_rate\r\n" + 
				"From clock C,employee E\r\n" + 
				"where E.employee_ID=C.employee_ID and E.employee_ID="+emp+" and C.entry_date>'"+From+"' and C.entry_date<'"+To+"';";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(SQL);

		while (rs.next()) {
			ClockRep.add(new ClockReport(Integer.parseInt(rs.getString(1)),rs.getString(2), rs.getString(3), rs.getString(4),Double.parseDouble(rs.getString(5))));
			}
		
		rs.close();
		statement.close();
		connection.close();
		return ClockRep;
	}
	
	
	public ArrayList<ClockReport> prepareClockReport(String From,String To) {
		int numOfEmp=0;
		
		
		//get the num of model ids
		try {
			numOfEmp=CheckEmpIDNum();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		int [] empArr = new int[numOfEmp];
		
		
		//creating and make array of model ids
		try {
			empArr=CheckEmpID();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//the final list to be printed
		ArrayList<ClockReport> FinalModel = new ArrayList<ClockReport>();
		
		for (int i = 0; i < empArr.length; i++) {
			ClockReport FinishedData = new ClockReport();
			String FinalHours="00:00:00";
			
			//array list that will get the info about each model
			//all info in this array list have the same model id
			ArrayList<ClockReport> TempModel = new ArrayList<ClockReport>();
			try {
				TempModel=clkReport(empArr[i],From,To);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			while (!TempModel.isEmpty()) {
				ClockReport dataFromArr=new ClockReport();
				dataFromArr=TempModel.remove(TempModel.size()-1);
				FinishedData.setEmployee_ID(dataFromArr.getEmployee_ID());
				FinishedData.setEmployee_name(dataFromArr.getEmployee_name());
				FinishedData.setHourly_rate(dataFromArr.getHourly_rate());
				FinishedData.setJob_type(dataFromArr.getJob_type());
				FinalHours=CalculateNumOfHours(FinalHours, dataFromArr.getTime_sum());

				
			 }
			
			
			
			FinishedData.setTime_sum(FinalHours);
			String [] time = new String[3];
			time=FinalHours.split(":");
			Double h = 0.0;
			h+=Double.parseDouble(time[0]);
			h+=(Double.parseDouble(time[1])/60);
			FinishedData.setSalary(h*FinishedData.getHourly_rate());
			FinalModel.add(FinishedData);
		//the end of the for loop
		}
		return FinalModel;
	}
	*/
		

	//the main get tuples to get info from sql to the table view
	public ArrayList<Sections> getTuples() throws ClassNotFoundException, SQLException {
		connection = new ConnectionWithDatabase(URL, port, dataBaseName, dataBaseUsername, dataBasePassword).makeConnections();
		
		data = new ArrayList<Sections>();
		String SQL = "select  S.model_ID,S.current_stage,S.next_stage,S.start_time,S.finish_time,S.date_worked,S.employee_ID\r\n" + 
				"From Sections S,Clock C,Model M, employee E\r\n" + 
				"where S.employee_ID=E.employee_ID and S.employee_ID=C.employee_ID and S.date_worked = C.entry_date and M.MID=S.model_ID and S.start_time>C.entry_time and S.start_time<C.out_time and \r\n" + 
				"S.finish_time>C.entry_time and S.finish_time<C.out_time and M.handle_to_sections_in<S.date_worked and S.current_stage=E.job_type \r\n" + 
				"order by 1,7;\r\n" + 
				"";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(SQL);

		while (rs.next()) {
			data.add(new Sections(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5)
					,rs.getString(6),Integer.parseInt(rs.getString(7))));
			}
		
		rs.close();
		statement.close();
		connection.close();
		
		return data;
	}
	
	//get the tuples with the specified model id
	public ArrayList<Sections> getTuplesWithid(int mod) throws ClassNotFoundException, SQLException {

		connection = new ConnectionWithDatabase(URL, port, dataBaseName, dataBaseUsername, dataBasePassword).makeConnections();
		
		data = new ArrayList<Sections>();
		String SQL = "select  S.model_ID,S.current_stage,S.next_stage,S.start_time,S.finish_time,S.date_worked,S.employee_ID\r\n" + 
				"From Sections S,Clock C,Model M, employee E\r\n" + 
				"where S.employee_ID=E.employee_ID and S.employee_ID=C.employee_ID and S.date_worked = C.entry_date and M.MID=S.model_ID and S.start_time>C.entry_time and S.start_time<C.out_time and \r\n" + 
				"S.finish_time>C.entry_time and S.finish_time<C.out_time and M.handle_to_sections_in<S.date_worked and S.current_stage=E.job_type and S.model_ID="+mod+"\r\n" + 
				"order by 1,7;\r\n" + 
				"";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(SQL);

		while (rs.next()) {
			data.add(new Sections(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5)
					,rs.getString(6),Integer.parseInt(rs.getString(7))));
			}
		
		rs.close();
		statement.close();
		connection.close();

		return data;
	}
	
	//get the tuples with the specified Employee id
	public ArrayList<Sections> getTuplesWithEmpid(int Emp) throws ClassNotFoundException, SQLException {
		connection = new ConnectionWithDatabase(URL, port, dataBaseName, dataBaseUsername, dataBasePassword).makeConnections();
		
		data = new ArrayList<Sections>();
		String SQL = "select  S.model_ID,S.current_stage,S.next_stage,S.start_time,S.finish_time,S.date_worked,S.employee_ID\r\n" + 
				"From Sections S,Clock C,Model M, employee E\r\n" + 
				"where S.employee_ID=E.employee_ID and S.employee_ID=C.employee_ID and S.date_worked = C.entry_date and M.MID=S.model_ID and S.start_time>C.entry_time and S.start_time<C.out_time and \r\n" + 
				"S.finish_time>C.entry_time and S.finish_time<C.out_time and M.handle_to_sections_in<S.date_worked and S.current_stage=E.job_type and S.employee_ID="+Emp+"\r\n" + 
				"order by 1,7;\r\n" + 
				"";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(SQL);

		while (rs.next()) {
			data.add(new Sections(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5)
					,rs.getString(6),Integer.parseInt(rs.getString(7))));
			}
		
		rs.close();
		statement.close();
		connection.close();
		
		return data;
	}
	
	//the main query to get the employee report with specified  employee id and between two dates
	public ArrayList<EmpReport> empReport(int emp,String From,String To) throws ClassNotFoundException, SQLException {
		connection = new ConnectionWithDatabase(URL, port, dataBaseName, dataBaseUsername, dataBasePassword).makeConnections();
		
		empRep = new ArrayList<EmpReport>();
		String SQL = "select S.employee_ID,E.employee_name,TimeDIFF(S.finish_time,S.start_time),E.job_type\r\n" + 
				"From employee E , sections S\r\n" + 
				"where S.employee_ID=E.employee_ID and S.employee_ID="+emp+" and S.date_worked>'"+From+"' and S.date_worked<'"+To+"';";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(SQL);

		while (rs.next()) {
			empRep.add(new EmpReport(Integer.parseInt(rs.getString(1)),rs.getString(2), rs.getString(3), rs.getString(4)));
			}
		
		rs.close();
		statement.close();
		connection.close();
		return empRep;
	}
	
	//this method is to arrange the data to show them in the employee report table
	public ArrayList<EmpReport> prepareEmpReport(String From,String To) {
		int numOfEmp=0;
		
		
		//get the num of model ids
		try {
			numOfEmp=CheckEmpIDNum();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		int [] empArr = new int[numOfEmp];
		
		
		//creating and make array of model ids
		try {
			empArr=CheckEmpID();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//the final list to be printed
		ArrayList<EmpReport> FinalModel = new ArrayList<EmpReport>();
		
		for (int i = 0; i < empArr.length; i++) {
			EmpReport FinishedData = new EmpReport();
			FinishedData.setEmpID(empArr[i]);
			
			String FinalHours="00:00:00";
			
			//array list that will get the info about each model
			//all info in this array list have the same model id
			ArrayList<EmpReport> TempModel = new ArrayList<EmpReport>();
			try {
				TempModel=empReport(empArr[i],From,To);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (TempModel.isEmpty()) {
				continue;
			}else {
			
			FinishedData.setNumOfModelsDone(TempModel.size());

			while (!TempModel.isEmpty()) {
				EmpReport dataFromArr=new EmpReport();
				dataFromArr=TempModel.remove(TempModel.size()-1);
				FinishedData.setEmpName(dataFromArr.getEmpName());
				FinishedData.setSection(dataFromArr.getSection());
				FinalHours=CalculateNumOfHours(FinalHours, dataFromArr.getNumOfHours());
			
			 }
			
			
			
			FinishedData.setNumOfHours(FinalHours);
			FinalModel.add(FinishedData);
		//the end of the for loop
		}
		}
		return FinalModel;
	}
	
	
	//the main query to get the model report with specified  model id and between two dates

	public ArrayList<ModelReport> modelReport(int mod,String From,String To) throws ClassNotFoundException, SQLException {
		connection = new ConnectionWithDatabase(URL, port, dataBaseName, dataBaseUsername, dataBasePassword).makeConnections();
		
		modRep = new ArrayList<ModelReport>();
		String SQL = "select M.MID,M.unit_number,TimeDIFF(S.finish_time,S.start_time),M.handle_to_sections_in,S.date_worked,S.next_stage\r\n" + 
				"From Model M , sections S\r\n" + 
				"where M.MID=S.model_ID and M.MID="+mod+" and S.date_worked>'"+From+"' and S.date_worked<'"+To+"';";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(SQL);

		while (rs.next()) {
			modRep.add(new ModelReport(Integer.parseInt(rs.getString(1)),Integer.parseInt(rs.getString(2)), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6)));
			}
		
		rs.close();
		statement.close();
		connection.close();
		return modRep;
	}
	
	//this method is to arrange the data to show them in the model report table
	public ArrayList<ModelReport> prepareModelReport(String From,String To) {
		int numOfModels=0;
		
		
		//get the num of model ids
		try {
			 numOfModels=CheckModelIDNum();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		int [] modelArr = new int[numOfModels];
		
		
		//creating and make array of model ids
		try {
			modelArr=CheckModelID();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//the final list to be printed
		
		ArrayList<ModelReport> FinalModel = new ArrayList<ModelReport>();
		
		for (int i = 0; i < modelArr.length; i++) {
			ModelReport FinishedData = new ModelReport();
			FinishedData.setId(modelArr[i]);
			
			String FinalHours="00:00:00";
			boolean FinishFlag=false;
			
			//array list that will get the info about each model
			//all info in this array list have the same model id
			ArrayList<ModelReport> TempModel = new ArrayList<ModelReport>();
			try {
				TempModel=modelReport(modelArr[i],From,To);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (TempModel.isEmpty()) {
				continue;
			}else {
			
			while (!TempModel.isEmpty()) {
				ModelReport dataFromArr=new ModelReport();
				dataFromArr=TempModel.remove(TempModel.size()-1);
				FinishedData.setNumOfTeeth(dataFromArr.getNumOfTeeth());
				FinishedData.setDateRecieve(dataFromArr.getDateRecieve());
				FinalHours=CalculateNumOfHours(FinalHours, dataFromArr.getHoursSpent());
				 
				 if ((dataFromArr.getNextStage().compareToIgnoreCase("Delivery"))==0) {
					 FinishFlag=true;
					 FinishedData.setDateDilever(dataFromArr.getDateWorked());
				 }
			 }
			
			
			if (FinishFlag==true) {
				FinishedData.setStatus("Finished");	
			}
			else {FinishedData.setStatus("Not Finished Yet ");
			FinishedData.setDateDilever("Not Finished Yet ");
			}FinishedData.setHoursSpent(FinalHours);
			FinalModel.add(FinishedData);
		//the end of the for loop
		}
		}
		return FinalModel;
	}
	
	//this method used to calculate the number of hours between two times using strings
	 public static String CalculateNumOfHours(String First,String Second) {
		 int minute1 =0,minute2 =0,hour1=0,hour2=0,hourSum=0,minuteSum=0;
		 String [] firstTime = new String[3];
		 String [] secondTime = new String[3];
		 firstTime=First.split(":");
		 secondTime=Second.split(":");
		 hour1=Integer.parseInt(firstTime[0]);
		 minute1=Integer.parseInt(firstTime[1]);
		 hour2=Integer.parseInt(secondTime[0]);
		 minute2=Integer.parseInt(secondTime[1]);
		 hourSum=hour1+hour2;
		 minuteSum=minute1+minute2;
		 minuteSum+=(hourSum*60);
		 hourSum=minuteSum/60;
		 minuteSum=minuteSum%60;
		 String Hour="",Minute="";
		 if (hourSum<10) {
			 Hour="0"+hourSum;
		 }else Hour=""+hourSum;
		 
		 if (minuteSum<10) {
			 Minute="0"+minuteSum;
		 }else Minute=""+minuteSum;

		 String Last = Hour+":"+Minute+":00";
				 
		return Last;
		 
	 }
	
	
	//this query is to check the model info when checking the entered data
	public ArrayList<Model2Sec> CheckModelWithSections(int mod) throws ClassNotFoundException, SQLException {
		connection = new ConnectionWithDatabase(URL, port, dataBaseName, dataBaseUsername, dataBasePassword).makeConnections();
		
		ModelData = new ArrayList<Model2Sec>();
		String SQL = "select S.model_ID,S.current_stage,S.next_stage\r\n" + 
				"From sections S,Model M\r\n" + 
				"where M.MID=S.model_ID and S.model_ID="+mod+" ;";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(SQL);

		while (rs.next()) {
			ModelData.add(new Model2Sec(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3)));
			}
		
		rs.close();
		statement.close();
		connection.close();
		return ModelData;
	}
	
	//this one is to Employees the model info when checking the entered data
	public ArrayList<Emp2Sec> CheckEmpWithSections(int id) throws ClassNotFoundException, SQLException {
		connection = new ConnectionWithDatabase(URL, port, dataBaseName, dataBaseUsername, dataBasePassword).makeConnections();
		
		EmpData = new ArrayList<Emp2Sec>();
		String SQL = "select E.employee_ID,E.job_type,C.entry_time,C.out_time,C.entry_date\r\n" + 
				"from clock C,employee E\r\n" + 
				"where C.employee_ID=E.employee_ID and C.employee_ID="+id+" ;";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(SQL);

		while (rs.next()) {
			EmpData.add(new Emp2Sec(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5)));
			}
		
		rs.close();
		statement.close();
		connection.close();
		return EmpData;
	}
//this is to check if the model exist or not it return array of models id	
	public int[] CheckModelID() throws ClassNotFoundException, SQLException {
		connection = new ConnectionWithDatabase(URL, port, dataBaseName, dataBaseUsername, dataBasePassword).makeConnections();
		int numberOfID=0;
		numberOfID=new Main().CheckModelIDNum();
		int[] arr=new int[numberOfID];
		String SQL = "select M.MID from Model M;";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(SQL);
		int i=0;
		while (rs.next()) {
			arr[i]=Integer.parseInt(rs.getString(1));
			i++;
			}
		rs.close();
		statement.close();
		connection.close();
		
		return arr;
	}
	//this is to check if the models number to create array of that size	
	//this query get the number of models in the database
	public int CheckModelIDNum() throws ClassNotFoundException, SQLException {
		connection = new ConnectionWithDatabase(URL, port, dataBaseName, dataBaseUsername, dataBasePassword).makeConnections();
		int numberOfID=0;
		String SQL = "select count(M.MID)\r\n" + 
				"from Model M;";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(SQL);
		while (rs.next()) {
			numberOfID=Integer.parseInt(rs.getString(1));
			}
		rs.close();
		statement.close();
		return numberOfID;
		
	}
	//this is to check if the employee exist or not it return array of models id	
	public int[] CheckEmpID() throws ClassNotFoundException, SQLException {
		connection = new ConnectionWithDatabase(URL, port, dataBaseName, dataBaseUsername, dataBasePassword).makeConnections();
		int numberOfID=0;
		numberOfID=new Main().CheckEmpIDNum();
		int[] arr=new int[numberOfID];
		String SQL = "select E.employee_ID\r\n" + 
				"from employee E;";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(SQL);
		int i=0;
		while (rs.next()) {
			arr[i]=Integer.parseInt(rs.getString(1));
			i++;
			}
		rs.close();
		statement.close();
		connection.close();
		
		return arr;
	}
	//this is to check if the emp number to create array of that size	
	//this query get the number of employee in the database
	public int CheckEmpIDNum() throws ClassNotFoundException, SQLException {
		connection = new ConnectionWithDatabase(URL, port, dataBaseName, dataBaseUsername, dataBasePassword).makeConnections();
		int numberOfID=0;
		String SQL = "select count(E.employee_ID)\r\n" + 
				"from employee E;";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(SQL);
		while (rs.next()) {
			numberOfID=Integer.parseInt(rs.getString(1));
			}
		rs.close();
		statement.close();
		return numberOfID;
		
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		return new ConnectionWithDatabase(URL, port, dataBaseName, dataBaseUsername, dataBasePassword).makeConnections();
	}
	public static void closeConnection(Connection connection) throws SQLException {
		connection.close();
	}
}