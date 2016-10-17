package ar.edu.itba.pod.server;

import ar.edu.itba.pod.ClienteEntradas;

import java.util.List;

/**
 * Created by lumarzo on 13/10/16.
 */
public class Recital {
    public enum State {A_CONFIRMAR, CONFIRMADO, AGOTADO, CANCELADO}

    private List<Entrada> entradas;
    private State state;
    private final String name;
    private final int confirmationNumber;
    private final int maxCapacity;
    private final int vipTicketsAmount;
    private int entradasTotal;

    public Recital(String name, int confirmationNumber, int maxCapacity, int vipTicketsAmount) {
        this.state = State.A_CONFIRMAR;
        this.name = name;
        this.confirmationNumber = confirmationNumber;
        this.maxCapacity = maxCapacity;
        this.vipTicketsAmount = vipTicketsAmount;
    }

    public State getState() {
        return this.state;
    }
    public String getName() {
        return name;
    }

    public void confirmar() {
        this.state = State.CONFIRMADO;
        entradas.stream()
                .filter((entrada -> entrada.getState() == Entrada.State.RESERVADA))
                .forEach((Entrada::confirmar));
    }

    public void cancelar() {
        this.state = State.CANCELADO;
        entradas.forEach((Entrada::cancelar));
    }

    public void cerrarVenta() {
        this.state = State.AGOTADO;
    }

    public void reservarEntrada(ClienteEntradas handler) {
        Entrada entrada;
        if (entradasTotal < vipTicketsAmount) {
            entrada = new EntradaVip(handler, this.name);
        } else {
            entrada = new EntradaRegular(handler, this.name);
        }
        entradasTotal++;
        entradas.add(entrada);
        if (this.state == State.CONFIRMADO) {
            entrada.confirmar();
        }
        if (this.entradasTotal >= this.confirmationNumber) {
            this.confirmar();
        }
        if (this.entradasTotal >= maxCapacity) {
            this.cerrarVenta();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recital)) return false;

        Recital recital = (Recital) o;

        return getName().equals(recital.getName());

    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}
