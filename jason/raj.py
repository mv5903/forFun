def negate_last(n, values):
    if len(values) == 0:
        return []
    if n not in values:
        return values
    else:
        if (values[-1] == n):
            return values[:-1] + [values[-1] * -1]
        else:
            return negate_last(n, values[:-1]) + [values[-1]]

print(negate_last(2, [1, 3, 5, 7, 9])  )