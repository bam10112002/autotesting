package mai.testing.platform.tests_executors;

import mai.testing.platform.handlers.unit_tests.CppHandler;
import mai.testing.platform.handlers.unit_tests.GradleHandler;
import mai.testing.platform.handlers.unit_tests.MavenHandler;
import mai.testing.platform.handlers.unit_tests.PythonHandler;
import mai.testing.platform.precompile.CppPrecompile;
import mai.testing.platform.precompile.PythonPrecompile;

public class UnitTestsExecutorBuilder {
    public static UnitTestsExecutor getExecutor(String tag) {
        return switch (tag) {
            case "Gradle" -> new UnitTestsExecutor("./gradlew test", new GradleHandler(), null);
            case "Maven"  -> new UnitTestsExecutor("mvn package",    new MavenHandler(), null);
            case "Python" -> new UnitTestsExecutor("./unit_tests_executor.sh",    new PythonHandler(), new PythonPrecompile());
            case "Cpp" -> new UnitTestsExecutor("./build_and_run.sh",   new CppHandler(),    new CppPrecompile());
            default -> null;
        };
    }
}
