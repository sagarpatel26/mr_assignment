package sagarpatel.mrassignment;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Q10DReducer 
		extends Reducer<Text, MovieInfoWritable, Text, MovieInfoWritable> {

	private static final Text First = new Text("MostRated");
	private static final Text Second = new Text("SecondMostRated");
	private static final Text Third = new Text("ThirdMostRated");
	
	@Override
	public void reduce(Text key, Iterable<MovieInfoWritable> values, Context context)
			throws IOException, InterruptedException {
		
		MovieInfoWritable first =  new MovieInfoWritable();
		MovieInfoWritable second =  new MovieInfoWritable();
		MovieInfoWritable third =  new MovieInfoWritable();
		
		first.setRating(-1);
		second.setRating(-1);
		third.setRating(-1);
	
		for (MovieInfoWritable value:values) {
			
			if (value.getRating() > first.getRating()) {
				first.copyFrom(value);
			}
			else if (value.getRating() > second.getRating()) {
				second.copyFrom(value);
			}
			else if(value.getRating() > third.getRating()) {
				third.copyFrom(value);
			}
		}
		
		
		if (context.getTaskAttemptID().toString().contains("_r_")) {
			
			context.write(First, first);
			context.write(Second, second);
			context.write(Third, third);
		}
		else {
			
			context.write(key, first);
			context.write(key, second);
			context.write(key, third);
		}
	}
}
