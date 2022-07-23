package exam4;
//package test;

public class BE extends MyRecursiveTask<Double> implements Expression {

    private static final long serialVersionUID = 1L;

    char operator;
    Expression left, right;


    public BE(char operator, Expression left, Expression right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    @Override
    public Double calculate() {
        return compute();
    }

    @Override
    protected Double compute() {
        if (right instanceof BE) {
            //If the right item is BE, calculate the right BE and give a number
            ((BE) right).testFork();

            double leftDouble = left.calculate();
            double rightDouble = ((BE) right).join();

            return activeoprtaor(leftDouble, rightDouble);
        }
        //If both left and right are numbers return the result
        return activeoprtaor(left.calculate(), right.calculate());

    }

    private double activeoprtaor(double left, double right) {
        switch (operator) {
            case '+':
                return left + right;
            case '-':
                return left - right;
            case '/':
                return left / right;
            case '*':
                return left * right;
        }
        return 0;
    }
}
