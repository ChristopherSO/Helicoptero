package com.patrones.helicoptero;

import android.graphics.BitmapFactory;
import com.patrones.laserlightgame.R;

import java.util.ArrayList;
import java.util.Random;

import framework.CambiadorDeEstados;
import framework.ObjetoDisparable;


/**
 * Created by suarezch on 22/08/2015.
 */
public class Fabrica {

    // Atributos y relaciones
    private Random rand;
    private PanelJuego panelJuego;
    private ArrayList<Comando> listaComandos;


    // Constructor
    public Fabrica(PanelJuego panelJuego) {
        this.rand = new Random();
        this.panelJuego = panelJuego;
        this.listaComandos = new ArrayList<Comando>();
        listaComandos.add(new CrearCambiadorEscudoSimple(panelJuego));
        listaComandos.add(new CrearCambiadorEscudoSimple(panelJuego));
        listaComandos.add(new CrearCambiadorEscudoSimple(panelJuego));
    }

    // Métodos
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
                rand.nextInt(4) + 1 // número aleatorio entre 1 y 3
        );
    }

    public Humo crearHumo() {
        return new Humo(this.panelJuego.getHelicoptero().getX(),
                this.panelJuego.getHelicoptero().getY());
    }

    private abstract class Comando {
        private PanelJuego panelJuego;
        Comando(PanelJuego panelJuego){this.panelJuego = panelJuego;}
        abstract CambiadorDeEstados ejecutar();
        PanelJuego getPanelJuego() {return this.panelJuego;}
    }
    private class CrearCambiadorEscudoSimple extends Comando {
        CrearCambiadorEscudoSimple(PanelJuego panelJuego){super(panelJuego);}
        public CambiadorEscudoSimple ejecutar() {
            return new CambiadorEscudoSimple(this.getPanelJuego().WIDTH,(int)(rand.nextDouble()*this.getPanelJuego().HEIGHT), this.getPanelJuego());
        }
    }
    private class CrearCambiadorEscudoDoble extends Comando {
        CrearCambiadorEscudoDoble(PanelJuego panelJuego){super(panelJuego);}
        public CambiadorEscudoPlus ejecutar() {
            return new CambiadorEscudoPlus(this.getPanelJuego().WIDTH,(int)(rand.nextDouble()*this.getPanelJuego().HEIGHT), this.getPanelJuego());
        }
    }
    private class CrearCambiadorDobleGravedad extends Comando {
        CrearCambiadorDobleGravedad(PanelJuego panelJuego){super(panelJuego);}
        public CambiadorGravedadDoble ejecutar() {
            return new CambiadorGravedadDoble(this.getPanelJuego().WIDTH,(int)(rand.nextDouble()*this.getPanelJuego().HEIGHT), this.getPanelJuego());
        }
    }
    private class CrearCambiadorGravedadNormal extends Comando {
        CrearCambiadorGravedadNormal(PanelJuego panelJuego){super(panelJuego);}
        public CambiadorGravedadNormal ejecutar() {
            return new CambiadorGravedadNormal(this.getPanelJuego().WIDTH,(int)(rand.nextDouble()*this.getPanelJuego().HEIGHT), this.getPanelJuego());
        }
    }
    private class CrearCambiadorGravedadInversa extends Comando {
        CrearCambiadorGravedadInversa(PanelJuego panelJuego){super(panelJuego);}
        public CambiadorGravedadInversa ejecutar() {
            return new CambiadorGravedadInversa(this.getPanelJuego().WIDTH,(int)(rand.nextDouble()*this.getPanelJuego().HEIGHT), this.getPanelJuego());
        }
    }
    private class CrearCambiadorTripleGravedad extends Comando {
        CrearCambiadorTripleGravedad(PanelJuego panelJuego){super(panelJuego);}
        public CambiadorGravedadTriple ejecutar() {
            return new CambiadorGravedadTriple(this.getPanelJuego().WIDTH,(int)(rand.nextDouble()*this.getPanelJuego().HEIGHT), this.getPanelJuego());
        }
    }
    private class CrearCambiadorCuadrupleGravedad extends Comando {
        CrearCambiadorCuadrupleGravedad(PanelJuego panelJuego){super(panelJuego);}
        public CambiadorGravedadCuadruple ejecutar() {
            return new CambiadorGravedadCuadruple(this.getPanelJuego().WIDTH,(int)(rand.nextDouble()*this.getPanelJuego().HEIGHT), this.getPanelJuego());
        }
    }

    public CambiadorDeEstados crearCambiador() {
        int numAleatorio;
        int puntaje = this.panelJuego.getHelicoptero().getPuntaje();
        IGravedad estadoHelicoptero = this.panelJuego.getHelicoptero().getGravedad();

        // Agregar comandos según el puntaje al que vaya llegando
        switch (puntaje) {
            case 500:
                if (listaComandos.size() == 3) { // Esta comprobación es necesaria ya que este método se puede ejecutar varias veces cuando se está en este puntaje, ya que hay varios frames por puntaje.
                    listaComandos.add(new CrearCambiadorDobleGravedad(this.panelJuego));
                    listaComandos.add(new CrearCambiadorGravedadNormal(this.panelJuego));
                    listaComandos.add(new CrearCambiadorGravedadNormal(this.panelJuego));
                    listaComandos.add(new CrearCambiadorEscudoSimple(panelJuego));
                }
                break;
            case 1000:
                if (listaComandos.size() == 7) {
                    listaComandos.add(new CrearCambiadorDobleGravedad(this.panelJuego));
                    listaComandos.add(new CrearCambiadorGravedadInversa(this.panelJuego));
                    listaComandos.add(new CrearCambiadorEscudoSimple(panelJuego));
                }
                break;
            case 1500:
                if (listaComandos.size() == 10) {
                    listaComandos.add(new CrearCambiadorGravedadInversa(this.panelJuego));
                    listaComandos.add(new CrearCambiadorTripleGravedad(this.panelJuego));
                    listaComandos.add(new CrearCambiadorEscudoSimple(panelJuego));
                }
                break;
            case 2000:
                if (listaComandos.size() == 13) {
                    listaComandos.add(new CrearCambiadorTripleGravedad(this.panelJuego));
                    listaComandos.add(new CrearCambiadorCuadrupleGravedad(this.panelJuego));
                }
                break;
        }

        // Remover los comandos que no apliquen para el estado en el que se encuentra
        ArrayList<Comando> listaComandosFiltrados = new ArrayList<Comando>(listaComandos);
        if (estadoHelicoptero instanceof GravedadNormal) {
            for (int i=listaComandosFiltrados.size()-1; i>=0; i--) {
                if (listaComandosFiltrados.get(i) instanceof CrearCambiadorGravedadNormal) {
                    listaComandosFiltrados.remove(i);
                }
            }
        }
        else if (estadoHelicoptero instanceof EscudoSimple) {
            for (int i=listaComandosFiltrados.size()-1; i>=0; i--) {
                if (listaComandosFiltrados.get(i) instanceof CrearCambiadorGravedadNormal
                        || listaComandosFiltrados.get(i) instanceof CrearCambiadorEscudoSimple) {
                    listaComandosFiltrados.remove(i);
                }
            }
        }
        else if (estadoHelicoptero instanceof GravedadDoble) {
            for (int i=listaComandosFiltrados.size()-1; i>=0; i--) {
                if (listaComandosFiltrados.get(i) instanceof CrearCambiadorDobleGravedad) {
                    listaComandosFiltrados.remove(i);
                }
            }
        }
        else if (estadoHelicoptero instanceof GravedadInversa) {
            for (int i=listaComandosFiltrados.size()-1; i>=0; i--) {
                if (listaComandosFiltrados.get(i) instanceof CrearCambiadorGravedadInversa) {
                    listaComandosFiltrados.remove(i);
                }
            }
        }
        else if (estadoHelicoptero instanceof GravedadTriple) {
            for (int i=listaComandosFiltrados.size()-1; i>=0; i--) {
                if (listaComandosFiltrados.get(i) instanceof CrearCambiadorTripleGravedad) {
                    listaComandosFiltrados.remove(i);
                }
            }
        }
        else if (estadoHelicoptero instanceof GravedadCuadruple) {
            for (int i=listaComandosFiltrados.size()-1; i>=0; i--) {
                if (listaComandosFiltrados.get(i) instanceof CrearCambiadorCuadrupleGravedad) {
                    listaComandosFiltrados.remove(i);
                }
            }
        }

        if (listaComandosFiltrados.size() > 0) {
            numAleatorio = rand.nextInt(listaComandosFiltrados.size());
            return listaComandosFiltrados.get(numAleatorio).ejecutar();
        } else {
            return (new CrearCambiadorEscudoSimple(this.panelJuego)).ejecutar();
        }

    }

    public ObjetoDisparable crearMisil() {
        int numAleatorio;
        int puntaje = this.panelJuego.getHelicoptero().getPuntaje();
        IGravedad estadoHelicoptero = this.panelJuego.getHelicoptero().getGravedad();
        numAleatorio = rand.nextInt(6) + 1 ; // Resultados entre 1 y 5

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

    public void resetListaComandos() {
        this.listaComandos.clear();
        listaComandos.add(new CrearCambiadorEscudoSimple(panelJuego));
        listaComandos.add(new CrearCambiadorEscudoSimple(panelJuego));
        listaComandos.add(new CrearCambiadorEscudoSimple(panelJuego));
    }

}
