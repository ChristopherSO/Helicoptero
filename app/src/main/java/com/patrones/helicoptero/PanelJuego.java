package com.patrones.helicoptero;

/**
 * Created by Felipe on 8/20/2015.
 */
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.patrones.laserlightgame.R;
import java.util.ArrayList;

import framework.CambiadorDeEstados;
import framework.Fondo;
import framework.ObjetoDisparable;
import framework.ObjetoVolador;


public class PanelJuego extends SurfaceView implements SurfaceHolder.Callback
{
    public static final int WIDTH = 856; //Ancho del fondo del juego
    public static final int HEIGHT = 480; //Alto del fondo del juego
    public static final int MOVESPEED = -5;
    float scaleFactorX;
    float scaleFactorY;
    private long misilTiempoComienzo;
    private MainThread thread;
    private Fondo bg;
    private BotonPausa botonPausa;
    private Helicoptero helicoptero;
    private ArrayList<ObjetoDisparable> misiles;
    private ArrayList<Humo> humo;
    private long smokeStartTime;
    private ArrayList<CambiadorDeEstados> cambiadores;
    private Fabrica fabrica;
    private boolean newGameCreated;
    private Paint fuenteTexto;
    private float touchedX;
    private float touchedY;
    private CuadroPausa cuadroPausa;

    //Variables para cuando el jugador muere
    private ExplosionHelicoptero explosion;
    private long startReset;

    //Reset indica el tiempo entre que el jugador muere, y vuelve a comenzar un juego nuevo. Permite un lapso de tiempo entre la muerte del jugador y un nuevo comienzo.
    private boolean reset;
    private boolean dissapear;
    private boolean started;
    private int best;

