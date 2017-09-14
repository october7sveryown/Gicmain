package app.gtuinnovationcouncil.gicapp;

import java.util.Date;

/**
 * Created by Abhi on 25-01-2017.
 */

public class MyData {

    private int event_id;
    private String event_name,event_venue,event_register_link,event_description;
    private Date event_date;
    private String event_starttime;
    private String event_endtime;
    private String event_img;

    public MyData(int event_id, String event_name, String event_venue, Date event_date, String event_register_link, String event_description, String event_starttime, String event_endtime,String event_img) {
        this.event_id = event_id;
        this.event_name = event_name;
        this.event_venue = event_venue;
        this.event_date = event_date;
        this.event_register_link=event_register_link;
        this.event_description=event_description;
        this.event_starttime=event_starttime;
        this.event_endtime=event_endtime;
        this.event_img = event_img ;
    }

    public String getEvent_img() {
        return event_img;
    }

    public void setEvent_img(String event_img) {
        this.event_img = event_img;
    }

    public int getEvent_id() {
        return event_id;

    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_venue() {
        return event_venue;
    }

    public void setEvent_venue(String event_venue) {
        this.event_venue = event_venue;
    }

    public String getEvent_register_link() {
        return event_register_link;
    }

    public void setEvent_register_link(String event_register_link) {
        this.event_register_link = event_register_link;
    }

    public String getEvent_description() {
        return event_description;
    }

    public void setEvent_description(String event_description) {
        this.event_description = event_description;
    }

    public Date getEvent_date() {
        return event_date;
    }

    public void setEvent_date(Date event_date) {
        this.event_date = event_date;
    }

    public String getEvent_starttime() {
        return event_starttime;
    }

    public void setEvent_starttime(String event_starttime) {
        this.event_starttime = event_starttime;
    }

    public String getEvent_endtime() {
        return String.valueOf(event_endtime);
    }

    public void setEvent_endtime(String event_endtime) {
        this.event_endtime = event_endtime;
    }
}
