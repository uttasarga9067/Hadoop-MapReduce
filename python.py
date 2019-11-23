import os
from operator import itemgetter
path = '/home/singhud/output_directed'
new_file=open('/home/singhud/output_directed')
dict1={}
dict2={}

for line in new_file:
    words=line.split()
    
    if len(words)!=0:
    
        s=str(words[1])
        
        s2=words[0]
        
        my_list=s.split('#')
        
        s1=my_list[1]
        
        dict1.update({s2:int(s1)})
        dict2.update({my_list[2]:my_list[0]})

my_list1=sorted(dict1.items(), key=itemgetter(1))
print('Node with Minimum connectivity:',str(my_list1[0][0]),'and its value is',my_list1[0][1])
print('Node with Maximum Connectivity:',str(my_list1[-1][0]),'and its value is',my_list1[-1][1])

for k,v in dict2.items():
    if k == my_list1[-1][0]:
        print('Longest Adjacency List is :-',v ,'for Node', k)
