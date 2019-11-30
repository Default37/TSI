from fcmeans import FCM
from sklearn.datasets import make_blobs
from matplotlib import pyplot as plt
from seaborn import scatterplot as scatter

n_samples = 3000 # decide o numero de pontos a serem gerados
n_bins = 3  # use n bins for calibration_curve as we have n clusters here
centers = [(-5, -5), (0, 0), (5, 5)] # define o centro dos clusters

# gera os pontos aleatórios
X,_ = make_blobs(n_samples=n_samples, n_features=2, cluster_std=1.0,
                  centers=centers, shuffle=False, random_state=42)

# fit the fuzzy-c-means
fcm = FCM(n_clusters=3) #define fcm como uma variável fuzzy c means com n clusters
fcm.fit(X) #executa o fit em fcm

# outputs
fcm_centers = fcm.centers #passa o centro de fcm para o plot
fcm_labels  = fcm.u.argmax(axis=1) #argmax retorna um vetor com os indices do elemento maximo no eixo y, fcm_labels será utilizado pro numero de cores para o plot


# plot result
#%matplotlib inline
f, axes = plt.subplots(1, 2, figsize=(11,5)) 
scatter(X[:,0], X[:,1], ax=axes[0]) #gera gráfico com os dados sem o fuzzy c means
scatter(X[:,0], X[:,1], ax=axes[1], hue=fcm_labels) #gera o gráfico com o fuzzy c means
scatter(fcm_centers[:,0], fcm_centers[:,1], ax=axes[1], marker="s", s=100) #scatter dos clusters
plt.show()