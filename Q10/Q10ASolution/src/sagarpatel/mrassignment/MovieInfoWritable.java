package sagarpatel.mrassignment;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class MovieInfoWritable implements Writable {

	private String ID;
	private String Name;
	private int Year;
	
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getYear() {
		return Year;
	}

	public void setYear(int year) {
		Year = year;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		
		this.ID = in.readLine();
		this.Name = in.readLine();
		this.Year = in.readInt();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		
		out.writeBytes(this.ID + "\n");
		out.writeBytes(this.Name + "\n");
		out.writeInt(this.Year);
	}
	
	@Override
	public String toString() {
		
		return this.ID + "|" + this.Name + "|" + this.Year;
	}

}
