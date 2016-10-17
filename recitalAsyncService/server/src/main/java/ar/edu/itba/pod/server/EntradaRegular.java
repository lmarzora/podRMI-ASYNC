package ar.edu.itba.pod.server;

import ar.edu.itba.pod.ClienteEntradas;

/**
 * Created by lumarzo on 17/10/16.
 */
public class EntradaRegular extends Entrada {
    public EntradaRegular(ClienteEntradas handler, String recital) {
        super(handler, recital);
    }

    @Override
    public void confirmar() {
        this.getHandler().entradaConfirmada(this.getRecital(),this.getId());
        this.confirmarEstado();
    }
}
