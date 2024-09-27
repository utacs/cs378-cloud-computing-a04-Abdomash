package edu.cs.utexas.HadoopEx;

import java.io.IOException;
import java.util.PriorityQueue;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.cs.utexas.HadoopEx.TopKTaxiMap.Entry;

public class TopKMapper extends Mapper<Object, Text, Text, FloatWritable> {
	private static final Logger LOGGER = LogManager.getLogger(TopKMapper.class.getName());

	private static final int K = 10;
	private TopKTaxiMap topK = new TopKTaxiMap(K);

	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		String[] columns = value.toString().split("\t");
		String taxiID = columns[0];
		Float amount = Float.parseFloat(columns[1]);
		Float duration = Float.parseFloat(columns[2]);
		float earning = amount / duration;
		topK.add(taxiID, earning);
	}

	@Override
	public void cleanup(Context context) throws IOException, InterruptedException {
		for (Entry entry : topK.getTopK()) {
			Text taxiID = new Text(entry.getKey());
			FloatWritable earning = new FloatWritable(entry.getValue());
			context.write(taxiID, earning);
		}
	}
}