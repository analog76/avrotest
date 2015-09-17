# avrotest

This document will go over the Avro simple example.

##Avro Schema and Java File
1) First generate a avro schema. Here the employer schema is located at tools folder. 
2) Generate a java file by compiling using the avrotool. 
 The general syntax is 
 $java -jar /path/to/avro-tools-1.7.8.jar compile schema <schema file> <destination>

3) For example , the employer schema generates the java file in the employer folder. 
 java -jar avro-tools-1.7.7.jar compile schema employer.avsc  employer/
 
 

