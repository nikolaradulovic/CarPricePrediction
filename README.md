# 1. About the project

The main idea of this project is to gather data about cars ads from the website [www.polovniautomobili.com](www.polovniautomobili.com) and use that data to predict a price for a specific car. The application uses two different types of prediction models based on gathered car prices on the market and characteristics of a car. Methods used for prediction are *Linear Regression* and *Support Vector Regression (SVR)*. Results of the two prediction models are compared in order to find out which method is more suitable for this tyle of prediction.

# 2. Linear Regression and SVR

*Linear Regression* is popular technique often used in the process of data mining. It can be very simple with only one variable as input and one as output, but it can also have far more input variables. Basically, this model uses one or more independent variables and produces dependent variable as a result. The regression model is used to predict the value of an unknown dependent variable.

*Support vector machine* is a supervised learning model, very similar to linear regression, that analyzes data, recognizes patters and uses these results for predictions. What differs it from simple regression is that SVR can efficiently perform a non-linear classification too.   

In this project we use these two regression methods to determine the formula for price calculation based on other independent variables that characterize a single car.   

# 3. Dataset

Data used in this research is collected from the website [www.polovniautomobili.com](www.polovniautomobili.com) that is the most popular online marketplace for buying cars. For the purpose of this project, only data related to car manufacturer "Renault" is collected. But this approach can be applied to any other car manifacturer. Data is scraped from the website and stored in file "data/dataSet.txt". In total, 3608 valid car ads are collected. Ads that do not contain price (direct customer to directly contact the seller and agree on the price) are excluded. 

The following example displays an instance of car ad, along with detail explanation of all attributes:

```
Megane,136570,6499,2010,81,0,0,1,0
```

Explanation for first car:

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

In this project two Weka classes were used - *LinearRegression* and *SMOreg* for Support Vector Regression. As this dataset contains nominal values too, two different types of classification were performed with each of these classifiers. The dataset was first classified with all attributes and the example of this method you can see below.

![Alt text](/image/classify2.png?raw=true "Regression results")

After that we used "Remove" filer in order to exclude first parameter ("model") from the classification and see whether the results without the car model are better or worse. 

![Alt text](/image/classify1.png?raw=true "Regression results")

SMOreg class normalizes all attributes every time before classification, and Linear Regression has option to standardize all values before the processing. The results of all these methods are described in the next paragraph.

# 5. Analysis

Detailed results of this project are provided in the file "data/results.txt" and here will be provided an explanation. 

In the first part of this paragraph you will see common characteristics of all 4 regression methods. Attributes which regression results listed as relevant for price prediction in all cases are: kilometers, year, horsepower and gear. First attribute, kilometers, has negative influence on price, which means the more kilometers one car had travelled, the lower its price will be. All other 3 attributes have positive coefficients before it, which clearly means that the higher they are, higher the car price will be. Concretely, for year parameter, this means the younger vehicle is, the higher value for the year of production it has. Similar goes for horsepower and gear parameters too. 

![Alt text](/image/price.png?raw=true "Regression results")

As Linear Regression doesn't include air-condition, fuel and gear in a price formula, we can say that these 3 attributes are not relevant to that method. But SVR includes these 3, so they are certainly relevant by implementing that type of regression.

Since all regressions were done on two different datasets, in the next part we will compare results and try to determine which dataset is more suitable for this prediction. First dataset includes car model as its first attribute, and in another case this attribute is omitted with the help of the weka remove filter. In the table below you can see compared results of all 4 evaluations.

![Alt text](/image/results.png?raw=true "Regression results")

As our relative absolute errors and root relative squared errors aren't so high, we can say that all this models can be used to predict the price of a single car, based on data we used in our research. From the table we can also conclude that predictions with "model" attribute gives us a little better result that the one without it. Since the method "SVR with car model" gave us the lowest errors we can say that this method is the most suitable for purpose of this project.

To conclude, we determine that Support Vector Regression is better method for car price prediction, but we can also use Linear Regression since the difference in the results of these two is not vast.  

# 6. References

- http://www.ibm.com/developerworks/library/os-weka1/
- http://www.cs.waikato.ac.nz/ml/weka/mooc/dataminingwithweka/slides/Class4-DataMiningWithWeka-2013.pdf
- http://cs.adelaide.edu.au/~chhshen/teaching/ML_SVR.pdf
- https://en.wikipedia.org/wiki/Linear_regression
- https://en.wikipedia.org/wiki/Support_vector_machine
- http://kernelsvm.tripod.com/
