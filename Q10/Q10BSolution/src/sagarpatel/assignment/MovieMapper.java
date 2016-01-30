package sagarpatel.assignment;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MovieMapper 
		extends Mapper<Object, Text, IntWritable, Text> {

	private static IntWritable Id = new IntWritable();
	private static Text movieInfo = new Text(); 
	
	@Override
	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {
		
		String line = value.toString();
		if (!line.isEmpty()) {
			
			StringTokenizer st = new StringTokenizer(line, "|");
			Id.set(Integer.parseInt(st.nextToken()));
			movieInfo.set("A" + st.nextToken() + "|" + st.nextToken());
			context.write(Id, movieInfo);
		}
	}
}
