package com.gni.frmk.tools.addon.configuration;

import com.gni.frmk.tools.addon.configuration.components.AdapterConnection;
import com.google.common.collect.Lists;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Date: 09/03/11
 * Time: 18:15
 *
 * @author: e03229
 */
public class Configuration {

    @Valid
    private final List<AdapterConnection> adapterConnections;

    public Configuration(Builder builder) {
        adapterConnections = builder.adapterConnections;
    }

    public List<AdapterConnection> getAdapterConnections() {
        return Collections.unmodifiableList(adapterConnections);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder implements BuilderWithValidation<Configuration> {
        private List<AdapterConnection> adapterConnections = Lists.newArrayList();

        public Builder addAdapterConnection(AdapterConnection connection) {
            adapterConnections.add(connection);
            return this;
        }

        @Override
        public Configuration build() throws BuildException {
            return new Configuration(this);
        }

        @Override
        public Configuration buildAndValidate() throws BuildException, ValidationException {
            Configuration cnf = build();
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Configuration>> constraintViolations = validator.validate(cnf);
            if (constraintViolations.size() > 0) {
                //TODO implementer gestion exception
                throw new RuntimeException("todo");
                //throw new ValidationException(constraintViolations);
            }
            return cnf;
        }

        @Override
        public void validate() throws ValidationException {
            //TODO validate
        }
    }

}
