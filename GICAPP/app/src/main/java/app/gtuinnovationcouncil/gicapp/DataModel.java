package app.gtuinnovationcouncil.gicapp;

/**
 * Created by Abhi on 24-01-2017.
 */

public class DataModel {

    String fest;
    String place;
    String date;
    int id_;

    public DataModel(String fest, String place,int id_,String date) {
        this.fest = fest;
        this.place = place;
        this.date=date;
    }

    public String getName() {
        return fest;
    }

    public String getVersion() {
        return place;
    }

    public String getImage() {
        return date;
    }
    public int getId() {
        return id_;
    }

}
