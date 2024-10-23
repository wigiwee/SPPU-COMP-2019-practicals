#include <stdio.h>
#include <string.h>

#define POLYNOMIAL 0x9B 

// Function to calculate CRC
unsigned char compute_crc(unsigned char *message, int len) {
    unsigned char crc = 0;  
    for (int i = 0; i < len; i++) {
        crc ^= message[i];  
        for (int j = 0; j < 8; j++) {
            if (crc & 0x80) {
                crc = (crc << 1) ^ POLYNOMIAL;
            } else {
                crc <<= 1;  
            }
        }
    }
    return crc;
}

// Function to append CRC to message
void append_crc(unsigned char *message, int len) {
    unsigned char crc = compute_crc(message, len);  
    message[len] = crc;  
}

// Function to check the message with CRC   
int check_crc(unsigned char *message, int len) {
    unsigned char crc = compute_crc(message, len);
    return (crc == 0);  
}

int main() {
    unsigned char message[100];
    int len;

    printf("Enter the message (7/8 bit ASCII): ");
    scanf("%s", message);
    len = strlen((const char *)message);


    append_crc(message, len);
    len++; 

    printf("Transmitted Message with CRC: ");
    for (int i = 0; i < len; i++) {
        printf("%02X ", message[i]);
    }
    printf("\n");


    // Checking validity of CRC at the receiver side
    if (check_crc(message, len)) {
        printf("Message is valid, no errors detected.\n");
    } else {
        printf("Message is corrupted, errors detected!\n");
    }

    return 0;
}
