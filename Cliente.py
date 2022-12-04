from socket import *
import random
options = ["rock", "paper", "scissors", "lizard", "spock"]
beats = {
    "rock": ["paper", "spock"],
    "paper": ["scissors", "lizard"],
    "scissors": ["rock", "spock"],
    "lizard": ["rock", "scissors"],
    "spock": ["paper", "lizard"]
}
server_last_play = ''

HOST, PORT = "127.0.0.1", 40000

for i in range(0, 16):
    choice = random.choice(options)
    with socket(AF_INET, SOCK_STREAM) as s:
        s.connect((HOST, PORT))

        if(i == 15):
            choice = "exit"
        elif(server_last_play != ''):
            choice = random.choice(beats[server_last_play])
        message = choice + "\n"
        print("Sent " + message)

        s.sendall(str.encode(message))
        data = s.recv(1024)
        server_last_play = data.decode("utf-8")
    print(f"Received {data.decode()!r}\n")
