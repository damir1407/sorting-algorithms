import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Algorithms algs = new Algorithms(args);
        algs.testSort();
    }
}

class Algorithms{
    Sort sortAlgorithm;

    public Algorithms(String[] rawArguments){
        Args args = new Args(rawArguments);

        switch(args.getSortingAlg()){
            case "bs": this.sortAlgorithm = new BubbleSort(args); break;
            case "ss": this.sortAlgorithm = new SelectionSort(args); break;
            case "is": this.sortAlgorithm = new InsertionSort(args); break;
            case "hs": this.sortAlgorithm = new HeapSort(args); break;
            case "qs": this.sortAlgorithm = new QuickSort(args); break;
            case "ms": this.sortAlgorithm = new MergeSort(args); break;
        }
    }

    public void testSort(){
        switch(this.sortAlgorithm.getArgs().getType()){
            case "trace":
                this.sortAlgorithm.sort();
                break;
            case "count":
                this.sortAlgorithm.sort();
                this.sortAlgorithm.getResults().printResults();
                this.sortAlgorithm.sort();
                this.sortAlgorithm.getResults().printResults();
                this.sortAlgorithm.getArgs().changeOrder();
                this.sortAlgorithm.sort();
                this.sortAlgorithm.getResults().printResults();
                break;
        }
    }
}

class Args{
    private int[] array;
    private int length;
    private String sortingAlg, type, order;

    public Args(String[] args){
        this.type = args[0];
        this.sortingAlg = args[1];
        this.order = args[2];
        if(args.length == 4) {
            this.length = Integer.parseInt(args[3]);
            initArray();
        }
        else{
            initArraySpecial();
        }
    }

    private void initArray(){
        this.array = new int[this.length];
        Scanner s = new Scanner(System.in);
        for(int i=0; i < array.length; i++){
            array[i] = s.nextInt();
        }
    }

    private void initArraySpecial(){
        Scanner s = new Scanner(System.in);
        int[] dumbArray = new int[1000000];
        int count=0;

        while(s.hasNext()){
            dumbArray[count] = s.nextInt();
            count++;
        }

        this.array = new int[count];
        for(int i=0; i<array.length;i++){
            this.array[i] = dumbArray[i];
        }
    }

    public int[] getArray(){
        return this.array;
    }
    public String getSortingAlg(){
        return this.sortingAlg;
    }
    public String getType(){
        return this.type;
    }
    public String getOrder(){
        return this.order;
    }
    public void changeOrder(){
        if(this.order.equals("up")) {
            this.order = "down";
        }else{
            this.order = "up";
        }
    }
    public void setArray(int[] arr){
        this.array = arr;
    }

}

class Results{
    int comparisonCount;
    int moveCount;

    public Results(int comparisonCount, int moveCount){
        this.comparisonCount = comparisonCount;
        this.moveCount = moveCount;
    }

    public void printResults(){
        System.out.println(this.comparisonCount + " " + this.moveCount);
    };
}

class Sort{
    protected Args args;
    protected Results res;

    public Sort(Args args){
        this.args = args;
    }

    public Args getArgs(){
        return this.args;
    }

    public Results getResults(){
        return this.res;
    }

    public void ispis(int[] array, int cnt){
        for(int i = 0; i < array.length; i++){
            if(cnt == i) System.out.print("| ");
            System.out.print(array[i] + " ");
        }
        if (cnt == array.length) System.out.print("|");
        System.out.println();
    }

    public void swap(int i, int j, int[] array){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public void sort(){}
}

class BubbleSort extends Sort{

    public BubbleSort(Args args){
        super(args);
    }

