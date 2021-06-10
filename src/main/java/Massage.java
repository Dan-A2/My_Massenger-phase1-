import com.google.gson.annotations.Expose;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class Massage {

    private static int lastId = 0;
    private int ID;
    private String massage;
    private String dateTime;
    private Integer senderID;
    private Integer receiverID;
    @Expose(serialize = false, deserialize = false)
    private static LinkedList<Massage> allMassages = new LinkedList<>();

    public Massage(String massage, Integer senderId, Integer receiverId){
        this.massage = massage;
        this.dateTime = LocalDateTime.now().toString();
        this.senderID = senderId;
        this.receiverID = receiverId;
        lastId++;
        this.ID = lastId;
        allMassages.add(this);
        SaveNLoad.saveMassages();
    }

    public String getMassage() {
        return massage;
    }

    public String getDateTime() {
        return dateTime;
    }

    public User getSender() {
        for (User user : User.getActiveUsers()) {
            if(user.getId() == senderID){
                return user;
            }
        }
        return null;
    }

    public User getReceiver() {
        for (User user : User.getActiveUsers()) {
            if(user.getId() == receiverID){
                return user;
            }
        }
        return null;
    }

    public static int getLastId() {
        return lastId;
    }

    public static void setLastId(int lastId) {
        Massage.lastId = lastId;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public static LinkedList<Massage> getAllMassages() {
        return allMassages;
    }

}