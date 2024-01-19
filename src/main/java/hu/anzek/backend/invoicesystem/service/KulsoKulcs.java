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
 * Egyszerű címke annotáció, meely jelzi, hogy a következő adat egy úgynevezett SQL külső-kulcs<br>
 * @author User
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD) 
public @interface KulsoKulcs {
       
    // A "@Retention" beállítás azt jelenti, hogy a futási időben is elérhető lesz az annotáció
    // A @Target beállítás azt jelzi, hogy ő egy meező-információ lesz
}
