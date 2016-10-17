package ar.edu.itba.pod.server;

import ar.edu.itba.pod.ClienteEntradas;

/**
 * Created by lumarzo on 17/10/16.
 */
public abstract class Entrada {

    public enum State {UNASSIGNED, RESERVADA, CONFIRMADA}
    private String id;
    private State state;
    private ClienteEntradas handler;

    private String recital;
    public Entrada(ClienteEntradas handler, String recital) {
        this.handler = handler;
        this.state = State.UNASSIGNED;
    }

    public abstract void confirmar();

    public State getState() {
        return this.state;
    }

    public void confirmarEstado(){
        this.state = State.CONFIRMADA;
    }

    public void reservar() {
        handler.entradaReservada(recital);
        this.state = State.RESERVADA;
    }

    public void cancelar() {
        handler.recitalCancelado(recital);
    }

    public ClienteEntradas getHandler() {
        return handler;
    }

    public String getId() {
        return id;
    }

    public String getRecital() {
        return recital;
    }
}
