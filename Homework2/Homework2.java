
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.time.chrono.MinguoChronology;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;



public class Homework2 {

	static PrintWriter out;
	static Stemmer stem = new Stemmer();
	static double execTime = 0.0;
	static int [] doclen;
	static int [] max_tf;
	static int [] max_sf;
	static StanfordCoreNLP pipeline1;
	static long execTime2=0;
	static long execTime3=0;
	public static void main(String[] args) throws Exception {

		String path = ".";

		TreeMap<String, TermNode> stemWordMap = new TreeMap<String, TermNode>();
		TreeMap<String, TermNode> wordMap = new TreeMap<String, TermNode>();
		File file1un = new File(path + "/index1-uncompressed");
		File file1com = new File(path + "/index1-compressed");
		File file2un = new File(path + "/index2-uncompressed");
		File file2com = new File(path + "/index2-compressed");

		File folder = new File("Cranfield");
		File[] listOfFiles = folder.listFiles();
		File stopwordsFile = new File("stopwords");
		doclen = new int [listOfFiles.length+1];
		max_tf = new int [listOfFiles.length+1];
		max_sf = new int [listOfFiles.length+1];
		Arrays.fill(doclen, 0);
		Arrays.fill(max_tf, 0);
		Arrays.fill(max_sf, 0);

		ArrayList<String> stopWordsList = extractStopWords(stopwordsFile);
		
		Properties props1 = new Properties();
		props1.setProperty("annotators", "tokenize, ssplit, pos, lemma");
		pipeline1 = new StanfordCoreNLP(props1);
		
		for (int i = 0; i < listOfFiles.length; i++) {
			int doclen1=0;
			TreeMap<String, Integer> max_lemma = new TreeMap<String, Integer>();
			TreeMap<String, Integer> max_stem = new TreeMap<String, Integer>();
			Integer DocID=0;
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
					String text = line;
					Annotation document = new Annotation(text);
					pipeline1.annotate(document);
					List<CoreMap> sentences = document.get(SentencesAnnotation.class);
					String tempWord = "";
					for (CoreMap sentence : sentences) {
						for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
							tempWord = token.get(LemmaAnnotation.class);
							doclen1++;
							if (!stopWordsList.contains(tempWord) && !tempWord.trim().isEmpty()) {
								if (max_lemma.containsKey(tempWord))
									max_lemma.put(tempWord, max_lemma.get(tempWord) + 1);
								else
									max_lemma.put(tempWord, 1);
					}
				}
		
				String[] words = line.split(" ");
				Stemmer stemmer= new Stemmer();
				
				for (int j = 0; j < words.length; j++) {
					if (!stopWordsList.contains(words[j])) {
					String stemChars = words[j].trim();
					stemmer.add(stemChars.toCharArray(), stemChars.length());
					stemmer.stem();
				if(max_stem.get(stemmer.toString()) == null)
						max_stem.put(stemmer.toString(), 1);
				else
						max_stem.put(stemmer.toString(), max_stem.get(stemmer.toString()) + 1);
					}
				}
			}
		}
			
