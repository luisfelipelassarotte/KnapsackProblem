package knapsack;

import java.util.ArrayList;
import java.util.List;

public class RecursionMethod {

    static class Result {
        int maxValue;
        List<Integer> items;

        Result(int maxValue, List<Integer> items) {
            this.maxValue = maxValue;
            this.items = items;
        }
    }

    static Result mochila(int capacity, int[] peso, int[] valor, int n) {
        if (n == 0 || capacity == 0) {
            return new Result(0, new ArrayList<>());
        }

        if (peso[n - 1] > capacity) {
            return mochila(capacity, peso, valor, n - 1);
        } else {
            Result exclude = mochila(capacity, peso, valor, n - 1);

            Result include = mochila(capacity - peso[n - 1], peso, valor, n - 1);
            include.maxValue += valor[n - 1];
            include.items.add(n);

            if (exclude.maxValue > include.maxValue) {
                return exclude;
            } else {
                return include;
            }
        }
    }

    public static void main(String[] args) {
        int[] valor = new int[]{60, 100, 120, 150, 200};
        int[] peso = new int[]{10, 10, 10, 10, 10};
        int capacity = 50;
        int n = valor.length;

        Result result = mochila(capacity, peso, valor, n);

        System.out.println("Valor máximo: " + result.maxValue);
        System.out.print("Itens na mochila: ");
        for (int item : result.items) {
            System.out.print("Item " + item + " ");
        }
    }
}
