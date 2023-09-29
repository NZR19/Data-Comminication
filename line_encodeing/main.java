/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package line_encodeing;

import java.io.IOException;
import java.util.Scanner;
public class main {
    public static void main(String[] args) throws IOException {
        sender s = new sender();
        Reciver r = new Reciver();
        
        //System.out.println("Trasmission complete");
        
        int c,n;
        Scanner sc=new Scanner(System.in);
        System.out.println("1. NZR_L");
        System.out.println("2. NZR_I");
        System.out.println("3. RZ");
        System.out.println("4. Manchester");
        System.out.println("5. diffManchester");
        System.out.println("6. AMI");
        System.out.println("7. Pseudoternary");
        
        
        System.out.print("Enter your choice: ");
        c=sc.nextInt();

        switch(c)
        {
            case 1:
                System.out.println("----------------NRZ_L----------------");
                s.val=0;
                r.val = 0;
                System.out.println("Trasmission complete");
                break;

            case 2:
                System.out.println("----------------NZR_I----------------");
                s.val=1;
                r.val =1;
                System.out.println("Trasmission complete");
                break;

            case 3:
                System.out.println("----------------RZ----------------");
                s.val=2;
                r.val =2;
                System.out.println("Trasmission complete");
                break;
                
            case 4:
                System.out.println("----------------Manchester----------------");
                
                s.val=3;
                r.val =3;
                System.out.println("Trasmission complete");
                break;
                
            case 5:
                System.out.println("----------------diffManchester----------------");
                s.val=4;
                r.val =4;
                System.out.println("Trasmission complete");
                break;
            
            case 6:
                System.out.println("----------------AMI----------------");
                s.val=5;
                r.val =5;
                System.out.println("Trasmission complete");
                break;

            case 7:
                System.out.println("----------------Pseudoternary----------------");
                s.val=6;
                r.val =6;
                System.out.println("Trasmission complete");
                break;
            default:
                System.out.println("Invalid Choice");
        }
        System.out.println(s.count);
        System.out.println(r.count1);
        System.out.println(r.count2);
        double snr=r.count2/r.count1;
        System.out.println(snr);
}
}
