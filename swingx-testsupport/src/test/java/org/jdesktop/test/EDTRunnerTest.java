package org.jdesktop.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import javax.swing.SwingUtilities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.jdesktop.test.EDTExtension;

@ExtendWith(EDTExtension.class)
public class EDTRunnerTest {
    /**
     * Ensure that the EDTRunner is using the EventDispatchThread.
     */
    @Test
    public void testForSwingThread() {
        assertThat(SwingUtilities.isEventDispatchThread(), is(true));
    }
}
