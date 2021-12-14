-- # LC 177
CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  RETURN (
      # SELF JOIN
      Select e1.salary from Employee e1 join Employee e2 on e1.salary <= e2.salary group by e1.salary having count(distinct e2.salary) = N
      
  );
END

-- # Using dense ranking

-- # Using subquery
