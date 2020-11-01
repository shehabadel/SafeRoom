import java.io.IOException;
import java.net.*;
import java.util.LinkedList;
import java.util.List;

public class SafeRoom {
    public static void main (String args[]) throws UnknownHostException {
        InetAddress localhost = InetAddress.getLocalHost();
        String myIP= localhost.getHostAddress();
        String myNetworkIPs="";
        List<String> deviceList = new LinkedList<String>();

        //removing the last digit of my own localhost
        //ip, so it would look like 192.168.1.
        //instead of 192.168.1.7
        //in order to create a template for the ip address for
        //all connected devices in my network, where I can
        //use getByName() method, so I return a list of their names
        //using myNetworkIPs

        for(int i=myIP.length();i>0;--i) {
            if (myIP.charAt(i - 1) == '.') {
                myNetworkIPs = myIP.substring(0, i);
                break;
            }
        }
        //looping and generating list of IPs addresses so
        //I can get Device's name based on each generated IP address
        System.out.println("W");
        for(int i=1; i<255; i++){
            try {
                InetAddress genAddress = InetAddress.getByName(myNetworkIPs+Integer.toString(i));
                if(genAddress.isReachable(1000))
                {
                    System.out.println("Available: " + genAddress.getHostAddress() +"\t"+ genAddress.getHostName());
                    deviceList.add(genAddress.getHostName());
                }
                else{
                    continue;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("X");
        for(String device: deviceList)
        {
            System.out.println("Device is "+device);
        }
        System.out.println("Y");
    }
}
