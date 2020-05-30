package br.com.livresbs.livres.controller;

import br.com.livresbs.livres.config.properties.MessageProperty;
import br.com.livresbs.livres.dto.ErroCampoRequestDTO;
import br.com.livresbs.livres.dto.ErroValidacaoRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@ControllerAdvice
public class TratamentoErroValidacaoRequest extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageProperty messageProperty;

    private static String VALOR_NAO_RECEBIDO = "null";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {

        List<ErroCampoRequestDTO> erros = new LinkedList<>();

        ex.getBindingResult()
            .getFieldErrors()
            .forEach(fieldError -> {

                ErroCampoRequestDTO erro = ErroCampoRequestDTO.builder()
                    .campo(fieldError.getField())
                    .valor(fieldError.getRejectedValue().toString())
                    .mensagem(fieldError.getDefaultMessage())
                    .build();

                erros.add(erro);

            });

        ErroValidacaoRequestDTO erroValidacaoRequestDTO =
                ErroValidacaoRequestDTO.builder()
                    .body(erros)
                    .build();

        return new ResponseEntity<>(erroValidacaoRequestDTO, HttpStatus.BAD_REQUEST);

    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {

        ErroCampoRequestDTO erro = ErroCampoRequestDTO.builder()
                .campo(ex.getParameterName())
                .valor(VALOR_NAO_RECEBIDO)
                .mensagem(String.format(messageProperty.getMenssageParametroNulo()))
                .build();

        ErroValidacaoRequestDTO erroValidacaoRequestDTO =
                ErroValidacaoRequestDTO.builder()
                        .param(erro)
                        .build();

        return new ResponseEntity<>(erroValidacaoRequestDTO, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ErroValidacaoRequestDTO handleMissingRequestHeader(MissingRequestHeaderException ex) {

        ErroCampoRequestDTO erro = ErroCampoRequestDTO.builder()
                .campo(ex.getHeaderName())
                .valor(VALOR_NAO_RECEBIDO)
                .mensagem(String.format(messageProperty.getMenssagemHeaderNulo()))
                .build();

        return ErroValidacaoRequestDTO.builder()
                .header(erro)
                .build();

    }

}
