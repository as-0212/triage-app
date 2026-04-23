# Earthquake Disaster Triage System
CS 2013-05  
Eric Liu  
Aidan Sanchez 
## Overview

This Java app models triage system designed for an Earthquake Disaster Scenario. Here is the
documentation for the making, refining, and testing of the system.

## Logic

The logic is as follows:
- Patients come into the hospital and are admitted in any random order by the user
- When patient admissions are done, the system will sort patients by injury level
  and demographic, which are put into the Priority Queue and sorted by age demographic
  as followes: Children > Elders > Adults.
- All other patients, no matter the age demographic, are put into the Normal Queue, which
  treats patients in FIFO order.

## Test Cases

**Priority Test**  
Description: Demonstrates priority system sorting who is treated first based on age demographic
and injury level
- Patient A: Adult with injury level 2
- Patient B: Child with injury level 5
Outcome: Patient B is treated before Patient A

**Demographic Test**  
Description: Demonstrates priority system sorting who is treated first based on age demographic
- Patient A: Adult with injury level 5
- Patient B: Elderly person with injury level 5
- Patient C: Child with patient level 5
Outcome: All patients are in priority queue in critical condition, so patients will be
treated based on age demographic; Child > Elderly > Adult

**FIFO Test**  
Description: Demonstrates priority system when two patients are admitted of the same injury
level and age demographic.
- Patient A: Child with injury level 2
- Patient B: Child with injury level 3
Outcome: Since either child isn't in critical condition, the system defaults to the Normal Queue
and therefore the patients are treated in FIFO order.

## Resources  
Claude AI  
Documentation:
1st prompt:
- Earthquake disaster
- Patient priority is based on injury level and if they are children or elderly
- Injury level is an integer from 1-5
- Patient superclass; Adult, elderly, children subclasses
- Priority queue and normal queue
- Patients with injury level 5 will be moved to Priority queue
- Once priority queue is empty, normal queue will continue

2nd Prompt:
- Needed to update code so that patient names are shown when queues are being viewed

Notes: Overall, Claude only needed two prompts to give us what we asked for based on the criteria 
in the first prompt. The second prompt was to refine the code so that it matched the rubric of 
the project, which I think can be considered a human error on our part for not specifying that.
