package sagarpatel.mrassignment;
import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Q10AMapper extends Mapper<Object, Text, Text, MovieInfoWritable> {

	private final static Text valid = new Text("oldestMovie");
	private static MovieInfoWritable movie = new MovieInfoWritable();
	
	@Override
	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {
		
		String line = value.toString();
		if (!line.isEmpty()) {
			
			StringTokenizer st = new StringTokenizer(line,"|");
			movie.setID(st.nextToken());
			movie.setName(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			if (y!=0) {
				movie.setYear(y);
				context.write(valid, movie);
			}
		}
	}
}
