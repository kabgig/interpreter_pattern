// Клиентский класс
class StateRunner {
    public static void main(String[] args) {
        Article article = new Article();// Создаем новую статью.
        article.publish(); //вызываем метод опубликовать
        article.publish(); //вызываем метод опубликовать
        article.publish(); //вызываем метод опубликовать
    }
}
// Общий интерфейс всех состояний статьи
interface State{
    //Метод, который будет изменять состояние статьи (контекста) в зависимости от текущего состояния. В качестве аргумента принимает объект класса Article.
    void changeState(Article article);
}

// Состояние Черновик
class Draft implements State{
    @Override
    public void changeState(Article article) {
        article.setState(new Moderation());// Устанавливает статью в состояние Модерация.
        System.out.println("Draft State: Article waiting for moderation...");
    }
}
// Состояние Модерация
class Moderation implements State{
    @Override
    public void changeState(Article article) {
        article.setState(new Publication());// Устанавливает статью в состояние Публикация.
        System.out.println("Moderation State: Publishing Article...");
    }
}
// Состояние Публикация
class Publication implements State{
    @Override
    public void changeState(Article article) {// Не изменяет состояние статьи после публикации.
        System.out.println("Publication State: Nothing happens...");
    }
}
// Класс статьи. Является контекстом состояния. Содержит ссылку на объект состояния.
class Article{
    private State state;// Ссылка на объект конкретного состояния
    // Сеттер состояния
    public void setState(State state) {
        this.state = state;
    }
    // конструктор статьи
    public Article(){
        this.state = new Draft();// При создании статьи устанавливает состояние в Черновик.
        System.out.println("Create Article...");// Сообщение о создании статьи для наглядности.
    }
    // Метод "опубликовать" в зависимости от текущего состояния статьи будет выполнять разный функционал.
    public void publish() {
        state.changeState(this);// В качестве аргумента принимает объект статьи.
    }
}