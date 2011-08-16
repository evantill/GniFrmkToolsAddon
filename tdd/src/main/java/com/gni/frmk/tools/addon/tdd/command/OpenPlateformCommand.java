package com.gni.frmk.tools.addon.tdd.command;

import com.gni.frmk.tools.addon.tdd.api.Component;
import com.gni.frmk.tools.addon.tdd.api.ComponentVisitor;
import com.gni.frmk.tools.addon.tdd.api.command.Command;
import com.gni.frmk.tools.addon.tdd.api.command.CommandContext;
import com.gni.frmk.tools.addon.tdd.api.command.CommandException;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

import javax.annotation.Nullable;
import java.util.List;

import static com.google.common.collect.Iterables.filter;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/08/11
 * Time: 18:30
 *
 * @author: e03229
 */
public class OpenPlateformCommand implements Command {

    private final Component<?> target;

    private final Predicate<Component<?>> componentFilter = new Predicate<Component<?>>() {
        @Override
        public boolean apply(@Nullable Component<?> input) {
            return input != null;
        }
    };

    public OpenPlateformCommand(Component<?> target) {
        this.target = target;
    }

    @Override
    public void execute(CommandContext context) throws CommandException {
        RollbackMacroCommand macro = defineMacroCommand();
        macro.execute(context);
    }

    private RollbackMacroCommand defineMacroCommand() {
        RollbackMacroCommand macro = new RollbackMacroCommand();
        addCommandsToMacro(macro);
        return macro;
    }

    private void addCommandsToMacro(RollbackMacroCommand macro) {
        for (Component component : parseComponent()) {
            addComponentToMacro(macro, component);
        }
    }

    private Iterable<Component<?>> parseComponent() {
        final List<Component<?>> components = Lists.newArrayList();
        target.accept(new ComponentVisitor() {
            @Override
            public void visitComponent(Component<?> visited) {
                components.add(visited);
            }
        });
        return filter(components, componentFilter);
    }

    private void addComponentToMacro(RollbackMacroCommand macro, Component<?> component) {
        macro.add(new RefreshStatusCommand(component));
        macro.add(OpenCommandIfNeeded.createCommand(component));
        macro.add(new CheckIsOpenedCommand(component));
    }

}
