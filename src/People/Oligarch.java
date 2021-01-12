package People;

import Actions.*;
import Actions.Error;
import Agencies.*;
import Constants.Cities;
import Exceptions.ByingException;
import Exceptions.StocksException;
import Finances.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;




public class Oligarch extends Human implements Error {
    private Money cash;
    private Integer amount;
    private Map<Stock, Integer> stocks;  //integer здесь - количество акций
    private boolean slave_flag = false;

    public Oligarch(String name, Money cash, Map<Stock, Integer> stocks, Cities city) {
        super(name, city);
        this.cash = cash;
        this.stocks = stocks;
        System.out.printf("%s with %.2f santiks in %s\n", this.getName(), this.cash.getMoney(), this.getCity().toString());
        this.printStocksAndAmount();
    }
    public Oligarch(Money cash, Cities city) {
        super(city);
        this.cash = cash;
        this.stocks = new HashMap<Stock, Integer>();
        System.out.printf("NoName with %.2f santiks in %s\n", this.cash.getMoney(), this.getCity().toString());
    }

    public Oligarch(Money cash, Map<Stock, Integer> stocks, Cities city) {
        super(city);
        this.cash = cash;
        this.stocks = stocks;
        System.out.printf("NoName with %.2f santiks in %s\n", this.cash.getMoney(), this.getCity().toString());
        this.printStocksAndAmount();
    }

    public Oligarch(String name, Money cash, Cities city) {
        super(name, city);
        this.cash = cash;
        this.stocks = new HashMap<Stock, Integer>();
        System.out.printf("%s with %.2f santiks in %s\n", this.getName(), this.cash.getMoney(), this.getCity().toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), cash, stocks, this.getCity());
    }
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Oligarch))
            return false;
        Oligarch someone = (Oligarch) object;
        return this.getName().equals(someone.getName()) &&
                this.stocks.equals(someone.stocks) &&
                this.cash.equals(someone.cash) &&
                this.getCity().equals(someone.getCity());
    }

    @Override
    public String toString() {
        return "Actions.Name: " + this.getName() + "Finances.Money: " + cash + "Stocks: " + stocks + "Actions.City: " + this.getCity();
    }


    public double getCash() {
        return cash.getMoney();
    }


    public int getAmount(Stock stock) throws StocksException {
        if (stocks.containsKey(stock)){
            return stocks.get(stock);
        }
        else{
            throw new StocksException("There is no this type of stocks");
        }


    }

    public Map<Stock, Integer> getStocks() {
        return stocks;
    }

    public void setCash(double cash) {
        this.cash.setMoney(cash);
    }

    public static String getError(){
        return("We can't perform the operation because the buyers city doesn't match the barge's\n");
    }

    public void setAmount(Stock stock, int amount) {
        if (stocks.containsKey(stock))
            stocks.replace(stock, stocks.get(stock) + amount);
        else
            stocks.put(stock, amount);
    }


    public void printStocksAndAmount() {
        System.out.printf("%s has stocks in pieces:\n", this.getName());
        for (Map.Entry stock : stocks.entrySet())
            System.out.printf("%s: %d\n", ((Stock) stock.getKey()).getName(), (int) stock.getValue());
    }
    public void UseTheSlave(Barge barge, Stock stock, int amount, Slave slave) throws StocksException{
        slave_flag = true;
        System.out.printf("%s uses his slave %s to buy new stocks:\n", this.getName(), slave.getName());
        if (slave.getCity() == barge.getCity()){
            this.BuyStock(barge, stock, amount);
        }
        else
        {
            slave.resettlement(barge.getCity());
            System.out.printf("People.Slave ");
            this.BuyStock(barge, stock, amount);
        }
        slave_flag = false;
    }
    public void BuyStock(Barge barge, Stock stock, int amount) throws StocksException{
        System.out.printf("%s try to buy %d %s stocks from %s\n", this.getName(), amount, stock.getName(), barge.getName());
        if (barge.hasStock(stock)) {
            if (this.getCity() == barge.getCity()|(slave_flag == true)){
                if (barge.getCost(stock) * amount <= this.getCash()) {
                    this.setCash(this.getCash() - barge.getCost(stock) * amount);
                    this.setAmount(stock, amount);
                    System.out.printf("Now %s has %d %s stocks and %f santiks\n", this.getName(), this.getAmount(stock), stock.getName(), this.getCash());
                } else
                    System.out.printf(Money.getError(), this.getName());
            } else
                System.out.printf(Oligarch.getError());
        } else
            System.out.println(Barge.getError());
    }
    public void BuyStockFromSomebody(Oligarch oligarch, Stock stock, int amount, Barge barge) throws ByingException, StocksException {
        System.out.printf("%s try to buy %d %s stocks from %s in %s\n", this.getName(), amount, stock.getName(), oligarch.getName(), barge.getCity());
        if (oligarch.getAmount(stock) >= amount){
            if (((this.getCity() == oligarch.getCity())&&(barge.getCity() == this.getCity()))|(slave_flag == true)){
                if (barge.getCost(stock) * amount <= this.getCash()) {
                    this.setCash(this.getCash() - barge.getCost(stock) * amount);
                    oligarch.setCash(oligarch.getCash() + barge.getCost(stock) * amount);
                    oligarch.setAmount(stock, -amount);
                    this.setAmount(stock, amount);
                    System.out.printf("Now %s has %d %s stocks and %f santiks\n", this.getName(), this.getAmount(stock), stock.getName(), this.getCash());
                } else
                    throw new ByingException("We can't perform the operation because there aren't enough money at buyer's account\n");
            } else
                throw new ByingException("We can't perform the operation because the buyers city doesn't match the barge's\n");
        } else
            throw new ByingException("We can't perform the operation because the barge doesn't sell this type of stocks");
    }
    public static class CoolestOligar{
        private List<Oligarch> oligarchs;
        public CoolestOligar(List<Oligarch> oligarchs){
            this.oligarchs = oligarchs;
        }
        public void find_coolest() throws StocksException{
            Oligarch coolest = oligarchs.get(0);
            int k1 = 0;
            int k2 = 0;

            if (oligarchs.get(1).getStocks().isEmpty())
                throw new StocksException(oligarchs.get(1).getName() + " don't have stocks!");
            else {
                for (Map.Entry stock : coolest.getStocks().entrySet())
                    k1 += (int) stock.getValue();
            }

            for(int i = 1; i < oligarchs.size(); ++i){
                if (oligarchs.get(i).getStocks().isEmpty())
                    throw new StocksException(oligarchs.get(i).getName() + " don't have stocks!");
                else {
                    for (Map.Entry stock : oligarchs.get(i).getStocks().entrySet())
                        k2 += (int) stock.getValue();
                    if (k2 > k1) {
                        coolest = oligarchs.get(i);
                        k1 = k2;
                    }
                    k2 = 0;
                }

                System.out.println(coolest.getName() + " is coolest oligarch in the world!!");
            }
        }
    }
}
