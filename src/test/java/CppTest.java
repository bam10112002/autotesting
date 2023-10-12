import mai.testing.platform.tests_executors.UnitTestsExecutor;
import mai.testing.platform.tests_executors.UnitTestsExecutorBuilder;
import mai.testing.platform.results.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CppTest {
    @Test
    void okTest() {
        UnitTestsExecutor exe = UnitTestsExecutorBuilder.getExecutor("Cpp");
        assert exe != null;
        Result result = exe.execute("/mnt/d/WorkSpace/Code/Autotesting/testing-projects/cpp/ok/testing-project");
        Assertions.assertEquals(Result.Status.OK, result.getStatus());
    }

    @Test
    void waTest() {
        UnitTestsExecutor exe = UnitTestsExecutorBuilder.getExecutor("Cpp");
        assert exe != null;
        Result result = exe.execute("/mnt/d/WorkSpace/Code/Autotesting/testing-projects/cpp/wrong-answer/testing-project");
        Assertions.assertEquals(Result.Status.WA, result.getStatus());
    }

    @Test
    void caTest() {
        UnitTestsExecutor exe = UnitTestsExecutorBuilder.getExecutor("Cpp");
        assert exe != null;
        Result result = exe.execute("/mnt/d/WorkSpace/Code/Autotesting/testing-projects/cpp/compilation-error/testing-project");
        Assertions.assertEquals(Result.Status.CE, result.getStatus());
    }
}
