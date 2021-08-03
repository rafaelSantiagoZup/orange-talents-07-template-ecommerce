package br.com.zup.desafiomercadolivre.desafiomercadolivre.configuration.handlers;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationHandler {

    public ValidationHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    private final MessageSource messageSource;

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public List<ValidationErrorsOutputDto> validationError(MethodArgumentNotValidException exception){
        List<ValidationErrorsOutputDto> dto = new ArrayList<ValidationErrorsOutputDto>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ValidationErrorsOutputDto erro = new ValidationErrorsOutputDto(e.getField(), mensagem);
            dto.add(erro);
        });
        return dto;
    }
}
