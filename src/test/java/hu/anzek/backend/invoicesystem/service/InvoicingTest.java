/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package hu.anzek.backend.invoicesystem.service;


import java.io.IOException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.TestInstance;


/**
 * A számlakészítő osztályunk metódusainak teszteléséhez<br>
 * @author User
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InvoicingTest {
    
    private Invoicing invoicing;
    
    /**
     * Konstruktor a számlakészítő osztályunk metódusainak teszteléséhez<br>
     */
    public InvoicingTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    /**
     * Az each minden teszt eset előtt az invoicing példányt inicializálja!<br> 
     * így minden teszteset egy tiszta példánnyal dolgozik.<br>
     * Egy adafolyam beolvaása és annak eredménye kiértékelés tesztelése előtt célszerű ezzel inicializálni<br>
     * @throws java.io.IOException olvasási hiba<br>
     * @throws java.lang.IllegalAccessException nem érvémyes hozzáférés<br>     
     */    
    @BeforeEach
    public void setUp() throws IOException, IllegalArgumentException, IllegalAccessException {
        invoicing = new Invoicing();
        invoicing.readSzamlakeszulActualConfigStatus();
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void readSzamlakeszulActualConfigStatus_in_readed_list_Exists_and_is_Not_NULL(){
        assertNotNull(invoicing.getSzamlakArfolyama());
        assertNotNull(invoicing.getSzamlakListaja());
    }
    
    @Test
    public void testThird_invoiceKod_is_equels_Four() {
        // 0,1,2 - tehát a harmadik számla vevőkódját keressük:
        assertEquals(4, invoicing.getSzamlakListaja().get(2).getMain().getKod());
    }    
}
