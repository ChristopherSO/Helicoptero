package com.patrones.helicoptero;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.patrones.laserlightgame.R;
import framework.JugadorAeronave;

/**
 * Created by Felipe on 8/21/2015.
 */
public class Helicoptero extends JugadorAeronave {

    // Atributos
    private PanelJuego panelJuego;
    private IGravedad gravedad;
    private Escudo escudo;

    // Constructor
    public Helicoptero(PanelJuego panelJuego) {
        super(
                100,
                PanelJuego.HEIGHT / 2,
                65,
                27,
                3,
                10,
                1
        );
        this.panelJuego = panelJuego;
        setGrafico(BitmapFactory.decodeResource(this.panelJuego.getResources(), R.drawable.helicopter));
        setEstadoInicial();
    }


    // Métodos
    public void update() {
        super.update(1, 12);
        if (this.escudo != null) {
            this.escudo.update();
        }
    }

    public void draw(Canvas canvas) {
        this.gravedad.draw(canvas);
        if (this.escudo != null) {
            this.escudo.draw(canvas);
        }
    }

    // Obtenedores y Modificadores
    public IGravedad getGravedad() {
        return this.gravedad;
    }
    public void setGravedad(IGravedad gravedad) {
        this.gravedad = gravedad;
    }

    public Escudo getEscudo() {
        return this.escudo;
    }
    public void setEscudo(Escudo escudo) {
        this.escudo = escudo;
    }

    public void setEstadoInicial() {
        if (!(gravedad instanceof GravedadNormal)) {
            this.gravedad = new GravedadNormal(this);
            this.escudo = null;
        }
    }

    public PanelJuego getPanelJuego() {
        return this.panelJuego;
    }

    public void colisionar() {
        if (escudo != null) {
            this.escudo.colisionar();
        } else {
            this.setJugando(false);
        }

    }
}