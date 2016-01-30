package sagarpatel.mrassignment;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class StudentCompositeMarksInfoWritable implements Writable {

	private String RollNo;
	private String Name;
	private int Marks;
	
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

	public int getMarks() {
		return Marks;
	}

	public void setMarks(int marks) {
		Marks = marks;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		
		this.RollNo = in.readLine();
		this.Name = in.readLine();
		this.Marks = in.readInt();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		
		out.writeBytes(this.RollNo + "\n");
		out.writeBytes(this.Name + "\n");
		out.writeInt(this.Marks);
	}
	
	@Override
	public String toString() {
		
		return this.RollNo + "|" + this.Name + "|" + this.Marks;
	}

}
