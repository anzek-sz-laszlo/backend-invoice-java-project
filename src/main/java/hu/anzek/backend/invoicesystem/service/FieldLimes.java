/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/AnnotationType.java to edit this template
 */
package hu.anzek.backend.invoicesystem.service;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Adatmező adat-határ ellenőrő annotáció<br>
 * @author User
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD) 
public @interface FieldLimes {
    
    String lastLimes() default "00000";
    String firstLimes() default "99999";
    String message() default "A szamlasorszam kivul esik a tartomanyon!";
}
