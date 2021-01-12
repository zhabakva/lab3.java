import Agencies.Barge;
import Agencies.Newspaper;
import Constants.Cities;
import Exceptions.ByingException;
import Exceptions.StocksException;
import Finances.Money;
import Finances.Stock;
import People.*;
import Actions.*;
import Agencies.*;
import Constants.*;
import Finances.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args)  {
        List<Oligarch> oligarchs = new ArrayList<Oligarch>();
        Newspaper Zhaba_news = new Newspaper("Zhaba_news", Cities.DAVILON);
        Newspaper Toad_news = new Newspaper("Toad_news", Cities.PANOPTICON);
        Barge davilon_barge = new Barge("Davilon barge", Cities.DAVILON);
        Barge greenberg_barge = new Barge("Agencies.Barge of Greenberg", Cities.GREENBERG);

        Oligarch Vasya = new Oligarch("Vasya", new Money(140.12), Cities.DAVILON);
        oligarchs.add(Vasya);
        davilon_barge.addCosts(new Stock[]{new Stock("Sber"), new Stock("Apple")});
        greenberg_barge.addCosts(new Stock[]{new Stock("Tinkoff")});
        Slave Andrew = new Slave("Andrew", Cities.DAVILON);
        Oligarch NoName = new Oligarch(new Money(68.4), Cities.GREENBERG);
        //oligarchs.add(NoName);
        Oligarch Vasya1 = new Oligarch("Vasya1", new Money(1400.12), Cities.DAVILON);
        oligarchs.add(Vasya1);
        try {
            NoName.UseTheSlave(davilon_barge, new Stock("Sber"), 3, Andrew);
        }catch(StocksException e){
            System.out.println(e.getMessage());
        }
        try{
        Vasya.BuyStock(davilon_barge, new Stock("Sber"), 1);
        }catch(StocksException e){
            System.out.println(e.getMessage());
        }
        try{
            Vasya1.BuyStock(davilon_barge, new Stock("Sber"), 2);
        }catch(StocksException e){
            System.out.println(e.getMessage());
        }
        try{
        NoName.BuyStock(greenberg_barge, new Stock("Apple"), 2);
        }catch(StocksException e){
            System.out.println(e.getMessage());
        }
        Zhaba_news.releaseArticle(davilon_barge, true);
        try {
            NoName.BuyStock(davilon_barge, new Stock("Tinkoff"), 2);
        }catch(StocksException e){
            System.out.println(e.getMessage());
        }
        Toad_news.releaseArticle(greenberg_barge, false);
        try {
            NoName.BuyStockFromSomebody(Vasya, new Stock("Apple"), 2, davilon_barge);
        }catch(ByingException | StocksException e){
            System.out.println(e.getMessage());
        }

        Oligarch.CoolestOligar theMainBuddy = new Oligarch.CoolestOligar(oligarchs);
        try {
            theMainBuddy.find_coolest();
        }catch(StocksException e){
            System.out.println(e.getMessage());
        }
    }

    }

