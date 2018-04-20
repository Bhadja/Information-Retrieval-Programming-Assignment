import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Homework1 {


    static int FilesCount =0;
    static TreeMap<String, Integer> token_tree = new TreeMap<String, Integer>();
    static TreeMap<String, Integer> stem_tree = new TreeMap<String, Integer>();

    static int numberOfTokens=0;
    static int numberOfStems=0;

    public static void main(String args[]){
         String filePath = args[0].toString();

	 System.out.println("Problem 1 Tokenization::::\n");

	//to note the start time
        long startTime = Calendar.getInstance().getTimeInMillis();

        try {//scan the input file
            scanFiles(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("1) Number of tokens: " + numberOfTokens);
        System.out.println("2) Number of unique tokens: " + token_tree.size());
        System.out.println("3) Number of tokens that occur only once: " + countOnes(token_tree));

        System.out.println("5)Number of average tokens per document: " + numberOfTokens/FilesCount);

        System.out.println("4) 30 most frequent tokens: ");

        TreeMap<String, Integer> sortedTokens= sortDecreasing(token_tree);
        Iterator<Map.Entry<String, Integer>> sortedIterator = sortedTokens.entrySet().iterator();
        int count=1;
        while(count<=30){
            Map.Entry<String, Integer> entry = sortedIterator.next();
            System.out.println(count + ". " + entry.getKey() + "\t" + entry.getValue());
            count++;
        }

        System.out.println("Time taken to acquire characteristics by this Program: " + (Calendar.getInstance().getTimeInMillis()-startTime) + "ms");
        System.out.println("\n\nProblem 2 Stemming:::::\n");

        System.out.println("1) Number of distinct stems: " + stem_tree.size());
        System.out.println("2) Number of stems occurring only once: " + countOnes(stem_tree));
        System.out.println("4) Number of average stems per document: " + numberOfStems/FilesCount);

        System.out.println("3) 30 most frequent stems: ");

        TreeMap<String, Integer> sortedStemTokens= sortDecreasing(stem_tree);
        Iterator<Map.Entry<String, Integer>> sortedStemIterator = sortedStemTokens.entrySet().iterator();
        count=1;
        while(count<=30){
            Map.Entry<String, Integer> entry = sortedStemIterator.next();
            System.out.println(count + ". " + entry.getKey() + "\t" + entry.getValue());
            count++;
        }

    }


    private static void scanFiles(String filePath) throws FileNotFoundException {
        File file = new File(filePath);

        File listOfFiles[] = file.listFiles();

        for(int i=0; i<listOfFiles.length; i++){
            if(listOfFiles[i].isFile()){
                FilesCount++;
                    fetchWords(listOfFiles[i]);
            }

        }
    }
// to count the tokens occered ones only
    static int countOnes(TreeMap<String, Integer> tokens){
        int countOfOnes=0;
        Iterator<Map.Entry<String, Integer>> countIterator = token_tree.entrySet().iterator();
        while(countIterator.hasNext()){
            Map.Entry<String, Integer> entry = countIterator.next();
            if(entry.getValue()==1)
                countOfOnes++;
        }

        return countOfOnes;
    }
	//sort the treemap in decreasing order
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

    static void fetchWords(File file) throws FileNotFoundException {
        Scanner inputFile = new Scanner(file);

        while(inputFile.hasNextLine()){

            String currentLine = inputFile.nextLine();
            if(currentLine!=null && !(currentLine.contains("<") && currentLine.contains(">"))){
                currentLine = currentLine.replaceAll("[-]", "");
                StringTokenizer stringTokenizer = new StringTokenizer(currentLine);
                while(stringTokenizer.hasMoreTokens()){
                    String currentToken = stringTokenizer.nextToken().toLowerCase();

                    String currentTokenModified = currentToken.replaceAll("[^a-zA-Z]", "");

                    if(currentTokenModified.equals(""))
                        continue;
                    else{
                        //add token of each document on TreeMap of tokens here
                        numberOfTokens++;
                        if(token_tree.get(currentTokenModified) == null)
                            token_tree.put(currentTokenModified, 1);
                        else
                            token_tree.put(currentTokenModified, token_tree.get(currentTokenModified) + 1);


                        //find stems here
                        Stemmer stemmer= new Stemmer();
                        char stemChars[] = currentTokenModified.toCharArray();
                        stemmer.add(stemChars, stemChars.length);
                        stemmer.stem();

                        //add stems of each file on TreeMap of stems here
                        numberOfStems++;
                        if(stem_tree.get(stemmer.toString()) == null)
                            stem_tree.put(stemmer.toString(), 1);
                        else
                            stem_tree.put(stemmer.toString(), stem_tree.get(stemmer.toString()) + 1);

                    }
                }
            }
        }

    }
}
