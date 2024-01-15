
package ca1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class CA1 {
    
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
            System.out.println("nombre nop funciono");
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
    
    public static boolean statusCreator(String[] data){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("status.txt"));
            bw.write(data[2] + " - " + data[0] + "\n" + data[1]);
            bw.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    public static void main(String[] args){
        try{
            BufferedReader br = new BufferedReader(new FileReader("students.txt"));
            
            //----------READING ALL INFORMATION FROM STUDENTS.TXT-------------//
            //Trigger created to break the loop which is getting all information
            boolean trigger = false;
            String reading;
            String[] studentPersonalData = new String[3];
            
            //Loop to read all information from students.txt
            while(trigger == false){
                
                //Filling final document in generalStudentsData Array with every studentPersonalData array in order to send them to a method which will be sending all information to status.txt
                
                    
                    //Filling personal information in studentPersonalData Array from students.txt
                    for(int j = 0; j < studentPersonalData.length; j++){
                        reading = br.readLine();
                        if((reading != null)){
                            switch(j){
                                case 0:
                                    String secondName = gettingSecondName(reading);
                                    studentPersonalData[0] = secondName;
                                    break;
                                case 1:
                                    int test = Integer.parseInt(reading);
                                    String number = workload(test);
                                    studentPersonalData[1] = number;
                                    break;
                                case 2:
                                    boolean checker = studentNumberChecker(reading);
                                    if(checker == true){
                                        studentPersonalData[2] = reading;
                                    }else break;
                                default:
                                    break;
                            }

                        }
                        
                        else br.close();trigger = true;
                    }
                    //----------------------------------------------------------------
                    
            }
            statusCreator(studentPersonalData);
        }catch(Exception e){
            System.out.println("main no funciono");
        }
        
    }
    
}
