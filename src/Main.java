class Interpreter {
    public static void main(String[] args) {
        Parser parser = new Parser();
        Expression expression = parser.evaluate("-25+43-34");
        System.out.println(expression.interpret());
    }
}

interface Expression{
    int interpret();
}
//Создаем терминальное выражение для чисел
class NumberExpression implements Expression{
    private int number;
    public NumberExpression(int number) {// конструктор
        this.number = number;
    }
    @Override
    public int interpret() {// возвращает число
        return number;
    }
}
// Создаем нетерминальный класс, описывающий синтаксическое правило сложения чисел
class AdditionExpression implements Expression {
    private Expression leftOperand;// число слева от знака "+" в строке
    private Expression rightOperand;// число справа от знака "+" в строке

    public AdditionExpression(Expression leftOperand, Expression rightOperand) {// конструктор
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    @Override
    public int interpret(){// возвращает сумму двух чисел по бокам от знака "+" в строке
        return leftOperand.interpret() + rightOperand.interpret();
    }
}
class SubstractionExpression implements Expression{
    private Expression leftOperand;// число слева от знака "-" в строке
    private Expression rightOperand;// число справа от знака "-" в строке

    public SubstractionExpression(Expression leftOperand, Expression rightOperand) {// конструктор
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    @Override
    public int interpret(){// возвращает сумму двух чисел по бокам от знака "-" в строке
        return leftOperand.interpret() - rightOperand.interpret();
    }
}
// Служебный класс для обхода абстрактного дерева рекурсивным методом
// Конкретная реализация зависит от сложности и условия задачи
class Parser{
    Expression evaluate(String str){
        int position = str.length() - 1;
        while (position > 0){
            if (Character.isDigit(str.charAt(position))){
                position--;
            } else {
                Expression leftOperand = evaluate(str.substring(0, position));
                Expression rightOperand = new NumberExpression(
                        Integer.parseInt(str.substring(position + 1)));
                char operator = str.charAt(position);
                switch (operator) {
                    case '+': return new AdditionExpression(leftOperand, rightOperand);
                    case '-': return new SubstractionExpression(leftOperand, rightOperand);
                }
            }
        }
        int result = Integer.parseInt(str);
        return new NumberExpression(result);
    }
}