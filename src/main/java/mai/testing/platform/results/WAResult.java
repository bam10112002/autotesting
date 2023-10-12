package mai.testing.platform.results;

import java.util.List;


public class WAResult extends Result {

    public WAResult(List<String[]> failedTests) {
        this.failedTests = failedTests;
        status = Status.WA;
    }

    List<String[]> failedTests;

    @Override
    public String toString() {
        return "Status: " + status + "\n WA tests: " + failedTests;
    }
}
