/*
 * Created on 04.10.2010
 *
 */
package org.jdesktop.swingx;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JXFrameTest extends InteractiveTestCase {
    @SuppressWarnings("unused")
    private static final Logger LOG = Logger.getLogger(JXFrameTest.class
            .getName());
    
    @Test
    public void testGraphicsConfig() {
        // This test will not work in a headless configuration.
        if (GraphicsEnvironment.isHeadless()) {
            LOG.fine("cannot run ui test - headless environment");
            return;
        }
        // A wrapper GraphicsConfiguration is not usable here. Window.init asks
        // the toolkit for screen insets, and XToolkit casts the configuration
        // to sun.awt.X11GraphicsConfig.
        JXFrame compare = new JXFrame();
        JXFrame frame = null;
        try {
            GraphicsConfiguration gc = screenConfiguration();
            frame = new JXFrame(gc);
            assertEquals(gc, frame.getGraphicsConfiguration());
            assertEquals(compare.getDefaultCloseOperation(), frame.getDefaultCloseOperation());
            assertEquals(compare.getTitle(), frame.getTitle());
        } finally {
            if (frame != null) {
                frame.dispose();
            }
            compare.dispose();
        }
    }

    /**
     * Prefer a non-default screen configuration so the constructor has to
     * keep the instance it was given. Fall back to the default when the
     * device exposes only one.
     */
    private static GraphicsConfiguration screenConfiguration() {
        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsConfiguration fallback = environment.getDefaultScreenDevice().getDefaultConfiguration();
        for (GraphicsDevice device : environment.getScreenDevices()) {
            if (device.getType() != GraphicsDevice.TYPE_RASTER_SCREEN) {
                continue;
            }
            for (GraphicsConfiguration candidate : device.getConfigurations()) {
                if (candidate != null && candidate != fallback) {
                    return candidate;
                }
            }
        }
        return fallback;
    }
}
