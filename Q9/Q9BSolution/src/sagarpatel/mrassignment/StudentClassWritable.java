package sagarpatel.mrassignment;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class StudentClassWritable 
		extends StudentWritable 
		implements Writable {

	
	private String _class;
	
	@Override
	public void readFields(DataInput in) throws IOException {
		
		super.readFields(in);
		this._class = in.readLine();
	}
	
	@Override
	public void write(DataOutput out) throws IOException {
		
		super.write(out);
		out.writeBytes(this._class + "\n");
	}

	public String get_class() {
		return _class;
	}

	public void set_class(String _class) {
		this._class = _class;
	}
	
	@Override
	public String toString() {
		
		String str = super.toString() + "|" + this._class;
		return str;
 	}
}
