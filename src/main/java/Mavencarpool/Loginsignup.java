package Mavencarpool;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Loginsignup {
    static EntityManagerFactory factory = Persistence.createEntityManagerFactory("jp");
    static EntityManager manager = factory.createEntityManager();
    static EntityTransaction transcation = manager.getTransaction();
    static Scanner sc = new Scanner(System.in);

    public int log() {
        System.out.println("Enter 1 for signup or 2 for login...");
        System.out.print("Please provide your input here:");
        int login = sc.nextInt();
        if(login == 1){
            System.out.print("Please enter a username:");
            String username = sc.next();
            if(checkUser(username)>0) {
                System.out.println("Username taken!!");
            }
            else{
                System.out.print("Please enter a emailId:");
                String email = sc.next();
                if(checkEmail(email)>0){
                    System.out.println("Email was already used please login!!");
                }
                else{
                    System.out.print("Please enter a password:");
                    String password = sc.next();
                    password = encryptPassword(password);
                    createUser(username,email,password);
                    System.out.println("User creation successful please login!!!");
                    return log();
                }
            }
        }
        else if(login == 2){
            System.out.print("Please enter a username:");
            String username = sc.next();
            System.out.print("Please enter password:");
            String password = sc.next();
            password = encryptPassword(password);
            System.out.println(username+"  "+password);
            int id = checkPassword(username,password);
            return id;
        }
        else{
            System.out.println("Please provide valid input");
        }
        return 0;
    }



    public int checkUser(String username){
        TypedQuery<Signup> query = (TypedQuery<Signup>) manager.createQuery("select u from Signup u where username =:username");
        query.setParameter("username",username);
        try{
            Signup u = query.getSingleResult();
            return u.getID();
        }
        catch (Exception e){
            return 0;
        }
    }
    public int checkEmail(String email){
        TypedQuery<Signup> query = (TypedQuery<Signup>) manager.createQuery("select u from Signup u where Email =:email");
        query.setParameter("email",email);
        try{
            Signup u = query.getSingleResult();
            return u.getID();
        }
        catch (Exception e){
            return 0;
        }
    }

    public int checkPassword(String username,String password){
        TypedQuery<Signup> query = (TypedQuery<Signup>) manager.createQuery("select u from Signup u where Username =:username and password =:password");
        query.setParameter("username",username);
        query.setParameter("password",password);
        try{
            Signup u = query.getSingleResult();
            System.out.println("Successfully logged in");
            return u.getID();
        }
        catch (Exception e){
            System.out.println("Please check entered credentials");
        }
        return 0;
    }

    public void createUser(String name,String email,String password){
        Signup signup = new Signup(name,email,password);
        transcation.begin();
        manager.persist(signup);
        transcation.commit();
    }

    public String encrypt(String password){
        char[] pass = password.toCharArray();

        for(int i=pass.length-1;i>=0;i--){
            pass[i] = (char) ((int)pass[i] + i/2);
        }
        return new String(pass);
    }
    public String encryptPassword(String password){
        password = encrypt(password);
        password = password+password;
        password = encrypt(password);
        System.out.println(password);
        return password;
    }
}
