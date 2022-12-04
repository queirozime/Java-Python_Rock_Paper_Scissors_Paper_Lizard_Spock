import java.io.*;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Server {
    private static ServerSocket server;
    private static int port = 40000;
    public int pythonWins = 0;
    public int javaWins = 0;
    private ArrayList<String> beatsRock = new ArrayList<String>(Arrays.asList("paper","spock"));
    private ArrayList<String> beatsPaper = new ArrayList<String>(Arrays.asList("scissors","lizard"));
    private ArrayList<String> beatsScissor = new ArrayList<String>(Arrays.asList("rock","spock"));
    private ArrayList<String> beatsLizard = new ArrayList<String>(Arrays.asList("rock","scissors"));
    private ArrayList<String> beatsSpock = new ArrayList<String>(Arrays.asList("paper","lizard"));
    private HashMap<String, ArrayList<String>> beats = new HashMap<String, ArrayList<String>>(){
        {
            put("rock",beatsRock);
            put("paper",beatsPaper);
            put("scissors",beatsScissor);
            put("lizard",beatsLizard);
            put("spock",beatsSpock);
        }
    };



    public String getWinner() {
        if(pythonWins > javaWins) return "Python";
        if(javaWins > pythonWins) return "Java";
        return "Draw";
    }

    public void handleWinner(String pythonChoice, String javaChoice) {
        if(pythonChoice.equals(javaChoice)) return;
        ArrayList<String> beatsChoice = beats.get(pythonChoice);
        System.out.println(javaWins);
        if(beatsChoice.contains(javaChoice)) {
            javaWins++;
        }
        else pythonWins++;
    }

    public static void main(String args[]) throws IOException, ClassNotFoundException{
        server = new ServerSocket(port);
        ArrayList<String> options = new ArrayList<String>();
        options.add("rock");
        options.add("paper");
        options.add("scissors");
        options.add("lizard");
        options.add("spock");
        Server instance = new Server();

        int index = 0;


        while(true){
            System.out.println("Waiting for the client request");
            Socket socket = server.accept();

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message = in.readLine();

            System.out.println("Message Received: " + message);
            String response = options.get(index);
            index = (index + 1) % options.size();


            BufferedOutputStream oos = new BufferedOutputStream(socket.getOutputStream());

            if(message.equalsIgnoreCase("exit")) {
                String winner = instance.getWinner();
                String endMessage = "Finished! Winner is " + winner + " " + instance.pythonWins + " x " +
                        instance.javaWins + " |" + (15 - instance.javaWins - instance.pythonWins) + " draws";
                System.out.println(endMessage);
                oos.write(endMessage.getBytes());
                oos.flush();
                socket.close();
                break;
            };

            oos.write((response).getBytes());
            instance.handleWinner(message, response);


            oos.flush();
            socket.close();
        }
        System.out.println("Shutting down Socket server!!");
        server.close();
    }
}
