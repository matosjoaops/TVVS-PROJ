package com.todotxt.util;

import com.chschmid.jdotxt.util.FileModifiedListener;
import com.chschmid.jdotxt.util.FileModifiedWatcher;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class FileModifiedWatcherTest {

    @Test
    public void fileModifiedWatcherTest() throws IOException {
        FileModifiedWatcher fileModifiedWatcher = new FileModifiedWatcher();
        FileModifiedListener fileModifiedListener = () -> {};
        fileModifiedWatcher.addFileModifiedListener(fileModifiedListener);

        File testFile = new File("./testFile.txt");
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
}
