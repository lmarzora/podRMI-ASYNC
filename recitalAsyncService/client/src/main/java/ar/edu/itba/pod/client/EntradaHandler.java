package ar.edu.itba.pod.client;

import ar.edu.itba.pod.ClienteEntradas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lumarzo on 17/10/16.
 */
public class EntradaHandler implements ClienteEntradas {
    private static Logger logger = LoggerFactory.getLogger(Client.class);

    @Override
    public void entradaReservada(String recital) {
        logger.info("Entrada reservada para el recital {}",recital);
    }

    @Override
    public void entradaVipConfirmada(String recital, String entrada) {
        logger.info("Entrada Vip {} confirmada para el recital {}",entrada, recital);
    }

    @Override
    public void entradaConfirmada(String recital, String entrada) {
        logger.info("Entrada {} confirmada para el recital {}",entrada, recital);
    }

    @Override
    public void recitalAgotado(String recital) {
        logger.info("Recital {} agotado",recital);

    }

    @Override
    public void recitalCancelado(String recital) {
        logger.info("Recital {} cancelado",recital);
    }
}
