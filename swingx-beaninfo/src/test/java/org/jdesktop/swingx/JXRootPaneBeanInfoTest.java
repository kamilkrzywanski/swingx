package org.jdesktop.swingx;

import org.jdesktop.beans.AbstractBeanInfoTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
public class JXRootPaneBeanInfoTest extends AbstractBeanInfoTest<JXRootPane> {
    @Override
    protected JXRootPane createInstance() {
        return new JXRootPane();
    }
}
