import java.io.FileWriter;
import java.io.IOException;

public class Ticket {
    private int row;
    private int seat;
    private double price;
    private Person person;

    public Ticket(int row, int seat, double price, Person person){
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    public double getPrice() {
        return price;
    }

    public int getSeat() {
        return seat;
    }

    public int getRow() {
        return row;
    }

    public Person getPerson() {
        return person;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRow(int row) {
        this.row = row;
    }
    public void setSeat(int seat) {
        this.seat = seat;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void printTicketInfo(){
        String row = "";
        switch (getRow()){
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

        System.out.print("Seat " + row + getSeat() + " costs " + getPrice() + ". The seat is booked by "); getPerson().printPersonInfo();
    }

    public void save(){
        int row_num = getRow();
        String row = "";
        switch (row_num){
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

        try{
            String fileName = row + getSeat() + ".txt";
            FileWriter file = new FileWriter(fileName);
            file.write("Seat " + row + getSeat() + " costs " + getPrice() + "\nThe ticket is owned by " + getPerson().getName() + " " + getPerson().getSurname());
            file.close();
        }
        catch (IOException e){
            System.out.println("File already exists");
        }
    }
}
