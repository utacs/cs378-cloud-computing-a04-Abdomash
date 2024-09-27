package edu.cs.utexas.HadoopEx;

import java.io.IOException;
import java.util.PriorityQueue;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TopKMapper extends Mapper<Object, Text, Text, FloatWritable> {

	private static final int K = 10;
	private PriorityQueue<Entry> topK;

	public TopKMapper() {
		topK = new PriorityQueue<Entry>(K);
	}

	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		String[] columns = value.toString().split("\t");
		String taxiID = columns[0];
		Float amount = Float.parseFloat(columns[1]);
		Float duration = Float.parseFloat(columns[2]);
		float earning = amount / duration;
		topK.add(new Entry(new Text(taxiID), new FloatWritable(earning)));
		while (topK.size() > K) {
			topK.poll();
		}
		// Entry entry = new Entry(new Text(taxiID), new FloatWritable(earning));
		// context.write(entry.getKey(), entry.getValue());
	}

	@Override
	public void cleanup(Context context) throws IOException, InterruptedException {
		while (!topK.isEmpty()) {
			Entry entry = topK.poll();
			context.write(entry.getKey(), entry.getValue());
		}
	}
}