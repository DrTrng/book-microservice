Publisher
│
│ 1-N
▼
Books
│
├── ManyToMany → Authors
│
├── ManyToMany → Categories
│
├── OneToOne → Inventory
│
└── OneToMany → BookImages

# How to create book 
1️⃣ PublisherService
2️⃣ AuthorService
3️⃣ CategoryService
4️⃣ BookService
5️⃣ InventoryService
6️⃣ BookImageService

# Flow create book in service
Client
  │
  ▼
BookController
  │
  ▼
BookService
  │
  ├ find Publisher
  ├ find Authors
  ├ find Categories
  ▼
Save Book
# Structure project Book
product-service
│
├── controller
│   ├── BookController
│   ├── AuthorController
│   ├── CategoryController
│   └── PublisherController
│
├── service
│   ├── BookService
│   ├── AuthorService
│   ├── CategoryService
│   └── PublisherService
│
├── repository
│
├── entity
│
├── mapper
│
└── dto

# Step create  -> 
1️⃣ Create Publisher
2️⃣ Create Author
3️⃣ Create Category
4️⃣ Create Book
