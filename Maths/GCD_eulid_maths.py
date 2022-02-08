def gcd(a, b) -> int:
    if a % b == 0:
        return b
    return gcd(b, a % b)
# other implemetation of lcm:

def lcm(a,b):
    temp=max(a,b)
    while(temp % a != 0 or temp % b != 0):
        temp=temp+1
    return temp

print(lcm(5,15))

t = int(input())
for _ in range(t):
    #   n=int(input())
    a, b = map(int, input("Input two space seperated integers: ").split())
#   l=list(map(int,input().split()))
    print(gcd(a, b))

'''
Important properties of LCM and HCF:
For two numbers say, 'a' and 'b', LCM x HCF = a x b.
HCF of co-primes = 1.
For two fractions,
HCF = HCF (Numerators) / LCM (Denominators)
LCM = LCM (Numerators) / HCF (Denominators)

'''
