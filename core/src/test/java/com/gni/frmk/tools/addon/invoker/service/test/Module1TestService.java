package com.gni.frmk.tools.addon.invoker.service.test;

import com.gni.frmk.tools.addon.invoker.api.Service;
import com.gni.frmk.tools.addon.invoker.api.ServiceContext;
import com.gni.frmk.tools.addon.invoker.api.ServiceException;
import com.gni.frmk.tools.addon.invoker.api.ServiceInput;
import com.gni.frmk.tools.addon.invoker.api.ServiceOutput;
import com.gni.frmk.tools.addon.invoker.service.test.Module1TestService.Input;
import com.gni.frmk.tools.addon.invoker.service.test.Module1TestService.Output;

/**
 * Created by IntelliJ IDEA.
 * Date: 21/06/11
 * Time: 17:25
 *
 * @author: e03229
 */
public class Module1TestService implements Service<Input, Output> {

    public static final class Input implements ServiceInput {
        private final String param1;
        private final String param2;

        public Input(String param1, String param2) {
            this.param1 = param1;
            this.param2 = param2;
        }

        public String getParam1() {
            return param1;
        }

        public String getParam2() {
            return param2;
        }
    }

    public static final class Output implements ServiceOutput {
        private final String concatMessage;

        public Output(String concatMessage) {
            this.concatMessage = concatMessage;
        }

        public String getConcatMessage() {
            return concatMessage;
        }
    }

    @Override
    public Output invoke(Input input, ServiceContext context) throws ServiceException {
        return new Output(String.format("%s+%s", input.getParam1(), input.getParam2()));
    }

    @Override
    public String getName() {
        return "Module1TestService";
    }
}
