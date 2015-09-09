package com.patrones.helicoptero;
/**
 * Created by suarezch on 08/09/2015.
 */

import android.graphics.Bitmap;
import android.graphics.Canvas;
import framework.Animacion;
import framework.ObjetoVolador;


public abstract class Escudo extends ObjetoVolador {

    // Atributos
    private Bitmap grafico;
    private Animacion animacion;
    private Helicoptero helicoptero;


    // Constructor
    public Escudo(Bitmap grafico, int w, int h, int cantFotogramas, Helicoptero helicoptero) {

        super.width = w;
        super.height = h;

        this.grafico = grafico;
        Bitmap[] image = new Bitmap[cantFotogramas];
        for(int i = 0; i<image.length;i++) {
            image[i] = Bitmap.createBitmap(this.grafico, i*width, 0, width, height);
        }
        animacion = new Animacion(image);

        this.helicoptero = helicoptero;
    }


    // Métodos
    public void update()
    {
        animacion.update();
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(
                animacion.getImage(),
                helicoptero.getX() - 12,
                helicoptero.getY() - 33,
                null);
    }

    public Animacion getAnimacion() {
        return this.animacion;
    }

    public Helicoptero getHelicoptero() {
        return this.helicoptero;
    }

    public abstract void colisionar();

}