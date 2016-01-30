package sagarpatel.mrassignment;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class ClassSchoolNameWritable implements WritableComparable<ClassSchoolNameWritable> {

	private String _class;
	private String SchoolName;

	public String getSchoolName() {
		return SchoolName;
	}

	public void setSchoolName(String schoolName) {
		SchoolName = schoolName;
	}

	public String get_class() {
		return _class;
	}

	public void set_class(String _class) {
		this._class = _class;
	}
	
	
	@Override
	public void readFields(DataInput in) throws IOException {
		
		this._class = in.readLine();
		this.SchoolName = in.readLine();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		
		out.writeBytes(this._class+"\n");
		out.writeBytes(this.SchoolName+"\n");
	}

	@Override
	public String toString() {
		
		return this.SchoolName + "|" + this._class;
	}

	@Override
	public int compareTo(ClassSchoolNameWritable o) {
		String t1 = this.SchoolName + this._class;
		String t2 = o.SchoolName + o._class;
		return t1.compareTo(t2);
	} 
}
