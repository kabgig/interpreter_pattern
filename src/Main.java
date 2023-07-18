class StrategyRunner {
    public static void main(String[] args) {
        Context context = new Context();

        context.setStrategy(new FirstStrategy());//назначаем контексту конкретную стратегию
        context.executeStrategy("Запускаем стратегию");// запускам стратегию

        context.setStrategy(new SecondStrategy());//назначаем контексту другую конкретную стратегию
        context.executeStrategy("Запускаем стратегию");// запускам стратегию
    }
}

interface Strategy{
    void run(String string);// определяем метод, который будет вызывать конкретный алгоритм (стратегию)
}
class FirstStrategy implements Strategy{
    @Override
    public void run(String string) {
        System.out.println("Первый вариант решения "+ string);
    }
}
class SecondStrategy implements Strategy{
    @Override
    public void run(String string) {
        System.out.println("Второй вариант решения "+ string);
    }
}
class Context{
    private Strategy strategy; // ссылка на общий интерфейс
    void setStrategy(Strategy strategy){
        this.strategy=strategy;// сеттер стратегии
    }
    void executeStrategy(String string){
        strategy.run(string);// вызываем стратегию
    }
}