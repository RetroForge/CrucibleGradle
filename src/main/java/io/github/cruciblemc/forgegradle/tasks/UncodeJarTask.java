package io.github.cruciblemc.forgegradle.tasks;

import com.juanmuscaria.uncode.ASMCodeRemover;
import net.minecraftforge.gradle.delayed.DelayedFile;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.InputFile;
import org.gradle.api.tasks.OutputFile;
import org.gradle.api.tasks.TaskAction;

import java.io.IOException;

@org.gradle.work.DisableCachingByDefault(because = "Uses legacy mutable task configuration and local file caching")
public class UncodeJarTask extends DefaultTask {
  private DelayedFile inputJar;

  private DelayedFile outJar;

  @org.gradle.api.tasks.PathSensitive(org.gradle.api.tasks.PathSensitivity.ABSOLUTE)
  @InputFile
  public java.io.File getInputJar() { return inputJar.call(); }
  public void setInputJar(DelayedFile inputJar) { this.inputJar = inputJar; }

  @OutputFile
  public java.io.File getOutJar() { return outJar.call(); }
  public void setOutJar(DelayedFile outJar) { this.outJar = outJar; }

  @TaskAction
  public void doTask() throws IOException {
    ASMCodeRemover.removeContent(getInputJar().toPath(), getOutJar().toPath(), true);
  }
}
