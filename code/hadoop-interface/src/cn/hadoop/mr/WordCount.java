package cn.hadoop.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class WordCount {
	public static void main(String[] args) throws Exception {
		Job job=Job.getInstance(new Configuration());
//		设置main方法所在的类
		job.setJarByClass(WordCount.class);
//		设置mapper
		job.setMapperClass(WCMapper.class);
//		设置输出类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
//		设置输入文件路径
		FileInputFormat.setInputPaths(job, new Path("/words.txt"));
//		设置reducer
		job.setReducerClass(WCReduce.class);
//		设置输出类型
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		FileOutputFormat.setOutputPath(job, new Path("/wcount.txt"));
//		将任务提交，true代表打印进度和详情，false不打印进度和详情
		job.waitForCompletion(true);
	}
	
}
