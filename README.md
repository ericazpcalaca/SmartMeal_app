# Smart Meal

Smart Meal is an app designed to reduce food waste by connecting businesses with consumers who can help redistribute or consume their nearly-expiring food to keep it out of the trash. Our mission is to make sure that the food is not wasted and help the consumers, local shops, and restaurants to save money.
#### Database
SQLite Database
#### Group
* Erica Zasimowicz Pinto Calaca 
* Icaro Novo de Oliveira
* Moisés Cavalcante Lino da Silva
* Tiago Guerra Endsfeldz
  
## Installation Steps
* Clone the Repository
```bash
git clone git@github.com:ericazpcalaca/SmartMeal_app.git
```
* Open in Android Studio: "Open an existing Android Studio project" from the welcome screen.
* Configure Gradle: Check if the gradle is compileSdk 33 and targetSdk 33.
* Click on the "Build" or "Run" button in Android Studio. 
* Run on Emulator/Device: Nexus 5X API 33

## Login & Register
* First Screen: Following the splash activity featuring our app icon, users click to proceed with the login process.
* Login Screen: On this screen, users make a choice between logging in with their existing credentials or creating a new account.
* New Account Registration Screen: The registration screen dynamically adapts its fields based on the user type (customer or business), ensuring the appropriate terms and information are collected.

### Screen capture
<img src="https://github.com/ericazpcalaca/SmartMeal_app/assets/15451346/f0b45879-d08e-4be8-b67f-42dd2cc71acf" width="800" height="500"> 

## Customer
In the customer interface, we'll feature a welcoming screen in the top left corner, offering restaurant suggestions to the user. At the bottom, there's a navigation bar presenting options like "Home," "Search," "Orders," and "Profile." This allows users to edit their profile information and log out of the app. To access their order history, users can simply click on the "Orders" option.

When a customer selects a restaurant, it leads to the order activity, providing a view of the establishment with an image, address, the restaurant's rating, and the available items for purchase. Once the user has made their choices regarding items and quantities, clicking the "View Order" button reveals order details, including the order number, item descriptions, and pickup or delivery address, allowing them to confirm the order.

By tapping on the order icon at the bottom, users can view their current order (if they have one) before accessing their order history. This screen also provides the option to cancel the order if necessary. The profile icon presents the user's information, allowing them to update their profile or log out as needed.

### Video - Customer
https://github.com/ericazpcalaca/SmartMeal_app/assets/15451346/4aa2f4a2-037c-45c3-8d6a-04bf1803587d

## Business
The registration and login process for Business Users mirrors that of Customers.

Within the Business home interface, five buttons will be prominently displayed: "Food Items," "Orders," "Reminder," "Reports," and "Logout." At the top of the screen, the Business User will find an icon allowing access to their profile and the ability to modify their account information.

In the 'Food Items' section, users have the capability to add, modify, or remove items available in their store's inventory.

The "Orders" section allows users to view order information and its details. If a customer has already claimed an order, the business can mark it as complete or cancel it if the customer fails to pick it up or arrives later than scheduled. Such actions will archive the order in the historical records.

In the "Reports" section, businesses can access data regarding sales and revenue. This includes valuable insights such as the number of orders placed, the total value of those orders, and the average order value.

### Video - Business
https://github.com/ericazpcalaca/SmartMeal_app/assets/15451346/51a56ae2-a4b2-4b44-8e33-7100b5bd5c4e





