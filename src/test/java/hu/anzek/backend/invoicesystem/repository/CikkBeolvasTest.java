/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package hu.anzek.backend.invoicesystem.repository;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;


/**
 * A "cikk.txt" beolvasását ellenőrző JUnit teszt-osztály<br>
 * 
 * Figyelem!<br>
 * A "@TestInstance" annotáció a JUnit 5 életcikluskezelésére vonatkozik.<br> 
 * Alapvetően két életciklusstratégiát támogat:<br>
 * Lifecycle.PER_METHOD: Ez az alapértelmezett stratégia, ahol minden tesztfuttatás egy külön példányt használ a tesztosztályból.<br>
 * Lifecycle.PER_CLASS: Ebben a stratégiában az egész tesztosztály egyetlen példányát használjuk a tesztek futtatásához.<br> 
 * Ez azt jelenti, hogy az összes tesztmetódusunk (tesztugrópont azaz a "test case") ugyanazt az osztálypéldányt használja.<br>
 * @author User
 */
@TestInstance(Lifecycle.PER_CLASS)
public class CikkBeolvasTest {
    
    // tagváltozó
    private CikkBeolvas cikkBeolvas;
    
    /**
     * A "cikk.txt" beolvasását ellenőrző JUnit teszt-osztály konstruktora<br>
     */
    public CikkBeolvasTest() {
    }
    
    /**
     * Alapvetően az előkészített metódusok statikusak, 
     * de a JAVA 10-től van mód osztályszintűvé tenni ezeket (úgy, hogy töröljük előlük a ststic kulcsszót)!<br>
     */
    @BeforeAll
    public void setUpClass() {       
        cikkBeolvas = new CikkBeolvas();        
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    /**
     * cikkRepo_ShouldNotBeNull Junit 5 Test: <br>
     * Fontos: hogy bár az előkészítő metódusok igen, a @Test annotációval ellátott metódusoknak nem lehetnek static-ok<br>
     * Ellenőrzzük le, hogy a getCikkList() metódus nem ad-e vissza a null értéket?!<br>
     */    
    @Test
    public void cikkRepoShouldNotBeNull() {
        // a végleges állapotában ezt is el kell távolítanunk:
        System.out.println("CikkBeolvasTest.Lista.Size() = " + cikkBeolvas.getCikkList().size());
        // teszt:
        assertNotNull(cikkBeolvas.getCikkList());
        assertEquals(cikkBeolvas.getCikkList().size(), 5);
    }
    
    /** 
     * Ellenőrzzük le, hogy a getCikkList() által visszaadott listában szereplő harmadik tétel<br> 
     * kódszáma a kettes szám!<br>
     * Figyeljünk arra, hogy amíg a "list.size()" az elemek db-számát muatatja (ami 1..n db tömbelemet jelent),<br>
     * addig a tömb indexek mindig [0..n-1] -ig tartanak!<br>
     */     
    @Test
    public void cikkTheThirdItemOnListIsCodeNumberTwo() {
        assertEquals(cikkBeolvas.getCikkList().get(2).kod(), 2L);
    }    
}
