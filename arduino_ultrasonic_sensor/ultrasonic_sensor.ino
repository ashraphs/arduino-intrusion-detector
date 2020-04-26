 
int echo_pin      = 2;
int trigger_pin   = 3;
int green_LED_pin = 4;
int red_LED_pin   = 5;
int buzzer        = 6;
 
void setup()
{
  Serial.begin(115200);
  // Set i/o pins
  pinMode(echo_pin, INPUT);
  pinMode(trigger_pin, OUTPUT);
  pinMode(green_LED_pin, OUTPUT);
  pinMode(red_LED_pin, OUTPUT);
  pinMode(buzzer, OUTPUT);

}
 
void loop()
{
  // Send trigger pulse
  digitalWrite(trigger_pin, HIGH);
  delayMicroseconds(20);
  digitalWrite(trigger_pin, LOW);
 
  // Wait for 4ms (68cm return trip at speed of sound)
  delayMicroseconds(4000);
 
  // Check if echo pulse is still active
  if (digitalRead(echo_pin) == 1)
  {
    // Echo pulse still active: no object within distance
    digitalWrite(green_LED_pin, HIGH);
    digitalWrite(red_LED_pin, LOW);
    delay(1000);  // ...for 1 sec

    // Echo to print 0 if no intruder
    Serial.println("0");

  }
  else
  {
    // Echo pulse has ended: an object is within distance
    digitalWrite(green_LED_pin, LOW);
    digitalWrite(red_LED_pin, HIGH);

    // Buzzer to start
    tone(buzzer, 1000); // Send 1KHz sound signal...
    delay(1000);        // ...for 1 sec
    noTone(buzzer);     // Stop sound...
    delay(1000);  

    // Echo to print 1 if got intruder
    Serial.println("1");
  }
 
  // Wait 100 ms before triggering sensor again
  delay(100);
}
