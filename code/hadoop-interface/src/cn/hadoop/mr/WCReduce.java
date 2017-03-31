package cn.hadoop.mr;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WCReduce extends Reducer<Text, LongWritable, Text, LongWritable> {

	@Override
	protected void reduce(Text k2, Iterable<LongWritable> v2s, Context context)
			throws IOException, InterruptedException {
		// 接受数据
		// 定义一个计数器
		Long counter = 0L;
		for (LongWritable v2 : v2s) {
			counter += v2.get();
		}
//		输出值
		context.write(k2, new LongWritable(counter));
	}

}
