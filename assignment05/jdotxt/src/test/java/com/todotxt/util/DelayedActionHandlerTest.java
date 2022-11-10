package com.todotxt.util;

import com.chschmid.jdotxt.util.DelayedActionHandler;
import org.junit.Test;

import java.awt.event.ActionListener;

public class DelayedActionHandlerTest {

    @Test
    public void delayedActionHandlerTest() {
        ActionListener actionListener = e -> {};
        DelayedActionHandler delayedActionHandler = new DelayedActionHandler(10, actionListener);

        delayedActionHandler.triggerAction();
        delayedActionHandler.close();

        // I don't know what to assert in this case
    }
}
