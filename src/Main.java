class MainMediator {
    public static void main(String[] args) {
        Admin admin = new Admin();// создаем конкретного коллегу admin
        Editor editor = new Editor();// создаем конкретного коллегу editor
        new ConcreteMediator(admin, editor);

        System.out.println("\nAdmin send message: Hello");
        admin.send("Hello I'm admin");// admin отправляет сообщение посреднику
        System.out.println("\nEditor send message: Hello");
        editor.send("Hello I'm editor"); // editor отправляет сообщение посреднику
    }
}

interface Mediator {
    void sendMessage(String message, Collegue collegue);
}

class ConcreteMediator implements Mediator {
    private Admin admin;// ссылка на объект класса Admin
    private Editor editor;// Ссылка на объект класса Editor

    public ConcreteMediator(Admin admin, Editor editor) {
        this.admin = admin;
        this.editor = editor;
        editor.setMediator(this);
        admin.setMediator(this);
    }

    @Override
    public void sendMessage(String message, Collegue sender) {
        if (sender.equals(admin)) {// если сообщение отправляет admin, оно отправляется объекту editor
            editor.getMessage(message);
        } else if (sender.equals(editor)) {// если сообщение отправляет editor, оно отправляется объекту admin
            admin.getMessage(message);
        }
    }
}

abstract class Collegue {
    private Mediator mediator;// ссылка на посредника

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    void send(String message) {
        mediator.sendMessage(message, this);
    }

    abstract void getMessage(String message);// метод получения сообщения для наглядности работы примера
}

class Admin extends Collegue {
    @Override
    void getMessage(String message) {
        System.out.println("Admin get message: " + message);
    }
}

class Editor extends Collegue {
    @Override
    void getMessage(String message) {
        System.out.println("Editor get message: " + message);
    }
}