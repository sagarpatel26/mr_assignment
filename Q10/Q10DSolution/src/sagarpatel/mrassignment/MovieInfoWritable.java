package sagarpatel.mrassignment;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class MovieInfoWritable implements Writable {

	private String Id;
	private String Name;
	private String Year;
	private int Rating;
	private double AvgRating;
	
	
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getYear() {
		return Year;
	}

	public void setYear(String year) {
		Year = year;
	}

	public int getRating() {
		return Rating;
	}

	public void setRating(int rating) {
		Rating = rating;
	}

	public double getAvgRating() {
		return AvgRating;
	}

	public void setAvgRating(double avgRating) {
		AvgRating = avgRating;
	}

	public void copyFrom(MovieInfoWritable o) {
		this.Id = o.Id;
		this.Name = o.Name;
		this.Year = o.Year;
		this.Rating = o.Rating;
		this.AvgRating = o.AvgRating;
	}
	
	@Override
	public void readFields(DataInput in) throws IOException {
	
		this.Id = in.readLine();
		this.Name = in.readLine();
		this.Year = in.readLine();
		this.Rating = in.readInt();
		this.AvgRating = in.readDouble();
	}

	@Override
	public void write(DataOutput out) throws IOException {

		out.writeBytes(this.Id + "\n");
		out.writeBytes(this.Name + "\n");
		out.writeBytes(this.Year + "\n");
		out.writeInt(this.Rating);
		out.writeDouble(this.AvgRating);
	}
	
	@Override
	public String toString() {
		
		return this.Id + "|" + this.Name + "|" + this.Year + "|" + this.Rating + "|" + this.AvgRating;
	}

}