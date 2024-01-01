```mermaid
%%{ init : {  "flowchart" : { "curve" : "linear" }}}%%
flowchart TD
    A["Launch"] --> B["Create Account"]
    A --> C["Login"]
    B --> C
    C --> D{"Role?"}
    D -->|"Administrator"| E["Administrator Menu"]
    D -->|"Customer"| F["Customer Menu"]
    E --> G["User Management"]
    E --> H["Inventory Management"]
    E --> X[Logout]
    X --> A[Launch];
    F --> X
    F --> J["Shopping Menu"]
    F --> K["View Orders"]
    F --> L["Pay an Order"]
    F --> M["Cancel an Order"]
    G --> N["- Add Administator\n- Display Administators\n- Display Customers\n- Display Orders\n- Delete a User"]
    H --> O["- Add Product\n- Display Products\n- Update Product\n- Delete Product\n- Display Inventory\n- Modify Quantity\n- Search Product\n- Add Coupon\n- Deactivate Coupon\n- Display Coupons"]
    J --> P["- View Products\n- Rate a Product\n- Add item to Cart\n- Update item quantity\n- Remove item from Cart\n- Display Cart\n- Checkout"]
    L --> Q["Cash Payment"]
    L --> R["Credit Card Payment"]
