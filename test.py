import sys
import time

def backspace(times):
    for i in range(times):
        sys.stdout.write('\010')

print('Hi', end="\r")
time.sleep(1)
backspace(2)
print('Bye')
