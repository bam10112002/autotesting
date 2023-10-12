package mai.testing.platform.precompile;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class PythonPrecompile implements BasePrecompile{

    @Override
    public void execute(String path) {
        File srcFile = new File("./src/main/resources/python/unit_tests_executor.sh");
        File destFile = new File(path + "/unit_tests_executor.sh");
        try {

            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
