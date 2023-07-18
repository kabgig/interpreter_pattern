class TemplateMethodeRunner {
    public static void main(String[] args) {
        DocWork docWork = new CSV();// Переменную docWork назначаем объектом CSV
        docWork.handleDoc();// Вызываем  шаблонный метод
        System.out.println("\n=============================\n");
        docWork = new PDF();// Переменную docWork назначаем объектом PDF
        docWork.handleDoc();// Вызываем  шаблонный метод
    }
}

abstract class DocWork {

    public void handleDoc() {
        loadDoc();
        rework();
        exit();
    }

    void loadDoc() {
        System.out.println("Document Loading");
    }

    abstract void rework();
    abstract void exit();
}

class PDF extends DocWork {
    @Override
    protected void rework() {
        System.out.println("Обработка PDF");
    }

    @Override
    protected void exit() {
        System.out.println("Конец PDF");
    }
}

class CSV extends DocWork {
    @Override
    protected void rework() {
        System.out.println("Обработка CSV");
    }

    @Override
    protected void exit() {
        System.out.println("Конец CSV");
    }
}