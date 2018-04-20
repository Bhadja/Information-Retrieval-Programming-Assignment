import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.regex.Pattern;

import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;


public class Homework3 {

	static PrintWriter out;
	public static TreeMap<String, TermNode> WordMap = new TreeMap<String, TermNode>();
	public static Map<Integer, String> documentDetails = new HashMap<Integer, String>();
	static Pattern pattern = Pattern.compile("<.?title>", Pattern.CASE_INSENSITIVE);
	public static int avgDocLength;
	public static int collectionSize;
	static StanfordCoreNLP pipeline;	
	static Properties props;
	static int [] doclen;
	static int [] max_tf;
	static File[] listOfFiles;
	static PrintWriter pw;
	static ArrayList<String> stopWordsList ;
	public static void main(String[] args) throws Exception {

		File folder = new File("Cranfield");
		File stopwordsFile = new File("stopwords");
		File queryFile = new File("hw3.queries");
		File file = new File("top5Vector");
		FileWriter fw = new FileWriter(file);
		pw = new PrintWriter(fw);
		listOfFiles = folder.listFiles();
		stopWordsList = extractStopWords(stopwordsFile);
		props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, pos, lemma");
		pipeline = new StanfordCoreNLP(props);
		doclen = new int [listOfFiles.length+1];
		max_tf = new int [listOfFiles.length+1];
		maxTermFreqForEachDoc(listOfFiles,stopWordsList);
		tokanizeDocuments(listOfFiles, stopWordsList, WordMap);
		avgDocLength = getAvgDocLength();
		collectionSize = listOfFiles.length-1;
		//System.out.println(documentDetails);	
		List<String> queries = QueryProcessor.readQueriesFromDiskFile(queryFile.getAbsolutePath());

		for (String query : queries) {
			QueryProcessor.process(query, stopWordsList);
		}
	}
	
