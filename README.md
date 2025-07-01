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


## ✅ Test Cases

| Test Method                         | Description                                                                 |
|------------------------------------|-----------------------------------------------------------------------------|
| `Test001_VerifyHomepageLoad`       | Validates homepage loads with nav bar, banners, and product list visible   |
| `Test002_CheckAiAssistantFunctionality` | Sends a question to the chatbot and verifies the response includes keywords |
| `Test002_Registration` *(disabled)*| Simulates user registration flow (requires manual OTP verification)         |
| `Test003_ChooseProductCate`        | Selects a random product category and checks that it loads properly         |
| `Test004_VerifiyProductListIsLoaded` | Confirms that product list is shown correctly after selecting a category    |
| `Test005_FilterFunctionality`      | Applies brand and stock filters and checks that URL reflects the filters    |
| `Test006_ChooseRandomProduct`      | Selects a product and verifies the product detail page matches the link     |
| `Test007_ViewingProductDetailes`   | Validates product name, price, code, and review section are displayed       |
| `Test008_AddItemToTheCart`         | Adds a product to the cart and verifies it appears in the basket            |
| `Test010_DicsountCode`             | Applies a discount code and validates the success message                   |
| `Test011_CheckSearchProductFunctionality` | Performs a product search and confirms results are shown                    |



## ❗ Notes

- The `Test002_Registration` method is **disabled** by default due to **OTP limitations**.
- Jordan’s country code is selectable, but **no SMS was received**, which blocks full registration.
- Discount code testing can be **inconclusive** without backend access — the UI may show failure if the code is expired or already used.
- `Thread.sleep()` is used in some places for simplicity but should ideally be replaced with `WebDriverWait`.

## 👩‍💻 Author

**Eman Emair**  
📧 emanimair2@gmail.com
📱 +962 799646707  
🔗 [LinkedIn](https://www.linkedin.com/in/eman-emair-%D8%A7%D9%8A%D9%85%D8%A7%D9%86-%D8%B9%D9%85%D9%8A%D8%B1/)

