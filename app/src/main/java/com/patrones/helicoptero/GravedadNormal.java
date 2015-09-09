package com.patrones.helicoptero;

import android.graphics.Canvas;

/**
 * Created by suarezch on 23/08/2015.
 */
public class GravedadNormal implements IGravedad {

    // Atributos
    private Helicoptero helicoptero;


    // Constructor
    public GravedadNormal(Helicoptero helicoptero) {
        this.helicoptero = helicoptero;
        helicoptero.setDyFactor(1);
    }


    // Métodos implementados
    public void draw(Canvas canvas) {
        canvas.drawBitmap(
                helicoptero.getAnimacion().getImage(),
                helicoptero.getX(),
                helicoptero.getY(),
                null
        );
    }

    public void colisionar() {
        helicoptero.setJugando(false);
    }

}
