package br.com.livresbs.livres.config.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource("application.properties")
@Configuration
@Data
public class ApplicationProperty {

    @Value("${quantidade.itens.pagina}")
    private Integer quantidadeIntesPagina;

}
