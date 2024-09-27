package edu.cs.utexas.HadoopEx;

import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.HashMap;

public class TopKTaxiMap {
    public class Entry implements Comparable<Entry> {
        private String key;
        private float value;
    
        public Entry(String key, float value) {
            this.key = key;
            this.value = value;
        }
    
        public String getKey() {
            return key;
        }

        public float getValue() {
            return value;
        }
    
        @Override
        public int compareTo(Entry other) {
            if (other == null) {
                return 1;
            }
            return Float.compare(getValue(), other.getValue());
        }

        @Override
        public boolean equals(Object other) {
            if (other == null || !(other instanceof Entry)) {
                return false;
            }
            Entry otherEntry = (Entry) other;
            return key.equals(otherEntry.key);
        }
    }

    private PriorityQueue<Entry> topK;
    private final int K;

    public TopKTaxiMap(final int K) {
        topK = new PriorityQueue<>(K, Comparator.reverseOrder());
        this.K = K;
    }

    public void add(String key, float value) {
        Entry entry = new Entry(key, value);
        topK.add(entry);
    }

    public Entry[] getTopK() {
        Entry[] result = new Entry[Math.min(K, topK.size())];
        for (int i = 0; i < result.length; i++) {
            result[i] = topK.poll();
        }
        return result;
    }
}

