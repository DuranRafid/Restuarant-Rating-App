package sample;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Controller implements Runnable{
    private boolean adminConnected = false;
    private Thread t;

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    ServerSocket serverSocket;
    private String priceRating;
    private String serviceRating;
    private String environmentRating;
    private String gmail;
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> addresses = new ArrayList<>();
    private ArrayList<String> foodTypes = new ArrayList<>();
    private ArrayList<String> areas = new ArrayList<>();
    public static int count;
    private Main main ;
    ArrayList<String> output = new ArrayList<>();

    public Controller(){
        Thread t = new Thread(this);
        t.start();
    }
    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(5555);
            while(true){
                Socket socket = serverSocket.accept();
                System.out.println("One Connected");
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                InfosSendToServer infosSendToServer = (InfosSendToServer) inputStream.readObject();
                infosSendToServer.print();

                if(infosSendToServer.getFlag().equals("Recommendation")) {
                    System.out.println(infosSendToServer.getName());
                    System.out.println(infosSendToServer.getAddress());
                    System.out.println(infosSendToServer.getFoodType());
                    if(!names.contains(infosSendToServer.getName())) {
                        names.add(infosSendToServer.getName());
                    }
                    if(!foodTypes.contains(infosSendToServer.getFoodType())) {
                        foodTypes.add(infosSendToServer.getFoodType());
                    }
                    if(!addresses.contains(infosSendToServer.getAddress())) {
                        addresses.add(infosSendToServer.getAddress());
                    }
                    if(!areas.contains(infosSendToServer.getArea())) {
                        areas.add(infosSendToServer.getArea());
                    }
                    for (int i = 0; i < names.size(); i++) {
                        System.out.println(names.get(i) + addresses.get(i) + foodTypes.get(i) + areas.get(i));
                    }
                }
                if(infosSendToServer.getFlag().equals("Admin")) {
                    adminConnected = true;

                }
                if (infosSendToServer.getFlag().equals("Disconnecting")){
                    System.out.println("Admin Disconnecting");
                    adminConnected = false;
                }
                if(adminConnected == true){
                    System.out.println("Admin Connected");
                    outputStream.writeObject(names);
                    outputStream.writeObject(addresses);
                    outputStream.writeObject(areas);
                    outputStream.writeObject(foodTypes);
                    for(int i=0;i<names.size();i++){
                        System.out.println(names.get(i));
                    }

                }
                if (infosSendToServer.getFlag().equals("Rating")){
                    new GenerateRating(infosSendToServer);
                }

            }
        } catch (Exception e) {
           // e.printStackTrace();
        }

    }

    public void setMain(Main main) {
        this.main = main;
    }
}
