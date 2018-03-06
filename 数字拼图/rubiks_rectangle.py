from collections import deque

class Rectangle():
    def __init__(self):
        self.Q = deque()
        self.result = {'12345678':0}
        self.Q.append('12345678')

    def right(self,data):

        temp_data = list(data)
        temp1 = temp_data[3]
        temp2 = temp_data[4]
        temp_data.remove(temp1)
        temp_data.remove(temp2)

        temp_data.insert(0,temp1)
        temp_data.insert(7,temp2)

        return ''.join(temp_data)

    def middle(self,data):
        temp_data = list(data)

        temp1 = temp_data[2]

        temp_data[2] = temp_data[1]
        temp_data[1] = temp_data[6]
        temp_data[6] = temp_data[5]
        temp_data[5] = temp1

        return ''.join(temp_data)
    def row(self,data):
        temp_data = list(data)

        temp1 = temp_data[0]
        temp_data[0] = temp_data[7]
        temp_data[7] = temp1

        temp1 = temp_data[1]
        temp_data[1] = temp_data[6]
        temp_data[6] = temp1

        temp1 = temp_data[2]
        temp_data[2] = temp_data[5]
        temp_data[5] = temp1

        temp1 = temp_data[3]
        temp_data[3] = temp_data[4]
        temp_data[4] = temp1

        return ''.join(temp_data)


    def getTheMinNumOfSteps(self,data):
        #c = 0
        while True:
            #c += 1
            if len(self.Q)==0:
                break

            if data in self.result:
                #print(c)
                return self.result[data]

            next_data = self.Q.popleft()
            current_steps = self.result[next_data]

            new_data = self.middle(next_data)
            if new_data not  in self.result:
                self.result[new_data] = current_steps + 1
                self.Q.append(new_data)

            new_data = self.row(next_data)
            if new_data not in self.result:
                self.result[new_data] = current_steps+1
                self.Q.append(new_data)

            new_data=self.right(next_data)
            if new_data not  in self.result:
                self.result[new_data] = current_steps+1
                self.Q.append(new_data)


def Run():
    rec = Rectangle()
    cmd = input("Input final configuration: ")
    cmds = list(cmd)

    data = ''
    for i in cmds:
        if i != ' ':
            data += i

    if len(data)!=8:
        print("Incorrect configuration, giving up...")
    else:
        steps=rec.getTheMinNumOfSteps(data)
        print("%d step is needed to reach the final configuration." % steps) \
            if steps==0 or steps==1 \
            else print("%d steps are needed to reach the final configuration." % steps)

Run()