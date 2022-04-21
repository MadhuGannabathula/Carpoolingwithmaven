package Mavencarpool;

import javax.persistence.*;
import java.util.List;

public class User {
    static EntityManagerFactory factory = Persistence.createEntityManagerFactory("jp");
    static EntityManager manager = factory.createEntityManager();
    static EntityTransaction transcation = manager.getTransaction();
    static Ride ride;
    public User() {

    }

    public void createRide(String source, String destination, int fare, int createrid) {
        ride = new Ride(source,destination,fare,createrid);
        try{
            transcation.begin();
            manager.persist(ride);
            transcation.commit();
            System.out.println("Ride created successfully");
        }
        catch (Exception e) {
            transcation.rollback();
            System.out.println("Ride creation failed");
        }
    }

    public void deleteRide(int id, int createrID) {
        try{
            transcation.begin();
            Ride r = manager.find(Ride.class,id);
            if(r.getCreaterID() == createrID || createrID == 1){
                manager.remove(r);
                System.out.println("Ride deleted successfully");
            }
            else{
                System.out.println("Ride deletion failed");
            }
            transcation.commit();
        }
        catch (Exception e) {
            transcation.rollback();
            System.out.println("Ride deletion failed");
        }
    }
    public void updateRide(int rideId,String source, String destination, int fare, int createrid){
        ride = new Ride(source,destination,fare,createrid);
        ride.setId(rideId);
        try{
            transcation.begin();
            manager.merge(ride);
            transcation.commit();
            System.out.println("Ride updated successfully");
        }
        catch (Exception e) {
            transcation.rollback();
            System.out.println("You can't update ride");
        }
    }

    public void availableRides(int userid) {
        TypedQuery<Ride> query = (TypedQuery<Ride>) manager.createQuery("Select r from Ride r where avilable = 'available' and createrid != :userid",Ride.class);
        query.setParameter("userid",userid);
        try{
            List<Ride> ridelist = query.getResultList();
            for(Ride r: ridelist){
                System.out.println(r);
            }
        }
        catch(Exception e){
            System.out.println("Please try again later;");
        }

    }
    public void bookRide(int rideId,int userId) {
        try {
            transcation.begin();
            Ride r = manager.find(Ride.class, rideId);
            if (r.getAvilable().equals("available")) {
                r.setAvilable("Booked");
                r.setBookerID(userId);
                manager.merge(r);
                transcation.commit();
                System.out.println("Ride updated successfully");
            }
            else{
                System.out.println("This ride is already full");
            }
        }
        catch (Exception e) {
            transcation.rollback();
            System.out.println("You can't update ride");
        }
    }

    public void yourRides(int id){
        TypedQuery<Ride> query = (TypedQuery<Ride>) manager.createQuery("select r from Ride r where avilable = 'available' and createrid ="+id);
        List<Ride> ridelist = query.getResultList();
        for(Ride r: ridelist){
            System.out.println(r);
        }
    }

    public void changePassword(String oldpassword,String newpassword,int userid){
        try{
            transcation.begin();
            Signup s = manager.find(Signup.class,userid);
            if(oldpassword.equals(s.getPassword())){
                s.setPassword(newpassword);
                manager.merge(s);
                System.out.println("Password chnaged successfully");
            }
            else{
                System.out.println("Wrogn password entered");
            }
            transcation.commit();
        }
        catch (Exception e) {
            transcation.rollback();
            System.out.println("Sorry please try again");
        }
    }

    public void cancelRide(int rideId,int userid){
        try {
            transcation.begin();
            Ride r = manager.find(Ride.class, rideId);
            if (r.getBookerID() == userid) {
                r.setAvilable("available");
                r.setBookerID(0);
                manager.merge(r);
                transcation.commit();
                System.out.println("Ride cancelled successfully");
            }
            else{
                System.out.println("You can't cancel the ride");
            }
        }
        catch (Exception e) {
            transcation.rollback();
            System.out.println("Sorry try again later");
        }
    }

    public void selectRide(String source,String destination){
        TypedQuery<Ride> query = (TypedQuery<Ride>) manager.createQuery("select r from Ride r where avilable = 'available' and source = :source and destination =:destination");
        query.setParameter("source",source);
        query.setParameter("destination",destination);
        List<Ride> ridelist = query.getResultList();
        for(Ride r: ridelist){
            System.out.println(r);
        }
    }

}
