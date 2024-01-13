/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.anzek.backend.invoicesystem.repository;


import hu.anzek.backend.invoicesystem.model.Szallito;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;


/**
 * Egy Jupiter.Junit API-teszt, a JUnit 4-ig verziók "stílusában"<br>
 * A szállító bekérését ellenőrizzük egy JUnit teszt során<br>
 * @author User
 */
public class SzallitoBekereseTest {

    /**
     * A "szallito.txt" beolvasását ellenőrző JUnit teszt-osztály<br>
     */
    public SzallitoBekereseTest() {
    }
    
    /**
     * Egy "szokványos" elnevezési formula (valamellyest ez is konvenció)<br>
     * Érvényes adatokkal kell viszatérnie (valami ilyesmi nevet teszünk bele)<br>
     * Fontos: a "@Test" annotációval ellátott metódusoknak nem lehetnek statikusak<br>
     */
    @Test
    public void beolvasTxtadattarbol_ValidFile_ShouldReturnSzallito() {
        // Arrange 
        // - elrendezzük, előkészítjük, stb:
        // jelen esetben példányosítunk:
        SzallitoBekerese szallitoBekerese = new SzallitoBekerese();
        
        // Act 
        // - ez lesz a lefutáskori tény, 
        // vagyíis az aktuális érték, ami bejön:
        Szallito szallito = szallitoBekerese.getSzallitoRepo().beolvasTxtAdattarbol("szallito.txt");
        
        // Assert 
        // - ez az amit elvárunk, ezt várjuk (ezt állítjuk), stb:
        // ha a rekord nem "null" értékű, EZ NEM fog semmit sem jelezni (és az jó dolog...)
        assertNotNull(szallito);
        // kulon a mezőket is megvizsgálhatjuk:
        assertNotNull(szallito.adoszam());
        assertNotNull(szallito.cim());
        assertNotNull(szallito.deviza());
        assertNotNull(szallito.megnevezes());
        
        // vagy megnézhetjük, hogy azonos-e a beolvasott eredmény az elvárttal:
        if(szallito.adoszam() != null)
            assertEquals("12345678-1-01", szallito.adoszam());
        if(szallito.megnevezes() != null)
            assertEquals("BackendJava Kft", szallito.megnevezes());
        if(szallito.megnevezes() !=null)
            assertEquals("1020304 Honijavacity Backend utca 123.", szallito.cim());
        if(szallito.deviza() != null )
            assertEquals("HUF", szallito.deviza());
        
        // vagy azt is megnézhetjük, hogy pont nem azonosak, itt pl az adószám esete:
        if(szallito.adoszam() != null){
            assertNotEquals("12345678-2-01", szallito.adoszam());

            // vagy másként is lehet:
            assertTrue(szallito.adoszam().equals("12345678-1-01"));
            assertFalse(szallito.adoszam().equals("12345678-2-01"));
        }
    }
}
