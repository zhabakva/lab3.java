package Finances;

import Actions.*;

import java.util.Objects;
public class Stock implements Name {
        private final String name;
        public Stock(String name){
            this.name = name;
        }
        @Override
        public int hashCode() {
            return Objects.hash(name);                              //хэш акции не зависит от биржи, он зависит только от имени
        }
        @Override
        public boolean equals(Object o){
            if (!(o instanceof Stock))
                return false;
            return this.getName().equals(((Stock) o).getName());
        }
        @Override
        public String toString() {
            return "Finances.Stock's name: " + this.getName();
        }
        public String getName(){
            return this.name;
        }
}

