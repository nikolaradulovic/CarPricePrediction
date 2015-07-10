# 1. About the project

The main idea of this project is to gather data about car ads from the website [www.polovniautomobili.com](www.polovniautomobili.com) and use that data to predict price for a specific car. The project uses two different types of prediction models based on gathered car prices on the market and characteristics of a car. Methods used for prediction are *Linear Regression* and *Support Vector Regression (SVR)*. Results of the two prediction models are compared in order to find out which method is more suitable for this type of prediction and what car features are relevant for prediction.

# 2. Linear Regression and SVR

*Linear Regression* is a popular technique often used in the process of data mining. It can be very simple with only one variable as input and one as output, but it can also have far more input variables. Basically, this model uses one or more independent variables and produces dependent variable as a result. The regression model is used to predict the value of an unknown dependent variable. [1]

*Support vector machine* is a supervised learning model, very similar to linear regression, that analyzes data, recognizes patters and uses these results for predictions. It differs from simple regression in the abality to efficiently perform a non-linear classification too.  

In this project both regression methods are used to determine the formula for price calculation based on other independent variables of a car.   

# 3. Dataset

Data used in this research is collected from the website [www.polovniautomobili.com](www.polovniautomobili.com) that is the most popular online marketplace for buying cars in Serbia. For the purpose of this project, only data related to car manufacturer "Renault" is collected, but this approach can be applied to any other car manifacturer. On the page for performing search query with manufacturer set to "Renault" was issued. Data was scraped from the search results. All gathered data is stored in file ["data/dataSet.txt"](https://github.com/nikolaradulovic/CarPricePrediction/blob/master/data/dataSet.txt). Ads that do not contain price (ads that direct customer to directly contact the seller and agree on the price) are excluded. In total, 3608 valid car ads are collected.

The following example displays an instance of a car ad, along with detailed explanation of all attributes:

```
Megane,136570,6499,2010,81,0,0,1,0
```

Legend:

- **Car model:** "Megane"
- **Mileage:** 136570 (in kilometers)
- **Price:** 6499 (Euros)
- **Year of production:** 2010
- **Horsepower:** 81
- **Fuel:** 0 (0-Diesel, 1-Gas)
- **Air condition:** 0 (0-Manuel, 1-Automatic)
- **Doors:** 1 (0- 2/3, 1- 4/5)
- **Gear:** 0 (0-Manual, 1-Automatic)

# 4. Technical realization

This project uses [Weka library](http://www.cs.waikato.ac.nz/ml/weka/). Weka is a collection of machine learning algorithms for data mining, that was founded at Waikato University of New Zeland. All these algorithms can be used directly from code by importing *weka.jar* file or through the graphical interface called *Weka Explorer*. Weka contains tools for data preprocessing, classification, regression, clustering and visualization.

In this project two Weka classes were used: *LinearRegression* and *SMOreg* for Support Vector Regression. As this dataset contains nominal values too, two different types of classifications were performed with both classifiers. The dataset was first classified with all attributes and the example of this method can be found below.

![Alt text](/image/classify2.png?raw=true "Regression results")

After that, "Remove" filer was used in order to exclude the first parameter ("model") from the classification to see whether the results without the car model are better or worse. 

![Alt text](/image/classify1.png?raw=true "Regression results")

SMOreg class normalizes all attributes every time before classification, and Linear Regression has an option to standardize all values before the processing. The results of all these methods are described in the next paragraph.

# 5. Analysis

Detailed results of this analysis are provided in the file ["data/results.txt"](https://github.com/nikolaradulovic/CarPricePrediction/blob/master/data/results.txt) and here will be provided an explanation. 

In the first part of the analysis result you will see common characteristics of all 4 regression methods. Attributes that are listed as relevant for price prediction in all cases are: kilometers, year, horsepower and gear. First attribute, kilometers, has negative influence on price, which means the more kilometers one car had travelled, the lower its price will be. All other 3 attributes have positive coefficients, which clearly means that the higher they are, higher the car price will be.

![Alt text](/image/price.png?raw=true "Regression results")

As Linear Regression doesn't include air-condition, fuel and gear in a price formula, we can say that these 3 attributes are not relevant for that method of analysis. But SVR does include these 3, so they are certainly relevant for that type of analysis.

Since both regression methods were performed on two different datasets, in the next section we will compare the results of these four analysis and try to determine which dataset is more suitable for this type of prediction. First dataset includes car model as its first attribute, and the second dataset is without the model attribute. The table displays the results of all 4 evaluations.

![Alt text](/image/results.png?raw=true "Regression results")

As relative absolute errors and root relative squared errors aren't so high, it can be stated that all these models can be used to predict the price of a car. From the table can be also concluded that predictions with "model" attribute gives us a little better result that ones without it. Since the method "SVR with car model" gave the lowest errors, we can say that this method is the most suitable for purpose of this project.

To conclude, we determine that Support Vector Regression is a better method for car price prediction, but Linear Regression can also be used since the difference in the results of these two is minor.  

# 6. References

[1] Michael Abernethy, "Data mining with WEKA, Part 1: Introduction and regression", 2010, link: http://www.ibm.com/developerworks/library/os-weka1/, last access: 24.06.2015. <br>
[2] Ian H. Witten, "Data Mining with Weka", 2013, link: http://www.cs.waikato.ac.nz/ml/weka/mooc/dataminingwithweka/slides/Class4-DataMiningWithWeka-2013.pdf, last access: 10.07.2015. <br>
[3] Paul Paisitkriangkrai, "Linear Regression and Support Vector Regression", 2012, link:
http://cs.adelaide.edu.au/~chhshen/teaching/ML_SVR.pdf, last access: 10.07.2015. <br>
[4] Wikipedia,  "Linear regression" link: https://en.wikipedia.org/wiki/Linear_regression, last access: 10.07.2015 <br>
[5] Wikipedia, "Support_vector_machine", link:  https://en.wikipedia.org/wiki/Support_vector_machine, last access: 10.07.2015. 

