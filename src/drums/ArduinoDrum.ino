#include <AFMotor.h>
AF_DCMotor stick1(1);
AF_DCMotor stick2(2);
int quarterDelay;
int hitDelay = 20;
int retractDelay = 20;
int count = -1;
String sequence;
int i = 0;
void setup() {
  stick1.setSpeed(0);
  stick2.setSpeed(0);
  Serial.begin(9600);
  Serial.setTimeout(50);
  String in = "";
  if(Serial.available()>0) {
    in = Serial.readString();
  }

  quarterDelay = (1/(parseTempo(in)*4)/60)*1000;
  sequence = parseSequence(in);
  sequence.trim();
}

void loop() {
  if(sequence.charAt(i) == '1') {
    hitAndRetract();
    delay(quarterDelay-hitDelay-retractDelay);
  }
  else {
    delay(quarterDelay);
  }
    
  
  if (i == sequence.length() - 1)
    i = 0;
  else
    i++;
}

int parseTempo(String in) { //**FIX WITH CORRECT SYNTAX**//
  String tempo = "";
  for(int i=6;i<in.length();i++){
    if(isDigit(in.charAt(i)))
      tempo+=in.charAt(i);
    else 
      break;
  }

  return tempo.toInt();

}



String parseSequence(String in){
  String out = in.substring((in.lastIndexOf(':')+1));
  return out;
}


void hitAndRetract() {
  count++;
  if(count%2 == 0){
    stick1.setSpeed(255);
    stick1.run(FORWARD);
    delay(hitDelay);
    stick1.run(BACKWARD);
    delay(retractDelay);
    stick1.setSpeed(0);
  }
  else {
    stick2.setSpeed(255);
    stick2.run(FORWARD);
    delay(hitDelay);
    stick2.run(BACKWARD);
    delay(retractDelay);
    stick2.setSpeed(0);
    
  }
  
}

