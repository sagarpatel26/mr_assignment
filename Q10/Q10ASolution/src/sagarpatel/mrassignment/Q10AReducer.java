package sagarpatel.mrassignment;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Q10AReducer 
		extends Reducer<Text, MovieInfoWritable, Text, MovieInfoWritable> {
	
	@Override
	public void reduce(Text key, Iterable<MovieInfoWritable> values, Context context)
			throws IOException, InterruptedException {
	
		MovieInfoWritable oldestMovie = new MovieInfoWritable();
		oldestMovie.setYear(Integer.MAX_VALUE);
		for (MovieInfoWritable movie:values) {
			
			if (movie.getYear()<oldestMovie.getYear()) {
				
				oldestMovie.setID(movie.getID());
				oldestMovie.setName(movie.getName());
				oldestMovie.setYear(movie.getYear());
			}
		}
		context.write(key,oldestMovie);
	}
}
