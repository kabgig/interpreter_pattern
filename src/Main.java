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

class Map{
    private String name;
    private Date date;

    public void setNameAndDate(String name) {
        this.name = name;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "Map" +
                "\nname=" + name +
                "\ndate=" + date + "\n";
    }

    public Snapshot createSnapshot(){
        return new Snapshot(name);
    }

    public void loadSnapshot(Snapshot snapshot){
        this.name = snapshot.getName();
        this.date = snapshot.getDate();
    }
}

class Snapshot{
    private final String name;
    private final Date date;

    public Snapshot(String name) {
        this.name = name;
        this.date = new Date();
    }

    public String getName() {
        return name;
    }
    public Date getDate() {
        return date;
    }
}

class Caretaker {
    Snapshot backup;

    public Snapshot getBackup() {
        return backup;
    }

    public void setBackup(Snapshot backup) {
        this.backup = backup;
    }
}