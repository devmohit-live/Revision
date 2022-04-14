UPDATE salary SET sex = IF(sex='m','f','m');

-- Approach 2
-- Update salary SET sex = (
-- case
--     when sex = 'm' then 'f'
--      else 'm'
--  end
--  );

--  UPDATE salary SET sex = IF(sex='m','f','m') where true;