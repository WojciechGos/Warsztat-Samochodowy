

# Overview

This is a fullstack application built using Spring Boot for the backend and React for the frontend. The application consists of three main components:

1. **Commission Management**: Handles the creation, updating, and tracking of commissions.
2. **Warehouse Parts Ordering**: Allows users to order parts directly from the warehouse.
3. **Wholesalers Parts Ordering**: Facilitates ordering parts from wholesalers.


## Table of Contents

- [Design Patterns Used](#design-patterns-used)
- [How to run](#how-to-run)
- [Security](#security)
- [Technologies Used](#technologies-used)
- [UML Diagram](#uml-diagram)
- [Screens from working app](#screens-from-working-app)


## How to run 


1. install mvn

2. Type in parent directory:

```bash
    docker compose up
```


## Design Patterns Used:

1. **Builder Pattern** - Utilized in the commission handling module for creating complex commission objects.
2. **Singleton Pattern** - Implemented in the warehouse module to ensure only one instance of the warehouse is available throughout the application.
3. **Mediator Pattern** - Used in the communication between different modules, particularly in the warehouse component for managing communication between different parts of the system.
4. **Bridge Pattern** - Applied in the invoice module to decouple the abstraction from its implementation.
5. **Adapter Pattern** - Implemented in the wholesalers module to adapt the interface of the wholesalers' systems to the application's interface.

## Security:

Endpoints within the system are secured against unauthorized access through an authentication mechanism, ensuring that only authenticated users can access the functionalities.

## Technologies Used:

- **Spring Boot 3**: Backend service designed in monolithic architecture. Written in Java 21. 
- **React**: For creating dynamic and interactive user interfaces.
- **RESTful APIs**: Used for communication between frontend and backend components.
- **JSON Web Tokens (JWT)**: Employed for secure authentication and authorization of API endpoints.


## UML Diagram

![diagram-14112519493894189060](https://github.com/WojciechGos/Car-Service/assets/36795978/971fb941-8df7-4b48-a23a-c3463ddbbd23)

## Screens from working app

### Login
Lets login as "Piotr Czerwiec" to the system
<img width="1269" alt="image" src="https://github.com/WojciechGos/Car-Service/assets/36795978/e9e23822-0743-4514-9e4a-6bd0d711e224">

### Client creator 
Lets simulate process of car repairing
<img width="1280" alt="image" src="https://github.com/WojciechGos/Car-Service/assets/36795978/6d640f86-4875-4d5b-974c-7f81c3388765">

### Client list page
Successfully added client
<img width="1280" alt="image" src="https://github.com/WojciechGos/Car-Service/assets/36795978/d4c34776-2f78-4403-8c0d-9b48e2f8f115">

### Vehicle creator
<img width="1273" alt="image" src="https://github.com/WojciechGos/Car-Service/assets/36795978/c29ceb2b-ede8-4e19-b537-ed4accb0c04e">

### Vehicle list page 
Successfully added vehicle
<img width="1280" alt="image" src="https://github.com/WojciechGos/Car-Service/assets/36795978/e4089631-c018-4368-af41-b0a3d800e36a">

### Commission creator
To add new commission we need to provide client id, vehicle id and worker id. Lets assign this job to Piotr Czerwiec.
<img width="1280" alt="image" src="https://github.com/WojciechGos/Car-Service/assets/36795978/fcdaafc7-e5ef-47a2-a9d3-d816f76cbea1">

### Commission
At the bottom we can see our new commission.
<img width="1280" alt="image" src="https://github.com/WojciechGos/Car-Service/assets/36795978/6a8e6a7c-2be2-4fd2-ab77-d635b012e50a">

### Commission details
Let's create a cost estimate so we can estimate the price of the service
<img width="1280" alt="image" src="https://github.com/WojciechGos/Car-Service/assets/36795978/9dfcf5b0-c141-4e81-8e79-be1b17110a17">

### Cost estimate
<img width="1248" alt="image" src="https://github.com/WojciechGos/Car-Service/assets/36795978/3c464d36-59ed-4806-bc57-f370344cbb72">



### Warehouse 
This is place where particular contractor can order spare parts for the commission

### Warehouse cart
This is place where are user's ordered products. When mechanic is sure he picked appriopriate spare parts then he can provide commission id and order it.
<img width="1238" alt="image" src="https://github.com/WojciechGos/Car-Service/assets/36795978/d692ae5c-8abd-46f1-8e57-e16acae2fd21">



### Warehouse internal orders list
After changing order status spare part is assigned to the commission invoice
<img width="1249" alt="image" src="https://github.com/WojciechGos/Car-Service/assets/36795978/54e57aa1-3548-4d10-bf71-ef1dc04e4a5d">