	/**
	 * maxTermFreq of each doc and Title of each document 
	 * @throws IOException 
	 */
	static void maxTermFreqForEachDoc(File [] listOfFiles,ArrayList<String> stopWordsList) throws IOException{
	for (int i = 0; i < listOfFiles.length; i++) {
		int doclen1=0;
		TreeMap<String, Integer> max_lemma = new TreeMap<String, Integer>();

		Integer DocID=0;
		if (listOfFiles[i].isFile()) {
			String[] name=listOfFiles[i].getName().split("d");
			if(name[0]!=null && name[0].contains("cran"))
			DocID=Integer.parseInt(name[1]);
		
		String title = getTitle(listOfFiles[i]);
		documentDetails.put(DocID,title);
		
			String line = "";
			FileReader fileReader = new FileReader(listOfFiles[i].getAbsoluteFile());
			@SuppressWarnings("resource")
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				line = line.toLowerCase();
				line = line.replaceAll("\\<.*?>", "");
				line = line.replaceAll("[^a-zA-Z\\s]", "").replaceAll("\\s+", " ");
				String text = line;
				Annotation document = new Annotation(text);
				pipeline.annotate(document);
				List<CoreMap> sentences = document.get(SentencesAnnotation.class);
				String word = "";
				for (CoreMap sentence : sentences) {
					for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
						word = token.get(LemmaAnnotation.class);
						if (!stopWordsList.contains(word) && !word.trim().isEmpty()) {
							doclen1++;
							if (max_lemma.containsKey(word))
								max_lemma.put(word, max_lemma.get(word) + 1);
							else
								max_lemma.put(word, 1);
						}
					}
				}
				}
				max_lemma = sortDecreasing(max_lemma);
				max_tf[DocID]= max_lemma.firstEntry().getValue();
				doclen[DocID]= doclen1;
				}
			}
		}
	
		
	/**
	 * Sort Map in decreasing order 
	 */
	public static TreeMap<String, Integer> sortDecreasing(final TreeMap<String, Integer> tokens){
		Comparator<String> stringComparator = new Comparator<String>() {
				public int compare(String o1, String o2) {
						if (tokens.get(o2).compareTo(tokens.get(o1)) == 0)
								return 1;
						else
								return tokens.get(o2).compareTo(tokens.get(o1));
				}
		};

		TreeMap<String, Integer> sortedTokens = new TreeMap<String, Integer>(stringComparator);
		sortedTokens.putAll(tokens);
		return sortedTokens;
}

	public static ArrayList<String> extractStopWords(File stopwordsFile) throws FileNotFoundException, IOException {

		ArrayList<String> stopWordsList = new ArrayList<String>();
		String line = "";
		FileReader fileReader = new FileReader(stopwordsFile);
		@SuppressWarnings("resource")
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		while ((line = bufferedReader.readLine()) != null) {
			stopWordsList.add(line.trim());
		}

		return stopWordsList;
	}

	public static void tokanizeDocuments(File[] listOfFiles, ArrayList<String> stopWordsList,
			TreeMap<String, TermNode> wordMap)
					throws FileNotFoundException, IOException {

		
		Integer DocID=0;
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				String[] name=listOfFiles[i].getName().split("d");
				if(name[0]!=null && name[0].contains("cran"))
				DocID=Integer.parseInt(name[1]);
				String line = "";
				FileReader fileReader = new FileReader(listOfFiles[i].getAbsoluteFile());
				@SuppressWarnings("resource")
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				while ((line = bufferedReader.readLine()) != null) {
					line = line.toLowerCase();
					line = line.replaceAll("\\<.*?>", "");
					line = line.replaceAll("[^a-zA-Z\\s]", "").replaceAll("\\s+", " ");

					// read some text in the text variable
					String text = line;

					// create an empty Annotation just with the given text
					Annotation document = new Annotation(text);

					// run all Annotators on this text
					pipeline.annotate(document);

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
							word = word.trim();
							if (!word.isEmpty()) {
								if (wordMap.containsKey(word)) {
									TermNode node = wordMap.get(word);
									node.termFrequency += 1;
									if (node.postingFiles.containsKey(DocID)) {
										int val = (node.postingFiles.get(DocID)).get(0);
										val++;
										ArrayList<Integer> obj = new ArrayList<Integer>();
										obj.add(val);
										obj.add(doclen[DocID]);
										obj.add(max_tf[DocID]);
										node.postingFiles.put(DocID, obj);

									
									} else {
										ArrayList<Integer> obj = new ArrayList<Integer>();
										int termFrequency = 1;
										obj.add(termFrequency);
										obj.add(doclen[DocID]);
										obj.add(max_tf[DocID]);
										node.postingFiles.put(DocID, obj);
										node.docFrequency += 1;
									}
								} else {
									TermNode node = new TermNode();
									node.termFrequency = 1;
									node.postingFiles = new TreeMap<Integer, ArrayList<Integer>>();
									if (node.postingFiles.containsKey(String.valueOf(DocID))) {
										int val = (node.postingFiles.get(DocID)).get(0);
										val++;

										ArrayList<Integer> obj = new ArrayList<Integer>();
										obj.add(val);
										obj.add(doclen[DocID]);
										obj.add(max_tf[DocID]);
										node.postingFiles.put(DocID, obj);
									} else {
										ArrayList<Integer> obj = new ArrayList<Integer>();
										int val = 1;
										obj.add(val);
										obj.add(doclen[DocID]);
										obj.add(max_tf[DocID]);
										node.postingFiles.put(DocID, obj);
										node.docFrequency += 1;
									}
									wordMap.put(word, node);
								}
							}

						}
					}
				}
			}
		}

	return;
	}

	public static String getTitle(File file) {
		try {
			String content = new String(Files.readAllBytes(file.toPath()));
			String[] fields = pattern.split(content);
			if (fields.length > 1) {
				return fields[1].replace("\n", " ");
			} else
				System.out.println("...." + file.getPath());

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public static int getAvgDocLength() throws IOException { 
		int sum=0;
		int n = listOfFiles.length-1;
		for(int i=1;i<doclen.length;i++)
			sum+=doclen[i];
		
		return sum/n;
	}

	
	/**
	 *	termFreq
	 *  docFreq at dic node
	 *  posting file 
	 *		Doc ID
	 *		Term Freq in Doc
	 *		docLen of the DocID
	 *		max-tf in DocTD
	 *
	 */
	public static class TermNode {
		int termFrequency;
		int docFrequency;
		TreeMap<Integer,ArrayList<Integer>> postingFiles = new TreeMap<Integer, ArrayList<Integer>>();
	}
}
