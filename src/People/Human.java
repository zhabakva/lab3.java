package People;

import Actions.*;
import Actions.Error;
import Constants.Cities;

import java.util.Objects;

public abstract class Human implements Name, City {
    private final String name;
    private Cities city;

    public Human(String name,  Cities city) {
        this.name = name;
        this.city = city;
        System.out.printf("%s in %s\n", this.name, this.city.toString());
    }
    public Human(Cities city) {
        this.name = "NoName";
        this.city = city;
        System.out.printf("NoName in %s\n", this.city.toString());
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, city);
    }
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Human))
            return false;
        Human someone = (Human) object;
        return this.name.equals(someone.name) &&
                this.city.equals(someone.city);
    }

    @Override
    public String toString() {
        return "Actions.Name: " + name + "Actions.City: " + city;
    }

    public Cities getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public void resettlement(Cities city) {
        class TrainStation implements Error {
            final Cities city;
            TrainStation(){
                this.city = Human.this.getCity();
            }
            TrainStation(Cities city){
                this.city = city;
            }
            boolean tryToTakeTrain(Cities city){
                return Math.random() > 0.5;
            }
            public String getError(){
                return "There isn't train from " + this.city + " to ";
            }
        }
        TrainStation st = new TrainStation();
        System.out.printf("%s tries to take the train to %s\n", this.getName(), this.getCity());
        if (st.tryToTakeTrain(city)) {
            this.city = city;
            System.out.printf("%s is in %s now\n", this.getName(), this.getCity());
        } else {
            System.out.println(st.getError() + city.toString());
        }
    }
}
