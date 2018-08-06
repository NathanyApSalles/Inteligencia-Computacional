#!-*- conding: utf8 -*-
import os
	
def minimo (q): 
    minimo = q[0]
    for j in range (len(q)):
        if q[j] < minimo :
            minimo = q[j]     
    
    return minimo

def dijkstra(s, listaAdj, vertices, arestas):
    dist = [99999] * len(listaAdj) #armazena a distancia entre da origem a cada vertice 
    pred = [None] * len(vertices) #vetor que indica o predecessor de cada vértice no caminho mínimo a partir da origem
    dist[s] = 0
    q = vertices #lista que contem todos os vertices
    
    while len(q) != 0:
        u = minimo(q) #vertice de menor distancia dentre os vertices de q 
        q.remove(u) # remove u de q, pois j´a foi processado
        for arestas in listaAdj[u]:                      
            v = arestas[0]
            w = arestas[1] #peso da aresta
            if dist[v] > ( dist[u] + w ):
                dist[v] = ( dist[u] + w )
                pred[v] = u
                
    return [dist,pred]

#funçao para imprimir caminho percorrido
def recCaminho(s, t, pred):
    c = [t] 
    aux = t 
    while aux != s: 
        aux = pred[aux] 
        c = [aux] + c 

    return c

def bellmanFord(s, listaAdj, vertices, arestas):
    dist = [999999] * len(listaAdj)
    pred = [None] * len(listaAdj)
    dist[s] = 0
    u = s
    for i in range (len(vertices)):
        for j in range (len(arestas)):
            v = arestas[j][0]
            w = arestas[j][1]           
            if dist[v] > dist [u] + w:
                dist[v] = dist[u] + w
                pred[v] = u
                u = v
    for j in range (len(arestas)):
        if dist[v] > dist [u] + w:
            return ['','']        
    return [dist,pred]
