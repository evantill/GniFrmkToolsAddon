package com.gni.frmk.tools.addon;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 30/03/11
 * Time: 14:07
 *
 * @author: e03229
 */
public abstract class BuilderWithJsr303Validation<B extends BuilderWithJsr303Validation<B, T>, T> implements BuilderWithValidation<B, T> {

    public <O> Set<ConstraintViolation<O>> validate(O object) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(object);
    }

    public <O> O raiseExceptionIfNotValid(O object) throws ValidationException {
        Set<ConstraintViolation<O>> violations = validate(object);
        if (violations.size() > 0) {
            throw new ValidationException(violations);
        }
        return object;
    }

    @Override
    final public T build() throws BuildException, ValidationException {
        T object = buildObjectBeforeValidation();
        return raiseExceptionIfNotValid(object);
    }

    protected abstract T buildObjectBeforeValidation();

    @Override
    public B validate() throws ValidationException {
        return raiseExceptionIfNotValid(self());
    }

}
