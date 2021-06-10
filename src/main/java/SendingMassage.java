import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SendingMassage {

    static private final Logger logger = (Logger) LogManager.getLogger(SendingMassage.class);

    static void sendMassage1(User from, User to, String txt){

        if(!to.getFollowingsId().contains(from.getId()) && !to.getFollowersId().contains(from.getId())){
            System.out.println(ConsoleColors.RED_UNDERLINED + "One of you must follow the other inorder to text each other!");
        }
        else if(from.getBlackListId().contains(to.getId()) || to.getBlackListId().contains(from.getId())){
            System.out.println(ConsoleColors.RED_UNDERLINED + "Due to security reasons you can't text this user!");
        }
        else{
            Massage massage = new Massage(txt, from.getId(), to.getId());
            Chat chat = Chat.findChat(to,from);
            chat.getMassages().add(massage);
            if(chat.getUser1().equals(from)){
                chat.setUser2UnseenMassages(chat.getUser2UnseenMassages()+1);
            }
            else{
                chat.setUser1UnseenMassages(chat.getUser1UnseenMassages()+1);
            }
            if(!to.getUnseenChatsId().contains(new Integer(chat.getID()))){
                to.getUnseenChatsId().add(chat.getID());
            }
            if(!to.getMyChatsId().contains(new Integer(chat.getID()))){
                to.getMyChatsId().add(chat.getID());
            }
            if(!from.getMyChatsId().contains(new Integer(chat.getID()))){
                from.getMyChatsId().add(chat.getID());
            }
            if(!to.getMutedId().contains(from.getId())){
                Notification notification = new Notification(ConsoleColors.RESET + "User " + from.getUsername() + " has texted you!");
                to.getMyNotifs().add(notification);
            }
            logger.info(from.getUsername() + " has sent a massage to " + to.getUsername());
            System.out.println(ConsoleColors.GREEN_BOLD + "Done!");
        }

    }

}