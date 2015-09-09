package framework;

import android.graphics.Bitmap;
import android.graphics.Matrix;


/**
 * Created by Felipe on 8/21/2015.
 */
public abstract class JugadorAeronave extends ObjetoVolador {

    // Atributos
    private Bitmap grafico;
    private int puntaje;
    private boolean tocandoPantalla;
    private boolean jugando;
    private long tiempoInicio;
    private int cantFotogramas;
    private int retrasoAnimacion;
    private int dyFactor;
    private boolean estaVolteado = false;
    private Animacion animacion;


    // Constructor
    public JugadorAeronave(int x, int y, int ancho, int alto, int cantFotogramas, int retrasoAnimacion, int factorDy) {

        super.x = x;
        super.y = y;
        super.dy = 0;
        super.height = alto;
        super.width = ancho;

        this.puntaje = 0;
        this.tiempoInicio = System.nanoTime();
        this.cantFotogramas = cantFotogramas;
        this.retrasoAnimacion = retrasoAnimacion;
    }


    // Métodos
    public void update(int deltaY, int maxDy)
    {
        long elapsed = (System.nanoTime()- tiempoInicio)/1000000;
        if(elapsed > 100)
        {
            puntaje++;
            tiempoInicio = System.nanoTime();
        }
        animacion.update();

        if(tocandoPantalla){
            this.dy -= deltaY;
        } else {
            this.dy += deltaY;
        }

        if(dy > maxDy)dy = maxDy;
        if(dy < -maxDy)dy = -maxDy;

        this.y += dy * this.dyFactor;

    }

    public void resetDY() {
        dy = 0;
    }

    public void resetPuntaje() {
        puntaje = 0;
    }

    public Bitmap getGrafico() {
        return this.grafico;
    }
    public void setGrafico(Bitmap grafico) {
        this.grafico = grafico;

        Bitmap[] image = new Bitmap[this.cantFotogramas];
        for (int i = 0; i < image.length; i++) {
            image[i] = Bitmap.createBitmap(this.grafico, i*width, 0, width, height);
        }
        this.animacion = new Animacion(image);
        this.animacion.setDelay(this.retrasoAnimacion);
    }
    public int getPuntaje(){return puntaje;}
    public boolean getTocandoPantalla() {
        return this.tocandoPantalla;
    }
    public void setTocandoPantalla(boolean b){
        tocandoPantalla = b;}
    public boolean getJugando(){return jugando;}
    public void setJugando(boolean b){
        jugando = b;
    }
    public void setDyFactor(int dyFactor) {
        this.dyFactor = dyFactor;

        // voltear el helicóptero si dyFactor=-1 o enderezarlo si dyFactor>0
        if (dyFactor==-1 && estaVolteado==false) {
            Bitmap graficoVolteado = this.grafico;
            Matrix matrix = new Matrix();
            matrix.preScale(1.0f, -1.0f);
            graficoVolteado = Bitmap.createBitmap(graficoVolteado, 0, 0, graficoVolteado.getWidth(), graficoVolteado.getHeight(), matrix, true);
            setGrafico(graficoVolteado);
            this.estaVolteado = true;
        }
        // Enderezar el helicóptero si dyFactor>0
        if (dyFactor>0 && estaVolteado==true) {
            Bitmap graficoVolteado = this.grafico;
            Matrix matrix = new Matrix();
            matrix.preScale(1.0f, -1.0f);
            graficoVolteado = Bitmap.createBitmap(graficoVolteado, 0, 0, graficoVolteado.getWidth(), graficoVolteado.getHeight(), matrix, true);
            setGrafico(graficoVolteado);
            this.estaVolteado = false;
        }
    }
    public Animacion getAnimacion () {
        return this.animacion;
    }



}