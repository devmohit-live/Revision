def modInverse(a: int, m: int) -> int:
    ''' There are many ways to do this it depends on number's properties
    prime, co-prime ,etc
    cryptography arithmetics
    https://www.geeksforgeeks.org/multiplicative-inverse-under-modulo-m/
    '''
    for i in range(m):
        if (a * i) % m == 1:
            return i
    return -1


t = int(input())
while(t > 0):
    t -= 1
    a, m = map(int, input().split())
    print(modInverse(a, m))
