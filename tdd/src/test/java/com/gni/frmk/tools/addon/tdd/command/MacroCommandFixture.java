package com.gni.frmk.tools.addon.tdd.command;

import com.gni.frmk.tools.addon.tdd.api.command.Command;
import com.gni.frmk.tools.addon.tdd.api.command.CommandContext;
import com.gni.frmk.tools.addon.tdd.api.command.CommandException;
import com.gni.frmk.tools.addon.tdd.api.command.UndoableCommand;
import org.mockito.InOrder;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;

import java.util.Arrays;
import java.util.List;

import static com.google.common.collect.Lists.reverse;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/08/11
 * Time: 14:38
 *
 * @author: e03229
 */
public abstract class MacroCommandFixture {

    @Mock
    protected Command cmd1;
    @Mock
    protected Command cmd2;
    @Mock
    protected Command cmd3;
    @Mock
    protected UndoableCommand undoCmd1;
    @Mock
    protected UndoableCommand undoCmd2;
    @Mock
    protected UndoableCommand undoCmd3;
    @Mock
    protected UndoableCommand undoCmd4;

    @Mock
    protected CommandContext context;

    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    protected void verifyExecutionInOrder(Command... commands) throws CommandException {
        InOrder orderedExecute = inOrder((Object[]) commands);
        for (Command cmd : commands) {
            orderedExecute.verify(cmd).execute(Matchers.<CommandContext>eq(context));
        }
    }

    protected void verifyRollbackInOrder(UndoableCommand... commands) throws CommandException {
        InOrder orderedExecute = inOrder((Object[]) commands);
        List<UndoableCommand> cmds = Arrays.asList(commands);
        List<UndoableCommand> failedCmds = cmds.subList(0, cmds.size() - 1);
        for (UndoableCommand cmd : commands) {
            orderedExecute.verify(cmd).execute(Matchers.<CommandContext>eq(context));
        }
        for (UndoableCommand cmd : reverse(failedCmds)) {
            orderedExecute.verify(cmd).rollback(Matchers.<CommandContext>eq(context));
        }
    }

    protected void verifyNoMoreCommandInteractions(){
        verifyNoMoreInteractions(cmd1,cmd2,cmd3,undoCmd1,undoCmd2,undoCmd3,undoCmd4);
    }
}
