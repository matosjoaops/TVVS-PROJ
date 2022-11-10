package com.jdotxt;

import com.chschmid.jdotxt.Jdotxt;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JdotxtTest {

    @Test
    public void jdotxtTest() {
        Jdotxt.main(null);

        // what to test here???

        String newString = Jdotxt.insertReplaceString("123", "-", 0);

        assertEquals(newString, "-23");
    }
}
