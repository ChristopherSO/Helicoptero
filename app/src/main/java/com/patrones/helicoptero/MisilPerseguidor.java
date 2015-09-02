package com.patrones.helicoptero;
/**
 * Created by Felipe on 8/21/2015.
 */
import android.graphics.Bitmap;

import framework.ObjetoDisparable;

public class MisilPerseguidor extends ObjetoDisparable {

    // Atributos
    private int dy;
    private Helicoptero helicoptero;

    // Constructor
    public MisilPerseguidor(Bitmap imagen, int x, int y, int puntaje, int alturaPanel, Helicoptero helicoptero, int dy) {
        super(
                imagen,
                x,
                y,
                45,
                15,
                puntaje,
                13
        );
        this.getAnimacion().setDelay(100 - this.getVelocidad());
        this.helicoptero = helicoptero;
        this.dy = dy;

        // Modificar el "y" para que se ajuste a sus márgenes
        y = (y * (alturaPanel - this.getHeight()*3) / alturaPanel) + this.getHeight();
        this.setY(y);
    }


    // Métodos
    @Override
    public int getWidth() {
        //Evita la colisión contra la llama del proyectil
        return width - 10;
    }

    public void update() {
        super.update();


        // Hacer bajar el misilPerseguidor cuando el helicóptero está más bajo que el misil
        // o hacerlo subir cuando el helicóptero esté más alto.
        if (Math.abs(this.helicoptero.getY() - this.getY()) > 5) {
            if (this.helicoptero.getY() > this.getY()) {
                this.setY(this.getY() + dy);
            } else {
                this.setY(this.getY() - dy);
            }
        }
    }

}