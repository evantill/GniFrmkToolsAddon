package com.gni.frmk.tools.addon.model;

import com.google.common.collect.Sets;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 01/06/11
 * Time: 10:32
 *
 * @author: e03229
 */
public interface BuilderWithValidation<B extends BuilderWithValidation<B, T>, T>
        extends Builder<B, T> {
    @Override
    T build() throws BuildException, ValidationException;

    B validate() throws ValidationException;

    public static class ValidationException extends RuntimeException {
        private final Set<ConstraintViolation> violations;

        public ValidationException(Set<? extends ConstraintViolation> violations) {
            super("validation exception");
            this.violations = Sets.newHashSet(violations);
        }

        public Set<ConstraintViolation> getViolations() {
            return violations;
        }

        public ValidationException(Throwable cause) {
            super(cause);
            violations = Sets.newHashSet();
        }

        @Override
        public String getMessage() {
            StringBuilder builder = new StringBuilder();
            for (ConstraintViolation violation : violations) {
                builder
                        .append(violation.getPropertyPath())
                        .append('-')
                        .append(violation.getMessage())
                        .append('\n');
            }
            return builder.toString();
        }
    }
}
