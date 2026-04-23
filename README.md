**Overview**

This Java app models triage system designed for an Earthquake Disaster Scenario. Here is the
documentation for the making, refining, and testing of the system.

**Logic**

The logic is as follows:
- Patients come into the hospital and are admitted in any random order by the user
- When patient admissions are done, the system will sort patients by injury level
  and demographic, which are put into the Priority Queue and sorted by age demographic
  as followes: Children > Elders > Adults.
- All other patients, no matter the age demographic, are put into the Normal Queue, which
  treats patients in FIFO order.

**Test Cases**

*Priority Test*
Description: Showcase priority system sorting who is treated first based on age demographic
and injury level
- Patient A: Adult with injury level 2
- Patient B: Child with injury level 5
Outcome: Patient B is treated before Patient A

*Demographic Test*
Description: Showcase priority system sorting who is treated first based on age demographic
- Patient A: Adult with injury level 5
- Patient B: Elderly person with injury level 5
- Patient C: Child with patient level 5
Outcome: All patients are in priority queue in critical condition, so patients will be
treated based on age demographic; Child > Elderly > Adult

*FIFO Test*
Description: Demonstrates priority system when two patients are admitted of the same injury
level and age demographic.
- Patient A: Child with injury level 2
- Patient B: Child with injury level 3
Outcome: Since either child isn't in critical condition, the system defaults to the Normal Queue
and therefore the patients are treated in FIFO order.
