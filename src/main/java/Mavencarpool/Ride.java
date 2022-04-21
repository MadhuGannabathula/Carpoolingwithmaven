package Mavencarpool;
import javax.persistence.*;

@Entity
@Table(name = "Rides")
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String source;
    private String destination;
    private int fare;
    private String avilable = "available";
    private int createrID;
    private int bookerID;


    public String getAvilable() {
        return avilable;
    }

    public void setAvilable(String avilable) {
        this.avilable = avilable;
    }

    public Ride() {
    }

    public Ride(String source, String destination, int fare, int createrID){
        this.source = source;
        this.destination = destination;
        this.fare = fare;
        this.createrID = createrID;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", fare=" + fare +
                ", id=" + id +
                '}';
    }

    public int getBookerID() {
        return bookerID;
    }

    public void setBookerID(int bookerID) {
        this.bookerID = bookerID;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getId() {
        return id;
    }

    public int getFare() {
        return fare;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreaterID() {
        return createrID;
    }

    public void setCreaterID(int createrID) {
        this.createrID = createrID;
    }
}
