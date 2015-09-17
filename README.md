# avrotest

This document will go over the Avro simple example.

##Avro Schema and Java File
1) First generate a avro schema. Here the employer schema is located at tools folder. 

2) Generate a java file by compiling using the avrotool. 
 The general syntax is 
 
 java -jar /path/to/avro-tools-1.7.8.jar compile schema <schema file> <destination>

3) For example , the employer schema generates the java file in the employer folder. 

 java -jar avro-tools-1.7.7.jar compile schema employer.avsc  employer/
 
 
## Avro Serializer
1) Set the values in the employer object.

        Employer employer = new Employer();

        employer.setName("glassdoor");
        employer.setLocation("100 Highway");
        employer.setDoj("2007");
        employer.setCity("Sausalito");
        employer.setTotalCount(500);
        employer.setAvgSalary(new Float(123444.2));
        
2) create DatumWriter  and DataFileWriter.

        File file = new File(fileName);
        DatumWriter<Employer> employerDatumWriter =
                new SpecificDatumWriter<Employer>(Employer.class);
        DataFileWriter<Employer> employerFileWriter =
                new DataFileWriter<Employer>(employerDatumWriter);


3) Add the employer object in the fileWriter and it generates the output in the employer.avro file.

            employerFileWriter.create(employer.getSchema(), file);


## Avro Deserializer

1) Create DatumReader and FileReader

        File file = new File(fileName);
        DatumReader<Employer> employerDatumReader = new SpecificDatumReader<Employer>(Employer.class);
        DataFileReader<Employer> employerFileReader = null;
        employerFileReader = new DataFileReader<Employer>(file, employerDatumReader);
        
 2) Iterate it throught he filereader object and it returns the employer object. 
 
 while (employerFileReader.hasNext())
        {
            employer = employerFileReader.next();
            System.out.println(employer);
        }
        
        
 
 
 For more information, check the source code.





