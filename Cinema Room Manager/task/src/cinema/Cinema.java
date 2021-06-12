package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        final int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        final int seats = scanner.nextInt();

        String[][] array = new String[rows][seats];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                array[i][j] = "S";
            }
        }

        printMenu(array);

    }


    public static void printMenu(String[][] array) {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");

        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        if (m == 1) {
            showTheSeats(array);
        } else if (m == 2) {
            buyTicket(array);
        } else if (m == 3) {
            statistics(array);
        } else {
            exit();
        }
    }


    public static void showTheSeats(String[][] array) {
        System.out.print("Cinema:\n ");
        for (int i = 1; i <= array[0].length; i++) {
            System.out.print(" " + i);
        }
        System.out.println();

        for (int i = 0; i < array.length; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(" " + array[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        printMenu(array);
    }

    public static void buyTicket(String[][] array) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a row number:");
        int rowNumber = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatNumber = scanner.nextInt();

        if (rowNumber > array.length || seatNumber > array[0].length) {
            System.out.println("Wrong input!");
            buyTicket(array);
        } else if ("B".equals(array[rowNumber - 1][seatNumber - 1])) {
            System.out.println("That ticket has already been purchased!");
            buyTicket(array);
        } else {
            int rows = array.length - 1;
            int seats = array[0].length - 1;
            int price = calculateTicketPrice(rows, seats, rowNumber);

            System.out.printf("Ticket price: $%d\n", price);

            array[rowNumber - 1][seatNumber - 1] = "B";
            System.out.println();
            printMenu(array);
        }
    }

    public static int calculateTicketPrice(int rows, int seats, int rowNumber) {
        if (rows * seats < 60 || (rows * seats >= 60 && rowNumber <= rows / 2)) {
            return 10;
        } else {
            return 8;
        }
    }

    public static void statistics(String[][] array) {
        int numberOfPurchasedTickets = 0;
        double percentage;
        int currentIncome = 0;
        int totalIncome = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                totalIncome += calculateTicketPrice(array.length, array[0].length, i + 1);
                if ("B".equals(array[i][j])) {
                    numberOfPurchasedTickets++;
                    currentIncome += calculateTicketPrice(array.length, array[0].length, i + 1);
                }
            }
        }
        percentage = (double) numberOfPurchasedTickets * 100 / (array.length * array[0].length);


        System.out.printf("Number of purchased tickets: %d\n", numberOfPurchasedTickets);
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.printf("Current income: $%d\n", currentIncome);
        System.out.printf("Total income: $%d\n", totalIncome);
        printMenu(array);
    }

    public static void exit() {

    }
}