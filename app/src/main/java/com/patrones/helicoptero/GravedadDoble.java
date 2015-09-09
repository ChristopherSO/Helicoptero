package com.patrones.helicoptero;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by suarezch on 24/08/2015.
 */
public class GravedadDoble implements IGravedad {

    // Atributos
    private Helicoptero helicoptero;


    // Constructor
    public GravedadDoble(Helicoptero helicoptero) {
        this.helicoptero = helicoptero;
        helicoptero.setDyFactor(2);
    }


    // Métodos implementados
    public void draw(Canvas canvas) {

        // Dibujar el escudo
        Paint estiloCirculo = new Paint();
        estiloCirculo.setColor(Color.argb(24, 255, 0, 0));
        estiloCirculo.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(
                helicoptero.getX() + 34,
                helicoptero.getY() + 12,
                40,
                estiloCirculo
        );

        // Dibujar el helicóptero normal
        canvas.drawBitmap(
                helicoptero.getAnimacion().getImage(),
                helicoptero.getX(),
                helicoptero.getY(),
                null
        );
    }

}
