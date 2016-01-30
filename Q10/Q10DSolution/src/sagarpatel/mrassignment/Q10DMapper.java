package sagarpatel.mrassignment;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Q10DMapper 
		extends Mapper<Object, Text, Text, MovieInfoWritable> {

	private static Text _key = new Text("DummyKey");
	private static MovieInfoWritable _value = new MovieInfoWritable();
	
	@Override
	public void map(Object key, Text value, Context context) 
			throws IOException, InterruptedException {
		
		// format = id|name|year|rating|avg
		String line = value.toString();
		
		if (!line.isEmpty()) {
			
			StringTokenizer st = new StringTokenizer(line,"|");
			_value.setId(st.nextToken());
			_value.setName(st.nextToken());
			_value.setYear(st.nextToken());
			if (_value.getYear().equals("0")) 
				_value.setYear("unknown");
			_value.setRating(Integer.parseInt(st.nextToken()));
			_value.setAvgRating(Double.parseDouble(st.nextToken()));
			context.write(_key, _value);
		}
	}
}
