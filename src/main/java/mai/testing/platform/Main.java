package mai.testing.platform;

import mai.testing.platform.tests_executors.UnitTestsExecutor;
import mai.testing.platform.tests_executors.UnitTestsExecutorBuilder;
import mai.testing.platform.handlers.BaseHandler;
import mai.testing.platform.handlers.unit_tests.CppHandler;
import mai.testing.platform.results.Result;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        UnitTestsExecutor exe = UnitTestsExecutorBuilder.getExecutor("Cpp");
        assert exe != null;
        Result result = exe.execute("/mnt/d/WorkSpace/Code/Autotesting/testing-projects/cpp/ok/testing-project");



//        String pathToDirectory = "/mnt/d/WorkSpace/Code/Autotesting/testing-projects/cpp/wrong-answer/testing-project";
//        String command = "./build_and_run.sh";
//
//        PythonPrecompile.accept(pathToDirectory);
//        try {
//            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
//            processBuilder.directory(new File(pathToDirectory));
//            processBuilder.redirectErrorStream(true);
//            Process process = processBuilder.start();
//
//            List<String[]> failedTests = getStrings(process);
//
//            System.out.println(pathToDirectory);
//            System.out.println(failedTests);
//            System.out.println(process.waitFor());
//
//            if (!failedTests.isEmpty())
//                System.out.println(new WAResult(failedTests));
//            else if (process.waitFor() != 0)
//                System.out.println(new Result(Result.Status.CE));
//            else
//                System.out.println(new Result());
//
//        } catch (IOException | InterruptedException e) {
//            System.err.println(e.getMessage());
//        }
    }

    private static List<String[]> getStrings(Process process) throws IOException {
        BaseHandler Basehandler = new CppHandler();
        Function<String, String[]> handler = Basehandler::handel;

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;

        List<String[]> failedTests = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            String[] res = handler.apply(line);
            if (res.length != 0)
                failedTests.add(res);
        }
        return failedTests;
    }

    /*  exit len
        java python
    ce   1/0  1/0
    wa   1/1  0/1
    ok   0/0  0/0

     */
}