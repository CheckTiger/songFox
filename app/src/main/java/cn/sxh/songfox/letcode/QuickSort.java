package cn.sxh.songfox.letcode;

/**
 * 快排算法实现 https://www.cnblogs.com/sunriseblogs/p/10009890.html
 */
public class QuickSort {

    public static void main(String[] args) {
        int a[] = {5,7,1,6,4,8,3,2,8,10};
        quickSort(a, 0, a.length-1);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();

    }

    public static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    public static void quickSort(int[] arr, int index1, int index2) {
        if (index1 < index2) {
            int i = split(arr, index1, index2);
            quickSort(arr,index1,i-1);
            quickSort(arr,i+1,index2);
        }
    }

     public static int split(int a[], int low, int high) {
        int i = low;    //i指向比较元素的期望位置
        int x = a[low];    //将该组的第一个元素作为比较元素
        //从第二个元素开始，若当前元素大于比较元素，将其跳过
         for (int j = low + 1; j <= high; j++) {
             //若找到了小于比较元素的元素，将其与前面较大的元素进行交换
             if (a[j] <= x) {
                 i++;
                 if (i != j) {
                     swap(a, i, j);
                 }
             }
         }

        swap(a, i, low); //将比较元素交换到正确的位置上
       return i;    //返回比较元素的位置
    }
}
