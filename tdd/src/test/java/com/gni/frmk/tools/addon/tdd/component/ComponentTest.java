package com.gni.frmk.tools.addon.tdd.component;

import com.gni.frmk.tools.addon.tdd.api.*;
import com.gni.frmk.tools.addon.tdd.command.ClosePlateformCommand;
import com.gni.frmk.tools.addon.tdd.command.OpenPlateformCommand;
import org.mockito.Matchers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.gni.frmk.tools.addon.tdd.api.ComponentStatus.*;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by IntelliJ IDEA.
 * Date: 16/08/11
 * Time: 13:28
 *
 * @author: e03229
 */
public class ComponentTest {

    private AlphaComponent alpha;
    private BetaComponent beta;
    private CommandContext context;

    @BeforeMethod
    public void setUp() {
        alpha = new AlphaComponent(new IntegerId(1));
        beta = new BetaComponent(new IntegerId(2), alpha);
        context = mock(CommandContext.class);
        when(context.findComponent(Matchers.<ComponentType>any(), Matchers.<ComponentId>any())).thenAnswer(new Answer<Component>() {
            @Override
            public Component answer(InvocationOnMock invocation) throws Throwable {
                ComponentType type = (ComponentType) invocation.getArguments()[0];
                if (type == alpha.getType()) {
                    return alpha;
                }
                if (type == beta.getType()) {
                    return beta;
                }
                throw new IllegalStateException("component not found");
            }
        });
    }

    @Test
    public void testOpenPlateform() throws CommandException {
        OpenPlateformCommand cmd = new OpenPlateformCommand(beta);
        assertComponentStatusIs(UNKNOWN, alpha, beta);
        cmd.execute(context);
        assertComponentStatusIs(OPENED, alpha, beta);
    }

    @Test
    public void testClosePlateform() throws CommandException {
        ClosePlateformCommand command = new ClosePlateformCommand(beta);
        assertComponentStatusIs(UNKNOWN, alpha, beta);
        command.execute(context);
        assertComponentStatusIs(CLOSED, alpha, beta);
    }

    @Test
    public void testReOpenPlateform() throws CommandException {
        ClosePlateformCommand closeCmd = new ClosePlateformCommand(beta);
        OpenPlateformCommand openCmd = new OpenPlateformCommand(beta);
        assertComponentStatusIs(UNKNOWN, alpha, beta);
        closeCmd.execute(context);
        assertComponentStatusIs(CLOSED, alpha, beta);
        openCmd.execute(context);
        assertComponentStatusIs(OPENED, alpha, beta);
    }

    private void assertComponentStatusIs(ComponentStatus expectedStatus, Component... component) {
        for (Component c : component) {
            assertThat(c.getStatus()).describedAs("component status for " + c).isEqualTo(expectedStatus);
        }
    }

}
