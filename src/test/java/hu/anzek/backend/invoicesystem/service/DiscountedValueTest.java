/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package hu.anzek.backend.invoicesystem.service;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.TestInstance;


/**
 * Engedményezést tesztelő osztály<br>
 * Osztály szintű életciklusa lesz - egyszer példányosít-<br>
 * @author User
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DiscountedValueTest {
    
    DiscountedValue discountedValue;
    
    public DiscountedValueTest() {
    }
    
    /**
     * Megvalósítjuk a discount számítás metódus testét<br>
     * Felhasználjuk benne a "default" metódust.<br>
     */
    @BeforeAll
    public void setUpClass() {        
        discountedValue = (alap,engedmeny) -> {                 
            // System.out.println("DiscountedValueTest.setUpClass0(" + alap + ", " + (engedmeny / 100) + ")");
            // System.out.println("DiscountedValueTest.setUpClass1(" + alap + ", " + ((double) engedmeny / 100) + ")");
            // System.out.println("DiscountedValueTest.setUpClass2(" + db +")");
            return discountedValue.rndTwo( alap * (1 -((double) engedmeny / 100)) );
        };
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
     * Egyszerű egészszámos teszt<br>
     */
    @Test
    public void discountTest() {
        assertEquals(discountedValue.engedmenyezo(1000, 10), 900);
    }

    @Test
    public void fractionaltest(){
       assertEquals(discountedValue.engedmenyezo(1891, 13), 1645.17);
    }
}
