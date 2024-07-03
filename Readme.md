
# Low Level Design of Online Shopping Apps like Amazon, Flipkart, eBay, etc.

## Design Patterns Used

### 1. Strategy Pattern
- **Where:** Payment processing (`CreditCardPayment`, `BankTransferPayment`, `UPIPayment`, `CashOnDelivery`).
- **Purpose:** Encapsulates different payment algorithms and allows them to be interchanged at runtime.
- **Benefits:** Enhances flexibility and adherence to the Open/Closed Principle (OCP) by enabling the addition of new payment methods without modifying existing code.

### 2. Singleton Pattern
- **Where:** `Cart` class.
- **Purpose:** Ensures a single instance of the Cart class for each customer.
- **Benefits:** Manages the global state of the cart for a customer, ensuring consistency and reducing the risk of multiple cart instances.

### 3. Builder Pattern
- **Where:** `Order` class.
- **Purpose:** Facilitates the construction of complex objects by providing a step-by-step initialization process.
- **Benefits:** Improves readability and maintainability by providing a fluent interface for object creation. It adheres to the Single Responsibility Principle (SRP) by separating object construction from its representation.

### 4. Factory Method Pattern
- **Where:** Notification creation (`EmailNotification`, `PhoneNotification`).
- **Purpose:** Encapsulates the instantiation logic of notification objects.
- **Benefits:** Simplifies object creation and promotes the Open/Closed Principle (OCP) by allowing the addition of new notification types without changing the existing client code.

## SOLID Principles Applied

### 1. Single Responsibility Principle (SRP)
- **Description:** A class should have only one reason to change, meaning it should have only one job or responsibility.
- **Examples:**
    - `PaymentStrategy` implementations (`CreditCardPayment`, `BankTransferPayment`, `UPIPayment`, `CashOnDelivery`) focus solely on their respective payment processing logic.
    - Builder classes (`OrderBuilder`) are responsible only for constructing the objects step-by-step.
    - Notification classes (`EmailNotification`, `PhoneNotification`) are responsible only for sending their respective types of notifications.

### 2. Open/Closed Principle (OCP)
- **Description:** Software entities (classes, modules, functions, etc.) should be open for extension but closed for modification.
- **Examples:**
    - Strategy Pattern in payment processing allows adding new payment methods without modifying existing code.
    - Factory Method Pattern in notification creation enables adding new notification types without altering existing code.

### 3. Liskov Substitution Principle (LSP)
- **Description:** Objects of a superclass should be replaceable with objects of a subclass without affecting the correctness of the program.
- **Examples:**
    - `PaymentStrategy` implementations ensure that any subclass (e.g., `CreditCardPayment`, `BankTransferPayment`, `UPIPayment`, `CashOnDelivery`) can be used interchangeably with the `Payment` class.
    - Notification implementations (`EmailNotification`, `PhoneNotification`) adhere to the `Notifiable` interface, allowing them to be used interchangeably.

### 4. Interface Segregation Principle (ISP)
- **Description:** Clients should not be forced to depend on interfaces they do not use.
- **Examples:**
    - The `CategoryManager` and `ProductManager` interfaces segregates the methods between `Seller` and `Admin`, ensuring that implementing classes are not burdened with methods they do not need.
    - The Notification interface specifies only the methods necessary for sending notifications, ensuring concrete notification classes implement only what is required.

### 5. Dependency Inversion Principle (DIP)
- **Description:** High-level modules should not depend on low-level modules; both should depend on abstractions. Abstractions should not depend on details. Details should depend on abstractions.
- **Examples:**
    - The Cart class depends on the PaymentStrategy interface rather than concrete payment method classes, promoting loose coupling and flexibility.
    - The Order class relies on the Notification interface, not the concrete notification types, ensuring that high-level modules depend on abstractions.

## Summary

- **Strategy Pattern:** Applied to payment processing to allow interchangeable payment methods.
- **Singleton Pattern:** Ensures a single instance of the Cart for each customer.
- **Builder Pattern:** Facilitates the construction of complex Order objects.
- **Factory Method Pattern:** Encapsulates the creation of different types of notifications.
- **Single Responsibility Principle:** Ensures classes have a single focus, improving maintainability.
- **Open/Closed Principle:** Allows the extension of functionalities without modifying existing code.
- **Liskov Substitution Principle:** Ensures subclasses can replace their superclasses without altering the program's correctness.
- **Interface Segregation Principle:** Promotes small, client-specific interfaces to prevent unnecessary dependencies.
- **Dependency Inversion Principle:** High-level modules depend on abstractions rather than concrete implementations, promoting flexibility and decoupling.

By applying these design patterns and SOLID principles, the design is made more modular, flexible, maintainable, and scalable, aligning with best practices in software engineering.
