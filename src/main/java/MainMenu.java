public class MainMenu { // this the main leading page which will give the access for everywhere...

    public static void showOptions(User user) {

        System.out.println(ConsoleColors.CYAN_BOLD + "Main Menu: ");
        System.out.println(ConsoleColors.YELLOW_BOLD + "1. Your Account");
        System.out.println("2. TimeLine");
        System.out.println("3. Explorer");
        System.out.println("4. Messaging");
        System.out.println("5. Settings");
        String response = MyScanner.GetScanner1().next();
        response = CheckValidity.numeric(5, response);
        System.out.println(ConsoleColors.RESET);
        switch (response){
            case ("1"):
                Your_Account.selectOption(user);
                showOptions(user);
                break;
            case ("2"):
                TimeLine.startTimeline(user);
                showOptions(user);
                break;
            case ("3"):
                Explorer.what_to_do(user);
                showOptions(user);
                break;
            case ("4"):
                Massaging.showOptions(user);
                showOptions(user);
                break;
            case ("5"):
                Setting.showSettings(user);
                showOptions(user);
                break;
        }

    }

}