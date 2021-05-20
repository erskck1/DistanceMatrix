# Programming Excercise

To generate 96 words of length with at least 3 distances use the following command : java -jar programming_exercise.jar -w 96 -l 6 -d 3

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
