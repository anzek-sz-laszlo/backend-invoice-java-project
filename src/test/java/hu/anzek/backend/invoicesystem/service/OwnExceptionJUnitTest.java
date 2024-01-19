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
     * Hiba dobása az OwnConnectionExeption segítségével<br>
     * Léterhozunk egy "sajét" kivételt<br>
     * @throws OwnConnectionExeption saját kivétel kezelő<br>
     */
    void throwException() throws OwnConnectionExeption {
    
        OwnExceptionClass innerException = new OwnExceptionClass("A kivetel atdobva a kulso, 'OwnExceptionClass' osztalynak,\n    ezzel letrehozva a Kulso kivetelt!");
        // Hibagenerálás,
        // egy kivételt lehet dobni az alábbi módon:
        throw new OwnConnectionExeption("A kivetel, ami 'nem allitja majd le a futast', letrehozasa!", innerException);
    }

    @Test
    public void ownExceptionTest(){
        // Kivétel elkapása:
        // A kivétel elkapására a try-catch blokkot használjuk. 
        // A "try-blokk"-ban lévő kód dobhat kivételt, és a megfelelő catch blokk próbálja majd elkapni és kezelni azt!
        try {
            
            // Példa egy kivétel (hiba) létrehozására és tovább dobására:
            throwException();            
        } catch (OwnConnectionExeption e) {
            
            // A saját kivétel elkapása és feldolgozása:
            System.out.println("A Catch agon elkaptuk (caught)\n    az 'OwnConnectionExeption' osztaly altal dobott hibauzenetet: \"" + e.getMessage() +"\"");

            // Az "OwnExceptionClass" példány elérése, amelyet a saját kivétel tartalmaz:
            OwnExceptionClass innerException = e.getConnectionException();

            if (innerException != null) {
                System.out.println("Belso kivetel: " + innerException.getMessage());
            }
        
        } catch (Exception e) {
            
            // Egyéb kivételek elkapása            
            System.out.println("Elkapott barmely mas, de altalanos kivetel: " + e.getMessage());
        } finally {
            
            // A finally blokk mindig lefut, függetlenül attól, hogy keletkezett-e kivétel vagy sem...
            System.out.println("Finally blokk is lefutott.");
        }
    }
}

