package Agencies;

import Actions.*;
import Actions.Error;
import Constants.Cities;
import Finances.*;

import java.util.Objects;

public class Newspaper implements Name, City, Error {

    private final String name;
    private Cities city;
    private int days;

    public Newspaper(String name, Cities city) {
        this.name = name;
        this.city = city;
        System.out.printf("Agencies.Newspaper \"%s\" is open!\n", this.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.city);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Newspaper))
            return false;
        Newspaper newspaper = (Newspaper) object;
        return this.getName().equals(newspaper.getName()) &&
                this.getCity().equals(newspaper.getCity());
    }

    @Override
    public String toString() {
        return "Actions.Name: " + this.getName() + "Actions.City: " + this.getCity();
    }


    public void releaseArticle(Barge barge, boolean growth) {
        System.out.printf("Agencies.Newspaper %s tries to change the price of stocks from %s\n", this.getName(), barge.getName());
        if (barge.getCity() == this.getCity()) {
            Name news = new Name() {
                @Override
                public String getName() {
                    return name;
                }
            };
            System.out.printf("An article of the newspaper %s in the %s was published!\n", this.getName(), this.getCity());
            if (growth) {
                System.out.printf("Finances.Stock prices on %s started to rise.\n", barge.getName());
            } else {
                System.out.printf("Finances.Stock prices on %s started to fall.\n", barge.getName());
            }

            for (int i = 0; i < Math.ceil(Math.random() * 5); i++)
            {
                days++;
            }
            if (growth) {
                barge.increaseCosts(10.d);
                System.out.printf("Prices increased for %d days", days);
            }
            else{
                barge.decreaseCosts(10.d);
                System.out.printf("Prices decreased for %d days", days);
            }
            barge.printStocksAndCosts();
            days = 0;
            }
        else System.out.println(Newspaper.getError());
        }
    public static String getError(){
        return "We can't write an article because the barge and the newspaper are not located in the same city";
    }
    }