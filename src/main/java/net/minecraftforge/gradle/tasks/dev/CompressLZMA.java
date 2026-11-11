package net.minecraftforge.gradle.tasks.dev;

import com.google.common.io.ByteStreams;
import lzma.streams.LzmaOutputStream;
import net.minecraftforge.gradle.delayed.DelayedFile;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.InputFile;
import org.gradle.api.tasks.OutputFile;
import org.gradle.api.tasks.TaskAction;

import java.io.*;
import java.nio.file.Files;

@org.gradle.work.DisableCachingByDefault(because = "Uses legacy mutable task configuration and local file caching")
public class CompressLZMA extends DefaultTask {
    @org.gradle.api.tasks.PathSensitive(org.gradle.api.tasks.PathSensitivity.ABSOLUTE)
    @InputFile
    private DelayedFile inputFile;

    @OutputFile
    private DelayedFile outputFile;

    @TaskAction
    public void doTask() throws IOException {
        final BufferedInputStream in = new BufferedInputStream(Files.newInputStream(getInputFile().toPath()));
        final OutputStream out = new LzmaOutputStream.Builder(Files.newOutputStream(getOutputFile().toPath()))
                .useEndMarkerMode(true)
                .build();

        ByteStreams.copy(in, out);

        in.close();
        out.close();
    }

    public File getInputFile() {
        return inputFile.call();
    }

    public void setInputFile(DelayedFile inputFile) {
        this.inputFile = inputFile;
    }

    public File getOutputFile() {
        return outputFile.call();
    }

    public void setOutputFile(DelayedFile outputFile) {
        this.outputFile = outputFile;
    }
}
