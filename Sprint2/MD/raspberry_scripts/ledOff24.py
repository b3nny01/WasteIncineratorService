import RPi.GPIO as GPIO
import time

GPIO.setmode(GPIO.BCM)
GPIO.setup(25,GPIO.OUT)

GPIO.output(25,GPIO.LOW)
time.sleep(1)
