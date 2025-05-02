import seaborn as sns

dataset = sns.load_dataset('iris')
dataset.head()

import matplotlib.pyplot as plt 
#fig, axes = plt.subplots(2,2, figsize = (16,9))
sns.histplot(dataset['sepal_length'])
sns.histplot(dataset['sepal_width'])
sns.histplot(dataset['petal_length'])
sns.histplot(dataset['petal_width'])

import matplotlib.pyplot as plt
#fig, axes = plt.subplots(2,2, figsize = (16,9))
sns.boxplot(y='petal_length',x='species', data = dataset)
sns.boxplot(y='petal_width',x='species', data = dataset)
sns.boxplot(y='sepal_length',x='species', data = dataset)
sns.boxplot(y='sepal_width',x='species', data = dataset)