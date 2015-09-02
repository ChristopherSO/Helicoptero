package com.patrones.helicoptero;

import android.graphics.Canvas;

import framework.CambiadorDeEstados;

/**
 * Created by suarezch on 23/08/2015.
 */
public interface IEstadoHelicoptero {

    void draw(Canvas canvas);

    void colisionar();
}
