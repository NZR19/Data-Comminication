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
import java.util.Random;


public class Reciver {
     String inputString;
    String outputString;
    FileWriter fw,fw1;
    FileReader fr,fr1;
    int count1=0;
    int count2=0;
    int val;
    
    Reciver() throws FileNotFoundException, IOException{
        
        fr = new FileReader("D:\\Semester_10\\Java\\Line_Encodeing\\src\\line_encodeing\\labsignal.txt");
        fw = new FileWriter("D:\\Semester_10\\Java\\Line_Encodeing\\src\\line_encodeing\\labout.txt");
        BufferedReader br = new BufferedReader(fr);
        int i = 0;
        inputString = "";
        while(true){
            int x = br.read();
            i++;
            if(x == -1){
                switch (val) {
                    case 0:
                        NRZ_L(inputString);
                        break;
                    case 1:
                        NRZ_I(inputString);
                        break;
                    case 2:
                        RZ(inputString);
                        break;
                    case 3:
                        Manchester(inputString);
                        break;
                    case 4:
                        diffManchester(inputString);
                        break;
                    case 5:
                        AMI(inputString);
                        break;
                    case 6:
                        PSU(inputString);
                        break;
                    default:
                        System.out.print("Invalid Choice");
                        break;
                }
                break;
            }
            char ch = (char) x;
            inputString = inputString + Character.toString(ch);
            if(i == 1200)
            {
                switch (val) {
                    case 0:
                        NRZ_L(inputString);
                        i = 0;
                        inputString = "";
                        break;
                    case 1:
                        NRZ_I(inputString);
                        i = 0;
                        inputString = "";
                        break;
                    case 2:
                        RZ(inputString);
                        i = 0;
                        inputString = "";
                        break;
                    case 3:
                        Manchester(inputString);
                        i = 0;
                        inputString = "";
                        break;
                    case 4:
                        diffManchester(inputString);
                        i = 0;
                        inputString = "";
                        break;
                    case 5:
                        AMI(inputString);
                        i = 0;
                        inputString = "";
                        break;
                    case 6:
                        PSU(inputString);
                        i = 0;
                        inputString = "";
                        break;
                    default:
                        System.out.print("Invalid Choice");
                        break;
                }

            }
        }
        fw.close();
        
    }
    
        
    
    void NRZ_L(String s) throws IOException{
        
        outputString = "";
           String sr="";
        for(int i = 0 ; i < s.length();i++){
            char c = s.charAt(i);
            if(c=='-')
            {
                sr = sr+'0';
                
            }
            else
                sr=sr+'1';
        }
            
            outputString = outputString + sr;
            
        
        physicalLayer(outputString);      
        
    }
    
    void Manchester(String s) throws IOException{
        outputString = "";
           String sr="";
        for(int i = 0 ; i <s.length();i=i+2){
            char c = s.charAt(i);
            char c1=s.charAt(i+1);
                       
           // char c = s.charAt(i);
            if(c =='-'&&c1=='+')
            {
                sr = sr+"1";
                
            }
            else
                sr=sr+"0";
           
        }
            
            outputString = outputString + sr;
            
        
        physicalLayer(outputString);  
        
    }
    
    void RZ(String s) throws IOException{
        
       outputString = "";
           String sr="";
        for(int i = 0 ; i <s.length();i=i+2){
            char c = s.charAt(i);
            char c1=s.charAt(i+1);
                       
            if(c =='-'&& c1=='~')
            {
                sr = sr+"0";    
                
            }
            
            else 
                sr=sr+"1";
    
           
        }
            
            outputString = outputString + sr;
            
        
        physicalLayer(outputString); 
        
    }
    
     void NRZ_I(String s) throws IOException{
        
       outputString = "";
           String sr="";
           char state='+';
           char antistate='-';
           char temp;
        for(int i = 0 ; i <s.length();i=i+1){
            char c = s.charAt(i);
                       
            if(c ==state)
            {
                sr = sr+'0';    
                
            }
            
            else {
                sr = sr + '1';
                temp = state;
                state = antistate;
                antistate = temp;
            }
    
           
        }
            
            outputString = outputString + sr;
            physicalLayer(outputString);  
        
    }
     
          void diffManchester(String s) throws IOException{
        
       outputString = "";
           String sr="";
           String state="+-";
           String antistate="-+";
           String temp;
        for(int i = 0 ; i <s.length();i=i+2){
            
            String x = s.substring(i,i+2);
                       
            if(x.equals(state))
            {
                sr = sr+"1"; 
            }
            
            else {
                sr=sr+"0";
                temp=state;
            state=antistate;
            antistate=temp;
                
            
            }
    
           
        }
            
            outputString = outputString + sr;
            physicalLayer(outputString); 
        
    }
          
          void AMI(String s) throws IOException{
        
       outputString = "";
           String sr="";
        for(int i = 0 ; i <s.length();i=i+1){
            char c = s.charAt(i);
                       
            if(c =='*')
            {
                sr = sr+'0';    
                
            }
            
            else {
                sr = sr + '1';
            }
    
           
        }
            
            outputString = outputString + sr;
            
        
        physicalLayer(outputString);  
        
    }
          
          
     void PSU(String s) throws IOException{
        
       outputString = "";
           String sr="";
        for(int i = 0 ; i <s.length();i=i+1){
            char c = s.charAt(i);
                       
            if(c =='*')
            {
                sr = sr+'1';    
                
            }
            
            else {
                sr = sr + '0';
            }
    
           
        }
            
            outputString = outputString + sr;
            
        
        physicalLayer(outputString);  
        
    }
    
     String error_string(){
        String s="";
        for(int i=0;i<150;i++){
            s=s+"#";
            
        }
        return s;
    }
    
    void physicalLayer(String s) throws IOException{
        outputString = "";
        
        Random rand = new Random();
      int int_random = rand.nextInt(100);
        if(int_random<20){
            
            outputString=error_string();
            count1++;
            outputString = outputString.substring(4);
        datalinkLayer(outputString);
            
        }
        else{
            int i;
        for(i = 0 ; i < s.length();i+=8)
        {
            String temp = s.substring(i, i+8);
            int x = Integer.parseInt(temp, 2);
            char ch = (char) x;
            outputString = outputString + Character.toString(ch);
        }
        outputString = outputString.substring(4);
        datalinkLayer(outputString);
        count2++;
    }
        }
    
    void datalinkLayer(String s) throws IOException{
        outputString = s.substring(3,s.length()-3);
        networkLayer(outputString);
        
    }
    
    void networkLayer(String s) throws IOException{
        outputString = s.substring(3);
        transportLayer(outputString);
    }
    
    void transportLayer(String s) throws IOException{
        outputString = s.substring(3);
        sessionLayer(outputString);
    }
    
    
    void sessionLayer(String s) throws IOException{
        outputString = s.substring(3);
        presentationLayer(outputString);
    }
    
    void presentationLayer(String s) throws IOException{
        outputString = s.substring(3);
        applicationLayer(outputString);
    }
    
    void applicationLayer(String s) throws IOException{
        outputString = s.substring(3);
        fw.write(outputString);
    }
    
}


