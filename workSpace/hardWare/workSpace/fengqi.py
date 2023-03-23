
import network
from umqtt.simple import MQTTClient
from machine import Pin, PWM, SPI
import mfrc522
import onewire, ds18x20
import _thread
import ujson
import time
import machine

#全局变量
temp = 0
state = 0   #0:初始默认状态 1:通过mqtt开启灯 2:通过mqtt关闭灯

#WIFI
wlan = None
wifissid = '杜杜的WiFi'
wifipassword = 'dudu888.'
#wifissid = 'D'
#wifipassword = '66668888'

#MQTT
server = '106.55.60.213'
client = None
message = ''
clientid = 'dsh'
port = 1883
username = 'dushengheng'
password = '123'
pubTopic = 'card_topic'
dsTopic = 'ds_topic'
subTopic = 'sub_topic'

#初始化硬件接口
led = Pin(2,Pin.OUT)    #板载LED  
led2 = Pin(15,Pin.OUT)  #外接LED
beep = Pin(22,Pin.OUT)  #外接蜂鸣器
beep.value(1)           #初始化高电平
fire = Pin(34,Pin.IN)   #外接火焰传感器(无火焰输出1,有火焰输出0)
light = Pin(14,Pin.IN)  #外接光敏传感器(无光源输出1,有光源输出0)

#初始化RC522
spi = SPI(miso=Pin(19), mosi=Pin(21, Pin.OUT), sck=Pin(16, Pin.OUT))
rst_pin = Pin(4,Pin.OUT)
sda_pin = Pin(17,Pin.OUT)
rfid = mfrc522.MFRC522(spi,rst_pin,sda_pin)

#初始化DS18B20
ow = onewire.OneWire(Pin(13))
ds = ds18x20.DS18X20(ow) 

#初始化SG90舵机
pwm = PWM(Pin(12),freq=50,duty=75)#舵机周期为20ms,1/freq=20ms,初始为90度,占空比7.5%

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

#连接MQTT
def ConnectMqtt():
  global client
  client=MQTTClient(clientid,server,port,username,password)
  client.connect()

#接收MQTT的信息
def receiveMessage(topic,msg):
  global state
  message = str(msg)
  #print(message)
  
  #判断卡号是否能开门
  try:
    if(message == "b'1'"):    #刷卡成功
      led.value(1)            
      pwm.duty(25)            #调整为0度,占空比2.5%
      print("门已开启")
    elif(message == "b'0'"):  #刷卡失败
      led.value(0)
      pwm.duty(75)            #调整为90度,占空比7.5%
      print("刷卡失败")
    elif(message == "b'on'"):
      state = 1
      print("灯已开启")
    elif(message == "b'off'"):
      state = 2
      print("灯已关闭")
    else:
      print("error")
  except:
    print("异常")

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
      client.publish(pubTopic,card_json)    #输出卡号  
    else:
      led.value(0)
    time.sleep(2)
    
#启动DS18B20
def Ds18b20():  # 读取温度函数
  global client
  global temp
  while True:
    roms = ds.scan()            # 扫描总线上的设备
    ds.convert_temp()           # 温度转换
    for rom in roms:            # 循环打印出设备列表
      temp = ds.read_temp(rom)  # 读出该设备的温度
      temp = round(temp)        #round()方法去除小数
      #print(temp)
      client.publish(dsTopic,str(temp))
      time.sleep(5)
    
      
#启动蜂鸣器
def Beep():
  global temp
  while True:
    while(temp>=30 and fire.value()==0):
      i = 0
      while(i<3):
        beep.value(0)
        time.sleep_ms(500)
        beep.value(1)
        time.sleep_ms(500)
        i = i+1
      time.sleep(5)
 
#启动光敏传感器
def Light():
  global state
  while True:
    if(state==1):     #开启灯
      led2.value(1)
    elif(state==2):   #关闭灯
      led2.value(0)
      
    elif(state==0):   #默认状态下根据光敏传感器控制灯
      if(light.value()==1):
        led2.value(1)
      elif(light.value()==0):
        led2.value(0)
      
    
    
def main():
  global client,server
  
  try:
    ConnectWifi(wifissid,wifipassword)
    ConnectMqtt()
    client.set_callback(receiveMessage)
    client.subscribe(subTopic)
    print("Connected to %s, subscribed to %s." % (server, subTopic))
    
     #开启多任务线程
    _thread.start_new_thread(Ds18b20,())
    _thread.start_new_thread(Rfid,())
    _thread.start_new_thread(Light,())
    time.sleep(1)                         #延时1秒,读取到温度后在进入蜂鸣器
    _thread.start_new_thread(Beep,())
    
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    



















