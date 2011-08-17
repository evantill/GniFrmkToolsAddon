package com.gni.frmk.tools.addon.tdd.command;

import com.gni.frmk.tools.addon.tdd.api.command.CommandContext;
import com.gni.frmk.tools.addon.tdd.api.command.CommandException;
import com.gni.frmk.tools.addon.tdd.api.command.CommandListener;
import org.testng.annotations.Test;

import static com.gni.frmk.tools.addon.tdd.command.MacroCommand.Priority.HIGHEST;
import static org.fest.assertions.Fail.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/08/11
 * Time: 14:38
 *
 * @author: e03229
 */
public class RollbackMacroCommandTest extends MacroCommandFixture {

    @Test
    public void testSequencialExecute() throws Exception {
        RollbackMacroCommand macro = new RollbackMacroCommand();
        macro.add(undoCmd1);
        macro.add(undoCmd2);
        macro.add(undoCmd3);
        macro.execute(context);
        verifyExecutionInOrder(undoCmd1, undoCmd2, undoCmd3);
        verifyNoMoreCommandInteractions();
    }

    @Test
    public void testPriorityExecute() throws Exception {
        RollbackMacroCommand macro = new RollbackMacroCommand();
        macro.add(undoCmd1);
        macro.add(undoCmd2);
        macro.add(HIGHEST, undoCmd3);
        macro.execute(context);
        verifyExecutionInOrder(undoCmd3, undoCmd1, undoCmd2);
        verifyNoMoreCommandInteractions();
    }

    @Test
    public void testSequencialRollback() throws Exception {
        doThrow(new IllegalStateException("error during execute of undoCmd3"))
                .when(undoCmd3)
                .execute(any(CommandContext.class));

        CommandListener listener = CommandListeners.createConsoleListener();

        RollbackMacroCommand macro = new RollbackMacroCommand();
        macro.add(CommandTracer.decorate(undoCmd1, listener));
        macro.add(CommandTracer.decorate(undoCmd2, listener));
        macro.add(CommandTracer.decorate(undoCmd3, listener));
        macro.add(CommandTracer.decorate(undoCmd4, listener));
        try {
            macro.execute(context);
            fail("CommandException must be fired");
        } catch (CommandException ignore) {}
        verifyRollbackInOrder(undoCmd1, undoCmd2, undoCmd3);
        verifyNoMoreCommandInteractions();
    }

    @Test
    public void testPriorityRollback() throws Exception {
        doThrow(new IllegalStateException("error during execute of undoCmd2"))
                .when(undoCmd2)
                .execute(any(CommandContext.class));
        RollbackMacroCommand macro = new RollbackMacroCommand();
        macro.add(undoCmd1);
        macro.add(undoCmd2);
        macro.add(undoCmd3);
        macro.add(HIGHEST, undoCmd4);
        try {
            macro.execute(context);
            fail("CommandException must be fired");
        } catch (CommandException ignore) {}
        verifyRollbackInOrder(undoCmd4, undoCmd1, undoCmd2);
        verifyNoMoreCommandInteractions();
    }
}
