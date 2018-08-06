#!-*- conding: utf8 -*-
import os
import math
import caminhoMinimo
import matplotlib.pyplot as plt

#funçao para calcular a distancia entre duas posiçoes
def distancia (origemL, destinoL,origemC, destinoC, peso1, peso2):
	dist = 0
	dist = math.sqrt(pow(0.1*(origemL-destinoL),2)+pow(0.1*(origemC-destinoC),2)+pow(peso2 - peso1,2))
	return dist


#leitura em arquivo
matrizStr = []

with open('relevo.txt') as openfileobject:
    for linha in openfileobject:
        matrizStr.append((linha.split(' \t')[:-1]))

matriz = []

#transforma os itens da matriz lida do arquivo de string para float
for i in matrizStr:
	listNum = []
	for j in i:
		listNum.append(float(j))
	matriz.append(listNum)

#transformar a matriz lida em uma lista de adjacencia 
tamMatriz = len(matriz)
listaAdj = [[] for x in range(10000)]
pos = 0

length = 100

#calcula a distancia dos vizinhos de cada posiçao
for i in range(0, length):
	for j in range(0, length):
		if i > 0 and i < length-1:
			if j > 0 and j < length-1:
				#quadrado_central
				listaAdj[ pos ].append((pos-tamMatriz, distancia(i,i-1,j,j,matriz[i-1][j],matriz[i][j]))) #cima
				listaAdj[ pos ].append((pos+tamMatriz, distancia(i,i+1,j,j,matriz[i+1][j],matriz[i][j]))) #baixo
				listaAdj[ pos ].append((pos-1, distancia(i,i,j,j-1,matriz[i][j],matriz[i][j-1]))) #anterior
				listaAdj[ pos ].append((pos+1, distancia(i,i,j,j+1,matriz[i][j],matriz[i][j+1]))) #sucessor
				listaAdj[ pos ].append((pos-tamMatriz+1, distancia(i,i-1,j,j+1,matriz[i][j],matriz[i-1][j+1]))) #diagonalDirSup
				listaAdj[ pos ].append((pos-tamMatriz-1, distancia(i,i-1,j,j-1,matriz[i][j],matriz[i-1][j-1]))) #diagonalEsqSup
				listaAdj[ pos ].append((pos+tamMatriz-1, distancia(i,i+1,j,j-1,matriz[i][j],matriz[i+1][j-1]))) #diagonalEsqInf
				listaAdj[ pos ].append((pos+tamMatriz+1, distancia(i,i+1,j,j+1,matriz[i][j],matriz[i+1][j+1]))) #diagonalDirInf
				
				


			elif j > 0:
				#lateral_direita
				listaAdj[ pos ].append((pos-tamMatriz, distancia(i,i-1,j,j,matriz[i-1][j],matriz[i][j]))) #cima
				listaAdj[ pos ].append((pos+tamMatriz, distancia(i,i+1,j,j,matriz[i+1][j],matriz[i][j]))) #baixo
				listaAdj[ pos ].append((pos-1, distancia(i,i,j,j-1,matriz[i][j],matriz[i][j-1]))) #anterior
				listaAdj[ pos ].append((pos-tamMatriz-1, distancia(i,i-1,j,j-1,matriz[i][j],matriz[i-1][j-1]))) #diagonalEsqSup
				listaAdj[ pos ].append((pos+tamMatriz-1, distancia(i,i+1,j,j-1,matriz[i][j],matriz[i+1][j-1]))) #diagonalEsqInf
				
				

			else:
				#lateral_esquerda
				listaAdj[ pos ].append((pos-tamMatriz, distancia(i,i-1,j,j,matriz[i-1][j],matriz[i][j]))) #cima
				listaAdj[ pos ].append((pos+tamMatriz, distancia(i,i+1,j,j,matriz[i+1][j],matriz[i][j]))) #baixo
				listaAdj[ pos ].append((pos+1, distancia(i,i,j,j+1,matriz[i][j],matriz[i][j+1]))) #sucessor
				listaAdj[ pos ].append((pos-tamMatriz+1, distancia(i,i-1,j,j+1,matriz[i][j],matriz[i-1][j+1]))) #diagonalDirSup
				listaAdj[ pos ].append((pos+tamMatriz+1, distancia(i,i+1,j,j+1,matriz[i][j],matriz[i+1][j+1]))) #diagonalDirInf

				
		elif j > 0 and j < length-1:
			if i < length-1:
				#superior
				listaAdj[ pos ].append((pos+tamMatriz, distancia(i,i+1,j,j,matriz[i+1][j],matriz[i][j]))) #baixo
				listaAdj[ pos ].append((pos-1, distancia(i,i,j,j-1,matriz[i][j],matriz[i][j-1]))) #anterior
				listaAdj[ pos ].append((pos+1, distancia(i,i,j,j+1,matriz[i][j],matriz[i][j+1]))) #sucessor
				listaAdj[ pos ].append((pos+tamMatriz-1, distancia(i,i+1,j,j-1,matriz[i][j],matriz[i+1][j-1]))) #diagonalEsqInf
				listaAdj[ pos ].append((pos+tamMatriz+1, distancia(i,i+1,j,j+1,matriz[i][j],matriz[i+1][j+1]))) #diagonalDirInf


			else:
				#inferior
				listaAdj[ pos ].append((pos-tamMatriz, distancia(i,i-1,j,j,matriz[i-1][j],matriz[i][j]))) #cima
				listaAdj[ pos ].append((pos-1, distancia(i,i,j,j-1,matriz[i][j],matriz[i][j-1]))) #anterior
				listaAdj[ pos ].append((pos+1, distancia(i,i,j,j+1,matriz[i][j],matriz[i][j+1]))) #sucessor
				listaAdj[ pos ].append((pos-tamMatriz+1, distancia(i,i-1,j,j+1,matriz[i][j],matriz[i-1][j+1]))) #diagonalDirSup
				listaAdj[ pos ].append((pos-tamMatriz-1, distancia(i,i-1,j,j-1,matriz[i][j],matriz[i-1][j-1]))) #diagonalEsqSup

		else:
			#quinas
			if i == 0 and j == 0:
				listaAdj[ pos ].append((pos+1, distancia(i,i,j,j+1,matriz[i][j],matriz[i][j+1]))) #sucessor
				listaAdj[ pos ].append((pos+tamMatriz, distancia(i,i+1,j,j,matriz[i+1][j],matriz[i][j]))) #baixo
				listaAdj[ pos ].append((pos+tamMatriz+1, distancia(i,i+1,j,j+1,matriz[i][j],matriz[i+1][j+1]))) #diagonalDirInf
			elif i == 99 and j == 0:
				listaAdj[ pos ].append((pos-tamMatriz, distancia(i,i-1,j,j,matriz[i-1][j],matriz[i][j]))) #cima
				listaAdj[ pos ].append((pos+1, distancia(i,i,j,j+1,matriz[i][j],matriz[i][j+1]))) #sucessor
				listaAdj[ pos ].append((pos-tamMatriz+1, distancia(i,i-1,j,j+1,matriz[i][j],matriz[i-1][j+1]))) #diagonalDirSup
			elif i == 0 and j == 99:
				listaAdj[ pos ].append((pos-1, distancia(i,i,j,j-1,matriz[i][j],matriz[i][j-1]))) #anterior
				listaAdj[ pos ].append((pos+tamMatriz, distancia(i,i+1,j,j,matriz[i+1][j],matriz[i][j]))) #baixo
				listaAdj[ pos ].append((pos+tamMatriz-1, distancia(i,i+1,j,j-1,matriz[i][j],matriz[i+1][j-1]))) #diagonalEsqInf
			else:
				listaAdj[ pos ].append((pos-1, distancia(i,i,j,j-1,matriz[i][j],matriz[i][j-1]))) #anterior
				listaAdj[ pos ].append((pos-tamMatriz, distancia(i,i-1,j,j,matriz[i-1][j],matriz[i][j]))) #cima
				listaAdj[ pos ].append((pos-tamMatriz-1, distancia(i,i-1,j,j-1,matriz[i][j],matriz[i-1][j-1]))) #diagonalEsqSup
				
		pos = pos + 1 

