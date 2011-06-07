package com.gni.frmk.tools.addon.model;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 07/06/11
 * Time: 17:42
 *
 * @author: e03229
 */
public abstract class BuilderWithJsr303Validation
        <B extends BuilderWithJsr303Validation<B, T>, T>
        implements BuilderWithValidation<B, T> {

    @Override
    public final B validate() throws ValidationException {
        return raiseExceptionIfNotValid(self());
    }

    public <O> O raiseExceptionIfNotValid(O object) throws ValidationException {
        Set<ConstraintViolation<O>> violations = validate(object);
        if (violations.size() > 0) {
            throw new ValidationException(violations);
        }
        return object;
    }


    protected <O> Set<ConstraintViolation<O>> validate(O object) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(object);
    }
}
