package ticket.booking;

import ticket.booking.entities.Ticket;
import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.services.TrainService;
import ticket.booking.services.UserBookingService;
import ticket.booking.util.UserServiceUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("Running Train Booking System:");
        Scanner scanner = new Scanner(System.in);
        UserBookingService userBookingService;
        try{
            userBookingService = new UserBookingService();
        }catch (IOException ex){
            System.out.println("There is Something Wrong");
            System.out.println("Error Message: " + ex.getMessage());
            return;
        }
        int option = 0;
        while(option != 7) {
            System.out.println("Choose Option :");
            System.out.println("1. Sign UP");
            System.out.println("2. Login");
            System.out.println("3. Fetch Bookings");
            System.out.println("4. Search Trains");
            System.out.println("5. Book a Seat");
            System.out.println("6. Cancel my Booking");
            System.out.println("7. Exit the App");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Enter the UserName to Sign UP");
                    String newUserName = scanner.next();
                    System.out.println("Enter the Password to Sing UP");
                    String newPassword = scanner.next();
                    User newUser = new User(newUserName,newPassword, UserServiceUtil.hashPassword(newPassword),new ArrayList<>(), UUID.randomUUID().toString());
                    userBookingService.signUp(newUser);
                    break;
                case 2:
                    System.out.println("Enter the UserName to Login");
                    String loginUserName = scanner.next();
                    System.out.println("Enter the Password to Loign");
                    String loginPassword = scanner.next();
                    User loginUser = new User(loginUserName,loginPassword, UserServiceUtil.hashPassword(loginPassword),new ArrayList<>(), UUID.randomUUID().toString());
                    try {
                        userBookingService = new UserBookingService(loginUser);
                    }catch(IOException ex){
                        return;
                    }
                    break;
                case 3:
                    System.out.println("Fetching your bookings");
                    userBookingService.fetchBooking();
                    break;
                case 4:
                    System.out.println("Enter your Source Station: ");
                    String searchSource = scanner.next();
                    System.out.println("Enter your Destination Station ");
                    String searchDestination = scanner.next();
                    List<Train> trains = userBookingService.getTrains(searchSource,searchDestination);
                    for(Train train:trains) {
                        System.out.println(train.getTrainInfo());
                        System.out.println(searchSource + " : " + train.getStationTiming(searchSource));
                        System.out.println(searchDestination + " : " + train.getStationTiming(searchDestination));
                    }
                    break;
                case 5:
                    System.out.println("Enter your Source Station: ");
                    String source = scanner.next();
                    System.out.println("Enter your Destination Station ");
                    String destination = scanner.next();
                    List<Train> availTrains = userBookingService.getTrains(source,destination);
                    for(Train train:availTrains) {
                        System.out.println("Train ID: " + train.getTrainID() + " Train No: " + train.getTrainNo());
                        System.out.println(source + " : " + train.getStationTiming(source));
                        System.out.println(destination + " : " + train.getStationTiming(destination));
                    }
                    System.out.println("Enter the Train ID: ");
                    String trainID = scanner.next();
                    TrainService trainService = new TrainService();
                    Train train = trainService.getTrain(trainID);
                    System.out.println("Enter Date of Travel: ");
                    String dateOfTravel = scanner.next();
                    Ticket newTicket = new Ticket(UUID.randomUUID().toString(),userBookingService.getUser().getUserID(),source,destination,dateOfTravel,train);
                    System.out.println("Ticked is Successfully Booked");
                    newTicket.getTicketInfo();
                    userBookingService.getUser().addTicket(newTicket);
                    break;
                case 6:
                    System.out.println("Enter the TickedID of Booking you want to cancel: ");
                    String ticketID = scanner.next();
                    if(userBookingService.cancelBooking(ticketID)){
                        System.out.println("Booking Successfully Removed");
                    }
                    else {
                        System.out.println("Error Occurred");
                    }
                    break;
                default:
                    option = 7;
                    break;
            }
        }
    }

}
