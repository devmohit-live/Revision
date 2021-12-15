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
-- Using offset and limit
-- offset : skip starting k rows
--  limit take only k rows 
--  approach : sort them by desc order of their salay (distinct as salary can be reapeated/same) : offset n-1, limit 1
--  at start use set N = N -1 (can't to that in query) : first limit is writtenn then offset

CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  set N = N -1;
  RETURN (
      # Write your MySQL query statement below.
      select distinct salary from employee order by salary desc limit 1  offset N
  );
END
