package sagarpatel.mrassignment;

import java.io.IOException;

import org.apache.hadoop.mapreduce.Reducer;

public class Q9DReducer 
		extends Reducer<ClassSchoolNameWritable, BoysGirlsMarksWritable, ClassSchoolNameWritable, BoysGirlsMarksWritable> {

	private static BoysGirlsMarksWritable finalR = new BoysGirlsMarksWritable(); 
	
	@Override
	public void reduce(ClassSchoolNameWritable key, Iterable<BoysGirlsMarksWritable> values, Context context)
			throws IOException, InterruptedException {
		
		finalR.setBoysTotalMarks(0);
		finalR.setGirlsTotalMarks(0);
		for (BoysGirlsMarksWritable value : values) {
			
			finalR.setBoysTotalMarks( finalR.getBoysTotalMarks() + value.getBoysTotalMarks() );
			finalR.setGirlsTotalMarks( finalR.getGirlsTotalMarks() + value.getGirlsTotalMarks() );
		}
		context.write(key, finalR);
	}
}
