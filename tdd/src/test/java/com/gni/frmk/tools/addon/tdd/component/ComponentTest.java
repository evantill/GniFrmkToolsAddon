package com.gni.frmk.tools.addon.tdd.component;

import com.gni.frmk.tools.addon.tdd.api.Component;
import com.gni.frmk.tools.addon.tdd.api.ComponentId;
import com.gni.frmk.tools.addon.tdd.api.ComponentStatus;
import com.gni.frmk.tools.addon.tdd.api.ComponentType;
import com.gni.frmk.tools.addon.tdd.api.command.CommandContext;
import com.gni.frmk.tools.addon.tdd.api.command.CommandException;
import com.gni.frmk.tools.addon.tdd.command.ClosePlateformCommand;
import com.gni.frmk.tools.addon.tdd.command.OpenPlateformCommand;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.gni.frmk.tools.addon.tdd.api.ComponentStatus.*;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/08/11
 * Time: 13:28
 *
 * @author: e03229
 */
public class ComponentTest {

    private AlphaComponent alpha1;
    private AlphaComponent alpha2;
    private AlphaComponent alpha3;
    private BetaComponent beta;
    private Component[] all;
    private CommandContext context;
    private AlphaComponent alpha3bug;
    private BetaComponent betaBug;
    private Component[] allBug;
    private CommandContext contextBug;

    @BeforeMethod
    public void setUp() {
        alpha1 = new AlphaComponent(new IntegerId(1));
        alpha2 = new AlphaComponent(new IntegerId(2));
        alpha3 = new AlphaComponent(new IntegerId(3));
        beta = new BetaComponent(new IntegerId(4), alpha1, alpha2, alpha3);
        all = new Component[]{alpha1,
                              alpha2,
                              alpha3,
                              beta};
        alpha3bug = spy(alpha3);
        Mockito.doThrow(new IllegalStateException("alpha3bug bug on open")).when(alpha3bug).open();
        betaBug = new BetaComponent(new IntegerId(4), alpha1, alpha2, alpha3bug);
        allBug = new Component[]{alpha1,
                                 alpha2,
                                 alpha3bug,
                                 betaBug};
        context = mock(CommandContext.class);
        when(context.findComponent(Matchers.<ComponentType>any(), Matchers.<ComponentId>any())).thenAnswer(new Answer<Component>() {
            @Override
            public Component answer(InvocationOnMock invocation) throws Throwable {
                ComponentId id = (ComponentId) invocation.getArguments()[1];
                for (Component component : all) {
                    if (id == component.getId()) {
                        return component;
                    }
                }
                throw new IllegalStateException("component not found");
            }
        });
        contextBug = mock(CommandContext.class);
        when(contextBug.findComponent(Matchers.<ComponentType>any(), Matchers.<ComponentId>any())).thenAnswer(new Answer<Component>() {
            @Override
            public Component answer(InvocationOnMock invocation) throws Throwable {
                ComponentId id = (ComponentId) invocation.getArguments()[1];
                for (Component component : allBug) {
                    if (id == component.getId()) {
                        return component;
                    }
                }
                throw new IllegalStateException("component not found");
            }
        });
    }

    @Test
    public void testOpenPlateform() throws CommandException {
        OpenPlateformCommand cmd = new OpenPlateformCommand(beta);
        assertComponentStatusIs(UNKNOWN, all);
        cmd.execute(context);
        assertComponentStatusIs(OPENED, all);
    }

    @Test
    public void testOpenFailedPlateform() throws CommandException {
        OpenPlateformCommand cmd = new OpenPlateformCommand(betaBug);
        for (Component component : allBug) {
            if (AlphaComponent.class.isInstance(component)) {
                AlphaComponent.class.cast(component).setRefreshOpenState(Boolean.FALSE);
            } else if (BetaComponent.class.isInstance(component)) {
                BetaComponent.class.cast(component).setRefreshOpenState(Boolean.FALSE);
            }
        }
        assertComponentStatusIs(UNKNOWN, allBug);
        cmd.execute(contextBug);
        assertComponentStatusIs(CLOSED, alpha1);
        assertComponentStatusIs(CLOSED, alpha2);
        assertComponentStatusIs(UNKNOWN, alpha3bug);
        assertComponentStatusIs(UNKNOWN, betaBug);

    }

    @Test
    public void testClosePlateform() throws CommandException {
        ClosePlateformCommand command = new ClosePlateformCommand(beta);
        assertComponentStatusIs(UNKNOWN, all);
        command.execute(context);
        assertComponentStatusIs(CLOSED, all);
    }

    @Test
    public void testReOpenPlateform() throws CommandException {
        ClosePlateformCommand closeCmd = new ClosePlateformCommand(beta);
        OpenPlateformCommand openCmd = new OpenPlateformCommand(beta);
        assertComponentStatusIs(UNKNOWN, all);
        closeCmd.execute(context);
        assertComponentStatusIs(CLOSED, all);
        openCmd.execute(context);
        assertComponentStatusIs(OPENED, all);
    }

    private void assertComponentStatusIs(ComponentStatus expectedStatus, Component... component) {
        for (Component c : component) {
            assertThat(c.getStatus()).describedAs("component status for " + c).isEqualTo(expectedStatus);
        }
    }

}
