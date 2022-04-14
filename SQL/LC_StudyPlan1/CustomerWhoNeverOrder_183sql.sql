SELECT 
    name as Customers 
from 
    Customers  
where 
    id not in 
    (
    SELECT customerId  from Orders
    )
;
