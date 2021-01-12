package Agencies;

import Actions.*;
import Constants.Cities;
import Finances.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Barge implements City, Name {

    private final String name;
    private final Cities city;
    private HashMap<Stock, Double> costs;
    private Money randomMoney;
    public Barge(String name, Cities city){
        this.name = name;
        this.city = city;
        this.costs = new HashMap<Stock, Double>();
        System.out.printf("%s is open!\n", this.getName());
    }

    @Override
    public Cities getCity(){
        return city;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), this.getCity(), costs);
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof Barge))
            return false;
        Barge barge = (Barge) o;
        return this.getName().equals(barge.getName()) &&
                this.getCity().equals(barge.getCity()) &&
                this.costs.equals(barge.costs);
    }

    @Override
    public String toString(){
        return "Actions.Name: " + this.getName() + "Actions.City: " + this.getCity() + "Costs:\n" + this.costs;
    }
    public void addCosts(HashMap<Stock, Double> costs){                         //в случае, когда задана цена
        for (Map.Entry cost: costs.entrySet())
            if (!(this.costs.containsKey((Stock) cost.getKey())))
                this.costs.put((Stock) cost.getKey(), (double) cost.getValue());
    }
    public void addCosts(Stock[] stocks){                                       //если цена не задана, меняем ее рандомно
        System.out.printf("%s sets stock prices:\n", this.getName());
        for (Stock stock : stocks)
            if (!(costs.containsKey(stock))) {

                costs.put(stock, Math.random() * 100 + 11);
                System.out.printf("%s: %.2f\n", stock.getName(), costs.get(stock));
            }

    }

    public void increaseCosts(Double multiplier){
        for (Map.Entry cost: costs.entrySet())
            this.costs.replace((Stock) cost.getKey(), (double) cost.getValue() + multiplier);
    }
    public void decreaseCosts(Double multiplier){
        for (Map.Entry cost: costs.entrySet())
            this.costs.replace((Stock) cost.getKey(), (double) cost.getValue() - multiplier);
    }
    public void printStocksAndCosts(){
        for (Map.Entry cost: costs.entrySet())
            System.out.printf("%s: %.2f\n", ((Stock) cost.getKey()).getName(), (double) cost.getValue());
    }
    public double getCost(Stock stock){
        return costs.get(stock);
    }
    public boolean hasStock(Stock stock){
        return costs.containsKey(stock);
    }
    public class EvacuationPlan {
        int exitsAmount;
        int floorsAmount;
        int windowsAmount;
        public EvacuationPlan(int exitsAmount, int floorsAmount, int windowsAmount){
            this.exitsAmount = exitsAmount;
            this.floorsAmount = floorsAmount;
            this.windowsAmount = windowsAmount;
        }
        public void setExits_amount(int exitsAmount){
            this.exitsAmount = exitsAmount;
        }
        public void setFloors_amount(int floorsAmount){
            this.floorsAmount = floorsAmount;
        }
        public void setWindows_amount(int windowsAmount){
            this.windowsAmount = windowsAmount;
        }

        public int getExitsAmount() {
            return exitsAmount;
        }

        public int getFloorsAmount() {
            return floorsAmount;
        }

        public int getWindowsAmount() {
            return windowsAmount;
        }

    }
    public static String getError(){
       return("We can't perform the operation because the barge doesn't sell this type of stocks");
    }
}
