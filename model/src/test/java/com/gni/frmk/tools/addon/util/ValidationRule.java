package com.gni.frmk.tools.addon.util;

import org.junit.rules.ExternalResource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 15:23
 *
 * @author: e03229
 */
public class ValidationRule extends ExternalResource {

    public <T> Set<ConstraintViolation<T>> validate(T cnf) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(cnf);
    }

    public <T> void raiseExceptionIfInvalid(T cnf) {
        Set<ConstraintViolation<T>> violations = validate(cnf);
        if (violations.size() > 0) {
            throw new RuntimeException(violations.toString());
        }
    }
}
