/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.anzek.backend.invoicesystem.service;

/**
 *
 * @author User
 */
public class OwnExceptionClass extends Exception  {
        
    private static final long serialVersionUID = 1L;

    /**
     * Konstruktor-1, paraméter nélküli<br>
     * A hibakezelő osztály<br>
     */
    public OwnExceptionClass(){        
        super();
    }
       
    /**
     * Konstruktor-2<br>
     * A hibakezelő osztály elkapja a try- kivétel üzenetét<br>
     * @param message a bejüvő üzenet<br>
     */
    public OwnExceptionClass(String message){        
        super(message);
    }
}
