package com.gni.frmk.tools.addon.tdd.command;

import com.gni.frmk.tools.addon.tdd.api.Command;
import com.gni.frmk.tools.addon.tdd.api.CommandException;
import com.gni.frmk.tools.addon.tdd.api.CommandListener;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/08/11
 * Time: 18:53
 *
 * @author: e03229
 */
public class CommandListeners {
    private enum NullListener implements CommandListener {
        singleton;

        @Override
        public void commandDone(Command command) {
        }

        @Override
        public void commandFailed(Command command, CommandException e) {
        }

        @Override
        public void rollbackDone(Command command) {
        }

        @Override
        public void rollbackFailed(Command command, CommandException e) {
        }
    }

    private CommandListeners() {}

    public static CommandListener createNullListener() {
        return NullListener.singleton;
    }
}
