package co.tantleff.libanimate.scripting;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.customizers.ImportCustomizer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ScriptLoader<T extends Script> {
    private final Path scriptDirectory;
//    private final Class<T> scriptClass;
    private final GroovyShell shell;

    public ScriptLoader(Path scriptDirectory, Class<T> scriptClass, ImportCustomizer imports) {
        assert Files.isDirectory(scriptDirectory);
        this.scriptDirectory = scriptDirectory;
//        this.scriptClass = scriptClass;

        final var config = new CompilerConfiguration();
        config.setScriptBaseClass(scriptClass.getCanonicalName());
        config.addCompilationCustomizers(imports);

        this.shell = new GroovyShell(config);
    }

    public Script loadScript(String name, Binding scriptBinding) throws IOException {
        // Grab the file with the passed in name
        final var file = scriptDirectory.resolve(name);
        if (!Files.isRegularFile(file))
            return null;

        // Load the file if it exists
        final var script = shell.parse(file.toFile());
        script.setBinding(scriptBinding);

        return script;
    }
}
