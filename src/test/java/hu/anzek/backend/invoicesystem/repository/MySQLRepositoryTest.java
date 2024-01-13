/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package hu.anzek.backend.invoicesystem.repository;


import hu.anzek.backend.invoicesystem.model.Cikk;
import hu.anzek.backend.invoicesystem.model.Vevo;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
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
public class MySQLRepositoryTest {
    
    // tagváltozók
    private CikkBeolvas cikkBeolvas;
    private JdbcCikkReadRepo jdbcCikkOlvaso;
    private VevoBekerese vevoBekerese;
    private VevoCikkRepo<Cikk> cikkRepo;
    private VevoCikkRepo<Vevo> vevoRepo;
    
    public MySQLRepositoryTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        
        cikkBeolvas = new CikkBeolvas();
        cikkRepo = new VevoCikkRepo<Cikk>() {
            // erre az absztrakt metódus implementációra nincs szükségünk, de kötelező
            // azonban a "default" metódusok nem szükséges mindenképpen felülírnuk...
            @Override
            public List<Cikk> beolvasTxtAdattarbol(String fajlEleres) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
            // de, a findByKod(){ üres bodyval} bír, 
            // tehát elvileg itt helyben le kellene programoznunk..
            // azonban ez nem lenne hatékony kódolási eljárás - emiatt implementáltuk osztályként a "JdbcCikkReadRepo()" -néven!
        };
        
        // íme a  findByKod(){ ürs bodyval} bíró interfész metódusunknak, egy 
        // külön osztályban leimplementált verziója:        
        jdbcCikkOlvaso = new JdbcCikkReadRepo();
        
        vevoBekerese = new VevoBekerese();
        vevoRepo = new VevoCikkRepo<Vevo>() {
            // erre az absztrakt metódus implementációra nincs szükségünk, de kötelező
            // azonban a "default" metódusok meg eleve deklaráltak az interfészen belül, így azokat meg nem kell felülírnuk
            @Override
            public List<Vevo> beolvasTxtAdattarbol(String fajlEleres) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        };
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void cikkek_Sql_save_Test() {
        // először kiolassuk a text-fájl tartalmát:
        System.out.println("Cikk_Beolvasas_Txt_bol.Lista.Size() = " + cikkBeolvas.getCikkList().size());
        // kiíratjuk egy lambda kifejezésen keresztül 
        if(cikkBeolvas.getCikkList().size() > 0){
            // az adatbázisba INSERT-el (persze csak ha még nem tettük meg, mert az INSERT nem ír felül és nem duplikál:
            cikkBeolvas.getCikkList().forEach(e -> {                                                       
                                                        try {
                                                            cikkRepo.sqlInsert(e);
                                                        } catch (SQLException ex) {
                                                            ex.printStackTrace();
                                                        }
                                                    }
            );
            // teszteljük úgy, hogy a kiírt adatok közül visszaolvasunk egyet, amiről tudjuk milyen értékelkkel kell rendelkeznie:
            Optional<Cikk> optCikk = jdbcCikkOlvaso.findByKod(2L);
            if ( (! optCikk.isEmpty()) || (optCikk.isPresent())) {
                Cikk cikk = optCikk.get();
                assertEquals(cikk.megnevezes(),"Ketszer-Kettes termek");
                assertNotNull(cikk.egyseg_ar() == 403.2);
                
                if(cikk.megnevezes().equalsIgnoreCase("Ketszer-Kettes termek")){
                    System.out.println("Azonosak!");
                }
            } else {
                fail("Nincs talalat, ures eredmenyt kaptunk!");
            }                
        }else{
            fail("Az egysegtesztnek nincs ertelme, mert valamiert nem tudtuk a szovegfajlból beolvasni az adatokat!");
        }
    }

    @Test
    public void vevok_Sql_save_Test(){
    }
}
