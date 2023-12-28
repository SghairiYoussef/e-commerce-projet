# E-Commerce Console Application

## Overview
This Java-based console application serves as an e-commerce system that I named 'Virtu-Cart' providing functionalities for users to manage products, shopping carts, and orders. It incorporates object-oriented programming principles to facilitate user authentication, product management, and order processing.

## Features

### User Authentication (AuthentificationService Class)
- Implements login/logout functionality for Admins and Clients.
- Manages user roles and access levels.

### ProductManagement
- Defines a Product Interface implemented by specific product categories (Clothing, Electronics).
- Supports CRUD operations for product management, all accessed by admins.

### Shopping Cart
- Allows users to manage items in their cart (addition, removal).
- Facilitates order completion from the cart.

### Order Processing
- Tracks user transactions through an Order class.
- Manages the transition from cart to completed orders.
- Completed Orders are saved as Transactions for the client to keep track of his recent orders.

### Inventory Management
- Partially manages inventory through product quantity updates. Implemented in the ProductManagement Class. (A list of Products)

### Dynamic Product Search and Filtering
- Enables filtering of products by type (clothing/electronics).
- Enables filtering of products by name (partial or total ressemblance).
- Enables displaying all Products at once.

### Payment Processing
- Includes sections in the Order class for potential payment simulation, while a client has a wallet (account attribute) that he can recharge and check. 
- When completing an Order, a client has to enter the exact amount or higher to pay, as a way to verify and confirm the transaction, if a higher value is entered, the client will receive change.
All clients start with $10 as a gift from admins.

## Usage
- To use the application, compile and run the Main class.
- Follow the prompts for login, product management, cart functionalities, etc.
- To ease Testing, some sample products have been added like the ASUS LAPTOP and the HA SWEATER that you can display once the program runs. Added to that, an admin was added having the username 'admin' and the password 'admin', a client sample was also added with the username 'client' and the password '123'. While products and users can also be added dynamically through signing up.
- A user friendly 'interface' was implemented in the 'ConsoleApp.java' file (ConsoleApp Class) that implements a menu that guides the user throughout the whole experience.

## Optional Features

### Product Reviews and Ratings.
- A client can review a product he recently bought for other users to see, along with a rating. When adding a review, The system verifies whether the client in question has actually bought the product, if not an error message is printed.

### Return Requests.
- If a client is not satisfied with a certain product. He has the option to request a return, which means he sends a request to admins, containing the reason for the request itself. An admin has then to verify the available requests and decide whether to accept or refuse the client's demand.
In case of approval, the client receives a full refund of the product in question.
- A client 'inbox' System was implemented, in which a client can check received messages sent by admins on whether a certain request is approved or denied.

## Implementation Details
- The application is built using Java and follows an object-oriented design. 
- Classes are organized hierarchically to represent users, products, carts, and orders.
- Inheritance and composition principles are applied for code reusability and modularity.

## Future Enhancements
- Potential areas for improvement include:
  - Adding the Products suggestions based on the client's history.
  - Enhancing code's readability.

## Contributing
- This project is for educational purposes and does not currently accept contributions.

## Authors
- Youssef Sghairi

## Acknowledgments
- ChatGPT, an AI language model developed by OpenAI, provided guidance and assistance throughout the development process.
- When problems were encountered, StackOverflow, a platform for programming Q&A, provided valuable insights and solutions that assisted in overcoming specific technical challenges encountered during the development of this project.
