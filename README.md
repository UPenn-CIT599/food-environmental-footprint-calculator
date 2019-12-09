# final-project-food-environmental-footprint-calculator

#### Before you begin:

* Remember to install all of the 8 provided .jar packages to run full project functions. We have provided all the .jar packages in the folder “needed jars”.
* Import the whole program from our Git repository, not just the “src” folder.

#### Algorithm of food suggestions:

After the user selects a food item, the program returns three food items in the same category with lower greenhouse gas (GHG) emissions. If the input food already ranks among the three lowest GHG-emitting foods in the selected food category, the program returns foods with lower GHG emissions in the same category, as well as some random food items in the same category in order to make up a total of three food recommendations (for example, if the user inputs “Chicken”, the program returns “Egg” as well as two random food in the “Meat” category, since “Chicken” ranks second lowest in terms of GHG emissions in the “Meat” category.)

### Let’s go! 

**1. Welcome screen (MainFrame.java)**

Run the MainFrame.java, and you will see this welcome frame. Input your name and email into the text fields, before hitting the ‘Let’s go!’ button. (Remember to input a valid email address to receive a surprise!)

![alt text](https://github.com/UPenn-CIT599/final-project-food-environmental-footprint-calculator/blob/master/src/images/frame1.png "Welcome frame")

**2. Select your food items (FoodSelectionFrame.java)**

Select a food category, after which a list of foods in your selected category will appear. Select the food item you want to check, and input the average amount of this food you consume. Change the unit of weight measurement (g/kg) using the dropdown list on the right, then click the button “Find Out!”.

![alt text](https://github.com/UPenn-CIT599/final-project-food-environmental-footprint-calculator/blob/master/src/images/frame2a.png "Food selection frame")

![alt text](https://github.com/UPenn-CIT599/final-project-food-environmental-footprint-calculator/blob/master/src/images/frame2b.png "Food selection frame")

**3. Calculation Results (ResultsFrame.java)**

See how much GHG you produce per year by eating this food item (the program assumes you eat it everyday) and its impacts - you may be surprised. Click the button “Tell me more!” to check out alternative food suggestions.

![alt text](https://github.com/UPenn-CIT599/final-project-food-environmental-footprint-calculator/blob/master/src/images/frame3.png "Results frame")

**4. Food and Dishes Recommendation (AlternativeFoodFrame.java)**

Now we have several food suggestions for you - to lead a more eco-friendly dietary lifestyle, or to diversify your recipes. Select a recommended food from the dropdown list to see what dishes you can make with this low(er)-carbon food. 

![alt text](https://github.com/UPenn-CIT599/final-project-food-environmental-footprint-calculator/blob/master/src/images/frame4a.png "Alternative food frame")

![alt text](https://github.com/UPenn-CIT599/final-project-food-environmental-footprint-calculator/blob/master/src/images/frame4b.png "Alternative food frame")

**5. Generate PDF and send mails (SendMail.java & PDFgenerator.java)**

You can choose to receive an email that records all the results of this test by clicking the “Email Results” button. A PDF file will be generated and delivered to the email address that you input in the first frame.

![alt text](https://github.com/UPenn-CIT599/final-project-food-environmental-footprint-calculator/blob/master/src/images/email.png "Email successful")

![alt text](https://github.com/UPenn-CIT599/final-project-food-environmental-footprint-calculator/blob/master/src/images/pdf.png "PDF report")
