package cryptofun.decryptiontools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class NgramSearcher {

	Splitter splitter;
	public NgramSearcher(Splitter splitter) {
		this.splitter = splitter;
	}
	
	public HashMap<String, Integer> find(String input, int n) {
		String[] splitted = splitter.split(input);
		HashMap<String, Integer> results = new HashMap<String, Integer>();
		for(int k =0; k < splitted.length - n; k++) {
			String ngram = "";
			for(int i = k; i < k+n ;i++) {
				ngram = ngram + splitted[i];
			}
			int indexSearcher = 0;
			List<Integer> indices = new ArrayList<Integer>();
			while(indexSearcher != -1) {
				indexSearcher = input.indexOf(ngram, indexSearcher);
				if(indexSearcher != -1) {
					indices.add(indexSearcher);
					indexSearcher++;
				}
			}
			int count = indices.size();
			results.put(ngram, count);
		}
		return results; 
	}
	
	public HashMap<String, Integer>[] find(String input, Iterator<Integer> iter) {
		LinkedList<HashMap<String, Integer>> results = new LinkedList<HashMap<String, Integer>>();
		while(iter.hasNext()) {
			results.add(find(input,iter.next()));
		}
		HashMap<String, Integer>[] output = (HashMap<String, Integer>[]) results.toArray();
		return output;
	}

}
