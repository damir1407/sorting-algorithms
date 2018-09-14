# Sorting Algorithms
Program for displaying traces and counting operations for sequence sorting algorithms.

## Technology
 - Java

## Usage
  - Open CLI or Terminal
  - Redirect to project directory
  - Execute:
      ```
      ..\path\to\project\sorting-algorithms\src> javac Main.java
      ..\path\to\project\sorting-algorithms\src> java Main trace bs up 6
      ```
  - The first argument specifies the operating mode of the program:
    - trace - the program displays the trace of the algorithm. The output displays the current state of the array. The border between the individual parts of the table (between sorted and unsorted part) is marked with the "|" sign.
    - count - the program displays the number of element comparisons and the number of element assignments (one swap counts for three assignments). Attention: only operations on array elements are counted (index comparison is not counted). 
    Count argument prints the number of operations for three different cases:
      - sorting the specified sequence of numbers in the given direction;
      - sorting an already sorted sequence in a given direction;
      - sorting the already sorted sequence in the reverse direction.

  - The second argument of the program specifies the tag of the selected algorithm:
    - bs - bubble sort;
    - ss - selection sort;
    - is - insertion sort;
    - hs - heap sort;
    - qs - quick sort;
    - ms - merge sort.

  - The third argument gives the direction of regulation:
    - up - ascending;
    - down - descending.
  - The fourth argument specifies the size of the sequence/array (this argument is not mandatory, because the program is capable of dynamically increasing the size of the array)
  - And finally read the input. Read the sequence of numbers from the standard input. The numbers have to be separated by space.
    ```
    ..\path\to\project\sorting-algorithms\src> java Main trace bs up 6
    8 5 6 1 7 2
    ```
  - Examples of usage and corresponding output:
    ```
    ..\path\to\project\sorting-algorithms\src> java Main trace bs up 6
    8 5 6 1 7 2
    | 8 5 6 1 7 2
    1 | 8 5 6 2 7 
    1 2 | 8 5 6 7 
    1 2 5 | 8 6 7 
    1 2 5 6 | 8 7 
    1 2 5 6 7 | 8
    ```
    ```
    ..\path\to\project\sorting-algorithms\src> java Main count bs up 6
    8 5 6 1 7 2
    15 30
    15 0
    15 45
    ```
