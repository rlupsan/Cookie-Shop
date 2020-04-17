# Cookie Shop Vision

# Introduction
<!--The purpose of this document is to collect, analyze, and define high-level needs and features of the Cookie Shop. It focuses on the capabilities needed by the stakeholders and the target users, and why these needs exist. The details of how the Cookie Shop fulfills these needs are detailed in the use-case and supplementary specifications.-->
<!--The introduction of the Vision document provides an overview of the entire document. It includes the purpose, scope, definitions, acronyms, abbreviations, references, and overview of this Vision document.-->

We all love cookies! This project is meant to implement a Cookie Shop System, which will alow people from Cluj-Napoca to order their favourite cookies from home, online. This will save a lot of time for the customers. Moreover, they will be able to view all types of cookies, the ratings and also to rate them. Everything from one place. </br>
</br>
The details of how the Cookie Shop fulfills these needs are detailed in the use-case and supplementary specification. Furthermore, this document includes the purpose, scope, definisions, acronyms, abbrevitions, references and also an overview.

## Purpose
The purpose of the Cookie Shop is to provide the easiest way for the people who love cookies to buy, view and rate cookies online. They will also get a live notification when their favourite cookies are back on stock. Moreover, the administrator of the Cookie Online Shop will be able to maintain the information on cookies and on the customers.  

## Scope
The scope of this project is to implement the following features:
- User Authentification - create account and login (for both a regular user and the administrator)
- The regular user will be able to:
   - search the cookies by the following properties: type, price, quantity of sweeteners and rating
   - add multiple cookies in multiple quantities in the cart at the time of buying (also to buy them)
   - add or remove any cookies from their favourite list
   - rate the cookies (1-5 stars) only once and also to comment their opinion about the specific cookies
   - get a live notification when their favourite cookie is back on stock
   - see others' ratings on cookies and thumbs up/down whether they think the comments from the rating are useful or not (visible for anyone)
- The administrator will be able to:
   - CRUD on cookies
   - CRUD on regular users' information
   - generate two types of report files - one in pdf format and one in txt format, with the cookies bought in the last X days ( he can select the days). The reports will be in a user-selected location.
   - edit the users' ratings
   - update the stock of the cookies </br> 
   
And also for me to learn how to use:
- the Mockito framework
- the MVC architectural pattern
- the Factory Method design pattern

## Definitions, Acronyms, and Abbreviations
Throughout this and all related papers the following terms will be defined and understood by the reader as follows:

- CRUD = create, read, update, delete functions
- Mockito = open source testing framework for Java, which allows the creation of tests with a clean and simple API.
- API = application programming interface
- MVC = Model-View-Controller
- Regular user = the person who wants to buy cookies, the customer
- Administrator = the person who manages the shop
- Cookie = any kind of sweet product

## References
For further clarifications see the following resources:
* project_supplementary_specification.md
* project_analysis_and_design.md

## Overview
The upcoming sections of the document will describe the product positioning in the marketplace, in contrast to other cookie shops. The involved skateholders, end users, end user environment, and the product hardware and software requirements will be described too.

# Positioning
## Problem Statement

|||
|----|------- |
| **The problem of** | staying too much in line if customers want to buy or to view cookies, and none of the cookies has ratings
| **affects**  | the people who want to buy cookies
| **the impact of which is** | the time, because staying in a long queue takes a lot of time
| **a successful solution would be** | to create a Cookie Shop Online - customers won't stay in line for ordering cookies anymore and also they will be able to rate the cookies (this will help other customers too in making their choice)

## Product Position Statement

|||
|----|------- |
| **For** | People from Cluj-Napoca
| **Who** | want to buy cookies
| **The** | Cookie Shop is a Desktop based online shop
| **That** | provides the easiest way to buy, view and rate the cookies online
| **Unlike** | the cookie shops that do not have an online shop or which does not have a rating part
| **Our product** | can be easily bought from the online Cookie Shop in multiple types and multiple quantities, and also the customers can leave their own ratings on the cookies

# Stakeholder and User Descriptions
<!--To effectively provide products and services that meet your stakeholders’ and users' real needs, it is necessary to identify and involve all of the stakeholders as part of the Requirements Modeling process. You must also identify the users of the system and ensure that the stakeholder community adequately represents them. This section provides a profile of the stakeholders and users involved in the project, and the key problems that they perceive to be addressed by the proposed solution. It does not describe their specific requests or requirements as these are captured in a separate stakeholder requests artifact. Instead, it provides the background and justification for why the requirements are needed.-->

## Stakeholder Summary
<!--There are a number of stakeholders with an interest in the development and not all of them are end users. Present a summary list of these non-user stakeholders. (The users are summarized in section 3.2.)-->

