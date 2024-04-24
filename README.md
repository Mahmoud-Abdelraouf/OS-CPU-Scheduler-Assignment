# CPU Scheduler Project

## Overview
This project aims to implement a CPU Scheduler application that supports various scheduling algorithms. The application allows users to simulate the execution of processes with different scheduling algorithms, visualize the scheduling process through a Gantt Chart, and calculate average waiting time and average turnaround time.

## Supported Schedulers
1. First-Come, First-Served (FCFS)
2. Shortest Job First (SJF) - Preemptive and Non-Preemptive
3. Priority - Preemptive and Non-Preemptive (Lower priority numbers indicate higher priority)
4. Round Robin

## Features
- Dynamic addition of processes during execution
- Live scheduling with each time unit mapped to 1 second
- Real-time update of remaining burst time for processes
- Option to run existing processes without live scheduling
- Graphical User Interface (GUI) desktop application

## Usage
1. Download the executable file from the provided link.
2. Launch the application.
3. Select the desired scheduling algorithm.
4. Enter the necessary information for each process according to the selected algorithm.
5. Optionally, add new processes dynamically during execution.
6. Run the scheduler to visualize the scheduling process and view the Gantt Chart.
7. Review the calculated average waiting time and average turnaround time.

## Screenshots
![Screenshot 1](screenshots/screenshot1.png)
![Screenshot 2](screenshots/screenshot2.png)

## Requirements
- Java Runtime Environment (JRE)
- Operating System: Windows

## Build Instructions
1. Clone the repository: `git clone <repository-url>`
2. Navigate to the project directory: `cd cpu-scheduler`
3. Build the project: `mvn package`

## Contributors
- [Karim Ibrahim](https://github.com/Karim-308)
- [Eslam Mohamed](https://github.com/SemoMoh)
- [Mahmoud Abdelraouf](https://github.com/Mahmoud-Abdelraouf)
- [Abdelrahman Elsayed](https://github.com/d3cypherd)
- [Adham Khaled](https://github.com/adhamkhaled312)
- [Ahmed Khaled](https://github.com/Ahmed-Khaled-Abdelmaksod)
