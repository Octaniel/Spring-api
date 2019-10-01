package com.octa.springapi.exceptionhandler;

import com.octa.springapi.service.exception.PesssoaInexistenteOuInativaException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class SpringExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    private MessageSource messageSource;
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        String messagemUsuario=messageSource.getMessage("menssagem.invalida",null, LocaleContextHolder.getLocale());
        String messagemDoDesenvolvedor=ex.getCause()==null?ex.toString():ex.getCause().toString();
        return handleExceptionInternal(ex,new Erro(messagemUsuario,messagemDoDesenvolvedor),headers,HttpStatus.BAD_REQUEST,request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        List<Erro> erros = criarListaDeErros(ex.getBindingResult());
        return handleExceptionInternal(ex,erros,headers,HttpStatus.BAD_REQUEST,request);
    }
    private List<Erro> criarListaDeErros(BindingResult bindingResult){
        List<Erro> erros =new ArrayList<>();
        for (FieldError fieldeerror:bindingResult.getFieldErrors()) {
            String messagemUsuario=messageSource.getMessage(fieldeerror,LocaleContextHolder.getLocale());
            String messagemDoDesenvolvedor=fieldeerror.toString();
            erros.add(new Erro(messagemUsuario,messagemDoDesenvolvedor));
        }
        return erros;
    }
    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<Object> handlerEmptyResultDataAccessException(EmptyResultDataAccessException ex,WebRequest request){
        String messagemUsuario=messageSource.getMessage("recurso.nao-encontrado",null, LocaleContextHolder.getLocale());
        String messagemDoDesenvolvedor=ex.toString();
        List<Erro> erros= Arrays.asList(new Erro(messagemUsuario,messagemDoDesenvolvedor));
        return handleExceptionInternal(ex,erros,new HttpHeaders(),HttpStatus.NOT_FOUND,request);
    }
    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> handlerDataIntegrityViolationException(DataIntegrityViolationException ex,WebRequest request){
        String messagemUsuario=messageSource.getMessage("recurso.operacao-nao-permitida",null, LocaleContextHolder.getLocale());
        String messagemDoDesenvolvedor= ExceptionUtils.getRootCauseMessage(ex);
        List<Erro> erros= Arrays.asList(new Erro(messagemUsuario,messagemDoDesenvolvedor));
        return handleExceptionInternal(ex,erros,new HttpHeaders(),HttpStatus.BAD_REQUEST,request);
    }
    @ExceptionHandler({PesssoaInexistenteOuInativaException.class})
    public ResponseEntity<Object> handlerPesssoaInexistenteOuInativaException(PesssoaInexistenteOuInativaException ex,WebRequest request){
        String messagemUsuario=messageSource.getMessage("pessoa.inexistente-ou-inativa",null, LocaleContextHolder.getLocale());
        String messagemDoDesenvolvedor= ExceptionUtils.getRootCauseMessage(ex);
        List<Erro> erros= Arrays.asList(new Erro(messagemUsuario,messagemDoDesenvolvedor));
        return handleExceptionInternal(ex,erros,new HttpHeaders(),HttpStatus.BAD_REQUEST,request);
    }
    public class Erro{
        private String messagemUsuario;
        private String messagemDoDesenvolvedor;

        public String getMessagemUsuario() {
            return messagemUsuario;
        }

        public void setMessagemUsuario(String messagemUsuario) {
            this.messagemUsuario = messagemUsuario;
        }

        public String getMessagemDoDesenvolvedor() {
            return messagemDoDesenvolvedor;
        }

        public void setMessagemDoDesenvolvedor(String messagemDoDesenvolvedor) {
            this.messagemDoDesenvolvedor = messagemDoDesenvolvedor;
        }

        public Erro(String messagemUsuario, String messagemDoDesenvolvedor) {
            this.messagemUsuario = messagemUsuario;
            this.messagemDoDesenvolvedor = messagemDoDesenvolvedor;
        }
    }
}
