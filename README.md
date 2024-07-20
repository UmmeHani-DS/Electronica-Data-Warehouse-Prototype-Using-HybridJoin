# Electronica Business Data Warehouse Prototype

### Introduction
This project involves the design, implementation, and analysis of a Data Warehouse (DW) prototype for Electronica Business Chain. The goal is to analyze the shopping behavior of customers in near-real-time to optimize selling techniques such as promotions. This project implements a near-real-time ETL (Extraction, Transformation, and Loading) process using the HYBRIDJOIN algorithm, developed in Java.

### Objective
The objective of this project is to build a near-real-time data warehouse that can process and analyze customer transactions efficiently. By implementing the HYBRIDJOIN algorithm and utilizing a multithreaded approach, the project aims to provide insights into customer behavior and optimize sales strategies.

### Key Features
1. **Star Schema Design**: The DW follows a star-schema design with appropriate dimension and fact tables for the sales scenario. The schema includes tables for transactions and master data, enriched with additional attributes.
   
2. **HYBRIDJOIN Algorithm**: The project implements the HYBRIDJOIN algorithm to join transactional data with master data during the ETL process. This involves reading chunks of data, loading them into memory, performing joins, and then loading the enriched data into the DW.

3. **Multithreading**: The project uses a multithreaded approach with three main threads:
   - **StreamGenerator**: Generates a stream of sales data.
   - **HybridJoin**: Executes the HYBRIDJOIN algorithm.
   - **Controller**: Monitors the stream and adjusts the processing speed.

4. **Data Analysis**: Once the data is loaded into the DW, various OLAP queries are applied to analyze sales data. This includes calculating total sales, identifying popular products, and creating materialized views for performance analysis.

### Project Components
- **createDW.sql**: SQL script to create the star-schema for the DW, including dimension and fact tables with primary and foreign keys.
- **ETL Java Project**: Eclipse project containing Java files to implement ETL operations, including the HYBRIDJOIN algorithm. The project supports multithreading and takes database credentials at runtime.
- **queriesDW.sql**: SQL script containing OLAP queries to analyze the DW data.
- **Project Report**: Document detailing the project overview, DW schema, HYBRIDJOIN algorithm, OLAP query outputs, shortcomings of HYBRIDJOIN, and project learnings.
- **readMe.txt**: Step-by-step instructions to operate the project.

### Repository Structure
- `createDW.sql`: SQL script to create the star-schema.
- `ETL/`: Eclipse project directory with Java files for ETL operations.
- `queriesDW.sql`: SQL script with OLAP queries.
- `projectReport.docx`: Detailed project report.
- `readMe.txt`: Instructions for operating the project.

### How to Run
1. Import the ETL project into Eclipse.
2. Set up MySQL and import the `transactions.csv` and `master_data.csv` files into the database.
3. Run the SQL script `createDW.sql` to set up the DW schema.
4. Execute the Java project, providing MySQL credentials when prompted.
5. Run the OLAP queries in `queriesDW.sql` to analyze the data.
6. Refer to the `readMe.txt` for detailed steps and additional information.

This project showcases the practical implementation of a data warehouse prototype, combining real-time data processing, multithreading, and analytical querying to optimize business operations.
