package med.voll.api.infra.exception;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratadorDeErros {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity tratarErro404() {
		return ResponseEntity.notFound().build();		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
		var erros = ex.getFieldErrors();
		
		return ResponseEntity.badRequest().body(erros.stream().map(DadosErrorValidacao::new).toList());
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity tratarErro400(HttpMessageNotReadableException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
		
	}
	
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity tratarErrorBadCredencials() {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticacao");
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity tratarErroAcessoNegado() {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso Negado");
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity tratarErro500(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERRO: "+ex);
	}
	
    private record DadosErrorValidacao(String campo, String mensagem) {
    	public DadosErrorValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
    	}
    }

}
