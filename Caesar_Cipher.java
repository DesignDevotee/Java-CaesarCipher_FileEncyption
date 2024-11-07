import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Caesar_Cipher {

    @SuppressWarnings("resource")
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter file path:");
        String path = s.nextLine();
        System.out.println("Enter key:");
        int key = s.nextInt();
        s.nextLine();
        System.out.println("Choose option:");
        System.out.println("1. Encryption");
        System.out.println("2. Decryption");
        int option = s.nextInt();
        s.nextLine();

        switch (option) {
            case 1 -> {
                encrypt(path, key);
                System.out.println("Encryption completed.");
            }
            case 2 -> {
                decrypt(path, key);
                System.out.println("Decryption completed.");
            }
            default -> System.out.println("Invalid option. Please enter 1 or 2.");
        }
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public static void encrypt(String file, int key) throws IOException {
        File f = new File(file);
        Scanner sc = new Scanner(f);
        StringBuilder message = new StringBuilder();

        while (sc.hasNextLine()) {
            message.append(sc.nextLine()).append("\n");
        }
        sc.close();

        StringBuilder encryptedMessage = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (Character.isUpperCase(c)) {
                c = (char) ((c - 'A' + key) % 26 + 'A');
            } else if (Character.isLowerCase(c)) {
                c = (char) ((c - 'a' + key) % 26 + 'a');
            }
            encryptedMessage.append(c);
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(f))) {
            pw.print(encryptedMessage.toString());
        }
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public static void decrypt(String file, int key) throws IOException {
        File f = new File(file);
        Scanner sc = new Scanner(f);
        StringBuilder message = new StringBuilder();

        while (sc.hasNextLine()) {
            message.append(sc.nextLine()).append("\n");
        }
        sc.close();

        StringBuilder decryptedMessage = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (Character.isUpperCase(c)) {
                c = (char) ((c - 'A' - key + 26) % 26 + 'A');
            } else if (Character.isLowerCase(c)) {
                c = (char) ((c - 'a' - key + 26) % 26 + 'a');
            }
            decryptedMessage.append(c);
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(f))) {
            pw.print(decryptedMessage.toString());
        }
    }
}
