package C1;

import java.net.InetAddress;
import java.util.Scanner;

public class DNSLookup {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter domain name or IP address: ");
        String input = scanner.nextLine();

        try {
            InetAddress inetAddress = InetAddress.getByName(input);

            // If input is an IP address, this returns the domain name
            String hostName = inetAddress.getHostName();

            // If input is a domain name, this returns the IP address
            String hostAddress = inetAddress.getHostAddress();

            System.out.println("Host Name: " + hostName);
            System.out.println("Host Address: " + hostAddress);
        } catch (Exception e) {
            System.out.println("Could not find information for: " + input);
        }

        scanner.close();
    }
}
