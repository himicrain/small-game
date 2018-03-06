


class DIE():
    def __init__(self):
        self.top = 3
        self.front = 2
        self.right = 1

    def toRight(self):
        temp = self.top
        self.top = 7-self.right
        self.right = temp

    def toLeft(self):
        temp = self.top
        self.top = self.right
        self.right = 7-temp

    def toForwards(self):
        temp = self.top
        self.top = 7-self.front
        self.front = temp

    def toBackwards(self):
        temp = self.top
        self.top = self.front
        self.front = 7-temp

    def display(self):
        print(self.top,'is at the top,',self.front,'at the front, and',self.right,'on the right.')



class main():
    def __init__(self,step,DIE_t):
        self.step = step-1
        self.STEP = step
        self.DIE_t = DIE_t


    def toRightBy(self,times):
        temp = times%4

        for i in range(temp):
            self.DIE_t.toRight()

    def toLeftBy(self,times):
        temp = times % 4

        for i in range(temp):
            self.DIE_t.toLeft()

    def toForwardsBy(self,times):
        temp = times % 4

        for i in range(temp):
            self.DIE_t.toForwards()

    def toBackwardsBy(self,times):
        temp = times%4

        for i in range(temp):
            self.DIE_t.toBackwards()

    def display(self):
        print('On cell',self.STEP,end=', ')
        self.DIE_t.display()


    def run(self):

        circle = 1

        while True:
            if self.step-1 >= 8*circle-2:
                self.toRightBy(2*circle-1)
                self.toForwardsBy(2*circle-1)
                self.toLeftBy(2*circle)
                self.toBackwardsBy(2*circle)
                self.step = self.step - (8*circle-2)
                circle += 1
            else:
                if self.step > circle*2-1:
                    self.toRightBy(2*circle-1)
                    self.step -= 2*circle-1
                else:
                    self.toRightBy(self.step)
                    break

                if self.step > circle*2-1:
                    self.toForwardsBy(2*circle-1)
                    self.step -= 2*circle-1
                else:
                    self.toForwardsBy(self.step)
                    break

                if self.step > circle*2:
                    self.toLeftBy(2*circle)
                    self.step -= 2*circle
                else:
                    self.toLeftBy(self.step)
                    break


                if self.step > circle*2:
                    self.toBackwardsBy(2*circle)
                    self.step -= 2*circle
                else:
                    self.toBackwardsBy(self.step)
                    break
        self.display()





def Run():

    while True:
        try:
            data = input('Enter the desired goal cell number: ')
            data = int(data)
        except:
            print('Incorrect value, try again')
            continue

        if data <= 0 :
            print('Incorrect value, try again')
            continue


        die = DIE()
        m = main(data,die)

        m.run()

        break

Run()
