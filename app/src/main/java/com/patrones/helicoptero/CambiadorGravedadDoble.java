package com.patrones.helicoptero;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.patrones.laserlightgame.R;

import framework.CambiadorDeEstados;

/**
 * Created by suarezch on 24/08/2015.
 */
public class CambiadorGravedadDoble extends CambiadorDeEstados {

    // Constructor
    public CambiadorGravedadDoble(int x, int y, PanelJuego panelJuego) {
        super(
                BitmapFactory.decodeResource(panelJuego.getResources(), R.drawable.cambiador_gravedad_doble),
                24,
                x,
                y,
                130,
                130,
                60,
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
        helicoptero.setGravedad(new GravedadDoble(helicoptero));
    }

}
