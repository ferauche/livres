package br.com.livresbs.livres.scheduler;

import br.com.livresbs.livres.model.DataEntrega;
import br.com.livresbs.livres.model.StatusDataEntrega;
import br.com.livresbs.livres.repository.DataEntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class VerificarDatasEntrega {

    private static final String PERIODO_TODO_DIA_MEIA_NOITE = "0 0 0 ? * * *";

    @Autowired
    private DataEntregaRepository dataEntregaRepository;

    @Scheduled(cron = PERIODO_TODO_DIA_MEIA_NOITE)
    public void processarDatasEntrega() {

        List<DataEntrega> datasEntrega = dataEntregaRepository.findByStatus(StatusDataEntrega.ATIVA);

        LocalDate dataAtual = LocalDate.now();

        for (DataEntrega dataEntrega : datasEntrega) {

            if (!dataAtual.isBefore(dataEntrega.getPrazoLimitePedido()) || !dataAtual.isEqual(dataEntrega.getPrazoLimitePedido())) {

                dataEntrega.setStatus(StatusDataEntrega.FINALIZADA);

                // TODO implementar busca da próxima data de entrega e colocala ativa

            }

        }

    }

}
