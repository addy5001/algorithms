package ramesh.aadhavan.misc;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class PostfixExpression {

    enum Operator {
        ADD("+"),
        SUBTRACT("-"),
        MULTIPLY("*"),
        DIVIDE("/");

        private final String operator;

        Operator(String operator) {
            this.operator = operator;
        }

        public String getOperator() {
            return operator;
        }
    }

    private static final HashMap<String, Operator> OPERATOR_MAP = new HashMap<>();
    private final Deque<Integer> stack = new ArrayDeque<>();

    static {
        OPERATOR_MAP.put("+", Operator.ADD);
        OPERATOR_MAP.put("-", Operator.SUBTRACT);
        OPERATOR_MAP.put("*", Operator.MULTIPLY);
        OPERATOR_MAP.put("/", Operator.DIVIDE);
    }

    public int evaluate(String[] operands) {
        for(String s : operands) {
            if(OPERATOR_MAP.containsKey(s)) {
                int operand1 = stack.pop();
                int operand2 = stack.pop();
                stack.push(_operate(operand1, operand2, OPERATOR_MAP.get(s)));
            }
            else {
                stack.push(Integer.parseInt(s));
            }
        }

        return stack.pop();
    }

    private int _operate(int operand1, int operand2, Operator op) {
        int result = 0;

        switch (op) {
            case ADD: {
                result += operand1 + operand2;
                break;
            }
            case SUBTRACT: {
                result += operand2 - operand1;
                break;
            }
            case MULTIPLY: {
                result += operand1 * operand2;
                break;
            }
            case DIVIDE: {
                result += operand2 / operand1;
                break;
            }
            default: { }
        }

        return result;
    }

}
