package com.jdotxt.gui.controls;

import com.chschmid.jdotxt.gui.controls.JdotxtCombobox;
import org.junit.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
        String placeholder = "placeholder";
        JdotxtCombobox jdotxtCombobox = new JdotxtCombobox(placeholder);
        JdotxtCombobox.PlaceholderComboBoxModel<String> placeholderComboBoxModel = jdotxtCombobox.new PlaceholderComboBoxModel<>(placeholder, new Vector<>());

        Object o = "o";
        placeholderComboBoxModel.setSelectedItem(o);
        assertEquals(placeholderComboBoxModel.getSelectedItem(), o);
    }

    @Test
    public void setSelectedItemTest3() {
        String placeholder = "placeholder";
        JdotxtCombobox jdotxtCombobox = new JdotxtCombobox(placeholder);
        JdotxtCombobox.PlaceholderComboBoxModel<String> placeholderComboBoxModel = jdotxtCombobox.new PlaceholderComboBoxModel<>(placeholder, new Vector<>());

        Object o = "placeholder";
        placeholderComboBoxModel.setSelectedItem(o);
        assertEquals(placeholderComboBoxModel.getSelectedItem(), o);
        assertFalse(jdotxtCombobox.firstSelect);
    }
}
