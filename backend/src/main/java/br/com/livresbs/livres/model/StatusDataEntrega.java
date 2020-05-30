package br.com.livresbs.livres.model;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum StatusDataEntrega {

    NAO_ATIVA(1),
    ATIVA(2),
    FINALIZADA(3);

    private Integer value;

    private StatusDataEntrega(Integer value) {
        this.value = value;
    }

    public static StatusDataEntrega of(Integer value) {
        return Stream.of(StatusDataEntrega.values())
                .filter(s -> s.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
