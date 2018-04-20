Terminal commands to run the Program on UTD machine.

1. javac Stemmer.java

2. javac Homework1.java

3. java Homework1 Cranfield/ > README.txt

4. vi README.txt or O/P will be in the README.txt file.


Additional Software :: N/A but Stemmer.java file is used as the addtional resoure here.

1. The time required by the prog to acquire the text characteristics is given in the README.txt file as a part of O/P.

2_A. It converts all the raw tokens to lowercase. As in the following line.
currentLine = currentLine.replaceAll("[-]", ""); 

2_B. It replaces all - with nothing so, the middle-class == middleclass	tean-ager== teanager.
String currentTokenModified = currentToken.replaceAll("[^a-zA-Z]", "");

2_C. It replaces all ' with nothing so, sheriff's will be sheriffs, university's will be universitys.

2_D. It replaces all . with nothing so U.S and U.k will be US and UK respectively.

3. Major algorithms : Stemmer.java contains the basic stemming algoritham as in five steps as follows.

step1: 	step1() gets rid of plurals and -ed or -ing. e.g.
          caresses  ->  caress
          ponies    ->  poni
          ties      ->  ti
          caress    ->  caress
          cats      ->  cat
          feed      ->  feed
          agreed    ->  agree
          disabled  ->  disable
          matting   ->  mat
          mating    ->  mate
          meeting   ->  meet
          milling   ->  mill
          messing   ->  mess
          meetings  ->  meet

step2:  step2() turns terminal y to i when there is another vowel in the stem.

step3:  step3() maps double suffices to single ones. so -ization ( = -ize plus -ation) maps to -ize etc. note that the string before the suffix must give
         m() > 0.

step4:  step4() deals with -ic-, -full, -ness etc. similar strategy to step3.

step5:  step5() takes off -ant, -ence etc., in context <c>vcvc<v>.

where m is nothing but the measure of the word defined as follows

[C](VC)^m[V]

where m indicates the number of VC sequences.
e.g	 <c><v>       gives 0
         <c>vc<v>     gives 1
         <c>vcvc<v>   gives 2
         <c>vcvcvc<v> gives 3

#) To get the most 30 frequent tokens and stems program does sorting and reverse the TreeMap key Value pair and return the first 30 words.

Major data structures : 
			TreeMap
 in java for the tokens and stems to be stored and accessed.
			It stores and accesses the keys/tokens using Red-Black tree algorithm.

			Uses object of the StringTokenizer class to handle the each word separated by the space, fetched from the file to make it a token. 
