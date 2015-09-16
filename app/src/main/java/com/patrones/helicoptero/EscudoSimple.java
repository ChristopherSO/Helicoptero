package com.patrones.helicoptero;
/**
 * Created by suarezch on 08/09/2015.
 */
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.patrones.laserlightgame.R;

import framework.ObjetoDisparable;

public class EscudoSimple extends Escudo {

    // Constructor
    public EscudoSimple(Helicoptero helicoptero) {
        super(
                BitmapFactory.decodeResource(helicoptero.getPanelJuego().getResources(), R.drawable.escudo_simple),
                12,
                90,
                90,
                35,
                helicoptero
        );
    }


    // Métodos
    public void update()
    {
        getAnimacion().update();
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(
                getAnimacion().getImage(),
                getHelicoptero().getX() - 12,
                getHelicoptero().getY() - 33,
                null
        );
    }

    public void colisionar() {
        this.getHelicoptero().setEscudo(null);
    }

}