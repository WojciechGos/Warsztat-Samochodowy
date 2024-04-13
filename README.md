

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
Lets simulate process of car repairing for client Wojciech Gos
![image](https://github.com/WojciechGos/Car-Service/assets/36795978/9a385be0-a191-46c8-ab57-02ae7e4ef4c8)


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

### Cost estimate creator
![image](https://github.com/WojciechGos/Car-Service/assets/36795978/65cff492-f20f-4c30-b032-3b0ccb37548a)

lets assume labor cost will be 400 zł.
![image](https://github.com/WojciechGos/Car-Service/assets/36795978/94d980fe-7a56-4730-adb2-1628727b47ca)
When we again come in to the cost estimate it will show the previous one.
![image](https://github.com/WojciechGos/Car-Service/assets/36795978/a57cfb20-276e-4109-8ea2-1bb914aa6c51)

### Warehouse 
This is place where particular contractor can order spare parts for the commission
![image](https://github.com/WojciechGos/Car-Service/assets/36795978/8f4ccba2-b76f-4dbc-b9ff-a8ec59a35e4a)

### Warehouse cart
This is place where are user's ordered products. When mechanic is sure he picked appriopriate spare parts then he can provide commission id and order it.
![image](https://github.com/WojciechGos/Car-Service/assets/36795978/17336270-3737-466e-b17f-157a8549eee5)

### Warehouse internal orders list
After changing order status spare part is assigned to the commission invoice
![image](https://github.com/WojciechGos/Car-Service/assets/36795978/cc540bfe-8b47-45a4-a277-9cbb25b2e0de)

### Total cost (data for invoice)
Look in the commission cost there is ordered spare part from warehouse.
![image](https://github.com/WojciechGos/Car-Service/assets/36795978/cdcb9506-d22a-4cf2-89d6-374b0d29fc50)

### Wholesaler
We can also order products from external warehouses.
![image](https://github.com/WojciechGos/Car-Service/assets/36795978/c571e045-7bcb-4e15-8b8f-5feb9ab30aa1)

Lets order "Filtr oleju"
![image](https://github.com/WojciechGos/Car-Service/assets/36795978/4b09a566-8bc9-4b1e-a13c-30d2a9e5966d)

And accept transaction
![image](https://github.com/WojciechGos/Car-Service/assets/36795978/5c4c7dc7-8e9f-4001-9f9c-dc92c2f1bf17)

In the orders we can track ordered products. After spare part receive the warehouseman or other manager can change order status to COMPLETED
![image](https://github.com/WojciechGos/Car-Service/assets/36795978/e0c67080-bead-42f2-935c-017d711eed16)

Look there is our ordered spare part is in the workshop's warehouse 
![image](https://github.com/WojciechGos/Car-Service/assets/36795978/b7bfe60f-fc84-47dc-87ee-6be0b197b100)

### Invoice
On the bottom showed 2 new buttons, because we have products attached to the commission and we assume that car service is on the finish.
![image](https://github.com/WojciechGos/Car-Service/assets/36795978/9c5ae42a-25be-4d7d-a5d1-fe68814af21e)

We can generate invoice in the pdf or take a look in the html.
![image](https://github.com/WojciechGos/Car-Service/assets/36795978/7058e3e3-df67-4981-a781-5eea82afde4f)

![image](https://github.com/WojciechGos/Car-Service/assets/36795978/44b730b9-8c0c-47af-98e4-de68474bd0f3)

We can finally finish commission.
![image](https://github.com/WojciechGos/Car-Service/assets/36795978/ea9a1694-57c2-457e-ae57-3c5fcc078d6e)

![image](https://github.com/WojciechGos/Car-Service/assets/36795978/4aa1a131-5551-4932-a631-dd9ab7616b22)

