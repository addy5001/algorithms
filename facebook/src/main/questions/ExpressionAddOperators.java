package questions;

import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators {
    public List<String> addOperators(String num, int target) {
        List<String> results = new ArrayList<>();
        _backtrack(num, 0, 0, 0, 0, target, new ArrayList<>(), results);
        return results;
    }

    private void _backtrack(String num, int idx, long previousOperand, long currentOperand, long value, int target, List<String> ops, List<String> results) {
        if(idx == num.length()) {
            if(value == target && currentOperand == 0) {
                String result = ops.subList(1, ops.size()).stream().reduce("", String::concat);
                results.add(result);
            }
            return;
        }

        currentOperand = currentOperand*10 + Character.getNumericValue(num.charAt(idx));
        String currentOperandString = Long.toString(currentOperand);

        if(currentOperand > 0) {
            _backtrack(num, idx+1, previousOperand, currentOperand, value, target, ops, results);
        }

        // Addition
        ops.add("+");
        ops.add(currentOperandString);
        _backtrack(num, idx+1, currentOperand, 0, value+currentOperand, target, ops, results);
        ops.remove(ops.size() - 1);
        ops.remove(ops.size() - 1);

        if(ops.size() > 1) {
            // Subtraction
            ops.add("-");
            ops.add(currentOperandString);
            _backtrack(num, idx+1, -currentOperand, 0, value-currentOperand, target, ops, results);
            ops.remove(ops.size() - 1);
            ops.remove(ops.size() - 1);

            // Multiplication
            ops.add("*");
            ops.add(currentOperandString);
            _backtrack(num, idx+1, previousOperand * currentOperand, 0, value - previousOperand + (currentOperand * previousOperand), target, ops, results);
            ops.remove(ops.size() - 1);
            ops.remove(ops.size() - 1);
        }
    }
}
