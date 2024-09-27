package edu.cs.utexas.HadoopEx;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;

public class Entry implements Comparable<Entry> {
    private Text key;
    private FloatWritable value;

    public Entry(Text key, FloatWritable value) {
        this.key = key;
        this.value = value;
    }

    public Text getKey() {
        return key;
    }

    public FloatWritable getValue() {
        return value;
    }

    @Override
    public int compareTo(Entry other) {
        float diff = value.get() - other.value.get();
        if (diff > 0) return 1;
        if (diff < 0) return -1;
        return 0;
    }
}


