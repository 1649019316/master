package cn.hadoop.mr;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WCMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
//		接受数据v1
		String line = value.toString();
//		分割数据
		String[] words = line.split(" ");
		for (String w : words) {
//			出现一次，计数一次
			context.write(new Text(w), new LongWritable(1));
		}
	}
	
}
