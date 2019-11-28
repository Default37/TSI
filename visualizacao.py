import pandas as ps
import matplotlib.pyplot as plt


dados = ps.read_csv('pontos2.csv')
dados = dados.values

print(dados)

plt.plot(dados[:,0], dados[:,1], 'ro')
plt.show()