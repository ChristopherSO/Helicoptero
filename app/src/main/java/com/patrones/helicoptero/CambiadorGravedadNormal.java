package com.patrones.helicoptero;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import framework.CambiadorDeEstados;

/**
 * Created by suarezch on 23/08/2015.
 */
public class CambiadorGravedadNormal extends CambiadorDeEstados {

    // Constructor
    public CambiadorGravedadNormal(int x, int y, int alturaPanel) {
        super(x, 80, 80, 40, 10);

        // Modificar el "y" para que no se salga de la pantalla abajo
        y = y * (alturaPanel - this.getRadio()*2) / alturaPanel;
        this.setY(y);

        // Para el texto
        Paint fuenteTexto = new Paint();
        fuenteTexto.setColor(Color.BLACK);
        fuenteTexto.setTextSize(12);
        fuenteTexto.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        this.setFuenteTexto(fuenteTexto);

        // Para el círculo
        Paint estiloCirculo = new Paint();
        estiloCirculo.setColor(Color.argb(255,255,192,0));
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
        canvas.drawText("GRAVEDAD", x + 10, y + 35, this.getFuenteTexto());
        canvas.drawText("NORMAL", x + 18, y + 55, this.getFuenteTexto());
    }

    public void cambiarEstado(Helicoptero helicoptero) {
        helicoptero.setDyFactor(1);
        helicoptero.setEstado(new EstadoNormal(helicoptero));
    }

}
