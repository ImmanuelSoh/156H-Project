-- Computer Science II
-- Assignment 4.0
--
-- Name(s):  Immanuel Soh
--           isoh2@huskers.unl.edu
--
-- Date: 2024/03/29
--
-- This is a set of queries for YRLess' new test database for its sales management system
--

-- 1. A query to retrieve the main attributes of each person (their code, and last/first name)
SELECT firstName, lastName, uuid AS code FROM Person;

-- 2. A query to retrieve the major fields for every person including their address (but excluding emails)
SELECT p.firstName AS firstName, p.lastName AS lastName, p.uuid AS code, a.street AS street, a.city AS city, s.state AS state, z.zip AS zip
FROM Person p LEFT JOIN Address a ON p.addressId = a.addressId
LEFT JOIN State s ON a.stateId = s.stateId
LEFT JOIN Zip z ON a.zipId = z.zipId;

-- 3. A query to get the email addresses of a specific person
SELECT p.firstName AS firstName, p.lastName AS lastName, e.emailAddress AS emails FROM Person p LEFT JOIN Email e ON p.personId = e.personId WHERE p.personId = 5;

-- 4. A query to change the email address of a specific email record
SELECT emailId, emailAddress AS unchangedAddress FROM Email WHERE emailId = 5;
UPDATE Email SET emailAddress = 'mynewemail@yahoo.com' WHERE emailId = 5;
SELECT emailId, emailAddress AS changedAddress FROM Email WHERE emailId = 5;

-- 5. A query (or series of queries) to remove a specific person record
SELECT p.personId AS personId, p.firstName AS firstName, p.lastName AS lastName, p.uuid AS code, a.street AS street, a.city AS city, s.state AS state, z.zip AS zip
FROM Person p LEFT JOIN Address a ON p.addressId = a.addressId
LEFT JOIN State s ON a.stateId = s.stateId
LEFT JOIN Zip z ON a.zipId = z.zipId;

SELECT p.personId AS personId, p.firstName AS firstName, p.lastName AS lastName, e.emailAddress AS emails FROM Person p LEFT JOIN Email e ON p.personId = e.personId;

DELETE FROM Email WHERE personId = 5;
DELETE FROM Person WHERE personId = 5;

SELECT p.personId AS personId, p.firstName AS firstName, p.lastName AS lastName, p.uuid AS code, a.street AS street, a.city AS city, s.state AS state, z.zip AS zip
FROM Person p LEFT JOIN Address a ON p.addressId = a.addressId
LEFT JOIN State s ON a.stateId = s.stateId
LEFT JOIN Zip z ON a.zipId = z.zipId;

SELECT p.personId AS personId, p.firstName AS firstName, p.lastName AS lastName, e.emailAddress AS emails FROM Person p LEFT JOIN Email e ON p.personId = e.personId;

-- 6. A query to get all the items on a specific sales record
SELECT si.saleId AS saleId, i.name AS itemsSold FROM Item i JOIN SaleItem si ON i.itemId = si.itemId AND si.saleId = 1;

-- 7. A query to get all the items purchased by a specific person
SELECT c.firstName AS customer, i.name AS itemsSold FROM Item i 
JOIN SaleItem si ON i.itemId = si.itemId 
JOIN Sale s ON si.saleId = s.saleId
JOIN Person c ON s.customerId = c.personId 
AND c.personId = 1;

-- 8. A query to find the total number of sales made at each store
SELECT st.storeCode AS store, COUNT(sa.saleId) AS numberOfSales FROM Store st 
JOIN Sale sa ON st.storeId = sa.storeId GROUP BY st.storeId;

-- 9. A query to find the total number of sales made by each employee
SELECT p.firstName AS employee, COUNT(sa.saleId) AS numberOfSales FROM Person p 
JOIN Sale sa ON p.personId = sa.employeeId GROUP BY p.personId;

-- 10. A query to find the subtotal charge of all data plans purchased in each sale 
-- (hint: you can take an aggregate of a mathematical expression). Do not include taxes.
SELECT s.saleId AS saleId, ROUND(SUM(i.baseCost * si.boughtGB), 2) AS subtotal FROM Sale s 
LEFT JOIN SaleItem si ON s.saleId = si.saleId
LEFT JOIN Item i ON si.itemId = i.itemId GROUP BY s.saleId;

-- 11. A query to detect invalid data in a sale as follows. When a customer buys a certain data plan 
-- they buy a certain number of GBs. It should not be the case that a single sale has the two of the 
-- same type of data plan for (say) 25GB and 25GB. There should be a single record with 50GB. Write a 
-- query to find and report any sale that includes multiple records of the same data plan. If your database 
-- design prevents such a situation, you should still write this query (but of course would never expect any results).
SELECT saleId, itemId, COUNT(ItemID) AS itemCount
FROM SaleItem Group By saleId, itemId HAVING itemCount > 1;

-- 12. Write a query to detect a potential instance of fraud where an employee makes a sale to 
-- themselves (the same person is the sales person as well as the customer).
SELECT s.saleId AS saleId, p.firstName AS employeeFirstName, p.lastName AS employeeLastName FROM Sale s
JOIN Person p ON s.employeeId = p.personId
JOIN Person c ON s.customerId = c.personId WHERE s.employeeId = s.customerId;

SELECT i.code, i.type, i.name, i.baseCost, si.beginningLeaseDate, si.endingLeaseDate, si.serviceHours, p.uuid AS employeeID, si.vpNumber, si.vpDays, si.boughtGB FROM Item i JOIN SaleItem si ON i.itemId = si.itemId LEFT JOIN Person p ON si.employeeID = p.personId;

SELECT i.code, i.type, i.name, i.baseCost, si.beginningLeaseDate, si.endingLeaseDate, si.serviceHours, si.employeeID, si.vpNumber, si.vpDays, si.boughtGB FROM Item i JOIN SaleItem si ON i.itemId = si.itemId;

SELECT * FROM Item;
SELECT * FROM Sale;
SELECT * FROM SaleItem;
SELECT s.saleCode, i.code AS itemCode FROM SaleItem si JOIN Sale s ON si.saleId = s.saleId JOIN Item i ON i.itemId = si.itemId;