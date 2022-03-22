import math
import random
import time

class Point:
    def __init__(self, x, y):
        self.x = x
        self.y = y
    
    def distance(self, point):
        return math.sqrt((point.x - self.x)**2 + (point.y - self.y)**2)

    def __str__(self):
        return f'({self.x},{self.y})'

points = []
for i in range(0, 10000):
    points.append(Point(random.randint(1, 100), random.randint(1, 100)))

start = time.time_ns()

smallestDistance = points[0].distance(points[1])
for x in range(0, len(points)):
    for y in range(1, len(points)):
        if (points[x].distance(points[y]) < smallestDistance):
            smallestDistance = points[x].distance(points[y])

end = time.time_ns()

print((end - start)/1000000)