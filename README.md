# EasyMart_Application
* It is a ecommerce System as Amazon or flipkart in which Customer can buy items and Seller can purchase thier Products.
* Customer can buy or purchase their products either from the cart or can direclty purchase the item.
* Customer can buy their product through their different ATM card as well.
* After purchasing a item, customer will get the notification on their email-id about the item purchased.

# Set Of APIs
* Customer APIs
* Seller APIs
* Product APIs
* Item APIs
* Cart APIs
* Card APIs
* Order APIs

# How to See APIs Documentation
* After running Backend, Open the browser and type the url `http://localhost:8080/swagger-ui/index.html#/` 
* It exposes all the APIs present in the Easy-Mart Application.
* After getting all APIs, Try out APIs for more understanding about the Application. **Click on the below link**
* [See All the APIs of Application](http://localhost:8080/swagger-ui/index.html#/)
* **Note - Need to run the Server to see All APIs**

# TechStack Used
* **Language** : Java
* **For Backend** : Spring Boot
* **For Database** : MySQL
* **IDE** : Intellij Idea
* **Other Tools** : Postman, Swagger


# How to run the Application

### For Backend :
* Open the Backend Project in any IDE
* Set some application.propeties like `DBName`, `Username` and `password`
* **Note - DbName should be same as created in MySQL**
* Start the server by running an application
* For sending **Real Time Emails on Customer Email-ID**, need some configuration that are given below :
     * **Configuration :**
        * First import the Java Mail Sender Library in pom.XML file.
        * After importing, write some configuration line in application.properties inside resource folder of an application.
        ```Java
        spring.mail.host=smtp.gmail.com
        spring.mail.port=587
        spring.mail.username=......
        spring.mail.password=......
        spring.mail.properties.mail.smtp.auth=true
        spring.mail.properties.mail.smtp.starttls.enable=true
    * **_Note : Username and Password must be generated with respective Email-ID with App password that will find inside setting of           respective Email-ID_.**

**Note : It uses a `MySQL` as a database, `JavaMailSender Library` for Sending Real-Time Emails and  `Swagger Dependency` for APIs Documentation**
