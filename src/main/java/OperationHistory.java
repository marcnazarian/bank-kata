import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OperationHistory {

    private List<Operation> operations = new ArrayList<>();

    public void printAllOperations(Screen screen) {
        operations.forEach(operation -> operation.print(screen));
    }

    public void add(Operation operation) {
        operations.add(operation);
    }

    public List<Operation> filteredBy(OperationType operationType) {
        return operations.stream().filter(o -> o.filterBy(operationType)).collect(Collectors.toList());
    }

    public List<Operation> filteredBy(Amount amount) {
        return operations.stream().filter(o -> o.filterBy(amount)).collect(Collectors.toList());
    }
}
