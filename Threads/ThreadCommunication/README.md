# ThreadCommunication
This project demonstrates communication between two threads.

## How to run the project

1. Download the zip file and extract it in your workspace.
2. Change directory to the extracted folder.
3. Run `javac src/*` to compile the project.
4. Change directory to src and run `java ThreadCommunication` to run the project.

## What it does?

There are 4 classes here.

1. Data - It describes the type of data to be used.
2. SendData - It implements Runnable. It uses BlockingQueue for sharing resources between the two threads. It will wait for queue to be non empty before accessing element and also wait for space to be available while adding element.
3. CubeData - It implements Runnable. It also uses same BlockingQueue and cubes the data in it and prints it.
4. ThreadCommunication - It initializes the BlockingQueue and the threads. It runs the threads to communicate between each other
