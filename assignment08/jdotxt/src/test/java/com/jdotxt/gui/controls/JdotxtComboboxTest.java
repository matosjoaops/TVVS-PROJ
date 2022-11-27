package com.jdotxt.gui.controls;

import com.chschmid.jdotxt.gui.controls.JdotxtCombobox;
import org.junit.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JdotxtComboboxTest {

    @Test
    public void setSelectedItemTest1() {
        String placeholder = "placeholder";
        JdotxtCombobox jdotxtCombobox = new JdotxtCombobox(placeholder);
        JdotxtCombobox.PlaceholderComboBoxModel<String> placeholderComboBoxModel = jdotxtCombobox.new PlaceholderComboBoxModel<>(placeholder, new Vector<>());

        Object o = null;
        placeholderComboBoxModel.setSelectedItem(o);
        assertEquals(placeholderComboBoxModel.getSelectedItem(), placeholder);
    }

    @Test
    public void setSelectedItemTest2() {

    }

    @Test
    public void setSelectedItemTest3() {

    }
}
