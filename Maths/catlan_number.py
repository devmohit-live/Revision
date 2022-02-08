''' Catlan Number 
This is a very important series => questions 1) No. of BST formed from n nodes
2) no. of Unlabelled Binary trees = same as np. of BST
3) NO. of Binary Trees =>(bst rule is removed , permutation is possible) => n! * no. of bst

no. of bst => nth catlan number => ( (2n)C(n) )/(n+1)      #direct method



Catlan Sereies is => sigma(i=1 to i=n)  [f(i-1)*f(n-i)]  => to be done in recursion or DP

f(i-1) => nodes forming bst on left subtrees taking "i" as the root node
f(n-i) => nodes forming bst on right subtrees taking "i" as the root node
f(N)=> sigma
f(0)=1 =>null tree
f(1)=1 => tree with 1 node
f(2)=2 => sum(f(0)f(1), f(1)f(0))

'''
# these comb and perm modules are present in python 3.8 interpreter only

# from math import perm
from math import comb  # in python 3.8

from math import factorial

# For various complex number use cmath =>  from cmath import
# doc:https://docs.python.org/3.6/library/cmath.html#module-cmath

# For stats use statistics => from statics import
# doc: https://docs.python.org/3.6/library/statistics.html

# For getting Fractions => numberator and Denominator use Fractions => from fractions import Fraction
# doc: https://docs.python.org/3.6/library/fractions.html


# # Python 3.6 or below
def calculate_combinations(n, r):
    return factorial(n) // factorial(r) // factorial(n-r)


t = int(input())
for _ in range(t):
    n = int(input('Enter the number of nodes: '))
    if n == 0:
        ct = 1
        p1 = 1
    else:
        # # Python 3.8
        ct = comb(2*n, n)//(n+1)
        # for python 3.6 or older
        ct1 = calculate_combinations(2*n, n) // (n+1)
        # p = perm(2*n, n+1)
        p1 = ct * factorial(n)

    print('Catlan number is :', ct)
    print("No. of BST's are :", ct)
    print("No. of Binary Trees are :", p1)
    print("No. of Unlabbeled Binary Trees are :", ct)
