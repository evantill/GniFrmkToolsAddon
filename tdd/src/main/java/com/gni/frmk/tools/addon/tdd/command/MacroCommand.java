package com.gni.frmk.tools.addon.tdd.command;

import com.gni.frmk.tools.addon.tdd.api.command.Command;
import com.gni.frmk.tools.addon.tdd.api.command.CommandContext;
import com.gni.frmk.tools.addon.tdd.api.command.CommandException;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

import java.util.Comparator;
import java.util.List;

import static com.gni.frmk.tools.addon.tdd.command.MacroCommand.Priority.NORMAL;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/08/11
 * Time: 18:31
 *
 * @author: e03229
 */
public class MacroCommand implements Command {
    public static enum Priority {
        LOWEST(1), BELOW_NORMAL(3), NORMAL(5), ABOVE_NORMAL(7), HIGHEST(10);
        final int executionOrder;

        Priority(int executionOrder) {
            this.executionOrder = executionOrder * (-1);
        }

        public int getExecutionOrder() {
            return executionOrder;
        }
    }

    enum PriorityComparator implements Comparator<Priority> {
        singleton;

        public int compare(Priority o1, Priority o2) {
            return ComparisonChain.start()
                                  .compare(o1.getExecutionOrder(), o2.getExecutionOrder())
                                  .result();
        }
    }

    private class PriorityCommand implements Comparable<PriorityCommand> {
        private final Priority priority;
        private final Command command;

        private PriorityCommand(Command command) {
            this.priority = NORMAL;
            this.command = command;
        }

        private PriorityCommand(Priority priority, Command command) {
            this.priority = priority;
            this.command = command;
        }

        public Priority getPriority() {
            return priority;
        }

        public Command getCommand() {
            return command;
        }

        @Override
        public int compareTo(PriorityCommand o) {
            return PriorityComparator.singleton.compare(getPriority(), o.getPriority());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PriorityCommand that = (PriorityCommand) o;
            return Objects.equal(getCommand(), that.getCommand())
                   && Objects.equal(getPriority(), that.getPriority());
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(getPriority(), getCommand());
        }
    }

    private final List<PriorityCommand> commands = Lists.newArrayList();

    public void add(Command command) {
        commands.add(new PriorityCommand(command));
    }

    public void add(Priority priority, Command command) {
        commands.add(new PriorityCommand(priority, command));
    }

    @Override
    public void execute(CommandContext context) throws CommandException {
        for (PriorityCommand command : orderedCommandForExecution()) {
            doExecute(context, command.getCommand());
        }
    }

    private Iterable<PriorityCommand> orderedCommandForExecution() {
        return Ordering.natural().immutableSortedCopy(commands);
    }

    protected void doExecute(CommandContext context, Command command) throws CommandException {
        command.execute(context);
    }

}
