
package ca1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CA1 {
    
    static int numberLines(String path) throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new FileReader(path));
        //Trigger created to break the "While "loop when the line is null or unexisting
                        boolean trigger = false;

                        //Checking how many lines the file has
                        int numberLine = 0;
                        while(trigger == false){
                            String line = br.readLine();
                            if(line != null){
                                numberLine++;
                            }else {
                                trigger = true;
                            }
                        }
                        return numberLine;
    }
    
    public static void main(String[] args){
        
        try{
            String path;
            //----------READING ALL INFORMATION FROM STUDENTS.TXT-------------//
            boolean exit = false;
            while(exit == false){
                Scanner sc = new Scanner(System.in);
                System.out.println("Welcome to this system \n Press 1 for standard operation \n Press 2 for manual operation \n Press 3 to exit");
                System.out.println();
                
                int menu = sc.nextInt();
                switch(menu){
                    
                    case 1:
                        //Standard Case
                        path = "student.txt";
                        //Calling txt creator method with validated information in order to create the status.txt file
                        Validator.statusCreator(Validator.Checker(numberLines(path),path));
                        break;
                    case 2:
                        path = "student2.txt";
                        System.out.println("You must introduce the student data as the example below \nLine 1 – <First Name> <Second Name>\nLine 2 – <Number of classes>\nLine 3 – <Student number>\nYou can start to type your students");
                        System.out.println();
                        Scanner sr = new Scanner(System.in);
                        BufferedWriter bw2 = new BufferedWriter(new FileWriter("student2.txt"));
                        boolean endTransaction = false;
                        while(endTransaction == false){
                            String data = sr.nextLine();
                            String ending = data.toLowerCase();
                            if(ending.equals("end")){
                                endTransaction = true;
                                sr.close();
                                bw2.close();
                            }else{
                                bw2.write(data);
                                bw2.newLine();
                            }
                            System.out.println("Write 'end' if you have finished. If not you can continue typing");
                        }
                        Validator.statusCreator(Validator.Checker(numberLines(path),path));
                        break;
                    case 3:
                        System.out.println("Thank you for using this system.");
                        exit = true;
                        break;
                    default:
                        System.out.println("Please press a valid number");
                        break;
                }
            }
        }catch(Exception e){
            System.out.println("Your document has an empty line. Please fix it to continue");
        }
        
    }
    
}
