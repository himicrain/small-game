# Randomly fills a grid of size height and width whose values are input by the user,
# with nonnegative integers randomly generated up to an upper bound N also input the user,
# and computes, for each n <= N, the number of paths consisting of all integers from 1 up to n
# that cannot be extended to n+1.
# Outputs the number of such paths, when at least one exists.
#
# Written by *** and Eric Martin for COMP9021


from random import seed, randint
import sys
from collections import defaultdict


def display_grid():
    for i in range(len(grid)):
        print('   ', ' '.join(str(grid[i][j]) for j in range(len(grid[0]))))

def checkAround(X,Y,shouldNotBe):
    global grid
    global width
    global height

    up =    grid[X-1][Y]  if X-1 >= 0  else -1
    right = grid[X][Y+1]  if Y+1 < width  else -1
    down =  grid[X+1][Y]  if X+1 < height  else -1
    left =  grid[X][Y-1]  if Y-1 >= 0  else -1

    if (left == -1 or left != shouldNotBe) and (right == -1 or right != shouldNotBe) and ( down == -1 or down != shouldNotBe ) and (up == -1 or up != shouldNotBe):

        return True
    else:
        return False


def check(currentX,currentY,goal,shouldBe):

    global grid
    global width
    global height

    if currentX <0 or currentX >= height or currentY <0 or currentY >= width:
        return 0

    current = grid[currentX][currentY]

    if current != shouldBe :
        return 0
    if current == goal:
        if checkAround(currentX,currentY,goal+1):
            return 1
        else:
            return 0

    sum = 0
    #up
    sum += check(currentX-1,currentY,goal,shouldBe+1)
    #right
    sum += check(currentX,currentY+1,goal,shouldBe+1)
    #down
    sum += check(currentX+1, currentY,goal, shouldBe + 1)
    #left
    sum += check(currentX, currentY - 1,goal, shouldBe + 1)

    return sum


def get_paths():

    global grid
    global width
    global height
    global max_length

    paths = {}
    sum = 0

    for k in range(1,max_length+1):
        for i in range(height):
            for j in range(width):
                sum += check(i,j,k,1)

        if sum > 0 :
            paths[k] = sum
        sum = 0

    return paths

    # Replace pass above with your code

# Insert your code for other functions
    
try:
    for_seed, max_length, height, width = [int(i) for i in
                                                  input('Enter four nonnegative integers: ').split()
                                       ]
    if for_seed < 0 or max_length < 0 or height < 0 or width < 0:
        raise ValueError
except ValueError:
    print('Incorrect input, giving up.')
    sys.exit()




seed(for_seed)
grid = [[randint(0, max_length) for _ in range(width)] for _ in range(height)]
print('Here is the grid that has been generated:')
display_grid()
paths = get_paths()
if paths:
    for length in sorted(paths):
        #print(f'The number of paths from 1 to {length} is: {paths[length]}')
        print('The number of paths from 1 to',length,'is: ',paths[length])
