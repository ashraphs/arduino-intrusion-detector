# Arduino Project-Intrusion Detector

## Description
This project contained the code to add the data in the AirTable and also Arduino code. The summary of the project is to store the data of ultrasonic sensor to AirTable and send notification to mobile app.

## Code Path
Code | Path
---------- | --------- 
__Intrusion detector__ | arduino-intrusion-detector
__Arduino code__ | arduino-intrusion-detector/arduino_ultrasonic_sensor

## Requirement
- Springboot v2.2.6
- Java 8
- Arduino
- Airtable account. Please click [this link](https://airtable.com/) to create your account.
- IFTT account. Please download the mobile apps in the store for android/ios.

## Items
- 1x Arduino uno
- 1x ultrasonic distance sensor (HC-SR04)
- 2x different colour led
- 1x buzzer
- 3x resistor 270 ohm
- cable

## Diagram
Diagram of intrusion detector:

![Diagram of intrusion detector](https://github.com/ashraphs/arduino-intrusion-detector/blob/master/images/arduino.png?raw=true)

## Notification flow
The notification send through IFTT, when the data insert in the AirTable.

### IFTT

if This
`Airtable-new record created`
then
`Send the alert through notification`

Diagram of IFTT in mobile app

![Diagram of IFTT](https://github.com/ashraphs/arduino-intrusion-detector/blob/master/images/iftt.jpg?raw=true)
