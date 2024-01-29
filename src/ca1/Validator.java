
package ca1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Validator {
    
    //Main method for the distribution of information which will be evaluated by different methods.
    static String[] Checker(int  time, String path){
        //Creating a new Array which containe how many lines has received from Main project
        String[] studentPersonalData = new String[time];
        try{
        
        //Creation of Buffered Reader in order to read the document which has been received from Main Project
        String reading;
        BufferedReader cr = new BufferedReader(new FileReader(path));
        
        //This for loop will read every 3 lines of the document in order to get every student and then calling back to other methods to get a verified result
        for(int i = 0; i < studentPersonalData.length; i+=3){
            
            //cleaning all white spaces in each line
            reading = cr.readLine().trim();
            
            //while the line read is not null, the for loop can work. Otherwise, it will jump by 3 lines, but if it doesn't find another true line, then the loop break itself.
            if(reading != null){
                
                //Checking name and surname with gettingSecondName Method.
                boolean secondName = gettingSecondName(reading);
                
                //checking how many clasess the person has with Workload Method.
                String reading2 = cr.readLine().trim();
                int test = Integer.parseInt(reading2);
                String number = workload(test);
                
                //Checking student number with studentNumberChecker Method.
                String reading3 = cr.readLine().trim();
                boolean checker = studentNumberChecker(reading3);
                
                //If Checker accept the three requirement for each student, it will insert information to array studentPersonalData. Otherwise, it will skip the student and check another student.
                if(secondName == true && number != null && checker == true){
                   String[] secondNameTrue = reading.split(" ");
                   studentPersonalData[0 + i] = secondNameTrue[1];
                   studentPersonalData[1 + i] = number;
                   studentPersonalData[2 + i] = reading3;
                } else continue;
                
            }else break;
        }
        //We can close the Buffered Reader
        cr.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return studentPersonalData;
    } 
    //---------------------------------------------------------------------------------------------------------------------
    
    //Method for validating student name, if first name and second name fulfil requirements method will return true.
    public static boolean gettingSecondName(String name){
        String[] secondName = name.split(" ");
        try{
            //Checking if first name only has letters and second name only has letters and numbers
            String regexFirstName = "^[a-zA-Z]+$";
            String regexSecondName = "^[a-zA-Z0-9]+$";
            if(secondName[0].matches(regexFirstName) && secondName[1].matches(regexSecondName)){
                return true;
            }
        }catch(Exception e){
            System.out.println(e);
        }
        System.out.println("Sorry, name " + secondName[0] + " does not comply with the requirements. \n The first name must be letters only. The second name can be letters and/or numbers and must be separated from the first name by a single space; ");
        System.out.println();
        return false;
    }
    //----------------------------------------------------------------------------------------------------------------------
    
    //Method for validating workload of classes. It cannot be more than 8.
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
            
            //Creation of variables for flowing control of the conditionals.
            int studentYear = Integer.parseInt(studentNumber.substring(0,2));
            int numberAfter = 200;
            boolean correctNumberAfter = false;
            
            //FIRST PART - Checking whether the 5th is a number or letter and also the number after the letters must be between 1 - 200.
            if(studentNumber.substring(4,5).matches("[0-9]+")){
                
                int reasonableNumber = Integer.parseInt(studentNumber.substring(4));
                if(reasonableNumber <= numberAfter) {
                    correctNumberAfter = true;
                }else correctNumberAfter = false;
                
            }else if(studentNumber.substring(5).matches("[0-9]+")){
                
                int reasonableNumber = Integer.parseInt(studentNumber.substring(5));
                if(reasonableNumber <= numberAfter) correctNumberAfter = true;
                
            }
            
            //SECOND PART - Verification of the whole student number requirements.
            
            //It must be equal or longer than 6.
            if(studentNumber.length() >= 6){
                //The two first numbers must be equal or greater than 20
                if(studentNumber.substring(0,2).matches("[0-9]+") && studentYear >= 20){
                    //The 3rd and 4th position must be letters
                    if(studentNumber.substring(2,4).matches("[a-zA-Z]+")){
                        //The 5th position must be letter or number
                        if(studentNumber.substring(4,5).matches("[a-zA-Z0-9]+")){
                            //From 6th position must be numbers and also it should have already been checked on first part
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
        System.out.println("Sorry, " + studentNumber + " does not comply with the requirements. \nThe student number must be a minimun of 6 characters with the first 2 characters being numbers (20 or higher).\nThe 3rd and 4th characters (and possibly 5th) being a letter, and everything after the last letter being a number between 1 - 200");
        System.out.println();
        return false;
    }
    //-----------------------------------------------------------------------------------------------------------------------
    
    //Method to create the status.txt file with all validated data
    public static void statusCreator(String[] data, String path){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            for(int k = 0; k < data.length; k+=3){
                //If everything has been validated and nothing is null, method can proceed to write into status.txt. Otherwise, the program will skip the student.
                if(data[0 + k] != null || data[1 + k] != null || data[2 + k] != null){
                    bw.write(data[2 + k] + " - " + data[0 + k] + "\n" + data[1 + k] + "\n");
                }
            }
            System.out.println("Status.txt File has been created succesfully");
            bw.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }
    //------------------------------------------------------------------------------------------------------------------------
}
