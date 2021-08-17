package application;

public class Emp2Sec {
	private int Emp_ID;
	private String Jop_type;
	private String Entry_time;
	private String Out_time;
	private String reg_date;
	
	public Emp2Sec() {
		super();
	}
	public Emp2Sec(int emp_ID, String jop_type, String entry_time, String out_time, String reg_date) {
		Emp_ID = emp_ID;
		Jop_type = jop_type;
		Entry_time = entry_time;
		Out_time = out_time;
		this.reg_date = reg_date;
	}
	public int getEmp_ID() {
		return Emp_ID;
	}
	public void setEmp_ID(int emp_ID) {
		Emp_ID = emp_ID;
	}
	public String getJop_type() {
		return Jop_type;
	}
	public void setJop_type(String jop_type) {
		Jop_type = jop_type;
	}
	public String getEntry_time() {
		return Entry_time;
	}
	public void setEntry_time(String entry_time) {
		Entry_time = entry_time;
	}
	public String getOut_time() {
		return Out_time;
	}
	public void setOut_time(String out_time) {
		Out_time = out_time;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	@Override
	public String toString() {
		return "Emp2Sec [Emp_ID=" + Emp_ID + ", Jop_type=" + Jop_type + ", Entry_time=" + Entry_time + ", Out_time="
				+ Out_time + ", reg_date=" + reg_date + "]\n";
	}


}
