package net.minecraftforge.gradle;

import javax.inject.Inject;
import org.gradle.api.Project;
import org.gradle.process.ExecOperations;

/** Process execution through Gradle's public, injected service API. */
public abstract class ProcessOperations {
    @Inject
    public abstract ExecOperations getExecOperations();

    public static ExecOperations get(Project project) {
        return project.getObjects().newInstance(ProcessOperations.class).getExecOperations();
    }
}
