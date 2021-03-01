package com.company;
import java.util.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int[] Availableblock={500,300,120,80,210,20}; //The array is editable because the methods have been coded in dynamic style.
        int[] process={100,320,200,75,120}; //The array is editable because the methods have been coded in dynamic style.
        System.out.print("To use First Fit Algorithm enter 1, to Best Fit Algorithm enter 2, to Worst Fit Algorithm enter 3 ");
        int a= input.nextInt();
        switch (a){
            case 1:
                AllocateFF(Availableblock,process);
                break;
            case 2:
                AllocateBF(Availableblock,process);
                break;
            case 3:
                AllocateWF(Availableblock,process);
                break;

        }
    }

    public static void AllocateFF(int[] block,int[] process){ //This is the First Fit Allocation Method. It allocates the process into the first convenient block.
        int c=0;
        int g=0;
        int[] Alloc= new int[process.length]; //I created an array to store the sequence of which block is used for allocation
        int[] processseq=new int[process.length]; //I created an array to store the sequence of which process allocated
        ArrayList<Integer> notavaible=new ArrayList<>(); //I created an ArrayList to store processes that are not allocated into any block
        for(int i=0;i< process.length;i++){  //I created an outer for loop to traverse through the processes
            boolean flag=false; //Flag is for control that if the process[i] size is lesser than any available blocks (block[j]) or not
           for(int j=0;j< block.length;j++){ //I created an inner for loop to traverse through the blocks
               if(process[i]<=block[j]){ //To check which block is convenient for allocation. It's size should be greater or equal than the process[i]
                   Alloc[g++]=block[j]; //If it founds the first convenient block it stores that block in the Alloc Array.
                   block[j]=block[j]-process[i]; //As we allocated the process in block[j] we should subtract the process size from block[j]
                   processseq[c++]=process[i]; //Here we store the allocated process
                   flag=true; //To indicate that we allocated a process in an available block
                   break; //As we found the convenient block for process[i] we break the inner loop, and do the same things for other processes.
               }

           }
           if(flag==false){ //If we can't find available block for allocation we add that process into notavailable array.
               notavaible.add(process[i]);

           }

        }
        for (int k=0;k< Alloc.length;k++){ //This loop is for printing sequence of allocated processes and blocks.
            if(processseq[k]!=0 && Alloc[k]!=0) {
            System.out.println("Which process allocated which block");
            System.out.println("Allocated process: " + "(seq) " + k + " " + processseq[k]);
            System.out.println("Data block: "+"(seq) " +k+" "+Alloc[k]);
            }
        }
        if(notavaible.size()>=1) { //If there is a 1 or more element in not-available array it prints the elements of the array
            for (int p = 0; p < notavaible.size(); p++) {
                System.out.println("The processes that can not allocated: " + notavaible.get(p));
            }

        }

    }
    public static void AllocateBF(int[] block,int[] process){ //This is the Best Fit Allocation Method. It allocates the process into the minimum-sized convenient block so it is the most efficient method to use
        int c=0;
        int g;
        int y=0;
        int[] Alloc= new int[process.length]; //I created an array to store the sequence of which block is used for allocation
        int[] processseq=new int[process.length]; //I created an array to store the sequence of which process allocated
        int[] mincalc=new int[block.length]; //To store available blocks for a specific process
        ArrayList<Integer> notavaible=new ArrayList<>();  //I created an ArrayList to store processes that are not allocated into any block
        for(int i=0;i<process.length;i++){ //I created an outer for loop to traverse through the processes
            boolean flag=false; //Flag is for control that if the process[i] size is lesser than any available blocks (block[j]) or not.(It returns true if and only if process size lesser than the block size)
            for(int j=0;j< block.length;j++){ //To traverse through the blocks.
                if(process[i]<=block[j]){ //Checks that if process size is lesser or equal than block size or not
                    mincalc[j]=block[j]; //If process size is less or equal than any block it adds that block to mincalc array
                    flag=true; //It sets flag true to indicate 1 or more block is convenient for process[i]
                }

            }
            if(flag==false){ //If flag remains false then it means that there is no available block for process[i] then it adds that process into not available arraylist and continues the loop.
                notavaible.add(process[i]);
                continue;
            }
            g=indexofminval(mincalc); //Here we are using a method for finding the index of minimum-sized convenient block
            Alloc[c++]=mincalc[g]; //As we found that block we add it into Alloc array
            processseq[y++]=process[i];//We add the allocated process into proceesseq array.
            block[g]=mincalc[g]-process[i]; //We subtract the process[i] from  the used block, so we are updating the block[g]'s block size.
            mincalc= new int[block.length];  //Here we reset the mincalc array to use in future.

        }
        for (int k=0;k< Alloc.length;k++){ //This loop is for printing sequence of allocated processes and blocks.
            if(processseq[k]!=0 && Alloc[k]!=0) {
                System.out.println("Which process allocated which block");
                System.out.println("Allocated process: " + "(seq) " + k + " " + processseq[k]);
                System.out.println("Data block: " + "(seq) " + k + " " + Alloc[k]);
            }

        }
        if(notavaible.size()>=1) { //If there is a 1 or more element in not-available array it prints the elements of the array
            for (int p = 0; p < notavaible.size(); p++) {
                System.out.println("The processes that can not allocated: " + notavaible.get(p));
            }

        }

    }

    public static void AllocateWF(int[] block,int[] process){ //This is the Worst Fit Allocation Method. It allocates the process into the maximum-sized convenient block so it is inefficient to use
        int c=0;  //This method is mostly the same as Best Fit Allocation method but this time maximum-sized convenient block used for allocation
        int g;
        int y=0;
        int[] Alloc= new int[process.length]; //I created an array to store the sequence of which block is used for allocation
        int[] processseq=new int[process.length];  //I created an array to store the sequence of which process allocated
        int[] maxcalc=new int[block.length]; //To store convenient blocks for a process
        ArrayList<Integer> notavaible=new ArrayList<>();
        for(int i=0;i<process.length;i++){ //Same as Best Fit Allocation Method
            boolean flag=false;
            for(int j=0;j< block.length;j++){ //Same as Best Fit Allocation Method
                if(process[i]<=block[j]){
                    maxcalc[j]=block[j];
                    flag=true;
                }

            }
            if(flag==false){//If flag remains false then it means that there is no available block for process[i] then it adds that process into not available arraylist
                notavaible.add(process[i]);
                continue;
            }
            g=indexofmaxval(maxcalc); //Here we used a method to find the index of the maximum value in maxcalc array.
            Alloc[c++]=maxcalc[g];
            processseq[y++]=process[i];
            block[g]=maxcalc[g]-process[i];
            maxcalc= new int[block.length];

        }
        for (int k=0;k< Alloc.length;k++){
            if(processseq[k]!=0 && Alloc[k]!=0) {
                System.out.println("Which process allocated which block");
                System.out.println("Allocated process: " + "(seq) " + k + " " + processseq[k]);
                System.out.println("Data block: " + "(seq) " + k + " " + Alloc[k]);
            }

        }
        if(notavaible.size()>=1) {
            for (int p = 0; p < notavaible.size(); p++) {
                System.out.println("The processes that can not allocated: " + notavaible.get(p));
            }

        }


    }
    public static int indexofminval(int[] array){
        int index = 0;

        for(int j=0;j< array.length;j++) {
            if (array[j] != 0) {
                index=j;
                break;
            }
        }
        int min = array[index];

        for (int i = 1; i < array.length; i++){
            if (array[i] <= min && array[i]!=0){
                min = array[i];
                index = i;
            }
        }
        return index;
    }

    public static int indexofmaxval(int[] array) {
        if (array.length == 0) {
            return -1;
        }
        int max = array[0];
        int ind = 0;

        for(int i=1; i<array.length; i++) {
            if (max < array[i]) {
                ind = i;
                max = array[i];
            }
        }
        return ind;
    }
}
