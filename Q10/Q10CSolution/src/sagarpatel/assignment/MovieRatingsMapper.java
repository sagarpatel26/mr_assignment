package sagarpatel.assignment;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MovieRatingsMapper extends
		Mapper<Object, Text, IntWritable, Text> {
	
	private static IntWritable FKId = new IntWritable();
	private final static Text movieRatingsInfo = new Text(); 
	
	@Override
	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {
		
		String line = value.toString();
		if (!line.isEmpty()) {
			
			StringTokenizer st = new StringTokenizer(line, "|");
			st.nextToken();
			FKId.set(Integer.parseInt(st.nextToken()));
			movieRatingsInfo.set("B" + st.nextToken());
			context.write(FKId, movieRatingsInfo);
		}
	}
}
