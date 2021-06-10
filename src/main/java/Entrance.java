import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.LocalDate;
import java.util.LinkedList;

public class Entrance {

    static private final Logger logger = (Logger) LogManager.getLogger(Entrance.class);

    public static void login(){

        logger.info("Program started");
        System.out.println(ConsoleColors.BLUE_BOLD + "Welcome to the God_Damn massager!");
        System.out.println(ConsoleColors.YELLOW_BOLD + "Do you already have an account? (yes/no)");
        String response = MyScanner.GetScanner1().next();
        switch (response) {
            case "yes":
                findUser();
                break;
            case "no":
                createUser();
                break;
            // checking validity
            default:
                System.out.println(ConsoleColors.RED_UNDERLINED + "Invalid input!");
                login();
        }

    }

    public static void findUser(){

        System.out.println(ConsoleColors.YELLOW_BOLD + "Username: ");
        String username = MyScanner.GetScanner1().nextLine();
        username = CheckValidity.username(username);
        LinkedList<User> allUsers = User.getActiveUsers();
        allUsers.addAll(User.getInactiveUsers());
        for (User user : allUsers) {
            if (user.getUsername().equals(username)) {
                System.out.println(ConsoleColors.YELLOW_BOLD + "Password: ");
                String pass = MyScanner.GetScanner1().next();
                if (user.getPassword().equals(pass)) {
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "User qualified");
                    System.out.println(ConsoleColors.RESET);
                    MainMenu.showOptions(user);
                    return;
                } else {
                    logger.info("user rid ba in passwordesh XD");
                    System.out.println(ConsoleColors.RED_BOLD + "Wrong password");
                    findUser();
                }
            }
        }
        System.out.println(ConsoleColors.RED_BOLD + "Username does not exist");
        System.out.println(ConsoleColors.WHITE_BOLD + "1. try again");
        System.out.println(ConsoleColors.WHITE_BOLD + "2. new user");
        String ans = MyScanner.GetScanner1().next();
        ans = CheckValidity.numeric(2, ans);
        if(ans.equals("1")){
            findUser();
        }
        else{
            createUser();
        }

    }

    public static void createUser(){

        System.out.println(ConsoleColors.YELLOW_BOLD + "Please fill the below fields (those with * are necessary)");
        System.out.println("Firstname*: ");
        String firstname = MyScanner.GetScanner1().next();
        firstname = CheckValidity.name(firstname);
        System.out.println("Lastname*: ");
        String lastname = MyScanner.GetScanner1().next();
        lastname = CheckValidity.name(lastname);
        System.out.println(ConsoleColors.YELLOW_BOLD + "Username*: (do not put any spaces!)");
        String username = MyScanner.GetScanner1().nextLine();
        username = CheckValidity.newUsername(username);
        System.out.println(ConsoleColors.YELLOW_BOLD + "Password*: ");
        String password = MyScanner.GetScanner1().next();
        System.out.println("Email*: ");
        String email = MyScanner.GetScanner1().next();
        email = CheckValidity.email(email);
        User user = new User(firstname, lastname, username, password, email);
        logger.info("user has made his account");
        /////////////////////////////////////////////////////////////////////
        System.out.println("Do you wanna add bio? (y/n)");
        String ans = MyScanner.GetScanner1().next();
        ans = CheckValidity.yn(ans);
        if(ans.equals("y")){
            System.out.println("bio: ");
            String bio = MyScanner.GetScanner1().nextLine();
            user.setBio(bio);
        }
        else{
            System.out.println(ConsoleColors.PURPLE_BOLD + "never mind! if you changed your opinion you can add bio in settings...");
        }
        /////////////////////////////////////////////////////////////////////
        System.out.println(ConsoleColors.YELLOW_BOLD + "Do you wanna add Phone number? (y/n)");
        ans = MyScanner.GetScanner1().next();
        ans = CheckValidity.yn(ans);
        if(ans.equals("y")){
            System.out.println("Phone number: ");
            String phoneNumber = MyScanner.GetScanner1().next();
            phoneNumber = CheckValidity.phoneNumber(phoneNumber);
            user.setPhoneNumber(phoneNumber);
        }
        else{
            System.out.println(ConsoleColors.PURPLE_BOLD + "never mind! if you changed your opinion you can add phone number in settings...");
        }
        /////////////////////////////////////////////////////////////////////
        System.out.println(ConsoleColors.YELLOW_BOLD + "Do you wanna add your birthday? (y/n)");
        ans = MyScanner.GetScanner1().next();
        ans = CheckValidity.yn(ans);
        if(ans.equals("y")){
            System.out.println(ConsoleColors.YELLOW_BOLD + "birth day: ");
            String day2 = MyScanner.GetScanner1().next();
            day2 = CheckValidity.number(day2);
            int day = Integer.parseInt(day2);
            while(day<=0 || day>30){
                System.out.println(ConsoleColors.RED_UNDERLINED + "invalid input, try again: ");
                day = MyScanner.GetScanner1().nextInt();
            }
            System.out.println(ConsoleColors.YELLOW_BOLD + "birth month(in number): ");
            String month2 = MyScanner.GetScanner1().next();
            month2 = CheckValidity.number(month2);
            int month = Integer.parseInt(month2);
            while(month<=0 || month>12){
                System.out.println(ConsoleColors.RED_UNDERLINED + "invalid input, try again: ");
                month = MyScanner.GetScanner1().nextInt();
            }
            System.out.println(ConsoleColors.YELLOW_BOLD + "birth year: ");
            String year2 = MyScanner.GetScanner1().next();
            int currentYear = LocalDate.now().getYear();
            year2 = CheckValidity.number(year2);
            int year = Integer.parseInt(year2);
            while(year<=0 || year>currentYear || !CheckValidity.isNumberValid(Integer.toString(year))){
                System.out.println(ConsoleColors.RED_UNDERLINED + "invalid input, try again: ");
                year = MyScanner.GetScanner1().nextInt();
            }
            user.setBirthday(LocalDate.of(year, month, day));
        }
        else{
            System.out.println(ConsoleColors.PURPLE_BOLD + "never mind! if you changed your opinion you can add birthday in settings...");
        }
        /////////////////////////////////////////////////////////////////////
        System.out.println(ConsoleColors.PURPLE_BOLD_BRIGHT + "User has created successfully!");
        SaveNLoad.saveUsers();
        System.out.println(ConsoleColors.RESET);
        MainMenu.showOptions(user);

    }

}