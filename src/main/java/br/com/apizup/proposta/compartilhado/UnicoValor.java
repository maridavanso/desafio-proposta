package br.com.apizup.proposta.compartilhado;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = {UnicoValorValido.class})
@Target({ FIELD})
@Retention(RUNTIME)
public @interface UnicoValor {
	
	String message() default "{com.bancozup.beanvalidation.unicovalor}";
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
	String fieldName();
	Class<?> domainClass();
}



