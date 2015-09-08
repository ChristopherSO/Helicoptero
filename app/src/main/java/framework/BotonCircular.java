package framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by suarezch on 07/09/2015.
 */
public class BotonCircular {

    // Atributos
    private Bitmap imagen;
    private int x;
    private int y;
    private int cx;
    private int cy;
    private int radio;


    // Constructor
    public BotonCircular(Bitmap imagen, int radio) {
        this.imagen = imagen;
        this.radio = radio;
        System.out.println("** Creando botón circular");
    }


    // Obtenedores y Modificadores
    public void setX(int x) {
        this.x = x;
        this.cx = x + this.imagen.getWidth()/2;
    }
    public void setY(int y) {
        this.y = y;
        this.cy = y + this.imagen.getHeight()/2;
    }
    public Bitmap getImagen() {
        return this.imagen;
    }


    // Métodos
    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.imagen, this.x, this.y, null);
    }

    public boolean touchDentroArea(float x, float y) {

        // Distancia entre el punto tocado y el centro del círculo mediante Pitágoras
        float dx = this.cx - x;
        float dy = this.cy - y;
        double distancia = Math.hypot(dx, dy);

        // Si distancia es menor que el radio, se tocó adentro del botón
        if (distancia<this.radio) {
            return true;
        } else {
            return false;
        }

    }
}
