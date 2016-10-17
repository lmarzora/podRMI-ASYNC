package ar.edu.itba.pod;

import java.rmi.Remote;

/**
 * Created by lumarzo on 13/10/16.
 */
public interface ClienteEntradas extends Remote {
    void entradaReservada(String recital);
    void entradaVipConfirmada(String recital, String entrada);
    void entradaConfirmada(String recital, String entrada);
    void recitalAgotado(String recital);
    void recitalCancelado(String recital);
}
