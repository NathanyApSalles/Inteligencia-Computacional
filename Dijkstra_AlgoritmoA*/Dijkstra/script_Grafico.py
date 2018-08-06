import matplotlib.pyplot as plt
import numpy as np

N = 200
dcpu = 2
d2 =  2.06
d3 = 2.06
D = dcpu + d2 + d3
Dmax = max(dcpu, d2, d3)
Z = 15
X0 = 0.22

arrayN = [X for X in range(1,N+1)]

arrayLinhaPrincipal = [n*D + Z for n in range(1,N)]
arrayLinhaPrincipal.append(200/X0 - Z)

arrayLimiteInferior = [n/(n*D + Z) for n in range(1,N+1)]
arrayLimiteSuperior = []

for n in range(1,N+1):
	arrayLimiteSuperior.append(min(n/(D + Z), 1/Dmax))

plt.plot(arrayN, arrayLimiteSuperior)
plt.plot(arrayN, arrayLimiteInferior)
plt.legend(['Limite Superior', 'Limite Inferior'], loc='lower right')
plt.show()
