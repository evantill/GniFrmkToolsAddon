package com.gni.frmk.tools.addon.tdd.command;

import com.gni.frmk.tools.addon.tdd.api.command.Command;
import com.gni.frmk.tools.addon.tdd.api.command.CommandListener;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/08/11
 * Time: 18:53
 *
 * @author: e03229
 */
public class CommandListeners {
    private enum ConsoleListener implements CommandListener {
        singleton;

        @Override
        public void commandDone(Command command) {
            trace(command, "execution done");
        }

        @Override
        public void commandFailed(Command command, Exception e) {
            trace(command, "execution failed");
        }

        @Override
        public void rollbackDone(Command command) {
            trace(command, "rollback done");
        }

        @Override
        public void rollbackFailed(Command command, Exception e) {
            trace(command, "rollback failed");

        }

        private void trace(Command command, String result) {
            String msg = String.format("command %s %s", command, result);
            System.err.println(msg);
        }

    }

    private enum NullListener implements CommandListener {
        singleton;

        @Override
        public void commandDone(Command command) {
        }

        @Override
        public void commandFailed(Command command, Exception e) {
        }

        @Override
        public void rollbackDone(Command command) {
        }

        @Override
        public void rollbackFailed(Command command, Exception e) {
        }
    }

    private CommandListeners() {}

    public static CommandListener createNullListener() {
        return NullListener.singleton;
    }

    public static CommandListener createConsoleListener() {
        return ConsoleListener.singleton;
    }
}
