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
                90,
                90,
                12,
                helicoptero
        );
    }


    // Métodos
    public void colisionar() {
        this.getHelicoptero().setEscudo(null);
    }

}