package Mavencarpool;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static EntityManagerFactory factory = Persistence.createEntityManagerFactory("jp");
    static EntityManager manager = factory.createEntityManager();
    static EntityTransaction transcation = manager.getTransaction();
    static Scanner sc = new Scanner(System.in);
    static Loginsignup log = new Loginsignup();

    public static void ui(int id) {
        int op = -1;
        int rideID;
        String source;
        String destination;
        int fare;
        int result;
        User user = new User();
        Admin admin = new Admin();
        while(op !=0){
            System.out.printf("%-35s", "Enter 1 to Create Ride");
            System.out.println("Enter 6 to see available rides");
            System.out.printf("%-35s", "Enter 2 to update Ride");
            System.out.println("Enter 7 to find a Ride for you");
            System.out.printf("%-35s", "Enter 3 to see your Rides");
            System.out.println("Enter 8 to see cancel your booking");
            System.out.printf("%-35s", "Enter 4 to delete Ride");
            System.out.println("Enter 9 to Change password");
            System.out.printf("%-35s", "Enter 5 to book Ride");
            System.out.println("Enter 0 to Logout");
            if(id == 1){
                System.out.printf("%-35s","Enter 11 to display all Rides");
                System.out.println("Enter 14 to see display all cities");
                System.out.printf("%-35s", "Enter 12 to total fare");
                System.out.println("Enter 15 to diaplay Rides from...");
                System.out.printf("%-35s", "EEnter 13 to see all users");
                System.out.println("Enter 16 to display Rides to... ");
            }
            System.out.print("Please provide your input here:");
            op = sc.nextInt();

            switch (op) {
                case 0:
                    System.out.println("Logged out successfully!!");
                    break;
                case 1:
                    System.out.print("Enter source:");
                    source = sc.next();
                    System.out.print("Enter destination:");
                    destination = sc.next();
                    System.out.print("Enter fare:");
                    fare = sc.nextInt();
                    user.createRide(source, destination, fare, id);
                    break;
                case 2:
                    System.out.print("Enter ride ID:");
                    rideID = sc.nextInt();
                    System.out.print("Enter source:");
                    source = sc.next();
                    System.out.print("Enter destination:");
                    destination = sc.next();
                    System.out.print("Enter fare:");
                    fare = sc.nextInt();
                    user.updateRide(rideID, source, destination, fare, id);
                    break;
                case 3:
                    user.yourRides(id);
                    break;
                case 4:
                    System.out.print("Enter ride ID:");
                    rideID = sc.nextInt();
                    user.deleteRide(rideID, id);

                    break;
                case 5:
                    System.out.print("Enter ride ID:");
                    rideID = sc.nextInt();
                    user.bookRide(rideID, id);
                    break;
                case 6:
                    user.availableRides(id);
                    break;
                case 7:
                    System.out.print("Enter source:");
                    source = sc.next();
                    System.out.print("Enter destination:");
                    destination = sc.next();
                    user.selectRide(source, destination);
                    break;
                case 8:
                    System.out.print("Enter ride ID:");
                    rideID = sc.nextInt();
                    user.cancelRide(rideID, id);
                    break;
                case 9:
                    System.out.print("Please enter password:");
                    String password = sc.next();
                    System.out.print("Please enter new password:");
                    String newpassword = sc.next();
                    user.changePassword(password, newpassword, id);
            }

            if(id == 1) {
                switch (op) {
                    case 11:
                        admin.displayRides();
                        break;
                    case 12:
                        admin.totalFare();
                        break;
                    case 13:
                        admin.displayUsers();
                        break;
                    case 14:
                        admin.displayCities();
                        break;
                    case 15:
                        System.out.print("Enter source:");
                        source = sc.next();
                        admin.displayDestination(source);
                        break;
                    case 16:
                        System.out.print("Enter destination:");
                        destination = sc.next();
                        admin.displaySource(destination);
                        break;
                }
                }
            }

    }

    public static void main(String[] args) throws SQLException {
        int id = log.log();
       // System.out.println(id);
        //int id = 1;
        if(id>0){
            ui(id);
        }
        /*Loginsignup log = new Loginsignup();
        log.encryptPassword("admin123");
        User user = new User();
        user.changePassword("99::;;iillqquuvv","adokr589ehsov9<=",1);*/
    }
}
