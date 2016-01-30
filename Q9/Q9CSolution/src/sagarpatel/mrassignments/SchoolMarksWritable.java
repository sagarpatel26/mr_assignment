package sagarpatel.mrassignments;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class SchoolMarksWritable implements WritableComparable<SchoolMarksWritable> {

	
	private String SchoolName;
	private int Marks;
	
	public String getSchoolName() {
		return SchoolName;
	}

	public void setSchoolName(String schoolName) {
		SchoolName = schoolName;
	}

	public int getMarks() {
		return Marks;
	}

	public void setMarks(int marks) {
		Marks = marks;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		
		this.SchoolName = in.readLine();
		this.Marks = in.readInt();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		
		out.writeBytes(this.SchoolName + "\n");
		out.writeInt(this.Marks);
	}

	@Override
	public int compareTo(SchoolMarksWritable o) {
		int cmp;
		if ( (cmp = this.SchoolName.compareToIgnoreCase(o.SchoolName))==0 ) {
			return (this.Marks>o.Marks)?1:-1;
		}
		else 
			return cmp;
	}

	@Override
	public String toString() {
		
		return this.SchoolName + "|" + this.Marks;
 	}
}
