int sensorPin           = A3;
int greenLed            = 11;
int yellowLed           = 10;
int redLed              = 9; 
int relaisSwitch        = 3;
int moistureValue       = 0;
int moisturePercentage  = 0;
String readString;

void setup() {
  Serial.begin(9600);
  pinMode(relaisSwitch, OUTPUT);
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
  if (readString.length() >0) {
    if (readString == "pumpon")
    {
      digitalWrite(relaisSwitch, HIGH);
      digitalWrite(greenLed, HIGH);
      digitalWrite(redLed, LOW);
      
      Serial.println("switching on");
    }
    
    if (readString == "pumpoff")
    {
      digitalWrite(relaisSwitch, LOW);
      digitalWrite(redLed, HIGH);
      digitalWrite(greenLed, LOW);

      Serial.println("switching off");
    }
    
    if(readString == "data") 
    {
      String ret = "moisture=";
      ret.concat(moisturePercentage);
      ret.concat("pumpstate=");
      ret.concat(digitalRead(relaisSwitch));
      
      Serial.println(ret);
    }
      

    readString="";
  } 
}


int getMoisturePercentage() {
  return convertToPercent(analogRead(sensorPin));
}

int convertToPercent(int value) {
  return map(value, 686, 1, 0, 100);
}



