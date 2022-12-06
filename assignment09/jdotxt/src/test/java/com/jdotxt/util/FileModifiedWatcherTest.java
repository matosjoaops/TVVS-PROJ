package com.jdotxt.util;

import com.chschmid.jdotxt.util.FileModifiedListener;
import com.chschmid.jdotxt.util.FileModifiedWatcher;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.Assert.*;

public class FileModifiedWatcherTest {

    private final String TEST_FILE_PATH = "." + File.separator + "testFile.txt";

    protected class TestListener implements FileModifiedListener {

        public boolean wasTriggered;
        public TestListener previousListener;

        TestListener() {
            wasTriggered = false;
            previousListener = null;
        }

        TestListener(TestListener previousListener) {
            wasTriggered = false;
            this.previousListener = previousListener;
        }
        @Override
        public void fileModified() {
            wasTriggered = this.previousListener == null || this.previousListener.wasTriggered;
        }
    }
    @AfterEach
    public void removeTestFile() {
        File testFile = new File(TEST_FILE_PATH);
        testFile.delete();
    }

    @Test
    public void fileModifiedWatcherTest() throws IOException {
        FileModifiedWatcher fileModifiedWatcher = new FileModifiedWatcher();
        FileModifiedListener fileModifiedListener = () -> {};
        fileModifiedWatcher.addFileModifiedListener(fileModifiedListener);

        File testFile = new File(TEST_FILE_PATH);
        fileModifiedWatcher.registerFile(testFile);
        fileModifiedWatcher.startProcessingEvents();
        File registeredFile = fileModifiedWatcher.getFile();

        assertEquals(registeredFile, testFile);

        fileModifiedWatcher.unRegisterFile();
        registeredFile = fileModifiedWatcher.getFile();

        assertNull(registeredFile);

        fileModifiedWatcher.stopProcessingEvents();
        fileModifiedWatcher.removeFileModifiedListener(fileModifiedListener);
    }

    @Test
    public void fireFileModified() throws IOException {
        FileModifiedWatcher watcher = new FileModifiedWatcher();
        TestListener listener = new TestListener();

        watcher.addFileModifiedListener(listener);
        watcher.fireFileModified();

        assertTrue(listener.wasTriggered);
    }

    @Test
    public void fireFileModified2() throws IOException {
        FileModifiedWatcher watcher = new FileModifiedWatcher();

        TestListener listener1 = new TestListener();
        TestListener listener2 = new TestListener(listener1);

        watcher.addFileModifiedListener(listener2);
        watcher.addFileModifiedListener(listener1);
        watcher.fireFileModified();

        assertTrue(listener2.wasTriggered);
        assertTrue(listener1.wasTriggered);
    }
}
