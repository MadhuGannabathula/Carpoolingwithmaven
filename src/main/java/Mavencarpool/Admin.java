package Mavencarpool;

import org.hibernate.query.NativeQuery;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

public class Admin {
    static EntityManagerFactory factory = Persistence.createEntityManagerFactory("jp");
    static EntityManager manager = factory.createEntityManager();
    static EntityTransaction transcation = manager.getTransaction();
    static Ride ride;

    public void displayRides() {
        TypedQuery<Ride> query = (TypedQuery<Ride>) manager.createQuery("Select r from Ride r",Ride.class);
        List<Ride> ridelist = query.getResultList();
        for(Ride r: ridelist){
            System.out.println(r);
        }
    }

    public void displayUsers() {
        TypedQuery<Signup> query = (TypedQuery<Signup>) manager.createQuery("Select u from Signup u",Signup.class);
        List<Signup> userslist = query.getResultList();
        for(Signup r: userslist){
            System.out.println(r);
        }
    }

    public void displayDestination(String source){
        TypedQuery<Ride> query = (TypedQuery<Ride>) manager.createQuery("Select r from Ride r where source =:source",Ride.class);
        query.setParameter("source",source);
        List<Ride> ridelist = query.getResultList();
        for(Ride r: ridelist){
            System.out.println(r.getDestination());
        }
    }

    public void displaySource(String destination){
        TypedQuery<Ride> query = (TypedQuery<Ride>) manager.createQuery("Select r from Ride r where destination =:destination");
        query.setParameter("destination",destination);
        try{
            List<Ride> ridelist = query.getResultList();
            for(Ride r: ridelist){
                System.out.println(r.getSource());
            }
        }
        catch (Exception e){
            System.err.println(e);
        }

    }

    public void displayCities(){
        NativeQuery<String> query = (NativeQuery<String>) manager.createNativeQuery("select distinct cities from (select distinct source as cities from rides union select distinct destination as cities from rides) as towns;");
        List<String> citylist = query.getResultList();
        for(String city:citylist){
            System.out.println(city);
        }
    }

    public void totalFare(){
        BigInteger total = BigInteger.valueOf(0);
        NativeQuery<BigInteger> query = (NativeQuery<BigInteger>) manager.createNativeQuery("select sum(fare) from rides;");
        total =  query.getSingleResult();
        System.out.println(total);
    }
}
