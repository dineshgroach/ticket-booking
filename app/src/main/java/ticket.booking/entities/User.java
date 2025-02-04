package ticket.booking.entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String name;
    private String password;
    private String hashedPassword;
    private List<Ticket> ticketsBooked;
    private String userID;

    public User() {}
    public User(String name,String password,String hashedPassword,List<Ticket> ticketsBooked,String userID) {
        this.name = name;
        this.password = password;
        this.hashedPassword = hashedPassword;
        this.ticketsBooked = ticketsBooked;
        this.userID = userID;
    }

    public String getName() {
        return  name;
    }
    public String getPassword() {
        return  password;
    }
    public String getHashedPassword() {
        return  hashedPassword;
    }
    public List<Ticket> getTicketsBooked() {
        return  ticketsBooked;
    }
    public void printTickets() {
        for(int i = 0; i < ticketsBooked.size(); i++) {
            System.out.println(ticketsBooked.get(i).getTicketInfo());
        }
    }

    public String getUserID() {
        return  userID;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
    public void setTicketsBooked(List<Ticket> ticketsBooked) {
        this.ticketsBooked = ticketsBooked;
    }
    public void setUserID(String userID) {
        this.userID  = userID;
    }

    public Boolean removeTicketsBooked(String ticketID){
        boolean exists = ticketsBooked.stream().anyMatch(ticket -> ticket.getTicketID().equals(ticketID));
        if(exists) {
            ticketsBooked.removeIf(ticket -> ticket.getTicketID().equals(ticketID));
            return Boolean.TRUE;
        }
        else {
            return Boolean.FALSE;
        }
    }
    public void addTicket(Ticket ticket){
        ticketsBooked.add(ticket);
    }
}
