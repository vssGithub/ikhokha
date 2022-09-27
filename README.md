# iKhokha Tech Check

> **Note**
Please do not make a public fork of this repository

### Customer Review Analyzer
---
### Introduction

At iKhokha, we love receiving feedback from our customers - especially when it's positive :) We would like to analyze our customer online reviews and comments and the new graduate was tasked to write a Java Application that will run through all the comments and create a report with totals to be used for business intelligence and marketing. 

We currently have three metrics that we keep track of:

  - Total number of comments that are shorter than 15 characters
  - Total number of comments that refer to the "Mover" device
  - Total number of comments that refer to the "Shaker" device

The daily comments are stored in text files under the **docs** directory within the project. Each line represents a single comment by a customer. All files in the directory get analyzed and the report gets printed to the console.

#### Prerequisites
  - Git
  - JDK 1.8 or later
  - An IDE of your choice (Eclipse, IntelliJ IDEA etc)

---
### Tasks
Clone this repo and complete the 3 tasks below. Be sure to read each one carefully and make your changes to the existing code provided in the project. Once complete, you can upload it to your own repo and share the url. Alternatively you can zip it up and send via email. Good luck!

##### 1. Debugging and Logical problem solving

Unfortunately our graduate seems to have a couple of bugs in his code. Word is that the report looks like it only shows the last day's comments. Emma from marketing also mentioned that some values are too low, particularly when more than one product (Mover or Shaker) is mentioned in the same comment. Your task is to fix these bugs so that the correct values are displayed in the final report.

##### 2. Object Oriented design

We foresee that over time, a lot of new metrics will be added to our report and our list of *if* statements will become long and clunky. By utilizing your skills in Object Oriented design, rewrite the matcher algorithm to make it more extensible/pluggable for adding new metrics.

Once you've made your change, add the following additional metrics:

  - Some customers use the comments section to ask questions. Add a new metric named **QUESTIONS** that displays a count of comments that contain one on more question marks "?"

  - Marketing noticed that some of the comments are spam. Add a new total called **SPAM** to the report, which should count lines that contain a url to a web page.
 
##### 3. Concurrency

Our social media pages are becoming more popular! We expect our daily comment files to become huge, resulting in long processing times per file. The current code processes the files sequentially. Change the implementation to process them concurrently using separate threads (ie one thread per file) and then consolidate the results to print the report.

Bonus question: Bear in mind that at any given time, there can be thousands of comment files in the docs folder which might crash the app if we spawn threads uncontrollably.

====================================================================

# Notes

The Main branch has the implementation for OOP using an abstract class approach.
If you run the project in the Main branch, this will
- parse the comment files
- collate the results into a single file
- process this file against each metric that has been setup
- print the report

## 1. Setting up a Metric - Part 1
In the Main branch, in order to setup a new __Metric__, do the following
- add a new Metric class in the metrics package (eg. MoverMetric.java)
- extend the newly added Metric with the _MetricType_ abstract class
- in the Override, add the functionality as required
- after the required functionality has been established, call _ProcessMetric_

## 2. Setting up a Metric - Part 2
Following from 1 above, when _ProcessMetric_ is called, you need to pass a reference to the map which contains the data and the metric to be monitored, eg.
- metricAnalyzer.ProcessMetric(metricAnalyzer.resultsMap, targetMetric);
  - here, targetMetric is simply a key (string) that will be stored in the map and incremented for every occurrence found

## 3. Wiring up a Metric
In Main.java, call Addmetric with a key and the concrete implementation, eg.
- metric.AddMetric("Mover", new MoverMetric());
  - this assumes that the concrete implementation has been created correctly

## 4. Sooo...what's happening in main then?
Effectively, main has been structured to do the following
- call ProcessFiles
  - this parses all the comment files
  - collates the information into a single file
- we new up a MetricAnalyzer
- MetricMediator needs both the collated files and something to analyze the data from collated files, ie. it needs the MetricAnalyzer
- we then setup the metrics that we created (ie. the concrete implementations) by calling AddMetric
- we call Handle which effectively iterates through a map and calls each metric
- we then call CreateReport which simply iterates through the map and outputs the data collected

## 5. What about interfaces then?
The _Main_ branch has the implementation for an abstract class approach.  
In the _try/use-interface_ branch, everything explained above has been achieved with interfaces

- similar to point 1, we setup the Metric but implement the IMetricType interface
- In Main.java, call Addmetric with a key and the concrete implementation for the __interface__, eg.
  - metric.AddMetric("Mover", new MoverMetric2());
  - here, Addmetric (in the mediator) has been overloaded
- when the mediator calls AnalyzeMetric, this also calls an overloaded method
- the rest of the functionality should be the same  
__Please note that in order to toggle (or test) between the abstract class or interface, you need to set the USE_ABSTRACT_CLASS flag in MetricMediator.java__

## 6. What's up with the branches..?
Usually, after a branch has been merged into master/main, it's also deleted.  
Here, I left this for easier tracking/inspection. On my side, I can just view the branches in source tree in my remote.
- _feature/implement-oop_ => merged into Main; this branch can be deleted
- _feature/implement-concurrency_ => merged into Main; this branch can be deleted
- _update/metric-package_ => merged into Main; this branch can be deleted
- _try/use-interface_ => this branch has the implementation for using interfaces; as this was an attempt to see how things work, it was done in a try branch. This could be tidied up in a feature branch and submitted for merge review for merge into Main/Master or possibly, this branch itself could be submitted once tidied up appropriately
