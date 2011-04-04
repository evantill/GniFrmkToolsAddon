package com.gni.frmk.tools.addon;

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

    B self();

    T build() throws BuildException, ValidationException;

    B validate() throws ValidationException;

    public class BuildException extends RuntimeException {

    }

    public class ValidationException extends RuntimeException {
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
            for(ConstraintViolation violation : violations){
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
