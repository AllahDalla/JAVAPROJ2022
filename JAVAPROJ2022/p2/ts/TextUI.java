package p2.ts;

import java.io.FileNotFoundException;
import java.util.Scanner;

import p2.Simulator;

public class TextUI {

    TextUI() {
        // just initialize the simulator from ts/Simulator.java here
        Scanner scan = new Scanner(System.in);
        String file = "p2\\SampleInput.txt";
        String cmd;
        System.out.println("Train System has started . . .\n");
        System.out.println("Would you like to use 'Sample Input' file : Type 'Y' or 'N'\n");
        cmd = scan.nextLine();
        if (cmd.equalsIgnoreCase("Y") || cmd.equalsIgnoreCase("Yes")) {
            try {
                System.out.println("SYSTEM WILL USE SAMPLE INPUT FILE . . .\n\n\n\n\n");
                new Simulator(file);

            } catch (FileNotFoundException fnf) {
                System.out.println("File was not found.\n");
                System.out.println("Please restart program.\n");
                System.exit(0);

            } catch (Exception e) {
                System.out.println("An error occured.\n" + e.getLocalizedMessage() + "\n");
                System.out.println("Please restart program.\n");
                System.exit(0);
            }

        } else {
            System.out.println("\nPlease include relative file path.\n");
            System.out.println("System requires a file name : ");
            cmd = scan.nextLine();
            try {

                new Simulator(cmd);

            } catch (FileNotFoundException fnf) {
                System.out.println("File was not found.\n" + fnf.getLocalizedMessage() + "\n");
                System.out.println("Please restart program.\n");
                System.exit(0);

            } catch (Exception e) {
                System.out.println("An error occured.\n" + e.getLocalizedMessage() + "\n");
                System.out.println("Please restart program.\n");
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        new TextUI();
    }
}
