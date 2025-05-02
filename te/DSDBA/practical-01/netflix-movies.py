import pandas as pd

df = pd.read_csv("./n_movies.csv")

print(df.head(20))

print(df.tail(20))

print(df.index)

print(df.columns)

print(df.shape)

print(df.dtypes)

print(df.columns.values)

print(df.describe(include='all'))

print(df['genre'])

print(df.sort_index(axis=1, ascending=False))

print(df.iloc[5])

print(df[0:3])

print(df.iloc[:, [0 ,1]])

print(df.iloc[:4, :])

print(df.iloc[:, :5])

print(df.iloc[:3, :5])