#cria as aresta com o peso entre os vizinhos		
arestas = []			
for i in range(0,len(listaAdj)):
	for j in listaAdj[i]:
		arestas.append((i , j[0], j[1]))



#cria os vertices
vertices = [x for x in range(len(listaAdj))]
	
s = 0 # vertice de origem
t = 0 # vertice de destino

#descobrir a posiçao de uma coordenada dentro da lista de adjacencia 
pos = 0 
length = 100    
for x in range(0, length):
    for y in range(0, length):
	    pos = pos + 1
	    if x == 0 and y == 0:
	        s = pos
	    if x == 99 and y == 98:
	        t = pos
		        
#funçao Dijkstra: funç~ao para obter caminho minimo para um vertice por iteraçao
#ate chegar todos os vertices
[dist, pred] = caminhoMinimo.dijkstra(s, listaAdj, vertices, arestas)

#funçao para retornar o caminho percorrido
caminho = caminhoMinimo.recCaminho(s, t, pred)
print("Caminho Dijkstra: ",caminho, "\nDistancia Dijkstra: ", dist[t])


x = []
y = []
i = 0
j = 0

for val in range(0,len(caminho)):
	i = caminho[val]%100
	j = int(caminho[val]/100)
	x.append(j)
	y.append(i)

for i in range(0,len(x)):
	print("(", x[i], ",", y[i], ")" )

plt.plot(x,y)
plt.show()

#[dist, pred] = caminhoMinimo.bellmanFord(s, listaAdj, vertices, arestas)
#caminho = caminhoMinimo.recCaminho(s, t, pred)
#print("Caminho bellmanFord: ",caminho, "\nDistancia bellmanFord: ", dist[t])


