# Android Manual Dependency Injection Example

This repository provides a simple Android project demonstrating manual dependency injection. Manual dependency injection is a technique where dependencies are provided to a class or component manually, as opposed to relying on a dependency injection framework.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Setup](#setup)
- [Usage](#usage)
- [Dependencies](#dependencies)
- [License](#license)

## Introduction

Dependency injection is a crucial aspect of building maintainable and testable Android applications. While many developers use dependency injection frameworks like Dagger, it's important to understand the underlying principles.

This project aims to illustrate how manual dependency injection can be implemented in an Android project. By manually providing dependencies, developers can gain a deeper understanding of the dependency injection process and potentially customize it according to specific project requirements.

## Features

- Manual dependency injection in an Android project.
- Simple example showcasing the injection of dependencies into activities and classes.
- Clean and organized project structure for easy understanding.

## Setup

1. Clone the repository:

    ```bash
    git clone https://github.com/arrazyfathan/android-manual-di.git
    ```

2. Open the project in Android Studio.

3. Build and run the app on an emulator or a physical device.

## Usage

The main focus of this project is to demonstrate the manual injection of dependencies. Check the source code and comments for detailed explanations. Key components include:

- `AppModule`: Module containing methods to provide individual dependencies.
- `MainActivity`: Example of how dependencies are manually injected into an activity.

Feel free to explore the code and experiment with different scenarios to enhance your understanding of manual dependency injection in Android.

## Dependencies

This project has intentionally avoided using dependency injection frameworks like Dagger to emphasize the manual injection process. No external dependencies are required beyond the standard Android development tools.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
