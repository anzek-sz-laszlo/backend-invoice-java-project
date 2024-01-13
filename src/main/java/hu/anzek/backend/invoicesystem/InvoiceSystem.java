/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package hu.anzek.backend.invoicesystem;


/**
 * Szovegfajlos szamlazo rendszer
 * @author User
 */
public class InvoiceSystem {

    /**
     * CSV mintafájl:
     */
    // szamla.csv
    // szamlaszam,szallito,vevo,atadasi_ido,fizhatido,fizmod,engedmeny,alap,afa,osszesen
    // 1,"s1","v1",2023-12-05,15,1,5,100,27,127
    // 2,"s1","v1",2023-12-05,15,1,5,100,27,127
    // 3,"s1","v1",2023-12-05,15,1,5,100,27,127
   
    public static void main(String[] args) {
        
        System.setProperty("file.encoding", "UTF-8");
        System.out.println("Hello World!");
    }
}
