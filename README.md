# Programming Excercise

Java Version : 1.8

java -jar programming_exercise.jar -h

Usage of the program<br/>
-w     		   : word count (mandatory, must be positive)<br/>
-l    		   : word length (mandatory, must be positive)<br/>
-d                : min distance between word (mandatory, must be positive and smaller than word length)<br/>
-h                : This help menu<br/>
<br/>
-w should be smaller than ceil(((number of letters)^(word length))/((number of letters)^0 + ... + (number of letters)^distance))<br/>

## Correct usages :
java -jar programming_exercise.jar -w 10 -l 5 -d 3<br/>

Distance matrix can be found under the following path : /<jar_path>/distance_matrix.tsv

Content of distance_matrix.tsv :

![image](https://user-images.githubusercontent.com/4020240/117873153-c2a3f000-b29f-11eb-98d6-5a8c15c114fa.png)

java -jar programming_exercise.jar -w 96 -l 6 -d 3

## Wrong usages : 

java -jar programming_exercise.jar -w 10 -l 0 -d 0<br/>
Given parameters are not suitable for generate unique words!<br/>
Entered parameters : -w 10 -l 0 -d 0 <br/>
Please use for more help -h<br/>

java -jar programming_exercise.jar -w 10 -l 2<br/>
Missing mandatory parameter(s)!<br/>
Entered parameters : -w 10 -l 0 <br/>
Please use for more help -h<br/>

java -jar programming_exercise.jar -w 10 -l 10 -d 2 -x 5<br/>
This command(s) are not found : -x<br/>
Entered parameters : -w 10 -l 10 -d 2 -x 5 <br/>
Please use for more help -h<br/>

java -jar programming_exercise.jar -w 10 -l 10 -d 2aaa<br/>
Given parameters are not suitable for generate unique words!<br/>
Entered parameters : -w 10 -l 10 -d 2aaa <br/>
Please use for more help -h<br/>

## Algorithm for time- and memory-efficient generation of unique words :
    Number of letters in the alphabet = N
    Number of words of length "X" = N^X
    Step size "S" = N^0 + .... + N^(D-1)
    Number of words of length "X" with a distance "D" between = Math.ceil((N^X)/S)
    Assume, our alphabet has four letters and we want to generate unique words of length 5 with a distance at least 3:
    For a alphabet which have four letters : A, T, G, C
    Number of words of length 5 : 4^5 = 1024
    Step Size S : 4^0 + 4^1 + 4^2 = 21
    Number of words of length 5 with a distance 3 between : Math.ceil(1024/21) = 49
    My algorithm can generate 49 unique words of length 5 with at least 3 distances.
    The word is considered as a number in base 4
    A refers to 0, T -> 1, G -> 2 and C -> 3
    A word "00123" refers to AATGC. To find unique words at least with the distance 3:
    We have to add "00111" to the "00123" for the next unique word :"00123" + "00111" = "00300" -> "AACAA"
    If we continue to add "00111" : "00300" + "01011" -> "ATATT", we can generate words with at least 3 distances between them.
