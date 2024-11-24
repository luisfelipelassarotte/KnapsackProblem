package knapsack;

import java.util.HashMap;
import java.util.Map;

public class RecursionWithQuantity {

    static class Result {
        int maxValue;
        int totalWeight;
        Map<Integer, Integer> items;

        Result(int maxValue, int totalWeight, Map<Integer, Integer> items) {
            this.maxValue = maxValue;
            this.totalWeight = totalWeight;
            this.items = items;
        }
    }

    static Result mochila(int capacity, int[] peso, int[] valor, int[] quantidade, int n) {
        if (n == 0 || capacity == 0) {
            return new Result(0, 0, new HashMap<>());
        }
        Result exclude = mochila(capacity, peso, valor, quantidade, n - 1);

        Result bestInclude = new Result(0, 0, new HashMap<>());
        for (int qty = 1; qty <= quantidade[n - 1]; qty++) {
            int totalWeight = qty * peso[n - 1];
            if (totalWeight <= capacity) {
                Result include = mochila(capacity - totalWeight, peso, valor, quantidade, n - 1);
                include.maxValue += qty * valor[n - 1];
                include.totalWeight += totalWeight;
                include.items.put(n, include.items.getOrDefault(n, 0) + qty);

                if (include.maxValue > bestInclude.maxValue) {
                    bestInclude = include;
                }
            } else {
                break;
            }
        }

        return (exclude.maxValue > bestInclude.maxValue) ? exclude : bestInclude;
    }

    public static void main(String[] args) {
        int[] valor = new int[]{60, 100, 120, 150, 200};
        int[] peso = new int[]{2, 6, 7, 8, 9};
        int[] quantidade = new int[]{3, 2, 4, 1, 5};
        int capacity = 50;
        int n = valor.length;

        Result result = mochila(capacity, peso, valor, quantidade, n);

        System.out.println("Valor máximo: " + result.maxValue);
        System.out.println("Peso total na mochila: " + result.totalWeight);
        System.out.println("Itens na mochila:");
        for (Map.Entry<Integer, Integer> entry : result.items.entrySet()) {
            System.out.println("Item " + entry.getKey() + " -> Quantidade: " + entry.getValue());
        }
    }
}
