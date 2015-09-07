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
        listaComandos.add(new CrearCambiadorEscudo(panelJuego));
        listaComandos.add(new CrearCambiadorEscudo(panelJuego));
        listaComandos.add(new CrearCambiadorEscudo(panelJuego));
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

    private abstract class Comando {
        private PanelJuego panelJuego;
        Comando(PanelJuego panelJuego){this.panelJuego = panelJuego;}
        abstract CambiadorDeEstados ejecutar();
        PanelJuego getPanelJuego() {return this.panelJuego;}
    }
    private class CrearCambiadorEscudo extends Comando {
        CrearCambiadorEscudo(PanelJuego panelJuego){super(panelJuego);}
        public CambiadorEscudo ejecutar() {
            return new CambiadorEscudo(this.getPanelJuego().WIDTH,(int)(rand.nextDouble()*this.getPanelJuego().HEIGHT), this.getPanelJuego().HEIGHT);
        }
    }
    private class CrearCambiadorDobleGravedad extends Comando {
        CrearCambiadorDobleGravedad(PanelJuego panelJuego){super(panelJuego);}
        public CambiadorDobleGravedad ejecutar() {
            return new CambiadorDobleGravedad(this.getPanelJuego().WIDTH,(int)(rand.nextDouble()*this.getPanelJuego().HEIGHT), this.getPanelJuego().HEIGHT);
        }
    }
    private class CrearCambiadorGravedadNormal extends Comando {
        CrearCambiadorGravedadNormal(PanelJuego panelJuego){super(panelJuego);}
        public CambiadorGravedadNormal ejecutar() {
            return new CambiadorGravedadNormal(this.getPanelJuego().WIDTH,(int)(rand.nextDouble()*this.getPanelJuego().HEIGHT), this.getPanelJuego().HEIGHT);
        }
    }
    private class CrearCambiadorGravedadInversa extends Comando {
        CrearCambiadorGravedadInversa(PanelJuego panelJuego){super(panelJuego);}
        public CambiadorGravedadInversa ejecutar() {
            return new CambiadorGravedadInversa(this.getPanelJuego().WIDTH,(int)(rand.nextDouble()*this.getPanelJuego().HEIGHT), this.getPanelJuego().HEIGHT);
        }
    }
    private class CrearCambiadorTripleGravedad extends Comando {
        CrearCambiadorTripleGravedad(PanelJuego panelJuego){super(panelJuego);}
        public CambiadorTripleGravedad ejecutar() {
            return new CambiadorTripleGravedad(this.getPanelJuego().WIDTH,(int)(rand.nextDouble()*this.getPanelJuego().HEIGHT), this.getPanelJuego().HEIGHT);
        }
    }
    private class CrearCambiadorCuadrupleGravedad extends Comando {
        CrearCambiadorCuadrupleGravedad(PanelJuego panelJuego){super(panelJuego);}
        public CambiadorCuadrupleGravedad ejecutar() {
            return new CambiadorCuadrupleGravedad(this.getPanelJuego().WIDTH,(int)(rand.nextDouble()*this.getPanelJuego().HEIGHT), this.getPanelJuego().HEIGHT);
        }
    }

    public CambiadorDeEstados crearCambiador() {
        int numAleatorio;
        int puntaje = this.panelJuego.getHelicoptero().getPuntaje();
        IEstadoHelicoptero estadoHelicoptero = this.panelJuego.getHelicoptero().getEstado();

        // Agregar comandos seg�n el puntaje al que vaya llegando
        switch (puntaje) {
            case 500:
                if (listaComandos.size() == 3) { // Esta comprobaci�n es necesaria ya que este m�todo se puede ejecutar varias veces cuando se est� en este puntaje, ya que hay varios frames por puntaje.
                    listaComandos.add(new CrearCambiadorDobleGravedad(this.panelJuego));
                    listaComandos.add(new CrearCambiadorGravedadNormal(this.panelJuego));
                    listaComandos.add(new CrearCambiadorGravedadNormal(this.panelJuego));
                    listaComandos.add(new CrearCambiadorEscudo(panelJuego));
                }
                break;
            case 1000:
                if (listaComandos.size() == 7) {
                    listaComandos.add(new CrearCambiadorDobleGravedad(this.panelJuego));
                    listaComandos.add(new CrearCambiadorGravedadInversa(this.panelJuego));
                    listaComandos.add(new CrearCambiadorEscudo(panelJuego));
                }
                break;
            case 1500:
                if (listaComandos.size() == 10) {
                    listaComandos.add(new CrearCambiadorGravedadInversa(this.panelJuego));
                    listaComandos.add(new CrearCambiadorTripleGravedad(this.panelJuego));
                    listaComandos.add(new CrearCambiadorEscudo(panelJuego));
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
        if (estadoHelicoptero instanceof EstadoNormal) {
            for (int i=listaComandosFiltrados.size()-1; i>=0; i--) {
                if (listaComandosFiltrados.get(i) instanceof CrearCambiadorGravedadNormal) {
                    listaComandosFiltrados.remove(i);
                }
            }
        }
        else if (estadoHelicoptero instanceof EstadoConEscudo) {
            for (int i=listaComandosFiltrados.size()-1; i>=0; i--) {
                if (listaComandosFiltrados.get(i) instanceof CrearCambiadorGravedadNormal
                        || listaComandosFiltrados.get(i) instanceof CrearCambiadorEscudo) {
                    listaComandosFiltrados.remove(i);
                }
            }
        }
        else if (estadoHelicoptero instanceof EstadoDobleGravedad) {
            for (int i=listaComandosFiltrados.size()-1; i>=0; i--) {
                if (listaComandosFiltrados.get(i) instanceof CrearCambiadorDobleGravedad) {
                    listaComandosFiltrados.remove(i);
                }
            }
        }
        else if (estadoHelicoptero instanceof EstadoGravedadInversa) {
            for (int i=listaComandosFiltrados.size()-1; i>=0; i--) {
                if (listaComandosFiltrados.get(i) instanceof CrearCambiadorGravedadInversa) {
                    listaComandosFiltrados.remove(i);
                }
            }
        }
        else if (estadoHelicoptero instanceof EstadoTripleGravedad) {
            for (int i=listaComandosFiltrados.size()-1; i>=0; i--) {
                if (listaComandosFiltrados.get(i) instanceof CrearCambiadorTripleGravedad) {
                    listaComandosFiltrados.remove(i);
                }
            }
        }
        else if (estadoHelicoptero instanceof EstadoCuadrupleGravedad) {
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
            return (new CrearCambiadorEscudo(this.panelJuego)).ejecutar();
        }

    }

    public ObjetoDisparable crearMisil() {
        int numAleatorio;
        int puntaje = this.panelJuego.getHelicoptero().getPuntaje();
        IEstadoHelicoptero estadoHelicoptero = this.panelJuego.getHelicoptero().getEstado();
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
        listaComandos.add(new CrearCambiadorEscudo(panelJuego));
        listaComandos.add(new CrearCambiadorEscudo(panelJuego));
        listaComandos.add(new CrearCambiadorEscudo(panelJuego));
    }

}
