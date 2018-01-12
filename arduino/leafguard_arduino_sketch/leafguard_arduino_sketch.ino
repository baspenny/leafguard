int sensorPin           = A3;
int greenLed            = 11;
int yellowLed           = 10;
int redLed              = 9;
int pump                = 3;
int moistureValue       = 0;

String readString;

void setup() {
  Serial.begin(9600);
  pinMode(pump, OUTPUT);
  pinMode(greenLed, OUTPUT);
  pinMode(yellowLed, OUTPUT);
  pinMode(redLed, OUTPUT);
  Serial.println("Arduino ready");
  digitalWrite(redLed, HIGH);
}

void loop() {
  while (Serial.available()) {
    delay(3);
    char c = Serial.read();
    readString += c;
  }
  readString.trim();
  if (readString.length() > 0) {

    if (readString == "pumpon")
    {            
      while (plantNeedWater()) {

        digitalWrite(pump, HIGH);
        digitalWrite(greenLed, HIGH);
        digitalWrite(redLed, LOW);

        Serial.println("switching on");
        
      } 
      Serial.println("No water needed");
        digitalWrite(pump, LOW);
        digitalWrite(greenLed, LOW);
        digitalWrite(redLed, HIGH);
    }

    if (readString == "pumpoff")
    {
      digitalWrite(pump, LOW);
      digitalWrite(redLed, HIGH);
      digitalWrite(greenLed, LOW);      
      Serial.println("switching off");
    }

    if (readString == "data")
    {
      moistureValue = analogRead(sensorPin);
      moistureValue = map(moistureValue, 550,0,0,100);
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
  moistureValue = map(moistureValue, 1024, 530, 0, 100);
  if (moistureValue > 100)
  {
    return moistureValue = 100;
  }
  return moistureValue;
}

boolean plantNeedWater() {
  if (getMoisturePercentage() < 50) {
    return true;
  }
  return false;
}
