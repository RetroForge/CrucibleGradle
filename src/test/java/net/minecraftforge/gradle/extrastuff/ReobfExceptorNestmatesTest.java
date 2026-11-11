package net.minecraftforge.gradle.extrastuff;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ReobfExceptorNestmatesTest {
    @TempDir Path directory;

    @Test void readsJava21Nestmates() throws Exception { readJar(Opcodes.V21); }
    @Test void stillReadsJava8Classes() throws Exception { readJar(Opcodes.V1_8); }

    private void readJar(int version) throws Exception {
        Path jar = directory.resolve("classes.jar");
        try (JarOutputStream output = new JarOutputStream(Files.newOutputStream(jar))) {
            for (boolean nested : new boolean[]{false, true}) {
                String host = "net/minecraft/Host";
                String name = host + (nested ? "$Nested" : "");
                ClassWriter writer = new ClassWriter(0);
                writer.visit(version, Opcodes.ACC_PUBLIC, name, null, "java/lang/Object", null);
                if (version >= Opcodes.V11) {
                    if (nested) writer.visitNestHost(host);
                    else writer.visitNestMember(host + "$Nested");
                }
                writer.visitEnd();
                output.putNextEntry(new JarEntry(name + ".class"));
                output.write(writer.toByteArray());
                output.closeEntry();
            }
        }
        Path config = directory.resolve("joined.exc");
        Files.writeString(config, "");
        ReobfExceptor exceptor = new ReobfExceptor();
        exceptor.deobfJar = jar.toFile();
        exceptor.toReobfJar = jar.toFile();
        exceptor.excConfig = config.toFile();
        assertDoesNotThrow(exceptor::doFirstThings);
    }
}
