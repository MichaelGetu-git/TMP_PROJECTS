/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CorrectedCode;

/**
 *
 * @author HP 15
 */
import java.util.ArrayList;
public class Customer {
    private String userID;
    private String name;
    private String email;
    private String password;
    private String phone;
    private int age;
    //seat
    //flight list
    private static ArrayList <Integer> bookedTicketNumList  = new ArrayList <>();
    private static ArrayList <Customer> customerList = new ArrayList<>();
    //constructers
    public Customer(){
        this.userID = "0000";
        this.name = "user1";
        this.email = "user1@gmail.com";
        this.password = "password";
        this.phone = "+251000000000";
        this.age = 19;
    }

    public Customer(String userID, String name, String email, String password, String phone, int age){
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.age = age;
    }
    public Customer(String name){
        this.userID = "0000";
        this.name = name;
        this.email = "user1@gmail.com";
        this.password = "password";
        this.phone = "+251000000000";
        this.age = 19; 
    }
    //methods

    //setters and getter
    public void setUserID(String input){
        userID = input;
    }

    public String getUserID(){
        return userID;
    }

    public void setName(String input){
        name = input;
    }
    
    public String getName(){
        return name;
    }

    public void setEmail(String input){
        email = input;
    }
    
    public String getEmail(){
        return email;
    }

    public void setPassword(String input){
        password = input;
    }
    
    public String getPassword(){
        return password;
    }

    public void setPhone(String input){
        phone = input;
    }
    
    public String getPhone(){
        return phone;
    }

    public void setAge(int input){
        age = input;
    }
    
    public int getAge(){
        return age;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "userID='" + userID + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                '}';
    }
}