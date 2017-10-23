package inlamning202;

import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.util.Scanner;
import java.io.*;


public class Login {
    
    public void inloggning(){
        String ln1;
	String ln2;
        String besokare;                                                // Deklarerar mina variabler.
        String[] medlemsLista; 
        Boolean arMedlem = false;
        
        LocalDate dagensDatum = LocalDate.now();                        // Deklarerar och initierar en variabel innehållandes dagens datum.
	LocalDate oneYearAgo = dagensDatum.minusYears(1).minusDays(1);  // Deklarerar och initierar en variabel innehållandes datumet för ett år sedan.
        
	besokare = JOptionPane.showInputDialog("Ange ditt fullständiga namn eller personnummer.");
	        
        try {
            Scanner sc = new Scanner(new FileReader ("Customers.txt")); // Skapar upp en scanner som läser från filen Customers.txt
            
            while(sc.hasNext()){                                        // Sålänge det finns en ny token att läsa in kommer denna sats pågå.
            	ln1 = sc.nextLine();                                    // Scannern läser in första raden i Customers.txt och tilldelar den till ln1.
            	medlemsLista = ln1.split(",");                          // Delar upp raden till två tokens och lägger dessa i min medlemsLista.
            	
            	if(sc.hasNext()) {                                      
                    ln2 = sc.nextLine();                                // Scannern läser in nästa rad. Stringen tilldelas ln2.
            	
                    if(medlemsLista[0].equals(besokare) || medlemsLista[1].trim().equals(besokare)) {   // Om nummret i första elementet är detsamma som personnumret vi matat in eller om namnet i det andra elementet är detsamma som namnet vi matat in skall denna sats utföras.
                        
                    	LocalDate senastBetalat = LocalDate.parse(ln2);                 // Parsar datumet som är inscannat i ln2 till variabel senastBetalt.
            		
            		if(senastBetalat.isAfter(oneYearAgo)) {                         // Om det var mindre än ett år sen besökaren betalade utförs denna sats.
                            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Senasttranat.txt", true))); // Skapar objektet out av typen printwriter inhållandes direktioner för hur datan ska strömma till textfilen.
            		    out.println(medlemsLista[0] + "," + medlemsLista[1]);       // Skriver innehållet i element ett och två till Senasttranat.txt
            		    out.println(dagensDatum);                                   // Skriver dagens datum på nästa rad i Senasttranat.txt
                            out.close();                                                // Stänger textfilen vi skrivit till.
                            
                            arMedlem = true;                                            // Tilldelar variabeln arMedlem med värdet true.                            
                            JOptionPane.showMessageDialog(null, "Välkommen in!");       // Skapar en dialogruta med texten "Välkommen in".
           		}
                        else{                                                           // Om senastBetalat är längre sen än för ett år sen kommer denna sats exekveras.
                            JOptionPane.showMessageDialog(null, "Du behöver förnya ditt medlemskap.");  // Skapar en dialogruta med texten "Du behöver förnya ditt medlemskap".
                            arMedlem = true;                                            // Tilldelar variabeln arMedlem med värdet true.
                        }
                    }
            	}
            }
        }
        catch (Exception e){                                                            // Om programmet inte kan läsa filen kommer denna sats exekveras.
            JOptionPane.showMessageDialog(null, "Ett fel uppstod.");                    // Skapar en dialogruta med texten "Ett fel uppstod".
        }
    if(arMedlem == false)                                                               // Om programmet tidigare inte hittat besökaren i Customers.txt så kommer arMedelem aldrig ändras till true och därför körs denna sast.
        JOptionPane.showMessageDialog(null, "Du finns inte med i medlemsregister");     // Skapar en dialogruta med texten "Du finns inte med i vårt medlemsregister".
    }
}
