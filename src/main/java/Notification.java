import java.time.LocalDateTime;

public class Notification {

    private String notif;
    private String dateTime;

    public Notification(String notif) {
        this.notif = notif;
        this.dateTime = LocalDateTime.now().toString();
    }

    public String getNotif() {
        return notif;
    }

    public String getDateTime() {
        return dateTime;
    }

}