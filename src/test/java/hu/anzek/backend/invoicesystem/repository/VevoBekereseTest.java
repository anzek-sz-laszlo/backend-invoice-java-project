/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package hu.anzek.backend.invoicesystem.repository;


import hu.anzek.backend.invoicesystem.model.Vevo;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;


/**
 * Ez a Jupiter.Junit API a JUnit 5 verzió<br>
 * @author User
 */
@TestInstance(Lifecycle.PER_CLASS)
public class VevoBekereseTest {
    
    // tagváltozó
    private VevoBekerese vevoBekerese;
    
    /**
     * A "vevo.txt" beolvasását ellenőrző JUnit teszt-osztály<br>
     */
    public VevoBekereseTest() {
    }
    
    /**
     * Kivettük előle a "static" kulcsszót, így osztálypéldány szintű <br>
     * (a Java 8 előtti időszakban ezek tényleg csak statikus metódusok lehettek)<br>
     * előkészítő metódus lett!<br>
     */
    @BeforeAll
    public void setUpClass() {        
        vevoBekerese = new VevoBekerese();
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
     * vevoRepo_ShouldNotBeNull Junit 5: <br>
     * Ellenőrzzük vele, hogy a getVevoRepo metódus ne adjon vissza a null értéket!<br>
     */    
    @Test
    public void vevoRepoShouldNotBeNull() {        
        System.out.println("BeolvasTest.Vevo-Lista.Size() = " + vevoBekerese.getVevo().size());
        assertNotNull(vevoBekerese.getVevoRepo());
    }

    @Test
    public void vevoRecordsShouldNotBeEmpty() {
       assertFalse(vevoBekerese.vevo().isEmpty());
    }
    
    /**
     * vevoRepo_ShouldNotBeNull: <br>
     * Ellenőrzzük vele, hogy a "getVevoRepo" metódus ne adjon vissza a null értéket!<br>
     * Megjegyzés<br>
     * - mert neki ugye a metódus implementációt kell szolgáltatnia!<br>
     * - vagyis a "vevoRepo" referencia itt használható lenne magának a vevő-listának a kinyerésre így:<br>
     * - - vevoRepo.beolvasTxtAdattarbol(fajlEleres)<br>
     */
    @Test
    public void vevoRepo_ShouldNotBeNull() {
       VevoCikkRepo<Vevo> vevoRepo = vevoBekerese.getVevoRepo();
       //assertNotNull(vevoRepo);
    }

    /**
     * vevo_ShouldNotBeNull:<br> 
     * Ellenőrzzük vele, hogy a vevo metódus ne adja vissza a null értéket.<br>
     */
    @Test
    public void vevo_ShouldNotBeNull() {
      List<Vevo> vevoList = vevoBekerese.vevo();
      assertNotNull(vevoList);
    }

    /**
     * vevo_ShouldNotBeEmpty:<br> 
     * Ellenőrzzük vele, hogy a vevo metódus által visszaadott lista ne legyen üres.<br>
     */
    @Test
    public void vevo_ShouldNotBeEmpty() {
        List<Vevo> vevoList = vevoBekerese.vevo();
        assertFalse(vevoList.isEmpty());
    }
}
