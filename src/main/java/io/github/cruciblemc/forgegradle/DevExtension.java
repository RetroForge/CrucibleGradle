package io.github.cruciblemc.forgegradle;

import net.minecraftforge.gradle.common.BaseExtension;
import org.gradle.api.Action;
import org.gradle.api.Project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DevExtension extends BaseExtension {
    private int javaRelease = 8;
    private String fmlDir;
    private String forgeDir;
    private String bukkitDir;
    private String mainClass = "";
    private String tweakClass = "";
    private List<String> repos = new ArrayList<>();
    private boolean makeJavadoc = true;
    private String installerVersion = "null";

    private Action<Project> subprojects;
    private Action<Project> cleanProject;
    private Action<Project> dirtyProject;

    public DevExtension(DevBasePlugin plugin) {
        super(plugin);
    }

    /** Server bytecode target; the Gradle runtime still requires JDK 21+. */
    public int getJavaRelease() { return javaRelease; }

    public void setJavaRelease(int javaRelease) {
        if (javaRelease != 8 && javaRelease != 21) {
            throw new IllegalArgumentException("minecraft.javaRelease must be 8 or 21");
        }
        this.javaRelease = javaRelease;
    }

    public String getFmlDir() {
        return normalizeOrDefault(fmlDir);
    }

    public String getForgeDir() {
        return normalizeOrDefault(forgeDir);
    }

    public String getBukkitDir() {
        return normalizeOrDefault(bukkitDir);
    }

    public void setMainClass(String mainClass) {
        this.mainClass = mainClass == null ? "" : mainClass;
    }

    public void setTweakClass(String tweakClass) {
        this.tweakClass = tweakClass == null ? "" : tweakClass;
    }

    public void setRepos(List<String> repos) {
        this.repos = new ArrayList<>(repos);
    }

    public void setRepos(String... repos) {
        this.repos = new ArrayList<>(Arrays.asList(repos));
    }

    public void setFmlDir(String fmlDir) { this.fmlDir = fmlDir; }
    public void setForgeDir(String forgeDir) { this.forgeDir = forgeDir; }
    public void setBukkitDir(String bukkitDir) { this.bukkitDir = bukkitDir; }
    public String getMainClass() { return mainClass; }
    public String getTweakClass() { return tweakClass; }
    public List<String> getRepos() { return repos; }
    public boolean isMakeJavadoc() { return makeJavadoc; }
    public void setMakeJavadoc(boolean makeJavadoc) { this.makeJavadoc = makeJavadoc; }
    public String getInstallerVersion() { return installerVersion; }
    public void setInstallerVersion(String installerVersion) { this.installerVersion = installerVersion; }
    public Action<Project> getSubprojects() { return subprojects; }
    public void setSubprojects(Action<Project> subprojects) { this.subprojects = subprojects; }
    public Action<Project> getCleanProject() { return cleanProject; }
    public void setCleanProject(Action<Project> cleanProject) { this.cleanProject = cleanProject; }
    public Action<Project> getDirtyProject() { return dirtyProject; }
    public void setDirtyProject(Action<Project> dirtyProject) { this.dirtyProject = dirtyProject; }

    // --- Helpers ---

    private String normalizeOrDefault(String path) {
        if (path == null) {
            return project.getProjectDir().getPath().replace('\\', '/');
        }
        return path.replace('\\', '/');
    }
}
