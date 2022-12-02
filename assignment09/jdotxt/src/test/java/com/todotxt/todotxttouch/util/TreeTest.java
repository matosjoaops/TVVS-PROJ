package com.todotxt.todotxttouch.util;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TreeTest {

    private final String testString = "Some text";
    private final String testString2 = "Some other text";

    @Test
    public void createTree1() {
        Tree<String> tree = new Tree<>(testString);
        assertNotNull(tree);
    }

    @Test
    public void createTree2() {
        Tree<String> tree = new Tree<>(testString);
        Tree<String> tree2 = new Tree<>(tree, testString2);
        assertNotNull(tree2);
    }

    @Test
    public void addChild() {
        Tree<String> tree = new Tree<>(testString);
        tree.addChild(testString2);
        Tree<String> child = tree.getChild(0);
        assertEquals(child, tree);
    }

    @Test
    public void addChild2() {
        Tree<String> tree = new Tree<>(testString);
        tree.addChild(testString2);
        assertTrue(tree.contains(testString2));
    }

    @Test
    public void addChild3() {
        Tree<String> tree = new Tree<>(testString);
        tree.addChild(testString2);
        Tree<String> tree2 = new Tree<>(testString2);
        assertTrue(tree.contains(tree2));
    }

    @Test
    public void addChild4() {
        Tree<String> tree = new Tree<>(testString);
        Tree<String> tree2 = new Tree<>(testString2);
        tree.addChild(tree2);
        assertEquals(tree.getChildren().get(0), tree2);
    }

    @Test
    public void addChild5() {
        Tree<String> tree = new Tree<>(testString);
        tree.addChild(testString2);
        tree.addChild("adfadf");
        assertEquals(tree.getChildren().size(), 2);
    }

    @Test
    public void setLoaded() {
        Tree<String> tree = new Tree<>(testString);
        tree.setLoaded();
        assertTrue(tree.isLoaded());
    }

    @Test
    public void setLoaded2() {
        Tree<String> tree = new Tree<>(testString);
        tree.addChild(testString2);
        tree.setLoaded();
        assertTrue(tree.isLoaded());
    }

    @Test
    public void getParent() {
        Tree<String> tree = new Tree<>(testString);
        assertNull(tree.getParent());
    }

    @Test
    public void contains() {
        Tree<String> tree = new Tree<>(testString);
        assertFalse(tree.contains(testString2));
    }

    @Test
    public void contains2() {
        Tree<String> tree = new Tree<>(testString);
        Tree<String> tree2 = new Tree<>(testString2);
        assertFalse(tree.contains(tree2));
    }

    @Test
    public void contains3() {
        Tree<String> tree = new Tree<>(testString);
        Tree<String> tree2 = new Tree<>(testString2);
        tree.addChild(tree2);
        assertFalse(tree.contains("sdfaf"));
    }

    @Test
    public void isLoaded() {
        Tree<String> tree = new Tree<>(testString);
        assertFalse(tree.isLoaded());
    }
}
