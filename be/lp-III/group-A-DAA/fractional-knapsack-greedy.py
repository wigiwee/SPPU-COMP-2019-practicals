class ItemValue:
    # Item Value DataClass
    def __init__(self, wt_, val_, ind_):
        self.wt = wt_
        self.val = val_
        self.ind = ind_
        self.cost = val_ / wt_

    def __lt__(self, other):
        return self.cost < other.cost


def fractionalKnapSack(wt, val, capacity):
    # Function to get maximum value
    iVal = [ItemValue(wt[i], val[i], i) for i in range(len(wt))]

    iVal.sort(reverse=True)  # sorting items by value/weight ratio

    totalValue = 0
    for i in iVal:
        curWt, curVal = i.wt, i.val
        if capacity - curWt >= 0:
            capacity -= curWt
            totalValue += curVal
        else:
            fraction = capacity / curWt
            totalValue += curVal * fraction
            capacity = int(capacity - (curWt * fraction))
            break
    return totalValue


if __name__ == "__main__":
    wt, val, capacity = [10, 40, 20, 30], [60, 40, 100, 120], 50

    maxValue = fractionalKnapSack(wt, val, capacity)
    print("Maximum value in Knapsack =", maxValue)