    @Override
    public void sort(){
        int[] array = this.args.getArray();
        String order = this.args.getOrder();
        String type = this.args.getType();

        int comparisonCount = 0;
        int moveCount = 0;

        int count=0;
        if(type.equals("trace")) ispis(array,count);

        while(count!=array.length){
            for(int i = array.length-1; i > count; i--){
                if(order.equals("up")) {
                    comparisonCount++;
                    if (array[i] < array[i - 1]) {
                        moveCount+=3;
                        swap(i, i - 1, array);
                    }
                }
                else if(order.equals("down")){
                    comparisonCount++;
                    if (array[i] > array[i - 1]) {
                        moveCount+=3;
                        swap(i, i - 1, array);
                    }
                }
            }
            count++;
            if(count!=array.length && type.equals("trace")) ispis(array,count);
        }

        if(type.equals("count")) this.res = new Results(comparisonCount, moveCount);
    }
}

class SelectionSort extends Sort{
    public SelectionSort(Args args){
        super(args);
    }
    @Override
    public void sort(){
        int[] array = this.args.getArray();
        String order = this.args.getOrder();
        String type = this.args.getType();

        int comparisonCount = 0;
        int moveCount = 0;
        int first = 0;

        if(type.equals("trace")) ispis(array,first);

        while(first!=array.length-1){
            int min = first;

            for(int i = first+1; i < array.length; i++) {
                if(order.equals("up")) {
                    comparisonCount++;
                    if (array[i] < array[min]) {
                        min = i;
                    }
                }
                else if(order.equals("down")){
                    comparisonCount++;
                    if (array[i] > array[min]) {
                        min = i;
                    }
                }
            }

            moveCount+=3;
            swap(first,min,array);
            first++;
            if(type.equals("trace")) ispis(array,first);
        }
        if(type.equals("count")) this.res = new Results(comparisonCount, moveCount);
    }
}

class InsertionSort extends Sort{
    public InsertionSort(Args args){
        super(args);
    }
    @Override
    public void sort(){
        int[] array = this.args.getArray();
        String order = this.args.getOrder();
        String type = this.args.getType();

        int comparisonCount = 0;
        int moveCount = 0;
        if(type.equals("trace")) ispis(array,1);
        for( int i = 1; i < array.length; i++ ){
            for( int j = i - 1; j >= 0; j-- ){
                if(order.equals("up")) {
                    comparisonCount++;
                    if (array[j + 1] < array[j]) {
                        moveCount+=3;
                        swap(j + 1, j, array);
                    } else break;
                }
                else if(order.equals("down")){
                    comparisonCount++;
                    if (array[j + 1] > array[j]) {
                        moveCount+=3;
                        swap(j + 1, j, array);
                    } else break;
                }
            }
            if(type.equals("trace")) ispis(array,i+1);
        }
        if(type.equals("count")) this.res = new Results(comparisonCount, moveCount);
    }
}

class HeapSort extends Sort{
    int comparisonCount;
    int moveCount;
    public HeapSort(Args args){
        super(args);
    }
    @Override
    public void sort(){
        int[] arr = this.args.getArray();
        String order = this.args.getOrder();
        String type = this.args.getType();

        comparisonCount=0;
        moveCount=0;
        int r = arr.length-1;

        sestavi_zacetno_kopico(arr, order);

        // sled
        if(type.equals("trace")) {
            ispisHS(arr, arr.length);
            System.out.println();
        }

        while(r>0){
            moveCount+=3;
            swap(0,r,arr);
            if(r>1) pogrezni(arr,0,r-1, order);

            // sled
            if(type.equals("trace")) {
                ispisHS(arr, r);
                System.out.println();
            }
            r=r-1;

        }
        if(type.equals("count")) this.res = new Results(this.comparisonCount, this.moveCount);
    }

    private void sestavi_zacetno_kopico(int[] arr, String ord){
        int n=arr.length-1;

        for(int i = n/2; i>=0; i--) {
            pogrezni(arr,i, n, ord);
        }
    }

    private void pogrezni(int[] arr, int i, int j, String ord){

        int s;

        if(i<=(j-1)/2){
            s = 2*i+1;

            if((s+1)<=j){
                if(ord.equals("up")) {
                    this.comparisonCount++;
                    if (arr[s] < arr[s + 1]) {
                        s = s + 1;
                    }
                }
                else if (ord.equals("down")){
                    this.comparisonCount++;
                    if (arr[s] > arr[s + 1]) {
                        s = s + 1;
                    }
                }
            }
            if(ord.equals("up")) {
                this.comparisonCount++;
                if (arr[i] < arr[s]) {
                    this.moveCount+=3;
                    swap(i, s, arr);
                    pogrezni(arr, s, j, ord);
                }
            }
            else if(ord.equals("down")){
                this.comparisonCount++;
                if (arr[i] > arr[s]) {
                    this.moveCount+=3;
                    swap(i, s, arr);
                    pogrezni(arr, s, j, ord);
                }
            }
        }
    }

    private void ispisHS(int[] array, int S){
        int gIndex = 2;
        for( int index = 0; index < S; index++ ) {
            if( index == gIndex - 2 && S!=1 && index!=S-1){
                System.out.print(array[index] + " | ");
                gIndex *= 2;
            } else {
                System.out.print(array[index] + " ");
            }
        }
    }

}

class QuickSort extends Sort{
    int comparisonCount;
    int moveCount;
    public QuickSort(Args args) { super(args); }

