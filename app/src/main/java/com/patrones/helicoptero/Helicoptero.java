package com.patrones.helicoptero;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.patrones.laserlightgame.R;
import framework.JugadorAeronave;

/**
 * Created by Felipe on 8/21/2015.
 */
public class Helicoptero extends JugadorAeronave {

    // Atributos
    private IEstadoHelicoptero estado;
    private PanelJuego panelJuego;

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
        super.update(1, 14);
    }


    // Obtenedores y Modificadores
    public IEstadoHelicoptero getEstado() {
        return this.estado;
    }

    public void setEstado(IEstadoHelicoptero estado) {
        this.estado = estado;
    }

    public void setEstadoInicial() {
        if (!(estado instanceof EstadoNormal)) {
            this.estado = new EstadoNormal(this);
            this.setDyFactor(1);
        }
    }

}