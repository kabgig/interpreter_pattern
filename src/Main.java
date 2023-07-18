// клиент для проверки кода
class IteratorMain {
    public static void main(String[] args) {
        ConcreteAggregate ca = new ConcreteAggregate();// Создаем коллекцию
        Iterator iterator = ca.getIterator();
        while (iterator.hasNext()){// пока в коллекции есть следующие элементы...
            System.out.println(iterator.next());//выводим их на экран, используя метод next() итератора
        }
    }
}

class ConcreteAggregate implements Aggregate{
    private String[] patterns = {"Singleton", "Factory", "Interpreter", "Decorator", "Facade", "Prototype"};

    @Override
    public Iterator getIterator() {// переопределяем метод getIterator() интерфейса Aggregate
        return new PatternsIterator(patterns);// вызываем конструктор итератора и передаем ссылку на массив
    }
}

interface Iterator{
    boolean hasNext();// вернет true, если в массиве есть не пройденные элементы
    Object next();// возвращает следующий элемент
}

class PatternsIterator implements Iterator{// переопределяем методы интерфейса
    private final String[] patterns;// Ссылка на массив паттернов
    int index = 0;// Переменная index соответствует индексу элемента в массиве.

    PatternsIterator(String[] patterns) {
        this.patterns = patterns;
    }

    @Override
    public boolean hasNext() {
        if (index < patterns.length){
            return true;// если index меньше длины массива - возвращает true
        }  else {
            return false;// иначе возвращает false, т.е. коллекция закончилась
        }
    }

    @Override
    public Object next() {
        return patterns[index++];// возвращаем следующий элемент коллекции, после чего инкрементируем index
    }
}

interface Aggregate{// описывает метод, который возвращает нам конкретный итератор
    Iterator getIterator();
}