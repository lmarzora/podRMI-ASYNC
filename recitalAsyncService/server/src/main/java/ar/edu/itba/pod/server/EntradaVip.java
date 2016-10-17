package ar.edu.itba.pod.server;

import ar.edu.itba.pod.ClienteEntradas;

/**
 * Created by lumarzo on 17/10/16.
 */
public class EntradaVip extends Entrada {

    public EntradaVip(ClienteEntradas handler, String recital) {
        super(handler, recital);
    }

    @Override
    public void confirmar() {
        this.getHandler().entradaVipConfirmada(this.getRecital(),this.getId());
        this.confirmarEstado();
    }
}
