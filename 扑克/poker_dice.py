
from random import randint

Poker = {'Ace':0,'King':1,'Queen':2,'Jack':3,'10':4,'9':5}

Pocker_order = {0:'Ace',1:'King',2:'Queen',3:'Jack',4:'10',5:'9'}
Count = {2:'second',3:'third',4:'fourth'}
Current_Pocker = []

def check(list_data):
    result = {'2': 0, '3': 0, '4': 0, '5': 0, '6': 0}

    temp  = 0

    d = list_data[0]


    for i in range(1,5):
        if d == list_data[i]:
            temp += 1
            d = list_data[i]
        else:
            if temp != 0:
                result[str(temp+1)] += 1
            d = list_data[i]
            temp = 0

    if temp != 0:
        result[str(temp + 1)] += 1

    return result

def deal(verbose,data_list):
    Current_Pocker.sort()

    if verbose == 0:
        print('The roll is: ', end='')
        for i in range(4):
            print(Pocker_order[Current_Pocker[i]], end=' ')

        print(Pocker_order[Current_Pocker[4]])
    #print(Current_Pocker)

    result = check(Current_Pocker)
    sum = 0
    for i in Current_Pocker:
        sum += i
    if verbose == 0:
        if result['5'] >= 1:
            print('It is a Five of a kind')
        elif result['4'] >= 1:
            print('It is a Four of a kind')
        elif result['3'] >= 1:
            if result['2'] == 1:
                print('It is a Full house')
            else:
                print('It is a Three of a kind')
        elif result['2'] == 2:
            print('It is a Two pair')
        elif result['2'] == 1:
            print('It is a One pair')

        elif sum == 10 or sum == 15:
            print('It is a Straight')
        else:
            print('It is a Bust')
    elif verbose == 1:
        if result['5'] >= 1:
            data_list['Five of a kind'] += 1
        elif result['4'] >= 1:
            data_list['Four of a kind'] += 1
        elif result['3'] >= 1:
            if result['2'] == 1:
                data_list['Full house'] += 1
            else:
                data_list['Three of a kind'] += 1
        elif result['2'] == 2:
            data_list['Two pair'] += 1
        elif result['2'] == 1:
            data_list['One pair'] += 1

        elif sum == 10 or sum == 15:
            data_list['Straight'] += 1
        else:
            data_list['Bust'] += 1

def play():
    global Current_Pocker
    global Count

    Current_Pocker  = []

    for i in range(5):
        temp = randint(0,5)
        Current_Pocker.append(temp)

    deal(0,[])

    temp_input = None

    counter = 2

    while True:

        cmd = input('Which dice do you want to keep for the %s roll? '%Count[counter])

        if cmd.strip().lower() == 'all':
            print('Ok, done.')
            break
        cmds = cmd.strip().split()


        temp_list = []

        for i in cmds:
            try:
                n = Poker[i.strip()]
            except:
                print('That is not possible, try again!')
                temp_list = []
                break

            temp_list.append(n)

        if temp_list == [] and cmds != []:
            continue

        Current_Pocker = []
        for i in range(len(temp_list)):
            Current_Pocker.append(temp_list[i])

        if len(Current_Pocker) == 5:
            print('Ok, done')
            break

        for i in range(5-len(Current_Pocker)):
            Current_Pocker.append(randint(0,5))

        Current_Pocker.sort()


        deal(0,[])

        if temp_input != None:
            if cmd.strip() == temp_input:
                break
        temp_input = cmd

        counter += 1



def simulate(num):
    global Current_Pocker

    result = {'Five of a kind':0,'Four of a kind':0,'Full house':0,'Straight':0,'Three of a kind':0,'Two pair':0,'One pair':0,'Bust':0}

    for j in range(num):

        Current_Pocker = []
        for i in range(5):
            temp = randint(0,5)
            Current_Pocker.append(temp)

        deal(1,result)


    print('Five of a kind : %.2f%%'  % (result['Five of a kind'] /  num*100.0))
    print('Four of a kind : %.2f%%' %  (result['Four of a kind'] / num * 100.0))
    print('Full house     : %.2f%%'  %   (result['Full house'] / num * 100.0))
    print('Straight       : %.2f%%' %  (result['Straight'] / num * 100.0))
    print('Three of a kind: %.2f%%' %  (result['Three of a kind'] / num * 100.0))
    print('Two pair       : %.2f%%' %  (result['Two pair'] / num * 100.0))
    print('One pair       : %.2f%%' %  (result['One pair'] / num * 100.0))
    #print(result)

'''
from random import seed
seed(0)

simulate(10)
simulate(100)
simulate(1000)
simulate(10000)
simulate(100000)


play()
'''