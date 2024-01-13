/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package hu.anzek.backend.invoicesystem.service;


import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * MySql kapcsolati osztály teszt<br>
 * @author User
 */
public class MySQLConnectionServiceTest {
    
    // tagváltozó:
    private MySQLConnectionService connectionService;
    
    // konstruktor
    public MySQLConnectionServiceTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        // egy kapcsolati példány és a paraméter adatbetöltés:
        connectionService = new MySQLConnectionService();
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void helloMysql_Connection() throws SQLException {
    
        assertNotNull(this.connectionService.getConnection());
        assertEquals(this.connectionService.getUsername(),"anzek");
    }
}
