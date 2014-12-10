import java.util.*;

public class Sorting{
	public static void main (String[] args){
		boolean bubbleRun = true;
		boolean mergeRun = true;
		int listLength = 10;

		try{
			while(bubbleRun == true || mergeRun == true){
				double[] one = new double[listLength];
				double[] two = new double[listLength];

				for(int i = 0; i < listLength; i++){
					double num = Math.random();
					one[i] = num;
					two[i] = num;
				}

				if(bubbleRun == true){			
					long bubbleStartTime = System.currentTimeMillis();
					one = bubbleSort(one, bubbleRun);
					long bubbleEndTime = System.currentTimeMillis();
					long bubbleTotalTime = bubbleEndTime - bubbleStartTime;
					if(bubbleTotalTime >= 20000){
						bubbleRun = false;
					}
					System.out.println("BubbleSort time for " + listLength + " numbers: " + bubbleTotalTime);
				}

				if(mergeRun == true){
					long mergeStartTime = System.currentTimeMillis();
					two = mergeSort(two, mergeRun);
					long mergeEndTime = System.currentTimeMillis();
					long mergeTotalTime = mergeEndTime - mergeStartTime;
					if(mergeTotalTime >= 20000){
						mergeRun = false;
					}
					System.out.println("MergeSort time for " + listLength + " numbers: " + mergeTotalTime);
				}
				listLength *= 10;
			}
		} catch(OutOfMemoryError e){
			System.out.println("Java has run out of memory, Sorry.");
			System.exit(0);
		}
	}

	public static double[] bubbleSort(double[] input, boolean bubbleRun){
		long start = System.currentTimeMillis();
		long end = start + 20*1000;
		int j;
    	boolean flag = true;
    	double temp;
		while (flag){
            flag= false;
            if(System.currentTimeMillis() >= end){
            	bubbleRun = false;
            	break;
            }
            for(j=0; j<input.length-1; j++){
                if (input[j]>input[j+1]){
	                temp = input[j];
	                input[j] = input[j+1];
                    input[j+1] = temp;
                    flag = true;
                } 
            } 
        } 
		return input;
	}
	
	public static double[] mergeSort(double[] input, boolean mergeRun){
		if(input.length <= 1){
			return input;
		}
		double[] newA = new double[input.length/2];
		double[] newB = new double[input.length - newA.length];
		for(int i = 0; i < newA.length; i++){
			newA[i] = input[i];
		}
		System.arraycopy(input, newA.length, newB, 0, newB.length);
		mergeSort(newA, mergeRun);
		mergeSort(newB, mergeRun);
		merge(newA, newB, input, mergeRun);
		return input;
	}
	
	public static void merge(double[] newA, double[] newB, double[] input, boolean mergeRun){
		int newAElement = 0;
		int newBElement = 0;
		int inputElement = 0;

		long start = System.currentTimeMillis();
		long end = start + 20*1000;
		while (newAElement < newA.length && newBElement < newB.length){
            if (newA[newAElement] < newB[newBElement]) {
            	input[inputElement] = newA[newAElement];
            	newAElement++;
            } else{
            	input[inputElement] = newB[newBElement];
            	newBElement++;
            }
            inputElement++;
	        if(System.currentTimeMillis() >= end){
	        	mergeRun = false;
		        break;
		    }
        }
        System.arraycopy(newA, newAElement, input, inputElement, newA.length - newAElement);
        System.arraycopy(newB, newBElement, input, inputElement, newB.length - newBElement);
	}
}