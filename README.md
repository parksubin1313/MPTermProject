# 음식 싹싹 Frigge 🥙
**_Mobile Programming Term Project TeamC_** <br><br>
![Logo](https://user-images.githubusercontent.com/96913056/173311744-a3eb35f7-8fc3-44ea-a5a5-6f82a70f83cd.png) <br>

### Information
* Gachon University Software Department 201935046 박수빈 wlqkr001113@gmail.com <br>
* Gachon University Software Department 201935080 오채영 oco6029@naver.com <br>
* Gachon University Software Department 201935121 임혜균 hgyim00@naver.com <br>
* Gachon University Software Department 201935142 최  선 sun4194@gachon.ac.kr <br>

## Motivation
It is said that the energy wasted by food waste is very high. <br>
We found that the proportion of food thrown away after the expiration date was high among food waste, and my group thought it would be good to create a service that manages the expiration date of products in the refrigerator. <br>
Through this service function, not only food waste can be reduced but also inefficient product consumption can be reduced through expiration date management.

## Our Goal
**_Minimize food waste_** from home through refrigerator management. <br> 
In particular, we would like to help **_a person living alone_** who have difficulty in managing food through the food sharing function.

## Key Features
### Adavantage
* Checking the **_expiration date_** and the product
* Can **_share_** your fridge with family
* **_Less_** left over food

### Difference
* **_Share food_** with people based on your fridge
* **_Easily_** make grocery market list

## Contents
### 1. Refrigerator

### 2. Grocery Shopping

### 3. Share

### 4. Community

## Implemetation & Technologies

## Demo Video



* Functions

1. The Bad Memory Eraser (나쁜 기억 지우개)
2. Diary (personal / shared)
3. Chatbot

***
# The Bad Memory Eraser (나쁜 기억 지우개)
<br><br>
![KakaoTalk_20211214_182451437](https://user-images.githubusercontent.com/94924853/145970540-31c77f50-1800-4c21-ab9f-49ac483f2ac8.jpg)<br><br><br>

**The Bad Memory Eraser** makes the user look at the situation objectively.
If Users send their bad memories, It displays a random Good message. And Users are notified by push message at the day user set and have two choices. When the user selects ‘보관하기’, it is stored in the archive. 

* Background

**Bad memory eraser** is a function based on **'자신과 거리두기'**, a psychological technique that allows users to objectively look at situations they are worried about. Distancing yourself is a technique that reduces anxiety and stress by objectively looking at the situation that has plagued you from a third party's point of view.

* Implementation

   * Firebase : server & database
   * Web crawling : 'Naver wise saying' crawling and convert dataframe to the excel file.

***
# Diary
![image](https://user-images.githubusercontent.com/94924853/145975927-e1431716-aae3-4425-96d4-0f7b290e0c8e.png)
**Diary** helps users improve their self-esteem to get out of depression and find ways to solve their problems.
The diary will be divided into a **personal diary** and a **shared diary**. A personal diary is a place to record your day, and in the case of a shared diary, the host creates a shared diary of the topic he wants. In that way, users can participate in a shared diary with the same concerns as themselves through search and share their concerns with others.

* Background

In a study related to actual self-esteem, as a result of writing a diary every day, self-esteem and psychological well-being increased. In order to maximize this effect, you need to be able to write steadily. And it turns out that the best thing for it is the diary. As such, we are seeing the expected effect of sharing concerns with people with similar concerns, deviating from comparison with others, solving concerns, and enhancing users' self-esteem through self-expression writing.<br>
(*Reference : https://www.kci.go.kr/kciportal/ci/sereArticleSearch/ciSereArtiView.kci?sereArticleSearchBean.artiId=ART002501144)

* Implementation

We have built a web server. The web server created APIs made of FLASK in EC2 through DOCKER files, created DOCKER images, used them as containers, distributed them, and made HTTP requests in Android studios to communicate with the server.  MySQL of AWS RDS was used as a database.<br>
(*Our Server github repository : https://github.com/jaehyuck521/STW-server/wiki)

***
# Chatbot
![챗봇 위키](https://user-images.githubusercontent.com/94924853/170637334-d7082526-1266-47e6-badf-53932b5b1b74.png)<br>
**Chatbot** provide the user with someone to share their concerns with.


* Background

We have prepared a chatbot function for those who do not have an acquaintance to share their concerns with, and those who are reluctant to share their concerns with them. According to a psychiatrist, there are three principles for consolation. The chatbot utterance part of the dataset we used includes them , so we expect the effect of sympathizing with and comforting the user's words appropriately for the situation.<br>
(*Reference : http://www.psychiatricnews.net/news/articleView.html?idxno=9111)

* Implementation

We let the chatbot model learned based on Pytorch and transformer saved the model. We uploaded this model to the ec2 server and link Android studios. <br>
(*Our chatbot github repository : https://github.com/jaehyuck521/STW-ChatbotModel/wiki)

***

# Installation

It can be installed through the Google Play Store.

***
# About Share The Worry (PPT)

[About_ShareTheWorry.pptx](https://github.com/parksubin1313/STW/files/8784262/About_ShareTheWorry.pptx)

***
# Project Video

https://www.youtube.com/watch?v=-BhmJwwwsHk

***
# 개인정보 처리 방침

[개인정보처리방침.txt](https://github.com/parksubin1313/STW/files/8784279/default.txt)

***
# Member Information

- 장재혁 jjwjjw215@gmail.com
- 박수빈 wlqkr23@gachon.ac.kr
- 백현정 hmuy913@gachon.ac.kr
