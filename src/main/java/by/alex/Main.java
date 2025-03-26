package by.alex;

import by.alex.list.List;
import by.alex.list.MyArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Testing MyArrayList:");
        List<Integer> arrayList = new MyArrayList<>();
        arrayList.add(5);
        arrayList.add(5);
        arrayList.add(5);
        arrayList.add(5);
        arrayList.add(2);
        arrayList.add(8);
        arrayList.add(1, 1);
        System.out.println("Size: " + arrayList.size());
        System.out.println("Element at index 2: " + arrayList.get(2));
        System.out.print("Before sort: ");
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.print(arrayList.get(i) + " ");
        }
        arrayList.sort();
        System.out.print("\nAfter sort: ");
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.print(arrayList.get(i) + " ");
        }
        arrayList.remove(5);
        System.out.println("\nAfter removing 5, size: " + arrayList.size());
    }
}