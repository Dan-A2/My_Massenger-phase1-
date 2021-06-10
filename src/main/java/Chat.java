import com.google.gson.annotations.Expose;
import java.util.LinkedList;

public class Chat {

    private static int lastId = 0;
    private int ID;
    private final Integer user1id;
    private final Integer user2id;
    private final LinkedList<Massage> massages;
    @Expose(serialize = false, deserialize = false)
    private static final LinkedList<Chat> allChats = new LinkedList<>();
    int user1UnseenMassages;
    int user2UnseenMassages;

    public Chat(Integer user1id, Integer user2id) {
        this.user1id = user1id;
        this.user2id = user2id;
        this.massages = new LinkedList<>();
        user1UnseenMassages = 0;
        user2UnseenMassages = 0;
        lastId++;
        this.ID = lastId;
        allChats.add(this);
        SaveNLoad.saveChats();
    }

    public User getUser1() {
        for (User user : User.getActiveUsers()) {
            if(user.getId() == user1id && user.isActive()){
                return user;
            }
        }
        return null;
    }

    public User getUser2() {
        for (User user : User.getActiveUsers()) {
            if(user.getId() == user2id && user.isActive()){
                return user;
            }
        }
        return null;
    }

    public LinkedList<Massage> getMassages() {
        return massages;
    }

    public static LinkedList<Chat> getAllChats() {
        return allChats;
    }

    public static Chat findChat(User u1, User u2){

        for (Chat chat: allChats) {
            if((chat.getUser1().equals(u1) && chat.getUser2().equals(u2))||
                    (chat.getUser2().equals(u1) && chat.getUser1().equals(u2))){ // chat already exists
                return chat;
            }
        }
        return new Chat(u1.getId(), u2.getId());

    }

    public static User findTheOtherUser(Chat chat, User user){
        if(chat.getUser1().getUsername().equals(user.getUsername())){
            return chat.getUser2();
        }
        return chat.getUser1();
    }

    public int getUser1UnseenMassages() {
        return user1UnseenMassages;
    }

    public void setUser1UnseenMassages(int user1UnseenMassages) {
        this.user1UnseenMassages = user1UnseenMassages;
    }

    public int getUser2UnseenMassages() {
        return user2UnseenMassages;
    }

    public void setUser2UnseenMassages(int user2UnseenMassages) {
        this.user2UnseenMassages = user2UnseenMassages;
    }

    public static int getLastId() {
        return lastId;
    }

    public static void setLastId(int lastId) {
        Chat.lastId = lastId;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

}