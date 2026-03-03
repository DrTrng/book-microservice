🧠 Architecture Explanation (Brief – Startup-Friendly)

🔐 Authentication Service

Login/Registration

JWT/OAuth

Token & Session Management

⚙️ Configuration Service

Centralized config

Load config by environment (dev/staging/prod)

Avoid hardcoding config in services

🧠 Core Service

Business Orchestration

No separate database (or very little)

Order Coordination:

Order

Payment

Product

User

Send events/logs

📦 Product Service

Manage books, categories, inventory

Synchronize search data to Elasticsearch

🧾 Order Service

Create orders

Manage order status

Publish events when orders are created/canceled

💳 Payment Service

Process payments

Integrate external gateways (Stripe, VNPay, PayPal…)

Consume events from orders Service

👤 User Service

User information

Profile, address, preferences


+-------------------+
|  Web / Mobile App |
+---------+---------+
          |
          v
+-------------------+
| AuthenticationSvc |
+-------------------+
          |
          v
+-------------------+        +----------------------+
|   Core Service    +------->| Configuration Service|
+----+----+----+----+        +----------------------+
     |    |    |
     |    |    |
     v    v    v
+--------+  +--------+  +--------+
| Product|  | Order  |  |  User  |
| Service|  | Service|  | Service|
+----+---+  +----+---+  +----+---+
     |           |            |
     v           v            v
 Product DB   Order DB     User DB
                   |
                   v
            +--------------+
            | Payment Svc  |
            +------+-------+
                   |
                   v
         External Payment Gateway

     +--------------------------------+
     | RabbitMQ / Kafka (Events)      |
     +--------------------------------+
                   |
                   v
         +------------------+
         | Elasticsearch    |
         +------------------+
                   |
                   v
              +---------+
              | Kibana  |
              +---------+