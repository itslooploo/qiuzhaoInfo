# f = open('keys', mode='r', encoding='utf-8')
# strs = f.readlines()
# f.close()
# strsList = []
# for s in strs:
#     strs2 = s.split('-')
#     strsList.append(strs2[0].replace(' ', '') + ' STRING COMMIT \'' + strs2[1].strip(' ').replace('\n', '') + '\',')
# f = open('keys', mode='w', encoding='utf-8')
# for s in strsList:
#     f.write(s + '\n')
# f.close()
#
#
f = open('OrderData.txt', 'r', encoding='utf-8')
strs = f.readlines()
f.close()
strsList = []
indexList = [0, -1, -2, -3, -4]
for s in strs:
    strs2 = s.split(',')
    for i in indexList:
        strs2[i] = strs2[i].replace('"', '')
    str1 = ','.join(strs2)
    strsList.append(str1)
f = open('orderdata2.txt', 'w', encoding='utf-8')
for s in strsList:
    f.write(s)
f.close()

