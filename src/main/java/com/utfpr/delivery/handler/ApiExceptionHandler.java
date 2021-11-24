package com.utfpr.delivery.handler;

import java.util.List;
import java.util.stream.Collectors;

import javax.xml.ws.spi.http.HttpHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.utfpr.delivery.dto.ExceptionDTO;
import com.utfpr.delivery.dto.ExceptionDTO.Validacao;
import com.utfpr.delivery.exception.NotFoundException;


@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(NotFoundException.class)
	protected ResponseEntity<Object> treatNotFoundException(NotFoundException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		ExceptionDTO exceptionDto = new ExceptionDTO();
		exceptionDto.setStatus(status.value());
		exceptionDto.setMensagem(ex.getMessage());
		
		return handleExceptionInternal(ex, exceptionDto, null, status, request);
		
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
		
		BindingResult bindingResult = ex.getBindingResult();
		
		List<Validacao> validacoes = bindingResult.getFieldErrors().stream()
			.map(field -> {
				
				String campo = field.getField();
				
				String erro = messageSource.getMessage(field, LocaleContextHolder.getLocale());
				
				ExceptionDTO.Validacao validacao = new ExceptionDTO.Validacao();
				validacao.setCampo(campo);
				validacao.setErro(erro);
				
				return validacao;
				
			}).collect(Collectors.toList());
		
		ExceptionDTO exceptionDto = new ExceptionDTO();
		exceptionDto.setStatus(status.value());
		exceptionDto.setMensagem("Alguns campos estão inválidos!");
		exceptionDto.setValidacoes(validacoes);
		
		return handleExceptionInternal(ex, exceptionDto, null, status, request);
		
	}
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> treatUncaughtException(Exception ex, WebRequest request){
		
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		
		ExceptionDTO exceptionDto = new ExceptionDTO();
		exceptionDto.setStatus(status.value());
		exceptionDto.setMensagem("Ocorrreu um erro inesperado no programa. Favor contatar o suporte !");
		
		return handleExceptionInternal(ex, exceptionDto, null, status, request);
		
	}
}