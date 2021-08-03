package br.com.zup.desafiomercadolivre.desafiomercadolivre.common.annotations;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue,String> {

    private final EntityManager manager;

    private Class<?> domainClass;
    private String fieldName;

    public UniqueValueValidator(EntityManager entityManager) {
        this.manager = entityManager;
    }

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        this.domainClass = constraintAnnotation.domainClass();
        this.fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        Query query = manager.createQuery("select 1 from " + domainClass.getName() + " where " + fieldName + "=:value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();
        Assert.state(list.size() <= 1, "Foi encontrado mais de um " + domainClass + "com o atributo" + fieldName + " = " + value);

        return list.isEmpty();
    }
}
