package com.patrones.helicoptero;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import framework.CambiadorDeEstados;

/**
 * Created by suarezch on 24/08/2015.
 */
public class CambiadorCuadrupleGravedad extends CambiadorDeEstados {

    // Constructor
    public CambiadorCuadrupleGravedad(int x, int y) {
        super(x, y, 180, 180, 90, 6);

        // Para el texto
        Paint fuenteTexto = new Paint();
        fuenteTexto.setColor(Color.WHITE);
        fuenteTexto.setTextSize(14);
        fuenteTexto.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        this.setFuenteTexto(fuenteTexto);

        // Para el c�rculo
        Paint estiloCirculo = new Paint();
        estiloCirculo.setColor(Color.argb(255,64,0,0));
        estiloCirculo.setStyle(Paint.Style.FILL_AND_STROKE);
        this.setEstiloCirculo(estiloCirculo);
    }


    // M�todos
    public void update()
    {
        super.update(this.getVelocidad());
    }

    public void draw(Canvas canvas) {

        // Dibujar c�rculo
        canvas.drawCircle(x + this.getRadio(), y + this.getRadio(), this.getRadio(), this.getEstiloCirculo());

        // Dibujar texto
        canvas.drawText("CUADRUPLE", x + 49, y + 85, this.getFuenteTexto());
        canvas.drawText("GRAVEDAD", x + 55, y + 105, this.getFuenteTexto());
    }

    public void cambiarEstado(Helicoptero helicoptero) {
        helicoptero.setDyFactor(4);
        helicoptero.setEstado(new EstadoCuadrupleGravedad(helicoptero));
    }

}
