package com.patrones.helicoptero;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import framework.CambiadorDeEstados;

/**
 * Created by suarezch on 23/08/2015.
 */
public class CambiadorEscudoSimple extends CambiadorDeEstados {

    // Constructor
    public CambiadorEscudoSimple(int x, int y, PanelJuego panelJuego) {
        super(x, 64, 64, 32, 10);

        // Modificar el "y" para que no se salga de la pantalla abajo
        y = y * (panelJuego.HEIGHT - this.getRadio()*2) / panelJuego.HEIGHT;
        this.setY(y);

        // Para el texto
        Paint fuenteTexto = new Paint();
        fuenteTexto.setColor(Color.BLUE);
        fuenteTexto.setTextSize(12);
        fuenteTexto.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        this.setFuenteTexto(fuenteTexto);

        // Para el c�rculo
        Paint estiloCirculo = new Paint();
        estiloCirculo.setColor(Color.YELLOW);
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
        canvas.drawText("ESCUDO", x + 7, y + 37, this.getFuenteTexto());
    }

    public void cambiarEstado(Helicoptero helicoptero) {
        helicoptero.setEscudo(new EscudoSimple(helicoptero));
    }

}