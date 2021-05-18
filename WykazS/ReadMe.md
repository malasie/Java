# WykazS
### This folder contains a program that allows to make something like a small datebase of students and their grades.
With function __"wstawStudenta"__ of WykazS class we can add to a list of students __"studenci"__ a new student (index number, name, grades).  
__"WstawOcene"__ allows as to add a student's grade. The functiom checks if the grade has a good format and is between 2 and 5 if not it throws exception __"ZlaOcenaException"__. Otherwise, if the grade meets the rules then it's added to ArrayList of grades called "oceny"  of student that index number is the same as given in the function.  
  
__"wypisz"__ is a void function that prints all students and their grades.  
With __"sortujA"__ we can sort the students by alphabetical order of their names.  
__"sortujS"__ in the other hand let us sort them by the average of their grades.  
__"sortuj"__ allows us to sort the students by the amount of grades that are greater or equal 4.   
The last function __"srednia"__ calculates the avarge of all students' grades and then prints it.
