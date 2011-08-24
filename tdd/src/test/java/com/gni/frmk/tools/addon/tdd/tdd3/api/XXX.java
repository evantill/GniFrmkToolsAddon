package com.gni.frmk.tools.addon.tdd.tdd3.api;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Date: 24/08/11
 * Time: 11:50
 *
 * @author: e03229
 */
public class XXX {

    interface Strategy<C extends Command, X extends Context<X>> {
        void open(X ctx, C command);

        Class<C> getRequiredCommandType();

        Class<X> getRequiredContextType();
    }

    interface Context<X extends Context<X>> {
        <C extends Command> Strategy<? super C, ? super X> getStrategy(C command);
    }

    interface Component {
        <X extends Context<X>> void open(X ctx);
    }

    interface Command {
        <X extends Context<X>> void execute(X ctx);

        public Class<? extends Component> getComponentType();
    }

    static class StrategyRegistry<X extends Context<X>> {
        private List<Strategy<?, ? super X>> strategies = Lists.newArrayList();

        public void register(Strategy<?, ? super X> strategy) {
            strategies.add(strategy);
        }

        public <C extends Command> Strategy<? super C, ? super X> getStrategy(C command) {
            for (Strategy<?, ? super X> strategy : strategies) {
                if (strategySupportCommand(strategy, command)) {
                    return asStrategyForCommand(strategy, command);
                }
            }
            throw new IllegalStateException("strategy not found for command " + command);
        }

        private boolean strategySupportCommand(Strategy<?, ?> strategy, Command command) {
            return strategy.getRequiredCommandType().isInstance(command);
        }

        @SuppressWarnings({"unchecked"})//guarded by strategySupportCommand
        private <C extends Command> Strategy<? super C, ? super X> asStrategyForCommand(Strategy<?, ? super X> strategy, C command) {
            return (Strategy<? super C, ? super X>) strategy;
        }
    }

    static class Strategies {
        @SuppressWarnings({"unchecked"})//guarded by using strategy.getRequiredCommandType()
        public static <C extends Command, X extends Context<X>> Strategy<? super C, ? extends X>
        forCommand(Strategy<?, ? extends X> strategy, C command) {
            Class<? extends Command> requiredCommandType = strategy.getRequiredCommandType();
            if (requiredCommandType.isInstance(command)) {
                return (Strategy<? super C, ? extends X>) strategy;
            }
            String errorMessage = String.format("strategy can not handle command %s", command);
            throw new IllegalStateException(errorMessage);
        }

        public static boolean isForCommand(Strategy<?, ?> strategy, Command command) {
            return strategy.getRequiredCommandType().isInstance(command);
        }
    }

    static class AlphaComponent implements Component {

        private final AlphaOpenCommand openCommand = new AlphaOpenCommand(this);
        private final String param1;

        AlphaComponent(String param1) {
            this.param1 = param1;
        }

        @Override
        public <X extends Context<X>> void open(X ctx) {
            openCommand.execute(ctx);
        }

        public String getParam1() {
            return param1;
        }

        public String getComponentType() {
            return getClass().getName();
        }
    }

    static class AlphaOpenCommand implements Command {
        private final AlphaComponent component;

        AlphaOpenCommand(AlphaComponent component) {
            this.component = component;
        }

        public String getParam1() {
            return component.getParam1();
        }

        public Class<? extends Component> getComponentType() {
            return component.getClass();
        }

        @Override
        public <X extends Context<X>> void execute(X ctx) {
            Strategy<? super AlphaOpenCommand, ? super X> strategy = ctx.getStrategy(this);
            strategy.open(ctx, this);
        }
    }

    static class LocalContext implements Context<LocalContext> {
        private final StrategyRegistry<LocalContext> registry;

        LocalContext(StrategyRegistry<LocalContext> registry) {
            this.registry = registry;
        }

        private String getLocalInfo() {
            return "localInfo";
        }

        @Override
        public <C extends Command> Strategy<? super C, ? super LocalContext> getStrategy(C command) {
            return registry.getStrategy(command);
        }
    }

    static class RemoteContext implements Context<RemoteContext> {
        private final StrategyRegistry<RemoteContext> registry;

        RemoteContext(StrategyRegistry<RemoteContext> registry) {
            this.registry = registry;
        }

        private String getRemoteInfo() {
            return "remoteInfo";
        }

        @Override
        public <C extends Command> Strategy<? super C, ? super RemoteContext> getStrategy(C command) {
            return registry.getStrategy(command);
        }
    }

    static class LocalAlphaStrategy implements Strategy<AlphaOpenCommand, LocalContext> {

        @Override
        public void open(LocalContext ctx, AlphaOpenCommand command) {
            String param1 = command.getParam1();
            Class<? extends Component> componentType = command.getComponentType();
            String localInfo = ctx.getLocalInfo();
            String message = String.format("open local with param=%s on %s in %s", param1, componentType.getSimpleName(), localInfo);
            System.out.println(message);
        }

        @Override
        public Class<AlphaOpenCommand> getRequiredCommandType() {
            return AlphaOpenCommand.class;
        }

        @Override
        public Class<LocalContext> getRequiredContextType() {
            return LocalContext.class;
        }
    }

    static class RemoteGenericStrategy implements Strategy<Command, RemoteContext> {

        @Override
        public void open(RemoteContext ctx, Command command) {
            Class<? extends Component> componentType = command.getComponentType();
            String localInfo = ctx.getRemoteInfo();
            String message = String.format("open remote on %s in %s", componentType.getSimpleName(), localInfo);
            System.out.println(message);
        }

        @Override
        public Class<Command> getRequiredCommandType() {
            return Command.class;
        }

        @Override
        public Class<RemoteContext> getRequiredContextType() {
            return RemoteContext.class;
        }
    }

    public static void main(String[] args) {
        AlphaComponent component = new AlphaComponent("valeur1");
        StrategyRegistry<LocalContext> localRegistry = new StrategyRegistry<LocalContext>();
        LocalAlphaStrategy localAlphaStrategy = new LocalAlphaStrategy();
        localRegistry.register(localAlphaStrategy);
        LocalContext localCtx = new LocalContext(localRegistry);
        component.open(localCtx);

        RemoteGenericStrategy remoteAlphaStrategy = new RemoteGenericStrategy();
        StrategyRegistry<RemoteContext> remoteRegistry = new StrategyRegistry<RemoteContext>();
        remoteRegistry.register(remoteAlphaStrategy);
        RemoteContext remoteCtx = new RemoteContext(remoteRegistry);
        component.open(remoteCtx);

    }
}
