package application;

public class ModelReport {
private int id,numOfTeeth ;
private String status,hoursSpent,dateRecieve,dateDilever,nextStage,dateWorked;
//id,numOfTeeth,status,hoursSpent,dateRecieve,dateDilever,nextStage,dateWorked
public ModelReport() {
}


public ModelReport(int id, int numOfTeeth, String hoursSpent, String dateRecieve, String dateWorked, String nextStage) {
	this.id = id;
	this.numOfTeeth = numOfTeeth;
	this.hoursSpent = hoursSpent;
	this.dateRecieve = dateRecieve;
	this.nextStage = nextStage;
	this.dateWorked = dateWorked;
}


public ModelReport(int id, int numOfTeeth, String status, String hoursSpent, String dateRecieve, String dateDilever,
		String nextStage, String dateWorked) {
	this.id = id;
	this.numOfTeeth = numOfTeeth;
	this.status = status;
	this.hoursSpent = hoursSpent;
	this.dateRecieve = dateRecieve;
	this.dateDilever = dateDilever;
	this.nextStage = nextStage;
	this.dateWorked = dateWorked;
}


public String getDateWorked() {
	return dateWorked;
}

public void setDateWorked(String dateWorked) {
	this.dateWorked = dateWorked;
}



public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getNumOfTeeth() {
	return numOfTeeth;
}
public void setNumOfTeeth(int numOfTeeth) {
	this.numOfTeeth = numOfTeeth;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getHoursSpent() {
	return hoursSpent;
}
public void setHoursSpent(String hoursSpent) {
	this.hoursSpent = hoursSpent;
}
public String getDateRecieve() {
	return dateRecieve;
}
public void setDateRecieve(String dateRecieve) {
	this.dateRecieve = dateRecieve;
}
public String getDateDilever() {
	return dateDilever;
}
public void setDateDilever(String dateDilever) {
	this.dateDilever = dateDilever;
}
public String getNextStage() {
	return nextStage;
}

public void setNextStage(String nextStage) {
	this.nextStage = nextStage;
}
@Override
public String toString() {
	return "ModelReport [id=" + id + ", numOfTeeth=" + numOfTeeth + ", status=" + status + ", hoursSpent=" + hoursSpent
			+ ", dateRecieve=" + dateRecieve + ", dateDilever=" + dateDilever + ", nextStage=" + nextStage + "]\n";
}

}
