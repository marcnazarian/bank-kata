package org.craftsmanship.gre;

import java.util.ArrayList;
import java.util.List;

public class OperationHistory {//org.craftsmanship.gre.OperationHistory history = new org.craftsmanship.gre.OperationHistory();
    private List<Operation> history = new ArrayList<Operation>();

    public OperationHistory() {
    }

    public void add(Operation operation) {
        history.add(operation);
    }

    public void print(Screen screen) {
        for (Operation operation: history) {
            screen.writeOperation(operation);
        }
    }
}