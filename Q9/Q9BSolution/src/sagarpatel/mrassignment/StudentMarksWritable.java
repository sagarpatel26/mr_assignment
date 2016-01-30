package sagarpatel.mrassignment;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class StudentMarksWritable 
		extends StudentWritable 
		implements Writable{
	
	private int Marks;

	public int getMarks() {
		return Marks;
	}

	public void setMarks(int marks) {
		Marks = marks;
	}
	
	@Override
	public void readFields(DataInput in) throws IOException {
		
		super.readFields(in);
		this.Marks = in.readInt();
	}
	
	@Override
	public void write(DataOutput out) throws IOException {
		
		super.write(out);
		out.writeInt(Marks);
	}
	
	@Override
	public String toString() {
		
		String str = super.toString() + "|" + this.Marks;
		return str;
 	}
}
