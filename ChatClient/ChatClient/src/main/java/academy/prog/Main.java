package academy.prog;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter your login: ");
            String login = scanner.nextLine();

            Thread th = new Thread(new GetThread());
            th.setDaemon(true);
            th.start();

            System.out.println("Enter your message: ");
            while (true) {
                String text = scanner.nextLine();
                    int res;
                    Message m;
                    if (text.isEmpty()) break;
                    if (text.contains("@")) {
                        String user = text.substring(1, text.indexOf(" "));
                        text = text.substring(text.indexOf(" ") + 1);
                        m = new Message(login, text, user);
                    } else {
                        m = new Message(login, text);
                    }

                    res = m.send(Utils.getURL() + "/add");// this is Post
                    if (res != 200) { // 200 OK
                        System.out.println("HTTP error ocurred: " + res);
                        return;
                    }

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
