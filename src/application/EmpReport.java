package application;

public class EmpReport {
private int empID,NumOfModelsDone;
private String empName,NumOfHours,Section;
//empID,NumOfModelsDone,empName,NumOfHours,Section
public EmpReport(int empID, String empName, String numOfHours, String section) {
	this.empID = empID;
	this.empName = empName;
	NumOfHours = numOfHours;
	Section = section;
}
public EmpReport(int empID, String empName, String numOfHours, String section, int numOfModelsDone) {
	this.empID = empID;
	NumOfModelsDone = numOfModelsDone;
	this.empName = empName;
	NumOfHours = numOfHours;
	Section = section;
}
public EmpReport() {
	super();
}
public int getEmpID() {
	return empID;
}
public void setEmpID(int empID) {
	this.empID = empID;
}
public int getNumOfModelsDone() {
	return NumOfModelsDone;
}
public void setNumOfModelsDone(int numOfModelsDone) {
	NumOfModelsDone = numOfModelsDone;
}
public String getEmpName() {
	return empName;
}
public void setEmpName(String empName) {
	this.empName = empName;
}
public String getNumOfHours() {
	return NumOfHours;
}
public void setNumOfHours(String numOfHours) {
	NumOfHours = numOfHours;
}
public String getSection() {
	return Section;
}
public void setSection(String section) {
	Section = section;
}
@Override
public String toString() {
	return "EmpModel [empID=" + empID + ", NumOfModelsDone=" + NumOfModelsDone + ", empName=" + empName
			+ ", NumOfHours=" + NumOfHours + ", Section=" + Section + "]\n";
}


}
