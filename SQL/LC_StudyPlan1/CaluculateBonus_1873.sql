-- # We can use if and witch condtions in sql
-- #  if condition , val1 , val2 => conditon, actions if true, action if false

select employee_id,  if(employee_id MOD 2 = 1 && name not like 'M%', salary,0) as bonus
from Employees order by employee_id;

-- #Switch condition : slower
-- #  case 
-- #  when cond then action
-- # end

select employee_id,
case
when  employee_id MOD 2 = 1 && name not like 'M%' then salary
else 0
end
as bonus
from Employees order by employee_id;