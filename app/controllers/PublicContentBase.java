package controllers;


import helpers.HashUtils;
import models.User;
import play.mvc.Controller;

public class PublicContentBase extends Controller {

    private static int MIN_PASSWORD_LENGTH = 10;

    public static void register(){
        render();
    }

    public static void processRegister(String username, String password, String passwordCheck, String type){
        if (password.equals(passwordCheck) && validatePassword(password)){
            User u = new User(username, HashUtils.getMd5(password), type, -1);
            u.save();
            registerComplete();
        }else {
            flash.error("Password is not valid.");
        }
    }

    public static void registerComplete(){
        render();
    }

    private static boolean validatePassword(String password){
        return password.length() >= MIN_PASSWORD_LENGTH;
    }
}
