package application;

public class Sections {
private int model_ID;
private String current_stage;
private String next_stage;
private String start_time;
private String end_time;
private String date_of_work;
private int employee_ID;
//model_ID,current_stage,next_stage,start_time,end_time,date_of_work,employee_ID

@Override
public String toString() {
	return "Sections [model_ID=" + model_ID + ", current_stage=" + current_stage + ", next_stage=" + next_stage
			+ ", start_time=" + start_time + ", end_time=" + end_time + ", date_of_work=" + date_of_work
			 + ", employee_ID=" + employee_ID + "]";
}


public Sections() {
	super();
}


public Sections(int model_ID, String current_stage, String next_stage, String start_time, String end_time,String date_of_work,
		  int employee_ID) {
	super();
	this.model_ID = model_ID;
	this.current_stage = current_stage;
	this.next_stage = next_stage;
	this.start_time = start_time;
	this.end_time = end_time;
	this.date_of_work = date_of_work;
	this.employee_ID = employee_ID;
}


public int getModel_ID() {
	return model_ID;
}


public void setModel_ID(int model_ID) {
	this.model_ID = model_ID;
}


public String getCurrent_stage() {
	return current_stage;
}


public void setCurrent_stage(String current_stage) {
	this.current_stage = current_stage;
}


public String getNext_stage() {
	return next_stage;
}


public void setNext_stage(String next_stage) {
	this.next_stage = next_stage;
}


public String getStart_time() {
	return start_time;
}


public void setStart_time(String start_time) {
	this.start_time = start_time;
}


public String getEnd_time() {
	return end_time;
}


public void setEnd_time(String end_time) {
	this.end_time = end_time;
}



public String getDate_of_work() {
	return date_of_work;
}


public void setDate_of_work(String date_of_work) {
	this.date_of_work = date_of_work;
}


public int getEmployee_ID() {
	return employee_ID;
}


public void setEmployee_ID(int employee_ID) {
	this.employee_ID = employee_ID;
}


}
