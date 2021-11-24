package com.utfpf.delivery.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.utfpr.delivery.dto.ExceptionDTO;
import com.utfpr.delivery.exception.NotFoundException;


@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(NotFoundException.class)
	protected ResponseEntity<Object> treatNotFoundException(NotFoundException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		ExceptionDTO exceptionDto = new ExceptionDTO();
		exceptionDto.setStatus(status.value());
		exceptionDto.setMensagem(ex.getMessage());
		
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
