package com.gni.frmk.tools.addon.configuration;

import com.google.common.collect.Sets;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 07/03/11
 * Time: 10:12
 * To change this template use File | Settings | File Templates.
 */
public interface BuilderWithValidation<B extends BuilderWithValidation<B,T>,T> {

    T build() throws BuildException;

    T buildAndValidate() throws BuildException, ValidationException;

    B validate() throws ValidationException;

    public class BuildException extends RuntimeException {

    }

    public class ValidationException extends RuntimeException {
        private final Set<ConstraintViolation<?>> violations;

        public ValidationException(Set<ConstraintViolation<?>> violations) {
            super("validation exception");
            this.violations = violations;
        }

        public Set<ConstraintViolation<?>> getViolations() {
            return violations;
        }

        public ValidationException(Throwable cause) {
            super(cause);
            violations = Sets.newHashSet();
        }
    }
}
