package simplefactory;

public class Client
{
    public static void main(String[] args)
    {
        SimpleFactory.makeProduct(Const.PRODUCT_A).show();
        SimpleFactory.makeProduct(Const.PRODUCT_B).show();
    }

    //抽象产品
    public interface Product
    {
        void show();
    }

    //具体产品：ProductA
    static class ConcreteProduct1 implements Product
    {
        public void show()
        {
            System.out.println("具体产品1显示...");
        }
    }

    //具体产品：ProductB
    static class ConcreteProduct2 implements Product
    {
        public void show()
        {
            System.out.println("具体产品2显示...");
        }
    }

    static class SimpleFactory
    {
        public static Product makeProduct(int kind)
        {
            switch (kind)
            {
                case Const.PRODUCT_A:
                    return new ConcreteProduct1();
                case Const.PRODUCT_B:
                    return new ConcreteProduct2();
            }
            return null;
        }
    }

    final class Const
    {
        static final int PRODUCT_A = 0;
        static final int PRODUCT_B = 1;
        static final int PRODUCT_C = 2;
    }
}
