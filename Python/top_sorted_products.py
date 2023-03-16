
import json

def get_top_3_products_per_city(data):
    # Load JSON input
    # data = json.loads(json_input)

    # Initialize result
    result = []

    # Iterate over cities in input
    for city_data in data:
        city_name = city_data['city']
        products = city_data['products']

        print(city_name)

        # Sort products by price (descending order)
        products_sorted = sorted(products, key=lambda x: x['price'], reverse=True)

        # Get top 3 products by price
        top_3_products = [  (p['name'], p['price']) for p in products_sorted[:3]]


        # Add top 3 products to result dictionary
        result.append( (city_name, top_3_products) )

    # Return result dictionary
    return result



myjson = [
        {
            "city" : "New York",
            "products" : [
                {"name": "Product A", "price": 10},
                {"name": "Product B", "price": 20},
                {"name": "Product C", "price": 15},
                {"name": "Product D", "price": 5},
                {"name": "Product E", "price": 25},
                {"name": "Product F", "price": 30}
            ],
            "id" : 12
        },
        {
            "city" :  "Los Angeles",
            "products" :[
                {"name": "Product G", "price": 12},
                {"name": "Product H", "price": 18},
                {"name": "Product I", "price": 22},
                {"name": "Product J", "price": 14},
            ],
             "id" : 23
        }
    ]

print(get_top_3_products_per_city(myjson))

