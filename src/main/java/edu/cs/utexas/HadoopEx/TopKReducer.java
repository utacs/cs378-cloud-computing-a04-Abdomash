package edu.cs.utexas.HadoopEx;

import java.io.IOException;
import java.util.PriorityQueue;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TopKReducer extends  Reducer<Text, Text, Text, FloatWritable> {     
     private static final int K = 10;
     private PriorityQueue<Entry> topK;

     public TopKReducer() {
          topK = new PriorityQueue<Entry>(K);
     }

     public void reduce(Text key, Iterable<FloatWritable> values, Context context) throws IOException, InterruptedException {
          for (FloatWritable value : values) { // values is always a single element
               topK.add(new Entry(new Text(key), new FloatWritable(value.get())));
               if (topK.size() > K) {
                    topK.poll();
               }
          }
     }

     @Override
     public void cleanup(Context context) throws IOException, InterruptedException {
          while (!topK.isEmpty()) {
               Entry entry = topK.poll();
               context.write(entry.getKey(), entry.getValue());
          }
     }
}