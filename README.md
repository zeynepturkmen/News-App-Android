<h1 align="center"> Android News Application :newspaper: </h1>

This project aims to display news articles with different categories to the user and allow them to leave comments. It utilizes tabs to organize the articles into different categories, which can be navigated by swiping or clicking. The number of tabs is dynamic and changes according to the number of categories in the database. When a user clicks on an article, they are redirected to a more detailed page where they can read the article in full and leave a comment.
 
Since most news APIs are not free to use, I have built my own API using the Spring Framework and added some mock data for testing purposes. When the code is executed, this data is automatically saved to the database as part of the initialization process. The backend code includes all the necessary routes for fetching news articles, comments, categories, and posting comments.

Everything in this project is ready to use, except for the mongo string. To use the project, you must add your own database URI to the "application.properties" file in the backend folder.

## Demo Video

[![Demo Video](https://user-images.githubusercontent.com/75041108/219482396-b376b745-1720-4ca6-ac15-43ccd6af2326.jpg)](https://drive.google.com/file/d/1i7PyFuSTKnb9le05G5PPD7PM_gjdeP_6/view?usp=sharing)
