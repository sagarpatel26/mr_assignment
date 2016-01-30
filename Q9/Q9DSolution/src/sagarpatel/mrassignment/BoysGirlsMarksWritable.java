package sagarpatel.mrassignment;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class BoysGirlsMarksWritable implements Writable {

	private int BoysTotalMarks;
	private int GirlsTotalMarks;
	
	public BoysGirlsMarksWritable() {
		this.BoysTotalMarks = 0;
		this.GirlsTotalMarks = 0;
	}
	
	public int getBoysTotalMarks() {
		return BoysTotalMarks;
	}

	public void setBoysTotalMarks(int boysTotalMarks) {
		BoysTotalMarks = boysTotalMarks;
	}

	public int getGirlsTotalMarks() {
		return GirlsTotalMarks;
	}

	public void setGirlsTotalMarks(int girlsTotalMarks) {
		GirlsTotalMarks = girlsTotalMarks;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		
		this.BoysTotalMarks = in.readInt();
		this.GirlsTotalMarks = in.readInt();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		
		out.writeInt(this.BoysTotalMarks);
		out.writeInt(this.GirlsTotalMarks);
	}
	
	@Override
	public String toString(){
		
		if (this.BoysTotalMarks == this.GirlsTotalMarks)
			return "equal";
		else if (this.BoysTotalMarks > this.GirlsTotalMarks)
			return "boys";
		else
			return "girls";
	}
}
