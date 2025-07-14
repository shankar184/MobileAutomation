# 📱 Mobile Automation Framework with Appium + TestNG

This project is a robust, cross-platform **mobile automation framework** built from scratch using **Appium**, **TestNG**, and **Java**. It is designed to automate Android (and optionally iOS) apps with support for:

✅ Appium server management  
✅ Emulator auto-launching  
✅ Parallel execution readiness  
✅ ExtentReports & Allure reporting  
✅ Jenkins CI integration  
✅ Slack notification support

---

## 🚀 Tech Stack

| Tool | Description |
|------|-------------|
| **Java** | Programming language |
| **TestNG** | Test framework |
| **Appium** | Mobile automation tool |
| **ExtentReports** / **Allure** | Test reporting |
| **Maven** | Build tool and dependency management |
| **Jenkins** | CI/CD pipeline |
| **Slack** | Notification integration |

---

## 🧠 Features

- 🔁 **Auto-restart Appium server** (kills port 4723 if occupied)
- 🤖 **Auto-start Android Emulator** if not already running
- 🧪 Page Object Model (POM) architecture
- 🔍 Real-device & emulator testing support
- 📝 Centralized capabilities via `CapabilitiesManager`
- 🧹 Clean teardown with session & server shutdown
- 📦 Compatible with Jenkins pipeline jobs
- 🔔 Slack integration for build status

---

## 📁 Project Structure

```bash
├── src
│   ├── main
│   │   └── java/utils               # Utility classes (Appium server, emulator, waits, etc.)
│   └── test
│       ├── java/base               # BaseTest with setup/teardown
│       ├── java/pages              # Page Objects (e.g., LoginPage)
│       ├── java/tests              # TestNG test classes
│       └── resources/testng.xml    # Test suite configuration
├── reports/                        # ExtentReports / Allure reports
└── pom.xml                         # Maven dependencies
