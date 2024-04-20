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
- Operating System: Windows, macOS, or Linux

## Build Instructions
1. Clone the repository: `git clone <repository-url>`
2. Navigate to the project directory: `cd cpu-scheduler`
3. Build the project: `mvn package`

## Contributors
- [Name 1](https://github.com/user1)
- [Name 2](https://github.com/user2)
- [Name 3](https://github.com/user3)
- [Name 4](https://github.com/user4)
- [Name 5](https://github.com/user5)
- [Name 6](https://github.com/user6)
- [Name 7](https://github.com/user7)

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
