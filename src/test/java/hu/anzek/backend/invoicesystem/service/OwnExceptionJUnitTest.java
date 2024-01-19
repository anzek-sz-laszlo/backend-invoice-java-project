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


/**
 *
 * @author User
 */
public class OwnExceptionJUnitTest {
    

    public OwnExceptionJUnitTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
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
     * Léterhozunk egy "sajét" kivételt<br>
     * @throws OwnConnectionExeption saját kivétel kezelő<br>
     */
    void throwException() throws OwnConnectionExeption {
        // Példa: Hiba dobása az OwnConnectionExeption segítségével
        OwnExceptionClass innerException = new OwnExceptionClass("Atdobva a 'OwnExceptionClass' osztalynak, ezzel letrehozva a Kulso kivetelt!");
        throw new OwnConnectionExeption("Belso kivetel letrehozasa", innerException);
    }

    @Test
    public void ownExceptionTest(){
        try {
            
            // Példa egy kivétel (hiba) létrehozására és tovább dobására:
            throwException();            
        } catch (OwnConnectionExeption e) {
            
            // A saját kivétel elkapása és feldolgozása:
            System.out.println("Elkapott(caught) 'OwnConnectionExeption' osztaly hibauzenete: " + e.getMessage());

            // Az "OwnExceptionClass" példány elérése, amelyet a saját kivétel tartalmaz:
            OwnExceptionClass innerException = e.getConnectionException();

            if (innerException != null) {
                System.out.println("Belso kivetel: " + innerException.getMessage());
            }
        
        } catch (Exception e) {
            // Egyéb kivételek elkapása
            System.out.println("Elkapott altalanos kivetel: " + e.getMessage());
        }
    }
}

