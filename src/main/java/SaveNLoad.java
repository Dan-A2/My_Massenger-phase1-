import com.google.gson.Gson;

import java.io.*;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SaveNLoad {

    static private final Logger logger = (Logger) LogManager.getLogger(SaveNLoad.class);

    static Gson gson = new Gson();

    public static void saveUsers(){

        String path = ".\\Resources\\Users";
        try {
            File file = new File(path);
            for (File file1 : file.listFiles()) {
                file1.delete();
            }
            File activeUsers = new File(path + "\\activeUsers");
            File inactiveUsers = new File(path + "\\inactiveUsers");
            activeUsers.createNewFile();
            inactiveUsers.createNewFile();
            PrintStream printStream1 = new PrintStream(new FileOutputStream(activeUsers, true));
            PrintStream printStream2 = new PrintStream(new FileOutputStream(inactiveUsers, true));
            // deleting repeated objects
            for (int i = 0; i < User.getActiveUsers().size()-1; i++) {
                for (int j = i+1; j < User.getActiveUsers().size(); j++) {
                    if (User.getActiveUsers().get(i) == User.getActiveUsers().get(j)){
                        User.getActiveUsers().remove(i);
                        i = -1;
                        break;
                    }
                }
            }
            for (int i = 0; i < User.getInactiveUsers().size()-1; i++) {
                for (int j = i+1; j < User.getInactiveUsers().size(); j++) {
                    if (User.getInactiveUsers().get(i) == User.getInactiveUsers().get(j)){
                        User.getInactiveUsers().remove(i);
                        i = -1;
                        break;
                    }
                }
            }
            for (User user : User.getActiveUsers()) {
                if(user.isActive()) {
                    String json = gson.toJson(user);
                    printStream1.println(json);
                }
            }
            for (User user : User.getInactiveUsers()) {
                String json = gson.toJson(user);
                printStream2.println(json);
            }
            printStream1.flush();
            printStream2.flush();
            printStream1.close();
            printStream2.close();
        } catch (Exception e) {}

    }

    public static void saveTweet_Comments(){

        String path = ".\\Resources\\Tweet&Comment";
        try {
            File file = new File(path);
            for (File file1 : file.listFiles()){
                file1.delete();
            }
            File tweets = new File(path + "\\tweet_comments");
            tweets.createNewFile();
            PrintStream printStream1 = new PrintStream(new FileOutputStream(tweets, true));
            for (int i = 0; i<Tweet_Comment.getAll().size()-1; i++){
                for (int j = i+1; j < Tweet_Comment.getAll().size(); j++) {
                    if(Tweet_Comment.getAll().get(i) == Tweet_Comment.getAll().get(j)){
                        Tweet_Comment.getAll().remove(i);
                        i = -1;
                        break;
                    }
                }
            }
            for (Tweet_Comment tweet: Tweet_Comment.getAll()) {
                String json = gson.toJson(tweet);
                printStream1.println(json);
            }
            printStream1.flush();
            printStream1.close();

        } catch (Exception e){}

    }

    public static void saveChats(){

        String path = ".\\Resources\\Chat";
        try {
            File file = new File(path);
            for (File file1 : file.listFiles()){
                file1.delete();
            }
            File chats = new File(path + "\\chats");
            chats.createNewFile();
            PrintStream printStream1 = new PrintStream(new FileOutputStream(chats, true));
            for (int i = 0; i<Chat.getAllChats().size()-1; i++){
                for (int j = i+1; j < Chat.getAllChats().size(); j++) {
                    if(Chat.getAllChats().get(i) == Chat.getAllChats().get(j)){
                        Chat.getAllChats().remove(i);
                        i = -1;
                        break;
                    }
                }
            }
            for (Chat chat : Chat.getAllChats()) {
                String json = gson.toJson(chat);
                printStream1.println(json);
            }
            printStream1.flush();
            printStream1.close();
        }
        catch (Exception e){}

    }

    public static void saveMassages(){

        String path = ".\\Resources\\Massages";
        try {
            File file = new File(path);
            for (File file1 : file.listFiles()){
                file1.delete();
            }
            File massages = new File(path + "\\massages");
            massages.createNewFile();
            PrintStream printStream1 = new PrintStream(new FileOutputStream(massages, true));
            for (int i = 0; i<Massage.getAllMassages().size()-1; i++){
                for (int j = i+1; j < Massage.getAllMassages().size(); j++) {
                    if(Massage.getAllMassages().get(i) == Massage.getAllMassages().get(j)){
                        Massage.getAllMassages().remove(i);
                        i = -1;
                        break;
                    }
                }
            }
            for (Massage massage : Massage.getAllMassages()) {
                String json = gson.toJson(massage);
                printStream1.println(json);
            }
            printStream1.flush();
            printStream1.close();
        }
        catch (Exception e){}

    }

    public static void loadUsers(){

        String path1 = ".\\Resources\\Users\\activeUsers";
        String path2 = ".\\Resources\\Users\\inactiveUsers";
        try{
            int maxId = 0;
            File active = new File(path1);
            if(active.exists()){
                Scanner scanner = new Scanner(active);
                while (scanner.hasNext()){
                    String json = scanner.nextLine();
                    User user = gson.fromJson(json, User.class);
                    if(user.getId() > maxId){
                        maxId = user.getId();
                    }
                    User.getActiveUsers().add(user);
                }
                scanner.close();
            }
            File inactive = new File(path2);
            if(inactive.exists()){
                Scanner scanner = new Scanner(inactive);
                while (scanner.hasNext()){
                    String json = scanner.nextLine();
                    User user = gson.fromJson(json, User.class);
                    if(user.getId() > maxId){
                        maxId = user.getId();
                    }
                    User.getInactiveUsers().add(user);
                }
                scanner.close();
            }
            int id = maxId;
            User.setLastId(id);
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    public static void loadTweet_Comments(){

        String path = ".\\Resources\\Tweet&Comment\\tweet_comments";
        File file = new File(path);
        int maxid = 0;
        try {
            if(file.exists()){
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()){
                    String json = scanner.nextLine();
                    Tweet_Comment tweet = gson.fromJson(json, Tweet_Comment.class);
                    Tweet_Comment.getAll().add(tweet);
                    if(tweet.getID() > maxid){
                        maxid = tweet.getID();
                    }
                }
                scanner.close();
            }
            Tweet_Comment.setLastId(maxid);
        }
        catch (Exception e){}

    }

    public static void loadChats(){

        String path = ".\\Resources\\Chat\\chats";
        int maxid = 0;
        try {
            File file = new File(path);
            if(file.exists()){
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()){
                    String json = scanner.nextLine();
                    Chat chat = gson.fromJson(json, Chat.class);
                    Chat.getAllChats().add(chat);
                    if(chat.getID() > maxid){
                        maxid = chat.getID();
                    }
                }
                scanner.close();
            }
            Chat.setLastId(maxid);
        }
        catch (Exception e){}

    }

    public static void loadMassages(){

        String path = ".\\Resources\\Massages\\massages";
        int maxid = 0;
        try {
            File file = new File(path);
            if(file.exists()){
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()){
                    String json = scanner.nextLine();
                    Massage massage = gson.fromJson(json, Massage.class);
                    Massage.getAllMassages().add(massage);
                    if(massage.getID() > maxid){
                        maxid = massage.getID();
                    }
                }
                Massage.setLastId(maxid);
                scanner.close();
            }
        }
        catch (Exception e){}

    }

    public static void save(){

        logger.info("saving...");
        saveUsers();
        saveChats();
        saveTweet_Comments();
        saveMassages();

    }

    public static void load(){

        logger.info("loading...");
        loadTweet_Comments();
        loadChats();
        loadUsers();
        loadMassages();

    }

}