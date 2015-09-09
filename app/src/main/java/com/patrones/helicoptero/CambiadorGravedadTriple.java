package com.patrones.helicoptero;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import framework.CambiadorDeEstados;

/**
 * Created by suarezch on 24/08/2015.
 */
public class CambiadorGravedadTriple extends CambiadorDeEstados {

    // Constructor
    public CambiadorGravedadTriple(int x, int y, int alturaPanel) {
        super(x, 150, 150, 75, 7);

        // Modificar el "y" para que no se salga de la pantalla abajo
        y = y * (alturaPanel - this.getRadio()*2) / alturaPanel;
        this.setY(y);

        // Para el texto
        Paint fuenteTexto = new Paint();
        fuenteTexto.setColor(Color.WHITE);
        fuenteTexto.setTextSize(14);
        fuenteTexto.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        this.setFuenteTexto(fuenteTexto);

        // Para el círculo
        Paint estiloCirculo = new Paint();
        estiloCirculo.setColor(Color.argb(255,128,0,0));
        estiloCirculo.setStyle(Paint.Style.FILL_AND_STROKE);
        this.setEstiloCirculo(estiloCirculo);
    }


    // Métodos
    public void update()
    {
        super.update(this.getVelocidad());
    }

    public void draw(Canvas canvas) {

        // Dibujar círculo
        canvas.drawCircle(x + this.getRadio(), y + this.getRadio(), this.getRadio(), this.getEstiloCirculo());

        // Dibujar texto
        canvas.drawText("TRIPLE", x + 50, y + 70, this.getFuenteTexto());
        canvas.drawText("GRAVEDAD", x + 40, y + 90, this.getFuenteTexto());
    }

    public void cambiarEstado(Helicoptero helicoptero) {
        helicoptero.setDyFactor(3);
        helicoptero.setGravedad(new GravedadTriple(helicoptero));
    }

}
