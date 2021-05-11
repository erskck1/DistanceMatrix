# Programming Excercise

Java Version : 1.8

java -jar programming_exercise.jar -h

Usage of the program
-w     		   : word count (mandatory, must be positive)
-l    		   : word length (mandatory, must be positive)
-d           : min distance between word (mandatory, must be positive and smaller than word length)
-h           : This help menu

-w should be smaller than ceil(((number of letters)^(word length))/((number of letters)^0 + ... + (number of letters)^distance))
##Correct usages :
java -jar programming_exercise.jar -w 10 -l 5 -d 3

Distance matrix can be found under the following path : /<jar_path>/distance_matrix.tsv

Content of distance_matrix.tsv :

     	GCAGG	GCTCC	GCCTA	CAAGT	CATCG	CACAC	CTAGA	CTTCT	CTCAG	CGATC	
GCAGG	  -  	  3  	  3  	  3  	  4  	  5  	  3  	  5  	  4  	  4  	
GCTCC	  3  	  -  	  3  	  5  	  3  	  4  	  5  	  3  	  5  	  4  	
GCCTA	  3  	  3  	  -  	  5  	  5  	  4  	  4  	  5  	  4  	  4  	
CAAGT	  3  	  5  	  5  	  -  	  3  	  3  	  2  	  3  	  4  	  3  	
CATCG	  4  	  3  	  5  	  3  	  -  	  3  	  4  	  2  	  3  	  4  	
CACAC	  5  	  4  	  4  	  3  	  3  	  -  	  4  	  4  	  2  	  3  	
CTAGA	  3  	  5  	  4  	  2  	  4  	  4  	  -  	  3  	  3  	  3  	
CTTCT	  5  	  3  	  5  	  3  	  2  	  4  	  3  	  -  	  3  	  4  	
CTCAG	  4  	  5  	  4  	  4  	  3  	  2  	  3  	  3  	  -  	  4  	
CGATC	  4  	  4  	  4  	  3  	  4  	  3  	  3  	  4  	  4  	  - 

java -jar programming_exercise.jar -w 96 -l 6 -d 3

##Wrong usages : 

java -jar programming_exercise.jar -w 10 -l 0 -d 0
Given parameters are not suitable for generate unique words!
Entered parameters : -w 10 -l 0 -d 0 
Please use for more help -h

java -jar programming_exercise.jar -w 10 -l 2

Missing mandatory parameter(s)!
Entered parameters : -w 10 -l 0 
Please use for more help -h

java -jar programming_exercise.jar -w 10 -l 10 -d 2 -x 5

This command(s) are not found : -x
Entered parameters : -w 10 -l 10 -d 2 -x 5 
Please use for more help -h

java -jar programming_exercise.jar -w 10 -l 10 -d 2aaa
Given parameters are not suitable for generate unique words!
Entered parameters : -w 10 -l 10 -d 2aaa 
Please use for more help -h
