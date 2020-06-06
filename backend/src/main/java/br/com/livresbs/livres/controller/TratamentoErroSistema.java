package br.com.livresbs.livres.controller;

import br.com.livresbs.livres.dto.ErroSistemaDTO;
import br.com.livresbs.livres.exception.LivresException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TratamentoErroSistema {

    @ExceptionHandler(LivresException.class)
    @ResponseBody
    public ErroSistemaDTO handleLivresException(LivresException ex) {

        return ErroSistemaDTO.builder()
                .mensagem(ex.getMessage())
                .build();

    }

}
