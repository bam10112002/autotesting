package mai.testing.platform.handlers.unit_tests;

import mai.testing.platform.handlers.BaseHandler;

public class GradleHandler implements BaseHandler {
    @Override
    public String[] handel(String line) {
        if (line.matches(".*\\(\\).*FAILED.*")) {
            line = line.replace("() FAILED", "")
                    .replace(" ", "");
            return line.split(">");
        }
        return new String[0];
    }
}
