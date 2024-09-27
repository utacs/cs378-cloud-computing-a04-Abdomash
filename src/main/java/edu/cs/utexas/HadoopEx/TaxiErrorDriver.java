package edu.cs.utexas.HadoopEx;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TaxiErrorDriver extends Configured implements Tool {

	private static final Logger LOGGER = LogManager.getLogger(TaxiErrorDriver.class.getName());

	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		int res = ToolRunner.run(new Configuration(), new TaxiErrorDriver(), args);
		System.exit(res);
	}

	/**
	 * 
	 */
	public int run(String args[]) {
		try {
			Configuration conf = new Configuration();

			Job job1 = new Job(conf, "Taxi-Error-First");
			job1.setJarByClass(TaxiErrorDriver.class);
			job1.setMapperClass(TaxiErrorMapper.class);
			job1.setReducerClass(TaxiErrorReducer.class);
			job1.setOutputKeyClass(Text.class);
			job1.setOutputValueClass(BooleanWritable.class);
			FileInputFormat.addInputPath(job1, new Path(args[0]));
			job1.setInputFormatClass(TextInputFormat.class);
			FileOutputFormat.setOutputPath(job1, new Path("temp"));
			job1.setOutputFormatClass(TextOutputFormat.class);

			if (!job1.waitForCompletion(true)) {
				return 1;
			}

			Job job2 = new Job(conf, "Taxi-Error-Second");
			job2.setJarByClass(TaxiErrorDriver.class);
			job2.setNumReduceTasks(1);
			job2.setMapperClass(TopKMapper.class);
			job2.setReducerClass(TopKReducer.class);
			job2.setOutputKeyClass(Text.class);
			job2.setOutputValueClass(FloatWritable.class);
			FileInputFormat.addInputPath(job2, new Path("temp"));
			job2.setInputFormatClass(TextInputFormat.class);
			FileOutputFormat.setOutputPath(job2, new Path(args[1]));
			job2.setOutputFormatClass(TextOutputFormat.class);

			if (!job2.waitForCompletion(true)) {
				return 1;
			}

			return 0;

		} catch (InterruptedException | ClassNotFoundException | IOException e) {
			LOGGER.error("Error during mapreduce job.", e);
			return 2;
		}
	}
}
