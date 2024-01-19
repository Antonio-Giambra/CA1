
package ca1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Validator {
    
    static String[] Checking(int  time) throws FileNotFoundException, IOException{
        String[] studentPersonalData = new String[time];
        String reading;
        BufferedReader cr = new BufferedReader(new FileReader("students.txt"));
        for(int i = 0; i < studentPersonalData.length; i+=3){
            
            reading = cr.readLine().trim();
            if(!reading.equals(" ")){
                String secondName = gettingSecondName(reading);
                studentPersonalData[0 + i] = secondName;
                reading = cr.readLine().trim();
                int test = Integer.parseInt(reading);
                String number = workload(test);
                studentPersonalData[1 + i] = number;
                reading = cr.readLine().trim();
                boolean checker = studentNumberChecker(reading);
                if(checker == true){
                   studentPersonalData[2 + i] = reading;
                } 
            }else break;
        }
                    //----------------------------------------------------------------
        return studentPersonalData;
    } 
    
    //Method for validating workload of classes
    static String workload(int classes){
        try{
            String vl = "Very Light";
            String l = "Ligth";
            String pt = "Part Time";
            String ft = "Full Time";
            String fail = "Sorry, your number is incorrect";
            if(classes == 1) return vl;
            else if(classes == 2) return l;
            else if(classes > 2 || classes <= 5) return pt;
            else if(classes >= 6) return ft;
        }catch(Exception e){
            System.out.println("workload no funciono");
        }
        return null;
    }
    
    //Method for validating and returning student second name
    public static String gettingSecondName(String name){
        try{
            String[] secondName = name.split(" ");
            return secondName[1];
        }catch(Exception e){
            System.out.println("Sorry Name is invalid or a line with white spaces has been detected");
        }
        return null;
    }
    
    //Method for checking whether the student number is valid or not
    public static boolean studentNumberChecker(String name){
        try{
            return true;
        }catch(Exception e){
            System.out.println("checker no funciono");
        }
        return false;
    }
    
    //Method to create a new file after validations
    public static boolean statusCreator(String[] data){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("status.txt"));
            for(int k = 0; k < data.length; k+=3){
                bw.write(data[2 + k] + " - " + data[0 + k] + "\n" + data[1 + k] + "\n");
            }
            bw.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
}
