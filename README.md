# 🧪 Boots.com Automated UI Testing

This repository contains a suite of automated UI test cases for [Boots.com](https://www.boots.com/) built with **Java**, **Selenium WebDriver**, and **TestNG**.  
The goal is to validate key user journeys such as product search, filtering, registration, AI assistant usage, and cart operations.

---

## 📋 Table of Contents

- [Overview](#-overview)
- [Technologies Used](#-technologies-used)
- [Installation](#-installation)
- [How to Run](#-how-to-run)
- [Test Cases](#-test-cases)
- [Author](#-author)

---

## 🧾 Overview

This project simulates and validates real user interactions on the Boots.com e-commerce website using automated test scripts.  
It tests everything from homepage rendering to chatbot functionality, product filtering, discount code application, and more.

---

## 🚀 Technologies Used

- Java
- Selenium WebDriver
- TestNG
- ChromeDriver
- Maven (optional)

---

## 🛠️ Installation

1. Install [Java JDK 8+](https://www.oracle.com/java/technologies/javase-downloads.html)
2. Install [Chrome Browser](https://www.google.com/chrome/)
3. Download [ChromeDriver](https://chromedriver.chromium.org/) and add it to your system PATH
4. Install TestNG (via your IDE or Maven)

---

## ▶️ How to Run

1. Clone this repository
2. Open the project in your IDE (Eclipse, IntelliJ, etc.)
3. Ensure ChromeDriver is accessible
4. Run the `BootsTesting.java` file as a TestNG test

Alternatively, using the terminal:

```bash
java org.testng.TestNG testng.xml



✅ Test Cases
Test Method	Description
Test001_VerifyHomepageLoad	Validates homepage loads with essential sections visible
Test002_CheckAiAssistantFunctionality	Sends a question to the AI chatbot and verifies response
Test002_Registration (disabled)	Simulates account registration
Test003_ChooseProductCate	Randomly selects and verifies a product category
Test004_VerifiyProductListIsLoaded	Ensures products are listed on category pages
Test005_FilterFunctionality	Tests brand filtering and in-stock status
Test006_ChooseRandomProduct	Clicks a product and checks the detail page
Test007_ViewingProductDetailes	Verifies product name, price, reviews, and code
Test008_AddItemToTheCart	Adds a product to the basket and verifies it's added
Test010_DicsountCode	Applies a promo code and checks the success message
Test011_CheckSearchProductFunctionality	Verifies the search bar shows valid results

❗ Notes
The Test002_Registration method requires manual interaction (e.g., CAPTCHA and OTP).

Thread.sleep() is used for simplicity but should be replaced by WebDriverWait in production-ready tests.

ChromeDriver must be compatible with your Chrome browser version.

👩‍💻 Author
Eman Emair
📧 e.manimair2@gmail.com
📱 +962 799646707
🔗 LinkedIn Profile

📄 License
This project is for educational and demonstration purposes only.

yaml
Copy
Edit

---

Let me know if you’d like me to create this as a downloadable file or if you'd like to add badges (e.g., T
