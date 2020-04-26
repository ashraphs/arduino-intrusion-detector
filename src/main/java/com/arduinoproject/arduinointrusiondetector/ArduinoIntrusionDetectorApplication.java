package com.arduinoproject.arduinointrusiondetector;

import com.fazecast.jSerialComm.SerialPort;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

@RestController
@Slf4j
@SpringBootApplication
public class ArduinoIntrusionDetectorApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(ArduinoIntrusionDetectorApplication.class, args);

        String URL = "https://api.airtable.com/v0/appobiA05lR9WxHfS/alarm_intrusion_detector";
        String auth = "keyhLqhEb3xJkWCJ4";

        // Configuration to communicate to arduino
        SerialPort sp = SerialPort.getCommPort("COM4");
        sp.setComPortParameters(115200, 8, 1, 0); // default connection settings for Arduino
        sp.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0); // block until bytes can be written

        //Check Port if open
        if (sp.openPort()) {
            log.info("The port is open !!!");
        } else {
            log.info("The port is closed !!!");
            return;
        }

        InputStream in = sp.getInputStream();
        do {
            String output = String.valueOf((char) in.read());
            log.info("Output: {}", output);

            if (output.equals("1")) {

                WebResource.Builder webResource = Client.create()
                        .resource(URL)
                        .header("Authorization", "Bearer " + auth)
                        .header("Content-Type", "application/json");

                String input = "{\"fields\": { \"send_alert\": \"Send Notification !!!\" }, \"typecast\": true}";
                ClientResponse response = webResource.post(ClientResponse.class, input);
                if (response.getStatus() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + response.getStatus());
                }
                log.info("Response: {}", response.getEntity(String.class));
                log.info("Send notification");


            } else {
                log.info("Ignore !!!");
            }

        } while (true);
    }
}
