package com.patrones.helicoptero;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;

/**
 * Created by suarezch on 07/09/2015.
 */
public class CuadroPausa {

    // Atributos
    private int margenHorizontal;
    private int margenVertical;
    private int radioEsquinas;
    private int color;
    private int left;
    private int top;
    private int right;
    private int bottom;
    private Paint paintCuadro;


    // Constructor
    public CuadroPausa(PanelJuego panelJuego) {
        this.margenHorizontal = 100;
        this.margenVertical = 80;
        this.radioEsquinas = 16;
        this.color = Color.argb(128, 255, 255, 255);

        // Límites del rectángulo
        this.left = this.margenHorizontal;
        this.top = this.margenVertical;
        this.right = panelJuego.WIDTH - this.margenHorizontal;
        this.bottom = panelJuego.HEIGHT - this.margenVertical;

        // Paint para el color del cuadro
        paintCuadro = new Paint();
        paintCuadro.setColor(this.color);
    }


    // Métodos
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void draw(Canvas canvas) {
        // Dibujar rectángulo redondeado
        canvas.drawRoundRect(
                this.left,
                this.top,
                this.right,
                this.bottom,
                this.radioEsquinas,
                this.radioEsquinas,
                this.paintCuadro
        );
    }

}
