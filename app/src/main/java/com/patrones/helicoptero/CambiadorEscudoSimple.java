package com.patrones.helicoptero;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.patrones.laserlightgame.R;

import framework.CambiadorDeEstados;

/**
 * Created by suarezch on 23/08/2015.
 */
public class CambiadorEscudoSimple extends CambiadorDeEstados {

    // Constructor
    public CambiadorEscudoSimple(int x, int y, PanelJuego panelJuego) {
        super(
                BitmapFactory.decodeResource(panelJuego.getResources(), R.drawable.cambiador_escudo_simple),
                12,
                x,
                y,
                90,
                90,
                35,
                8
        );

        // Modificar el "y" para que no se salga de la pantalla abajo
        y = y * (panelJuego.HEIGHT - this.getRadio()*2) / panelJuego.HEIGHT;
        this.setY(y);
    }


    // Métodos
    public void update() {
        this.x -= this.getVelocidad();
        getAnimacion().update();
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(
                getAnimacion().getImage(),
                this.getX(),
                this.getY(),
                null
        );
    }

    public void cambiarEstado(Helicoptero helicoptero) {
        helicoptero.setEscudo(new EscudoSimple(helicoptero));
    }

}
