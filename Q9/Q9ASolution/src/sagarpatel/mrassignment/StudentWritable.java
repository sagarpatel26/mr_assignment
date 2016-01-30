package sagarpatel.mrassignment;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class StudentWritable 
		implements WritableComparable<StudentWritable> {

	private String RollNo;
	private String Name;
	private String SchoolName;
	
	public String getRollNo() {
		return RollNo;
	}

	public void setRollNo(String rollNo) {
		RollNo = rollNo;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getSchoolName() {
		return SchoolName;
	}

	public void setSchoolName(String schoolName) {
		SchoolName = schoolName;
	}

	@Override
	public void readFields(DataInput in) 
			throws IOException {
		
		this.Name = in.readLine();
		this.RollNo = in.readLine();
		this.SchoolName = in.readLine();
	}

	@Override
	public void write(DataOutput out) 
			throws IOException {

		out.writeBytes(this.Name + "\n");
		out.writeBytes(this.RollNo + "\n");
		out.writeBytes(this.SchoolName + "\n");
	}
	
	@Override
	public int compareTo(StudentWritable o) {
		String t1 = this.RollNo + this.Name + this.SchoolName;
		String t2 = o.RollNo + o.Name + o.SchoolName;
		return t1.compareTo(t2);
	}
	
	@Override
	public String toString() {
		
		return this.RollNo + "|" + this.Name + "|" + this.SchoolName;
 	}
}
