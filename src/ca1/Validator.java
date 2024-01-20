
package ca1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Validator {
    
    static String[] Checker(int  time) throws FileNotFoundException, IOException{
        String[] studentPersonalData = new String[time];
        String reading;
        BufferedReader cr = new BufferedReader(new FileReader("students.txt"));
        for(int i = 0; i < studentPersonalData.length; i+=3){
            
            reading = cr.readLine().trim();
            if(!reading.equals(" ")){
                //Checking name and surname with gettingSecondName Method
                boolean secondName = gettingSecondName(reading);
                //checking how many clasess the person has with worload method
                String reading2 = cr.readLine().trim();
                int test = Integer.parseInt(reading2);
                String number = workload(test);
                //Checking student number with studentNumberChecker method
                String reading3 = cr.readLine().trim();
                boolean checker = studentNumberChecker(reading3);
                
                //If Checker accept the three requirement for every student, it will insert information to array studentPersonalData. If not, it skips the student and then check another student
                if(secondName == true && number != null && checker == true){
                   String[] secondNameTrue = reading.split(" ");
                   studentPersonalData[0 + i] = secondNameTrue[1];
                   studentPersonalData[1 + i] = number;
                   studentPersonalData[2 + i] = reading3;
                } else continue;
            }else break;
        }
                    //----------------------------------------------------------------
        return studentPersonalData;
    } 
    //---------------------------------------------------------------------------------------------------------------------
    
    //Method for validating student name, if first name and second name fulfil requirements method will return true.
    public static boolean gettingSecondName(String name){
        String[] secondName = name.split(" ");
        try{
            String regexFirstName = "^[a-zA-Z]+$";
            String regexSecondName = "^[a-zA-Z0-9]+$";
            if(secondName[0].matches(regexFirstName) && secondName[1].matches(regexSecondName)){
                return true;
            }
        }catch(Exception e){
            System.out.println("Sorry, name " + secondName[0] + " does not comply with the requirements.\nThe first name must be letters only. The second name can be letters and/or numbers and must be separated from the first name by a single space; ");
        }
        System.out.println("Sorry, name " + secondName[0] + " does not comply with the requirements. \n The first name must be letters only. The second name can be letters and/or numbers and must be separated from the first name by a single space; ");
        System.out.println();
        return false;
    }
    //----------------------------------------------------------------------------------------------------------------------
    
    //Method for validating workload of classes
    static String workload(int classes){
        try{
            String vl = "Very Light";
            String l = "Ligth";
            String pt = "Part Time";
            String ft = "Full Time";
            if(classes == 1) return vl;
            else if(classes == 2) return l;
            else if(classes > 2 || classes <= 5) return pt;
            else if(classes >= 6 && classes < 9) return ft;
            
        }catch(Exception e){
            System.out.println(e);
        }
        System.out.println("Sorry, " + classes + " classes does not comply with the requirements\nThe number of classes must be an integer value between 1 and 8 (inclusive).");
        System.out.println();
        return null;
    }
   //---------------------------------------------------------------------------------------------------------------------
    
    //Method for checking whether the student number is valid or not, if student number is valid, method will return true.
    public static boolean studentNumberChecker(String studentNumber){
        try{
            int studentYear = Integer.parseInt(studentNumber.substring(0,2));
            
            int numberAfter = 201;
            boolean correctNumberAfter = false;
            
            if(studentNumber.substring(4,5).matches("[0-9]+")){
                int reasonableNumber = Integer.parseInt(studentNumber.substring(4));
                if(reasonableNumber <= numberAfter) {
                    correctNumberAfter = true;
                }else correctNumberAfter = false;
            }else if(studentNumber.substring(5).matches("[0-9]+")){
                int reasonableNumber = Integer.parseInt(studentNumber.substring(5));
                if(reasonableNumber < numberAfter) correctNumberAfter = true;
            }
            
            if(studentNumber.length() >= 6){
                if(studentNumber.substring(0,2).matches("[0-9]+") && studentYear >= 20){
                    if(studentNumber.substring(2,4).matches("[a-zA-Z]+")){
                        if(studentNumber.substring(4,5).matches("[a-zA-Z0-9]+")){
                            if(studentNumber.substring(5).matches("[0-9]+") && correctNumberAfter == true){ 
                              return true;  
                            }
                        }
                    }
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
        System.out.println("Sorry, name " + studentNumber + " does not comply with the requirements. \nThe student number must be a minimun of 6 characters with the first 2 characters being numbers (20 or higher).\nThe 3rd and 4th characters (and possibly 5th) being a letter, and everything after the last letter being a number between 1 - 200");
        
        System.out.println();
        return false;
    }
    //-----------------------------------------------------------------------------------------------------------------------
    
    //Method to create a new file after validations
    public static boolean statusCreator(String[] data){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("status.txt"));
            for(int k = 0; k < data.length; k+=3){
                bw.write(data[2 + k] + " - " + data[0 + k] + "\n" + data[1 + k] + "\n");
            }
            System.out.println("Status.txt File has been created succesfully");
            bw.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    //------------------------------------------------------------------------------------------------------------------------
}
