import java.util.Scanner;

class MyScanner {

        private static MyScanner scanner1;
        private Scanner scanner;

        private MyScanner() {
            scanner = new Scanner(System.in);
        }

        public static MyScanner GetScanner1(){
            if (scanner1 == null){
                scanner1 = new MyScanner();
            }
            return scanner1;
        }

        public String next(){
            String res = scanner.next();
            scanner.nextLine();
            return res;
        }

        public String nextLine(){
            return scanner.nextLine();
        }

        public int nextInt(){
            return scanner.nextInt();
        }

}