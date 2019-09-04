# Aeróglifo

Graph | Table
:-------------------------:|:-------------------------:
![](https://github.com/CeuAzul/Aeroglifo/blob/master/assets/graphs.png)  |  ![](https://github.com/CeuAzul/Aeroglifo/blob/master/assets/table.png)


![cover](https://github.com/CeuAzul/Aeroglifo/blob/master/assets/Mainscreen.gif) 

## What is Aeróglifo?
Aeróglifo is a post-flight data analysis software for UAVs. It reads a CSV file from a flight and organizes the data for an optimized readability. This software enables the user to **generate graphics** for multiple flight parameters and **add filters**. Aeróglifo has a built-in **video analysis tool** in which is possible to **synchronize flight data and a video**.

## How to use?
- Download the latest [release](https://github.com/CeuAzul/Aeroglifo/releases) of Aeróglifo.
- Unzip the .rar file
- Execute the .jar file

#### Opening a data file
To open a file go to **Iniciar > Abrir arquivo** and select the file you want to open. Aeróglifo expects a .csv file, with the first column being the time reference. If the second line contains information of the metrics, select this option in the box. 

#### Plotting graphs
In the menu **Gráfico** you can select the data field and it will appear on the screen, in which you can zoom in or out, and move in any direction you want. If your file contains Weight On Wheels info, you can detect automatically the moment of takeoff and landing. You can stack two graphs above each other on the **plus** button on the left. 

#### Video analysis
Click on the **Análise interativa** button on top right of screen to start your video analysis. For each gadgets' parameters, select the corresponding data in your file. Select the video you want to open. Remember to click on **Sincroniar dados** to synchronize the video and the data time. 


## How to contribute?

- Fork this repository.
- Clone the project in your local files.
- Install [Eclipse](https://www.eclipse.org) IDE.
- Choose a workspace of your preference.
- Import an existing project and select the root of this repository.
- Compile and be happy :blush:


## An advise

> **Never** use Java for data analysis. Don't be stupid :wink:

## Contact

Developed with :heart: by [Leonardo Mariga](https://github.com/leomariga) (leomariga@gmail.com)

**Special thanks to:** Caroline Mariga and Céu Azul Team. 
