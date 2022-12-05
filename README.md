# Rock, Paper, Scissors, Lizard, Spock
Esse repositório representa uma implementação simples de um jogo da série Big Bang Theory entre um programa em Java e um em Python, utilizando comunicação via socket.
# Arquivos
## Java Server
O socket servidor, feito em Java, se responsabiliza por receber a jogada do cliente e, utilizando uma heurística que não depende da jogada recebida, competir com o cliente. O servidor calcula o vencedor da rodada, devolve sua jogada para o cliente e armazena o número de rodadas vencidas por cada um.
Nesse caso, a heurística do servidor é um palpite circular, que joga uma jogada diferente na mesma sequência todas as vezes.

## Python Client
O socket cliente, feito em Python, começa seu palpite com uma jogada aleatória. Depois de receber a primeira resposta do servidor, armazena-se a jogada e escolhe aleatoriamente uma jogada que ganharia da última jogada do servidor. O cliente faz um total de 15 rodadas, ao fim das quais envia uma mensagem que termina a conexão.

# Conclusão
As heurísticas escolhidas mostram uma vantagem clara para o servidor em Java, visto que sua jogada é sequencial e a do cliente é baseada na última jogada do servidor.

## Resposta no Cliente 

![image](https://user-images.githubusercontent.com/63520242/205523945-c3b763a4-bb85-46ca-93dc-8540feb6a56b.png)

## Resposta no Servidor
Os números são os contadores de vitória do Java em cada rodada.

![image](https://user-images.githubusercontent.com/63520242/205523992-2768c62a-5316-4158-bb34-70ad75047c11.png)
