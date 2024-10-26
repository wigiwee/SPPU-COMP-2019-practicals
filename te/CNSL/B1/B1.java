// package B1;

import java.util.Scanner;

public class B1{

    // Convert decimal IP address or mask to binary
    private static String decimalToBinary(int num) {
        return String.format("%08d", Integer.parseInt(Integer.toBinaryString(num)));
    }

    // Convert an IP address (e.g., "8.20.15.1") into binary format
    private static String[] convertToBinary(String ipAddress) {
        String[] parts = ipAddress.split("\\.");
        String[] binaryParts = new String[parts.length];
        for (int i = 0; i < parts.length; i++) {
            binaryParts[i] = decimalToBinary(Integer.parseInt(parts[i]));
        }
        return binaryParts;
    }

    // Perform bitwise AND between IP address and subnet mask to find network ID
    private static String[] calculateNetworkID(String[] ipBinary, String[] maskBinary) {
        String[] networkID = new String[ipBinary.length];
        for (int i = 0; i < ipBinary.length; i++) {
            networkID[i] = bitwiseAND(ipBinary[i], maskBinary[i]);
        }
        return networkID;
    }

    // Perform bitwise AND operation between two binary strings
    private static String bitwiseAND(String bin1, String bin2) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < bin1.length(); i++) {
            result.append(bin1.charAt(i) == '1' && bin2.charAt(i) == '1' ? '1' : '0');
        }
        return result.toString();
    }

    // Convert binary back to decimal format
    private static String binaryToDecimal(String[] binaryParts) {
        StringBuilder decimal = new StringBuilder();
        for (String binary : binaryParts) {
            int decimalValue = Integer.parseInt(binary, 2);
            decimal.append(decimalValue).append(".");
        }
        return decimal.substring(0, decimal.length() - 1); // Remove the trailing dot
    }

    // Subnetting example for Class C network 255.255.255.224 (/27)
    private static void subnetExample() {
        System.out.println("\n--- Subnetting Example ---");
        String ipAddress = "204.17.5.0";
        String subnetMask = "255.255.255.224";

        String[] ipBinary = convertToBinary(ipAddress);
        String[] maskBinary = convertToBinary(subnetMask);

        System.out.println("IP Address: " + ipAddress);
        System.out.println("Binary IP: " + String.join(".", ipBinary));

        System.out.println("Subnet Mask: " + subnetMask);
        System.out.println("Binary Mask: " + String.join(".", maskBinary));

        String[] networkID = calculateNetworkID(ipBinary, maskBinary);
        System.out.println("Network ID (Binary): " + String.join(".", networkID));
        System.out.println("Network ID (Decimal): " + binaryToDecimal(networkID));
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.print("Enter an IP Address (e.g., 8.20.15.1): ");
            String ipAddress = scanner.nextLine();

            System.out.print("Enter a Subnet Mask (e.g., 255.0.0.0): ");
            String subnetMask = scanner.nextLine();

            String[] ipBinary = convertToBinary(ipAddress);
            String[] maskBinary = convertToBinary(subnetMask);

            // Display IP and subnet mask in binary form
            System.out.println("\nIP Address in Binary: " + String.join(".", ipBinary));
            System.out.println("Subnet Mask in Binary: " + String.join(".", maskBinary));

            // Calculate Network ID
            String[] networkID = calculateNetworkID(ipBinary, maskBinary);
            System.out.println("Network ID in Binary: " + String.join(".", networkID));

            // Calculate and display Host ID
            String hostIDBinary = ipAddressToHostID(ipBinary, maskBinary);
            System.out.println("Host ID in Binary: " + hostIDBinary);
            System.out.println("Host ID in Decimal: " + binaryToDecimal(hostIDBinary.split("\\.")));
        }

        // Subnetting example for a Class C network
        subnetExample();
    }

    // Extract Host ID by identifying bits where the mask is '0'
    private static String ipAddressToHostID(String[] ipBinary, String[] maskBinary) {
        StringBuilder hostID = new StringBuilder();
        for (int i = 0; i < ipBinary.length; i++) {
            hostID.append(bitwiseAND(ipBinary[i], invertBits(maskBinary[i]))).append(".");
        }
        return hostID.substring(0, hostID.length() - 1); // Remove the trailing dot
    }

    // Invert the binary string (i.e., switch 1s to 0s and vice versa) to extract the host part
    private static String invertBits(String binaryString) {
        StringBuilder inverted = new StringBuilder();
        for (char bit : binaryString.toCharArray()) {
            inverted.append(bit == '1' ? '0' : '1');
        }
        return inverted.toString();
    }
}