    @Override
    public void sort(){
        int[] array = this.args.getArray();
        String order = this.args.getOrder();
        String type = this.args.getType();

        comparisonCount=0;
        moveCount=0;

        quickSort(array,0,array.length-1,order,type);
        if(type.equals("count")) this.res = new Results(this.comparisonCount, this.moveCount);
    }

    private void quickSort(int[] array,int p, int z, String order, String type){

        if(array.length <= 1) return;
        else{
            this.moveCount++;
            int m = array[(p+z)/2];

            int[] newValues = prerazporedi(array, m, p, z, order);

            if(type.equals("trace")) ispisQS(p,z,newValues[0],newValues[1],array);

            if(p < newValues[1]) quickSort(array, p, newValues[1], order, type);

            if(newValues[0] < z) quickSort(array, newValues[0], z, order, type);
        }
    }
    private int[] prerazporedi(int[] array, int m, int p, int z, String order){
        int i = p;
        int j = z;
        int newValues[] = new int[2];

        while(i<=j){
            if(order.equals("up")) {
                this.comparisonCount++;
                while (array[i] < m && i <= z) {
                    i++;
                    this.comparisonCount++;
                }

                this.comparisonCount++;
                while (array[j] > m && j >= p) {
                    j--;
                    this.comparisonCount++;
                }
            }
            else if(order.equals("down")){
                this.comparisonCount++;
                while (array[i] > m && i <= z) {
                    i++;
                    this.comparisonCount++;
                }

                this.comparisonCount++;
                while (array[j] < m && j >= p) {
                    j--;
                    this.comparisonCount++;
                }
            }
            if(i<=j){
                this.moveCount+=3;
                swap(i,j,array);
                i++;
                j--;
            }
        }
        newValues[0] = i;
        newValues[1] = j;
        return newValues;
    }
    private void ispisQS(int p, int z, int i, int j, int[] array){
        for(int k = p; k <= j; k++) {
            System.out.print(array[k] + " ");
        }
        if(j+1 == i) System.out.print("| ");
        else if(j+2==i) System.out.print("| " + array[i-1] + " ");
        System.out.print("| ");

        for(int k = i; k <= z; k++){
            System.out.print(array[k] + " ");
        }
        System.out.println();
    }
}

class MergeSort extends Sort{
    int comparisonCount;
    int moveCount;
    public MergeSort(Args args) { super(args); }

    @Override
    public void sort(){
        int[] array = this.args.getArray();
        String order = this.args.getOrder();
        String type = this.args.getType();

        comparisonCount=0;
        moveCount=0;

        this.args.setArray(mergeSort(array,0,array.length, type, order));
        moveCount++;
        if(type.equals("count")) this.res = new Results(this.comparisonCount, this.moveCount);
    }

    private int[] mergeSort( int[] array, int start, int end, String type, String order ){
        int diff = end - start;
        if( diff == 1 ) {
            //this.moveCount++;
            return new int[]{array[start]};
        }
        else {
            int mid = (end+start)/2;
            if((end-start)%2!=0) mid += 1;
            if(type.equals("trace")) ispisMS(array,mid,start,end);

            int[] half1 = mergeSort( array, start, mid ,type, order);
            int[] half2 = mergeSort( array, mid, end ,type, order);
            int[] merged = new int[end-start];

            //merge step
            int k = 0, i = 0, j = 0;
            while( k < merged.length && i < half1.length && j < half2.length){
                if(order.equals("up")) {
                    this.comparisonCount++;
                    if (half1[i] <= half2[j]) {
                        this.moveCount++;
                        merged[k] = half1[i];
                        i++;
                    } else {
                        this.moveCount++;
                        merged[k] = half2[j];
                        j++;
                    }
                    k++;
                }
                else{
                    this.comparisonCount++;
                    if (half1[i] >= half2[j]) {
                        this.moveCount++;
                        merged[k] = half1[i];
                        i++;
                    } else {
                        this.moveCount++;
                        merged[k] = half2[j];
                        j++;
                    }
                    k++;
                }
            }
            int p = k;
            if(i<k){
                for(int g = i; g<half1.length; g++,p++){
                    this.moveCount++;
                    merged[p] = half1[g];
                }
            }
            if(j<k){
                for(int h = j; h < half2.length; h++,p++){
                    this.moveCount++;
                    merged[p] = half2[h];
                }
            }
            if(type.equals("trace")) ispisMS(merged,-1,0,merged.length);
            this.moveCount++;
            return merged;
        }


    }

    private void ispisMS(int[] array, int mid,int start, int end){
        for(int i = start; i < end; i++){
            if(i == mid) System.out.print("| " + array[i] + " ");
            else System.out.print(array[i] + " ");

        }
        System.out.println();

    }
}