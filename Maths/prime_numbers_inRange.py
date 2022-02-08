import math


def primeNumer(n) -> list:
    ''' Sieve Of Erathosthenes Algorithm to find numers within the range are prime or not, here we just jump to the next multiples of the current number
    in an array and make it False, we explicitly make the 0 and 1 False, this alogorithm help us to solve this O(N) task  in 
    O(n^1/2) '''
    # i = 2  # in python we have to first initialized the loop variables manually if we want to use them in loopupdate
    # j = i*2
    primeArray = [True]*(n+1)  # 0 to n
    primeArray[0], primeArray[1] = False, False
    # for i in range(2, n+1, i*i):  # until i*i <=n
    for i in range(2, math.floor(math.sqrt(n+1))):
        for j in range(2*i, n+1, i):
            primeArray[j] = False  # make the nth multiple False
    return primeArray


t = int(input())
for _ in range(t):
    n = int(input())
    for i, j in enumerate(primeNumer(n)):
        print("{} : {}".format(i, j))
    # print(primeNumer(n))
