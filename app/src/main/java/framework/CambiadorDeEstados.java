package framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.patrones.helicoptero.Helicoptero;
import com.patrones.helicoptero.PanelJuego;

/**
 * Created by suarezch on 23/08/2015.
 */

public abstract class CambiadorDeEstados extends ObjetoVolador implements Cloneable{

    // Atributos
    private Bitmap grafico;
    private Animacion animacion;
    private int radio;
    private int velocidad;

    // Constructor
    public CambiadorDeEstados(Bitmap grafico, int cantFotogramas, int x, int y, int ancho, int alto, int radio, int velocidad) {
        super.x = x;
        super.y = y;
        super.width = ancho;
        super.height = alto;

        this.grafico = grafico;
        Bitmap[] image = new Bitmap[cantFotogramas];
        for(int i = 0; i<image.length;i++) {
            image[i] = Bitmap.createBitmap(this.grafico, i*width, 0, width, height);
        }
        this.animacion = new Animacion(image);
        this.radio = radio;
        this.velocidad = velocidad;
    }

    // Métodos
    public abstract void update();
    public abstract void draw(Canvas canvas);
    public abstract void cambiarEstado(Helicoptero helicoptero);

    // Obtenedores y Modificadores
    public Animacion getAnimacion() {
        return  this.animacion;
    }
    public int getRadio() {
        return this.radio;
    }
    public int getVelocidad() {
        return this.velocidad;
    }

}