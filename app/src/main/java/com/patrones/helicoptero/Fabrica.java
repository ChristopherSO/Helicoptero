package com.patrones.helicoptero;

import android.graphics.BitmapFactory;
import com.patrones.laserlightgame.R;
import java.util.Random;

import framework.CambiadorDeEstados;
import framework.ObjetoDisparable;


/**
 * Created by suarezch on 22/08/2015.
 */
public class Fabrica {

    // Atributos
    private Random rand;
    // Relaciones
    private PanelJuego panelJuego;


    // Constructor
    public Fabrica(PanelJuego panelJuego) {
        this.rand = new Random();
        this.panelJuego = panelJuego;
    }

    // M�todos
    public Misil crearMisilNormal() {
        return new Misil(BitmapFactory.decodeResource(this.panelJuego.getResources(), R.drawable.
                misil),this.panelJuego.WIDTH, (int)(rand.nextDouble()*this.panelJuego.HEIGHT), this.panelJuego.getHelicoptero().getPuntaje(), this.panelJuego.HEIGHT);
    }
    public MisilPerseguidor crearMisilPerseguidor() {
        return new MisilPerseguidor(
                BitmapFactory.decodeResource(this.panelJuego.getResources(), R.drawable.
                misil_perseguidor),
                this.panelJuego.WIDTH,
                (int)(rand.nextDouble()*this.panelJuego.HEIGHT),
                this.panelJuego.getHelicoptero().getPuntaje(),
                this.panelJuego.HEIGHT,
                this.panelJuego.getHelicoptero(),
                rand.nextInt(4) + 1 // n�mero aleatorio entre 1 y 3
        );
    }

    public Humo crearHumo() {
        return new Humo(this.panelJuego.getHelicoptero().getX(),
                this.panelJuego.getHelicoptero().getY());
    }

    private CambiadorEscudo crearCambiadorEscudo() {
        return new CambiadorEscudo(this.panelJuego.WIDTH,(int)(rand.nextDouble()*this.panelJuego.HEIGHT), this.panelJuego.HEIGHT);
    }
    private CambiadorDobleGravedad crearCambiadorDobleGravedad() {
        return new CambiadorDobleGravedad(this.panelJuego.WIDTH,(int)(rand.nextDouble()*this.panelJuego.HEIGHT), this.panelJuego.HEIGHT);
    }
    private CambiadorTripleGravedad crearCambiadorTripleGravedad() {
        return new CambiadorTripleGravedad(this.panelJuego.WIDTH,(int)(rand.nextDouble()*this.panelJuego.HEIGHT), this.panelJuego.HEIGHT);
    }
    private CambiadorCuadrupleGravedad crearCambiadorCuadrupleGravedad() {
        return new CambiadorCuadrupleGravedad(this.panelJuego.WIDTH,(int)(rand.nextDouble()*this.panelJuego.HEIGHT), this.panelJuego.HEIGHT);
    }
    private CambiadorGravedadInversa crearCambiadorGravedadInversa() {
        return new CambiadorGravedadInversa(this.panelJuego.WIDTH,(int)(rand.nextDouble()*this.panelJuego.HEIGHT), this.panelJuego.HEIGHT);
    }
    private CambiadorGravedadNormal crearCambiadorGravedadNormal() {
        return new CambiadorGravedadNormal(this.panelJuego.WIDTH,(int)(rand.nextDouble()*this.panelJuego.HEIGHT), this.panelJuego.HEIGHT);
    }