### Stakeholder 1 - Customer
* **Name**: Customer
* **Description**: The person who wants to buy cookies from the cookie shop.
* **Responsibilities**: 
   * pay their own order
   * be at the specified office when the deliver person comes with their order
   * update their own contact information in case of legally changing
   * view his own history of orders
   * rate the cookies
   * order cookies
   * is notified when their favourite cookies are back on stock
   
### Stakeholder 2 - Deliver Person
* **Name**: Deliver person
* **Description**: The person who deliver the ordered cookies to customers' houses.
* **Responsibilities**: 
   * get the order from the applcation
   * deliver the order to the customer's house
   * ensures that the cookies are in the same form as at the time when they were in the real shop
  
  ### Stakeholder 3 - System Administrator
* **Name**: Administrator
* **Description**: The person who maintain the application after the implementation is done
* **Responsibilities**: 
   * ensures the cookies are on stock
   * manage the ratings in order to not make a bad impression
   * perform CRUD tasks on both cookies and customers' information
   * ensures that the system will be maintainable
   * Provides valid data to the system database
    
### Stakeholder 4 - Manager
* **Name**: Manager
* **Description**: 
* **Responsibilities**: The person who manages the entire Cookie shop.
   * ensures that the customers are happy with the delivarables
   * ensures that the baker has enough ingredients
   * coordinates the work
   * ensures that will be a market demand for the product's features
   
### Stakeholder 5 - Developer
* **Name**: Developer
* **Description**: Is the person who implement the application - me. 
* **Responsibilities**: 
   * provide a clean implementation of the application
   

## User Summary

### User 1 - Regular User
* **Name**: regular user
* **Description**: They are the customers that view, rate and order cookies
* **Responsibilities**: <!--List the user’s key responsibilities with regard to the system being developed; for example:-->
   <!-- * captures details
    * produces reports
    * coordinates work
    * and so on -->
    * search the cookies by the following properties: type, price, quantity of sweeteners and rating
    * add multiple cookies in multiple quantities in the cart at the time of buying (also to buy them)
    * add or remove any cookies from their favourite list
    * rate the cookies (1-5 stars) only once and also to comment their opinion about the specific cookies
    * get a live notification when their favourite cookie is back on stock
    * see others' ratings on cookies and thumbs up/down whether they think the comments from the rating are useful or not (visible for anyone
* **Stakeholder**: They are are direct users.

### User 2 - Administrator
* **Name**: administrator
* **Description**: They are the managers that perform tasks on the stock, and edit the information within the application.
* **Responsibilities**:
    * CRUD on cookies
    * CRUD on regular users' information
    * generate two types of report files - one in pdf format and one in txt format, with the cookies bought in the last X days ( he can select the days). The reports will be in a user-selected location.
   * edit the users' ratings
   * update the stock of the cookies  
* **Stakeholder**: They are are direct users.

## User Environment
<!--[Detail the working environment of the target user. Here are some suggestions:
Number of people involved in completing the task? Is this changing?
How long is a task cycle? Amount of time spent in each activity? Is this changing?
Any unique environmental constraints: mobile, outdoors, in-flight, and so on?
Which systems platforms are in use today? Future platforms?
What other applications are in use? Does your application need to integrate with them?]-->

**Customer**</br>
The customer is considered to view, rate the cookies and place their order online from their home in an non-stressful and calm environment. They can also be accompanied by their friends or family in order to make the right choice. The task may vary depending on how the customer is decided in choosing their favourite cookies. It may not exceed half an hour. The shortest time should be around 2 minutes for each of the activities: view, rate and order the cookies. The most favourable environment constraint should be indoor, inside of a building with connectivity to electricity, because the application is on a laptop or on a computer, being a Desktop based application. A future platform could be on web, but for now the application will only be on Desktop. The application does need only a connection to the internet. Inside of the application, a payment system is already build. 

**Administrator**</br>
The administrator is considered to work from an office. They can be intrerrupted by other people. The tasks does not vary that much, because their tasks are more technical. It may not succeed 1 minute each task. The shortest time should be around 30 seconds for each part of the CRUD activity. The most favourable environment constraint should be indoor, inside of a building with connectivity to electricity, because the application is on a laptop or on a computer, being a Desktop based application. A future platform could be on web, but for now the application will only be on Desktop. The application does need only a connection to the internet. The application does not integrate with any other.


# Product Requirements
For both the regular users and the administrator, the Cookie Shop application requires a stable internet connection, a computer or a laptop, which have at least 8 GB RAM and a Dual Core processor. In the case of the computer, a monitor, a mouse and a keyboard are needed too. The environment should be indoor, inside of a building, with connection to electricity. It is favorably not to be used outdoor. </br> 
The Cookie Shop application must be integrated with at least one online payment system used by the customers. Optional, it can be also integrated with the real factory/shop.


# Bibliography

- [Markdown online editor](http://dillinger.io/)
- [Markdown Tutorial](https://www.markdowntutorial.com )
- [Mastering Markdown](https://guides.github.com/features/mastering-markdown/)
