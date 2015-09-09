package framework;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by suarezch on 23/08/2015.
 */

public abstract class CambiadorDeEstados extends ObjetoVolador implements Cloneable{

    // Atributos
    private Paint fuenteTexto;
    private int radio;
    private Paint estiloCirculo;
    private int velocidad;


    // Constructor
    public CambiadorDeEstados(int x, int ancho, int alto, int radio, int velocidad) {
        super.x = x;
        super.width = ancho;
        super.height = alto;
        this.radio = radio;
        this.velocidad = velocidad;
    }


    // Métodos
    public void update(int dx) {
        x -= dx;
    }

    public abstract void draw(Canvas canvas);

    // Obtenedores y Modificadores
    public Paint getFuenteTexto() {
        return this.fuenteTexto;
    }
    public void setFuenteTexto(Paint fuenteTexto) {
        this.fuenteTexto = fuenteTexto;
    }
    public int getRadio() {
        return this.radio;
    }
    public Paint getEstiloCirculo() {
        return this.estiloCirculo;
    }
    public void setEstiloCirculo(Paint estiloCirculo) {
        this.estiloCirculo = estiloCirculo;
    }
    public int getVelocidad() {
        return this.velocidad;
    }

}