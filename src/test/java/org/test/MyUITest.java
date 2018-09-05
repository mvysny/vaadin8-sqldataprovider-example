package org.test;

import com.github.karibu.testing.GridKt;
import com.github.karibu.testing.LocatorKt;
import com.github.karibu.testing.MockVaadin;
import com.vaadin.ui.Grid;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Look Ma, no Spring/JavaEE/Servlet container necessary! That's what you get when you keep things simple.
 * @author mavi
 */
public class MyUITest {
    @BeforeAll
    public static void mockContainer() {
        new Bootstrap().contextInitialized(null);
    }

    @AfterAll
    public static void stopMockedContainer() {
        new Bootstrap().contextDestroyed(null);
    }

    @BeforeEach
    public void mockVaadin() {
        MockVaadin.setup(MyUI::new);
    }

    @Test
    public void testGridHas100Rows() {
        final Grid grid = LocatorKt._get(Grid.class, gridSearchSpec -> null);
        GridKt.expectRows(grid, 100);
    }
}
