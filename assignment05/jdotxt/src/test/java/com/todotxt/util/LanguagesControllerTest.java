package com.todotxt.util;

import com.chschmid.jdotxt.util.LanguagesController;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LanguagesControllerTest {

    @Test
    public void languagesControllerTest() {
        String language = "English";
        LanguagesController languagesController = new LanguagesController(language);
        String word = languagesController.getWord("dates_today");

        assertEquals(word, "today");
    }
}
