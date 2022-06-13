# 음식 싹싹 Frigge 🥙
**_Mobile Programming Term Project TeamC_** <br><br>
![Logo](https://user-images.githubusercontent.com/96913056/173311744-a3eb35f7-8fc3-44ea-a5a5-6f82a70f83cd.png) <br>

### Information
* Gachon University Software Department 201935046 박수빈 wlqkr001113@gmail.com <br>
* Gachon University Software Department 201935080 오채영 oco6029@naver.com <br>
* Gachon University Software Department 201935121 임혜균 hgyim00@naver.com <br>
* Gachon University Software Department 201935142 최  선 sun4194@gachon.ac.kr <br>

## Motivation
* It is said that the energy wasted by food waste is very high. <br>
* We found that the proportion of food thrown away after the expiration date was high among food waste, and my group thought it would be good to create a service that manages the expiration date of products in the refrigerator. <br>
* Through this service function, not only food waste can be reduced but also inefficient product consumption can be reduced through expiration date management.

## Our Goal
* **_Minimize food waste_** from home through refrigerator management. <br> 
* In particular, we would like to help **_a person living alone_** who have difficulty in managing food through the food sharing function.

## Key Features
### Adavantage
* Checking the **_expiration date_** and the product
* Can **_share_** your fridge with family
* **_Less_** left over food

### Difference
* **_Share food_** with people based on your fridge
* **_Easily_** make grocery market list

## Contents
### 1. Sign up & Login
* Sign up <br>
![Signup](https://user-images.githubusercontent.com/96913056/173331693-f06742e5-1bb9-43d5-994c-a1ae90e98bdb.png)
* Login (Automatic login functionality)<br>
![Login](https://user-images.githubusercontent.com/96913056/173331682-5f4406cb-cf5b-4296-8749-522b1f56c2dd.png)

### 2. Refrigerator
* Refrigerator list <br>
  * Origin Refrigerator list
![1_기존냉장고 리스트](https://user-images.githubusercontent.com/96913056/173332748-d9ea1683-5b95-4a59-bc7a-d05c7f163be5.png)
<br><br>
  * Add Refrigerator
![4_이름입력](https://user-images.githubusercontent.com/96913056/173332767-2780f142-f038-46a2-8688-63b1d2be0002.png) 
<br>
![5_추가된냉장고리스트](https://user-images.githubusercontent.com/96913056/173332780-6c44bea8-6ffd-428d-8fd5-c27304a88b08.png) 
<br><br>

  * Delete Refrigerator
![6_아이템롱클릭하면삭제팝업](https://user-images.githubusercontent.com/96913056/173332791-e67ca63f-4f4d-4f4d-8d33-9ddcbd8db610.png)
<br><br>

* Add Refrigerator
* Delete Refrigerator
* Tow types
* Add product to refriegerator
* Take Barcode and connect API
* Add product in Refrigerator list

### 2. Grocery Shopping
* Check list from the refrigerator
* Add new product
* Choose refrigerator to add product
* Delte, Add product in Grocery Shopping

### 3. Share
* Share the refrigerator with people
* Get your Uid

### 4. Community
* Product detail
* Search product in community
* Product upload
* Chatting

## Implemetation & Technologies
### Firebase
* Use **_Real Time Database_**
* Store user information, refrigerator information, food information, chat messages, etc. <br><br>
![Firebase](https://user-images.githubusercontent.com/96913056/173331198-2bf1bccb-d3d1-45d1-86f0-fcd86d7d592d.png)

### Use ZXing Library
* [journeyapps/zxing-android-embedded](https://github.com/journeyapps/zxing-android-embedded)<br><br>
* Code (build.gradle)<br><br>
![Zxing_gradle](https://user-images.githubusercontent.com/96913056/173329208-93d56f7c-c6ac-429c-89f7-6b449df2d767.png)

### Connect Barcode and API
* The barcode API was approved by applying for **_the barcode Open API in "식품안전나라"_**, and the food can be registered when the barcode is stamped.<br><br>
![API](https://user-images.githubusercontent.com/96913056/173330717-76b581a6-1699-405b-9da6-e8a0b2ab2e88.png)

### Github Repository
[MP_TermProject Team C](https://github.com/parksubin1313/MPTermProject)

## Frigge (PPT)
[Frigge.pptx](https://github.com/parksubin1313/MPTermProject/files/8889503/Frigge.pptx)

## Demo Video






