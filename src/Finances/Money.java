package Finances;

import Actions.Error;

public class Money implements Error {
    private double money;
    public Money(double money){
        this.money = money;
    }
    public double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
    public static String getError(){
        return("We can't perform the operation because there aren't enough money at buyer's account\n");
    }
}
