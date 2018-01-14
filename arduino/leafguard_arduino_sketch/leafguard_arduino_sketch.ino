int sensorPin           = A3;
int pump                = 3;
int moistureValue       = 0;
int redLed = 7;
int greenLed = 6;
int blueLed = 5;

String readString;

void setup() {
  Serial.begin(9600);
  pinMode(pump, OUTPUT);
  pinMode(redLed, OUTPUT);
  pinMode(greenLed, OUTPUT);
  pinMode(blueLed, OUTPUT);
  Serial.println("Arduino ready");
}

void loop() {

  if (getMoisturePercentage() <= 30) {
    setColor(255, 0, 0);  // red
  }
  if (getMoisturePercentage() > 30 && getMoisturePercentage() <= 60) {
    setColor(0, 255, 0);  // blue
  }
  if (getMoisturePercentage() > 60) {
    setColor(255, 255, 0);  // purple
  }

  while (Serial.available()) {
    delay(3);
    char c = Serial.read();
    readString += c;
  }

  readString.trim();
  if (readString.length() > 0) {

    if (readString == "pumpon") {

      while (plantNeedWater()) {
        digitalWrite(pump, HIGH);
      }
      digitalWrite(pump, LOW);
    }

    if (readString == "pumpoff") {
      digitalWrite(pump, LOW);
    }

    if (readString == "data") {
      String ret = "moisture=";
      ret.concat(getMoisturePercentage());
      ret.concat("&pumpstate=");
      ret.concat(digitalRead(pump));
      Serial.println(ret);
    }
    readString = "";
  }
}
int getMoisturePercentage() {
  moistureValue = analogRead(sensorPin);
  moistureValue = (map(moistureValue, 1024, 530, 0, 100) / 1.5);

  if (moistureValue > 100) {
      return moistureValue = 100;
  }
  if (moistureValue < 0) {
      return moistureValue = 0;
  }
  return moistureValue;
}

boolean plantNeedWater() {
  if (getMoisturePercentage() < 50) {
    return true;
  }
  return false;
}

void setColor(int redValue, int greenValue, int blueValue) {
  analogWrite(redLed, redValue);
  analogWrite(greenLed, greenValue);
  analogWrite(blueLed, blueValue);
}
