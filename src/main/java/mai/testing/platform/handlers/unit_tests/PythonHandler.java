package mai.testing.platform.handlers.unit_tests;

import mai.testing.platform.handlers.BaseHandler;

public class PythonHandler implements BaseHandler {

    @Override
    public String[] handel(String line) {
        if(line.contains("FAIL: ")) {
            line = line.replace("FAIL: ", "")
                        .replace(" ", "");
            String[] res = line.split("\\.")[0]
                                .split("\\(");
            return new String[] {res[1], res[0]};
        }
        return new String[0];
    }
}
