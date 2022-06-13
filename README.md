# 음식 싹싹 Frigge 🥙
**_Mobile Programming Term Project TeamC_** <br><br>
![Logo](https://user-images.githubusercontent.com/96913056/173311744-a3eb35f7-8fc3-44ea-a5a5-6f82a70f83cd.png) <br>

![그림2](https://user-images.githubusercontent.com/96913056/173336820-1005f46d-1c1e-4cd2-9e74-560a2d1c9b6e.gif)

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
![1_기존냉장고 리스트](https://user-images.githubusercontent.com/96913056/173332748-d9ea1683-5b95-4a59-bc7a-d05c7f163be5.png)
<br><br>
* Add Refrigerator
![4_이름입력](https://user-images.githubusercontent.com/96913056/173332767-2780f142-f038-46a2-8688-63b1d2be0002.png) 
![5_추가된냉장고리스트](https://user-images.githubusercontent.com/96913056/173332780-6c44bea8-6ffd-428d-8fd5-c27304a88b08.png) 
<br><br>
* Delete Refrigerator
![6_아이템롱클릭하면삭제팝업](https://user-images.githubusercontent.com/96913056/173332791-e67ca63f-4f4d-4f4d-8d33-9ddcbd8db610.png)
<br><br>
* Two types
![1_냉장리스트](https://user-images.githubusercontent.com/96913056/173334032-34dcbfcc-f47b-4a95-9237-cc5307f695c3.png)
![2_냉동리스트](https://user-images.githubusercontent.com/96913056/173334039-2b3fe1f3-0da5-4c1d-a857-e491e9676157.png)
<br><br>
* Add product to refriegerator
![6_입력한 식품추가창](https://user-images.githubusercontent.com/96913056/173335941-38317d67-6408-467f-989b-92ab0346174e.png)
![5_식품추가_유통기한](https://user-images.githubusercontent.com/96913056/173335944-8bde65ff-b89c-4b09-9f0d-dfea01525f81.png)
![7_추가된 식품리스트](https://user-images.githubusercontent.com/96913056/173335952-f43660fe-36c7-46ff-abbb-60c766f18b16.png)
<br><br>
* Take Barcode and connect API
* Add product in Refrigerator list

### 2. Grocery Shopping
* Check list from the refrigerator
![1_장보기리스트화면](https://user-images.githubusercontent.com/96913056/173336492-516e4673-0333-4fac-bc5f-f10bd759798a.png)
<br><br>
* Add new product
![3_입력](https://user-images.githubusercontent.com/96913056/173336550-732264a4-8e47-453f-90ae-520114cdfbca.png)
![4_추가된 화면](https://user-images.githubusercontent.com/96913056/173336553-ab24b320-5973-4a7b-a2d1-823be1106508.png)
<br><br>
* Choose refrigerator to add product
![5_아이템클릭하면어느냉장고에추가할지뜸](https://user-images.githubusercontent.com/96913056/173336599-bd53581c-a7a9-46d4-a9da-10017bc328c0.png)
![6_냉장고를선택하면식품정보입력하는창이뜸(이름은자동으로넘어옴)](https://user-images.githubusercontent.com/96913056/173336604-9645cdf5-4daf-4fdc-80fe-080943fbda37.png)
<br><br>
* Delte, Add product in Grocery Shopping
![7_추가하면장보기리스트에서는 사라지고](https://user-images.githubusercontent.com/96913056/173336649-f3ad7dfb-b232-4ebd-8981-c5ed065ab939.png)
![8_선택한냉장고에 추가됨](https://user-images.githubusercontent.com/96913056/173336654-d53898a5-e280-44c9-a81e-a216a078d3f1.png)
<br><br>

### 3. Share
* Share the refrigerator with people
![3_친구uid입력](https://user-images.githubusercontent.com/96913056/173336183-8b88c285-f3e2-4786-8799-59e09e90c676.png)
![4_초대완료](https://user-images.githubusercontent.com/96913056/173336214-5a9d55ee-cd93-4303-a94a-f397d2d66844.png)
<br><br>

* Get your Uid
![2_본인uid복사가능](https://user-images.githubusercontent.com/96913056/173336206-fd8e3268-e8b2-47d9-bb3d-717f5a89d87a.png)

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

