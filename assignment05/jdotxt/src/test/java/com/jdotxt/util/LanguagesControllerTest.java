package com.jdotxt.util;

import com.chschmid.jdotxt.util.LanguagesController;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LanguagesControllerTest {

    @Test
    public void languagesControllerTest() {
        LanguagesController languagesController = new LanguagesController("English");
        String word = languagesController.getWord("dates_today");

        assertEquals(word, "today");
    }
}
