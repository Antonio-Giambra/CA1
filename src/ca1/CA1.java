
package ca1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CA1 {
    
    static int numberLines(String path){
        int numberLine = 0;
        try{
        BufferedReader br = new BufferedReader(new FileReader(path));
        //Trigger created to break the "While "loop when the line is null or unexisting
        boolean trigger = false;

                        //Checking how many lines the file has
        while(trigger == false){
            String line = br.readLine();
            if(line == null || line.equals(" ")){
                trigger = true;
                
            }
            if(trigger == false) numberLine = numberLine + 1;
       }
       }catch(Exception e){
           System.out.println("el programa detecto un espacio en blanco");
       }
       return numberLine;
    }
    
    public static void main(String[] args){
        
        try{
            //----------READING ALL INFORMATION FROM STUDENTS.TXT-------------//
            String pathStandard = "students.txt";
            String pathManual = "student2.txt";
            String pathStatus = "status.txt";
            
            boolean exit = false;
            Scanner sc = new Scanner(System.in);
            while(exit == false){
                System.out.println("Welcome to this system!! \n\n Press 1 for standard operation \n Press 2 for manual operation \n Press 3 to exit");
                
                if(sc.hasNextLine()){
                switch(Integer.parseInt(sc.nextLine())){
                    
                    case 1: //Standard Case
                        //Calling txt creator method with validated information in order to create the status.txt file
                        
                        Validator.statusCreator(Validator.Checker(numberLines(pathStandard),pathStandard), pathStatus);
                        break;
                    case 2:
                        System.out.println("You must introduce the student data as the example below \nLine 1 – <First Name> <Second Name>\nLine 2 – <Number of classes>\nLine 3 – <Student number>\nYou can start to type your students");
                        System.out.println();
                        System.out.println("Write 'end' if you have finished. If not you can continue typing");
                        System.out.println();
                        BufferedWriter bw2 = new BufferedWriter(new FileWriter(pathManual));
                        boolean endTransaction = false;
                        while(endTransaction == false){
                            String data = sc.nextLine();
                            String ending = data.toLowerCase();
                            
                            if(ending.equals("end")){
                                sc.close();
                                bw2.close();
                                endTransaction = true;
                            }else if(sc.hasNext()){
                                bw2.write(data);
                                if(!ending.equals("end")) bw2.newLine();
                            }
                        }
                        Validator.statusCreator(Validator.Checker(numberLines(pathManual),pathManual),pathStatus);
                        break;
                    case 3:
                        System.out.println();
                        System.out.println("Thank you for using this system.");
                        sc.close();
                        exit = true;
                        break;
                    default:
                        System.out.println("Please enter a valid number");
                        break;
                }
             }
            }
        }catch(IOException e){
            System.out.println(e);
        }
        
    }
    
}
