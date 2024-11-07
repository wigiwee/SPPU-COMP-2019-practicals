const int buzzerPin = 9;

void setup(){
  pinMode(buzzerPin, OUTPUT);
}
void loop(){
  analogWrite(buzzerPin, 127);
  delay(500);
  analogWrite(buzzerPin,0);
  delay(2500);
}