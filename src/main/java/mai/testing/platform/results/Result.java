package mai.testing.platform.results;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Result {
    public enum Status {OK, CE, WA, FUCK};

    Status status;

    public Result() {
        status = Status.OK;
    }

    @Override
    public String toString() {
        return "Status: " + status;
    }
}
