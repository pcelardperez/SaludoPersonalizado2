package com.example.pedro.saludopersonalizado;

import java.io.Serializable;

/**
 * Serializamos objetos para pasar información. Esta es otra forma opcional a los intent/bundles etc.. utiliza la que quieras
 * Created by pedro on 5/11/14. .
 */
public class MiClase implements Serializable {
    private static final long serieV = 1L;
    String nombre;
    int edad;

    public MiClase(String nom, int edad){
        this.nombre=nom;
        this.edad=edad;
    }
}
