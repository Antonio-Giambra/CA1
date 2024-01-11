
package ca1;

public class CA1 {
    
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
            System.out.println(e);
        }
        return null;
    }
    
    public static void main(String[] args) {
        System.out.println(workload(1));
        System.out.println(workload(1));
    }
    
}
