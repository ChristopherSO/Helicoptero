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
    private int radio;
    private Helicoptero helicoptero;


    // Constructor
    public Escudo(Bitmap grafico, int cantFotogramas, int w, int h, int radio, Helicoptero helicoptero) {
        super.width = w;
        super.height = h;

        this.grafico = grafico;
        Bitmap[] image = new Bitmap[cantFotogramas];
        for(int i = 0; i<image.length;i++) {
            image[i] = Bitmap.createBitmap(this.grafico, i*width, 0, width, height);
        }
        this.animacion = new Animacion(image);
        this.radio = radio;
        this.helicoptero = helicoptero;
    }


    // Métodos
    public abstract void update();
    public abstract void draw(Canvas canvas);
    public abstract void colisionar();


    // Obtenedores y Modificadores
    public Animacion getAnimacion() {
        return  this.animacion;
    }
    public int getRadio() {
        return this.radio;
    }
    public Helicoptero getHelicoptero() {
        return this.helicoptero;
    }

}