		max_lemma = sortDecreasing(max_lemma);
		max_stem = sortDecreasing(max_stem);
		max_sf[DocID]= max_stem.firstEntry().getValue();
		max_tf[DocID]= max_lemma.firstEntry().getValue();
		doclen[DocID]= doclen1;
		}
			
			
		}
		
		

		
		tokanizeDocuments(listOfFiles, stopWordsList, wordMap, stemWordMap);
		
	
			
		ArrayList<CompressedTermIndex> compressedTermIndex1 = compressDictionaryAndPostingFile1(wordMap, 8);
		
		ArrayList<CompressedTermIndex> compressedTermIndex2 = compressDictionaryAndPostingFile2(stemWordMap, 8);
		//long endtime = new Date().getTime();
		//long execTime3 = (endtime - startTime1) / 1000;
		
		
		
		//write indexes to disk
		writeIndex(wordMap, file1un.getAbsolutePath());
		writeIndex(stemWordMap, file2un.getAbsolutePath());
		writeIndex(compressedTermIndex1, file1com.getAbsolutePath());
		writeIndex(compressedTermIndex2, file2com.getAbsolutePath());
		
		
		System.out.println("	1. Elapsed time ('wall-clock time') required to build any Uncompressed version of your index = " + execTime+"s");
		System.out.println("	1. Elapsed time ('wall-clock time') required to build Compressed version 1 of your index = " + execTime2+"ms");
		System.out.println("	1. Elapsed time ('wall-clock time') required to build Compressed version 2 of your index = " + execTime3+"ms");
		System.out.println("	2. The size of the index Version 1 uncompressed (in bytes) = " + file1un.length());
		System.out.println("	3. The size of the index Version 2 uncompressed (in bytes) = " + file2un.length());
		System.out.println("	4. The size of the index Version 1 compressed (in bytes)   = " + file1com.length());
		System.out.println("	5. The size of the index Version 2 compressed (in bytes)   = " + file2com.length());

		System.out.println("	6.1. The number of inverted lists in version 1 uncompressed = " + wordMap.size());
		System.out.println("	6.2. The number of inverted lists in version 2 uncompressed = " + stemWordMap.size());
		System.out.println("	6.3. The number of inverted lists in version 1 compressed = " + compressedTermIndex1.size());
		System.out.println("	6.4. The number of inverted lists in version 2 compressed = " + compressedTermIndex2.size());
		System.out.println("	Answer 7");

		String [] terms = {"Reynolds", "NASA", "Prandtl", "flow", "pressure", "boundary", "shock"};
		for (int i = 0; i < terms.length; i++) {
			String testWord = terms[i].toLowerCase();
			stem.add(testWord.toCharArray(), testWord.length());
			stem.stem();
			String stemmedWord = stem.toString();
			TermNode node = stemWordMap.get(stemmedWord);
			System.out.println("			Term  = " + testWord);
			System.out.println("			Document Frequency(df) 	= " + node.docFrequency);
			System.out.println("			Term Frequency(tf) 	= " + node.termFrequency);
			System.out.println("			Size of Posting file (in bytes) = " + node.postingFiles.size() * 2 * Integer.BYTES);
			System.out.println();
		}
		
		
		TermNode node12 = wordMap.get("nasa");
		System.out.println("	8).nasa");
		System.out.println("	8.a) Term freq of 'nasa':: "+node12.termFrequency);
		System.out.println("	8.b) Doc Freq of 'nasa':: "+node12.docFrequency);
		
		Iterator<Map.Entry<Integer, ArrayList<Integer>>> it1 = node12.postingFiles.entrySet().iterator();
		
		
		for (int i=0;i<3;i++) {
			  it1.hasNext();
			  Map.Entry<Integer, ArrayList<Integer>> entry = it1.next();
			  Integer docId =  entry.getKey();
			  Integer termFreq = (entry.getValue()).get(0);
			  Integer doc_Len = (entry.getValue()).get(1);
			  Integer max_tf = (entry.getValue()).get(2);
			  System.out.println("	8.c)  Document ID::"+docId +"  term_freq:: " +termFreq + "  Max_tf::"+ max_tf +"  doc_Len::"+doc_Len);
			}
		
		System.out.println("\n	9).Lemma with the Minimum Maximun Document Freq::");
		max_min_df_term(wordMap);
		System.out.println("\n	10).Stem with the Minimum Maximun Document Freq::");
		max_min_df_term(stemWordMap);
		
		ArrayList<Integer> DocID_MaxLen = findMaxValue(doclen);
		ArrayList<Integer> DocID_maxTF = findMaxValue(max_tf);
		ArrayList<Integer> DocID_maxSF = findMaxValue(max_sf);
		
		System.out.println("	11.a)- the document with the largest max_tf in collection(lemma)::"+DocID_maxTF.toString());
		System.out.println("	11.b)- the document with the largest max_tf in collection(stem)::"+DocID_maxSF.toString());
		System.out.println("	11.c)- the document with the largest doclen in the collection::"+DocID_MaxLen.toString());
		
	}
	
	public static void max_min_df_term(TreeMap<String, TermNode> wordMap){

		   ArrayList<String> maxDFTerm = new ArrayList<String>();
		   ArrayList<String> minDFTerm =  new ArrayList<String>();
		  int MaxDf = -1;
		  int MinDf = 1000000000;
		  Iterator<String> it = wordMap.keySet().iterator();
		  		
		  		while(it.hasNext()){
		  			String term = it.next();
		  			TermNode node = wordMap.get(term);
		        
		        if(node.docFrequency>MaxDf){
						    maxDFTerm.clear();
						    MaxDf=node.docFrequency;
						    maxDFTerm.add(term);
					}
		      else if(node.docFrequency==MaxDf)
					{
						maxDFTerm.add(term);
					}
					
					if(node.docFrequency<MinDf){
						minDFTerm.clear();
						MinDf=node.docFrequency;
						minDFTerm.add(term);
					}else if(node.docFrequency==MinDf)
					{
						minDFTerm.add(term);
					}
		  		}
		  		System.out.println("	Max ::	"+maxDFTerm.toString());
				System.out.println("	Min ::	"+minDFTerm.toString());
		  }
	

	public static ArrayList<Integer> findMaxValue(int [] a){
		ArrayList<Integer> temp = new ArrayList<Integer>();
		int tempMax = 0;
		for(int i = 0 ;i < a.length;i++){
			if(a[i]>tempMax){
				temp.clear();
				tempMax=a[i];
				temp.add(i);
			}
			else if(a[i]==tempMax)
				temp.add(i);
				
		}
		return temp;
	}
	
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


	
	private static void writeIndex(ArrayList<CompressedTermIndex> compressedTermIndex1, String filePath) throws Exception {
		RandomAccessFile index = new RandomAccessFile(filePath, "rw");
		index.setLength(0);
		index.writeBytes(CompressedTermIndex.termFrontCoding);
		//index.writeBytes("\n");
		for (int i = 0; i < compressedTermIndex1.size(); i++) {
			CompressedTermIndex term = compressedTermIndex1.get(i);
			index.write(term.docFrequency);//index.writeBytes("\t");
			//index.write(term.termFrequency);index.writeBytes("\t");
			index.write(term.termPointer);//index.writeBytes("\t");
			for (int j = 0; j < term.postingFile.size(); j++) {
				 index.write(term.postingFile.get(j).docId);//index.writeBytes("\t");
				 index.write(term.postingFile.get(j).termFrequency);//index.writeBytes("\t");
				 index.write(term.postingFile.get(j).max_tf1);//index.writeBytes("\t");
				 index.write(term.postingFile.get(j).doclen1);
			}
//			index.writeBytes("\n");
		}
		index.close();
	}

	
	private static void writeIndex(TreeMap<String, TermNode> wordMap, String filePath) throws Exception{
	
		RandomAccessFile index = new RandomAccessFile(filePath, "rw");
		index.setLength(0);
		Iterator<String> it = wordMap.keySet().iterator();
		while(it.hasNext()){
			String term = it.next();
			TermNode node = wordMap.get(term);
			index.writeBytes(term);
			index.writeBytes("\t");
			index.write(node.docFrequency);
			//index.write(node.termFrequency);
			Iterator<Map.Entry<Integer, ArrayList<Integer>>> it1 = node.postingFiles.entrySet().iterator();
			while (it1.hasNext()) {
				Map.Entry<Integer, ArrayList<Integer>> entry = it1.next();
				Integer docId =  entry.getKey();
				Integer termFreq = (entry.getValue()).get(0);
				index.write(docId);
				index.writeBytes("\t");
				index.write(termFreq);
				index.writeBytes("\t");
				index.write((entry.getValue()).get(1));
				index.writeBytes("\t");
				index.write((entry.getValue()).get(2));
			}
			index.writeBytes("\n");
		}
		index.close();
	}

	public static ArrayList<CompressedTermIndex> compressDictionaryAndPostingFile1(TreeMap<String, TermNode> map,
			int blockingFactor) {
		long startTime1 = new Date().getTime();
		Iterator<String> it = map.keySet().iterator();
		ArrayList<CompressedTermIndex> compressedTermIndexList = new ArrayList<CompressedTermIndex>();
		int count = -1;
		int termPointer = 0;
		String[] terms = new String[blockingFactor];
		StringBuffer result = new StringBuffer();
		while (it.hasNext()) {
			String term = it.next();
			CompressedTermIndex termNode = compressPostingFile1(map, term);
			if (count == -1) {
				termNode.termPointer = termPointer;
				count++;
				terms[count] = term;
				count++;
			} else if (count <= blockingFactor - 1) {
				terms[count] = term;
				count++;
			} else {
				String frontCodedTerm = performBlockCoding(terms);
				result.append(frontCodedTerm);
				termPointer += frontCodedTerm.length();
				count = -1;
			}
			compressedTermIndexList.add(termNode);
		}
		CompressedTermIndex.termFrontCoding = result.toString();
		long midtime = new Date().getTime();
		execTime2 = (midtime - startTime1);
		
		return compressedTermIndexList;
	}
	
	public static ArrayList<CompressedTermIndex> compressDictionaryAndPostingFile2(TreeMap<String, TermNode> map,
			int blockingFactor) {
		long startTime1 = new Date().getTime();
		Iterator<String> it = map.keySet().iterator();
		ArrayList<CompressedTermIndex> compressedTermIndexList = new ArrayList<CompressedTermIndex>();
		int count = -1;
		int termPointer = 0;
		String[] terms = new String[blockingFactor];
		StringBuffer result = new StringBuffer();
		while (it.hasNext()) {
			String term = it.next();
			CompressedTermIndex termNode = compressPostingFile2(map, term);
			if (count == -1) {
				termNode.termPointer = termPointer;
				count++;
				terms[count] = term;
				count++;
			} else if (count <= blockingFactor - 1) {
				terms[count] = term;
				count++;
			} else {
				String frontCodedTerm = performFrontCoding(terms);
				result.append(frontCodedTerm);
				termPointer += frontCodedTerm.length();
				count = -1;
			}
			compressedTermIndexList.add(termNode);
		}
		CompressedTermIndex.termFrontCoding = result.toString();
		long midtime = new Date().getTime();
		execTime3 = (midtime - startTime1);
		return compressedTermIndexList;
	}
	
	
	public static String performBlockCoding(String[] terms) {
		String encode = "";
		for(int i=0;i<terms.length;i++){
		String temp = terms[i];
		
		encode += temp.length() + temp ;
		}
		return encode;
	}

	public static String performFrontCoding(String[] terms) {
		String temp = terms[0];
		String encode = "";
		encode += temp.length() + temp + "*";
		String x = "";
		for (int i = 0; i < terms.length; i++) {
			x = terms[i];
			x = x.replace(temp, "");
			encode += x.length() + x + "*";
		}
		return encode;
	}

	
	public static CompressedTermIndex compressPostingFile1(TreeMap<String, TermNode> map, String term) {
		TermNode node = map.get(term);
		CompressedTermIndex termNode = new CompressedTermIndex();
		termNode.docFrequency = ExtractGammaCode(node.docFrequency);
		termNode.termFrequency = ExtractGammaCode(node.termFrequency);
		termNode.postingFile  = new ArrayList<>();
		termNode.postingFile.addAll(compressPostingFileUsingGammaCode(node.postingFiles));
		return termNode;
	}

	public static CompressedTermIndex compressPostingFile2(TreeMap<String, TermNode> map, String term) {
		TermNode node = map.get(term);
		CompressedTermIndex termNode = new CompressedTermIndex();
		termNode.docFrequency = deltaCode(node.docFrequency);
		termNode.termFrequency = deltaCode(node.termFrequency);
		termNode.postingFile  = new ArrayList<>();
		termNode.postingFile.addAll(compressPostingFileUsingDeltaCode(node.postingFiles));
		return termNode;
	}

	
	public static ArrayList<PostingFileNode> compressPostingFileUsingGammaCode(
			TreeMap<Integer, ArrayList<Integer>> postingFiles) {

		ArrayList<PostingFileNode> compressedList = new ArrayList<>();
		Iterator<Map.Entry<Integer, ArrayList<Integer>>> it = postingFiles.entrySet().iterator();
		Map.Entry<Integer, ArrayList<Integer>> entry = it.next();
		Integer firstDocIdEntry = entry.getKey();
		Integer termFreq = (entry.getValue()).get(0);

		PostingFileNode node = new PostingFileNode();
		node.docId = ExtractGammaCode(firstDocIdEntry);
		node.termFrequency = ExtractGammaCode(termFreq);
		node.max_tf1 = ExtractGammaCode(max_tf[firstDocIdEntry.intValue()]);
		node.doclen1 = ExtractGammaCode(doclen[firstDocIdEntry.intValue()]);
		compressedList.add(node);

		while(it.hasNext()){
			entry = it.next();
			Integer docId = entry.getKey();
			termFreq = (entry.getValue()).get(0);

			node = new PostingFileNode();
			node.docId = ExtractGammaCode(docId - firstDocIdEntry);
			node.termFrequency = ExtractGammaCode(termFreq);
			node.max_tf1 = ExtractGammaCode(max_tf[docId.intValue()]);
			node.doclen1 = ExtractGammaCode(doclen[docId.intValue()]);

			compressedList.add(node);
			firstDocIdEntry = docId;
		}
		return compressedList;
	}

	public static ArrayList<PostingFileNode> compressPostingFileUsingDeltaCode(
			TreeMap<Integer, ArrayList<Integer>> postingFiles) {

		ArrayList<PostingFileNode> compressedList = new ArrayList<>();
		Iterator<Map.Entry<Integer, ArrayList<Integer>>> it = postingFiles.entrySet().iterator();
		Map.Entry<Integer, ArrayList<Integer>> entry = it.next();
		Integer firstDocIdEntry = entry.getKey();
		Integer termFreq = (entry.getValue()).get(0);

		PostingFileNode node = new PostingFileNode();
		node.docId = deltaCode(firstDocIdEntry);
		node.termFrequency = deltaCode(termFreq);
		node.max_tf1 = deltaCode(max_tf[firstDocIdEntry.intValue()]);
		node.doclen1 = deltaCode(doclen[firstDocIdEntry.intValue()]);
		compressedList.add(node);

		while(it.hasNext()){
			entry = it.next();
			Integer docId = entry.getKey();
			termFreq = (entry.getValue()).get(0);

			node = new PostingFileNode();
			node.docId = deltaCode(docId - firstDocIdEntry);
			node.termFrequency = deltaCode(termFreq);
			node.max_tf1 = deltaCode(max_tf[docId.intValue()]);
			node.doclen1 = deltaCode(doclen[docId.intValue()]);

			compressedList.add(node);
			firstDocIdEntry = docId;
		}
		return compressedList;
	}

	
	public static byte[] deltaCode(Integer termFreq) {
		String binary = Integer.toBinaryString(termFreq);
		String gammaCode = gammaCode(binary.length());
		binary = binary.substring(1);
		String deltaCode = gammaCode + binary;
		BitSet bits = new BitSet();
		bits = fromString(deltaCode);
		return bits.toByteArray();
	}

	
	public static byte[] ExtractGammaCode(Integer docId){
		BitSet bits = new BitSet();
		bits = fromString(gammaCode(docId));
		return bits.toByteArray();
	}

	
	private static BitSet fromString(final String s) {
        return BitSet.valueOf(new long[] { Long.parseLong(s, 2) });
    }

	
	public static String gammaCode(Integer docId) {
		String binary = Integer.toBinaryString(docId);
		binary = binary.substring(1);
		String unaryCodeForLength = "";
		for (int i = 0; i < binary.length(); i++) {
			unaryCodeForLength += "1";
		}
		unaryCodeForLength += "0";
		String gamma = unaryCodeForLength + binary;
		return gamma;
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
			TreeMap<String, TermNode> wordMap, TreeMap<String, TermNode> stemWordMap)
					throws FileNotFoundException, IOException {

		long startTime = new Date().getTime();
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
					pipeline1.annotate(document);

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
							String testWord = word.trim();
							if (!testWord.isEmpty()) {
								if (wordMap.containsKey(testWord)) {
									TermNode node = wordMap.get(testWord);
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
									wordMap.put(testWord, node);
								}
							}

						}
					}

					String[] words = line.split(" ");
					
					for (int j = 0; j < words.length; j++) {
						if (stopWordsList.contains(words[j])) {
							continue;
						}
						String testWord = words[j].trim();

						stem.add(testWord.toCharArray(), testWord.length());
						stem.stem();
						String stemmedWord = stem.toString();

						if (!stemmedWord.isEmpty()) {
							if (stemWordMap.containsKey(stemmedWord)) {
								TermNode node = stemWordMap.get(stemmedWord);
								node.termFrequency += 1;
								if (node.postingFiles.containsKey(DocID)) {
									int val = (node.postingFiles.get(DocID)).get(0);
									val++;
									ArrayList<Integer> obj = new ArrayList<Integer>();
									obj.add(val);
									obj.add(doclen[DocID]);
									obj.add(max_sf[DocID]);
									node.postingFiles.put(DocID, obj);


								} else {
									ArrayList<Integer> obj = new ArrayList<Integer>();
									int termFrequency = 1;
									obj.add(termFrequency);
									obj.add(doclen[DocID]);
									obj.add(max_sf[DocID]);
									node.postingFiles.put(DocID, obj);
									node.docFrequency += 1;

								}
							} else {
								TermNode node = new TermNode();
								node.termFrequency = 1;
								node.postingFiles = new TreeMap<Integer, ArrayList<Integer>>();
								if (node.postingFiles.containsKey(DocID)) {
									int val = (node.postingFiles.get(DocID)).get(0);
									val++;

									ArrayList<Integer> obj = new ArrayList<Integer>();
									obj.add(val);
									obj.add(doclen[DocID]);
									obj.add(max_sf[DocID]);
									node.postingFiles.put(DocID, obj);

								} else {
									ArrayList<Integer> obj = new ArrayList<Integer>();
									int val = 1;
									obj.add(val);
									obj.add(doclen[DocID]);
									obj.add(max_sf[DocID]);
									node.postingFiles.put(DocID, obj);
									node.docFrequency += 1;
								}
								stemWordMap.put(stemmedWord, node);
							}
						}

					}
				}
			}
		}

		long endtime = new Date().getTime();
		execTime = (endtime - startTime) / 1000;
		return;
	}

	public static class TermNode {
		int termFrequency;
		int docFrequency;
		TreeMap<Integer,ArrayList<Integer>> postingFiles = new TreeMap<Integer, ArrayList<Integer>>();
		//TreeMap<Integer, Integer> postingFiles = new TreeMap<Integer, Integer>();
	}

	
	public static class CompressedTermIndex {
		static String termFrontCoding;
		byte[] termFrequency;
		byte[] docFrequency;
		int termPointer;
		ArrayList<PostingFileNode> postingFile = new ArrayList<PostingFileNode>();
	}
	


	public static class PostingFileNode{
		byte[] docId;
		byte[] termFrequency;
		byte[] max_tf1;
		byte[] doclen1;
	}
}