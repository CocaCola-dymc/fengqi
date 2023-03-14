
import network
from umqtt.simple import MQTTClient
from machine import Pin, SPI
import mfrc522
import _thread
import ujson
import time
import machine

#from test1 import set_name,get_name,get_bytes,set_value,read_value

#WIFI
wlan = None
#wifissid = 'MC-D'
#wifipassword = 'Z18011846602'

wifissid = 'D'
wifipassword = '66668888'


#MQTT
server = '106.55.60.213'
client = None
clientid = 'dsh'
port = 1883
username = 'dushengheng'
password = '123'
pubTopic = 'pay_topic'
pubTopic1 = 'card_topic'
subTopic = 'money_topic'


spi = SPI(miso=Pin(19), mosi=Pin(21, Pin.OUT), sck=Pin(16, Pin.OUT))
rst_pin = Pin(4,Pin.OUT)
sda_pin = Pin(17,Pin.OUT)

rfid = mfrc522.MFRC522(spi,rst_pin,sda_pin)
led = Pin(2,Pin.OUT)


  
def ConnectMqtt():
  global client
  client=MQTTClient(clientid,server,port,username,password)
  client.connect()


def receiveMessage(topic,msg):
  global subTopic,client
  message = str(msg)
  #print(message)
  
  #判断余额是否足够
  try:
    if(message == "b'1'"): #刷卡成功
      led.value(1)
      print("刷卡成功，谢谢")
    elif(message == "b'0'"): #刷卡失败
      led.value(0)
      print("刷卡失败,余额不足")
    else:
      print("error")
  except:
    print("异常")
    
#连接WIFI
def ConnectWifi(wifissid,wifipassword):
  global wlan
  wlan=network.WLAN(network.STA_IF)
  wlan.active(True)
  wlan.disconnect()
  wlan.connect(wifissid,wifipassword)
  while(wlan.ifconfig()[0] == '0.0.0.0'):
    time.sleep(1)
  print('Connected to wifi successfully!')
    
 
#启动RFID
def Rfid():
  global led,rfid
  global pubTopic,client
  while True:
    a = rfid.read_card()
    if a:
      #print(a)
      num = str(a[0])
      print(num)

      card_json = num
      client.publish(pubTopic,card_json)    #判断余额
      client.publish(pubTopic1,card_json)   #充值，查询
      
      
    else:
      led.value(0)
    time.sleep(2)
    
    
def main():
  global client
  global server
  try:
    
    ConnectWifi(wifissid,wifipassword)
    ConnectMqtt()
    
    client.set_callback(receiveMessage)
    client.subscribe(subTopic)
    print("Connected to %s, subscribed to %s." % (server, subTopic))
    _thread.start_new_thread(Rfid,())
    
  
    while True:
      client.wait_msg()
  except:
    print("connect wifi failed")
    #machine.reset()
  finally:
    if(client is not None):
      client.disconnect()
    wlan.disconnect()
    wlan.active(False)
  
      
main()    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    














