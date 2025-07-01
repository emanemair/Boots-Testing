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
