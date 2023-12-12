import java.util.Random;
import java.util.Scanner;

public class lab10 {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Q1();
        Q2();
        Q3();
        Q4();
        scan.close();
    }

    public static void Q1() {
        while (true) {
            System.out.println("Pick a shape: square, rectangle, circle (or 'q' to quit)");
            String input = scan.nextLine();
            if (input.equals("q")) {
                return;
            }

            if (input.equals("square")) {
                double sideA;
                System.out.println("Enter the length of side a: ");
                sideA = Double.parseDouble(scan.nextLine());
                System.out.println("The circumference of the square is: " + sideA * 4);
                System.out.println("The area of the square is: " + sideA * sideA);

            } else if (input.equals("rectangle")) {
                double sideA, sideB;
                System.out.println("Enter the length of side a: ");
                sideA = Double.parseDouble(scan.nextLine());
                System.out.println("Enter the length of side b: ");
                sideB = Double.parseDouble(scan.nextLine());
                System.out.println("The circumference of the rectangle is: " + (2 * sideA + 2 * sideB));
                System.out.println("The area of the rectangle is: " + (sideA * sideB));
            } else if (input.equals("circle")) {
                double radius;
                System.out.println("Enter the radius: ");
                radius = Double.parseDouble(scan.nextLine());
                System.out.println("The circumference of the circle is: " + (Math.PI * radius * 2));
                System.out.println("The area of the circle is: " + (Math.PI * radius * radius));
            }
        }
    }

    public static void Q2() {
        System.out.println("Q2: Enter the current day (1-31): ");
        int day = Integer.parseInt(scan.nextLine());
        System.out.println("Enter the current month: (1-12)");
        int month = Integer.parseInt(scan.nextLine());
        String suffix = new String("th");

        if (day == 1 || day == 21 || day == 31) {
            suffix = "st";
        } else if (day == 2 || day == 22) {
            suffix = "nd";
        } else if (day == 3 || day == 23) {
            suffix = "rd";
        }
        if (day > 0 && day < 32) {
            System.out.print("You selected " + day + suffix + " of ");
        } else {
            System.out.println("Invalid day");
        }
        switch (month) {
            case 1:
                System.out.println("January");
                break;
            case 2:
                System.out.println("February");
                break;
            case 3:
                System.out.println("March");
                break;
            case 4:
                System.out.println("April");
                break;
            case 5:
                System.out.println("May");
                break;
            case 6:
                System.out.println("June");
                break;
            case 7:
                System.out.println("July");
                break;
            case 8:
                System.out.println("August");
                break;
            case 9:
                System.out.println("September");
                break;
            case 10:
                System.out.println("October");
                break;
            case 11:
                System.out.println("November");
                break;
            case 12:
                System.out.println("December");
                break;
            default:
                System.out.println("Invalid month");
                break;
        }
    }

    public static void Q3() {
        System.out.println("Q3: Enter how many numbers you want to check for primality: ");
        int input = Integer.parseInt(scan.nextLine());
        int counter = 0;
        for (int i = 2; i < input; i++) {
            boolean check = true;
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) {
                    check = false;
                    break;
                }
            }
            if (check == true) {
                counter++;
            }
        }

        System.out.println("There are: " + counter + " primes between 0 and " + input);
    }

    public static void Q4() {
        Random rng = new Random();

        int enemyHP = 100;
        int turn = 0;
        int enemyArmorClass = 12;
        int attackBuff = 5;

        String nextInput;
        System.out.println(
                "Q4: Let's play a game. Type \"A\" to attack, \"B\" to buff your next attack. Kill the enemy to win!");
        System.out.println(
                "Q4: You must roll higher than the enemy armor class ("+ enemyArmorClass + ") to hit. Roll 20 for a critical hit!");
        System.out.println("Q4: Your damage is 2-16 (2d8)");

        boolean doBuff = false;
        while (true) {

            boolean doAttack = false;
            boolean validInput = false;
            while (!validInput) {
                nextInput = scan.nextLine();
                validInput = true;
                switch (nextInput) {
                    case "A", "a":
                        doAttack = true;
                        break;
                    case "B", "b":
                        doBuff = true;
                        System.out.println("Buffing! +" + attackBuff + " to your next attack roll and damage");
                        break;
                    default:
                        System.out.println("Invalid input");
                        validInput = false;
                }
            }

            if (doAttack) {
                turn++;
                int attackRoll = rng.nextInt(20) + 1;
                int damage = 0;
                System.out.print("You rolled: " + attackRoll);
                if (doBuff) {
                    attackRoll += attackBuff;
                    System.out.print(" + "+attackBuff+" (buff active)\n");
                } else {
                    System.out.println();
                }
                if (attackRoll >= enemyArmorClass) {
                    damage = rng.nextInt(8) + 1;
                    damage += rng.nextInt(8) + 1;
                    if (doBuff) {
                        damage += attackBuff;
                    }
                    if (attackRoll == 20 || (doBuff && attackRoll == 20 + attackBuff)) {
                        damage *= 2;
                        System.out.print("Critical hit! ");
                    }
                    System.out.print("You dealt " + damage + " damage");
                    if (doBuff) {
                        System.out.print(" (buffed attack)");
                    }
                    enemyHP -= damage;
                    System.out.println("\nEnemy HP: " + Math.max(0, enemyHP));

                } else {
                    System.out.println("Miss");
                }

                doBuff = false;
                if (enemyHP <= 0) {
                    System.out.println("Enemy died in " + turn + " turns");
                    scan.close();
                    return;
                }
            }

        }
    }
}
