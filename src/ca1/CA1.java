
package ca1;

import java.io.BufferedReader;
import java.io.FileReader;

public class CA1 {
    public static void main(String[] args){
        try{
            BufferedReader br = new BufferedReader(new FileReader("students.txt"));
            
            //----------READING ALL INFORMATION FROM STUDENTS.TXT-------------//
            //Trigger created to break the loop which is getting all information
            boolean trigger = false;
            int numberLine = 0;
            
            //Checking how many lines has the file
            while(trigger == false){
                String hola = br.readLine();
                if(hola != null){
                    numberLine++;
                }else {
                    trigger = true;
                }
            }
            
            //Calling new txt creator method with validated information
            Validator.statusCreator(Validator.Checker(numberLine));
            
        }catch(Exception e){
            System.out.println(e);
        }
        
    }
    
}
