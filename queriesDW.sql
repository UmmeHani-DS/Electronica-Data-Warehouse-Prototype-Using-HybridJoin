-- Q1  Present total sales of all products supplied by each supplier with respect to quarter and month using drill down concept.

select P.productID, S.supplierID, t.quarterr, t.months, sum(S.revenue) as Total_sales
from `electronica-dw`.sales S
inner join `electronica-dw`.suppliers sup on S.supplierID = sup.supplierID
inner join `electronica-dw`.products P on S.productID = P.productID
inner join `electronica-dw`.timeDimension t on S.timeID = t.timeID
group by P.productID, S.SupplierID, t.quarterr, t.months;

-- Q2
select  s.Product_ID, T.months, SUM(s.revenue) as Total_sales
from  `electronica-dw`.sales s
join `electronica-dw`.products P on s.ProductID = P.ProductID
join `electronica-dw`.timeDimension T on s.TimeID = T.TimeID
join `electronica-dw`.`Supplier` Supp on s.SupplierID = supp.SupplierID
where supp.Supplier_Name = "DJI" AND T.year = 2019
group by s.ProductID, T.months WITH ROLLUP
order by s.ProductID, T.months ASC;