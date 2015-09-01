package com.patrones.helicoptero;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import framework.CambiadorDeEstados;

/**
 * Created by suarezch on 24/08/2015.
 */
public class EstadoTripleGravedad implements IEstadoHelicoptero {

    // Atributos
    private Helicoptero helicoptero;


    // Constructor
    public EstadoTripleGravedad(Helicoptero helicoptero) {
        this.helicoptero = helicoptero;
    }


    // Métodos implementados
    public void draw(Canvas canvas) {

        // Dibujar el escudo
        Paint estiloCirculo = new Paint();
        estiloCirculo.setColor(Color.argb(24, 128, 0, 0));
        estiloCirculo.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(
                helicoptero.getX() + 34,
                helicoptero.getY() + 12,
                40,
                estiloCirculo
        );

        // Dibujar el helicóptero normalcanvas.drawBitmap(
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
