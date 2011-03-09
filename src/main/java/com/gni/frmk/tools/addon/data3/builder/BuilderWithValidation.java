package com.gni.frmk.tools.addon.data3.builder;

/**
 * Created by IntelliJ IDEA.
 * User: e03229
 * Date: 07/03/11
 * Time: 10:12
 * To change this template use File | Settings | File Templates.
 */
public interface BuilderWithValidation<T> {

    T build() throws BuildException;

    void validate() throws ValidationException;

    public class BuildException extends RuntimeException{

    }

    public class ValidationException extends RuntimeException{

        public ValidationException(Throwable throwable) {
            super(throwable);
        }
    }
}
