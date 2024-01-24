/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.anzek.backend.invoicesystem.service;


/**
 *
 * @author User
 */
public class OwnConnectionExeption extends Exception {

    private static final long serialVersionUID = 1L;    
    private final OwnExceptionClass connectionException;

    public OwnConnectionExeption(final OwnExceptionClass ownException) {
        super();
        this.connectionException = ownException;
    }

    public OwnConnectionExeption(String message, final OwnExceptionClass ownException) {
        super(message);
        this.connectionException = ownException;
    }    

    public OwnExceptionClass getConnectionException() {
        return connectionException;
    }    
}
