import pandas as pd

df = pd.read_csv("evSales.csv")

df

# mean

df.Battery_Capacity_kWh.mean()
df.loc[:, 'Battery_Capacity_kWh'].mean()
df.Battery_Capacity_kWh[0:4].mean()

# median

df.Battery_Capacity_kWh.median()
df.loc[:, 'Battery_Capacity_kWh'].median()
df.Battery_Capacity_kWh[0:4].median()

# mode

df.mode()
df.loc[:, 'Battery_Capacity_kWh'].mode()

# minimum
df.Battery_Capacity_kWh.min(skipna=False)
df.loc[:,'Battery_Capacity_kWh'].min(skipna=False)

# maximum
df.max()
df.loc[:, 'Battery_Capacity_kWh'].max(skipna=True)

# Standard deviation
df.Battery_Capacity_kWh.std()
df.loc[:,'Battery_Capacity_kWh'].std()