    public PanelJuego(Context context)
    {
        super(context);
        this.misiles = new ArrayList<ObjetoDisparable>();
        this.humo = new ArrayList<Humo>();
        this.cambiadores = new ArrayList<CambiadorDeEstados>();

        //Callback al surfaceholder para interceptar events
        getHolder().addCallback(this);

        //Permite focus para poder interceptar events
        setFocusable(true);

        //Definir la fuente de los textos inferiores
        fuenteTexto = new Paint();
        fuenteTexto.setColor(Color.RED);
        fuenteTexto.setTextSize(30);
        fuenteTexto.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        int counter = 0;
        while(retry && counter<1000)
        {
            counter++;
            try{thread.setRunning(false);
                thread.join();
                retry = false;
                thread = null;

            }catch(InterruptedException e){e.printStackTrace();}

        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){

        bg = new Fondo(this, BitmapFactory.decodeResource(getResources(), R.drawable.grassbg1));
        botonPausa = new BotonPausa(this);
        cuadroPausa = new CuadroPausa(this);
        helicoptero = new Helicoptero(this);
        fabrica = new Fabrica(this);
        misilTiempoComienzo = System.nanoTime();
        this.scaleFactorX = getWidth()/(WIDTH*1.f);
        this.scaleFactorY = getHeight()/(HEIGHT*1.f);

        thread = new MainThread(getHolder(),this);

        //Empieza el juego
        thread.setRunning(true);
        thread.start();

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        touchedX = event.getX() / scaleFactorX;
        touchedY = event.getY() / scaleFactorY;

        if(event.getAction()==MotionEvent.ACTION_DOWN){

            // Averiguar si presionó el botón de pausa
            if(botonPausa.touchDentroArea(touchedX,touchedY)) {
                helicoptero.setJugando(false);
                return true;
            }

            if(!helicoptero.getJugando()&&newGameCreated && reset)
            {
                helicoptero.setJugando(true);
                helicoptero.setTocandoPantalla(true);
            }
            if(helicoptero.getJugando()){
                if(!started)started = true;
                reset = false;
                helicoptero.setTocandoPantalla(true);
            }
            return true;
        }

        if(event.getAction()==MotionEvent.ACTION_UP)
        {
            helicoptero.setTocandoPantalla(false);
            return true;
        }

        return super.onTouchEvent(event);
    }

    public void update() {

        if(helicoptero.getJugando()) {

            bg.update();
            helicoptero.update();

            //Revisa que el jugador no se salga de la pantalla
            if (fueraPantalla()) {
                helicoptero.setJugando(false);
            }

            //Anade misiles al timer
            long missileElapsed = (System.nanoTime()-misilTiempoComienzo)/1000000;
            if(missileElapsed >(2000 - helicoptero.getPuntaje()/4)){

                System.out.println("creando misil");

                misiles.add(fabrica.crearMisil());

                //reset timer
                misilTiempoComienzo = System.nanoTime();
            }

            //loop a cada misil y revisa colision, remueve misiles que hagan colision
            for(int i = 0; i< misiles.size();i++)
            {
                //update missile
                misiles.get(i).update();

                if(colision(misiles.get(i), helicoptero))
                {
                    helicoptero.colisionar();
                    misiles.remove(i);
                    break;
                }
                //remueve el misil si se sale de la pantalla
                if(misiles.get(i).getX()<-100)
                {
                    misiles.remove(i);
                    break;
                }
            }

            //anade el humo
            long elapsed = (System.nanoTime() - smokeStartTime)/1000000;
            if(elapsed > 120) {
                if (helicoptero.getTocandoPantalla()) {
                    humo.add(fabrica.crearHumo());
                    smokeStartTime = System.nanoTime();
                }
            }
            // Remueve el humo cuando sale de la pantalla
            for(int i = 0; i<humo.size();i++)
            {
                humo.get(i).update();
                if(humo.get(i).getX()<-10)
                {
                    humo.remove(i);
                }
            }

            // Añadir Cambiador a la lista
            if (helicoptero.getPuntaje() % 100 == 0) {
                if (cambiadores.size() == 0) { // Este if asegura que solo se cree una instancia durante el tiempo en que el puntaje coincide con el resto del módulo
                    CambiadorDeEstados cambiador = fabrica.crearCambiador();
                    if (!(this.helicoptero.getEscudo() instanceof EscudoSimple && cambiador instanceof CambiadorEscudoSimple)) {
                        cambiadores.add(cambiador);
                    }
                }
            }
            // Para cada cambiador...
            for(int i = 0; i< cambiadores.size();i++) {
                // Update cambiador
                if (cambiadores.get(i) instanceof CambiadorEscudoSimple) {
                    ((CambiadorEscudoSimple)cambiadores.get(i)).update();
                }
                else if (cambiadores.get(i) instanceof CambiadorGravedadDoble) {
                    ((CambiadorGravedadDoble)cambiadores.get(i)).update();
                }
                else if (cambiadores.get(i) instanceof CambiadorGravedadTriple) {
                    ((CambiadorGravedadTriple)cambiadores.get(i)).update();
                }
                else if (cambiadores.get(i) instanceof CambiadorGravedadCuadruple) {
                    ((CambiadorGravedadCuadruple)cambiadores.get(i)).update();
                }
                else if (cambiadores.get(i) instanceof CambiadorGravedadInversa) {
                    ((CambiadorGravedadInversa)cambiadores.get(i)).update();
                }
                else if (cambiadores.get(i) instanceof CambiadorGravedadNormal) {
                    ((CambiadorGravedadNormal)cambiadores.get(i)).update();
                }

                // Comprobar colisión para remover el cambiador
                if(colision(cambiadores.get(i), helicoptero)) {

                    if (cambiadores.get(i) instanceof CambiadorEscudoSimple) {
                        ((CambiadorEscudoSimple)cambiadores.get(i)).cambiarEstado(helicoptero);
                    }
                    else if (cambiadores.get(i) instanceof CambiadorGravedadDoble) {
                        ((CambiadorGravedadDoble)cambiadores.get(i)).cambiarEstado(helicoptero);
                    }
                    else if (cambiadores.get(i) instanceof CambiadorGravedadCuadruple) {
                        ((CambiadorGravedadCuadruple)cambiadores.get(i)).cambiarEstado(helicoptero);
                    }
                    else if (cambiadores.get(i) instanceof CambiadorGravedadTriple) {
                        ((CambiadorGravedadTriple)cambiadores.get(i)).cambiarEstado(helicoptero);
                    }
                    else if (cambiadores.get(i) instanceof CambiadorGravedadInversa) {
                        ((CambiadorGravedadInversa)cambiadores.get(i)).cambiarEstado(helicoptero);
                    }
                    else if (cambiadores.get(i) instanceof CambiadorGravedadNormal) {
                        ((CambiadorGravedadNormal)cambiadores.get(i)).cambiarEstado(helicoptero);
                    }

                    cambiadores.remove(i);
                    break;
                }
                // Remueve el cambiador si se sale de la pantalla
                if(cambiadores.get(i).getX() < -cambiadores.get(i).getWidth()) {
                    cambiadores.remove(i);
                    break;
                }
            }
        }
        else
        {
            helicoptero.resetDY();
            if(!reset){
                newGameCreated = false;
                startReset = System.nanoTime();
                reset = true;
                dissapear = true;
                explosion = new ExplosionHelicoptero(BitmapFactory.decodeResource(getResources(),R.drawable.explosion),helicoptero);
            }

            helicoptero.setEstadoInicial();

            explosion.update();
            long resetElapsed = (System.nanoTime()-startReset)/1000000;

            if(resetElapsed > 2500 && !newGameCreated){
                newGame();
            }
        }
    }

    //Funcion que revisa la posicion el jugador dentro de la pantalla
    public boolean fueraPantalla(){
        if(helicoptero.getY() <= 0){
            return true;
        }

        else if(helicoptero.getY() >= HEIGHT-helicoptero.getHeight()){
            return true;
        }

        return false;

    }

    public boolean colision(ObjetoVolador a, ObjetoVolador b)
    {
        if(Rect.intersects(a.getRectangle(), b.getRectangle()))
        {
            return true;
        }
        return false;
    }

    @Override
    public void draw(Canvas canvas)
    {
        if(canvas!=null) {
            final int savedState = canvas.save();

            canvas.scale(scaleFactorX, scaleFactorY);
            bg.draw(canvas);

            if(!dissapear){
                helicoptero.draw(canvas);
            }

            //draw humo
            for(Humo sp: humo)
            {
                sp.draw(canvas);
            }

            //draw cambiadores
            for(CambiadorDeEstados c: cambiadores) {
                c.draw(canvas);
            }

            //draw misiles
            for(ObjetoDisparable m: misiles) {
                m.draw(canvas);
            }

            //draw explosion
            if(started) {
                explosion.draw(canvas);
                //cuadroPausa.draw(canvas);
            }

            //draw texto
            drawText(canvas);

            // draw botón de pausa
            if(helicoptero.getJugando()) {
                botonPausa.draw(canvas);
            }

            canvas.restoreToCount(savedState);
        }
    }

    public void newGame(){
        dissapear = false;
        misiles.clear();
        humo.clear();
        cambiadores.clear();
        fabrica.resetListaComandos();

        //Revisa el puntaje para el jugador en panalla
        helicoptero.resetDY();
        if(helicoptero.getPuntaje() > best){
            best = helicoptero.getPuntaje();
        }

        helicoptero.resetPuntaje();
        helicoptero.setY(HEIGHT / 2);



        newGameCreated = true;
    }

    public void drawText(Canvas canvas){
        canvas.drawText("PUNTAJE: " + helicoptero.getPuntaje(), 10, HEIGHT - 10, fuenteTexto);
        canvas.drawText("MEJOR: " + best, WIDTH - 215, HEIGHT - 10, fuenteTexto);
    }

    public Helicoptero getHelicoptero() {
        return helicoptero;
    }


}