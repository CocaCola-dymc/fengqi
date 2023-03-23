from machine import PWM,Pin
import time

pwm = PWM(Pin(12),freq=50,duty=0)

value=0
while True:
  while(value<=1023):
    value = value+1
    pwm.duty(value)
    time.sleep_ms(20)
    print(value)
  while(value>=0):
    value = value-1
    pwm.duty(value)
    time.sleep_ms(20)
    print(value)
