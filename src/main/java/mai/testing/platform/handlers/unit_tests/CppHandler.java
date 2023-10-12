package mai.testing.platform.handlers.unit_tests;

import mai.testing.platform.handlers.BaseHandler;

public class CppHandler implements BaseHandler {
    @Override
    public String[] handel(String line) {
        if(line.contains("[  FAILED  ]") && line.contains("ms"))
            return line.split(" ")[1].split("\\.");
        return new String[0];
    }
}
