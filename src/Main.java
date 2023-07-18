import java.util.Date;

class Memento {
    public static void main(String[] args) {
        Map map = new Map();// создаем объект, состояние которого нужно сохранить
        Caretaker caretaker = new Caretaker();// создаем объект, который сохранит объект-хранитель

        System.out.println("Creating new Map...");
        map.setNameAndDate("My Map");// устанавливаем имя карте
        System.out.println(map);// выводим состояние карты

        System.out.println("Saving current state...");
        /* создаем бэкап, вызывая метод setBackup() объекта caretaker, который принимает в качестве аргумента объект Snapshot, который в свою очередь получаем из метода createSnapshot() нашего объекта Map. */
        caretaker.setBackup(map.createSnapshot());

        System.out.println("Updating name of Map...");
        map.setNameAndDate("My checked Map");// устанавливаем новое имя нашему объекту Map
        System.out.println(map);// выводим toString()

        System.out.println("Rolling back to oldest Name...");
        map.loadSnapshot(caretaker.getBackup());// совершаем откат до предыдущего состояния
        System.out.println(map);// выводим результат
    }
}

//Класс карты. Соответствует на схеме Originator-у. Его состояние будем сохранять.
class Map{
    //Внутреннее состояние объекта, которое нужно сохранить
    private String name;
    private Date date;
    // конструктор не создаем, будем использовать по умолчанию
    // метод setNameAndDate() устанавливает имя из аргумента, а дату создает текущую
    public void setNameAndDate(String name) {
        this.name = name;
        this.date = new Date();
    }

    @Override // toString переопределяем для наглядности
    public String toString() {
        return "Map" +
                "\nname=" + name +
                "\ndate=" + date + "\n";
    }
    // создает снимок состояния объекта Map (создает резервную копию)
    public Snapshot createSnapshot(){
        return new Snapshot(name);
    }
    // восстанавливает прежнее состояние Map из резервной копии
    public void loadSnapshot(Snapshot snapshot){
        this.name = snapshot.getName();
        this.date = snapshot.getDate();
    }
}
// класс-хранитель состояния нашей карты, содержит поля, аналогичные классу Map
class Snapshot{
    private final String name;
    private final Date date;
    // конструктор принимает name в качестве аргумента, дату устанавливает текущую
    public Snapshot(String name) {
        this.name = name;
        this.date = new Date();
    }
    // геттеры имени и даты
    public String getName() {
        return name;
    }
    public Date getDate() {
        return date;
    }
}
// отвечает за сохранение объекта-хранителя, содержит ссылку на Snapshot. Отвечает за откат к прежнему состоянию.
class Caretaker {
    Snapshot backup;
    // возвращает сохраненный объект класса-хранителя
    public Snapshot getBackup() {
        return backup;
    }
    // сохраняет объект класса хранителя
    public void setBackup(Snapshot backup) {
        this.backup = backup;
    }
}