package p2.logging;

import java.util.ArrayList;
import java.util.Scanner;

import p2.event.Event;

public class Tester extends Event {

    public ArrayList<Event> events = new ArrayList<Event>();
    public int time;
    public String object;

    Tester(String object, int time, ArrayList<Event> events) {
        super(object, time);
        this.events = events;
    }

    Tester(String object, int time) {
        super(object, time);
    }

    @Override
    public String getObject() {
        // TODO Auto-generated method stub
        return super.getObject();
    }

    @Override
    public int getTime() {
        // TODO Auto-generated method stub
        return super.getTime();
    }

    public Boolean contains(ArrayList<Event> events) {
        for (int index = 0; index < this.events.size(); index++) {
            for (int i = 0; i < events.size(); i++) {
                if ((this.events.get(index).equals(events.get(i))) && (index == i)) {
                    System.out.println("Logable's list contains '" + events.get(index) + "' at index " + i + "\n");
                    return true;
                }
            }
        }
        return false;
    }

    public Boolean validate() {
        Event checker = this.events.get(0);
        for (int index = 0; index < this.events.size(); index++) {
            for (int x = 0; x < this.events.size(); x++) {
                if (index == x) {
                    continue;
                } else {
                    System.out.println("Checking for duplicates . . .\n");
                    if (checker.equals(this.events.get(x))) {
                        System.out.println(
                                "Duplicate found : Index #" + index + " and Index #" + x + " are the same. \n");
                        System.out.println("Duplicate Object = " + this.events.get(x).toString() + "\n");
                        return false;
                    }

                    System.out.println("Checking '" + checker + "' and '" + this.events.get(x).toString() + "'\n");
                    System.out.println("Events are not the same\n");
                }

            }
            if ((this.events.size() - 1) != index) {
                checker = this.events.get(index + 1);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Tester aTester = new Tester("I am a", 1);
        // Tester bTester = new Tester("I am b", 2);
        // Tester cTester = new Tester("I am c", 3);
        // Tester dTester = new Tester("I am d", 4);
        // Tester eTester = new Tester("I am e", 5);
        // Tester fTester = new Tester("I am f", 6);
        // Tester gTester = new Tester("I am g", 7);
        // Tester hTester = new Tester("I am h", 8);

        // ArrayList<Event> ev = new ArrayList<Event>();
        // ev.add(aTester);
        // ev.add(bTester);
        // ev.add(cTester);
        // ev.add(aTester);
        // ev.add(eTester);
        // ev.add(fTester);
        // ev.add(gTester);
        // ev.add(hTester);

        // ArrayList<Event> es = new ArrayList<Event>();
        // es.add(bTester);
        // es.add(bTester);
        // es.add(aTester);
        // es.add(aTester);
        // es.add(aTester);
        // es.add(aTester);
        // es.add(aTester);
        // es.add(aTester);

        // Tester finalTester = new Tester("Final Tester", 100, ev);

        // finalTester.contains(es);
        // System.out.println("Finished\n");

        // finalTester.validate();
        // System.out.println("Finished\n");

        // Scanner scan = new Scanner(System.in);
        // int num1, num2;
        // System.out.println("PLease enter a numerator : ");
        // num1 = scan.nextInt();
        // System.out.println("Enter a denominator : ");
        // num2 = scan.nextInt();
        // while (num1 > 0) {
        // try {
        // if (num1 > num2) {
        // int result = num1 / num2;
        // System.out.println("The result is " + result + "\n");
        // }
        // } catch (Exception e) {
        // System.out.println("Exception caught : \n" + e + "\n");
        // }

        // System.out.println("PLease enter a numerator : ");
        // num1 = scan.nextInt();
        // System.out.println("Enter a denominator : ");
        // num2 = scan.nextInt();

        // }
        // scan.close();
        int x = 1;
        int y = 2;
        String test = x > y ? "Yes, x is greater than y" : "No, x is less than or equal to y";
        System.out.println(test);
    }
}
