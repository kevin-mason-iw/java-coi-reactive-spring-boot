import json
import random
from datetime import datetime, timedelta

# Load inventory.json
with open("inventory.json", "r") as f:
    inventory = json.load(f)
real_skus = [item["sku"] for item in inventory]

# Initialize parameters
start_order_number = 10000001
start_date = datetime.strptime("2025-04-01", "%Y-%m-%d")
sku_pool = real_skus.copy()
sku_index = 0

orders = []

for i in range(2500):
    if sku_index >= len(sku_pool):
        random.shuffle(real_skus)
        sku_pool = real_skus.copy()
        sku_index = 0

    order = {
        "OrderNumber": str(start_order_number + i),
        "customerId": f"CUST-{random.randint(1000, 1025)}",
        "orderItems": [],
        "orderDate": (start_date + timedelta(days=random.randint(0, 60))).isoformat(),
        "totalAmount": 0.0
    }

    num_items = random.randint(1, 3)
    total_amount = 0.0

    for _ in range(num_items):
        if sku_index >= len(sku_pool):
            random.shuffle(real_skus)
            sku_pool = real_skus.copy()
            sku_index = 0

        sku = sku_pool[sku_index]
        sku_index += 1
        quantity = random.randint(1, 3)
        unit_price = round(random.uniform(10.0, 100.0), 2)
        total = round(quantity * unit_price, 2)

        order["orderItems"].append({
            "sku": sku,
            "quantity": quantity,
            "unitPrice": unit_price,
            "total": total
        })

        total_amount += total

    order["totalAmount"] = round(total_amount, 2)
    orders.append(order)

# Save the generated orders
with open("2500_orders_with_real_skus.json", "w") as f:
    json.dump(orders, f, indent=2)

print("✅ Generated 2500 orders and saved to 2500_orders_with_real_skus.json")
