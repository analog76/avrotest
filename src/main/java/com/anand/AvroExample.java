package com.anand;

import com.anand.avro.Employer;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;

/**
 * Created by anand.ranganathan on 9/16/15.
 */
public class AvroExample {

    public static void main(String[] args) throws IOException {


        long d1  = new java.util.Date().getTime();

        String fileName = "emplopyer_"+d1+".avro";

        AvroExample avro = new AvroExample();
        avro.serializer(fileName);
        avro.deSerializer(fileName);


    }


    public void serializer(String fileName){


        Employer employer = new Employer();

        // There are 3 way to construct an object in avro.

        // Method 1 to create an object.

        employer.setName("glassdoor");
        employer.setLocation("100 Highway");
        employer.setDoj("2007");
        employer.setCity("Sausalito");
        employer.setTotalCount(500);
        employer.setAvgSalary(new Float(123444.2));


        // Method 2 to create an object
        //  public Employer(java.lang.CharSequence name,
        //                  java.lang.CharSequence doj,
        //                  java.lang.CharSequence location,
        //                  java.lang.CharSequence city,
        //                  java.lang.Integer totalCount,
        //                  java.lang.Float avgSalary) {


        Employer employer2 = new Employer("Uber","2010","Market St","San Francisco",2500,new Float(123444));

        // Method 3 to create an object using builder.

    /*    Employer employer3 = Employer.newBuilder()
                                .setName("AirBNB")
                                .setCity("San Francisco")
                                .setTotalCount(1000)
                                .setAvgSalary(new Float(52344));
*/



        File file = new File(fileName);
        DatumWriter<Employer> employerDatumWriter =
                new SpecificDatumWriter<Employer>(Employer.class);
        DataFileWriter<Employer> employerFileWriter =
                new DataFileWriter<Employer>(employerDatumWriter);

        try {
            employerFileWriter.create(employer.getSchema(), file);
            employerFileWriter.append(employer);
            employerFileWriter.append(employer2);
            //   employerFileWriter.append(employer2);
            employerFileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void deSerializer(String fileName){
        File file = new File(fileName);
        DatumReader<Employer> employerDatumReader = new SpecificDatumReader<Employer>(Employer.class);
        DataFileReader<Employer> employerFileReader = null;
        try {
            employerFileReader = new DataFileReader<Employer>(file, employerDatumReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Employer employer = null;
        while (employerFileReader.hasNext())
        {
            employer = employerFileReader.next();
            System.out.println(employer);
        }

    }

}
