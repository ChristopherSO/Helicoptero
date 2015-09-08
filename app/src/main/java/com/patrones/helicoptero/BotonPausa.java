package com.patrones.helicoptero;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import com.patrones.helicoptero.PanelJuego;
import com.patrones.laserlightgame.R;

import framework.BotonCircular;

/**
 * Created by suarezch on 07/09/2015.
 */
public class BotonPausa extends BotonCircular {

    // Atributos
    private int margen = 8;


    // Constructor
    public BotonPausa(PanelJuego panelJuego) {
        super(
                BitmapFactory.decodeResource(panelJuego.getResources(), R.drawable.boton_pausa),
                36 // Radio real del círculo del ícono de 30
        );
        setX(panelJuego.WIDTH - (getImagen().getWidth() + this.margen));
        setY(this.margen);
    }

}
