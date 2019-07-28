package leetfree;

/**
 * The Employee table holds all employees. The employee table has three columns: Employee Id, Company Name, and Salary.
 * <p>
 * +-----+------------+--------+
 * |Id   | Company    | Salary |
 * +-----+------------+--------+
 * |1    | A          | 2341   |
 * |2    | A          | 341    |
 * |3    | A          | 15     |
 * |4    | A          | 15314  |
 * |5    | A          | 451    |
 * |6    | A          | 513    |
 * |7    | B          | 15     |
 * |8    | B          | 13     |
 * |9    | B          | 1154   |
 * |10   | B          | 1345   |
 * |11   | B          | 1221   |
 * |12   | B          | 234    |
 * |13   | C          | 2345   |
 * |14   | C          | 2645   |
 * |15   | C          | 2645   |
 * |16   | C          | 2652   |
 * |17   | C          | 65     |
 * +-----+------------+--------+
 * Write a SQL query to find the median salary of each company. Bonus points if you can solve it without using any built-in SQL functions.
 * <p>
 * +-----+------------+--------+
 * |Id   | Company    | Salary |
 * +-----+------------+--------+
 * |5    | A          | 451    |
 * |6    | A          | 513    |
 * |12   | B          | 234    |
 * |9    | B          | 1154   |
 * |14   | C          | 2645   |
 * +-----+------------+--------+
 */
public class _569_MedianEmployeeSalary {
/**
 * BUI:D TABLE
 * CREATE TABLE users (Id INTEGER UNIQUE NOT NULL,
 *                     Company varchar(1) NOT NULL,
 *                     salary INTEGER NOT NULL,
 *                    PRIMARY KEY (Id));
 * INSERT INTO users (Id, Company, salary) VALUES (1, 'A', 2431);
 * INSERT INTO users (Id, Company, salary) VALUES (2, 'A', 341);
 * INSERT INTO users (Id, Company, salary) VALUES (3, 'A', 15);
 * INSERT INTO users (Id, Company, salary) VALUES (4, 'A', 15314);
 * INSERT INTO users (Id, Company, salary) VALUES (5, 'A', 451);
 * INSERT INTO users (Id, Company, salary) VALUES (6, 'A', 513);
 * INSERT INTO users (Id, Company, salary) VALUES (7, 'B', 15);
 * INSERT INTO users (Id, Company, salary) VALUES (8, 'B', 13);
 * INSERT INTO users (Id, Company, salary) VALUES (9, 'B', 1154);
 * INSERT INTO users (Id, Company, salary) VALUES (10, 'B', 1345);
 * INSERT INTO users (Id, Company, salary) VALUES (11, 'B', 1221);
 * INSERT INTO users (Id, Company, salary) VALUES (12, 'B', 234);
 * INSERT INTO users (Id, Company, salary) VALUES (13, 'C', 2345);
 * INSERT INTO users (Id, Company, salary) VALUES (14, 'C', 2645);
 * INSERT INTO users (Id, Company, salary) VALUES (15, 'C', 2645);
 * INSERT INTO users (Id, Company, salary) VALUES (16, 'C', 2652);
 * INSERT INTO users (Id, Company, salary) VALUES (17, 'C', 65);
 */

/**
 * median on id
 *
 * select u.id, u.company, u.salary
 * from users as u
 * where u.id IN (
 *   select distinct(FLOOR( ((max-min)/2)+min )) as floor
 *   from users,
 *     (
 *         select company, min(id) as min, max(id) as max
 *         from users
 *         group by company
 *     ) as u2
 *   where users.company = u2.company
 * ) OR u.id IN (
 *   select distinct(CEIL( ((max-min)/2)+min )) as ceil
 *   from users,
 *     (
 *         select company, min(id) as min, max(id) as max
 *         from users
 *         group by company
 *     ) as u2
 *   where users.company = u2.company
 * );
 */

/**
 * real solution
 * select u.id, u.company, u.salary
 * from users as u,
 *     users as u2
 * where u.company = u2.company
 * group by u.Company, u.salary
 * HAVING SUM(CASE
 *     WHEN u.Salary = u2.Salary THEN 1
 *     ELSE 0
 * END) >= ABS(SUM(SIGN(u.Salary - u2.Salary)))
 * ORDER BY u.Id
 */
}
