class StateRunner {
    public static void main(String[] args) {
        Article article = new Article();// Создаем новую статью.
        article.publish(); //вызываем метод опубликовать
        article.publish(); //вызываем метод опубликовать
        article.publish(); //вызываем метод опубликовать
    }
}

interface State{
    void changeState(Article article);
}

class Draft implements State{
    @Override
    public void changeState(Article article) {
        article.setState(new Moderation());// Устанавливает статью в состояние Модерация.
        System.out.println("Draft State: Article waiting for moderation...");
    }
}

class Moderation implements State{
    @Override
    public void changeState(Article article) {
        article.setState(new Publication());// Устанавливает статью в состояние Публикация.
        System.out.println("Moderation State: Publishing Article...");
    }
}

class Publication implements State{
    @Override
    public void changeState(Article article) {// Не изменяет состояние статьи после публикации.
        System.out.println("Publication State: Nothing happens...");
    }
}

class Article{
    private State state;// Ссылка на объект конкретного состояния

    public void setState(State state) {
        this.state = state;
    }

    public Article(){
        this.state = new Draft();// При создании статьи устанавливает состояние в Черновик.
        System.out.println("Create Article...");// Сообщение о создании статьи для наглядности.
    }

    public void publish() {
        state.changeState(this);// В качестве аргумента принимает объект статьи.
    }
}