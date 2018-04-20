import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Pattern;

import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.util.CoreMap;

public class QueryProcessor {
	
	public static TreeMap<String, Integer> parseQuery(String queryText, List<String> stopWordsList) {

		TreeMap<String, Integer> QueryMap = new TreeMap<String, Integer>();
		String text = queryText;
		text = text.replaceAll("[^a-zA-Z\\s]", "").replaceAll("\\s+", " ");
		// create an empty Annotation just with the given text
		Annotation document = new Annotation(text);

		// run all Annotators on this text
		Homework3.pipeline.annotate(document);

		// these are all the sentences in this document
		// a CoreMap is essentially a Map that uses class objects as
		// keys and has values with custom types
		List<CoreMap> sentences = document.get(SentencesAnnotation.class);
		String word = "";
		for (CoreMap sentence : sentences) {
			// traversing the words in the current sentence
			// a CoreLabel is a CoreMap with additional
			// token-specific methods
			for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
				// this is the text of the token
				word = token.get(LemmaAnnotation.class);
				if (stopWordsList.contains(word)) {
					continue;
				}
				

				if (!word.isEmpty()&&!word.trim().isEmpty()) {
					if (QueryMap.containsKey(word)) {
						QueryMap.put(word, QueryMap.get(word) + 1);
					} else {
						QueryMap.put(word, 1);
					}
				}

			}
		}
		return QueryMap;
	}

	public static void process(String query, List<String> stopWordsList) throws Exception {
		TreeMap<String, Integer> QueryMap = parseQuery(query, stopWordsList);
		
		SortedSet<Entry<String, Integer>> SortedMapByValuesForLemma = entriesSortedByFrequency(QueryMap);
		Integer maxQueryTermFreq = SortedMapByValuesForLemma.first().getValue();
		
		Map<Integer, Double> weight1_table = new HashMap<>();
		Map<Integer, Double> weight2_table = new HashMap<>();
		
		Map<String, Double> query_weight1_table = new HashMap<>();
		Map<String, Double> query_weight2_table = new HashMap<>();
		TreeMap<String, Double> doc_weight1_table = new TreeMap<>();
		TreeMap<String, Double> doc_weight2_table = new TreeMap<>();
		int queryLength = getQueryLenght(QueryMap);
		
		
		//System.out.println(collectionSize);
		for (String queryTerm : QueryMap.keySet()) {
			Homework3.TermNode node = Homework3.WordMap.get(queryTerm);
			if (node == null) {
				continue;
			}
			int queryTermFreq = QueryMap.get(queryTerm);
			int docFreq = node.docFrequency;
			double query_W1 = W1(queryTermFreq, maxQueryTermFreq, docFreq, Homework3.collectionSize);
			double query_W2 = W2(queryTermFreq, queryLength, Homework3.avgDocLength, docFreq, Homework3.collectionSize);
			
			query_weight1_table.put(queryTerm, query_W1*query_W1);
			query_weight2_table.put(queryTerm, query_W2*query_W2);
			
			Iterator<Map.Entry<Integer, ArrayList<Integer>>> it = node.postingFiles.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<Integer, ArrayList<Integer>> entry = it.next();
				int docId =  entry.getKey();
				int termFreq = (entry.getValue()).get(0);
				int maxTermFreq = (entry.getValue()).get(1);
				int docLenght = (entry.getValue()).get(2);
				
				double doc_W1 = W1(termFreq, maxTermFreq, docFreq, Homework3.collectionSize);
				double doc_W2 = W2(termFreq, docLenght, Homework3.avgDocLength, docFreq, Homework3.collectionSize);
				
				doc_weight1_table.put(queryTerm+":"+docId, doc_W1*doc_W1);
				doc_weight2_table.put(queryTerm+":"+docId, doc_W2*doc_W2);
				
				formVectorTable(weight1_table, docId, doc_W1 * query_W1);
				formVectorTable(weight2_table, docId, doc_W2 * query_W2);
			}
			
		}
		
		System.out.println("==========================================================================================================");
		
		System.out.print("Query: " + query);
		System.out.println();
		System.out.println("\nVector Representation of Query with Raw Count  - ");
		System.out.print("[");
		for (String entry : QueryMap.keySet()) {
			System.out.print(entry + ":" + QueryMap.get(entry) + ", ");
		}
		System.out.print("]");
		System.out.println();
		
		System.out.println("\nVector Representation of Query by Weight Schema W1 - ");
		System.out.print("[");
		for (String entry : query_weight1_table.keySet()) {
			System.out.print(entry + ":" + Math.sqrt(query_weight1_table.get(entry)) + ", ");
		}
		System.out.print("]");
		System.out.println();
		System.out.println("\nTop Five document by W1");
		Homework3.pw.println("Query::::"+query);
		Homework3.pw.println("\nVector Representation of Top5 Document by Weight Schema W1 - ");
		TopFiveRelevantDocuments(true,weight1_table,query_weight1_table,doc_weight1_table);
		System.out.println();
		
		System.out.println("\nVector Representation of Query by Weight Schema W2 - ");
		System.out.print("[");
		for (String entry : query_weight2_table.keySet()) {
			System.out.print(entry + ":" + Math.sqrt(query_weight2_table.get(entry)) + ", ");
		}
		System.out.print("]");
		System.out.println();
		
		System.out.println("\nTop Five document by W2");
		Homework3.pw.println("\nVector Representation of Top5 Document by Weight Schema W2 - ");
		TopFiveRelevantDocuments(false,weight2_table,query_weight2_table,doc_weight2_table);
		System.out.println("==========================================================================================================");
		
	}

	/**
	 * @param filename
	 * @return
	 * @throws Exception
	 * this method is responsible for reading all the queries from document,
	 * parsing those queries and process those by calling process() method.
	 */
	public static List<String> readQueriesFromDiskFile(String filename) throws Exception {
		String content = new String(Files.readAllBytes(new File(filename).toPath()));
		String[] fields = Pattern.compile("[Q0-9:]+").split(content);
		List<String> query_list = new ArrayList<>();
		for (String part : fields) {
			String query = part.trim().replaceAll("\\r\\n", " ");
			if (query.length() > 0) {
				query_list.add(query);
			}
		}
		return query_list;
	}

	/**
	 * @param QueryMap
	 * @return length of the query
	 */
	public static int getQueryLenght(Map<String, Integer> QueryMap) {
		int length = 0;
		for (String queryTerm : QueryMap.keySet()) {
			length += QueryMap.get(queryTerm);
		}
		return length;
	}

	/**
	 * @param weight_table
	 * @param docID
	 * @param w
	 * this adds the query*document similarity to weight table
	 * 
	 */
	public static void formVectorTable(Map<Integer, Double> weight_table, int docID, double weight) {
		if (weight_table.get(docID) == null) {
			weight_table.put(docID, weight);
			return;
		}
		weight_table.put(docID, weight + weight_table.get(docID));
	}

	/**
	 * @param termFreq
	 * @param maxTermFreq
	 * @param docFreq
	 * @param collectionSize
	 * @return vector by calculating the query vector or document vector by given formula W1
	 */
	public static double W1(int termFreq, int maxTermFreq, int docFreq, int collectionSize) {
		double weight1 = 0;
		try {
			weight1 = (0.4 + 0.6 * Math.log(termFreq + 0.5) / Math.log(maxTermFreq + 1.0))
					* (Math.log(collectionSize / docFreq) / Math.log(collectionSize));
		} catch (Exception e) {
			weight1 = 0;
		}
		return weight1;
	}

	/**
	 * @param termFreq
	 * @param doclength
	 * @param avgDoclength
	 * @param docFreq
	 * @param collectionSize
	 * @return vector by calculating the query vector or document vector by given formula W2
	 */
	public static double W2(int termFreq, int doclength, double avgDoclength, int docFreq, int collectionSize) {
		double weight2 = 0;
		try {
			weight2 = (0.4 + 0.6 * (termFreq / (termFreq + 0.5 + 1.5 * (doclength / avgDoclength)))
					* Math.log(collectionSize / docFreq) / Math.log(collectionSize));
		} catch (Exception e) {
			weight2 = 0;
		}
		return weight2;
	}
	public static void PrintTop5DocumentVector(boolean flag,int DocID) throws IOException{
		
	String line="";
	FileReader fileReader = new FileReader(Homework3.listOfFiles[DocID].getAbsoluteFile());
	@SuppressWarnings("resource")
	BufferedReader bufferedReader = new BufferedReader(fileReader);
	while ((line = bufferedReader.readLine()) != null) {
		line = line.toLowerCase();
		line = line.replaceAll("\\<.*?>", "");
		line = line.replaceAll("[^a-zA-Z\\s]", "").replaceAll("\\s+", " ");
		String text = line;
		Annotation document = new Annotation(text);
		Homework3.pipeline.annotate(document);
		List<CoreMap> sentences = document.get(SentencesAnnotation.class);
		String word = "";
		for (CoreMap sentence : sentences) {
			for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
				word = token.get(LemmaAnnotation.class);
				if (!Homework3.stopWordsList.contains(word) && !word.trim().isEmpty()) {
					Homework3.TermNode node = Homework3.WordMap.get(word);
					if (node == null) {
						continue;
					}
					int max_tf = Homework3.max_tf[DocID];
					int doclength = Homework3.doclen[DocID];
					
					
					if(flag){
						Double W1 = W1(node.termFrequency, max_tf, node.docFrequency, Homework3.collectionSize);
						Homework3.pw.println(String.format("%-22s %s" , word, W1) );//word+"\t\t"+W1);
					}
					else{
						Double W2 = W2(node.termFrequency, doclength, Homework3.avgDocLength, node.docFrequency, Homework3.collectionSize);
						Homework3.pw.println(String.format("%-22s %s" , word, W2 ));//word+"\t\t"+W2);
					}
				}
			}
		}
	}
	}
	
	/**
	 * @param weight1_table
	 * 
	 * This method is responsible for showing top five documents by using cosine similarity.
	 * @param doc_weight1_table 
	 * @param query_weight1_table 
	 * @throws IOException 
	 */
	public static void TopFiveRelevantDocuments(boolean flag, Map<Integer, Double> weight1_table, Map<String, Double> query_weight1_table, Map<String, Double> doc_weight1_table) throws IOException {
		
		double query_sum_square = 0;
		double doc_sum_square = 0;
		
		for(String key : query_weight1_table.keySet()){
			query_sum_square += query_weight1_table.get(key);
		}
		HashMap<String, Double> map = new HashMap<String, Double>();
		
		for(String key : doc_weight1_table.keySet()){
			String docId = key.split(":")[1];
			if(map.containsKey(docId)){
				map.put(docId, map.get(docId) + doc_weight1_table.get(key));
			}else{
				map.put(docId, doc_weight1_table.get(key));
			}
		}
		
		for(String key : map.keySet()){
			doc_sum_square += map.get(key);
		}
		
		double doc_vector_Normalised = Math.sqrt(doc_sum_square);
		double query_vector_Normalised = Math.sqrt(query_sum_square);
		
		
		for(Integer key : weight1_table.keySet()){
			weight1_table.put(key, weight1_table.get(key)/(doc_vector_Normalised* query_vector_Normalised));
		}
		
		
		TreeSet<Entry<Integer, Double>> sortedSet = new TreeSet<Entry<Integer, Double>>(new ValueComparator());
		sortedSet.addAll(weight1_table.entrySet());
		
		System.out
				.println("Rank : " + "\t Weight   " + "    : " + " DocId" + " : "  + " : " + " Title");
		Iterator<Entry<Integer, Double>> iterator = sortedSet.iterator();
		for (int i = 0; i < 5 && iterator.hasNext(); i++) {
			Entry<Integer, Double> entry = iterator.next();
			String title = Homework3.documentDetails.get(entry.getKey());
			System.out.println((i + 1) + " : " + entry.getValue() + " : " + entry.getKey()/*documentDetails.docId */+ " : "
					 + " : " + title);
			
				Homework3.pw.println("Rank::"+(i+1)+"\tDocID::"+entry.getKey());
				Homework3.pw.println("Word\t\t\tWeight");//+(flag)!=null?1:2 );
				PrintTop5DocumentVector(flag,entry.getKey());
				Homework3.pw.println("\n");
			}
		}
	
	static class ValueComparator implements Comparator<Entry<Integer, Double>> {
		@Override
		public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
			if (o1.getValue() < o2.getValue()) {
				return 1;
			}
			return -1;
		}
	}
	
	public static SortedSet<Map.Entry<String,Integer>> entriesSortedByFrequency(Map<String,Integer> map) {
	    SortedSet<Map.Entry<String,Integer>> sortedEntries = new TreeSet<Map.Entry<String,Integer>>(
	        new Comparator<Map.Entry<String,Integer>>() {
	            @Override public int compare(Map.Entry<String,Integer> e1, Map.Entry<String,Integer> e2) {
	                int res = e1.getValue().compareTo(e2.getValue());
	                if(res == -1){
	                	res = 1;
	                }else if(res == 1){
	                	res = -1;
	                }
	                if (e1.getKey().equals(e2.getKey())) {
	                    return res; 
	                } else {
	                    return res != 0 ? res : 1; 
	                }
	            }
	        }
	    );
	    sortedEntries.addAll(map.entrySet());
	    return sortedEntries;
	}
	

}