    public CambiadorDeEstados crearCambiador() {
        int numAleatorio;
        int puntaje = this.panelJuego.getHelicoptero().getPuntaje();
        IEstadoHelicoptero estadoHelicoptero = this.panelJuego.getHelicoptero().getEstado();

        if (puntaje >= 2000) {
            numAleatorio = rand.nextInt(13) + 1 ; // Resultados entre 1 y 12
            System.out.println("** puntaje = " + puntaje + ", numAleatorio entre 1 y 12 = " + numAleatorio);
            while (numAleatorio > 0) {
                switch (numAleatorio) {
                    case 12:
                        if (!(estadoHelicoptero instanceof EstadoCuadrupleGravedad)) {
                            return crearCambiadorCuadrupleGravedad();
                        }
                    case 11:
                    case 10:
                        if (!(estadoHelicoptero instanceof EstadoTripleGravedad)) {
                            return crearCambiadorTripleGravedad();
                        }
                    case 9:
                    case 8:
                        if (!(estadoHelicoptero instanceof EstadoDobleGravedad)) {
                            return crearCambiadorDobleGravedad();
                        }
                    case 7:
                    case 6:
                        if (!(estadoHelicoptero instanceof EstadoGravedadInversa)) {
                            return crearCambiadorGravedadInversa();
                        }
                    case 5:
                    case 4:
                        if (!(estadoHelicoptero instanceof EstadoNormal || estadoHelicoptero instanceof EstadoConEscudo)) {
                            return  crearCambiadorGravedadNormal();
                        }
                    default:
                        if (!(estadoHelicoptero instanceof EstadoConEscudo)) {
                            return crearCambiadorEscudo();
                        }
                        System.out.println("** Cay� CambiadorEscudo cuando ya ten�a escudo puesto");
                        numAleatorio = rand.nextInt(13) + 6 ; // Resultados entre 6 y 12
                        /***
                         * si llega a este punto es porque ten�a el escudo puesto, por eso se
                         * reinicia el proceso con el while pero esta vez quitando las opciones de
                         * escudo y gravedad normal.
                         ***/
                } // cierre del switch
            } // cierre del while
        } // cierre del if (puntaje > 2000)
        else if (puntaje >= 1500) {
            numAleatorio = rand.nextInt(11) + 1 ; // Resultados entre 1 y 10
            System.out.println("** puntaje = " + puntaje + ", numAleatorio entre 1 y 10 = " + numAleatorio);
            while (numAleatorio > 0) {
                switch (numAleatorio) {
                case 10:
                    if (!(estadoHelicoptero instanceof EstadoTripleGravedad)) {
                        return crearCambiadorTripleGravedad();
                    }
                case 9:
                case 8:
                    if (!(estadoHelicoptero instanceof EstadoDobleGravedad)) {
                        return crearCambiadorDobleGravedad();
                    }
                case 7:
                case 6:
                    if (!(estadoHelicoptero instanceof EstadoGravedadInversa)) {
                        return crearCambiadorGravedadInversa();
                    }
                case 5:
                case 4:
                    if (!(estadoHelicoptero instanceof EstadoNormal || estadoHelicoptero instanceof EstadoConEscudo)) {
                        return  crearCambiadorGravedadNormal();
                    }
                default:
                    if (!(estadoHelicoptero instanceof EstadoConEscudo)) {
                        return crearCambiadorEscudo();
                    }
                    System.out.println("** Cay� CambiadorEscudo cuando ya ten�a escudo puesto");
                    numAleatorio = rand.nextInt(11) + 6 ; // Resultados entre 6 y 10
                    /***
                     * si llega a este punto es porque ten�a el escudo puesto, por eso se
                     * reinicia el proceso con el while pero esta vez quitando las opciones de
                     * escudo y gravedad normal.
                     ***/
                } // cierre del switch
            }
        }
        else if (puntaje >= 1000) {
            numAleatorio = rand.nextInt(9) + 1; // Resultados entre 1 y 8
            System.out.println("** puntaje = " + puntaje + ", numAleatorio entre 1 y 8 = " + numAleatorio);
            while (numAleatorio > 0) {
                switch (numAleatorio) {
                    case 8:
                        return crearCambiadorGravedadInversa();
                    case 7:
                    case 6:
                        if (!(estadoHelicoptero instanceof EstadoDobleGravedad)) {
                            return crearCambiadorDobleGravedad();
                        }
                    case 5:
                    case 4:
                        if (!(estadoHelicoptero instanceof EstadoNormal || estadoHelicoptero instanceof EstadoConEscudo)) {
                            return  crearCambiadorGravedadNormal();
                        }
                    default:
                        if (!(estadoHelicoptero instanceof EstadoConEscudo)) {
                            return crearCambiadorEscudo();
                        }
                        System.out.println("** Cay� CambiadorEscudo cuando ya ten�a escudo puesto");
                        numAleatorio = rand.nextInt(9) + 6 ; // Resultados entre 6 y 8
                        /***
                         * si llega a este punto es porque ten�a el escudo puesto, por eso se
                         * reinicia el proceso con el while pero esta vez quitando las opciones de
                         * escudo y gravedad normal.
                         ***/
                } // cierre del switch
            }
        }
        else if (puntaje >= 500) {
            numAleatorio = rand.nextInt(7) + 1; // Resultados entre 1 y 6
            System.out.println("** puntaje = " + puntaje + ", numAleatorio entre 1 y 6 = " + numAleatorio);
                switch (numAleatorio) {
                    case 6:
                        if (!(estadoHelicoptero instanceof EstadoDobleGravedad)) {
                            return crearCambiadorDobleGravedad();
                        }
                    case 5:
                    case 4:
                        if (!(estadoHelicoptero instanceof EstadoNormal || estadoHelicoptero instanceof EstadoConEscudo)) {
                            return  crearCambiadorGravedadNormal();
                        }
                    default:
                        if (!(estadoHelicoptero instanceof EstadoConEscudo)) {
                            return crearCambiadorEscudo();
                        }
                        System.out.println("** Cay� CambiadorEscudo cuando ya ten�a escudo puesto");
                        return crearCambiadorDobleGravedad();
                } // cierre del switch
        }
        return crearCambiadorEscudo();
    }

    public ObjetoDisparable crearMisil() {
        int numAleatorio;
        int puntaje = this.panelJuego.getHelicoptero().getPuntaje();
        IEstadoHelicoptero estadoHelicoptero = this.panelJuego.getHelicoptero().getEstado();
        numAleatorio = rand.nextInt(6) + 1 ; // Resultados entre 1 y 5
        System.out.println("** puntaje = " + puntaje + ", numAleatorio entre 1 y 6 = " + numAleatorio);

        if (puntaje >= 2500) {
            return crearMisilPerseguidor();
        }
        else if (puntaje >= 2000) {
            switch (numAleatorio) {
                case 5:
                case 4:
                case 3:
                case 2:
                    return crearMisilPerseguidor();
                default:
                    return crearMisilNormal();
            }
        }
        else if (puntaje >= 1500) {
            switch (numAleatorio) {
                case 5:
                case 4:
                case 3:
                    return crearMisilPerseguidor();
                default:
                    return crearMisilNormal();
            }
        }
        else if (puntaje >= 1000) {
            switch (numAleatorio) {
                case 5:
                case 4:
                    return crearMisilPerseguidor();
                default:
                    return crearMisilNormal();
            }
        }
        else if (puntaje >= 500) {
            switch (numAleatorio) {
                case 5:
                    return crearMisilPerseguidor();
                default:
                    return crearMisilNormal();
            }
        }
        return crearMisilNormal();
    }

}
