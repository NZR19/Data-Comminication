/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package line_encodeing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Admin
 */
public class sender {
    String inputString;
    String outputString;
    int count=0;
    FileWriter fw,fw1;
    FileReader fr,fr1;
    int val;
   
    
    
    sender() throws FileNotFoundException, IOException{
        fr = new FileReader("D:\\Semester_10\\Java\\Line_Encodeing\\src\\line_encodeing\\labin.txt");
        fw = new FileWriter("D:\\Semester_10\\Java\\Line_Encodeing\\src\\line_encodeing\\labtemp.txt");
        fw1 = new FileWriter("D:\\Semester_10\\Java\\Line_Encodeing\\src\\line_encodeing\\labsignal.txt");
        
        BufferedReader br = new BufferedReader(fr);
        int i = 0;
        inputString = "";
        while(true){
            int x = br.read();
            i++;
            if(x == -1){
                applicationLayer(inputString);
                break;
            }
            char ch = (char) x;
            inputString = inputString + Character.toString(ch);
            
            if(i == 125)
            {
                applicationLayer(inputString);
                i = 0;
                inputString = "";
            }
        }
        
        fw.close();
        
    }
    
    void NZR_L(String s) throws IOException{
        
        outputString = "";
           String sr="";
        for(int i = 0 ; i < s.length();i++){
            char c = s.charAt(i);
            if(c=='0')
            {
                sr = sr+'-';
                
            }
            else
                sr=sr+'+';
        }
            
            outputString = outputString + sr;
            
        
        fw1.write(outputString);       
        
    }
    
    void RZ(String s) throws IOException{
        
        outputString = "";
           String sr="";
        for(int i = 0 ; i < s.length();i++){
            char c = s.charAt(i);
            if(c=='1')
            {
                sr = sr+"+~";
                
            }
            else
                sr=sr+"-~";
        }
            
            outputString = outputString + sr;
            
        
        fw1.write(outputString);  
        
    }
    
    void Manchester(String s) throws IOException{
        
        outputString = "";
           String sr="";
        for(int i = 0 ; i < s.length();i=i+1){
            char c = s.charAt(i);
            if(c=='1')
            {
                sr = sr+"-+";
                
            }
            else
                sr=sr+"+-";
        }
            
            outputString = outputString + sr;
            
        
        fw1.write(outputString);  
        
    }
    
    void NRZ_I(String s) throws IOException{
        outputString = "";
           String sr="";
           char state='+';
           char antistate='-';
           char temp;
        for(int i = 0 ; i < s.length();i=i+1){
            char c = s.charAt(i);
            if(c=='0')
            {
                sr = sr+state;  
            }
            else{
                sr = sr + antistate;
                temp = state;
                state = antistate;
                antistate = temp;
            }
            
        }
            
            outputString = outputString + sr;
            
        
        fw1.write(outputString);  
    }
    
        void diffManchester(String s) throws IOException{
        outputString = "";
           String sr="";
           String state="+-";
           String antistate="-+";
           String temp;
        for(int i = 0 ; i < s.length();i=i+1){
            char c = s.charAt(i);
            if(c=='0')
            {
                sr = sr + antistate;
                temp = state;
                state = antistate;
                antistate = temp;
            }
            else{
                sr = sr+state;
                
            }
            
        }
            
            outputString = outputString + sr;
            
        
        fw1.write(outputString);  
    }
     void AMI(String s) throws IOException{
        outputString = "";
           String sr="";
           char state='+';
           char antistate='-';
           char temp;
        for(int i = 0 ; i < s.length();i=i+1){
            char c = s.charAt(i);
            if(c=='0')
            {
                sr = sr+'*';  
            }
            else{
                sr = sr + state;
                temp = state;
                state = antistate;
                antistate = temp;
            }
            
        }
            
            outputString = outputString + sr;
            
        
        fw1.write(outputString);  
    }
        
     void PSU(String s) throws IOException{
        outputString = "";
           String sr="";
           char state='+';
           char antistate='-';
           char temp;
        for(int i = 0 ; i < s.length();i=i+1){
            char c = s.charAt(i);
            if(c=='1')
            {
                sr = sr+'*';  
            }
            else{
                sr = sr + state;
                temp = state;
                state = antistate;
                antistate = temp;
            }
            
        }
            
            outputString = outputString + sr;
            
        
        fw1.write(outputString);  
    }
    
    void applicationLayer(String s) throws IOException{
        String mod_s = "A-H" + s;
        presentationLayer(mod_s);
    }
    
    void presentationLayer(String s) throws IOException{
        String mod_s = "P-H" + s;
        sessionLayer(mod_s);
    }
    
    void sessionLayer(String s) throws IOException{
        String mod_s = "S-H" + s;
        transportLayer(mod_s);
    }
    
    void transportLayer(String s) throws IOException{
        String mod_s = "T-H" + s;
        networkLayer(mod_s);
    }
    
    void networkLayer(String s) throws IOException{
        String mod_s = "N-H" + s;
        dataLinkLayer(mod_s);
    }
    
    void dataLinkLayer(String s) throws IOException{
        String mod_s = "D-H" + s + "D-T";
        physicalLayer(mod_s);
    }
    
    void physicalLayer(String s) throws IOException{
        
        String mod_s = "PH-H" + s;
        outputString = "";
        for(int i = 0 ; i < mod_s.length();i++){
            char c = mod_s.charAt(i);
            String sr = Integer.toBinaryString(c);
            int sr_len = sr.length();
            if(sr_len != 8)
            {
                for(int j = 0 ; j< 8 - sr_len ;j++){
                sr = "0" + sr;
                }
            }
            
            outputString = outputString + sr;
            
        }
                
        
        fw.write(outputString);
        //nrz habijabi function
        switch (val) {
            case 0:
                NZR_L(outputString);
                break;
            case 1:
                NRZ_I(outputString);
                break;
            case 2:
                RZ(outputString);
                break;
            case 3:
                Manchester(outputString);
                break;
            case 4:
                diffManchester(outputString);
                break;
            case 5:
                AMI(outputString);
                break;
            case 6:
                PSU(outputString);
                System.out.print(outputString);
                break;
            
            default:
                System.out.print("Invalid Choice");
                break;
        }
        
    }
}
