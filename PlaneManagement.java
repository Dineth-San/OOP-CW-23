import java.util.Scanner;

public class PlaneManagement {

    private int[][] seats = new int[4][];
    private Scanner input = new Scanner(System.in);
    Ticket[] tickets;

    public PlaneManagement(){
        tickets = new Ticket[52];
        seats[0] = new int[14];
        seats[1] = new int[12];
        seats[2] = new int[12];
        seats[3] = new int[14];
    }

    public void menu(){
        System.out.println("*************************************");
        System.out.println("*            MENU OPTIONS           *");
        System.out.println("*************************************");
        System.out.println("\t1) Buy a seat");
        System.out.println("\t2) Cancel a seat");
        System.out.println("\t3) Find first available seat");
        System.out.println("\t4) Show seating plan");
        System.out.println("\t5) Print tickets information and total sales");
        System.out.println("\t6) Search ticket");
        System.out.println("\t0) Quit");
        System.out.println("*************************************");
        System.out.print("Please select and option: ");
        int value = input.nextInt();
        input.nextLine();

        switch (value){
            case 1:
                buy_seat();
                break;
            case 2:
                cancel_seat();
                break;
            case 3:
                find_first_available();
                break;
            case 4:
                show_seating_plan();
                break;
            case 5:
                print_tickets_info();
                break;
            case 6:
                search_ticket();
                break;
            case 0:
                System.out.println("Ending program");
                break;
            default:
                System.out.println("Invalid input");
                menu();
        }
    }

    private int[] select_seat(){
        int[] return_array = new int[3];
        int row_num;
        int seat_num;
        int seat_count = 0;
        while(true){
            System.out.println("Enter row letter: ");
            String row = input.nextLine().toLowerCase();

            switch(row) {
                case "a":
                    seat_count = 14;
                    row_num = 0;
                    break;
                case "b":
                    seat_count = 12;
                    row_num = 1;
                    break;
                case "c":
                    seat_count = 12;
                    row_num = 2;
                    break;
                case "d":
                    seat_count = 14;
                    row_num = 3;
                    break;
                default:
                    System.out.println("Invalid row. Try again");
                    continue;
            }
            System.out.println("Enter seat number: ");
            seat_num = input.nextInt();
            input.nextLine();
            break;
        }
        return_array[0] = row_num;
        return_array[1] = seat_num;
        return_array[2] = seat_count;

        return return_array;
    }

    public void buy_seat(){
        int[] seat_details = select_seat();
        int row_num = seat_details[0];
        int seat_num = seat_details[1];
        int seat_count = seat_details[2];

        if(seat_num <= seat_count && seats[row_num][seat_num-1] == 0){

            System.out.println("Enter your first name: ");
            String name = input.nextLine();
            System.out.println("Enter your surname: ");
            String surname = input.nextLine();
            System.out.println("Enter your email: ");
            String email = input.nextLine();
            Person person = new Person(name, surname, email);

            double price;
            if(seat_num <= 5){
                price = 200;
            }
            else if (seat_num <= 9) {
                price = 150;
            }
            else {
                price = 180;
            }

            Ticket ticket = new Ticket(row_num, seat_num, price, person);
            for (int i = 0; i < tickets.length; i++){
                if(tickets[i] == null){
                    tickets[i] = ticket;
                    break;
                }
            }

            System.out.println("Your seat has been selected");
            seats[row_num][seat_num-1] = 1;
            ticket.save();
            menu();
            return;
        }
        System.out.println("Invalid seat index OR seat is already taken. Try again.");
        menu();

    }

    public void cancel_seat(){
        int[] seat_details = select_seat();
        int row_num = seat_details[0];
        int seat_num = seat_details[1];
        int seat_count = seat_details[2];

        if(seat_num <= seat_count && seats[row_num][seat_num-1] == 1){
            for(int i = 0; i < tickets.length; i++){
                if(tickets[i] != null){
                    if(tickets[i].getRow() == row_num && tickets[i].getSeat() == seat_num){
                        tickets[i] = null;
                    }
                }
            }

            System.out.println("Your seat has been cancelled");
            seats[row_num][seat_num-1] = 0;
            menu();
            return;
        }
        System.out.println("Invalid seat index OR seat is not occupied. Try again.");
        menu();
    }

    public void find_first_available(){
        for(int i = 0; i < 4; i++){
            for (int seat = 0; seat < seats[i].length; seat++){
                if(seats[i][seat] == 0){
                    String row = "";
                    switch (i){
                        case 0:
                            row = "A";
                            break;
                        case 1:
                            row = "B";
                            break;
                        case 2:
                            row = "C";
                            break;
                        case 3:
                            row = "D";
                            break;
                    }
                    System.out.println("First available seat is " + row + (seat+1));
                    menu();
                }
            }
        }
    }

    public void show_seating_plan(){
        for(int i = 0; i < 4; i++){
            for (int seat = 0; seat < seats[i].length; seat++){
                if(seats[i][seat] == 0){
                    System.out.print("O");
                }
                else{
                    System.out.print("X");
                }
            }
            System.out.println();
        }
        menu();
    }

    public void print_tickets_info(){
        double total = 0;
        for(int i = 0; i < tickets.length; i++){
            if(tickets[i] != null){
                total +=  tickets[i].getPrice();
            }
        }

        System.out.println("Total sales = $" + total);
        menu();
    }

    public void search_ticket(){
        int[] seat_details = select_seat();
        int row_num = seat_details[0];
        int seat_num = seat_details[1];

        if(seats[row_num][seat_num-1] == 0){
            System.out.println("This seat is available");
            menu();
            return;
        }
        for(int i = 0; i < tickets.length; i++){
            if(tickets[i] != null){
                if(tickets[i].getRow() == row_num && tickets[i].getSeat() == seat_num){
                    tickets[i].printTicketInfo();
                    menu();
                }
            }
        }

    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Plane Management application");
        PlaneManagement plane = new PlaneManagement();
        plane.menu();
    }
}
