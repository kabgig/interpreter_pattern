class VisitorRunner {
    public static void main(String[] args) {
        ProductCollection pc = new ProductCollection();// создаем коллекцию товаров
        RubPriceVisitor rubPriceVisitor = new RubPriceVisitor();// создаем посетителя, которые переведет цену в российские рубли
        BynPriceVisitor bynPriceVisitor = new BynPriceVisitor();// создаем посетителя, которые переведет цену в белорусские рубли

        System.out.println("RUB");
        System.out.println(pc.getCost(rubPriceVisitor));// пересчитываем стоимость товаров корзины в российские рубли
        System.out.println("\n++++++++++++++++++++++++\n");
        System.out.println("BYN");
        System.out.println(pc.getCost(bynPriceVisitor));// пересчитываем стоимость товаров корзины в белорусские рубли
    }
}

interface Product {
    double getCost(Visitor visitor);
}

class Bike implements Product {
    private double usdPrice;// цена в долларах
    public Bike(double usdPrice) {
        this.usdPrice = usdPrice;
    }
    public double getUsdPrice() {
        return usdPrice;
    }

    @Override
    public double getCost(Visitor visitor) {
        return visitor.bikePriceVisitor(this);
    }
}

class TV implements Product {
    private double usdPrice;// цена

    public TV(double usdPrice) {
        this.usdPrice = usdPrice;
    }

    public double getUsdPrice() {
        return usdPrice;
    }

    @Override
    public double getCost(Visitor visitor) {
        return visitor.tvPriceVisitor(this);
    }
}

class ProductCollection {
    Product[] products;

    public ProductCollection() {
        this.products = new Product[]{
                new Bike(128),
                new Bike(223),
                new TV(414),
                new TV(214),
                new TV(164),
                new Bike(1134)
        };
    }

    public double getCost(Visitor visitor) {
        double price = 0;// стоимость товаров в пустой корзине
        for (Product product : products) {// перебираем все элементы корзины
            price += product.getCost(visitor); // переводим в российские или белорусские рубли и подсчитываем общую сумму товаров в корзине
        }
        return price;// возвращаем стоимость товаров в корзине в российских или белорусских рублях
    }
}

interface Visitor {
    double bikePriceVisitor(Bike bike);
    double tvPriceVisitor(TV tv);
}

class BynPriceVisitor implements Visitor {
    @Override
    public double bikePriceVisitor(Bike bike) {
        double price = bike.getUsdPrice() * 2.53;// переводим цену в белорусские рубли
        System.out.println("Bike costs: " + price + "BYN");//выводим цену на экран
        return price;// возвращаем новую цену
    }

    @Override
    public double tvPriceVisitor(TV tv) {
        double price = tv.getUsdPrice() * 2.53;// переводим цену в белорусские рубли
        System.out.println("TV costs: " + price + "BYN");//выводим цену на экран
        return price;// возвращаем новую цену
    }
}

class RubPriceVisitor implements Visitor {
    @Override
    public double bikePriceVisitor(Bike bike) {
        double price = bike.getUsdPrice() * 72.17;// переводим цену в российские рубли
        System.out.println("Bike costs: " + price + "RUB");//выводим цену на экран
        return price;// возвращаем новую цену
    }

    @Override
    public double tvPriceVisitor(TV tv) {
        double price = tv.getUsdPrice() * 72.17;// переводим цену в российские рубли
        System.out.println("TV costs: " + price + "RUB");//выводим цену на экран
        return price;// возвращаем новую цену
    }
}