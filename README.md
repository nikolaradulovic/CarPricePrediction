<h1> 1. About the project </h1>

The main idea of this project is to get data about used cars from web site www.polovniautomobili.com and use that information to predict the price for a specific car. The application uses 2 different types of predictions, all based on current car prices on the market and characteristics of the car. Methods used for classification are Linear Regression and Support Vector Regression, and results of this process are later compared in order to find out which method is more suitable for this prediction.

<h1> 2. Linear Regression and SVR </h1>

Linear Regression is a famous technique often used in the process of data mining. It can be very simple with only one variable as input and one as output, but of course it can have far more input variables. Basically, all this models have one or more independent variables which together produce dependent variable as a result. The regression model is used to predict the value of an unknown dependent variable.

Support vector machine is a supervised learning model, very similar to linear regression, that analyze data, recognize patters and use these results for later predictions. What differs it from simple regression is that SVR can efficiently perform a non-linear classification too.   

<h1> 3. Dataset </h1>

Data used in this research are collected from web site www.polovniautomobili.com. For the purpose of this assignment, all data is about french car manufacturer "Renault", currently selling on the Serbian market, but of course it can include any other car type. This application uses the Jsoup library for Java, which can easily scrape all necessary data from HTML site, in this case different specifications for a large number of selling vehicles. All data are stored in in "dataSet.csv" file located in folder "data" on project's root. Our search gave 3608 different car instances, which are valid and can be used later for regression. In the next example, you can find a couple of instances with detail explanation for all their attributes:

1. Megane,136570,6499,2010,81,0,0,1,0
2. Clio,168000,1850,2002,60,0,1,0,0
3. Megane,137000,3500,2003,88,0,1,1,0
4. Clio,156000,2100,2002,43,0,1,0,0
5. Laguna,180000,1950,2002,88,1,1,1,0

Explanation for first car:

Car model - "Megane" <br>
Mileage- 136570km <br>
Price - 6499 (Euros) <br>
Year of production- 2010 <br>
Horsepower- 81 <br>
Fuel- 0 (0-Diesel, 1-Gas) <br>
Air conditioner- 0 (0-Manuel, 1-Automatic) <br>
Door count- 1 (0- 2/3, 1- 4/5) <br>
Gear 0 (0-Manuel, 1-Automatic) <br>

All this car attributes are used to predict price of a single car, based on current market prices.

<h1> Technical realization </h1>

This project uses Weka library for Java. Weka is a collection of machine learning algorithms for data mining, that was founded in Waikato University of New Zeland. All these algorithms can be used directly from code by importing "weka. jar" file or through the graphical interface called Weka Explorer. Weka contains tools for data preprocessing, classification, regression, clustering and visualization.

In this project two weka classes that were used are LinearRegression and SMOreg (Support Vector Regression). As this dataset contains nominal values too, two different types of classification were done with each of these classifiers. The dataset was classified with all attributes at first, and after that "Remove" filter was used in order to classify all instances without the first nominal attribute. SMOreg class normalizes all attributes every time before classification, and Linear Regression has option to standardize all values before the processing.




