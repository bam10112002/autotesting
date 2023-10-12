package mai.testing.platform.tests_executors;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import mai.testing.platform.handlers.BaseHandler;
import mai.testing.platform.precompile.BasePrecompile;
import mai.testing.platform.results.Result;
import mai.testing.platform.results.WAResult;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@AllArgsConstructor
public class UnitTestsExecutor {

    private String command;
    BaseHandler handler;
    BasePrecompile precompile;

    public Result execute(@NonNull String pathToDirectory) {
        if (precompile != null)
            precompile.execute(pathToDirectory);

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            processBuilder.directory(new File(pathToDirectory));
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            Function<String, String[]> handler = this.handler::handel;

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            List<String[]> failedTests = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] res = handler.apply(line);
                if (res.length != 0)
                    failedTests.add(res);
            }

            if (!failedTests.isEmpty())
                return new WAResult(failedTests);
            else if (process.waitFor() != 0)
                return new Result(Result.Status.CE);
            else
                return new Result();

        } catch (IOException | InterruptedException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}
