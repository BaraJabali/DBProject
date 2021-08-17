package application;

public class Model2Sec {
	private int model_ID;
	private String current_stage;
	private String next_stage;
	
	public Model2Sec() {
	}
	
	public Model2Sec(int model_ID, String current_stage, String next_stage) {
		this.model_ID = model_ID;
		this.current_stage = current_stage;
		this.next_stage = next_stage;
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
	@Override
	public String toString() {
		return "Model2Sec [model_ID=" + model_ID + ", current_stage=" + current_stage + ", next_stage=" + next_stage
				+ "]\n";
	}
	

}
