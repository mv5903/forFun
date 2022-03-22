def sumfold(list):
    sum = 0;
    newList = [];
    for i in range(0, len(list)):
        sum+=list[i]
        newList.append(sum)

    return newList


def zerocols(table):
    zeroCols = []
    for i in range(len(table)):
        foundNotZero = False
        for num in table[i]:
            if (num != 0):
                foundNotZero = True
        #If there wasn't a time where code found a nonzero
        if not foundNotZero:
            zeroCols.append(i)
    
    return zeroCols

def prefix(a):
    if len(a) == 0:
        return 0
    if len(a) == 1:
        


#print(sumfold([0, 1, 2, 3, 4]))

#print(zerocols([[0, 1, 0], [0, 0, 0], [0, 0, 0]]))