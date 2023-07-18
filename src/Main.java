import java.util.ArrayList;
import java.util.List;

class ObserverRunner {
    public static void main(String[] args) {
        NewsSite newsSite = new NewsSite();// создаем сайт новостей
        NewsListener listenerOne = new NewsListener("firstListener");//создаем подписчика
        NewsListener listenerTwo = new NewsListener("secondListener");//создаем подписчика
        newsSite.addObserver(listenerOne);//добавляем подписчика в список
        newsSite.addObserver(listenerTwo);//добавляем подписчика в список
        //добавляем две новости
        newsSite.addNews("First news");
        newsSite.addNews("Second news");
        //удаляем новость
        newsSite.removeNews("First news");
    }
}
// Интерфейс издателя
interface Observable{
    void addObserver(Observer observer);// Метод подписки на событие, в качестве аргумента принимает конкретного подписчика (слушателя) события.
    void removeObserver(Observer observer);// Метод отписки от события, в качестве аргумента принимает конкретного подписчика (слушателя) события.
    void notifyObserver();// метод оповещения всех подписчиков
}
// Интерфейс подписчика
interface Observer{
    // метод обработки события издателя
    void handleEvent(List<String> news);// в качестве аргумента принимает список строк news
}

//Класс конкретного издателя. Реализует интерфейс Observable, хранит список всех подписчиков.
class NewsSite implements Observable{
    private List<Observer> listeners = new ArrayList<>();// Список всех подписчиков.
    private List<String> news = new ArrayList<>();// Список новостей, об изменении которого будут оповещаться подписчики.
    // Переопределяем методы подписки, отписки и извещения подписчиков.
    @Override
    public void addObserver(Observer observer) {
        listeners.add(observer);
    }// добавляет конкретного подписчика в список

    @Override
    public void removeObserver(Observer observer) {
        listeners.remove(observer);
    }// удаляет конкретного подписчика из списка

    @Override
    public void notifyObserver() {
        // оповещает каждого конкретного подписчика из списка о событии, используя цикл forEach
        for (Observer observer : listeners){
            observer.handleEvent(news);
        }
    }
    // добавляем новость в список news
    public void addNews(String news){
        this.news.add(news);// добавляем непосредственно новость
        this.notifyObserver();// извещаем всех подписчиков конкретного издателя
    }
    // удаляем новость из списка
    public void removeNews(String news){
        this.news.remove(news);// удаляем новость
        this.notifyObserver();// извещаем подписчиков
    }
}
// Конкретный класс подписчика. Реализует интерфейс Observer.
class NewsListener implements Observer{
    private String name;// имя подписчика для наглядности
    public NewsListener(String name) {
        this.name = name;
    }// конструктор подписчика
    // переопределяем метод обработки подписчиком события
    @Override
    public void handleEvent(List<String> news) {
        System.out.println("I'm " + name + " I get news");
        System.out.println(news);// выводим новости
    }
}