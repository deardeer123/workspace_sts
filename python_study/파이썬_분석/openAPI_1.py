import pandas as pd
import requests
import bs4

#  api로 데이터 불러오기
url = 'http://apis.data.go.kr/3210000/SeochoIaqSvc/getSeochoIaqRtData'
params ={
    'serviceKey' : 'qRoXflrfBsgsvMowjtk5JxPXmX78OsOAuczZ0Qmuw74bzNxxJpD8XL1LMAANOMOBn0BASMfklbM8mbZFxCFeyQ==',
    'numOfRows' : '10',
    'pageNo' : '1' }

response = requests.get(url, params=params)

# 가져온 데이터(문자열)를 beautifulSoap 형식으로 만들어 주기
bs_data = bs4.BeautifulSoup(response.text, 'lxml-xml')
items = bs_data.find_all('item')


dataTimeList = []
for item in items :
    print(item.find('dataTime'))
    dataTimeList.append(item.find('dataTime').text)
    print(type(item))

#items 안의 내용을 통해 딕셔너리 데이터를 구성

dic_data = {}
dic_data['dataTime'] = dataTimeList

df = pd.DataFrame(dic_data)
df.to_excel("api_test.xlsx")
print(df)

