package com.gni.frmk.tools.addon.tdd.command;

import com.gni.frmk.tools.addon.tdd.command.MacroCommand.Priority;
import com.gni.frmk.tools.addon.tdd.command.MacroCommand.PriorityComparator;
import com.google.common.collect.Ordering;
import org.testng.annotations.Test;

import java.util.List;

import static com.gni.frmk.tools.addon.tdd.command.MacroCommand.Priority.*;
import static com.google.common.collect.Lists.newArrayList;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Created by IntelliJ IDEA.
 * Date: 17/08/11
 * Time: 13:44
 *
 * @author: e03229
 */
public class MacroCommandTest extends MacroCommandFixture {

    @Test
    public void testSequencialExecute() throws Exception {
        MacroCommand macro = new MacroCommand();
        macro.add(cmd1);
        macro.add(cmd2);
        macro.add(cmd3);
        macro.execute(context);
        verifyExecutionInOrder(cmd1, cmd2, cmd3);
        verifyNoMoreInteractions(cmd1, cmd2, cmd3);
    }

    @Test(invocationCount = 3)
    public void testPriorityOrder() throws Exception {
        List<Priority> naturalOrder = newArrayList(Priority.values());
        List<Priority> randomOrder = Ordering.arbitrary().immutableSortedCopy(naturalOrder);
        List<Priority> actualOrder = Ordering.from(PriorityComparator.singleton).immutableSortedCopy(randomOrder);
        List<Priority> expectedOrder = newArrayList(HIGHEST,
                ABOVE_NORMAL,
                NORMAL,
                BELOW_NORMAL,
                LOWEST);
        assertThat(actualOrder).isEqualTo(expectedOrder);
    }

    @Test
    public void testPriorityExecute() throws Exception {
        MacroCommand macro = new MacroCommand();
        macro.add(cmd1);
        macro.add(cmd2);
        macro.add(HIGHEST, cmd3);
        macro.execute(context);
        verifyExecutionInOrder(cmd3, cmd1, cmd2);
        verifyNoMoreInteractions(cmd1, cmd2, cmd3);
    }

    @Test
    public void testComplexPriorityExecute() throws Exception {
        MacroCommand macro = new MacroCommand();
        macro.add(LOWEST, cmd1);
        macro.add(NORMAL, cmd2);
        macro.add(HIGHEST, cmd3);
        macro.execute(context);
        verifyExecutionInOrder(cmd3, cmd2, cmd1);
        verifyNoMoreInteractions(cmd1, cmd2, cmd3);
    }
}
