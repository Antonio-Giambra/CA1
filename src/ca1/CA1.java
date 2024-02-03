
package ca1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

//*************************************************************************************************************************************************//
//***************************************** GitHub Repository: https://github.com/Antonio-Giambra/CA1  ********************************************//
//*************************************************************************************************************************************************//
public class CA1 {
    
    //Method for counting the lines inside the txt file provided
    static int numberLines(String path){
        //lines counter
        int numberLine = 0;
        
        try{
        BufferedReader br = new BufferedReader(new FileReader(path));
        
        //Trigger created to break the "While "loop when the line is null or unexisting
        boolean trigger = false;

        //Checking how many lines the file has
        while(trigger == false){
            String line = br.readLine();
            
            //this conditional can help us to read white lines, if the program find any white line then break the loop
            if(line == null || line.equals(" ")){
                trigger = true;
                
            }
            //Incrementing line counter in order to get an exact number of valid lines
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
            
            // Paths for each document.
            String pathStandard = "students.txt";
            String pathManual = "student2.txt";
            String pathStatus = "status.txt";
            //-------------------------
            
            //Trigger to break the while loop
            boolean exit = false;
            Scanner sc = new Scanner(System.in);
            
            //While loop for interactive menu
            while(exit == false){
                System.out.println("Welcome to this system!! \n\n Press 1 for standard operation \n Press 2 for manual operation \n Press 3 to exit");
                
                switch(sc.nextLine()){
                    
                    case "1": //Standard Case
                        //Calling txt creator method with validated information in order to create the status.txt file
                        Validator.statusCreator(Validator.Checker(numberLines(pathStandard),pathStandard), pathStatus);
                        break;
                    case "2":
                        
                        //Guidelines for usability
                        System.out.println();
                        System.out.println("You must introduce the student data as the example below \nLine 1 – <First Name> <Second Name>    :     Antonio Giambra\nLine 2 – <Number of classes>           :     3\nLine 3 – <Student number>              :     22DIP123\n\nWrite 'end' if you have finished.");
                        System.out.println();
                        System.out.println("You can start to type your student list");
                        System.out.println();
                        
                        //Buffered reader opened to start writing in a txt file in order to reuse the case 1 code
                        BufferedWriter bw2 = new BufferedWriter(new FileWriter(pathManual));
                        
                        //trigger to finish the case 2 While loop
                        boolean endTransaction = false;
                        
                        //Gathering data in order to write it on the txt file
                        while(endTransaction == false){
                            String data = sc.nextLine();
                            
                            //User can exit from this process entering end
                            if(data.toLowerCase().equals("end")){
                                //Closing our Buffered Writer
                                bw2.close();
                                //Activating trigger to break the loop
                                endTransaction = true;
                            }else if(sc.hasNext()){
                                //Writing data to file
                                bw2.write(data);
                                //With this conditional we are trying to start in another line and avoid an empty line after pressing "end".
                                if(!data.toLowerCase().equals("end")) bw2.newLine();
                            }
                        }
                        
                        //Calling txt creator method with validated information in order to create the status.txt file
                        Validator.statusCreator(Validator.Checker(numberLines(pathManual),pathManual),pathStatus);
                        break;
                    case "3":
                        //User can exit from the system
                        System.out.println();
                        System.out.println("Thank you for using this system.");
                        System.out.println();
                        sc.close();
                        exit = true;
                        break;
                    default:
                        //User needs to enter a valid number
                        System.out.println();
                        System.out.println("Please enter a valid number");
                        System.out.println();
                        break;
                }
             
            }
            //Closing Scanner
            sc.close();
        }catch(IOException e){
            System.out.println(e);
        }
        
    }
    
}
